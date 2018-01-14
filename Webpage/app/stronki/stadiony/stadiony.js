'use strict';

angular.module('myApp.stadiony', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/stadiony', {
            templateUrl: 'stronki/stadiony/stadiony.html',
            controller: 'StadionCtrl'
        });
    }])

    .service('StadionS', function ($resource) {
        this.Factory = $resource(url + 'stadiony/:id', {id: '@id'}, {
            'update': {method: 'PUT'}
        });
        this.columns = [
            {
                field: 'id',
                displayName: 'Id',
                enableFiltering: false
            },
            {
                field: 'boisko.nazwa',
                displayName: 'Nazwa'
            },
            {
                field: 'boisko.iloscMiejsc',
                displayName: 'Ilosc miejsc'
            },
            {
                field: 'boisko.miejscowosc',
                displayName: 'Miejscowosc'
            },
            {
                field: 'chroniPrzedDeszczem',
                displayName: 'Chroni przed deszczem',
                enableFiltering: false
            },
            editTemplate,
            delTemplate
        ];

        this.data = [
            {
                id: 0,
                nazwa: '',
                boisko: {id: 0, nazwa: '', iloscMiejsc: 0, miejscowosc: '', orlik: null, stadion: null, mecze: null},
                chroniPrzedDeszczem: false
            }
        ];
    })

    .controller('StadionCtrl', function ($scope, $filter, StadionS) {
        const Factory = StadionS.Factory;
        let data = StadionS.data;

        const empty = function () {
            return {id: 0, boisko: {id: 0, nazwa: '', iloscMiejsc: '', miejscowosc: ''}, chroniPrzedDeszczem: false};
        };

        $scope.entry = empty();
        $scope.edited = empty();

        const reload = function () {
            data = Factory.query(function () {
                $scope.grid.data = $filter('orderBy')(data, 'id');
            }, function (r) {
                alert(r)
            });
        };
        reload();

        $scope.grid = {
            enableFiltering: true,
            enableSorting: true,
            enableCellSelection: true,
            enableCellEditOnFocus: true,
            columnDefs: StadionS.columns,
            data: data
        };

        $scope.add = function (row) {//musi
            console.log(row);
            if (validateForm(row, 0)) {
                row.boisko.id = null;
                addF(row, Factory, reload);
                $scope.entry = empty();
                $scope.error = '';
            } else {
                $scope.error = 'Niepoprawne dane';
            }
        };

        function validateForm(row, i) {
            if(i===0){
                $scope.nazwaErr = (row.boisko.nazwa.length < 1) ? 'error' : 'normal';
                $scope.miejscowoscErr = (row.boisko.miejscowosc.length < 1) ? 'error' : 'normal';
                $scope.iloscMiejscErr = (row.boisko.iloscMiejsc === '' || row.boisko.iloscMiejsc === 0 || row.boisko.iloscMiejsc === null) ? 'error' : 'normal';
            }else{
                $scope.nazwaErr2 = (row.boisko.nazwa.length < 1) ? 'error' : 'normal';
                $scope.miejscowoscErr2 = (row.boisko.miejscowosc.length < 1) ? 'error' : 'normal';
                $scope.iloscMiejscErr2 = (row.boisko.iloscMiejsc === '' || row.boisko.iloscMiejsc === 0 || row.boisko.iloscMiejsc === null) ? 'error' : 'normal';
            }

            return !(row.boisko.nazwa.length < 1 || row.boisko.miejscowosc.length < 1 || row.boisko.iloscMiejsc === '' || row.boisko.iloscMiejsc === 0 || row.boisko.iloscMiejsc === null);
        }

        function resetErr() {
            $scope.nazwaErr2 = '';
            $scope.miejscowoscErr2 = '';
            $scope.iloscMiejscErr2 = '';
        }

        $scope.edit = function (row) {//musi
            resetErr();
            $scope.showEdit = true;
            $scope.edited = copyOf(row);
        };

        $scope.delete = function (row) {//musi
            deleteF(row, Factory, reload);
        };

        $scope.update = function (row) {//musi
            if(validateForm(row, 1)){
                $scope.error2 = '';
                let entry = copyOf(row);
                const json = angular.toJson(entry);
                const id = entry.id;
                Factory.update({id: id}, json, function () {
                    reload();
                }, function (r) {
                    alert(r);
                });

                $scope.showEdit = false;
                $scope.edited = empty();
            }else{
                $scope.error2 = 'Niepoprawne dane';
            }
        };
    });

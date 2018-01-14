'use strict';

angular.module('myApp.orliki', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/orliki', {
            templateUrl: 'stronki/orliki/orliki.html',
            controller: 'OrlikiCtrl'
        });
    }])

    .service('OrlikS', function ($resource) {
        this.Factory = $resource(url + 'orliki/:id', {id: '@_id'}, {
            'update': {method: 'PUT'}
        });
        this.columns = [
            {
                field: 'id',
                displayName: 'Id',
                width: '4%',
                enableFiltering: false
            },
            {
                field: 'boisko.nazwa',
                displayName: 'Nazwa'
            },
            {
                field: 'boisko.iloscMiejsc',
                displayName: 'Ilosc miejsc',
                width: '14%'
            },
            {
                field: 'boisko.miejscowosc',
                displayName: 'Miejscowosc',
                width: '14%'
            },
            editTemplate,
            delTemplate
        ];

        this.data = [
            {id: 0, boisko: {nazwa: '', iloscMiejsc: '', miejscowosc: '', orlik: '', stadion: '', mecze: ''}}
        ];
    })

    .controller('OrlikiCtrl', function ($scope, $filter, OrlikS, BoiskaF) {
        const Factory = OrlikS.Factory;
        let data = OrlikS.data;
        const empty = function () {
            return {
                id: 0,
                boisko: {id: 0, nazwa: '', iloscMiejsc: '', miejscowosc: '', orlik: null, stadion: null, mecze: null}
            };
        };
        $scope.entry = empty();
        $scope.boiska = [];

        let boiskaTemp = (BoiskaF.query(function () {
            $scope.boiska = $filter('orderBy')(boiskaTemp, 'id');
        }));

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
            columnDefs: OrlikS.columns,
            data: data
        };

        $scope.add = function (row) {//musi
            if(validateForm(row, 0)){
                $scope.error= '';
                row.boisko.id = null;
                addF(row, Factory, reload);
                $scope.entry = empty();
            }else{
                $scope.error= 'Niepoprawne dane';
            }
        };
        function validateForm(row, i){
            if(i===0){
                $scope.nazwaErr = (row.boisko.nazwa.length<1)?'error':'normal';
                $scope.miejscowoscErr = (row.boisko.miejscowosc.length<1)?'error':'normal';
                $scope.iloscMiejscErr = (row.boisko.iloscMiejsc==='' || row.boisko.iloscMiejsc===0 || row.boisko.iloscMiejsc===null)?'error':'normal';
            }else{
                $scope.nazwaErr2 = (row.boisko.nazwa.length<1)?'error':'normal';
                $scope.miejscowoscErr2 = (row.boisko.miejscowosc.length<1)?'error':'normal';
                $scope.iloscMiejscErr2 = (row.boisko.iloscMiejsc==='' || row.boisko.iloscMiejsc===0 || row.boisko.iloscMiejsc===null)?'error':'normal';
            }

            return !(row.boisko.nazwa.length<1 || row.boisko.miejscowosc.length<1 || row.boisko.iloscMiejsc==='' || row.boisko.iloscMiejsc===0 || row.boisko.iloscMiejsc===null);
        }


        function resetErr() {
            $scope.nazwaErr2 = '';
            $scope.miejscowoscErr2 = '';
            $scope.iloscMiejscErr2 = '';
            $scope.error2 = '';
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
            console.log(row);
            if(validateForm(row, 1)){
                $scope.error2 = '';
                updateF(row.boisko, BoiskaF, reload);
                $scope.showEdit = false;
                $scope.edited = empty();
            }else{
                $scope.error2 = 'Niepoprawne dane';
            }
        };
    });

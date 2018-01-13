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

    .controller('StadionCtrl', function ($scope, $filter, StadionS,BoiskaF) {
        const Factory = StadionS.Factory;
        let data = StadionS.data;
        const empty = function () {
            return {id: 0, boisko: {id: 0, nazwa: '', iloscMiejsc: 0, miejscowosc: ''}, chroniPrzedDeszczem: false};
        };

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
            row.boisko.id = null;
            addF(row, Factory, reload);
            $scope.entry = empty();
        };

        $scope.edit = function (row) {//musi
            $scope.showEdit = true;
            $scope.edited = copyOf(row);
        };

        $scope.delete = function (row) {//musi
            deleteF(row, Factory, reload);
        };

        $scope.update = function (row) {//musi
            // console.log(row.boisko);
            // let boi = copyOf(row.boisko);
            // let stad = row;
            // stad.boisko = null;
            //
            // boi.stadion = stad;
            // console.log(boi);
            //
            // updateF(boi,BoiskaF,reload);
            console.log(row);
            // BoiskaF.query()

            let entry = copyOf(row);
            const json = angular.toJson(entry);
            const id = entry.id;
            Factory.update({id: id},json, function () {
                reload();
            }, function (r) {
                alert(r);
            });

            // updateF(row,BoiskaF,reload);

            // updateF(row,Factory,reload);
            // updateF(row,)
            $scope.showEdit = false;
            $scope.edited = empty();
        };
    });

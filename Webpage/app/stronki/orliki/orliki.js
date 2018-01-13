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
                width:'14%'
            },
            {
                field: 'boisko.miejscowosc',
                displayName: 'Miejscowosc',
                width:'14%'
            },
            editTemplate,
            delTemplate
        ];

        this.data = [
            {id: 0, boisko: {nazwa: '', iloscMiejsc: 0, miejscowosc: '', orlik: '', stadion: '', mecze: ''}}
        ];
    })

    .controller('OrlikiCtrl', function ($scope, $filter, OrlikS, BoiskaF) {
        const Factory = OrlikS.Factory;
        let data = OrlikS.data;
        const empty = function () {
            return {
                id: 0,
                boisko: {id: 0, nazwa: '', iloscMiejsc: 0, miejscowosc: '', orlik: null, stadion: null, mecze: null}
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
            // updateF(row.boisko, BoiskaF, reload);
            console.log(row);
            updateF(row.boisko, BoiskaF, reload);
            $scope.showEdit = false;
            $scope.edited = empty();
        };
    });

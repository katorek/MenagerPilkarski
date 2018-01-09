'use strict';

angular.module('myApp.orliki', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/orliki', {
            templateUrl: 'stronki/orliki/orliki.html',
            controller: 'OrlikiCtrl'
        });
    }])

    .service('OrlikS', function ($resource) {
        this.Factory = $resource(url + 'orliki/:id', {id: '@id'}, {
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
            editTemplate,
            delTemplate
        ];

        this.data = [
            {id: 0, boisko: {nazwa: '', iloscMiejsc: 0, miejscowosc: ''}}
        ];
    })

    .controller('OrlikiCtrl', function ($scope, $filter, OrlikS) {
        const Factory = OrlikS.Factory;
        let data = OrlikS.data;
        const empty = function () {
            return {id: 0, boisko: {id:0,nazwa: '', iloscMiejsc: 0, miejscowosc: ''}};
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
            columnDefs: OrlikS.columns,
            data: data
        };

        $scope.add = function (row) {//musi
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
            updateF(row, Factory, reload);
            $scope.showEdit = false;
            $scope.edited = empty();
        };
    });

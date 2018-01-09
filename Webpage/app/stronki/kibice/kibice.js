'use strict';

angular.module('myApp.kibice', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/kibice', {
            templateUrl: 'stronki/kibice/kibice.html',
            controller: 'KibiceCtrl'
        });
    }])

    .service('KibicS', function ($resource) {
        this.Factory = $resource(url + 'kibice/:id', {id: '@id'}, {
            'update': {method: 'PUT'}
        });
        this.columns = [
            {
                field: 'id',
                displayName: 'Id',
                width: "*",
                enableFiltering: false
            },
            {
                field: 'imie',
                displayName: 'Imie',
                width: "*",
            },
            {
                field: 'nazwisko',
                displayName: 'Nazwisko',
                width: "*"
            },
            {
                field: 'pesel',
                displayName: 'Pesel',
                width: "*"
            },
            {
                field: 'znizka',
                displayName: 'Znizka',
                width: '*'
            },
            editTemplate,
            delTemplate
        ];

        const empty = function () {
            return {
                id: 0,
                imie: '',
                nazwisko: '',
                pesel: '',
                znizka: false
            }
        };

        this.data = [empty()];
    })

    .controller('KibiceCtrl', function ($scope, $filter, KibicS) {
        const Factory = KibicS.Factory;
        let data = KibicS.data;

        const empty = function () {
            return {
                id: 0,
                nazwa: '',
                rodzaj: '',
                druzyna: '',
            }
        };

        $scope.showEdit = false;
        $scope.entry = empty();
        $scope.edited = empty();

        const reload = function () {
            data = Factory.query(function () {
                $scope.grid.data = $filter('orderBy')(data, 'id');
            }, function (response) {
                alerty(response);
            });
        };

        reload();

        $scope.grid = {
            enableFiltering: true,
            enableSorting: true,
            enableCellSelection: true,
            enableCellEditOnFocus: true,
            columnDefs: KibicS.columns,
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

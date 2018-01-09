'use strict';

angular.module('myApp.druzyny', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/druzyny', {
            templateUrl: 'stronki/druzyna/druzyny.html',
            controller: 'DruzynyCtrl'
        });
    }])

    .service('DruzynaS',function ($resource) {
        this.Factory = $resource(url + 'druzyny/:id', {id: '@id'}, {
            'update': {method: 'PUT'}
        });
        this.columns = [
            {
                name: 'id',
                width: '6%',
                enableFiltering: false
            },
            {
                name: 'nazwa',
                enableCellEdit: true,
                enableFiltering: true
            },
            editTemplate,
            delTemplate
        ];

        const empty = function () {
            return {id: 0, nazwa: ''}
        };

        this.data = [empty()];
    })

    .controller('DruzynyCtrl', function ($scope, $filter, DruzynaS) {
        const Factory = DruzynaS.Factory;
        let data = DruzynaS.data;
        const empty = function () {
            return {id: 0, nazwa: ''}
        };


        $scope.showEdit = false; //musi
        $scope.entry = empty();//musi
        $scope.edited = empty();//musi

        const reload = function () {
            data = Factory.query(function () {
                $scope.grid.data = $filter('orderBy')(data, 'id');
            }, function (r) {
                alert(r);
            });
        };

        reload();

        $scope.grid = { //musi
            enableFiltering: true,
            enableSorting: true,
            enableCellSelection: true,
            enableCellEditOnFocus: true,
            columnDefs: DruzynaS.columns,
            data: data
        };

        //CRUD
        $scope.add = function (row) {//musi
            row.id = 0;
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
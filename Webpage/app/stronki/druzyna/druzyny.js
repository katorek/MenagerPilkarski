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
            {
                field:'liga.nazwa',
            },
            editTemplate,
            delTemplate
        ];

        const empty = function () {
            return {id: 0, nazwa: ''}
        };

        this.data = [empty()];
    })

    .controller('DruzynyCtrl', function ($scope, $filter, DruzynaS, LigaService) {
        const Factory = DruzynaS.Factory;
        let data = DruzynaS.data;
        const empty = function () {
            return {id: 0, nazwa: ''}
        };

        $scope.ligi = [];
        let ligiT = LigaService.Factory.query(function () {
            $scope.ligi = $filter('orderBy')(ligiT,'id');

        });

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
            console.log(row);
            row.liga = $scope.ligi[row.liga-1];
            console.log(row);
            addF(row, Factory, reload);
            $scope.entry = empty();
        };

        $scope.edit = function (row) {//musi
            $scope.showEdit = true;
            $scope.edited = copyOf(row);

            let id = 0;
            if($scope.edited.liga===null){
                for (let i = 0; i < $scope.ligi.length; ++i) {
                    id = ($scope.ligi[i].nazwa === 'Brak') ? i : id;
                }
            } else{
                for (let i = 0; i < $scope.ligi.length; ++i) {
                    id = ($scope.ligi[i].id === $scope.edited.liga.id) ? i : id;
                }
            }

            $scope.ligi.selected = $scope.ligi[id];
        };

        $scope.delete = function (row) {//musi
            deleteF(row, Factory, reload);
        };

        $scope.update = function (row) {//musi
            row.liga = $scope.ligi.selected;
            updateF(row, Factory, reload);
            $scope.showEdit = false;
            $scope.edited = empty();
        };
    });
'use strict';

angular.module('myApp.sponsorzy', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/sponsorzy', {
            templateUrl: 'stronki/sponsorzy/sponsorzy.html',
            controller: 'SponsorzyCtrl'
        });
    }])

    .service('SponsorzyS', function ($resource) {
        this.Factory = $resource(url + 'sponsorzy/:id', {id: '@id'}, {
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
                field: 'nazwa',
                displayName: 'Nazwa',
                width: '*'
            },
            {
                field: 'rodzaj',
                displayName: 'Rodzaj',
                width: '*'
            },
            {
                field: 'druzyna.nazwa',
                displayName: 'Druzyna',
                width: '*',
            },
            editTemplate,
            delTemplate
        ];

        const empty = function () {
            return {
                id: 0,
                nazwa: '',
                rodzaj: '',
                druzyna: '',
            }
        };

        this.data = [empty()];
    })

    .controller('SponsorzyCtrl', function ($scope, $filter, SponsorzyS, DruzynaS) {
        const Factory = SponsorzyS.Factory;
        let data = SponsorzyS.data;
        $scope.druzyny = [{}];
        let druzynyLet = (DruzynaS.Factory.query(function () {
            $scope.druzyny = $filter('orderBy')(druzynyLet, 'id');
        }));

        // $scope.getDruzyna = function (druzyna) {
        //     if (isNumber(druzyna)) {
        //         druzyna = $scope.druzyny[druzyna - 1];
        //     }
        //     return druzyna.nazwa;
        // };


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

        function init() {
            $scope.nazwaErr = '';
            $scope.rodzajErr = '';
            $scope.druzynaErr = '';
        }

        init();

        $scope.grid = {
            enableFiltering: true,
            enableSorting: true,
            enableCellSelection: true,
            enableCellEditOnFocus: true,
            columnDefs: SponsorzyS.columns,
            data: data
        };

        $scope.add = function (row) {//musi
            if (validateForm(row, 0)) {
                $scope.error = '';
                row.druzyna = $scope.druzyny[row.druzyna - 1];
                addF(row, Factory, reload);
                $scope.entry = empty();
            } else {
                $scope.error = 'Niepoprawne dane';
            }
        };

        function validateForm(row, i) {
            if (i === 0) {
                $scope.nazwaErr = (row.nazwa.length < 1) ? 'error' : 'normal';
                $scope.rodzajErr = (row.rodzaj.length < 1) ? 'error' : 'normal';
                $scope.druzynaErr = (row.druzyna === null || row.druzyna === '') ? 'error' : 'normal';
            } else {
                $scope.nazwaErr2 = (row.nazwa.length < 1) ? 'error' : 'normal';
                $scope.rodzajErr2 = (row.rodzaj.length < 1) ? 'error' : 'normal';
                $scope.druzynaErr2 = (row.druzyna === null || row.druzyna === '') ? 'error' : 'normal';
            }

            return !(row.nazwa.length < 1 || row.rodzaj.length < 1 || row.druzyna === null || row.druzyna === '');
        }

        function resetErr() {
            $scope.nazwaErr2 = '';
            $scope.rodzajErr2 = '';
            $scope.druzynaErr2 = '';
            $scope.error2 = '';
        }

        $scope.edit = function (row) {//musi
            resetErr();
            $scope.showEdit = true;
            $scope.edited = copyOf(row);
            let id = 0;
            for (let i = 0; i < $scope.druzyny.length; ++i) {
                id = ($scope.druzyny[i].id === $scope.edited.druzyna.id) ? i : id;
            }
            $scope.druzyny.selected = $scope.druzyny[id];
        };

        $scope.delete = function (row) {//musi
            deleteF(row, Factory, reload);
        };

        $scope.update = function (row) {//musi
            row.druzyna = $scope.druzyny.selected;
            row.druzyna.pilkarze = null;

            if(validateForm(row, 1)){
                $scope.error2 = '';
                updateF(row, Factory, reload);
                $scope.edited = empty();
                $scope.showEdit = false;
            }else{
                $scope.error2 = 'Niepoprawne dane';
            }
        };
    });

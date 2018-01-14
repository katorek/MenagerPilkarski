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
                imie: '',
                nazwisko: '',
                pesel: '',
            }
        };

        $scope.showEdit = false;
        $scope.entry = empty();
        $scope.edited = empty();

        function init() {
            $scope.imieErr = '';
            $scope.nazwiskoErr = '';
            $scope.peselErr = '';
            $scope.error = '';
        }

        init();

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
            console.log(row);
            if (validateForm(row, 0)) {
                $scope.error = '';
                addF(row, Factory, reload);
                $scope.entry = empty();
            } else {
                $scope.error = 'Nie poprawne dane';
            }
        };

        function validateForm(row, i) {
            if (i === 0) {
                $scope.imieErr = (row.imie.length < 1) ? 'error' : 'normal';
                $scope.nazwiskoErr = (row.nazwisko.length < 1) ? 'error' : 'normal';
                $scope.peselErr = !(isPeselValid(row.pesel)) ? 'error' : 'normal';
            } else {
                $scope.imieErr2 = (row.imie.length < 1) ? 'error' : 'normal';
                $scope.nazwiskoErr2 = (row.nazwisko.length < 1) ? 'error' : 'normal';
                $scope.peselErr2 = !(isPeselValid(row.pesel)) ? 'error' : 'normal';
            }

            return !(row.imie.length < 1 || row.nazwisko.length < 1 || !isPeselValid(row.pesel));
        }



        function resetErr() {
            $scope.imieErr2 = 'normal';
            $scope.nazwiskoErr2 = 'normal';
            $scope.peselErr2 = 'normal';
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
            if (validateForm(row, 1)) {
                $scope.error2 = '';
                updateF(row, Factory, reload);
                $scope.showEdit = false;
                $scope.edited = empty();
            } else {
                $scope.error2 = 'Niepoprawne dane';
            }
        };

    });

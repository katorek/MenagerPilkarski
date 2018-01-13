'use strict';

angular.module('myApp.pilkarze', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/pilkarze', {
            templateUrl: 'stronki/pilkarze/pilkarze.html',
            controller: 'PilkarzeCtrl'
        });
    }])

    .service('PilkarzS', function ($resource) {
        this.Factory = $resource(url + 'pilkarze/:id', {id: '@id'}, {
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
                field: 'numer',
                displayName: 'Numer',
                width: '*'
            },
            {
                name: 'pozycja',
                displayName: 'Pozycja',
                cellTemplate: '<div class="ui-grid-cell-contents">{{grid.appScope.getPozycja(row.entity.pozycja)}}</div>'
            },
            {
                field: 'druzyna.nazwa',
                displayName: 'Druzyna',
                width: '*'
            },
            editTemplate,
            delTemplate
        ];
        this.pozycje = [
            {id: 'NAPASTNIK', name: 'Napastnik'},
            {id: 'POMOCNIK', name: 'Pomocnik'},
            {id: 'OBRONCA', name: 'Obronca'},
            {id: 'BRAMKARZ', name: 'Bramkarz'}
        ];

        const empty = function () {
            return {
                id: 0,
                imie: '',
                nazwisko: '',
                pesel: '',
                numer: '',
                pozycja: '',
                druzyna: ''
            }
        };

        this.data = [empty()];
    })

    .controller('PilkarzeCtrl', function ($scope, $filter, PilkarzS, DruzynaS) {
        const Factory = PilkarzS.Factory;
        let data = PilkarzS.data;
        const empty = function () {
            return {
                id: 0,
                imie: '',
                nazwisko: '',
                pesel: '',
                numer: '',
                pozycja: '',
                druzyna: ''
            };
            // PilkarzS.pozycje.ge
        };

        let druzynyLet = DruzynaS.Factory.query(function () {
            $scope.druzyny = $filter('orderBy')(druzynyLet, 'id');
        });

        $scope.pozycje = {
            lista: [
                {id: 'BRAK', nazwa: '--Brak--'},
                {id: 'NAPASTNIK', nazwa: 'Napastnik'},
                {id: 'POMOCNIK', nazwa: 'Pomocnik'},
                {id: 'OBRONCA', nazwa: 'Obronca'},
                {id: 'BRAMKARZ', nazwa: 'Bramkarz'}
            ],
        };

        $scope.showEdit = false; //musi
        $scope.entry = empty();//musi

        $scope.edited = empty();//musi

        const reload = function () {
            data = Factory.query(function () {
                $scope.grid.data = data;
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
            columnDefs: PilkarzS.columns,
            data: data
        };

        $scope.getDruzyna = function (druzyna) {
            if (isNumber(druzyna)) {
                druzyna = $scope.druzyny[druzyna - 1];
            }
            return druzyna.nazwa;
        };

        $scope.getPozycja = function (pozycja) {
            // console.log('Getting pozycja for: '+ pozycja);
            for (let i = 0; i < $scope.pozycje.lista.length; ++i) {
                // console.log($scope.pozycje.lista[i].id+' - '+pozycja);
                if ($scope.pozycje.lista[i].id === pozycja) return $scope.pozycje.lista[i].nazwa;
            }
        };

        //CRUD
        $scope.add = function (row) {//musi
            row.druzyna = $scope.druzyny[row.druzyna - 1];
            addF(row, Factory, reload);
            $scope.entry = empty();
            $scope.entry.pozycja = $scope.pozycje[0];
        };

        $scope.edit = function (row) {//musi
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
            updateF(row, Factory, reload);

            $scope.showEdit = false;
            $scope.edited = empty();
        };

        $scope.entry.pozycja = $scope.pozycje[0];
    });

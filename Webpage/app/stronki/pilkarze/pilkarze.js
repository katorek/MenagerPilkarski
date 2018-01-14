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

        function init(){
            $scope.imieErr = '';
            $scope.nazwiskoErr = '';
            $scope.peselErr = '';
            $scope.numerErr = '';
            $scope.pozycjaErr = '';
            $scope.druzynaErr = '';
        }
        init();

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
            if(validateForm(row, 0)){
                $scope.error = "";
                row.druzyna = $scope.druzyny[row.druzyna - 1];
                addF(row, Factory, reload);
                $scope.entry = empty();
                $scope.entry.pozycja = $scope.pozycje[0];
            }else{
                $scope.error = "Niepoprawne dane";
            }
        };

        function validateForm(row, i) {
            console.log(row);
            if(i===0){

                $scope.imieErr = (row.imie.length<1)?'error':'normal';
                $scope.nazwiskoErr = (row.nazwisko.length<1)?'error':'normal';
                $scope.peselErr = !isPeselValid(row.pesel)?'error':'normal';
                $scope.numerErr = (row.numer==='')?'error':'normal';
                $scope.pozycjaErr = (row.pozycja === undefined)?'error':'normal';
                $scope.druzynaErr = (row.druzyna==='')?'error':'normal';

            }else{
                $scope.imieErr2 = (row.imie.length<1)?'error':'normal';
                $scope.nazwiskoErr2 = (row.nazwisko.length<1)?'error':'normal';
                $scope.peselErr2 = (!isPeselValid(row.pesel) || row.pesel===null)?'error':'normal';
                $scope.numerErr2 = (row.numer==='')?'error':'normal';
                $scope.pozycjaErr2 = (row.pozycja === undefined)?'error':'normal';
                $scope.druzynaErr2 = (row.druzyna==='')?'error':'normal';
            }

            return !(row.imie.length < 1 || row.nazwisko.length < 1 || row.pesel === '' || !isPeselValid(row.pesel) || row.numer === '' || row.pozycja === undefined || row.druzyna==='');
        }

        function resetErr() {
            $scope.imieErr2 = '';
            $scope.nazwiskoErr2 = '';
            $scope.peselErr2 = '';
            $scope.numerErr2 = '';
            $scope.pozycjaErr2 = '';
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

                $scope.showEdit = false;
                $scope.edited = empty();
            }else{
                $scope.error2 = 'Niepoprawne dane';
            }
        };

        $scope.entry.pozycja = $scope.pozycje[0];
    });

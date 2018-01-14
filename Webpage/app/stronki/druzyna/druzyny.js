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
            'update': {method: 'PUT'},
            'wygraneMecze': {
                method: 'GET',
                url: '/wygrane'
            }
        });
        this.columns = [
            {
                name: 'id',
                width: '6%',
                enableFiltering: false
            },
            {
                name: 'nazwa',
            },
            {
                field:'wygranychMeczy'
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

        $scope.ligaErr = "normal";
        $scope.nazwaErr = "normal";
        //CRUD
        $scope.add = function (row) {//musi
            if(validateForm(row,0)){
                $scope.error = "";
                $scope.isError = "normal";
                row.id = 0;
                // console.log(row);
                row.liga = $scope.ligi[row.liga-1];
                // console.log(row);
                addF(row, Factory, reload);
                $scope.entry = empty();
            }else{
                $scope.error = "Niepoprawne dane";
            }
        };

        function validateForm(row,i) {
            if(i===0){
                $scope.nazwaErr = (row.nazwa.length<1)?'error':'normal';
                $scope.ligaErr = !('liga' in row)?'error':'normal';
            }else{
                $scope.nazwaErr2 = (row.nazwa.length<1)?'error':'normal';
                $scope.ligaErr2 = !('liga' in row)?'error':'normal';
            }
            return !(row.nazwa.length < 1 || !('liga' in row));
        }

        function resetErr() {
            $scope.nazwaErr2 = 'normal';
            $scope.ligaErr2 = 'normal';
            $scope.error2 = '';
        }

        $scope.edit = function (row) {//musi
            resetErr();
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
            if(validateForm(row,1)){
                $scope.error2 = '';
                updateF(row, Factory, reload);
                $scope.showEdit = false;
                $scope.edited = empty();
            }else{
                $scope.error2 = 'Niepoprawne dane';
            }
        };
    });
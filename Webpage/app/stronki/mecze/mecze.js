'use strict';

// CREATE TABLE mecze (
//     ID            INT PRIMARY KEY AUTO_INCREMENT,
//     boisko
//     CENA_BILETU   DOUBLE,
//     WYNIK_MECZU   VARCHAR(10),
//     GOSPODARZ_ID  INTEGER,
//     PRZYJEZDNI_ID INTEGER,
//     DATA          DATE
// );

angular.module('myApp.mecze', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/mecze', {
            templateUrl: 'stronki/mecze/mecze.html',
            controller: 'MeczeCtrl'
        });
    }])

    .factory('BoiskaF', function ($resource) {
        return $resource(url + 'boiska/:id', {id: '@_id'},{
            'update': {method: 'PUT'}
        });
    })

    .service('MeczeS', function ($resource) {
        this.Factory = $resource(url + 'mecze/:id', {id: '@_id'}, {
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
                field: 'boisko.nazwa',
                displayName: 'Boisko'
            },
            {
                field: 'cenaBiletu',
                displayName: 'Cena biletu',
                // cellTemplate: '<div class="ui-grid-cell-contents">{{grid.appScope.getNazwa(row.entity.kibic)}}</div>'
            },
            {
                field: 'gospodarz.nazwa',
                displayName: 'Gospodarze'
            },
            {
                field: 'wynikMeczu',
                displayName: 'Wynik meczu',
            },
            {
                field: 'przyjezdni.nazwa',
                displayName: 'Na wyjezdzie'
            },
            {
                field: 'data',
                displayName: 'Data',
                type: 'date', cellFilter: 'date:\'yyyy-MM-dd\''
            },
            editTemplate,
            delTemplate
        ];

        this.data = [{
            id: 0,
            boisko: '',
            cenaBiletu: 0,
            wynikMeczu: '',
            gospodarz: '',
            przyjezdni: '',
            data: new Date()
        }];

    })

    .controller('MeczeCtrl', function ($scope, $filter, MeczeS, DruzynaS, BoiskaF) {
        const Factory = MeczeS.Factory;
        let data = MeczeS.data;

        $scope.druzyny = [{}];
        $scope.boiska = [{}];

        let druzynyTemp = (DruzynaS.Factory.query(function () {
            $scope.druzyny = $filter('orderBy')(druzynyTemp, 'id');
        }));
        let boiskaTemp = (BoiskaF.query(function () {
            $scope.boiska = $filter('orderBy')(boiskaTemp, 'id');
        }));

        const empty = function () {
            return {id: 0, boisko: {}, cenaBiletu: 0, wynikMeczu: '', gospodarz: '', przyjezdni: '', data: new Date()};
        };

        $scope.showEdit = false;
        $scope.entry = empty();
        $scope.edited = empty();


        //type: 'date', cellFilter: 'date:\'yyyy-MM-dd\''
        const reload = function () {
            data = Factory.query(function () {
                $scope.grid.data = $filter('orderBy')(data, 'id');
                // for(let i=0;i<)
            }, function (response) {
                alert(response);
            });
        };

        reload();

        $scope.grid = {
            enableFiltering: true,
            enableSorting: true,
            enableCellSelection: true,
            enableCellEditOnFocus: true,
            columnDefs: MeczeS.columns,
            data: data
        };

        $scope.add = function (row) {//musi
            console.log(row);
            console.log($scope.boiska[row.boisko-1]);
            row.boisko = copyOf($scope.boiska[row.boisko-1]);
            row.gospodarz = copyOf($scope.druzyny[row.gospodarz - 1]);
            row.przyjezdni = copyOf($scope.druzyny[row.przyjezdni - 1]);
            row.boisko.mecze = null;

            row.boisko.stadion = null;
            row.boisko.orlik = null;

            console.log(row);

            // row.druzyna = $scope.druzyny[row.druzyna - 1];
            addF(row, Factory, reload);
            $scope.entry = empty();
        };

        $scope.edit = function (row) {//musi
            console.log(row);
            $scope.showEdit = true;
            $scope.edited = copyOf(row);
            let id1 = 0;
            let id2 = 0;

            for (let i = 0; i < $scope.druzyny.length; ++i) {
                id1 = ($scope.druzyny[i].id === $scope.edited.gospodarz.id) ? i : id1;
                id2 = ($scope.druzyny[i].id === $scope.edited.przyjezdni.id) ? i : id2;
            }

            let id3 = 0;
            for(let i=0;i<$scope.boiska.length;++i){
                id3 = ($scope.boiska[i].id === $scope.edited.boisko.id) ? i: id3;
            }

            // $scope.edited.data = $scope.edited.data | date: "yyyy-MM-dd"; TODO
            $scope.druzyny.gospodarz = $scope.druzyny[id1];
            $scope.druzyny.przyjezdni = $scope.druzyny[id2];
            $scope.boiska.selected = $scope.boiska[id3];
            $scope.edited.data = new Date($scope.edited.data);
        };

        $scope.delete = function (row) {//musi
            deleteF(row, Factory, reload);
        };

        $scope.update = function (row) {//musi
            row.boisko = $scope.boiska.selected;
            row.druzyna = $scope.druzyny.selected;
            console.log(row);
            updateF(row, Factory, reload);
            $scope.showEdit = false;
            $scope.edited = empty();
        };
    });

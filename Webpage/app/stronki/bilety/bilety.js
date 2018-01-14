'use strict';

// CREATE TABLE bilety (
//     ID       INT PRIMARY KEY AUTO_INCREMENT,
//     MECZ_ID  INT,
// );

angular.module('myApp.bilety', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/bilety', {
            templateUrl: 'stronki/bilety/bilety.html',
            controller: 'BiletyCtrl'
        });
    }])

    .service('BiletyS', function ($resource) {
        this.Factory = $resource(url + 'bilety/:id', {id: '@id'}, {
            'update': {method: 'PUT'}
        });

        this.columns = [
            {
                field: 'id',
                displayName: 'Id',
                width: "6%",
                enableFiltering: false
            },
            {
                name: 'mecz',
                displayName: 'Mecz',
                cellTemplate: '<div class="ui-grid-cell-contents">{{grid.appScope.getMecz(row.entity.mecz.id)}}</div>'
            },
            {
                field: 'kibic.imie',
                displayName: 'Imie',
                width: '13%'
                // cellTemplate: '<div class="ui-grid-cell-contents">{{grid.appScope.getNazwa(row.entity.kibic)}}</div>'
            },
            {
                field: 'kibic.nazwisko',
                displayName: 'Nazwisko',
                width: '13%'
            },
            {
                field: 'znizka',
                displayName: 'Znizka',
                width: '7%',
            },
            editTemplate,
            delTemplate
        ];

        this.data = [{id: 0, mecz: '', znizka: 0, kibic: ''}];

    })

    .controller('BiletyCtrl', function ($scope, $filter, BiletyS, KibicS, MeczeS) {
        const Factory = BiletyS.Factory;
        let data = BiletyS.data;

        $scope.kibice = [];
        $scope.mecze = [{
            id: 0,
            boisko: '',
            cenaBiletu: 0,
            wynikMeczu: '',
            gospodarz: '',
            przyjezdni: '',
            data: new Date()
        }];

        let kibiceTemp = (KibicS.Factory.query(function () {
            $scope.kibice = $filter('orderBy')(kibiceTemp, 'id');
        }));

        let meczeTemp = (MeczeS.Factory.query(function () {
            $scope.mecze = $filter('orderBy')(meczeTemp, 'id');
        }));

        const empty = function () {
            return {id: 0, mecz: 0, znizka: 0, kibic: 0};
        };

        $scope.getKibic = function (kibic) {
            return kibic.imie + ' ' + kibic.nazwisko;
        };

        $scope.showEdit = false;
        $scope.entry = empty();
        $scope.edited = empty();

        $scope.getNazwa = function (kibic) {
            return kibic.imie + ' ' + kibic.nazwisko;
        };

        $scope.getMecz = function (meczId) {
            if ($scope.mecze.length <= 0) return '';
            let mecz;
            for (let i = 0; i < $scope.mecze.length; ++i) {
                if ($scope.mecze[i].id === meczId) mecz = $scope.mecze[i];
            }
            // let d;
            // if( angular.isDate(mecz.data)) d = mecz.data;
            // console.log('mecz');
            // console.log(mecz);
            const d = $filter('date')(mecz.data, "dd-MM-yyyy");
            const g = mecz.gospodarz.nazwa;
            const p = mecz.przyjezdni.nazwa;
            return d + ': ' + g + '-' + p;
            // return $filter('date')(mecz.data, "dd-MM-yyyy") + ':' + mecz.gospodarz.nazwa + '-' + mecz.przyjezdni.nazwa;
        };
        //{id: 0, boisko: '', cenaBiletu: 0, wynikMeczu: '', gospodarz: '', przyjezdni: '', data: ''}

        const reload = function () {
            data = Factory.query(function () {
                    $scope.grid.data = $filter('orderBy')(data, 'id');
                }, function (response) {
                    alerty(response);
                }
            );
        };

        reload();

        function init() {
            $scope.meczErr = '';
            $scope.kibicErr = '';
            $scope.error = '';
            $scope.meczErr2 = '';
            $scope.kibicErr2 = '';
            $scope.error2 = '';
        }

        init();

        $scope.grid = {
            enableFiltering: true,
            enableSorting: true,
            enableCellSelection: true,
            enableCellEditOnFocus: true,
            columnDefs: BiletyS.columns,
            data: data
        };

        $scope.add = function (row) {
            if (validateForm(row,0)) {
                $scope.error = '';
                // console.log(row);
                row.mecz = $scope.mecze[row.mecz - 1];
                row.kibic = $scope.kibice[row.kibic - 1];
                // row.druzyna = $scope.druzyny[row.druzyna - 1];
                addF(row, Factory, reload);
                $scope.entry = empty();

            } else {
                $scope.error = 'Niepoprawne dane';
            }
        };

        function validateForm(row, i) {
            console.log(row);
            if(i===0){
                $scope.meczErr = (row.mecz === 0) ? 'error' : 'normal';
                $scope.kibicErr = (row.kibic === 0) ? 'error' : 'normal';
                return !(row.mecz === 0 || row.kibic === 0);

            }else{
                $scope.meczErr2 = (row.mecz === 0) ? 'error' : 'normal';
                $scope.kibicErr2 = (row.kibic === 0) ? 'error' : 'normal';
                return !(row.mecz === 0 || row.kibic === 0);
            }
        }

        $scope.edit = function (row) {
            $scope.showEdit = true;
            $scope.edited = copyOf(row);

            let id1 = 0;
            for (let i = 0; i < $scope.kibice.length; ++i)
                if ($scope.kibice[i].id === row.kibic.id)
                    id1 = i;
            $scope.kibice.selected = $scope.kibice[id1];

            let id2 = 0;
            for (let i = 0; i < $scope.mecze.length; ++i)
                if ($scope.mecze[i].id === row.mecz.id)
                    id2 = i;
            $scope.mecze.selected = $scope.mecze[id2];
        };

        $scope.delete = function (row) {//todo
            deleteF(row, Factory, reload);
        };

        $scope.update = function (row) {
            row.mecz = $scope.mecze.selected;
            row.kibic = $scope.kibice.selected;
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

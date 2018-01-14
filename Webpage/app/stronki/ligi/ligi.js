'use strict';

angular.module('myApp.ligi', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/ligi', {
            templateUrl: 'stronki/ligi/ligi.html',
            controller: 'LigaCtrl',
            controllerAs: 'vm'
        });
    }])
    .service('LigaService', LigaService)
    .controller('LigaCtrl', LigaCtrl);

LigaService.$inject = ['$resource'];
LigaCtrl.$inject = ['$filter', 'LigaService'];


const editT = {
    hideHeader: false,
    name: 'edit',
    displayName: '',
    enableSorting: false,
    enableFiltering: false,
    enableHiding: false,
    width: '8%',
    cellTemplate: '<button id="editBtn" type="button" class="btn-small" ng-click="grid.appScope.vm.edit(row.entity)" >Edytuj</button> '
};

const delT = {

    hideHeader: true,
    name: 'delete',
    displayName: '',
    enableSorting: false,
    enableFiltering: false,
    enableHiding: false,
    width: '8%',
    cellTemplate: '<button id="deleteBtn" type="button" class="btn-small" ng-click="grid.appScope.vm.delete(row.entity)" >Usun</button> '
};

function LigaService($resource) {
    this.Factory = $resource(url + 'ligi/:id', {id: '@id'}, {
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
            width: "*",
        },
        editT,
        delT
    ];

    const empty = function () {
        return {
            id: 0,
            nazwa: '',
        }
    };

    this.data = [empty()];
}


function LigaCtrl($filter, LigaService) {
    const vm = this;
    const Factory = LigaService.Factory;
    let data = LigaService.data;

    vm.showEdit = false;
    vm.entry = empty();
    vm.edited = empty();
    vm.add = add;
    vm.edit = edit;
    vm.delete = deleteR;
    vm.update = update;
    vm.grid = {
        enableFiltering: true,
        enableSorting: true,
        enableCellSelection: true,
        enableCellEditOnFocus: true,
        columnDefs: LigaService.columns,
        data: data
    };
    vm.error = '';
    vm.error2 = '';
    vm.nazwaErr = '';
    vm.nazwaErr2 = '';

    reload();

    function resetErr() {
        vm.nazwaErr2 = 'normal';
        vm.error2 = '';
    }

    function empty() {
        return {
            id: 0,
            nazwa: '',
        }
    }

    function reload() {
        data = Factory.query(function () {
            vm.grid.data = $filter('orderBy')(data, 'id');
        }, function (response) {
            alerty(response);
        });
    }

    function add(row) {//musi
        if (validateForm(row, 0)) {
            vm.error = '';
            console.log(row);
            addF(row, Factory, reload);
            vm.entry = empty();
        } else {
            vm.error = 'Niepoprawne dane';
        }
    }

    function validateForm(row, i) {
        if (i === 0) vm.nazwaErr = (row.nazwa.length < 1) ? 'error' : 'normal';
        else vm.nazwaErr2 = (row.nazwa.length < 1) ? 'error' : 'normal';
        return !(row.nazwa.length < 1);
    }

    function edit(row) {//musi
        resetErr();
        // console.log(row);
        vm.showEdit = true;
        vm.edited = copyOf(row);
    }

    function deleteR(row) {//musi
        deleteF(row, Factory, reload);
    }

    function update(row) {//musi
        if (validateForm(row, 1)) {
            vm.error2 = '';
            updateF(row, Factory, reload);
            vm.showEdit = false;
            vm.edited = empty();
        } else {
            vm.error2 = 'Niepoprawne dane';
        }
    }

}

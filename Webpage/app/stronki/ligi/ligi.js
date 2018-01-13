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
    width:'8%',
    cellTemplate: '<button id="editBtn" type="button" class="btn-small" ng-click="grid.appScope.vm.edit(row.entity)" >Edytuj</button> '
};

const delT = {

    hideHeader: true,
    name: 'delete',
    displayName: '',
    enableSorting: false,
    enableFiltering: false,
    enableHiding: false,
    width:'8%',
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
    // vm.add = add;
    // function ($scope, $filter, LigaService) {
    const Factory = LigaService.Factory;
    let data = LigaService.data;

    const empty = function () {
        return {
            id: 0,
            nazwa: '',
        }
    };

    vm.showEdit = false;
    vm.entry = empty();
    vm.edited = empty();

    const reload = function () {
        data = Factory.query(function () {
            vm.grid.data = $filter('orderBy')(data, 'id');
        }, function (response) {
            alerty(response);
        });
    };

    reload();

    vm.grid = {
        enableFiltering: true,
        enableSorting: true,
        enableCellSelection: true,
        enableCellEditOnFocus: true,
        columnDefs: LigaService.columns,
        data: data
    };

    vm.add = function (row) {//musi
        console.log(row);
        addF(row, Factory, reload);
        vm.entry = empty();
    };

    vm.edit = function (row) {//musi
        console.log(row);
        vm.showEdit = true;
        vm.edited = copyOf(row);
    };

    vm.delete = function (row) {//musi
        deleteF(row, Factory, reload);
    };

    vm.update = function (row) {//musi
        updateF(row, Factory, reload);
        vm.showEdit = false;
        vm.edited = empty();
    };

}

// )
// ;
// }
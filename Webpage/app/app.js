'use strict';
//Global consts
const url = 'http://localhost:8080/';

const editTemplate = {
    name: 'edit',
    displayName: 'Edycja',
    enableSorting: false,
    enableFiltering: false,
    enableHiding: false,
    cellTemplate: '<button id="editBtn" type="button" class="btn-small" ng-click="grid.appScope.edit(row.entity)" >Edytuj</button> '
};

const delTemplate = {
    name: 'delete',
    displayName: 'Usuwanie',
    enableSorting: false,
    enableFiltering: false,
    enableHiding: false,
    cellTemplate: '<button id="deleteBtn" type="button" class="btn-small" ng-click="grid.appScope.delete(row.entity)" >Usun</button> '
};

//CRUD
const addF = function (entry, Factory, reload) {
    entry.id = 0;
    entry = copyOf(entry);
    Factory.save(angular.toJson(entry), function () {
        reload();
    }, function (r) {
        alert(r);
    });

};

const deleteF = function (entry, Factory, reload) {
    Factory.delete(entry, function () {
        reload();
    }, function (r) {
        alert(r);
    });


};

const updateF = function (entry, Factory, reload) {
    entry = copyOf(entry);
    const json = angular.toJson(entry);
    Factory.update({id: entry.id}, json, function () {
        reload();
    }, function (r) {
        alert(r);
    });
};

const copyOf = function (object) {
    return angular.copy(object);
};

// Declare app level module which depends on views, and components
angular.module('myApp', [
    'ngResource',
    'ui.grid',
    'ngTouch',
    'ngRoute',
    'ngMessages',
    'myApp.druzyny',
    'myApp.view2',
    'myApp.version'
]).config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');

    $routeProvider.otherwise({redirectTo: '/view1'});
}]);

'use strict';
//Global consts
const url = 'http://localhost:8080/';

const editTemplate = {
    hideHeader: false,
    name: 'edit',
    displayName: '',
    enableSorting: false,
    enableFiltering: false,
    enableHiding: false,
    width:'8%',
    cellTemplate: '<button id="editBtn" type="button" class="btn-small" ng-click="grid.appScope.edit(row.entity)" >Edytuj</button> '
};

const delTemplate = {

    hideHeader: true,
    name: 'delete',
    displayName: '',
    enableSorting: false,
    enableFiltering: false,
    enableHiding: false,
    width:'8%',
    cellTemplate: '<button id="deleteBtn" type="button" class="btn-small" ng-click="grid.appScope.delete(row.entity)" >Usun</button> '
};

//CRUD
const addF = function (entry, Factory, reload) {
    entry.id = 0;
    entry = copyOf(entry);
    Factory.save(angular.toJson(entry), function () {
        reload();
    }, function (response) {

        if(response.status===409){
            // alert('Duplikat: \''+response.config.data+'\'');
            alert('Zduplikowana wartość !\nSpróbuj ponownie !')//: \''+response.config.data+'\'');
        }
        // alert(response);
        console.log(response);
        // handleError(response);
        // alert(response.toString());
    });

};

function handleError(response) {
    console.log('handlEerror');
    console.log(typeof(response));
    alert(response);
    // console.log(response);
    // let array = response.split(" ");
    // console.log('SplitArray');
    // console.log(array);
    // let newResp = '';
    // if(response.contains('Duplicate')){
    //
    // }
    alert(response);
}

function doNothing() {

}

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
    'myApp.welcome',
    'myApp.druzyny',
    'myApp.pilkarze',
    'myApp.kibice',
    'myApp.sponsorzy',
    'myApp.orliki',
    'myApp.stadiony',
    'myApp.bilety',
    'myApp.mecze',
    'myApp.ligi',
    'myApp.version'
]).config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
    $locationProvider.hashPrefix('!');

    $routeProvider.otherwise({redirectTo: '/welcome'});
}]);

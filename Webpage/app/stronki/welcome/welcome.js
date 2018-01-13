'use strict';

angular.module('myApp.welcome', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/welcome', {
            templateUrl: 'stronki/welcome/welcome.html',
            controller: 'WelcomeCtrl',
            controllerAs: 'vm'
        });
    }])

    .controller('WelcomeCtrl', Welcome);

function Welcome() {
    const vm = this;
    vm.title = 'Hello World !';
    vm.title = 'Hello World ! !';
}
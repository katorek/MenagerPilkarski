'use strict';

angular.module('myApp.X', ['ngRoute'])

    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/viewX', {
            templateUrl: 'viewX/page.html',
            controller: 'Ctrl'
        });
    }])

    .service('S', function ($resource) {
        this.Factory = $resource(url + 'URL/:id', {id: '@id'}, {
            'update': {method: 'PUT'}
        });
        this.columns = [
            {
                field: 'id',
                displayName: 'Id',
                width: "*",
                enableFiltering: false
            },
            editTemplate,
            delTemplate
        ];

        const empty = function () {
            return {
                id: 0,
            }
        };

        this.data = [empty()];
    })

    .controller('Ctrl', function ($scope, $filter) {

    });

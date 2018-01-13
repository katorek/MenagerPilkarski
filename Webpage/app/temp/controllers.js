//Controllers
"use strict";

const isNumber = function (val) {
    return (val === parseInt(val, 10));
};


app.controller('orlikCtrl', function ($scope, $filter, OrlikS) {
    const Factory = OrlikService.Factory;
    let data = OrlikService.data;
    const empty = function () {
        return {id: 0, boisko: {id:0,nazwa: '', iloscMiejsc: 0, miejscowosc: ''}};
    };

    const reload = function () {
        data = Factory.query(function () {
            $scope.grid.data = $filter('orderBy')(data, 'id');

        }, function (r) {
            alert(r)
        });
    };

    reload();

    $scope.grid = {
        enableFiltering: true,
        enableSorting: true,
        enableCellSelection: true,
        enableCellEditOnFocus: true,
        columnDefs: OrlikService.columns,
        data: data
    };

    $scope.add = function (row) {//musi
        addF(row, Factory, reload);
        $scope.entry = empty();
    };

    $scope.edit = function (row) {//musi
        $scope.showEdit = true;
        $scope.edited = copyOf(row);
    };

    $scope.delete = function (row) {//musi
        deleteF(row, Factory, reload);
    };

    $scope.update = function (row) {//musi
        updateF(row, Factory, reload);
        $scope.showEdit = false;
        $scope.edited = empty();
    };
});

app.controller('stadionCtrl', function ($scope, $filter, StadionS) {
    const Factory = StadionService.Factory;
    let data = StadionService.data;
    const empty = function () {
        return {id: 0, boisko: {id:0,nazwa: '', iloscMiejsc: 0, miejscowosc: ''}, chroniPrzedDeszczem: false};
    };

    const reload = function () {
        data = Factory.query(function () {
            $scope.grid.data = $filter('orderBy')(data, 'id');
        }, function (r) {
            alert(r)
        });
    };

    reload();

    $scope.grid = {
        enableFiltering: true,
        enableSorting: true,
        enableCellSelection: true,
        enableCellEditOnFocus: true,
        columnDefs: StadionService.columns,
        data: data
    };

    $scope.add = function (row) {//musi
        addF(row, Factory, reload);
        $scope.entry = empty();
    };

    $scope.edit = function (row) {//musi
        $scope.showEdit = true;
        $scope.edited = copyOf(row);
    };

    $scope.delete = function (row) {//musi
        deleteF(row, Factory, reload);
    };

    $scope.update = function (row) {//musi
        updateF(row, Factory, reload);
        $scope.showEdit = false;
        $scope.edited = empty();
    };
});
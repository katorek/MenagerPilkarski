//Services
"use strict";

app.service('StadionS', function (StadionyFactory) {
    this.Factory = StadionyFactory;
    this.columns = [
        {
            field: 'id',
            displayName: 'Id',
            enableFiltering: false
        },
        {
            field: 'boisko.nazwa',
            displayName: 'Nazwa'
        },
        {
            field: 'boisko.iloscMiejsc',
            displayName: 'Ilosc miejsc'
        },
        {
            field: 'boisko.miejscowosc',
            displayName: 'Miejscowosc'
        },
        {
            field: 'chroniPrzedDeszczem',
            displayName: 'Chroni przed deszczem',
            enableFiltering: false
        },
        editTemplate,
        delTemplate
    ];

    this.data = [
        {id: 0, nazwa: '', boiska: {id: 0, iloscMiejsc: 0, miejscowosc: ''}, chroniPrzedDeszczem: false}
    ];

});

app.service('OrlikS', function (OrlikFactory) {
    this.Factory = OrlikFactory;
    this.columns = [
        {
            field: 'id',
            displayName: 'Id',
            enableFiltering: false
        },
        {
            field: 'boisko.nazwa',
            displayName: 'Nazwa'
        },
        {
            field: 'boisko.iloscMiejsc',
            displayName: 'Ilosc miejsc'
        },
        {
            field: 'boisko.miejscowosc',
            displayName: 'Miejscowosc'
        },
        editTemplate,
        delTemplate
    ];

    this.data = [
        {id: 0, boisko: {nazwa: '', iloscMiejsc: 0, miejscowosc: ''}}
    ];

});


package com.meneger.model;

public interface Template {
    /**
     * Przygotowanie obiektu do wyswietlenia
     * Dla wszystkich pod Obiektow ustawia ich pola na null tj wywoluje clear'a
     * Zabezpiecza przed nieskonczona rekursja
     *
     */
    void prepare();

    /**
     * Nulluje wszystkie obiekty w danej klasie
     */
    void clear();
}

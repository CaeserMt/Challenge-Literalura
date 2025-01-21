package com.example.literalura.dto;

import java.util.List;

public class BookResponse {
    private int count; // Total de libros encontrados
    private String next; // URL para los próximos resultados, si hay paginación
    private String previous; // URL para los resultados previos
    private List<Book> results; // Lista de libros

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<Book> getResults() {
        return results;
    }

    public void setResults(List<Book> results) {
        this.results = results;
    }
}




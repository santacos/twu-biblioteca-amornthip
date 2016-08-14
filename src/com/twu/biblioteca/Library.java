package com.twu.biblioteca;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;


public class Library {
    private String name;
    private ArrayList<Book> books;

    public Library(String name, ArrayList<Book> books) {
        this.name = name;
        this.books = books;
    }

    public String getName() {
        return name;
    }

    public Optional<Book> findBookByName(String name) {
        for(Book book: books){
            if(book.getName().equals(name)){
                return Optional.of(book);
            }
        }
        return Optional.empty();
    }


    public boolean checkOutBookByName(String name) {
        Optional<Book> book = findBookByName(name);
        if(book.isPresent()){
            Book checkedOutBook = book.get();
            boolean isCheckedOutSuccessfully = checkedOutBook.checkOut();
            return isCheckedOutSuccessfully;

        }else{
            return false;
        }
    }

    public boolean returnBookByName(String name) {
        Optional<Book> book = findBookByName(name);
        if(book.isPresent()){
            Book returningBook = book.get();
            boolean isReturnBookSuccessfully = returningBook.returnBook();
            return isReturnBookSuccessfully;
        }else{
            return false;
        }
    }


    public ArrayList<Book> getAllAvailableBooks() {
        return books
                .stream()
                .filter(book -> !book.isCheckedOut())
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

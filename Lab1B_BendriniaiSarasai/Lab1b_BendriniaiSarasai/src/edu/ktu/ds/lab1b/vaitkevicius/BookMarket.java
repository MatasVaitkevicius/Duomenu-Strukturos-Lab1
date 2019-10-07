/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.vaitkevicius;

/**
 *
 * @author vaitk
 */
public class BookMarket {

    public BookList allBooks = new BookList();

    public BookList getNewerBooks(int fromYear) {
        BookList books = new BookList();
        for (Book b : allBooks) {
            if (b.getPublicationDate() >= fromYear) {
                books.add(b);
            }
        }

        return books;
    }

    public BookList getByPrice(int fromPrice, int toPrice) {
        BookList books = new BookList();
        for (Book b : allBooks) {
            if (b.getPrice() >= fromPrice && b.getPrice() <= toPrice) {
                books.add(b);
            }
        }
        return books;
    }

    public BookList getLongestBooks() {
        BookList books = new BookList();
        // formuojamas sąrašas su maksimalia reikšme vienos peržiūros metu
        double maxPages = 0;
        for (Book b : allBooks) {
            double pages = b.getNumberOfPages();
            if (pages >= maxPages) {
                if (pages > maxPages) {
                    books.clear();
                    maxPages = pages;
                }
                books.add(b);
            }
        }
        return books;
    }

    public BookList getByAuthorAndTitle(String authorAndTitle) {
        BookList books = new BookList();
        for (Book b : allBooks) {
            String bookAuthorAndTitle = b.getAuthor() + " " + b.getTitle();
            if (bookAuthorAndTitle.startsWith(authorAndTitle)) {
                books.add(b);
            }
        }
        return books;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.vaitkevicius;

import edu.ktu.ds.lab1b.util.Ks;
import java.util.Comparator;
import java.util.Locale;

/**
 *
 * @author vaitk
 */
public class BookTest {

    BookList books = new BookList();

    void execute() {
        createBooks();
        createBookList();
        countSruoga();
        appendBookList();
        checkBookMarketFilters();
        checkBookMarketSorting();
    }

    void createBooks() {
        Book b1 = new Book("Baba", "Bremenas", 1995, 60, 60);
        Book b2 = new Book("Aaamas", "Brauningas", 2000, 70, 50);
        Book b3 = new Book("Sruoga", "Dievu miskas", 1852, 100, 160);
        Book b4 = new Book();
        Book b5 = new Book();
        Book b6 = new Book();
        b4.parse("Brakata Alemono 2003 40 50");
        b5.parse("Brakata Zenono 2003 50 2000");
        b6.parse("Zigatata Rererer 1969 450 123");

        Ks.oun(b1);
        Ks.oun(b2);
        Ks.oun(b3);
        Ks.oun("First three books average pages = "
                + (b1.getNumberOfPages() + b2.getNumberOfPages() + b3.getNumberOfPages() / 3));
        Ks.oun(b4);
        Ks.oun(b5);
        Ks.oun(b6);
        Ks.oun("Last three price sum = "
                + (b4.getPrice() + b5.getPrice() + b6.getPrice()));
    }

    void createBookList() {
        Book b1 = new Book("Baba", "Bremenas", 1995, 60, 60);
        Book b2 = new Book("Aaamas", "Brauningas", 2000, 70, 50);
        Book b3 = new Book("Abc", "Dbc", 1852, 100, 160);
        books.add(b1);
        books.add(b2);
        books.add(b3);
        books.println("First three books: ");
        books.add("Brakata Alemono 2003 40 50");
        books.add("Sruoga Zenono 2003 50 2000");
        books.add("Sruoga Rererer 1969 450 123");

        books.println("All books");
        books.forEach(System.out::println);
        Ks.oun("First three books average pages = "
                + (books.get(0).getNumberOfPages() + books.get(1).getNumberOfPages()
                + books.get(2).getNumberOfPages() / 3));
        Ks.oun("Last three price sum = "
                + (books.get(3).getPrice() + books.get(4).getPrice()
                + books.get(5).getPrice()));

//        books.add(0, new Car("Nauja","Pavadinimas",2007,50,270));
//        books.add(6, new Car("SestojiKnyga","SestojiPavadinimas",1998,20,200));
//        books.set(4, c3);
//        books.println("After insert");
//        books.remove(7);
//        books.remove(0);
//        books.println("After remove");
//        books.remove(0); books.remove(0); books.remove(0);
//        books.remove(0); books.remove(0); books.remove(0);
//        books.println("After all removals");s
//        books.remove(0);
//        books.println("After all removals");
    }

    void countSruoga() {
        int count = 0;
        for (Book b : books) {
            if (b.getAuthor().compareTo("Sruoga") == 0) {
                count++;
            }
        }
        Ks.oun("There are books by Sruoga = " + count);
    }

    void appendBookList() {
        for (int i = 0; i < 8; i++) {
            books.add(new Book("PridetasAutorius" + i, "PridetasPavadinimas" + i,
                    2012 - i, 100 - i, 120 + i * 2));
        }
        books.add("Alio Pavadinimas 1908 370 1060");
        books.add("Autorius Bravo 2008 270 325");
        books.add("Ford Fiesta 2009 370 1600");
        books.add("Audi A6 2006 870 360");
        books.println("Books that are being tested list");
        books.save("bannedBooks.txt");
    }

    void checkBookMarketFilters() {
        BookMarket market = new BookMarket();

        market.allBooks.load("bannedBooks.txt");
        market.allBooks.println("Test Package");

        books = market.getNewerBooks(2001);
        books.println("Books starting from 2001");

        books = market.getByPrice(65, 700);
        books.println("Books from 65 and 700");

        books = market.getLongestBooks();
        books.println("Longest books");

        books = market.getByAuthorAndTitle("A");
        books.println("Books with authors starting with A");

        books = market.getByAuthorAndTitle("Sru");
        books.println("Books written by Sruoga only");

        int n = 0;
        for (Book b : books) {
            n++;    // testuojame ciklo veikimą
        }
        Ks.oun("Only books written by Sruoga count = " + n);
    }

    void checkBookMarketSorting() {
        BookMarket market = new BookMarket();

        market.allBooks.load("bannedBooks.txt");
        Ks.oun("========" + market.allBooks.get(0));
        market.allBooks.println("Test package");
        
        market.allBooks.sortBuble(Book.byAuthorAndTitle);
        market.allBooks.println("Sort by author and title");

        market.allBooks.sortBuble((a, b) -> 
                Double.compare(a.getPrice(), b.getPrice()));
        //market.allBooks.sortBuble(Book.byPrice);
        market.allBooks.println("Sort by price");

        market.allBooks.sortBuble(Book.byPublicationDateAndPrice);
        market.allBooks.println("Sort by publication date and price");

        market.allBooks.sortBuble(byNumberOfPages);
        market.allBooks.sortBuble((a, b)
                -> Integer.compare(a.getNumberOfPages(), b.getNumberOfPages()));
        market.allBooks.println("Sort by number of pages");

        market.allBooks.sortBuble();
        market.allBooks.println("Sort by compareTo - Price");
    }

    static Comparator byNumberOfPages = new Comparator() {
        @Override
        public int compare(Object firstObject, Object secondObject) {
            int m1 = ((Book) firstObject).getNumberOfPages();
            int m2 = ((Book) secondObject).getNumberOfPages();
            // rida atvirkščia mažėjančia tvarka, pradedant nuo didžiausios
            if (m1 < m2) {
                return 1;
            }
            if (m1 > m2) {
                return -1;
            }
            return 0;
        }
    };
    
        public static void main(String... args) {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new BookTest().execute();
    }
}

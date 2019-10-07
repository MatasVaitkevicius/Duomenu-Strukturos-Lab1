/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.vaitkevicius;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.Parsable;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author vaitk
 */
public class Book implements Parsable<Book> {

    final static private int minPages = 50;
    final static private double minPrice = 50.0;
    final static private double maxPrice = 2000.0;

    private String author;
    private String title;
    private int publicationDate;
    private int numberOfPages;
    private double price;

    public Book() {
    }

    public Book(String author, String title, int publicationDate,
            int numberOfPages, double price) {
        this.author = author;
        this.title = title;
        this.publicationDate = publicationDate;
        this.numberOfPages = numberOfPages;
        this.price = price;
    }

    public Book(String data) {
        parse(data);
    }

    @Override
    public final void parse(String data) {
        try {
            Scanner elementsSeparatedBySpace = new Scanner(data);
            author = elementsSeparatedBySpace.next();
            title = elementsSeparatedBySpace.next();
            publicationDate = elementsSeparatedBySpace.nextInt();
            numberOfPages = elementsSeparatedBySpace.nextInt();
            setPrice(elementsSeparatedBySpace.nextDouble());
        } catch (InputMismatchException e) {
            Ks.ern("The book data format is incorrect -> " + data);
        } catch (NoSuchElementException e) {
            Ks.ern("There should be more data about books -> " + data);
        }
    }

    public String validate() {
        String error = "";
        if (numberOfPages < minPages) {
            error = "Book has to have more pages >" + minPages ;
        }
        if (price < minPrice || price > maxPrice) {
            error += "Book has to be between prices [" + minPrice
                    + ":" + maxPrice + "]";
        }
        return error;
    }

    @Override
    public String toString() {  // surenkama visa reikalinga informacija
        return String.format("%-8s %-8s %4d %7d %8.1f %s",
                author, title, publicationDate, numberOfPages, price,
                validate());
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public double getPrice() {
        return price;
    }

    // keisti bus galima tik kainą - kiti parametrai pastovūs
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Book otherBook) {
        double otherPrice = otherBook.getPrice();
        if (price < otherPrice) {
            return -1;
        }
        if (price > otherPrice) {
            return +1;
        }
        return 0;

    }

    public final static Comparator<Book> byAuthorAndTitle
            = (Book firstBook, Book secondBook) -> {
                int comparator = firstBook.getAuthor().compareTo(secondBook.getAuthor());
                if (comparator != 0) {
                    return comparator;
                }
                return firstBook.getTitle().compareTo(secondBook.getTitle());
            };

    public final static Comparator byPrice
            = (Comparator) (Object firstObject, Object secondObject) -> {
                double firstPrice = ((Book) firstObject).getPrice();
                double secondPrice = ((Book) secondObject).getPrice();

               
        // didėjanti tvarka, pradedant nuo mažiausios
         if (firstPrice < secondPrice) {
             return -1;
         }
         if (firstPrice > secondPrice) {
             return 1;
         }
         return 0;
            };

    public final static Comparator byPublicationDateAndPrice
            = (Comparator) (Object firstObject, Object secondObject) -> {
                Book firstBook = (Book) firstObject;
                Book secondBook = (Book) secondObject;
                // metai mažėjančia tvarka, esant vienodiems lyginama price
                if (firstBook.getPublicationDate() < secondBook.getPublicationDate()) {
                    return 1;
                }
                if (firstBook.getPublicationDate() > secondBook.getPublicationDate()) {
                    return -1;
                }
                if (firstBook.getPrice() < secondBook.getPrice()) {
                    return 1;
                }
                if (firstBook.getPrice() > secondBook.getPrice()) {
                    return -1;
                }
                return 0;
            };

    public static void main(String... args) {
        Locale.setDefault(new Locale("LT"));
        Book b1 = new Book("Baba", "Bremenas", 1995, 60, 60);
        Book b2 = new Book("Sruoga", "Dievu Miskas", 2000, 70, 50);
        Book b3 = new Book();
        Book b4 = new Book();
        b3.parse("Brakata Alemono 2003 40 50");
        b4.parse("Brakata Zenono 2003 50 2000");

        Ks.oun(b1);
        Ks.oun(b2);
        Ks.oun(b3);
        Ks.oun(b4);
    }
}

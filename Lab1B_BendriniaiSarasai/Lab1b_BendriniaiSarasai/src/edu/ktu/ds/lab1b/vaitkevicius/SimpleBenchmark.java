/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.vaitkevicius;

import edu.ktu.ds.lab1b.util.Ks;
import edu.ktu.ds.lab1b.util.LinkedList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

/**
 *
 * @author vaitk
 */
public class SimpleBenchmark {
    
    Book[] books;
    LinkedList<Book> bookSeries = new LinkedList<>();
    Random rg = new Random();  // atsitiktinių generatorius
   // int[] counts = {2_000, 4_000, 8_000, 16_000};
//    pabandykite, gal Jūsų kompiuteris įveiks šiuos eksperimentus
//    paieškokite ekstremalaus apkrovimo be burbuliuko metodo
    static int[] counts = {10_000, 20_000, 40_000, 80_000};

    void generateBooks(int count) {
        String[][] authorsAndTitles = {
            {"Jr.Barometras", "Aras", "Jura", "Siena"},
            {"Arlauskas", "Kandis", "Merinda", "Fanta"},
            {"Sruoga", "Tadada", "Bababa", "Zzzzz"},
            {"Domantas", "Lovys", "Koptis", "Karanka"},
            {"Jonauskas", "Laguna", "Escort", "Mondeo"},
        };
        books = new Book[count];
        rg.setSeed(2017);
        for (int i = 0; i < count; i++) {
            int authorIndex = rg.nextInt(authorsAndTitles.length);
            int titleIndex = rg.nextInt(authorsAndTitles[authorIndex].length - 1) +1;
            books[i] = (new Book(authorsAndTitles[authorIndex][0], authorsAndTitles[authorIndex][titleIndex],
                    1800 + rg.nextInt(200), //Publication Date
                    50 + rg.nextInt(400), //Number of Pages
                    50 + rg.nextDouble() * 50)); // Price
        }
        Collections.shuffle(Arrays.asList(books));
        bookSeries.clear();
        for (Book b : books) {
            bookSeries.add(b);
        }
    }

    void generateAndExecute(int elementCount) {
// Paruošiamoji tyrimo dalis
        long t0 = System.nanoTime();
        generateBooks(elementCount);
        LinkedList<Book> carSeries2 = bookSeries.clone();
        LinkedList<Book> carSeries3 = bookSeries.clone();
        LinkedList<Book> carSeries4 = bookSeries.clone();
        long t1 = System.nanoTime();
        System.gc();
        System.gc();
        System.gc();
        long t2 = System.nanoTime();
//  Greitaveikos bandymai ir laiko matavimai
        bookSeries.sortSystem();
        long t3 = System.nanoTime();
        carSeries2.sortSystem(Book.byPrice);
        long t4 = System.nanoTime();
        carSeries3.sortBuble();
        long t5 = System.nanoTime();
        carSeries4.sortBuble(Book.byPrice);
        long t6 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f %7.4f %7.4f \n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9, (t5 - t4) / 1e9, (t6 - t5) / 1e9);
    }

    void execute() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generateBooks(20);
        for (Book b : bookSeries) {
            Ks.oun(b);
        }
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Rūšiavimas sisteminiu greitu būdu be Comparator");
        Ks.oun("4 - Rūšiavimas sisteminiu greitu būdu su Comparator");
        Ks.oun("5 - Rūšiavimas List burbuliuku be Comparator");
        Ks.oun("6 - Rūšiavimas List burbuliuku su Comparator");
        Ks.ouf("%6d %7d %7d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4, 5, 6);
        for (int n : counts) {
            generateAndExecute(n);
        }
    }

    public static void main(String[] args) {
        // suvienodiname skaičių formatus pagal LT lokalę (10-ainis kablelis)
        Locale.setDefault(new Locale("LT"));
        new SimpleBenchmark().execute();
    }
}

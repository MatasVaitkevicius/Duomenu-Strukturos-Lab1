/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.vaitkevicius;

import edu.ktu.ds.lab1b.util.ParsableList;
import java.util.Random;

/**
 *
 * @author vaitk
 */
public class BookList extends ParsableList<Book>{
    
    public BookList() {
    }
    
    public BookList(int count) {
        // invoke or call parent class constructor 
        super();
        String[][] authorsAndTitles = {
            {"Jr.Barometras", "Aras", "Jura", "Siena"},
            {"Arlauskas", "Kandis", "Merinda", "Fanta"},
            {"Sruoga", "Tadada", "Bababa", "Zzzzz"},
            {"Domantas", "Lovys", "Koptis", "Karanka"},
            {"Jonauskas", "Laguna", "Escort", "Mondeo"},
        };
        Random random = new Random();
        random.setSeed(2000);
        for (int i = 0; i < count; i++) {
            int authorIndex = random.nextInt(authorsAndTitles.length);
            int titleIndex = random.nextInt(authorsAndTitles[authorIndex].length - 1) +1;
            add(new Book(authorsAndTitles[authorIndex][0], authorsAndTitles[authorIndex][titleIndex],
                    1800 + random.nextInt(200), //Publication Date
                    50 + random.nextInt(400), //Number of Pages
                    50 + random.nextDouble() * 50)); // Price
        }
    }
    
    @Override
    protected Book createElement(String data) {
        return new Book(data);
    }
}
//    final static private int minPages = 50;
//    final static private double minPrice = 50.0;
//    final static private double maxPrice = 200.0;
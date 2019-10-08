/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.util;

import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedList;

/**
 *
 * @author vaitk
 */
public class Task11Benchmark {

    int[] counts = {100_000, 200_000, 500_000};
    ArrayList<Integer> intArrayList = new ArrayList<>();
    LinkedList<Integer> intLinkedList = new LinkedList<>();
    Object randomObject = new Object();
    Random random = new Random();

    void generateValues(int count) {
        intArrayList.clear();
        intLinkedList.clear();
        random.setSeed(123);
        randomObject = random.nextInt();
        for (int i = 0; i < count; i++) {
            intArrayList.add(random.nextInt());
            intLinkedList.add(random.nextInt());
        }
    }

    void generateAndExecute(int elementCount) {
        long t0 = System.nanoTime();
        generateValues(elementCount);
        long t1 = System.nanoTime();
        System.gc();
        long t2 = System.nanoTime();
        //  Greitaveikos bandymai ir laiko matavimai
        intArrayList.lastIndexOf(randomObject);
        long t3 = System.nanoTime();
        intLinkedList.lastIndexOf(randomObject);
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9);

    }

    void execute() {
        long memTotal = Runtime.getRuntime().totalMemory();
        Ks.oun("memTotal= " + memTotal);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generateValues(100);
        Ks.oun("Array List - ");
        for (int value : intArrayList) {
            Ks.oun(value);
        }
        Ks.oun("==============");
        Ks.oun("Linked List - ");
        for (int value : intLinkedList) {
            Ks.oun(value);
        }
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Int Array List Last index of");
        Ks.oun("4 - Int Linked List Last index of");
       
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : counts) {
            generateAndExecute(n);
        }
    }

    public static void main(String... args) {
        new Task11Benchmark().execute();
    }
}


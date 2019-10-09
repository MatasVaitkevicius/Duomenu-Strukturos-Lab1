/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ktu.ds.lab1b.util;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author vaitk
 */
public class Task5Benchmark {

    int[] counts = {100_000, 200_000, 500_000};
    ArrayList<Double> values = new ArrayList<>();
    Random random = new Random();

    void generateValues(int count) {
        values.clear();
        random.setSeed(123);
        for (int i = 0; i < count; i++) {
            values.add(random.nextDouble());
        }
    }

    void generateAndExecute(int elementCount) {
        long t0 = System.nanoTime();
        generateValues(elementCount);
        long t1 = System.nanoTime();
        System.gc();
        long t2 = System.nanoTime();
        //  Greitaveikos bandymai ir laiko matavimai
        benchmarkMathSqrt();
        long t3 = System.nanoTime();
        benchmarkMathSin();
        long t4 = System.nanoTime();
        Ks.ouf("%7d %7.4f %7.4f %7.4f %7.4f \n", elementCount,
                (t1 - t0) / 1e9, (t2 - t1) / 1e9, (t3 - t2) / 1e9,
                (t4 - t3) / 1e9);

    }

    void benchmarkMathSqrt() {
        double answer = 0.0;
        for (int i = 0; i < values.size(); i++) {
            answer = Math.sqrt(values.get(i));
        }
    }

    void benchmarkMathSin() {
        double answer = 0.0;
        for (int i = 0; i < values.size(); i++) {
            answer = Math.sin(values.get(i));
        }
    }
    
    void execute() {
        long memTotal = Runtime.getRuntime().totalMemory();
        long memUsed = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        Ks.oun("Memory total = " + memTotal);
        Ks.oun("Memory used = " + memUsed);
        // Pasižiūrime kaip generuoja automobilius (20) vienetų)
        generateValues(100);
        for (double value : values) {
            Ks.oun(value);
        }
        Ks.oun("1 - Pasiruošimas tyrimui - duomenų generavimas");
        Ks.oun("2 - Pasiruošimas tyrimui - šiukšlių surinkimas");
        Ks.oun("3 - Math.sqrt");
        Ks.oun("4 - Math.sin");
       
        Ks.ouf("%6d %7d %7d %7d %7d \n", 0, 1, 2, 3, 4);
        for (int n : counts) {
            generateAndExecute(n);
        }
    }

    public static void main(String... args) {
        new Task5Benchmark().execute();
    }
}

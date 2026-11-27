package com.uet.construction.singleton;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.function.Supplier;

/**
 * Helper for Lab Task 3: starts 5 threads that all call the given
 * getInstance() at the same moment and prints System.identityHashCode()
 * of the object each thread received.
 */
public final class RaceHarness {

    public static final int THREAD_COUNT = 5;

    private RaceHarness() {
    }

    /**
     * @param label  text printed in the headline
     * @param source the getInstance() call under test
     * @return the set of DISTINCT identity hash codes that the threads received
     */
    public static Set<Integer> run(String label, Supplier<?> source) throws InterruptedException {
        System.out.println("--- " + label + " : " + THREAD_COUNT + " concurrent threads ---");

        Set<Integer> distinctHashes = ConcurrentHashMap.newKeySet();
        CountDownLatch startSignal = new CountDownLatch(1);
        List<Thread> threads = new ArrayList<>();

        for (int i = 1; i <= THREAD_COUNT; i++) {
            final int id = i;
            Thread t = new Thread(() -> {
                try {
                    startSignal.await();          // all threads start together
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
                Object obj = source.get();
                int hash = System.identityHashCode(obj);
                distinctHashes.add(hash);
                System.out.println("Thread-" + id + " received instance with hash code: " + hash);
            }, "Thread-" + id);
            threads.add(t);
            t.start();
        }

        startSignal.countDown();                  // release all threads at once
        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Distinct instances seen : " + distinctHashes.size());
        return distinctHashes;
    }
}

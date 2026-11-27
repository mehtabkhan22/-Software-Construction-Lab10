package com.uet.construction.singleton;

/**
 * Lab Task 3 - STEP 1: the NAIVE lazy Singleton (this is the original
 * "AuditLogger" before the fix; it is kept under this name so that the broken
 * and the fixed version can both stay in the project).
 *
 * BUG: getInstance() is not synchronized. Several threads can pass the
 * "instance == null" check at the same time, and each one then creates its
 * own object. Thread.sleep(50) simulates a slow initialization and makes the
 * race condition appear every time.
 */
public class NaiveAuditLogger {

    private static NaiveAuditLogger instance;

    /** How many objects were really created (should be 1, but is not). */
    private static int createdCount = 0;

    private NaiveAuditLogger() {
        createdCount++;
    }

    public static NaiveAuditLogger getInstance() {
        if (instance == null) {                 // check
            try {
                Thread.sleep(50);               // simulated initialization latency
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            instance = new NaiveAuditLogger();  // act  (check-then-act is NOT atomic)
        }
        return instance;
    }

    public static int getCreatedCount() {
        return createdCount;
    }

    public void log(String message) {
        System.out.println("[AUDIT] " + message);
    }
}

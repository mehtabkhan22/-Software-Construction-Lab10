package com.uet.construction.singleton;

/**
 * Lab Task 3 - STEP 2: the FIXED AuditLogger (thread-safe Singleton).
 *
 * Fix = Double-Checked Locking:
 *   1. "volatile" on the instance field - guarantees that other threads never
 *      see a half-constructed object (prevents instruction re-ordering).
 *   2. First check WITHOUT a lock - fast path once the object exists.
 *   3. synchronized block - only one thread at a time may create the object.
 *   4. Second check INSIDE the lock - a thread that waited must not create a
 *      second object.
 *
 * Thread.sleep(50) is kept on purpose to prove that the fix still works under
 * the same initialization latency that broke the naive version.
 */
public class AuditLogger {

    private static volatile AuditLogger instance;

    private static int createdCount = 0;

    private AuditLogger() {
        createdCount++;
    }

    public static AuditLogger getInstance() {
        if (instance == null) {                         // 1st check (no lock)
            synchronized (AuditLogger.class) {
                if (instance == null) {                 // 2nd check (with lock)
                    try {
                        Thread.sleep(50);               // simulated initialization latency
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                    instance = new AuditLogger();
                }
            }
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

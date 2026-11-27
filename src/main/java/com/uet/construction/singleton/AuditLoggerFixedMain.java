package com.uet.construction.singleton;

import java.util.Set;

/**
 * Lab Task 3 - STEP 2 demo: AuditLogger fixed with volatile + Double-Checked Locking.
 * Run: right-click this file in NetBeans -> Run File (Shift+F6).
 */
public class AuditLoggerFixedMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Lab Task 3 (Step 2): FIXED AuditLogger (Double-Checked Locking) ===");
        System.out.println();

        Set<Integer> hashes = RaceHarness.run("AuditLogger", AuditLogger::getInstance);

        System.out.println("Constructor executed    : " + AuditLogger.getCreatedCount() + " time(s)");
        System.out.println(hashes.size() == 1
                ? "RESULT: all " + RaceHarness.THREAD_COUNT + " threads received the SAME instance."
                : "RESULT: FAILED - multiple instances were created!");
    }
}

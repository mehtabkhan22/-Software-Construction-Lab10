package com.uet.construction.singleton;

import java.util.Set;

/**
 * Lab Task 3 - STEP 1 demo: the naive Singleton FAILS under multithreading.
 * Run: right-click this file in NetBeans -> Run File (Shift+F6).
 */
public class AuditLoggerNaiveMain {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Lab Task 3 (Step 1): NAIVE lazy AuditLogger ===");
        System.out.println();

        Set<Integer> hashes = RaceHarness.run("NaiveAuditLogger", NaiveAuditLogger::getInstance);

        System.out.println("Constructor executed    : " + NaiveAuditLogger.getCreatedCount() + " time(s)");
        System.out.println(hashes.size() > 1
                ? "RESULT: RACE CONDITION - multiple instances were created!"
                : "RESULT: (race not visible in this run - run again)");
    }
}

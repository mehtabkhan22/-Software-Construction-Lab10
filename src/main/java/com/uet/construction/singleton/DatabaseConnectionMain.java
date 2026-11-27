package com.uet.construction.singleton;

/**
 * Demo for Lab Task 1.
 * Run: right-click this file in NetBeans -> Run File (Shift+F6).
 */
public class DatabaseConnectionMain {

    public static void main(String[] args) {
        System.out.println("=== Lab Task 1: Database Connection Manager (Lazy Singleton) ===");
        System.out.println();

        // Three independent modules, each one asks for the connection.
        StudentPortal studentPortal = new StudentPortal();
        FeeBillingService feeBillingService = new FeeBillingService();
        AttendanceTracker attendanceTracker = new AttendanceTracker();

        DatabaseConnectionManager ref1 = studentPortal.getConnection();
        DatabaseConnectionManager ref2 = feeBillingService.getConnection();
        DatabaseConnectionManager ref3 = attendanceTracker.getConnection();

        System.out.println();
        System.out.println("--- Identity hash codes ---");
        System.out.println("StudentPortal      -> " + System.identityHashCode(ref1));
        System.out.println("FeeBillingService  -> " + System.identityHashCode(ref2));
        System.out.println("AttendanceTracker  -> " + System.identityHashCode(ref3));

        System.out.println();
        System.out.println("--- Sample query from each module ---");
        studentPortal.runSampleQuery();
        feeBillingService.runSampleQuery();
        attendanceTracker.runSampleQuery();

        System.out.println();
        System.out.println("--- Proof using == ---");
        System.out.println("StudentPortal     == FeeBillingService : " + (ref1 == ref2));
        System.out.println("FeeBillingService == AttendanceTracker : " + (ref2 == ref3));
        System.out.println("StudentPortal     == AttendanceTracker : " + (ref1 == ref3));
        System.out.println("Constructor executed " + DatabaseConnectionManager.getConstructorCallCount()
                + " time(s)");

        boolean onlyOne = (ref1 == ref2) && (ref2 == ref3)
                && DatabaseConnectionManager.getConstructorCallCount() == 1;
        System.out.println(onlyOne
                ? "RESULT: only ONE database connection object was instantiated."
                : "RESULT: FAILED - more than one connection object exists!");
    }
}

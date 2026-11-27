package com.uet.construction.singleton;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** Tests for the Lazy Singleton DatabaseConnectionManager (Lab Task 1). */
class DatabaseConnectionManagerTest {

    @Test
    void getInstance_neverReturnsNull() {
        assertNotNull(DatabaseConnectionManager.getInstance());
    }

    @Test
    void getInstance_calledManyTimes_returnsSameObject() {
        DatabaseConnectionManager a = DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager b = DatabaseConnectionManager.getInstance();
        assertSame(a, b);
    }

    @Test
    void modules_shareTheSameConnection() {
        StudentPortal portal = new StudentPortal();
        FeeBillingService billing = new FeeBillingService();
        AttendanceTracker tracker = new AttendanceTracker();

        assertSame(portal.getConnection(), billing.getConnection());
        assertSame(billing.getConnection(), tracker.getConnection());
    }

    @Test
    void constructor_executesExactlyOnce() {
        DatabaseConnectionManager.getInstance();
        DatabaseConnectionManager.getInstance();
        assertEquals(1, DatabaseConnectionManager.getConstructorCallCount());
    }

    @Test
    void executeQuery_countsQueriesOnTheSharedConnection() {
        DatabaseConnectionManager db = DatabaseConnectionManager.getInstance();
        int before = db.getQueriesExecuted();

        new StudentPortal().runSampleQuery();
        new FeeBillingService().runSampleQuery();

        assertEquals(before + 2, db.getQueriesExecuted());
    }
}

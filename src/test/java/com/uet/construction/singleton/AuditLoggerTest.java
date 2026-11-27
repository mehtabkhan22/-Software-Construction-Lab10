package com.uet.construction.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.Set;
import org.junit.jupiter.api.Test;

/** Tests for the thread-safe AuditLogger (Lab Task 3, Double-Checked Locking). */
class AuditLoggerTest {

    @Test
    void getInstance_calledTwice_returnsSameObject() {
        assertSame(AuditLogger.getInstance(), AuditLogger.getInstance());
    }

    @Test
    void fiveConcurrentThreads_allReceiveTheSameInstance() throws InterruptedException {
        Set<Integer> hashes = RaceHarness.run("AuditLogger (test)", AuditLogger::getInstance);

        assertEquals(1, hashes.size());
        assertEquals(1, AuditLogger.getCreatedCount());
    }
}

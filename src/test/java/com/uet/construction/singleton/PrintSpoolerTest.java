package com.uet.construction.singleton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests for the PrintSpooler Singleton (Lab Task 2). */
class PrintSpoolerTest {

    /** The spooler is a Singleton, so every test first empties the shared queue. */
    @BeforeEach
    void emptyQueue() {
        PrintSpooler spooler = PrintSpooler.getInstance();
        while (spooler.getPendingJobCount() > 0) {
            spooler.processNextJob();
        }
    }

    @Test
    void bothLabs_getTheSameSpoolerInstance() {
        SoftwareLab1 software = new SoftwareLab1();
        NetworkingLab2 networking = new NetworkingLab2();
        assertSame(software.getSpooler(), networking.getSpooler());
    }

    @Test
    void jobsFromBothLabs_areInTheSameQueue_noneLost() {
        SoftwareLab1 software = new SoftwareLab1();
        NetworkingLab2 networking = new NetworkingLab2();

        software.submit("doc1.pdf");
        software.submit("doc2.pdf");
        networking.submit("doc3.pdf");

        assertEquals(3, software.getSpooler().getPendingJobCount());
        assertEquals(3, networking.getSpooler().getPendingJobCount());
    }

    @Test
    void jobs_areProcessedInFifoOrder() {
        SoftwareLab1 software = new SoftwareLab1();
        NetworkingLab2 networking = new NetworkingLab2();
        software.submit("doc1.pdf");
        software.submit("doc2.pdf");
        networking.submit("doc3.pdf");

        PrintSpooler spooler = PrintSpooler.getInstance();
        assertEquals("SoftwareLab1 -> doc1.pdf", spooler.processNextJob());
        assertEquals("SoftwareLab1 -> doc2.pdf", spooler.processNextJob());
        assertEquals("NetworkingLab2 -> doc3.pdf", spooler.processNextJob());
        assertEquals(0, spooler.getPendingJobCount());
    }

    @Test
    void processNextJob_onEmptyQueue_returnsNull() {
        assertNull(PrintSpooler.getInstance().processNextJob());
    }
}

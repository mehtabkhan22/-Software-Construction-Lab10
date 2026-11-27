package com.uet.construction.singleton;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Lab Task 2 - Campus Print Spooler Queue (shared state consistency).
 *
 * One single queue of pending print jobs is shared by every computer
 * laboratory. Because the class is a Singleton, a job added from one lab is
 * visible to every other lab, and no job is lost or overwritten.
 *
 * All public methods are synchronized so that the shared queue stays
 * consistent even if several labs print at the same moment.
 */
public final class PrintSpooler {

    private static PrintSpooler instance;

    /** FIFO queue of pending print jobs. */
    private final Queue<String> jobQueue = new LinkedList<>();

    private PrintSpooler() {
        System.out.println("[PrintSpooler] Spooler started (single shared queue created).");
    }

    public static synchronized PrintSpooler getInstance() {
        if (instance == null) {
            instance = new PrintSpooler();
        }
        return instance;
    }

    /** Adds a job to the END of the queue. */
    public synchronized void addPrintJob(String labName, String documentName) {
        String job = labName + " -> " + documentName;
        jobQueue.offer(job);
        System.out.println("[PrintSpooler] Job added   : " + job
                + "   (pending = " + jobQueue.size() + ")");
    }

    /**
     * Removes and "prints" the job at the FRONT of the queue (FIFO).
     *
     * @return the processed job, or null if the queue is empty
     */
    public synchronized String processNextJob() {
        String job = jobQueue.poll();
        if (job == null) {
            System.out.println("[PrintSpooler] No pending jobs to process.");
            return null;
        }
        System.out.println("[PrintSpooler] Job printed : " + job
                + "   (pending = " + jobQueue.size() + ")");
        return job;
    }

    public synchronized int getPendingJobCount() {
        return jobQueue.size();
    }
}

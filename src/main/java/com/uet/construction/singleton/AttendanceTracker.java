package com.uet.construction.singleton;

/** Simulated university module (Lab Task 1). It asks the Singleton for the shared connection. */
public class AttendanceTracker {

    private final DatabaseConnectionManager connection;

    public AttendanceTracker() {
        this.connection = DatabaseConnectionManager.getInstance();
    }

    /** @return the connection reference this module is using. */
    public DatabaseConnectionManager getConnection() {
        return connection;
    }

    /** Runs a sample query through the shared connection. */
    public void runSampleQuery() {
        connection.executeQuery("AttendanceTracker", "UPDATE attendance SET present = 1 WHERE student_id = 101");
    }
}

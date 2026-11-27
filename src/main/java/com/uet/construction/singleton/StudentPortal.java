package com.uet.construction.singleton;

/** Simulated university module (Lab Task 1). It asks the Singleton for the shared connection. */
public class StudentPortal {

    private final DatabaseConnectionManager connection;

    public StudentPortal() {
        this.connection = DatabaseConnectionManager.getInstance();
    }

    /** @return the connection reference this module is using. */
    public DatabaseConnectionManager getConnection() {
        return connection;
    }

    /** Runs a sample query through the shared connection. */
    public void runSampleQuery() {
        connection.executeQuery("StudentPortal", "SELECT * FROM students WHERE id = 101");
    }
}

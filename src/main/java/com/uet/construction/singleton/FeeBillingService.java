package com.uet.construction.singleton;

/** Simulated university module (Lab Task 1). It asks the Singleton for the shared connection. */
public class FeeBillingService {

    private final DatabaseConnectionManager connection;

    public FeeBillingService() {
        this.connection = DatabaseConnectionManager.getInstance();
    }

    /** @return the connection reference this module is using. */
    public DatabaseConnectionManager getConnection() {
        return connection;
    }

    /** Runs a sample query through the shared connection. */
    public void runSampleQuery() {
        connection.executeQuery("FeeBillingService", "SELECT SUM(amount) FROM fee_challans WHERE status = 'UNPAID'");
    }
}

package com.uet.construction.singleton;

/**
 * Lab Task 1 - Centralized Database Connection Manager (Lazy Singleton).
 *
 * All university modules share ONE connection object instead of opening a
 * redundant connection each. The static counter {@code constructorCallCount}
 * records how many times the constructor really ran - for a correct
 * Singleton it must stay at 1.
 *
 * The instance is created lazily (only on the first getInstance() call).
 * getInstance() is synchronized so that the lazy creation is also safe when
 * several modules start on different threads.
 */
public final class DatabaseConnectionManager {

    /** The single shared instance (null until first requested = lazy). */
    private static DatabaseConnectionManager instance;

    /** Counts how many times the constructor has executed. */
    private static int constructorCallCount = 0;

    /** Counts the queries executed through the shared connection. */
    private int queriesExecuted = 0;

    /** Private constructor: nobody outside this class can call "new". */
    private DatabaseConnectionManager() {
        constructorCallCount++;
        System.out.println("[DatabaseConnectionManager] Opening database connection ... "
                + "(constructor call #" + constructorCallCount + ")");
    }

    /** Global access point - creates the object on first use only. */
    public static synchronized DatabaseConnectionManager getInstance() {
        if (instance == null) {
            instance = new DatabaseConnectionManager();
        }
        return instance;
    }

    /** Simulates running an SQL statement on behalf of a module. */
    public synchronized void executeQuery(String moduleName, String sql) {
        queriesExecuted++;
        System.out.println("[DB] Module '" + moduleName + "' executed query #"
                + queriesExecuted + ": " + sql);
    }

    /** @return how many times the constructor has executed (must be 1). */
    public static int getConstructorCallCount() {
        return constructorCallCount;
    }

    /** @return number of queries executed through the shared connection. */
    public synchronized int getQueriesExecuted() {
        return queriesExecuted;
    }
}

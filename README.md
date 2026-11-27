# Lab 10 - Creational Design Patterns: The Singleton Pattern

**Course:** Software Construction & Development
**Institute:** University of Engineering and Technology, Abbottabad Campus
**Instructor:** Engr. Rizwan Shah
**Submitted by:** Muhammad Mehtab (24ABSWE0018)

## Objective
Apply creational software construction principles by designing centralized, memory-safe and
thread-safe Singleton components that prevent duplicate resource allocation and keep shared
state consistent across modules.

## Tasks

### Lab Task 1 - Centralized Database Connection Manager (Lazy Singleton)
`DatabaseConnectionManager` has a private constructor, a static counter of constructor calls
and `executeQuery(String moduleName, String sql)`. Three modules (`StudentPortal`,
`FeeBillingService`, `AttendanceTracker`) call `getInstance()`. The demo prints
`System.identityHashCode()` for all three references, runs one query from each module and
proves with `==` that only one connection object exists.

### Lab Task 2 - Campus Print Spooler Queue (Shared State Consistency)
`PrintSpooler` encapsulates a `Queue<String>` (`LinkedList`) with `addPrintJob(labName,
documentName)`, `processNextJob()` and `getPendingJobCount()`. `SoftwareLab1` submits two
jobs and `NetworkingLab2` submits one; all jobs are then processed in FIFO order from the same
shared queue.

### Lab Task 3 - Exposing & Fixing Race Conditions (Thread Safety)
* `NaiveAuditLogger` - lazy Singleton **without** synchronization and with `Thread.sleep(50)`
  before `new`. Five concurrent threads receive different objects (race condition).
* `AuditLogger` - the fix with `volatile` + Double-Checked Locking (`synchronized`).
  All five threads receive the same instance.

## How to run (NetBeans)
1. `File -> Open Project` and select this folder (Maven project, JDK 17 or newer).
2. Right-click a class below and choose **Run File** (Shift+F6):

| Task | Class to run |
|------|--------------|
| 1 | `DatabaseConnectionMain` |
| 2 | `PrintSpoolerMain` |
| 3 (naive, shows the bug) | `AuditLoggerNaiveMain` |
| 3 (fixed, shows the fix) | `AuditLoggerFixedMain` |

3. Run the unit tests with right-click project -> **Test** (JUnit 5).

## How to run (command line)
```
mvn test
mvn compile exec:java -Dexec.mainClass=com.uet.construction.singleton.PrintSpoolerMain
```

## Project structure
```
src/main/java/com/uet/construction/singleton
    DatabaseConnectionManager, StudentPortal, FeeBillingService, AttendanceTracker, DatabaseConnectionMain
    PrintSpooler, SoftwareLab1, NetworkingLab2, PrintSpoolerMain
    NaiveAuditLogger, AuditLogger, RaceHarness, AuditLoggerNaiveMain, AuditLoggerFixedMain
src/test/java/com/uet/construction/singleton
    DatabaseConnectionManagerTest, PrintSpoolerTest, AuditLoggerTest
```

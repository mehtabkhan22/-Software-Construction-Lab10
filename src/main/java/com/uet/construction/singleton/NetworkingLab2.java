package com.uet.construction.singleton;

/** Simulated client: the Computer Networking laboratory (Lab Task 2). */
public class NetworkingLab2 {

    private static final String LAB_NAME = "NetworkingLab2";

    private final PrintSpooler spooler = PrintSpooler.getInstance();

    public void submit(String documentName) {
        spooler.addPrintJob(LAB_NAME, documentName);
    }

    public PrintSpooler getSpooler() {
        return spooler;
    }
}

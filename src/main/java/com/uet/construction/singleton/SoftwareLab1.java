package com.uet.construction.singleton;

/** Simulated client: the Software Engineering computer laboratory (Lab Task 2). */
public class SoftwareLab1 {

    private static final String LAB_NAME = "SoftwareLab1";

    private final PrintSpooler spooler = PrintSpooler.getInstance();

    public void submit(String documentName) {
        spooler.addPrintJob(LAB_NAME, documentName);
    }

    public PrintSpooler getSpooler() {
        return spooler;
    }
}

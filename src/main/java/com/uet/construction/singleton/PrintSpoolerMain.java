package com.uet.construction.singleton;

/**
 * Demo for Lab Task 2.
 * Run: right-click this file in NetBeans -> Run File (Shift+F6).
 */
public class PrintSpoolerMain {

    public static void main(String[] args) {
        System.out.println("=== Lab Task 2: Campus Print Spooler Queue ===");
        System.out.println();

        SoftwareLab1 softwareLab = new SoftwareLab1();
        NetworkingLab2 networkingLab = new NetworkingLab2();

        System.out.println("--- Same spooler object? ---");
        PrintSpooler s1 = softwareLab.getSpooler();
        PrintSpooler s2 = networkingLab.getSpooler();
        System.out.println("SoftwareLab1   spooler hash : " + System.identityHashCode(s1));
        System.out.println("NetworkingLab2 spooler hash : " + System.identityHashCode(s2));
        System.out.println("SoftwareLab1 spooler == NetworkingLab2 spooler : " + (s1 == s2));

        System.out.println();
        System.out.println("--- Submitting jobs ---");
        softwareLab.submit("OOP_Assignment.pdf");
        softwareLab.submit("DesignPatterns_Notes.pdf");
        networkingLab.submit("Subnetting_Worksheet.pdf");

        System.out.println();
        System.out.println("Pending jobs seen by SoftwareLab1   : " + softwareLab.getSpooler().getPendingJobCount());
        System.out.println("Pending jobs seen by NetworkingLab2 : " + networkingLab.getSpooler().getPendingJobCount());

        System.out.println();
        System.out.println("--- Processing jobs in FIFO order ---");
        PrintSpooler spooler = PrintSpooler.getInstance();
        while (spooler.getPendingJobCount() > 0) {
            spooler.processNextJob();
        }

        System.out.println();
        System.out.println("Pending jobs after processing : " + spooler.getPendingJobCount());
    }
}

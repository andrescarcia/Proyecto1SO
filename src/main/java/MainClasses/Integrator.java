/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class Integrator extends Thread {
    private final Drive drive;
    private double earnings = 0;
    private boolean isActive = true;

    public Integrator(Drive drive) {
        this.drive = drive;
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                drive.getMutex().acquire();

                if (drive.canAssembleGame()) {
                    System.out.println("Integrator: Components available. Starting assembly...");

                    // Consume components
                    drive.assembleGame();

                    // Sleep for the necessary assembly time
                    // (Here we sleep for a short time. Adjust as necessary.)
                    Thread.sleep(2000);

                    // Add assembled game to Drive
                    // (Implement this as per your requirements.)
                    System.out.println("Integrator: Game assembled!");

                    // Pay the Integrator
                    payIntegrator();
                } else {
                    System.out.println("Integrator: Not enough components to assemble a game.");
                }
                
                drive.getMutex().release();

                // Sleep for a while before the next check.
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Integrator interrupted.");
            }
        }
    }

    public void payIntegrator() {
        earnings += 25 * 24;  // $25/hour, 24 hours/day, for 2 days
        System.out.println("Integrator: Paid for 2 days of work. Total earnings: $" + earnings);
    }

    public void stopWorking() {
        isActive = false;
    }
}


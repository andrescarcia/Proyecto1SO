/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */

public class ProjectManager extends Thread {
    private final double hourlyRate = 20;
    private double earnings;
    private int daysToDelivery;
    private boolean isWatchingStream;
    private int numberOfFines;
    private double totalFines;

    public ProjectManager(int initialDaysToDelivery) {
        this.daysToDelivery = initialDaysToDelivery;
        this.isWatchingStream = false;
        this.numberOfFines = 0;
        this.totalFines = 0;
    }

    @Override
    public void run() {
        while (true) { // El Project Manager sigue trabajando indefinidamente
            try {
                for (int i = 0; i < 16; i++) { // 16 horas de trabajo y streaming
                    isWatchingStream = !isWatchingStream; // Cambia entre ver streams y trabajar
                    Thread.sleep(30 * 60 * 1000); // Duerme 30 minutos
                }
                updateDaysToDelivery();
                Thread.sleep(8 * 60 * 60 * 1000); // Duerme 8 horas
            } catch (InterruptedException ex) {
                System.out.println("Error en el hilo del Project Manager.");
            }
        }
    }

    private void updateDaysToDelivery() {
        if (daysToDelivery > 0) {
            daysToDelivery--;
        }
    }

    public void fine() {
        numberOfFines++;
        totalFines += 50;
    }
    
        public void addFine() {
        this.numberOfFines++;
    }

    public void deductSalary(double amount) {
        this.earnings -= amount;
    }

    public boolean watchStreams() {
        this.isWatchingStream = true;
        return true;
    }

    public boolean work() {
        this.isWatchingStream = false;
        return true;
    }
    
    

    // ... (otros métodos getter y setter según sea necesario)

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getEarnings() {
        return earnings;
    }

    public int getDaysToDelivery() {
        return daysToDelivery;
    }


    public int getNumberOfFines() {
        return numberOfFines;
    }

    public double getTotalFines() {
        return totalFines;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public void setDaysToDelivery(int daysToDelivery) {
        this.daysToDelivery = daysToDelivery;
    }

    public void setIsWatchingStream(boolean isWatchingStream) {
        this.isWatchingStream = isWatchingStream;
    }

    public void setNumberOfFines(int numberOfFines) {
        this.numberOfFines = numberOfFines;
    }

    public void setTotalFines(double totalFines) {
        this.totalFines = totalFines;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.concurrent.Semaphore;
import javax.swing.JLabel;

/**
 *
 * @author andre
 */
public class Integrator extends Thread {
    private Drive drive;
    private Semaphore mutex;
    private double salary = 25;
    private boolean isActive = true;
    private int dayDuration;

    public Integrator(Drive drive, Semaphore m, int day) {
        this.drive = drive;
        this.mutex = m;
        this.dayDuration = day;
    }

    @Override
    public void run() {
        while (isActive) {
            try {
                // Verificar si hay suficientes componentes para ensamblar un juego
                this.mutex.acquire(1);
                if (this.drive.canAssembleGame()) {
                    System.out.println("Integrador: Componentes disponibles. Iniciando ensamblaje...");

                    // Consumir componentes
                    drive.assembleGame();
                    this.drive.addSalary(this.salary);
                    this.mutex.release();
                    // Dormir durante el tiempo necesario para el ensamblaje
                    // (Aquí dormimos por un tiempo corto. Ajustar según sea necesario.)
                    sleep(this.dayDuration*2);

                    // Agregar el juego ensamblado al Drive
                    // (Implementar esto según tus requisitos.)
                    System.out.println("Integrador: ¡Juego ensamblado!");

                } else {
                    System.out.println("Integrador: No hay suficientes componentes para ensamblar un juego.");
                    this.drive.addSalary((this.salary));
                    this.mutex.release();
                    sleep(this.dayDuration);
                }
                
            } catch (InterruptedException e) {
                System.out.println("Integrador interrumpido.");
            }
        }
    }


    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public Drive getDrive() {
        return drive;
    }

    public void setDrive(Drive drive) {
        this.drive = drive;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    
}



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
    private double earnings = 0;
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
                    this.mutex.release();
                    // Dormir durante el tiempo necesario para el ensamblaje
                    // (Aquí dormimos por un tiempo corto. Ajustar según sea necesario.)
                    sleep(this.dayDuration*2);

                    // Agregar el juego ensamblado al Drive
                    // (Implementar esto según tus requisitos.)
                    System.out.println("Integrador: ¡Juego ensamblado!");

                    // Pagar al Integrador
                    payIntegrator();
                } else {
                    System.out.println("Integrador: No hay suficientes componentes para ensamblar un juego.");
                    this.mutex.release();
                    sleep(this.dayDuration);
                }
                // Pagar al Integrador
                payIntegrator();
                
            } catch (InterruptedException e) {
                System.out.println("Integrador interrumpido.");
            }
        }
    }

    public void payIntegrator() {
        // $25/hora, 24 horas/día, durante 2 días
        earnings += 25 * 24;  
        System.out.println("Integrador: Pagado por 2 días de trabajo. Ganancias totales: $" + earnings);
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

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    
}



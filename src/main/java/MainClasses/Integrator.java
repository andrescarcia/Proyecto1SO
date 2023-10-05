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
                // Adquirir el semáforo para asegurar el acceso exclusivo al Drive
                drive.getMutex().acquire();

                // Verificar si hay suficientes componentes para ensamblar un juego
                if (drive.canAssembleGame()) {
                    System.out.println("Integrador: Componentes disponibles. Iniciando ensamblaje...");

                    // Consumir componentes
                    drive.assembleGame();

                    // Dormir durante el tiempo necesario para el ensamblaje
                    // (Aquí dormimos por un tiempo corto. Ajustar según sea necesario.)
                    Thread.sleep(2000);

                    // Agregar el juego ensamblado al Drive
                    // (Implementar esto según tus requisitos.)
                    System.out.println("Integrador: ¡Juego ensamblado!");

                    // Pagar al Integrador
                    payIntegrator();
                } else {
                    System.out.println("Integrador: No hay suficientes componentes para ensamblar un juego.");
                }
                
                // Liberar el semáforo para permitir el acceso a otros threads
                drive.getMutex().release();

                // Dormir un rato antes de la siguiente comprobación.
                Thread.sleep(1000);
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

    public void stopWorking() {
        // Detener el hilo
        isActive = false;
    }
}



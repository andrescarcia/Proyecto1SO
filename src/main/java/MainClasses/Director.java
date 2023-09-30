/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class Director extends Thread {
    private final double hourlyRate = 30;
    private double earnings;
    private ProjectManager pm;
    private Drive drive;

    public Director(ProjectManager pm, Drive drive) {
        this.pm = pm;
        this.drive = drive;
        this.earnings = 0;
    }

    @Override
    public void run() {
        while (true) { // El Director sigue trabajando indefinidamente
            try {
                if (pm.getDaysToDelivery() == 0) {
                    deliverGames();
                    resetDaysToDelivery();
                } else {
                    performAdministrativeTasks();
                    checkOnProjectManager(); // No necesitas pasar un argumento aquí
                }
                Thread.sleep(24 * 60 * 60 * 1000); // Duerme 24 horas
            } catch (InterruptedException ex) {
                System.out.println("Error en el hilo del Director.");
            }
        }
    }

    private void deliverGames() {
        // Lógica para enviar los videojuegos a las tiendas
        //  vaciar el Drive de juegos terminados
    }

    private void resetDaysToDelivery() {
        // Lógica para reiniciar el contador de días para la entrega
        //  establecer un nuevo plazo basado en algún criterio
    }

    private void performAdministrativeTasks() {
        // Lógica para realizar tareas administrativas
    }
    
    public void checkOnProjectManager() { // No necesitas un argumento aquí
        if (pm.watchStreams()) {
            pm.addFine();
            pm.deductSalary(50);
            System.out.println("El PM fue descubierto viendo streams. Se le ha descontado $50 de su sueldo.");
        } else {
            System.out.println("El PM está trabajando correctamente.");
        }
    }
}

    // ... (otros métodos getter y setter según sea necesario)



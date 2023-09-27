/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class ProjectManager {
    private String nombre;
    private double sueldoPorHora;
    private int horasTrabajadas;
    private int diasRestantesParaEntrega;
    private boolean viendoStreams;
    private int faltas;

    public ProjectManager(String nombre) {
        this.nombre = nombre;
        this.sueldoPorHora = 20; // Según el enunciado, cobra $20 la hora
        this.horasTrabajadas = 0;
        this.diasRestantesParaEntrega = 30; // Puede ser un valor inicial o definido según el proyecto
        this.viendoStreams = false;
        this.faltas = 0;
    }

    // Método para trabajar y actualizar el contador de días
    public void trabajar() {
        horasTrabajadas += 24; // Suponiendo una jornada laboral de 24 horas
        diasRestantesParaEntrega--;
    }

    // Método para ver streams
    public void verStreams() {
        viendoStreams = true;
    }

    // Método para registrar una falta
    public void recibirFalta() {
        faltas++;
    }

    // Método para calcular sueldo
    public double calcularSueldo() {
        return sueldoPorHora * horasTrabajadas - (faltas * sueldoPorHora * 8); // Se descuenta el sueldo por las faltas
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public double getSueldoPorHora() {
        return sueldoPorHora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public int getDiasRestantesParaEntrega() {
        return diasRestantesParaEntrega;
    }

    public boolean isViendoStreams() {
        return viendoStreams;
    }

    public int getFaltas() {
        return faltas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setViendoStreams(boolean viendoStreams) {
        this.viendoStreams = viendoStreams;
    }
}

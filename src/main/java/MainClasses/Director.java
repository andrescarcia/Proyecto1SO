/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class Director {
    private String nombre;
    private double sueldoPorHora;
    private int horasTrabajadas;
    private int diasRestantesParaEntregaTiendas;

    public Director(String nombre) {
        this.nombre = nombre;
        this.sueldoPorHora = 30; // Según el enunciado, cobra $30 la hora
        this.horasTrabajadas = 0;
        this.diasRestantesParaEntregaTiendas = 60; // Puede ser un valor inicial o definido según el proyecto
    }

    // Método para trabajar y actualizar el contador de días
    public void trabajar() {
        horasTrabajadas += 8; // Suponiendo una jornada laboral de 8 horas
        diasRestantesParaEntregaTiendas--;
    }

    // Método para calcular sueldo
    public double calcularSueldo() {
        return sueldoPorHora * horasTrabajadas;
    }

    // Getters
    public String getNombre() {
        return nombre;
    }

    public double getSueldoPorHora() {
        return sueldoPorHora;
    }

    public int getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public int getDiasRestantesParaEntregaTiendas() {
        return diasRestantesParaEntregaTiendas;
    }

    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSueldoPorHora(double sueldoPorHora) {
        this.sueldoPorHora = sueldoPorHora;
    }

    public void setHorasTrabajadas(int horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public void setDiasRestantesParaEntregaTiendas(int diasRestantesParaEntregaTiendas) {
        this.diasRestantesParaEntregaTiendas = diasRestantesParaEntregaTiendas;
    }
}
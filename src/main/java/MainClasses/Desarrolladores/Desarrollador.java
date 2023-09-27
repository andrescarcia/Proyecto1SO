/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses.Desarrolladores;

import OldClasses.ElementoOLD;
import MainClasses.Lista;

/**
 *
 * @author andre
 */
public abstract class Desarrollador {
    protected String nombre;
    protected double sueldoPorHora;
    protected int horasTrabajadas;
    protected int capacidadDrive;
    protected int elementosProducidos;
    protected int tiempoProduccion;

    public Desarrollador(String nombre, double sueldoPorHora, int capacidadDrive, int tiempoProduccion) {
        this.nombre = nombre;
        this.sueldoPorHora = sueldoPorHora;
        this.horasTrabajadas = 0;
        this.capacidadDrive = capacidadDrive;
        this.elementosProducidos = 0;
        this.tiempoProduccion = tiempoProduccion;
    }

    public abstract void trabajar();

    public double cobrar() {
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

    public int getCapacidadDrive() {
        return capacidadDrive;
    }

    public int getElementosProducidos() {
        return elementosProducidos;
    }

    public int getTiempoProduccion() {
        return tiempoProduccion;
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

    public void setCapacidadDrive(int capacidadDrive) {
        this.capacidadDrive = capacidadDrive;
    }

    public void setElementosProducidos(int elementosProducidos) {
        this.elementosProducidos = elementosProducidos;
    }

    public void setTiempoProduccion(int tiempoProduccion) {
        this.tiempoProduccion = tiempoProduccion;
    }
}
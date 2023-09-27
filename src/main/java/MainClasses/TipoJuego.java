/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import MainClasses.Desarrolladores.Desarrollador;

/**
 *
 * @author andre
 */
public class TipoJuego {
    private String nombreTipo;
    private int guiones;
    private int niveles;
    private int setsSprites;
    private int sistemasJuego;
    private double ganancia;

    public TipoJuego(String nombreTipo, int guiones, int niveles, int setsSprites, int sistemasJuego, double ganancia) {
        this.nombreTipo = nombreTipo;
        this.guiones = guiones;
        this.niveles = niveles;
        this.setsSprites = setsSprites;
        this.sistemasJuego = sistemasJuego;
        this.ganancia = ganancia;
    }
    
    public int calcularTiempoTotalProduccion(Lista<Desarrollador> equipoDesarrollo) {
        int tiempoTotal = 0;
        for (int i = 0; i < equipoDesarrollo.tamano(); i++) {
            tiempoTotal += equipoDesarrollo.obtener(i).getTiempoProduccion();
        }
        return tiempoTotal;
    }

 // Getters
    public String getNombreTipo() {
        return nombreTipo;
    }

    public int getGuiones() {
        return guiones;
    }

    public int getNiveles() {
        return niveles;
    }

    public int getSetsSprites() {
        return setsSprites;
    }

    public int getSistemasJuego() {
        return sistemasJuego;
    }

    public double getGanancia() {
        return ganancia;
    }

    // Setters
    public void setNombreTipo(String nombreTipo) {
        this.nombreTipo = nombreTipo;
    }

    public void setGuiones(int guiones) {
        this.guiones = guiones;
    }

    public void setNiveles(int niveles) {
        this.niveles = niveles;
    }

    public void setSetsSprites(int setsSprites) {
        this.setsSprites = setsSprites;
    }

    public void setSistemasJuego(int sistemasJuego) {
        this.sistemasJuego = sistemasJuego;
    }

    public void setGanancia(double ganancia) {
        this.ganancia = ganancia;
    }
}
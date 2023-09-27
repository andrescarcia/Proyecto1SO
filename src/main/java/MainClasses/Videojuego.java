/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class Videojuego {
    private String nombreJuego;
    private TipoJuego tipo;
    private boolean tieneDLC;
    private int copiasVendidas;

    public Videojuego(String nombreJuego, TipoJuego tipo) {
        this.nombreJuego = nombreJuego;
        this.tipo = tipo;
        this.tieneDLC = false; // Por defecto no tiene DLC
        this.copiasVendidas = 0; // Inicialmente no se han vendido copias
    }


    // Método para agregar DLC
    public void agregarDLC() {
        this.tieneDLC = true;
    }

    // Método para calcular ganancias
    public double calcularGanancias() {
        // Por simplicidad, supongamos que cada copia vendida genera la ganancia base del tipo de juego
        return tipo.getGanancia() * copiasVendidas;
    }

    // Método para vender copias (puede ser más complejo dependiendo de la lógica de negocio)
    public void venderCopias(int cantidad) {
        this.copiasVendidas += cantidad;
    }

    // Getters
    public String getNombreJuego() {
        return nombreJuego;
    }

    public TipoJuego getTipo() {
        return tipo;
    }

    public boolean isTieneDLC() {
        return tieneDLC;
    }

    public int getCopiasVendidas() {
        return copiasVendidas;
    }

    // Setters
    public void setNombreJuego(String nombreJuego) {
        this.nombreJuego = nombreJuego;
    }

    public void setTipo(TipoJuego tipo) {
        this.tipo = tipo;
    }

    public void setTieneDLC(boolean tieneDLC) {
        this.tieneDLC = tieneDLC;
    }

    public void setCopiasVendidas(int copiasVendidas) {
        this.copiasVendidas = copiasVendidas;
    }
}


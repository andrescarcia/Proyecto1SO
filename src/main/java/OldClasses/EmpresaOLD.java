/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OldClasses;

import MainClasses.Lista;

/**
 *
 * @author andre
 */
public class EmpresaOLD {
    private String nombre;
    private double gananciaPorJuegoEstandar;
    private double gananciaPorJuegoConDLC;
    private int politicaDLC;
    private double gananciaTotal;
    private Lista<JuegoOLD> listaDeJuegos;

    // Constructor
    public EmpresaOLD(String nombre) {
        this.nombre = nombre;
        this.gananciaPorJuegoEstandar = 0;
        this.gananciaPorJuegoConDLC = 0;
        this.politicaDLC = 0;
        this.gananciaTotal = 0;
        this.listaDeJuegos = new Lista<>();
    }

    // Métodos

    // Calcular ganancias totales
    public void calcularGanancias() {
        for (int i = 0; i < listaDeJuegos.tamano(); i++) {
            JuegoOLD juego = listaDeJuegos.obtener(i);
            if (juego.getDlcs().tamano() == 0) {
                this.gananciaTotal += this.gananciaPorJuegoEstandar;
            } else {
                this.gananciaTotal += this.gananciaPorJuegoConDLC;
            }
        }
    }

    // Crear un juego estándar
    public void crearJuego() {
        JuegoOLD nuevoJuego = new JuegoOLD();
        this.listaDeJuegos.agregar(nuevoJuego);
    }

    // Crear un juego con DLC
    public void crearJuegoConDLC(String dlc) {
        JuegoOLD nuevoJuego = new JuegoOLD();
        nuevoJuego.agregarDLC(dlc);
        this.listaDeJuegos.agregar(nuevoJuego);
    }

  public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGananciaPorJuegoEstandar() {
        return gananciaPorJuegoEstandar;
    }

    public void setGananciaPorJuegoEstandar(double gananciaPorJuegoEstandar) {
        this.gananciaPorJuegoEstandar = gananciaPorJuegoEstandar;
    }

    public double getGananciaPorJuegoConDLC() {
        return gananciaPorJuegoConDLC;
    }

    public void setGananciaPorJuegoConDLC(double gananciaPorJuegoConDLC) {
        this.gananciaPorJuegoConDLC = gananciaPorJuegoConDLC;
    }

    public int getPoliticaDLC() {
        return politicaDLC;
    }

    public void setPoliticaDLC(int politicaDLC) {
        this.politicaDLC = politicaDLC;
    }

    public double getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(double gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public Lista<JuegoOLD> getListaDeJuegos() {
        return listaDeJuegos;
    }

    public void setListaDeJuegos(Lista<JuegoOLD> listaDeJuegos) {
        this.listaDeJuegos = listaDeJuegos;
    }

}

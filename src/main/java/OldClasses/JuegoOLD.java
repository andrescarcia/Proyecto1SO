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
public class JuegoOLD {
    private Lista<String> elementosNecesarios;
    private String estado;
    private Lista<String> dlcs;

    // Constructor
    public JuegoOLD() {
        this.elementosNecesarios = new Lista<>();
        this.estado = "En desarrollo";
        this.dlcs = new Lista<>();
    }

    // Métodos

    // Agregar un elemento necesario para el juego
    public void agregarElemento(String elemento) {
        this.elementosNecesarios.agregar(elemento);
    }

    // Ensamblar el juego
    public void ensamblar() {
        if (this.elementosNecesarios.tamano() == 0) {
            this.estado = "Ensamblado";
        } else {
            System.out.println("Aún faltan elementos para ensamblar el juego.");
        }
    }

    // Agregar un DLC al juego
    public void agregarDLC(String dlc) {
        this.dlcs.agregar(dlc);
    }

    // Getters y Setters

    public Lista<String> getElementosNecesarios() {
        return elementosNecesarios;
    }

    public void setElementosNecesarios(Lista<String> elementosNecesarios) {
        this.elementosNecesarios = elementosNecesarios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Lista<String> getDlcs() {
        return dlcs;
    }

    public void setDlcs(Lista<String> dlcs) {
        this.dlcs = dlcs;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class Drive<T> {
    private int capacidadMaxima;
    private int capacidadActual;
    private Lista<T> elementos;

    public Drive(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = 0;
        this.elementos = new Lista<>();
    }

    public void agregar(T elemento) {
        if (capacidadActual < capacidadMaxima) {
            elementos.agregar(elemento);
            capacidadActual++;
        } else {
            System.out.println("Drive lleno. No se puede agregar más elementos.");
        }
    }

    public void usar(int cantidad) {
        if (capacidadActual >= cantidad) {
            for (int i = 0; i < cantidad; i++) {
                T elementoAEliminar = elementos.obtener(0); // Obtener el primer elemento
                elementos.eliminar(elementoAEliminar); // Eliminar el elemento específico
            }
            capacidadActual -= cantidad;
        } else {
            System.out.println("No hay suficientes elementos en el Drive.");
        }
    }


    public int getCapacidadActual() {
        return capacidadActual;
    }
}


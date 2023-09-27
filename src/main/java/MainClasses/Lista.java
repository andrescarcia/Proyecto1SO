/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 * @param <T>
 */
public class Lista<T> {
    private Nodo<T> cabeza;
    private int tamano;

    private static class Nodo<T> {
        T dato;
        Nodo<T> siguiente;

        Nodo(T dato) {
            this.dato = dato;
            this.siguiente = null;
        }
    }

    public Lista() {
        cabeza = null;
        tamano = 0;
    }

    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevoNodo;
        }
        tamano++;
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= tamano) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.siguiente;
        }
        return actual.dato;
    }

    public boolean estaVacia() {
        return cabeza == null;
    }

    public void limpiar() {
        cabeza = null;
        tamano = 0;
    }

    // Método tamano() añadido
    public int tamano() {
        return tamano;
    }
    public void eliminar(T dato) {
    if (cabeza == null) {
        return; // La lista está vacía
    }
    if (cabeza.dato.equals(dato)) {
        cabeza = cabeza.siguiente;
        tamano--;
        return;
    }
    Nodo<T> actual = cabeza;
    while (actual.siguiente != null && !actual.siguiente.dato.equals(dato)) {
        actual = actual.siguiente;
    }
    if (actual.siguiente != null) {
        actual.siguiente = actual.siguiente.siguiente;
        tamano--;
    }
}

}

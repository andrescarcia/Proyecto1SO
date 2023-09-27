/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class Integrador {
    private EstudioDesarrollo estudio;
    private final int TIEMPO_ENSAMBLAJE = 2; // 2 días para ensamblar un videojuego
    private int diasTrabajados; // Contador de días trabajados en el ensamblaje actual
    private boolean estaTrabajando; // Indica si el integrador está trabajando en un videojuego

    public Integrador() {
        this.diasTrabajados = 0;
        this.estaTrabajando = false;
        this.estudio = estudio;

    }

    // Método que simula el trabajo diario del integrador
    public void trabajar() {
        if (estaTrabajando) {
            diasTrabajados++;
            if (diasTrabajados == TIEMPO_ENSAMBLAJE) {
                // Finalizar ensamblaje y resetear contadores
                ensamblarVideojuego();
                diasTrabajados = 0;
                estaTrabajando = false;
            }
        }
    }

    // Método que inicia el ensamblaje de un videojuego
    public void iniciarEnsamblaje() {
        estaTrabajando = true;
    }

    // Método que ensambla el videojuego (aquí se puede agregar lógica adicional si es necesario)
    private void ensamblarVideojuego() {
         // Lógica para tomar las partes de los Drives de los desarrolladores
        // Esto dependerá de las especificaciones de cada estudio (Capcom o Square Enix)

        // Ejemplo (esto es solo un esquema, la lógica real dependerá de las especificaciones exactas):
        if (estudio.getNombre().equals("Capcom")) {
            // Tomar 1 guión, 2 niveles, 6 sets de sprites, 5 sistemas de juego de los Drives
            // Si todas las partes están disponibles, ensamblar el videojuego
            // Agregar el videojuego ensamblado a la lista de videojuegos del estudio
        } else if (estudio.getNombre().equals("Square Enix")) {
            // Tomar 1 guión, 1 nivel, 2 sets de sprites, 4 sistemas de juego de los Drives
            // Si todas las partes están disponibles, ensamblar el videojuego
            // Agregar el videojuego ensamblado a la lista de videojuegos del estudio
        }    }

    // Getters y Setters
    public boolean estaTrabajando() {
        return estaTrabajando;
    }
}

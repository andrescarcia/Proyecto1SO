/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OldClasses;

/**
 *
 * @author andre
 */
public class ElementoOLD {
    private String tipo; // Tipo de elemento (guion, nivel, sprite, sistema, DLC, etc.)
    private String contenido; // Contenido del elemento, por ejemplo, el texto del guión

    // Constructor
    public ElementoOLD(String tipo, String contenido) {
        this.tipo = tipo;
        this.contenido = contenido;
    }

    // Getter y Setter para tipo
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Getter y Setter para contenido
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Elemento{" +
               "tipo='" + tipo + '\'' +
               ", contenido='" + contenido + '\'' +
               '}';
    }
}

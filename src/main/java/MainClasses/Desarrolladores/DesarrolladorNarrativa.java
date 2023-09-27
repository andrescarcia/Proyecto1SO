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
class DesarrolladorNarrativa extends Desarrollador {
    private int guionesGenerados;

    public DesarrolladorNarrativa(String nombre) {
        super(nombre, 10, 25, 48); // $10 por hora, capacidad de 25 guiones, 1 guión cada 2 días (48 horas)
        this.guionesGenerados = 0;
    }

    @Override
    public void trabajar() {
        horasTrabajadas += tiempoProduccion;
        guionesGenerados++;
        elementosProducidos++;
    }
}
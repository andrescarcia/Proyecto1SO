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

class ProgramadorLogica extends Desarrollador {
    private int sistemasGenerados;

    public ProgramadorLogica(String nombre) {
        super(nombre, 8, 35, 24); // $8 por hora, capacidad de 35 sistemas, 3 sistemas cada día (24 horas)
        this.sistemasGenerados = 0;
    }

    @Override
    public void trabajar() {
        horasTrabajadas += tiempoProduccion;
        sistemasGenerados += 3; // Genera 3 sistemas de juego cada día
        elementosProducidos += 3;
    }
}
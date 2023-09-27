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

class DesarrolladorDLC extends Desarrollador {
    private int dlcsGenerados;

    public DesarrolladorDLC(String nombre) {
        super(nombre, 17, 10, 72); // $17 por hora, capacidad de 10 DLCs, 1 DLC cada 3 días (72 horas)
        this.dlcsGenerados = 0;
    }

    @Override
    public void trabajar() {
        horasTrabajadas += tiempoProduccion;
        dlcsGenerados++;
        elementosProducidos++;
    }
}
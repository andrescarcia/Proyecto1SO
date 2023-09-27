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

class DiseñadorNiveles extends Desarrollador {
    private int nivelesGenerados;

    public DiseñadorNiveles(String nombre) {
        super(nombre, 13, 20, 48); // $13 por hora, capacidad de 20 niveles, 1 nivel cada 2 días (48 horas)
        this.nivelesGenerados = 0;
    }

    @Override
    public void trabajar() {
        horasTrabajadas += tiempoProduccion;
        nivelesGenerados++;
        elementosProducidos++;
    }
}
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
class ArtistaSprites extends Desarrollador {
    private int spritesGenerados;

    public ArtistaSprites(String nombre) {
        super(nombre, 20, 55, 24); // $20 por hora, capacidad de 55 sprite sets, 3 sprite sets cada día (24 horas)
        this.spritesGenerados = 0;
    }

    @Override
    public void trabajar() {
        horasTrabajadas += tiempoProduccion;
        spritesGenerados += 3; // Genera 3 sprites set cada día
        elementosProducidos += 3;
    }
}


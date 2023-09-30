/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class Drive {
    private final SuperDrive[] driveSections;

    public Drive(SuperDrive[] currentParameters) {
        this.driveSections = currentParameters;
    }

    public SuperDrive[] getDriveSections() {
        return driveSections;
    }

    // Otros métodos para gestionar el Drive...
}

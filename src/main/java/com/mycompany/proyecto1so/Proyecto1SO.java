/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto1so;

import MainClasses.Drive;
import MainClasses.GUIHelpers;
import MainClasses.Integrator;

/**
 *
 * @author andre
 */
public class Proyecto1SO {

    public static void main(String[] args) {
        System.out.println("Hello Isaac!!");
        GUIHelpers.openMain();
        Drive drive = new Drive();
        // Simulate adding components to the Drive
        drive.setScripts(5);
        drive.setLevels(5);
        drive.setSprites(5);
        drive.setLogics(5);
        drive.setDlcs(5);

        Integrator integrator = new Integrator(drive);
        integrator.start();

        // Allow the Integrator to run for a while
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Stop the Integrator thread
        integrator.stopWorking();
         
    }
}

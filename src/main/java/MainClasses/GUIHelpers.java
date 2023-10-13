/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import GUI_Classes.Main;

/**
 *
 * @author andre
 */
public class GUIHelpers {
    private static final Main mainPage = new Main();
    public static Main getMain() {
        return mainPage;
    }
    public static void openMain() {
        getMain().setVisible(true);
    }
    
}

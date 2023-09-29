/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class DeveloperType {
    static public String guion = "guion";
    static public String nivel = "nivel";
    static public String spriteSet = "spriteSet";
    static public String sistemaJuego = "sistemaJuego";
    static public String dlc = "dlc";
    static public String ensamblador = "ensamblador";
    static public String sinTipo = "sinTipo";

    public static String getPartePorOrden(int posicion) {
        switch (posicion) {
            case 0:
                return DeveloperType.guion;
            case 1:
                return DeveloperType.nivel;
            case 2:
                return DeveloperType.spriteSet;
            case 3:
                return DeveloperType.sistemaJuego;
            case 4:
                return DeveloperType.dlc;
            case 5:
                return DeveloperType.ensamblador;
            default:
                return DeveloperType.sinTipo;
        }
    }

    public static String getEtiquetasProductor(int posicion) {
        switch (posicion) {
            case 0:
                return "Guión";
            case 1:
                return "Nivel";
            case 2:
                return "Sprite Set";
            case 3:
                return "Sistema de Juego";
            case 4:
                return "DLC";
            case 5:
                return "Ensamblador";
            default:
                return DeveloperType.sinTipo;
        }
    }
}

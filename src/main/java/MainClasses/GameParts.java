/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class GameParts {
    public static final String narrative = "narrative";
    public static final String levelDesign = "levelDesign";
    public static final String spriteArt = "spriteArt";
    public static final String gameLogic = "gameLogic";
    public static final String dlc = "dlc";
    public static final String integrator = "integrator";
    public static final String noType = "noType";
    public static final String game = "game";

    /**
     * Returns the game component based on its order.
     * @param position The order of the game component.
     * @return The game component as a string.
     */
    public static String getGameComponentByOrder(int position) {
        switch (position) {
            case 0:
                return GameParts.narrative;
            case 1:
                return GameParts.levelDesign;
            case 2:
                return GameParts.spriteArt;
            case 3:
                return GameParts.gameLogic;
            case 4:
                return GameParts.dlc;
            case 5:
                return GameParts.integrator;
            case 6:
                return GameParts.game;
            default:
                return GameParts.noType;
        }
    }

    /**
     * Returns the label for the game component for chart representation.
     * @param position The order of the game component.
     * @return The label of the game component.
     */
    public static String getChartLabel(int position) {
        switch (position) {
            case 0:
                return "Narrativa";
            case 1:
                return "Diseño de Nivel";
            case 2:
                return "Arte de Sprite";
            case 3:
                return "Lógica del Juego";
            case 4:
                return "DLC";
            case 5:
                return "Integrador";
            case 6:
                return "Videojuego";
            default:
                return GameParts.noType;
        }
    }
}

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
    private int scripts;
    private int levels;
    private int sprites;
    private int logics;
    private int dlcs;
    private int daysRemaining;
    private int vanillaGames;
    private int dlcGames;
    private int vanillaCounter;

    public Drive() {
        this.scripts = 0;
        this.levels = 0;
        this.sprites = 0;
        this.logics = 0;
        this.dlcs = 0;
        this.daysRemaining = 5;
        this.vanillaGames = 0;
        this.dlcGames = 0;
        this.vanillaCounter = 0;
    }
    
    
    

    // Otros métodos para gestionar el Drive...

    public int getScripts() {
        return scripts;
    }

    public void setScripts(int scripts) {
        this.scripts = scripts;
    }

    public int getLevels() {
        return levels;
    }

    public void setLevels(int levels) {
        this.levels = levels;
    }

    public int getSprites() {
        return sprites;
    }

    public void setSprites(int sprites) {
        this.sprites = sprites;
    }

    public int getLogics() {
        return logics;
    }

    public void setLogics(int logics) {
        this.logics = logics;
    }

    public int getDlcs() {
        return dlcs;
    }

    public void setDlcs(int dlcs) {
        this.dlcs = dlcs;
    }

    public int getDaysRemaining() {
        return daysRemaining;
    }

    public void setDaysRemaining(int daysRemaining) {
        this.daysRemaining = daysRemaining;
    }

    public int getVanillaGames() {
        return vanillaGames;
    }

    public void setVanillaGames(int vanillaGames) {
        this.vanillaGames = vanillaGames;
    }

    public int getDlcGames() {
        return dlcGames;
    }

    public void setDlcGames(int dlcGames) {
        this.dlcGames = dlcGames;
    }

    public int getVanillaCounter() {
        return vanillaCounter;
    }

    public void setVanillaCounter(int vanillaCounter) {
        this.vanillaCounter = vanillaCounter;
    }
    
    /**
     * 
     *funcion addToDrive
     * 
     * Ejecutada por trabajadores cuando su acumulado sea mayor o igual a 1, segun sea su tipo ejecuta condicionales
     * para verificar si el drive esta lleno.
     * 
     * Si no esta lleno:
     * Verifica si la cantidad a añadir excede del maximo, si excede, solo añade una parte para no exceder del maximo
     * Si no excede, añade la cantidad normalmente
     * 
     * Si esta lleno:
     * No hace nada :)
     * 
     * @param amount
     * @param devType 
     */
    
    public void addToDrive(int amount, String devType){
        
        switch (devType){
        
            case "scripts":
                if (this.scripts < 25){
                    if ((this.scripts + amount) > 25){
                        this.scripts += (25 - this.scripts);
                
                    }else{
                        this.scripts += amount;
                    }
                }
                break;
                
            case "level":
                if (this.levels < 20){
                    if ((this.levels + amount) > 20){
                        this.levels += (20 - this.levels);
                
                    }else{
                        this.levels += amount;
                    }
                }
                break;
                
            case "sprite":
                if (this.sprites < 55){
                    if ((this.sprites + amount) > 55){
                        this.sprites += (55 - this.sprites);
                
                    }else{
                        this.sprites += amount;
                    }
                }
                break;
                
            case "logic":
                if (this.logics < 35){
                    if ((this.logics + amount) > 35){
                        this.logics += (35 - this.logics);
                
                    }else{
                        this.logics += amount;
                    }
                }
                break;
            
            case "dlc":
                if (this.dlcs < 10){
                    if ((this.dlcs + amount) > 10){
                        this.dlcs += (10 - this.dlcs);
                
                    }else{
                        this.dlcs += amount;
                    }
                }
                break;
                
        }
        
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.concurrent.Semaphore;
import javax.swing.JLabel;

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
    private int deadLine;
    private int vanillaGames;
    private int dlcGames;
    private int vanillaCounter;
    private JLabel[] labels;
    private String company;
    private double salary;


    public Drive(String comp) {
        this.scripts = 0;
        this.levels = 0;
        this.sprites = 0;
        this.logics = 0;
        this.dlcs = 0;
        this.daysRemaining = 5;
        this.vanillaGames = 0;
        this.dlcGames = 0;
        this.vanillaCounter = 0;
        this.company = comp;
        this.salary = 0;
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
        this.labels[5].setText(Integer.toString(this.daysRemaining));
            
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
    
    public void addToDrive(int amount, String devType)throws InterruptedException {
        switch (devType){
        
            case "Narrative":
                if (this.scripts < 25){
                    if ((this.scripts + amount) > 25){
                        this.scripts += (25 - this.scripts);
                
                    }else{
                        this.scripts += amount;
                    }
                    
                    this.labels[0].setText(Integer.toString(this.scripts));
                    
                }
                break;
                
            case "LevelDesign":
                if (this.levels < 20){
                    if ((this.levels + amount) > 20){
                        this.levels += (20 - this.levels);
                
                    }else{
                        this.levels += amount;
                    }
                    
                    this.labels[1].setText(Integer.toString(this.levels));
                    
                }
                break;
                
            case "SpriteArt":
                if (this.sprites < 55){
                    if ((this.sprites + amount) > 55){
                        this.sprites += (55 - this.sprites);
                
                    }else{
                        this.sprites += amount;
                    }
                    
                    this.labels[2].setText(Integer.toString(this.sprites));
                    
                }
                break;
                
            case "GameLogic":
                if (this.logics < 35){
                    if ((this.logics + amount) > 35){
                        this.logics += (35 - this.logics);
                
                    }else{
                        this.logics += amount;
                    }
                    
                    this.labels[3].setText(Integer.toString(this.logics));
                    
                }
                
                break;
            
            case "DLC":
                if (this.dlcs < 10){
                    if ((this.dlcs + amount) > 10){
                        this.dlcs += (10 - this.dlcs);
                
                    }else{
                        this.dlcs += amount;
                    }
                    
                    this.labels[4].setText(Integer.toString(this.dlcs));
                    
                }
                break;
        }
    }
    public boolean canAssembleGame() {
        if(this.company.equals("Capcom")){
            if(this.vanillaCounter >= 3){
                return this.scripts >= 1 && this.levels >= 2 && this.sprites >= 6 && this.logics >= 5 && this.dlcs >= 1;
                
            }else{
                return this.scripts >= 1 && this.levels >= 2 && this.sprites >= 6 && this.logics >= 5;
            }
            
        }else{
            if(this.vanillaCounter >= 2){
                return this.scripts >= 1 && this.levels >= 1 && this.sprites >= 2 && this.logics >= 4;
                
            }else{
                return this.scripts >= 1 && this.levels >= 2 && this.sprites >= 6 && this.logics >= 5 && this.dlcs>= 3;
            }
        }
    }
     
    public void assembleGame() {
        if(this.company.equals("Capcom")){
            this.scripts -= 1;
            this.levels -= 2;
            this.sprites -= 6;
            this.logics -= 5;
            
            if(this.vanillaCounter >= 3){
                this.dlcs -= 1;
                this.vanillaCounter = 0;
                this.dlcGames += 1;
                
            }else{
                this.vanillaCounter += 1;
                this.vanillaGames += 1;
            }
                
        }else{
            this.scripts -= 1;
            this.levels -= 1;
            this.sprites -= 2;
            this.logics -= 4;
            
            if(this.vanillaCounter >= 2){
                this.dlcs -= 3;
                this.vanillaCounter = 0;
                this.dlcGames += 1;
                
            }else{
                this.vanillaCounter += 1;
                this.vanillaGames += 1;
            }
        
        }
        
        this.labels[0].setText(Integer.toString(this.scripts));
        this.labels[1].setText(Integer.toString(this.levels));
        this.labels[2].setText(Integer.toString(this.sprites));
        this.labels[3].setText(Integer.toString(this.logics));
        this.labels[4].setText(Integer.toString(this.dlcs));
        this.labels[6].setText(Integer.toString(this.vanillaGames));
        this.labels[7].setText(Integer.toString(this.dlcGames));
        
        
        
        
        
    }
    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public int getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(int deadLine) {
        this.deadLine = deadLine;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
    
    
    
}

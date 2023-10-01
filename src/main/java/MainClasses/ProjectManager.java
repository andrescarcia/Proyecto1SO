/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sebastián
 */
public class ProjectManager extends Thread{
    private int salary;
    private int dayDuration;
    private int hourDuration;
    private int minDuration;
    private Drive drive;
    private Semaphore mutex;
    private String currentState;

    public ProjectManager(int salary, int dayDuration, Drive drive, Semaphore m) {
        this.salary = salary;
        this.dayDuration = dayDuration;
        
        this.hourDuration = this.dayDuration/24;
        if(this.hourDuration < 1){
            this.hourDuration = 1;
        }
        
        this.minDuration = this.hourDuration/60;
        if(this.minDuration < 1){
            this.minDuration = 1;
        }
        
        this.minDuration = minDuration;
        
        this.drive = drive;
        this.mutex = m;
        this.currentState = "Trabajando";
    }
    
    @Override
    public void run(){
        while(true){
            try{
                susTime();                
                if (((this.hourDuration/2)*32) + (this.hourDuration*8) < this.dayDuration){
                    sleep((this.hourDuration*8) + (this.dayDuration - (((this.hourDuration/2)*32) + (this.hourDuration*8))));
                    System.out.println("Bajar un dia");
                }else{
                    sleep(this.hourDuration*8);
                    System.out.println("Bajar un dia");
                }
                
                
                
                
            }catch (InterruptedException ex){
                System.out.println(ex);
                        }
        }
    }
    
    public void susTime() {
        for (int i = 1; i <= 16; i++){
            try {
                sleep(this.hourDuration/2);
                this.currentState = watchSus();
                
                sleep(this.hourDuration/2);   
                this.currentState = "Trabajando";
                
            } catch (InterruptedException ex) {
                Logger.getLogger(ProjectManager.class.getName()).log(Level.SEVERE, null, ex);
            }
                    
                }
}
    
    /*Esta funcion es solo por entretenimiento*/
    public String watchSus(){
        String stream = "";
        Random random = new Random();
        int randNumb = random.nextInt(7 - 1) + 1;
        switch (randNumb){
            
            case 1:
                stream = "EVO Among Us Arena Finales";
                break;
                
            case 2:
                stream = "League of Imposters Semifinales";
                break;
                
            case 3:
                stream = "Among Us money match";
                break;
                
            case 4:
                stream = "[Among Us Arena] Sus! | Guau Gooba ch";
                break;
                
            case 5:
                stream = "MOMENTOS MAS LOCOS DE TRIPULANTE !Giveaway";
                break;    
                
            case 6:
                stream = "Crewmate Legends partidas ranked #593";
        }
        
        return stream;
        
    }

    public String getCurrentState() {
        return currentState;
    }

    public void setCurrentState(String currentState) {
        this.currentState = currentState;
    }
    
    
    
}

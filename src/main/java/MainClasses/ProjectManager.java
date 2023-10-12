/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

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
    private JLabel label;


    public ProjectManager(int day, int hour, int min ,Drive drive, Semaphore m, JLabel label) {
        this.salary = 20;
        this.dayDuration = day;
        
        this.hourDuration = hour;
        
        this.minDuration = min;
        
        this.drive = drive;
        this.mutex = m;
        this.currentState = "Trabajando";
        this.label = label;
    }
    
    @Override
    public void run(){
        while(true){
            try{
                susTime();                
                if (((this.hourDuration/2)*32) + (this.hourDuration*8) < this.dayDuration){
                    sleep((this.hourDuration*8) + (this.dayDuration - (((this.hourDuration/2)*32) + (this.hourDuration*8))));
                    this.mutex.acquire(1);
                    countDay();
                    this.mutex.release();
                }else{
                    sleep(this.hourDuration*8);
                    this.mutex.acquire(1);
                    countDay();
                    this.mutex.release();
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
                this.label.setText(this.currentState);
                
                sleep(this.hourDuration/2);   
                this.currentState = "Trabajando";
                this.label.setText(this.currentState);
                
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

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
    
    public void countDay(){
        this.drive.setDaysRemaining(this.drive.getDaysRemaining() - 1);
        this.drive.addSalary(salary);
        daysPassed();
    }
    
    public void daysPassed(){
        this.drive.addDayspassed();
    }
    
}

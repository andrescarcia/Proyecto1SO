/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

/**
 *
 * @author Sebastián
 */
public class Director extends Thread {
    private Drive drive;
    private Semaphore mutex;
    private ProjectManager pm;
    private boolean paused;
    private int minuteDuration;
    
    public Director(Drive drive, Semaphore m, ProjectManager proj, int min){
        this.drive = drive;
        this.mutex = m;
        this.pm = proj;
        this.minuteDuration = min;
        this.paused = false;
    }
    
    @Override
    public void run(){
        while(true){
            if(!this.paused){
                if(!"Trabajando".equals(this.pm.getCurrentState())){
                    System.out.println("PM flojeando");
                } else {
                    System.out.println("PM Trabajando");
                }
                try {
                    Thread.sleep(minuteDuration);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public int randHour(){
        Random random = new Random();
        return random.nextInt(25 - 1) + 1;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }
 
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;
import javax.swing.JLabel;

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
    private boolean wasLazy;
    private int pmPenalties;
    private JLabel label;
    private JLabel faultLabel;
    
    public Director(Drive drive, Semaphore m, ProjectManager proj, int min, JLabel label, JLabel fault){
        this.drive = drive;
        this.mutex = m;
        this.pm = proj;
        this.minuteDuration = min;
        this.paused = false;
        this.wasLazy = false;
        this.pmPenalties = 0;
        this.label = label;
        this.faultLabel = fault;
    }
    
    
    @Override
    public void run(){
        try {
            sleep(10);
            while(true){
                if(!this.paused){
                    
                    if(!"Trabajando".equals(this.pm.getCurrentState()) && !this.wasLazy){
                        this.mutex.acquire(1);
                        faultPM();
                        this.mutex.release();
                    }
                    
                sleep(5);
                    
                    
                }else{
                    try {
                        sleep(this.minuteDuration);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
            /**    public void work(){
             * this.acc += this.productionPerDay;
             * if (this.acc >= 1){
             * try {
             * //drive
             * //Seccion critica
             * this.mutex.acquire(1);          //wait
             * this.drive.addToDrive(type, 1);
             * this.acc = 0;
             * this.mutex.release();                 //signal
             * } catch (InterruptedException ex) {
             * Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
             * }
             * System.out.println(this.drive.levels);
             * }
             * }
             * 
             **/     } catch (InterruptedException ex) {
            Logger.getLogger(Director.class.getName()).log(Level.SEVERE, null, ex);
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

    public boolean isWasLazy() {
        return wasLazy;
    }

    public void setWasLazy(boolean wasLazy) {
        this.wasLazy = wasLazy;
    }

    public int getPmPenalties() {
        return pmPenalties;
    }

    public void setPmPenalties(int pmPenalties) {
        this.pmPenalties = pmPenalties;
    }
 
    public void faultPM(){
        this.wasLazy = true;
        this.pmPenalties += 1;
        this.label.setText(Integer.toString(this.pmPenalties));
        this.faultLabel.setText(Integer.toString(this.pmPenalties*50));
        this.drive.setSalary(this.drive.getSalary() - (50/1000));
    }

    public JLabel getFaultLabel() {
        return faultLabel;
    }

    public void setFaultLabel(JLabel faultLabel) {
        this.faultLabel = faultLabel;
    }
    
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 *
 * @author Sebastián
 */
public class DirectorWatch extends Thread{
    private int salary;
    private int dayDuration;
    private int hourDuration;
    private int minuteDuration;
    private Director dir;
    private Drive drive;
    private JLabel label;
    
    public DirectorWatch(int sal, int day, int hora, int min, Director dir, Drive drive, JLabel label){
    this.salary = sal;
    this.dayDuration = day;
    this.dir = dir;
    this.hourDuration = hora;
    this.minuteDuration = min;
    this.drive = drive;
    this.label = label;
    }
    
    public void run(){
        System.out.println(this.minuteDuration);
        while(true){
            try {
                
                if(drive.getDaysRemaining() != 0){
                
                
                    int randomHour = randHour();
                    int upperWait = this.hourDuration * randomHour;
                    int lowerWait = this.hourDuration * (24 - randomHour + 1);

                    for(int i = 0; i < 3; i++){
                        switch (i){

                            case 0:
                                this.dir.setPaused(true);
                                this.label.setText("Labores administrativas");
                                sleep(upperWait);
                                break;

                            case 1:
                                this.dir.setPaused(false);
                                this.label.setText("Revisando al PM");
                                sleep(this.minuteDuration * 25);
                                break;

                            case 2:
                                this.dir.setPaused(true);
                                this.label.setText("Labores administrativas");
                                this.dir.setWasLazy(false);
                                sleep(this.dayDuration - ((this.minuteDuration*25) + upperWait));
                        }
                    }
            
                }else{
                    this.drive.setDaysRemaining(this.drive.getDeadLine());
                }
            
            } catch (InterruptedException ex) {
                Logger.getLogger(DirectorWatch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    }
    
    public int randHour(){
        Random random = new Random();
        return random.nextInt(25 - 1) + 1;
    }
    
}

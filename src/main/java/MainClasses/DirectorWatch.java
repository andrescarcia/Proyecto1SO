/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public DirectorWatch(int sal, int day, int hora, int min, Director dir){
    this.salary = sal;
    this.dayDuration = day;
    this.dir = dir;
    this.hourDuration = hora;
    this.minuteDuration = min;
    }
    
    public void run(){
        System.out.println(this.minuteDuration);
        while(true){
            try {
                
                
                int randomHour = randHour();
                int upperWait = this.hourDuration * randomHour;
                int lowerWait = this.hourDuration * (24 - randomHour + 1);
                
                for(int i = 0; i < 2; i++){   ///no seria 3?
                    switch (i){
                
                        case 0:
                            this.dir.setPaused(true);
                            System.out.println("Director Pausado");
                            sleep(upperWait);
                            break;
                        
                        case 1:
                            this.dir.setPaused(false);
                            System.out.println("Director resumido");
                            sleep(this.minuteDuration * 25);
                            break;
                            
                        case 2:
                            this.dir.setPaused(true);
                            System.out.println("Director Pausado");
                            sleep(this.dayDuration - ((this.minuteDuration*25) + upperWait));
                    }
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

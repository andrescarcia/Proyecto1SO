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
public class DirectorWatch extends Thread{
    private int salary;
    private int dayDuration;
    private int hourDuration;
    private int minuteDuration;
    private Director dir;
    private Drive drive;
    private JLabel label;
    private Company company;
    private Semaphore mutex;
    
    public DirectorWatch(int day, int hora, int min, Director dir, JLabel label, Company comp){
    this.salary = 30;
    this.dayDuration = day;
    this.dir = dir;
    this.hourDuration = hora;
    this.minuteDuration = min;
    this.label = label;
    this.company = comp;
    this.drive = this.company.getCompanyDrive();
    this.mutex = this.company.getMutex();
    }
    
    public void run(){
        /**
         * Ejecucion del director, si no hay 0 dias restantes, revisa al pm en una hora aleatoria cambiando el booleano de
         * activacion para el thread que ejecuta las reviciones
         * al final de los 25 minutos de la revision, cambia el booleano para desactivar al thread de revision
         * 
         * Si hay 0 dias restantes, procede a vender los juegos
         */
        System.out.println(this.minuteDuration);
        while(true){
            try {
                
                if(drive.getDaysRemaining() > 0){
                
                    int randomHour = randHour();
                    int upperWait = this.hourDuration * randomHour;
                    int lowerWait = this.hourDuration * (24 - randomHour - 1);

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
                    
                    this.mutex.acquire(1);
                    this.drive.addSalary(salary);
                    this.mutex.release();
            
                }else{
                    this.mutex.acquire(1);
                    this.sellGames();
                    this.mutex.release();
                }
            
            } catch (InterruptedException ex) {
                Logger.getLogger(DirectorWatch.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    
    }
    
    /**
     * Funcion de hora aleatoria, esta si es la que se usa
     * @return 
     */
    public int randHour(){
        Random random = new Random();
        return random.nextInt(24 - 1) + 1;
    }
    
    /**
     * Funcion de venta de juegos
     * 
     * Se revisa de que compañia se trata para determinar la ganancia por juego
     * 
     * Luego se calcula la ganancia segun los juegos, se añade el salario del director para este dia
     * se carga el salario acumulado en el drive para este deadline, se calculan las utilidades.
     * 
     * Despues se reinicia el salario para este deadline, la cantidad de juegos sin dlc, los juegos con dlc
     * la cantidad de dias restantes y las faltas del pm
     */
    
    public void sellGames(){
        if(this.company.getCompanyName().equals("Capcom")){
            this.company.addIncome((this.drive.getVanillaGames() * 400) + (this.drive.getDlcGames() * 750));       
        }else{
            this.company.addIncome((this.drive.getVanillaGames() * 350) + (this.drive.getDlcGames() * 700));
        }
        
        this.drive.addSalary(this.salary);
        this.company.addSalary(this.drive.getSalary());
        this.company.setUtilities(this.company.getIncome() - this.company.getSalary());
        this.drive.setSalary(0);
        this.drive.setVanillaGames(0);
        this.drive.setDlcGames(0);
        this.drive.setDaysRemaining(this.drive.getDeadLine());
        System.out.println(this.drive.getDeadLine());
        System.out.println(this.drive.getDaysRemaining());
        this.dir.setPmPenalties(0);
        this.dir.getFaultLabel().setText(Integer.toString(this.dir.getPmPenalties()));
        
    }
    
}

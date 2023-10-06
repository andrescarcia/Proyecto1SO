/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */

public class GameDeveloper extends Thread {
    private double earnings;
    private double hourlyRate;
    private double dailyOutput;
    private double accumulatedOutput;
    private String gameComponent;
    private boolean isActive = true;
    private String company; // Capcom or SquareEnix
    private int dayDuration;
    private Drive drive;
    private Semaphore mutex;
    public GameDeveloper(String componentType, String company, int day, Drive drive, Semaphore m) {
        this.gameComponent = componentType;
        this.company = company;
        this.earnings = 0;
        this.accumulatedOutput = 0;
        this.configureDeveloperByComponent();
        this.dayDuration = day;
        this.drive = drive;
        this.mutex = m;
    }

    @Override
    public void run() {
        while (this.isActive) {
            try {
                this.produceGameComponent();
                sleep(this.dayDuration); // hay que despues cambiarlo para que se pueda cambiar la duracion del dia en la interfaz
            } catch (InterruptedException ex) {
                System.out.println("Error in developer thread.");
            }
        }
        
    }

    private void configureDeveloperByComponent() {
        if (this.company.equals("Capcom")) {
            switch (this.gameComponent) {
                case "Narrative":
                    this.hourlyRate = 10;
                    this.dailyOutput = 0.5; // 1 guión cada 2 días
                    break;
                case "LevelDesign":
                    this.hourlyRate = 13;
                    this.dailyOutput = 0.5; // 1 nivel cada 2 días
                    break;
                case "SpriteArt":
                    this.hourlyRate = 20;
                    this.dailyOutput = 3; // 3 sprites set cada día
                    break;
                case "GameLogic":
                    this.hourlyRate = 8;
                    this.dailyOutput = 3; // 3 sistemas de juego cada día
                    break;
                case "DLC":
                    this.hourlyRate = 17;
                    this.dailyOutput = 1.0/3; // 1 DLC cada 3 días
                    break;
                case "Integrator":
                    this.hourlyRate = 25;
                    this.dailyOutput = 0.5; // 1 juego cada 2 días
                    break;
                default:
                    // Manejo de componentes no definidos
                    break;
            }
        } else if (this.company.equals("SquareEnix")) {
            switch (this.gameComponent) {
                case "Narrative":
                    this.hourlyRate = 10;
                    this.dailyOutput = 0.25; // 1 guión cada 4 días
                    break;
                case "LevelDesign":
                    this.hourlyRate = 13;
                    this.dailyOutput = 0.25; // 1 nivel cada 4 días
                    break;
                case "SpriteArt":
                    this.hourlyRate = 20;
                    this.dailyOutput = 1; // 1 sprites set cada día
                    break;
                case "GameLogic":
                    this.hourlyRate = 8;
                    this.dailyOutput = 5; // 5 sistemas de juego cada día
                    break;
                case "DLC":
                    this.hourlyRate = 17;
                    this.dailyOutput = 0.5; // 1 DLC cada 2 días
                    break;
                case "Integrator":
                    this.hourlyRate = 25;
                    this.dailyOutput = 0.5; // 1 juego cada 2 días
                    break;
                default:
                    // Manejo de componentes no definidos
                    break;
            }
        }
    }
    


    public void produceGameComponent() {
        this.accumulatedOutput += this.dailyOutput;
        if(this.accumulatedOutput >= 1){
            try {
                int roundAcc = (int) Math.floor(this.accumulatedOutput);
                this.mutex.acquire(1);
                this.drive.addToDrive(roundAcc, this.gameComponent);   
                this.mutex.release();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameDeveloper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void addDailyOutput() {
        // Lógica para añadir la salida diaria a la unidad correspondiente
    }

    public void payDeveloper() {
        this.earnings += (this.hourlyRate * 24);
    }

    public double getEarnings() {
        return earnings;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getDailyOutput() {
        return dailyOutput;
    }

    public double getAccumulatedOutput() {
        return accumulatedOutput;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean b){
        this.isActive = b;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setDailyOutput(double dailyOutput) {
        this.dailyOutput = dailyOutput;
    }

    public void setAccumulatedOutput(double accumulatedOutput) {
        this.accumulatedOutput = accumulatedOutput;
    }


    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
        this.configureDeveloperByComponent(); // Reconfigure based on the new company
    }
    
    public String getGameComponent() {
        return this.gameComponent;
    }

    public void setGameComponent(String gameComponent) {
        this.gameComponent = gameComponent;
        configureDeveloperByComponent(); //  llamar a este método aquí para reconfigurar automáticamente al desarrollador cuando cambies su componente.
    }
    
    
    

}


//import main.classes.DeveloperType;
//
//public class Developer extends Thread {
//    private final int carnet; // su ID
//    private double paga; //paga total del dia
//    private double pagaxHora; //paga por hora
//    private String parteGame; //la parte del juego que produjo
//    private double totalProducido; // lo que el desarrollador produce ese dia
//    private double produccion; //acumulado de la produccion
//    private boolean esIntegrador;
//    private boolean activo = true;
//    
//    
//    //constructor
//    
//    public Developer (int carnet , String DeveloperType){
//        
//        this.carnet = carnet;
//        this.paga = 0;
//        this.parteGame = DeveloperType;
//        
//        
//        
//        
//    }
    

    
    


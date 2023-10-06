/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import java.util.concurrent.Semaphore;
import javax.swing.JLabel;
import javax.swing.JSpinner;

/**
 *
 * @author Sebastián
 */
public class Company {
    private LinkList scriptDevs;
    private LinkList levelDevs;
    private LinkList spriteDevs;
    private LinkList logicDevs;
    private LinkList dlcDevs;
    private ProjectManager pm;
    private Director dir;
    private DirectorWatch dirWatch;
    private Semaphore mutex;
    private Drive companyDrive;
    private int income;
    private int salary;
    private int dayDuration;
    private int hourDuration;
    private int minDuration;
    private JSpinner[] spinners;
    
    public Company(int day, int hour, int min){
        this.mutex = new Semaphore(1);
        this.companyDrive = new Drive();
        
        this.scriptDevs = new LinkList();
        this.levelDevs = new LinkList();
        this.spriteDevs = new LinkList();
        this.logicDevs = new LinkList();
        this.dlcDevs = new LinkList();
        
        this.dayDuration = day;
        this.hourDuration = hour;
        this.minDuration = min;
        
        this.income = 0;
        this.salary = 0;
        
        
    }

    public LinkList getScriptDevs() {
        return scriptDevs;
    }

    public void setScriptDevs(LinkList scriptDevs) {
        this.scriptDevs = scriptDevs;
    }

    public LinkList getLevelDevs() {
        return levelDevs;
    }

    public void setLevelDevs(LinkList levelDevs) {
        this.levelDevs = levelDevs;
    }

    public LinkList getSpriteDevs() {
        return spriteDevs;
    }

    public void setSpriteDevs(LinkList spriteDevs) {
        this.spriteDevs = spriteDevs;
    }

    public LinkList getLogicDevs() {
        return logicDevs;
    }

    public void setLogicDevs(LinkList logicDevs) {
        this.logicDevs = logicDevs;
    }

    public LinkList getDlcDevs() {
        return dlcDevs;
    }

    public void setDlcDevs(LinkList dlcDevs) {
        this.dlcDevs = dlcDevs;
    }

    public ProjectManager getPm() {
        return pm;
    }

    public void setPm(ProjectManager pm) {
        this.pm = pm;
    }

    public Director getDir() {
        return dir;
    }

    public void setDir(Director dir) {
        this.dir = dir;
    }

    public DirectorWatch getDirWatch() {
        return dirWatch;
    }

    public void setDirWatch(DirectorWatch dirWatch) {
        this.dirWatch = dirWatch;
    }

    public Semaphore getMutex() {
        return mutex;
    }

    public void setMutex(Semaphore mutex) {
        this.mutex = mutex;
    }

    public Drive getCompanyDrive() {
        return companyDrive;
    }

    public void setCompanyDrive(Drive companyDrive) {
        this.companyDrive = companyDrive;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getDayDuration() {
        return dayDuration;
    }

    public void setDayDuration(int dayDuration) {
        this.dayDuration = dayDuration;
    }

    public int getHourDuration() {
        return hourDuration;
    }

    public void setHourDuration(int hourDuration) {
        this.hourDuration = hourDuration;
    }

    public int getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(int minDuration) {
        this.minDuration = minDuration;
    }

    public void addDev(GameDeveloper dev){
        
        Node devNode = new Node(dev);
        switch (dev.getGameComponent()){
        
            case "Narrative":
                this.scriptDevs.insertEnd(devNode);
                this.spinners[0].setValue(this.scriptDevs.getlSize());
                break;
        
            case "LevelDesign":
                this.levelDevs.insertEnd(devNode);
                this.spinners[1].setValue(this.levelDevs.getlSize());
                break;
                
            case "SpriteArt":
                this.spriteDevs.insertEnd(devNode);
                this.spinners[2].setValue(this.spriteDevs.getlSize());
                break;
                
            case "GameLogic":
                this.logicDevs.insertEnd(devNode);
                this.spinners[3].setValue(this.logicDevs.getlSize());
                break;
                
            case "DLC":
                this.dlcDevs.insertEnd(devNode);
                this.spinners[4].setValue(this.dlcDevs.getlSize());
                break;
        }        
    }

    public JSpinner[] getSpinners() {
        return spinners;
    }

    public void setSpinners(JSpinner[] spinners) {
        this.spinners = spinners;
    }
    
    public void activateDevs(){
        Node tempNode = this.scriptDevs.getlFirst();
        for(int i = 0; i < this.scriptDevs.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
        
        tempNode = this.levelDevs.getlFirst();
        for(int i = 0; i < this.levelDevs.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
        
        tempNode = this.spriteDevs.getlFirst();
        for(int i = 0; i < this.spriteDevs.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
        
        tempNode = this.logicDevs.getlFirst();
        for(int i = 0; i < this.logicDevs.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
        
        tempNode = this.dlcDevs.getlFirst();
        for(int i = 0; i < this.dlcDevs.getlSize(); i++){
            GameDeveloper tempDev = GameDeveloper.class.cast(tempNode.getData()); 
            tempDev.start();
            tempNode = tempNode.getpNext();
        }
    
    
    }
    
    public void removeDev(String devType){
    
        Node tempNode;
        GameDeveloper tempDev;
        
        switch(devType){
        
            
            
            case "Narrative":
                
                tempNode = this.scriptDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.scriptDevs.delLast();
                
                break;
                
            case "LevelDesign":
                
                tempNode = this.levelDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.levelDevs.delLast();
                
                break;
                
            case "SpriteArt":
                
                tempNode = this.spriteDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.spriteDevs.delLast();
                
                break;
                
            case "GameLogic":
                
                tempNode = this.logicDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.logicDevs.delLast();
                
                break;
                
            case "DLC":
                
                tempNode = this.dlcDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.dlcDevs.delLast();
                
                break;
            
        }
        
    }
    
    
}

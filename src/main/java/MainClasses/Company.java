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
    private String companyName;
    
    private LinkList scriptDevs;
    private LinkList levelDevs;
    private LinkList spriteDevs;
    private LinkList logicDevs;
    private LinkList dlcDevs;
    private LinkList integrators;
    
    private ProjectManager pm;
    private Director dir;
    private DirectorWatch dirWatch;
    
    private Semaphore mutex;
    private Drive companyDrive;
    
    private double income;
    private double salary;
    private double utilities;
    
    private int dayDuration;
    private int hourDuration;
    private int minDuration;
    
    private JLabel[] labels;
    
    private int maxDevs;
    
    public Company(int maxDevs, String company){
        this.mutex = new Semaphore(1);
        this.companyDrive = new Drive(company);
        
        this.scriptDevs = new LinkList();
        this.levelDevs = new LinkList();
        this.spriteDevs = new LinkList();
        this.logicDevs = new LinkList();
        this.dlcDevs = new LinkList();
        this.integrators = new LinkList();
               
        this.utilities = 0;
        this.income = 0;
        this.salary = 0;
        
        this.maxDevs = maxDevs;
        
        this.companyName = company;
        
        
    }

    public int getMaxDevs() {
        return maxDevs;
    }

    public void setMaxDevs(int maxDevs) {
        this.maxDevs = maxDevs;
    }
    
    

    public LinkList getScriptDevs() {
        return scriptDevs;
    }

    public LinkList getIntegrators() {
        return integrators;
    }

    public void setIntegrators(LinkList integrators) {
        this.integrators = integrators;
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

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
        this.labels[14].setText(Double.toString(this.income));
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
        this.labels[16].setText(Double.toString(this.salary));
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
        if((this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()) < this.maxDevs){
            Node devNode = new Node(dev);
            switch (dev.getGameComponent()){

                case "Narrative":
                    this.scriptDevs.insertEnd(devNode);
                    this.labels[1].setText(Integer.toString(this.scriptDevs.getlSize()));
                    this.labels[8].setText(Integer.toString(this.scriptDevs.getlSize()));
                    break;

                case "LevelDesign":
                    this.levelDevs.insertEnd(devNode);
                    this.labels[2].setText(Integer.toString(this.levelDevs.getlSize()));
                    this.labels[9].setText(Integer.toString(this.levelDevs.getlSize()));
                    break;

                case "SpriteArt":
                    this.spriteDevs.insertEnd(devNode);
                    this.labels[3].setText(Integer.toString(this.spriteDevs.getlSize()));
                    this.labels[10].setText(Integer.toString(this.spriteDevs.getlSize()));
                    break;

                case "GameLogic":
                    this.logicDevs.insertEnd(devNode);
                    this.labels[4].setText(Integer.toString(this.logicDevs.getlSize()));
                    this.labels[11].setText(Integer.toString(this.logicDevs.getlSize()));
                    break;

                case "DLC":
                    this.dlcDevs.insertEnd(devNode);
                    this.labels[5].setText(Integer.toString(this.dlcDevs.getlSize()));
                    this.labels[12].setText(Integer.toString(this.dlcDevs.getlSize()));
                    break;
            }        

            this.labels[0].setText(Integer.toString(this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()));
            this.labels[7].setText(Integer.toString(this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()));
            dev.start();
            }
    }

    
    public void addIntegrator(Integrator integ){
        if((this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()) < this.maxDevs){
            Node integNode = new Node(integ);
            this.integrators.insertEnd(integNode);
            this.labels[6].setText(Integer.toString(this.integrators.getlSize()));
            this.labels[13].setText(Integer.toString(this.integrators.getlSize()));
            
            this.labels[0].setText(Integer.toString(this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()));
            this.labels[7].setText(Integer.toString(this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()));
            integ.start();
        }
    }

    public void removeIntegrator(){
        if (this.integrators.getlSize() > 1) {
            Node tempNode;
            Integrator tempInte;

            tempNode = this.integrators.getlLast();
            tempInte = Integrator.class.cast(tempNode.getData());
            tempInte.setIsActive(false);
            this.integrators.delLast();
            this.labels[6].setText(Integer.toString(this.integrators.getlSize()));
            this.labels[13].setText(Integer.toString(this.integrators.getlSize()));
            this.labels[0].setText(Integer.toString(this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()));
            this.labels[7].setText(Integer.toString(this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()));
        
        }
       
    }
    
    
    public boolean canDecreaseMaxDevs() {
        int totalDevs = scriptDevs.getlSize() + levelDevs.getlSize() + spriteDevs.getlSize() + logicDevs.getlSize() + dlcDevs.getlSize() + integrators.getlSize();
        return totalDevs < maxDevs;
    }
    
 /*
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
*/
    
    public void removeDev(String devType){
    
        Node tempNode;
        GameDeveloper tempDev;
        
        switch(devType){
        
            
            
            case "Narrative":
                
                tempNode = this.scriptDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.scriptDevs.delLast();
                this.labels[1].setText(Integer.toString(this.scriptDevs.getlSize()));
                this.labels[8].setText(Integer.toString(this.scriptDevs.getlSize()));
                
                break;
                
            case "LevelDesign":
                
                tempNode = this.levelDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.levelDevs.delLast();
                this.labels[2].setText(Integer.toString(this.levelDevs.getlSize()));
                this.labels[9].setText(Integer.toString(this.levelDevs.getlSize()));
                
                break;
                
            case "SpriteArt":
                
                tempNode = this.spriteDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.spriteDevs.delLast();
                this.labels[3].setText(Integer.toString(this.spriteDevs.getlSize()));
                this.labels[10].setText(Integer.toString(this.spriteDevs.getlSize()));
                
                break;
                
            case "GameLogic":
                
                tempNode = this.logicDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.logicDevs.delLast();
                this.labels[4].setText(Integer.toString(this.logicDevs.getlSize()));
                this.labels[11].setText(Integer.toString(this.logicDevs.getlSize()));
                
                break;
                
            case "DLC":
                
                tempNode = this.dlcDevs.getlLast();
                tempDev = GameDeveloper.class.cast(tempNode.getData());
                tempDev.setIsActive(false);
                this.dlcDevs.delLast();
                this.labels[5].setText(Integer.toString(this.dlcDevs.getlSize()));
                this.labels[12].setText(Integer.toString(this.dlcDevs.getlSize()));
                
                break;
            
        }
        
        this.labels[0].setText(Integer.toString(this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()));
        this.labels[7].setText(Integer.toString(this.scriptDevs.getlSize() + this.levelDevs.getlSize() + this.spriteDevs.getlSize() + this.logicDevs.getlSize() + this.dlcDevs.getlSize() + this.integrators.getlSize()));
        
    }

    public JLabel[] getLabels() {
        return labels;
    }

    public void setLabels(JLabel[] labels) {
        this.labels = labels;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getUtilities() {
        return utilities;
    }

    public void setUtilities(double utilities) {
        this.utilities = utilities;
        this.labels[15].setText(Double.toString(this.utilities));
    }
    
    public void addSalary(double salary){
        this.salary += salary;
        this.labels[16].setText(Double.toString(this.salary));
    }
    
    public void addIncome(double income){
        this.income += income;
        this.labels[14].setText(Double.toString(this.income));
    }
    
}

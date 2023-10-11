/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI_Classes;
import MainClasses.Company;
import MainClasses.Director;
import MainClasses.DirectorWatch;
import MainClasses.GameDeveloper; 
import MainClasses.Drive;
import MainClasses.Integrator;
import MainClasses.ProjectManager;
import java.util.concurrent.Semaphore;
import javax.swing.JLabel;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.JSpinner;
/**
 *
 * @author andre y sebaa 
 */
public class Main extends javax.swing.JFrame {
        Company capcom = new Company(11, "Capcom");
        
        Company squareEnix = new Company(10, "SquareEnix");
        
        int dayDuration;
        
    /**
     * Creates new form Main
     */
    public Main() {
        initComponents();
        
        JLabel[] capDriveLabels = {narrativeqtyGUI, levelqtyGUI, spriteqtyGUI, logicqtyGUI1, dlcqtyGUI, DeadlineGUI, readygamesqtyGUI, readydlcGUI};
        JLabel[] capCompanyLabels = {DevQty, NarrativeQty, LevelQty, SpriteQty, LogicQty, DLCQty, IntegratorQty, DevQty2, NarrativeQty2, LevelQty2, SpriteQty2, LogicQty2, DLCQty2, IntegratorQty2, incomeGUI, utilityGUI, totalsalariesGUI};
        
        JLabel[] squareDriveLabels = {narrativeqtyGUI1, levelqtyGUI1, spriteqtyGUI1, logicqtyGUI2, dlcqtyGUI1, DeadlineGUI1, readygamesqtyGUI1, readydlcGUI1};  
        JLabel[] squareCompanyLabels = {DevQty1, NarrativeQty1, LevelQty1, SpriteQty1, LogicQty1, DLCQty1, IntegratorQty1, DevQty3, NarrativeQty3, LevelQty3, SpriteQty3, LogicQty3, DLCQty3, IntegratorQty3, incomeGUI1, utilityGUI2, totalsalariesGUI1};
        
        
        
        this.capcom.getCompanyDrive().setLabels(capDriveLabels);
        this.capcom.setLabels(capCompanyLabels);
        
        this.squareEnix.getCompanyDrive().setLabels(squareDriveLabels);
        this.squareEnix.setLabels(squareCompanyLabels);
        
       
     
        readConfig();
        
        
        int hourDuration = dayDuration/24;
        int minDuration = hourDuration/60;
        if(hourDuration == 0){
            minDuration = 1;
        }
 
/*
        this.capcom.activateDevs();
        this.squareEnix.activateDevs();
*/        
        ProjectManager capMan = new ProjectManager(10, this.dayDuration, hourDuration, minDuration,this.capcom.getCompanyDrive(),this.capcom.getMutex(), PMstateGUI);
        Director capDir = new Director(this.capcom.getCompanyDrive(), this.capcom.getMutex(), capMan, minDuration, PMfaultsGUI);
        DirectorWatch capWatch = new DirectorWatch(10, this.dayDuration, hourDuration, minDuration, capDir, directorstateGUI, this.capcom);

        
        ProjectManager squareMan = new ProjectManager(10, this.dayDuration, hourDuration, minDuration, this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex(), PMstateGUI1);
//        Director squareDir = new Director(SquareDrive, SquareMutex, squareMan, minDuration, PMfaultsGUI1);
//        DirectorWatch squareWatch = new DirectorWatch(10, dayDuration, hourDuration, minDuration, squareDir, SquareDrive, directorstateGUI1);
        
        
        capMan.start();
        capDir.start();
        capWatch.start();
        
        
        
        
        
    }
    
    public void readConfig(){
        String savedDevs = "";
        String line;
        String path = "cosas.txt";
        File file = new File(path);
        try{
            if(!file.exists()){
                file.createNewFile();
                
            }else{
            
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                
                while((line = br.readLine()) != null){
                    if(!line.isEmpty()){
                        String[] arraySplit = line.split(",");
                        
                        switch(arraySplit[0]){
                        
                            case "dia":
                                this.dayDuration = Integer.parseInt(arraySplit[1]);
                                addDefaults();
                                break;
                                
                            case "GameDeveloper":
                                
                                switch(arraySplit[1]){
                                    
                                    case "Capcom":
                                        
                                        for(int j = 0; j < Integer.parseInt(arraySplit[3]) - 1; j++){
                                        
                                            GameDeveloper capDev = new GameDeveloper(arraySplit[2], "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
                                            capcom.addDev(capDev);
                                        
                                        }
                                        break;
                                        
                                    case "SquareEnix":
                                        
                                        for(int j = 0; j < Integer.parseInt(arraySplit[3]) - 1; j++){
                                        
                                            GameDeveloper squareDev = new GameDeveloper(arraySplit[2], "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
                                            squareEnix.addDev(squareDev);
                                        
                                        }
                                        break;
                                
                                }
                                
                                break;
                                
                            case "deadLine":
                                
                                this.capcom.getCompanyDrive().setDeadLine(Integer.parseInt(arraySplit[1]));
                                this.capcom.getCompanyDrive().setDaysRemaining(Integer.parseInt(arraySplit[1]));
                                this.squareEnix.getCompanyDrive().setDeadLine(Integer.parseInt(arraySplit[1]));
                                this.squareEnix.getCompanyDrive().setDaysRemaining(Integer.parseInt(arraySplit[1]));
                                
                                
                                
                                break;
                            
                            case "Integrator":
                                
                                switch(arraySplit[1]){
                                    
                                    case "Capcom":
                                        
                                        for(int j = 0; j < Integer.parseInt(arraySplit[2]) - 1; j++){
                                        
                                            Integrator capInt = new Integrator(this.capcom.getCompanyDrive(), this.capcom.getMutex(), this.dayDuration);
                                            capcom.addIntegrator(capInt);
                                        
                                        }
                                        break;
                                        
                                    case "SquareEnix":
                                        
                                        for(int j = 0; j < Integer.parseInt(arraySplit[2]) - 1; j++){
                                        
                                            Integrator squareInt = new Integrator(this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex(), this.dayDuration);
                                            squareEnix.addIntegrator(squareInt);
                                        
                                        }
                                        break;
                             
                            }
                        
                        }
                    }
                }
                br.close();
                
            }
            
            
            
                
        }catch(Exception e){
            System.out.println(e);
            }
        }
        
    /*Funcion que inicializa los trabajadores iniciales, el minimo de 1 por cada tipo*/
    
    public void addDefaults(){
        String[] gameParts = {"Narrative", "LevelDesign", "SpriteArt", "GameLogic", "DLC"};
        for(int i = 0; i < gameParts.length; i++){
            GameDeveloper capDev = new GameDeveloper(gameParts[i], "Capcom", this.dayDuration, this.capcom.getCompanyDrive(), this.capcom.getMutex());
            this.capcom.addDev(capDev);
        }
        Integrator capInt = new Integrator(this.capcom.getCompanyDrive(), this.capcom.getMutex(), this.dayDuration);
        this.capcom.addIntegrator(capInt);

        for(int j = 0; j < gameParts.length; j++){
            GameDeveloper squareDev = new GameDeveloper(gameParts[j], "SquareEnix", this.dayDuration, this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex());
            this.squareEnix.addDev(squareDev);
        }
        Integrator squareInt = new Integrator(this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex(), this.dayDuration);
        this.squareEnix.addIntegrator(squareInt);
        
        /*        
        GameDeveloper capScript = new GameDeveloper("Narrative", "Capcom", this.dayDuration, this.capcom.getCompanyDrive(), this.capcom.getMutex());
        GameDeveloper capLevel = new GameDeveloper("LevelDesign", "Capcom", this.dayDuration, this.capcom.getCompanyDrive(), this.capcom.getMutex());
        GameDeveloper capSprite = new GameDeveloper("SpriteArt", "Capcom", this.dayDuration, this.capcom.getCompanyDrive(), this.capcom.getMutex());
        GameDeveloper capLogic = new GameDeveloper("GameLogic", "Capcom", this.dayDuration, this.capcom.getCompanyDrive(), this.capcom.getMutex());
        GameDeveloper capDLC = new GameDeveloper("DLC", "Capcom", this.dayDuration, this.capcom.getCompanyDrive(), this.capcom.getMutex());
        Integrator capInt = new Integrator(this.capcom.getCompanyDrive(), this.capcom.getMutex(), this.dayDuration);   
        this.capcom.addDev(capScript);
        this.capcom.addDev(capLevel);
        this.capcom.addDev(capSprite);
        this.capcom.addDev(capLogic);
        this.capcom.addDev(capDLC);
        this.capcom.addIntegrator(capInt);
  
       
        GameDeveloper squareScript = new GameDeveloper("Narrative", "SquareEnix", this.dayDuration, this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex());
        GameDeveloper squareLevel = new GameDeveloper("LevelDesign", "SquareEnix", this.dayDuration, this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex());
        GameDeveloper squareSprite = new GameDeveloper("SpriteArt", "SquareEnix", this.dayDuration, this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex());
        GameDeveloper squareLogic = new GameDeveloper("GameLogic", "SquareEnix", this.dayDuration, this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex());
        GameDeveloper squareDLC = new GameDeveloper("DLC", "SquareEnix", this.dayDuration, this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex());
        Integrator squareInt = new Integrator(this.squareEnix.getCompanyDrive(), this.squareEnix.getMutex(), this.dayDuration);
        this.squareEnix.addDev(squareScript);
        this.squareEnix.addDev(squareLevel);
        this.squareEnix.addDev(squareSprite);
        this.squareEnix.addDev(squareLogic);
        this.squareEnix.addDev(squareDLC);
        this.squareEnix.addIntegrator(squareInt);
 
*/        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        ConfigPanel = new javax.swing.JPanel();
        BG3 = new javax.swing.JPanel();
        SidePanel3 = new javax.swing.JPanel();
        btn_SquareEnix3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btn_Capcom = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        btn_Configuration = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        barrasuperior = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        panelDerecho = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        plusLogicQty1 = new javax.swing.JButton();
        plusLevelQty1 = new javax.swing.JButton();
        LevelQty1 = new javax.swing.JLabel();
        lessSpriteQty1 = new javax.swing.JButton();
        plusSpriteQty1 = new javax.swing.JButton();
        lessLevelQty1 = new javax.swing.JButton();
        plusDLCQty1 = new javax.swing.JButton();
        IntegratorQty1 = new javax.swing.JLabel();
        lessIntegratorQty1 = new javax.swing.JButton();
        DevQty1 = new javax.swing.JLabel();
        lessDevQty1 = new javax.swing.JButton();
        DLCQty1 = new javax.swing.JLabel();
        LogicQty1 = new javax.swing.JLabel();
        SpriteQty1 = new javax.swing.JLabel();
        lessNarrativeQty1 = new javax.swing.JButton();
        lessDLCQty1 = new javax.swing.JButton();
        plusNarrativeQty1 = new javax.swing.JButton();
        plusDevQty1 = new javax.swing.JButton();
        plusIntegratorQty1 = new javax.swing.JButton();
        NarrativeQty1 = new javax.swing.JLabel();
        lessLogicQty1 = new javax.swing.JButton();
        panelIzquierdo = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        DevQty = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        lessDevQty = new javax.swing.JButton();
        plusDevQty = new javax.swing.JButton();
        jLabel103 = new javax.swing.JLabel();
        LevelQty = new javax.swing.JLabel();
        plusLevelQty = new javax.swing.JButton();
        lessLevelQty = new javax.swing.JButton();
        LogicQty = new javax.swing.JLabel();
        plusLogicQty = new javax.swing.JButton();
        lessLogicQty = new javax.swing.JButton();
        SpriteQty = new javax.swing.JLabel();
        plusSpriteQty = new javax.swing.JButton();
        lessSpriteQty = new javax.swing.JButton();
        NarrativeQty = new javax.swing.JLabel();
        plusNarrativeQty = new javax.swing.JButton();
        lessNarrativeQty = new javax.swing.JButton();
        DLCQty = new javax.swing.JLabel();
        plusDLCQty = new javax.swing.JButton();
        lessDLCQty = new javax.swing.JButton();
        IntegratorQty = new javax.swing.JLabel();
        plusIntegratorQty = new javax.swing.JButton();
        lessIntegratorQty = new javax.swing.JButton();
        panelDerecho3 = new javax.swing.JPanel();
        jLabel102 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        saveButton = new javax.swing.JButton();
        DayDur = new javax.swing.JLabel();
        plusDayDur = new javax.swing.JButton();
        lessDayDur = new javax.swing.JButton();
        plusDeadLine = new javax.swing.JButton();
        lessDeadLine = new javax.swing.JButton();
        DeadLineDays = new javax.swing.JLabel();
        CapcomPanel = new javax.swing.JPanel();
        BG4 = new javax.swing.JPanel();
        SidePanel4 = new javax.swing.JPanel();
        btn_SquareEnix4 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        btn_Capcom1 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        btn_Configuration1 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        barrasuperior1 = new javax.swing.JPanel();
        exit1 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        panelIzquierdo1 = new javax.swing.JPanel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        directorstateGUI = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        PMstateGUI = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        PMfaultsGUI = new javax.swing.JLabel();
        PMfaults$GUI = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        IntegratorQty2 = new javax.swing.JLabel();
        plusIntegratorQty2 = new javax.swing.JButton();
        lessIntegratorQty2 = new javax.swing.JButton();
        lessDLCQty2 = new javax.swing.JButton();
        plusDLCQty2 = new javax.swing.JButton();
        DLCQty2 = new javax.swing.JLabel();
        LogicQty2 = new javax.swing.JLabel();
        plusLogicQty2 = new javax.swing.JButton();
        lessLogicQty2 = new javax.swing.JButton();
        lessSpriteQty2 = new javax.swing.JButton();
        plusSpriteQty2 = new javax.swing.JButton();
        SpriteQty2 = new javax.swing.JLabel();
        LevelQty2 = new javax.swing.JLabel();
        plusLevelQty2 = new javax.swing.JButton();
        lessLevelQty2 = new javax.swing.JButton();
        lessNarrativeQty2 = new javax.swing.JButton();
        plusNarrativeQty2 = new javax.swing.JButton();
        NarrativeQty2 = new javax.swing.JLabel();
        DevQty2 = new javax.swing.JLabel();
        plusDevQty2 = new javax.swing.JButton();
        lessDevQty2 = new javax.swing.JButton();
        panelCentro = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        levelqtyGUI = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        DeadlineGUI = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        dayspassedGUI1 = new javax.swing.JLabel();
        logicqtyGUI1 = new javax.swing.JLabel();
        spriteqtyGUI = new javax.swing.JLabel();
        dlcqtyGUI = new javax.swing.JLabel();
        narrativeqtyGUI = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        logicMAXqtyGUI2 = new javax.swing.JLabel();
        dlcMAXqtyGUI = new javax.swing.JLabel();
        spriteMAXqtyGUI3 = new javax.swing.JLabel();
        levelMAXqtyGUI1 = new javax.swing.JLabel();
        narrativeMAXqtyGUI = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        readydlcGUI = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        readygamesqtyGUI = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        panelDerecho1 = new javax.swing.JPanel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        totalsalariesGUI = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        utilityGUI = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        incomeGUI = new javax.swing.JLabel();
        SquareEnixPanel = new javax.swing.JPanel();
        BG5 = new javax.swing.JPanel();
        SidePanel5 = new javax.swing.JPanel();
        btn_SquareEnix5 = new javax.swing.JPanel();
        jLabel69 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        btn_Capcom2 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        btn_configuration = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        barrasuperior2 = new javax.swing.JPanel();
        exit2 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        panelIzquierdo2 = new javax.swing.JPanel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        directorstateGUI1 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        PMstateGUI1 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        PMfaultsGUI1 = new javax.swing.JLabel();
        PMfaults$GUI1 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        IntegratorQty3 = new javax.swing.JLabel();
        plusIntegratorQty3 = new javax.swing.JButton();
        lessIntegratorQty3 = new javax.swing.JButton();
        lessDLCQty3 = new javax.swing.JButton();
        plusDLCQty3 = new javax.swing.JButton();
        DLCQty3 = new javax.swing.JLabel();
        LogicQty3 = new javax.swing.JLabel();
        plusLogicQty3 = new javax.swing.JButton();
        lessLogicQty3 = new javax.swing.JButton();
        lessSpriteQty3 = new javax.swing.JButton();
        plusSpriteQty3 = new javax.swing.JButton();
        SpriteQty3 = new javax.swing.JLabel();
        LevelQty3 = new javax.swing.JLabel();
        plusLevelQty3 = new javax.swing.JButton();
        lessLevelQty3 = new javax.swing.JButton();
        lessNarrativeQty3 = new javax.swing.JButton();
        plusNarrativeQty3 = new javax.swing.JButton();
        NarrativeQty3 = new javax.swing.JLabel();
        DevQty3 = new javax.swing.JLabel();
        plusDevQty3 = new javax.swing.JButton();
        lessDevQty3 = new javax.swing.JButton();
        panelCentro1 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        levelqtyGUI1 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        DeadlineGUI1 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        dayspassedGUI2 = new javax.swing.JLabel();
        logicqtyGUI2 = new javax.swing.JLabel();
        spriteqtyGUI1 = new javax.swing.JLabel();
        dlcqtyGUI1 = new javax.swing.JLabel();
        narrativeqtyGUI1 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        logicMAXqtyGUI3 = new javax.swing.JLabel();
        dlcMAXqtyGUI1 = new javax.swing.JLabel();
        spriteMAXqtyGUI4 = new javax.swing.JLabel();
        levelMAXqtyGUI2 = new javax.swing.JLabel();
        narrativeMAXqtyGUI1 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        readydlcGUI1 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        readygamesqtyGUI1 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        panelDerecho2 = new javax.swing.JPanel();
        jLabel99 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        totalsalariesGUI1 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        incomeGUI1 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        utilityGUI2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        BG3.setBackground(new java.awt.Color(255, 255, 255));
        BG3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SidePanel3.setBackground(new java.awt.Color(10, 44, 88));
        SidePanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_SquareEnix3.setBackground(new java.awt.Color(22, 95, 190));
        btn_SquareEnix3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SquareEnix3MouseClicked(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Square Enix stats ");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_SquareEnix3Layout = new javax.swing.GroupLayout(btn_SquareEnix3);
        btn_SquareEnix3.setLayout(btn_SquareEnix3Layout);
        btn_SquareEnix3Layout.setHorizontalGroup(
            btn_SquareEnix3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_SquareEnix3Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel13)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        btn_SquareEnix3Layout.setVerticalGroup(
            btn_SquareEnix3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_SquareEnix3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel3.add(btn_SquareEnix3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 330, 70));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Unimet 2023 ®");
        SidePanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, -1, -1));

        jSeparator1.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SidePanel3.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 96, 260, 10));

        btn_Capcom.setBackground(new java.awt.Color(22, 95, 190));
        btn_Capcom.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CapcomMouseClicked(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Capcom STATS");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_CapcomLayout = new javax.swing.GroupLayout(btn_Capcom);
        btn_Capcom.setLayout(btn_CapcomLayout);
        btn_CapcomLayout.setHorizontalGroup(
            btn_CapcomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_CapcomLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel14)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        btn_CapcomLayout.setVerticalGroup(
            btn_CapcomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_CapcomLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel3.add(btn_Capcom, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        btn_Configuration.setBackground(new java.awt.Color(22, 95, 190));
        btn_Configuration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ConfigurationMouseClicked(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Configuration");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_ConfigurationLayout = new javax.swing.GroupLayout(btn_Configuration);
        btn_Configuration.setLayout(btn_ConfigurationLayout);
        btn_ConfigurationLayout.setHorizontalGroup(
            btn_ConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_ConfigurationLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel15)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        btn_ConfigurationLayout.setVerticalGroup(
            btn_ConfigurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_ConfigurationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel3.add(btn_Configuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, -1));
        SidePanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel110.setFont(new java.awt.Font("Alien Encounters", 1, 20)); // NOI18N
        jLabel110.setForeground(new java.awt.Color(255, 255, 255));
        jLabel110.setText("Sebastian Castillo ");
        SidePanel3.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, -1, 33));

        jLabel111.setFont(new java.awt.Font("Alien Encounters", 1, 20)); // NOI18N
        jLabel111.setForeground(new java.awt.Color(255, 255, 255));
        jLabel111.setText("Andre Scarcia");
        SidePanel3.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 520, -1, 30));

        BG3.add(SidePanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 720));

        jPanel2.setBackground(new java.awt.Color(22, 95, 190));

        barrasuperior.setBackground(new java.awt.Color(29, 121, 241));
        barrasuperior.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barrasuperiorMouseDragged(evt);
            }
        });
        barrasuperior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barrasuperiorMousePressed(evt);
            }
        });

        exit.setFont(new java.awt.Font("Alien Encounters", 1, 24)); // NOI18N
        exit.setForeground(new java.awt.Color(255, 205, 16));
        exit.setText("Exit");
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exitMousePressed(evt);
            }
        });

        javax.swing.GroupLayout barrasuperiorLayout = new javax.swing.GroupLayout(barrasuperior);
        barrasuperior.setLayout(barrasuperiorLayout);
        barrasuperiorLayout.setHorizontalGroup(
            barrasuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barrasuperiorLayout.createSequentialGroup()
                .addContainerGap(1226, Short.MAX_VALUE)
                .addComponent(exit)
                .addGap(20, 20, 20))
        );
        barrasuperiorLayout.setVerticalGroup(
            barrasuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barrasuperiorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exit, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );

        jLabel16.setBackground(new java.awt.Color(22, 95, 190));
        jLabel16.setFont(new java.awt.Font("Alien Encounters", 1, 48)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 205, 16));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("configuration");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barrasuperior, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(356, 356, 356))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(barrasuperior, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addContainerGap())
        );

        BG3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 1300, 120));

        panelDerecho.setBackground(new java.awt.Color(10, 44, 88));

        jLabel17.setFont(new java.awt.Font("Alien Encounters", 1, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Square Enix");

        jLabel38.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(255, 255, 255));
        jLabel38.setText("Integrators:");

        jLabel27.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Sprite artist:");

        jLabel24.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Narrative developer:");

        jLabel29.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("DLC Developer:");

        jLabel28.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Logic programmer :");

        jLabel25.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Level designer:");

        jLabel19.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Developers qty :");

        plusLogicQty1.setText("-");
        plusLogicQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLogicQty1ActionPerformed(evt);
            }
        });

        plusLevelQty1.setText("-");
        plusLevelQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLevelQty1ActionPerformed(evt);
            }
        });

        LevelQty1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        LevelQty1.setForeground(new java.awt.Color(255, 255, 255));
        LevelQty1.setText("X");

        lessSpriteQty1.setText("+");
        lessSpriteQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessSpriteQty1ActionPerformed(evt);
            }
        });

        plusSpriteQty1.setText("-");
        plusSpriteQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusSpriteQty1ActionPerformed(evt);
            }
        });

        lessLevelQty1.setText("+");
        lessLevelQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessLevelQty1ActionPerformed(evt);
            }
        });

        plusDLCQty1.setText("-");
        plusDLCQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDLCQty1ActionPerformed(evt);
            }
        });

        IntegratorQty1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        IntegratorQty1.setForeground(new java.awt.Color(255, 255, 255));
        IntegratorQty1.setText("X");

        lessIntegratorQty1.setText("-");
        lessIntegratorQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessIntegratorQty1ActionPerformed(evt);
            }
        });

        DevQty1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DevQty1.setForeground(new java.awt.Color(255, 255, 255));
        DevQty1.setText("X");

        lessDevQty1.setText("-");
        lessDevQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDevQty1ActionPerformed(evt);
            }
        });

        DLCQty1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DLCQty1.setForeground(new java.awt.Color(255, 255, 255));
        DLCQty1.setText("X");

        LogicQty1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        LogicQty1.setForeground(new java.awt.Color(255, 255, 255));
        LogicQty1.setText("X");

        SpriteQty1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        SpriteQty1.setForeground(new java.awt.Color(255, 255, 255));
        SpriteQty1.setText("X");

        lessNarrativeQty1.setText("+");
        lessNarrativeQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessNarrativeQty1ActionPerformed(evt);
            }
        });

        lessDLCQty1.setText("+");
        lessDLCQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDLCQty1ActionPerformed(evt);
            }
        });

        plusNarrativeQty1.setText("-");
        plusNarrativeQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusNarrativeQty1ActionPerformed(evt);
            }
        });

        plusDevQty1.setText("+");
        plusDevQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDevQty1ActionPerformed(evt);
            }
        });

        plusIntegratorQty1.setText("+");
        plusIntegratorQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusIntegratorQty1ActionPerformed(evt);
            }
        });

        NarrativeQty1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        NarrativeQty1.setForeground(new java.awt.Color(255, 255, 255));
        NarrativeQty1.setText("X");

        lessLogicQty1.setText("+");
        lessLogicQty1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessLogicQty1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelDerechoLayout = new javax.swing.GroupLayout(panelDerecho);
        panelDerecho.setLayout(panelDerechoLayout);
        panelDerechoLayout.setHorizontalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(32, 32, 32)
                .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDerechoLayout.createSequentialGroup()
                        .addComponent(DevQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(plusDevQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessDevQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerechoLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDerechoLayout.createSequentialGroup()
                                .addComponent(IntegratorQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(plusIntegratorQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessIntegratorQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDerechoLayout.createSequentialGroup()
                                .addComponent(LevelQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(plusLevelQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessLevelQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDerechoLayout.createSequentialGroup()
                                .addComponent(LogicQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(plusLogicQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessLogicQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDerechoLayout.createSequentialGroup()
                                .addComponent(SpriteQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(plusSpriteQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessSpriteQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDerechoLayout.createSequentialGroup()
                                .addComponent(NarrativeQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(plusNarrativeQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessNarrativeQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelDerechoLayout.createSequentialGroup()
                                .addComponent(DLCQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(plusDLCQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessDLCQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDerechoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(70, 70, 70))
        );
        panelDerechoLayout.setVerticalGroup(
            panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerechoLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDerechoLayout.createSequentialGroup()
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerechoLayout.createSequentialGroup()
                        .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessDevQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusDevQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DevQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessNarrativeQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusNarrativeQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NarrativeQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessLevelQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusLevelQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LevelQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessSpriteQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusSpriteQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpriteQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessLogicQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusLogicQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LogicQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessDLCQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusDLCQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DLCQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelDerechoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessIntegratorQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusIntegratorQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IntegratorQty1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(156, Short.MAX_VALUE))
        );

        BG3.add(panelDerecho, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 400, 600));

        panelIzquierdo.setBackground(new java.awt.Color(10, 44, 88));

        jLabel20.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Narrative developer:");

        jLabel21.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Level designer:");

        jLabel22.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Sprite artist:");

        jLabel23.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Logic programmer :");

        jLabel26.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("DLC Developer:");

        jLabel37.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(255, 255, 255));
        jLabel37.setText("Integrators:");

        DevQty.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DevQty.setForeground(new java.awt.Color(255, 255, 255));
        DevQty.setText("X");

        jLabel33.setFont(new java.awt.Font("Alien Encounters", 1, 36)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setText("Capcom");

        lessDevQty.setText("-");
        lessDevQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDevQtyActionPerformed(evt);
            }
        });

        plusDevQty.setText("+");
        plusDevQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDevQtyActionPerformed(evt);
            }
        });

        jLabel103.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setText("Developers qty :");

        LevelQty.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        LevelQty.setForeground(new java.awt.Color(255, 255, 255));
        LevelQty.setText("X");

        plusLevelQty.setText("-");
        plusLevelQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLevelQtyActionPerformed(evt);
            }
        });

        lessLevelQty.setText("+");
        lessLevelQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessLevelQtyActionPerformed(evt);
            }
        });

        LogicQty.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        LogicQty.setForeground(new java.awt.Color(255, 255, 255));
        LogicQty.setText("X");

        plusLogicQty.setText("-");
        plusLogicQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLogicQtyActionPerformed(evt);
            }
        });

        lessLogicQty.setText("+");
        lessLogicQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessLogicQtyActionPerformed(evt);
            }
        });

        SpriteQty.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        SpriteQty.setForeground(new java.awt.Color(255, 255, 255));
        SpriteQty.setText("X");

        plusSpriteQty.setText("-");
        plusSpriteQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusSpriteQtyActionPerformed(evt);
            }
        });

        lessSpriteQty.setText("+");
        lessSpriteQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessSpriteQtyActionPerformed(evt);
            }
        });

        NarrativeQty.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        NarrativeQty.setForeground(new java.awt.Color(255, 255, 255));
        NarrativeQty.setText("X");

        plusNarrativeQty.setText("-");
        plusNarrativeQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusNarrativeQtyActionPerformed(evt);
            }
        });

        lessNarrativeQty.setText("+");
        lessNarrativeQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessNarrativeQtyActionPerformed(evt);
            }
        });

        DLCQty.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DLCQty.setForeground(new java.awt.Color(255, 255, 255));
        DLCQty.setText("X");

        plusDLCQty.setText("-");
        plusDLCQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDLCQtyActionPerformed(evt);
            }
        });

        lessDLCQty.setText("+");
        lessDLCQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDLCQtyActionPerformed(evt);
            }
        });

        IntegratorQty.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        IntegratorQty.setForeground(new java.awt.Color(255, 255, 255));
        IntegratorQty.setText("X");

        plusIntegratorQty.setText("-");
        plusIntegratorQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusIntegratorQtyActionPerformed(evt);
            }
        });

        lessIntegratorQty.setText("+");
        lessIntegratorQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessIntegratorQtyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelIzquierdoLayout = new javax.swing.GroupLayout(panelIzquierdo);
        panelIzquierdo.setLayout(panelIzquierdoLayout);
        panelIzquierdoLayout.setHorizontalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdoLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addComponent(jLabel103)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DevQty, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(plusDevQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessDevQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addComponent(jLabel37)
                        .addGap(130, 130, 130)
                        .addComponent(IntegratorQty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                        .addComponent(plusIntegratorQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessIntegratorQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(106, 106, 106)
                        .addComponent(LevelQty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plusLevelQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessLevelQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(68, 68, 68)
                        .addComponent(LogicQty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plusLogicQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessLogicQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(SpriteQty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plusSpriteQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessSpriteQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addGap(39, 39, 39)
                        .addComponent(NarrativeQty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plusNarrativeQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessNarrativeQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdoLayout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addGap(110, 110, 110)
                        .addComponent(DLCQty, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(plusDLCQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lessDLCQty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzquierdoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel33)
                .addGap(122, 122, 122))
        );
        panelIzquierdoLayout.setVerticalGroup(
            panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdoLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lessDevQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plusDevQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DevQty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel103, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lessNarrativeQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plusNarrativeQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(NarrativeQty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lessLevelQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plusLevelQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LevelQty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lessSpriteQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plusSpriteQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(SpriteQty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lessLogicQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plusLogicQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(LogicQty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lessDLCQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plusDLCQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DLCQty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lessIntegratorQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plusIntegratorQty, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(IntegratorQty, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(148, Short.MAX_VALUE))
        );

        BG3.add(panelIzquierdo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 400, 600));

        panelDerecho3.setBackground(new java.awt.Color(10, 44, 88));

        jLabel102.setFont(new java.awt.Font("Alien Encounters", 1, 36)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 255, 255));
        jLabel102.setText("Days Config");

        jLabel32.setFont(new java.awt.Font("Alien Encounters", 1, 20)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setText("deadline days:");

        jLabel31.setFont(new java.awt.Font("Alien Encounters", 1, 20)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Day duration:");

        saveButton.setFont(new java.awt.Font("Alien Encounters", 0, 18)); // NOI18N
        saveButton.setText("SAVE");

        DayDur.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DayDur.setForeground(new java.awt.Color(255, 255, 255));
        DayDur.setText("X");

        plusDayDur.setText("+");
        plusDayDur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDayDurActionPerformed(evt);
            }
        });

        lessDayDur.setText("-");
        lessDayDur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDayDurActionPerformed(evt);
            }
        });

        plusDeadLine.setText("+");
        plusDeadLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDeadLineActionPerformed(evt);
            }
        });

        lessDeadLine.setText("-");
        lessDeadLine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDeadLineActionPerformed(evt);
            }
        });

        DeadLineDays.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DeadLineDays.setForeground(new java.awt.Color(255, 255, 255));
        DeadLineDays.setText("X");

        javax.swing.GroupLayout panelDerecho3Layout = new javax.swing.GroupLayout(panelDerecho3);
        panelDerecho3.setLayout(panelDerecho3Layout);
        panelDerecho3Layout.setHorizontalGroup(
            panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerecho3Layout.createSequentialGroup()
                .addContainerGap(71, Short.MAX_VALUE)
                .addGroup(panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDerecho3Layout.createSequentialGroup()
                        .addComponent(jLabel102)
                        .addGap(70, 70, 70))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDerecho3Layout.createSequentialGroup()
                        .addGroup(panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel32)
                            .addGroup(panelDerecho3Layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(119, 119, 119))
                    .addGroup(panelDerecho3Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addGap(33, 33, 33)
                        .addGroup(panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelDerecho3Layout.createSequentialGroup()
                                .addComponent(DeadLineDays, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(plusDeadLine, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessDeadLine, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(panelDerecho3Layout.createSequentialGroup()
                                .addComponent(DayDur, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(plusDayDur, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessDayDur, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27))))))
        );
        panelDerecho3Layout.setVerticalGroup(
            panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerecho3Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62)
                .addGroup(panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lessDayDur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plusDayDur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DayDur, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDerecho3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lessDeadLine, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(plusDeadLine, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(DeadLineDays, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(271, Short.MAX_VALUE))
        );

        BG3.add(panelDerecho3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 120, 400, 600));

        javax.swing.GroupLayout ConfigPanelLayout = new javax.swing.GroupLayout(ConfigPanel);
        ConfigPanel.setLayout(ConfigPanelLayout);
        ConfigPanelLayout.setHorizontalGroup(
            ConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG3, javax.swing.GroupLayout.DEFAULT_SIZE, 1620, Short.MAX_VALUE)
        );
        ConfigPanelLayout.setVerticalGroup(
            ConfigPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ConfigPanelLayout.createSequentialGroup()
                .addComponent(BG3, javax.swing.GroupLayout.PREFERRED_SIZE, 729, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Configuration", ConfigPanel);

        BG4.setBackground(new java.awt.Color(5, 23, 46));
        BG4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SidePanel4.setBackground(new java.awt.Color(10, 44, 88));
        SidePanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_SquareEnix4.setBackground(new java.awt.Color(22, 95, 190));
        btn_SquareEnix4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SquareEnix4MouseClicked(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(255, 255, 255));
        jLabel34.setText("Square Enix stats ");
        jLabel34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel34MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_SquareEnix4Layout = new javax.swing.GroupLayout(btn_SquareEnix4);
        btn_SquareEnix4.setLayout(btn_SquareEnix4Layout);
        btn_SquareEnix4Layout.setHorizontalGroup(
            btn_SquareEnix4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_SquareEnix4Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel34)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        btn_SquareEnix4Layout.setVerticalGroup(
            btn_SquareEnix4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_SquareEnix4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel4.add(btn_SquareEnix4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 330, 70));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Unimet 2023 ®");
        SidePanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, -1, -1));

        jSeparator2.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SidePanel4.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 96, 260, 10));
        SidePanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 330, 300, 400));

        btn_Capcom1.setBackground(new java.awt.Color(22, 95, 190));
        btn_Capcom1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Capcom1MouseClicked(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(255, 255, 255));
        jLabel35.setText("Capcom STATS");
        jLabel35.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel35MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_Capcom1Layout = new javax.swing.GroupLayout(btn_Capcom1);
        btn_Capcom1.setLayout(btn_Capcom1Layout);
        btn_Capcom1Layout.setHorizontalGroup(
            btn_Capcom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Capcom1Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel35)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        btn_Capcom1Layout.setVerticalGroup(
            btn_Capcom1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_Capcom1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel4.add(btn_Capcom1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        btn_Configuration1.setBackground(new java.awt.Color(22, 95, 190));
        btn_Configuration1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Configuration1MouseClicked(evt);
            }
        });

        jLabel36.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setText("Configuration");
        jLabel36.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel36MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_Configuration1Layout = new javax.swing.GroupLayout(btn_Configuration1);
        btn_Configuration1.setLayout(btn_Configuration1Layout);
        btn_Configuration1Layout.setHorizontalGroup(
            btn_Configuration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Configuration1Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel36)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        btn_Configuration1Layout.setVerticalGroup(
            btn_Configuration1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_Configuration1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel4.add(btn_Configuration1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, -1));
        SidePanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 80));

        BG4.add(SidePanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 720));

        jPanel4.setBackground(new java.awt.Color(22, 95, 190));

        barrasuperior1.setBackground(new java.awt.Color(29, 121, 241));
        barrasuperior1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barrasuperior1MouseDragged(evt);
            }
        });
        barrasuperior1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barrasuperior1MousePressed(evt);
            }
        });

        exit1.setFont(new java.awt.Font("Alien Encounters", 1, 24)); // NOI18N
        exit1.setForeground(new java.awt.Color(255, 205, 16));
        exit1.setText("Exit");
        exit1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exit1MousePressed(evt);
            }
        });

        javax.swing.GroupLayout barrasuperior1Layout = new javax.swing.GroupLayout(barrasuperior1);
        barrasuperior1.setLayout(barrasuperior1Layout);
        barrasuperior1Layout.setHorizontalGroup(
            barrasuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barrasuperior1Layout.createSequentialGroup()
                .addContainerGap(1226, Short.MAX_VALUE)
                .addComponent(exit1)
                .addGap(20, 20, 20))
        );
        barrasuperior1Layout.setVerticalGroup(
            barrasuperior1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barrasuperior1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exit1, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );

        jLabel39.setBackground(new java.awt.Color(22, 95, 190));
        jLabel39.setFont(new java.awt.Font("Alien Encounters", 1, 48)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(255, 205, 16));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("capcom section");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barrasuperior1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(barrasuperior1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel39)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BG4.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 1300, 120));

        panelIzquierdo1.setBackground(new java.awt.Color(10, 44, 88));

        jLabel40.setFont(new java.awt.Font("Alien Encounters", 1, 20)); // NOI18N
        jLabel40.setForeground(new java.awt.Color(255, 255, 255));
        jLabel40.setText("employee management");

        jLabel41.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(255, 255, 255));
        jLabel41.setText("Developers qty :");

        jLabel42.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(255, 255, 255));
        jLabel42.setText("Narrative developer:");

        jLabel43.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(255, 255, 255));
        jLabel43.setText("Level designer:");

        jLabel44.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(255, 255, 255));
        jLabel44.setText("Sprite artist:");

        jLabel45.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(255, 255, 255));
        jLabel45.setText("Logic programmer :");

        jLabel46.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(255, 255, 255));
        jLabel46.setText("DLC Developer:");

        jLabel47.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("Director:");

        directorstateGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        directorstateGUI.setForeground(new java.awt.Color(255, 205, 16));
        directorstateGUI.setText("x");

        jLabel48.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(255, 255, 255));
        jLabel48.setText("Project Manager:");

        PMstateGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        PMstateGUI.setForeground(new java.awt.Color(255, 205, 16));
        PMstateGUI.setText("x");

        jLabel49.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel49.setForeground(new java.awt.Color(255, 255, 255));
        jLabel49.setText("Integrators:");

        jLabel50.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel50.setForeground(new java.awt.Color(255, 255, 255));
        jLabel50.setText("PM faults:");

        PMfaultsGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        PMfaultsGUI.setForeground(new java.awt.Color(255, 205, 16));
        PMfaultsGUI.setText("0");

        PMfaults$GUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        PMfaults$GUI.setForeground(new java.awt.Color(255, 205, 16));
        PMfaults$GUI.setText("0");

        jLabel51.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel51.setForeground(new java.awt.Color(255, 255, 255));
        jLabel51.setText("dolars");

        jLabel52.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(255, 205, 16));
        jLabel52.setText("Monitor");

        IntegratorQty2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        IntegratorQty2.setForeground(new java.awt.Color(255, 255, 255));
        IntegratorQty2.setText("0");

        plusIntegratorQty2.setText("-");
        plusIntegratorQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusIntegratorQty2ActionPerformed(evt);
            }
        });

        lessIntegratorQty2.setText("+");
        lessIntegratorQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessIntegratorQty2ActionPerformed(evt);
            }
        });

        lessDLCQty2.setText("+");
        lessDLCQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDLCQty2ActionPerformed(evt);
            }
        });

        plusDLCQty2.setText("-");
        plusDLCQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDLCQty2ActionPerformed(evt);
            }
        });

        DLCQty2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DLCQty2.setForeground(new java.awt.Color(255, 255, 255));
        DLCQty2.setText("0");

        LogicQty2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        LogicQty2.setForeground(new java.awt.Color(255, 255, 255));
        LogicQty2.setText("0");

        plusLogicQty2.setText("-");
        plusLogicQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLogicQty2ActionPerformed(evt);
            }
        });

        lessLogicQty2.setText("+");
        lessLogicQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessLogicQty2ActionPerformed(evt);
            }
        });

        lessSpriteQty2.setText("+");
        lessSpriteQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessSpriteQty2ActionPerformed(evt);
            }
        });

        plusSpriteQty2.setText("-");
        plusSpriteQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusSpriteQty2ActionPerformed(evt);
            }
        });

        SpriteQty2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        SpriteQty2.setForeground(new java.awt.Color(255, 255, 255));
        SpriteQty2.setText("0");

        LevelQty2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        LevelQty2.setForeground(new java.awt.Color(255, 255, 255));
        LevelQty2.setText("0");

        plusLevelQty2.setText("-");
        plusLevelQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLevelQty2ActionPerformed(evt);
            }
        });

        lessLevelQty2.setText("+");
        lessLevelQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessLevelQty2ActionPerformed(evt);
            }
        });

        lessNarrativeQty2.setText("+");
        lessNarrativeQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessNarrativeQty2ActionPerformed(evt);
            }
        });

        plusNarrativeQty2.setText("-");
        plusNarrativeQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusNarrativeQty2ActionPerformed(evt);
            }
        });

        NarrativeQty2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        NarrativeQty2.setForeground(new java.awt.Color(255, 255, 255));
        NarrativeQty2.setText("0");

        DevQty2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DevQty2.setForeground(new java.awt.Color(255, 255, 255));
        DevQty2.setText("0");

        plusDevQty2.setText("+");
        plusDevQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDevQty2ActionPerformed(evt);
            }
        });

        lessDevQty2.setText("-");
        lessDevQty2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDevQty2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelIzquierdo1Layout = new javax.swing.GroupLayout(panelIzquierdo1);
        panelIzquierdo1.setLayout(panelIzquierdo1Layout);
        panelIzquierdo1Layout.setHorizontalGroup(
            panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzquierdo1Layout.createSequentialGroup()
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                .addComponent(DevQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(plusDevQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessDevQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                        .addComponent(IntegratorQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusIntegratorQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessIntegratorQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                        .addComponent(LevelQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusLevelQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessLevelQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                        .addComponent(LogicQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusLogicQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessLogicQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                        .addComponent(SpriteQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusSpriteQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessSpriteQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                        .addComponent(NarrativeQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusNarrativeQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessNarrativeQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                        .addComponent(DLCQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusDLCQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessDLCQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(7, 7, 7))
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel40)
                                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                        .addComponent(jLabel41)
                                        .addGap(143, 143, 143)))
                                .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(directorstateGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(PMfaultsGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(PMfaults$GUI, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel51)))
                            .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                                .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PMstateGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelIzquierdo1Layout.setVerticalGroup(
            panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                        .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel49, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdo1Layout.createSequentialGroup()
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessDevQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusDevQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DevQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessNarrativeQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusNarrativeQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NarrativeQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessLevelQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusLevelQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LevelQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessSpriteQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusSpriteQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpriteQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessLogicQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusLogicQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LogicQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessDLCQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusDLCQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DLCQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessIntegratorQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusIntegratorQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IntegratorQty2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directorstateGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PMstateGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelIzquierdo1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PMfaultsGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PMfaults$GUI, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        BG4.add(panelIzquierdo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 400, 600));

        panelCentro.setBackground(new java.awt.Color(10, 44, 88));

        jLabel53.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(255, 255, 255));
        jLabel53.setText("Production");

        levelqtyGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        levelqtyGUI.setForeground(new java.awt.Color(255, 255, 255));
        levelqtyGUI.setText("x");

        jLabel54.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("Days passed");

        jLabel55.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel55.setForeground(new java.awt.Color(255, 255, 255));
        jLabel55.setText("Next Deadline");

        DeadlineGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DeadlineGUI.setForeground(new java.awt.Color(255, 255, 255));
        DeadlineGUI.setText("0");

        jLabel56.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel56.setForeground(new java.awt.Color(255, 205, 16));
        jLabel56.setText("max qty");

        jLabel57.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setText("Sprite artist:");

        jLabel58.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setText("Logic programmer :");

        jLabel59.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("Narrative developer:");

        jLabel60.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setText("DLC Developer:");

        jLabel61.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setText("Level designer:");

        dayspassedGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        dayspassedGUI1.setForeground(new java.awt.Color(255, 255, 255));
        dayspassedGUI1.setText("0");

        logicqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        logicqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        logicqtyGUI1.setText("x");

        spriteqtyGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        spriteqtyGUI.setForeground(new java.awt.Color(255, 255, 255));
        spriteqtyGUI.setText("x");
        spriteqtyGUI.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                spriteqtyGUIPropertyChange(evt);
            }
        });

        dlcqtyGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        dlcqtyGUI.setForeground(new java.awt.Color(255, 255, 255));
        dlcqtyGUI.setText("x");

        narrativeqtyGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        narrativeqtyGUI.setForeground(new java.awt.Color(255, 255, 255));
        narrativeqtyGUI.setText("x");

        jLabel62.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 205, 16));
        jLabel62.setText("Game parts");

        logicMAXqtyGUI2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        logicMAXqtyGUI2.setForeground(new java.awt.Color(255, 255, 255));
        logicMAXqtyGUI2.setText("x");

        dlcMAXqtyGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        dlcMAXqtyGUI.setForeground(new java.awt.Color(255, 255, 255));
        dlcMAXqtyGUI.setText("x");

        spriteMAXqtyGUI3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        spriteMAXqtyGUI3.setForeground(new java.awt.Color(255, 255, 255));
        spriteMAXqtyGUI3.setText("x");

        levelMAXqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        levelMAXqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        levelMAXqtyGUI1.setText("x");

        narrativeMAXqtyGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        narrativeMAXqtyGUI.setForeground(new java.awt.Color(255, 255, 255));
        narrativeMAXqtyGUI.setText("25");

        jLabel63.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("DLC game:");

        readydlcGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        readydlcGUI.setForeground(new java.awt.Color(255, 255, 255));
        readydlcGUI.setText("0");

        jLabel64.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 255, 255));
        jLabel64.setText("normal game:");

        readygamesqtyGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        readygamesqtyGUI.setForeground(new java.awt.Color(255, 255, 255));
        readygamesqtyGUI.setText("0");

        jLabel65.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 205, 16));
        jLabel65.setText("Redy for sale");

        javax.swing.GroupLayout panelCentroLayout = new javax.swing.GroupLayout(panelCentro);
        panelCentro.setLayout(panelCentroLayout);
        panelCentroLayout.setHorizontalGroup(
            panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentroLayout.createSequentialGroup()
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCentroLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentroLayout.createSequentialGroup()
                                .addGap(0, 1, Short.MAX_VALUE)
                                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addComponent(jLabel58)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(logicqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addComponent(jLabel57)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spriteqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addComponent(jLabel60)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dlcqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addComponent(jLabel63)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readydlcGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addComponent(jLabel64)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readygamesqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel65, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(136, 136, 136))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentroLayout.createSequentialGroup()
                                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel59)
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel56))
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(narrativeqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(spriteMAXqtyGUI3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                                    .addComponent(logicMAXqtyGUI2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(dlcMAXqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(levelMAXqtyGUI1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(narrativeMAXqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3))))
                            .addGroup(panelCentroLayout.createSequentialGroup()
                                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addComponent(jLabel61)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(levelqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addComponent(jLabel55)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DeadlineGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentroLayout.createSequentialGroup()
                                        .addComponent(jLabel54)
                                        .addGap(18, 18, 18)
                                        .addComponent(dayspassedGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelCentroLayout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        panelCentroLayout.setVerticalGroup(
            panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentroLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayspassedGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeadlineGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(narrativeqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(narrativeMAXqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(levelMAXqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(levelqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spriteqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spriteMAXqtyGUI3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logicqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logicMAXqtyGUI2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dlcqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dlcMAXqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readydlcGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readygamesqtyGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        BG4.add(panelCentro, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 400, 600));

        panelDerecho1.setBackground(new java.awt.Color(10, 44, 88));

        jLabel66.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 255, 255));
        jLabel66.setText("stats");

        jLabel67.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 205, 16));
        jLabel67.setText("Salaries");

        totalsalariesGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        totalsalariesGUI.setForeground(new java.awt.Color(255, 255, 255));
        totalsalariesGUI.setText("0");

        jLabel68.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 205, 16));
        jLabel68.setText("Utility");

        utilityGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        utilityGUI.setForeground(new java.awt.Color(255, 255, 255));
        utilityGUI.setText("0");

        jLabel104.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel104.setForeground(new java.awt.Color(255, 205, 16));
        jLabel104.setText("Raw income:");

        incomeGUI.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        incomeGUI.setForeground(new java.awt.Color(255, 255, 255));
        incomeGUI.setText("0");

        javax.swing.GroupLayout panelDerecho1Layout = new javax.swing.GroupLayout(panelDerecho1);
        panelDerecho1.setLayout(panelDerecho1Layout);
        panelDerecho1Layout.setHorizontalGroup(
            panelDerecho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerecho1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelDerecho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelDerecho1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerecho1Layout.createSequentialGroup()
                        .addComponent(jLabel67)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalsalariesGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerecho1Layout.createSequentialGroup()
                        .addComponent(jLabel104, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(incomeGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerecho1Layout.createSequentialGroup()
                        .addComponent(jLabel68)
                        .addGap(18, 18, 18)
                        .addComponent(utilityGUI, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        panelDerecho1Layout.setVerticalGroup(
            panelDerecho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerecho1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDerecho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalsalariesGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelDerecho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(utilityGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDerecho1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel104, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incomeGUI, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(393, Short.MAX_VALUE))
        );

        BG4.add(panelDerecho1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 120, 400, -1));

        javax.swing.GroupLayout CapcomPanelLayout = new javax.swing.GroupLayout(CapcomPanel);
        CapcomPanel.setLayout(CapcomPanelLayout);
        CapcomPanelLayout.setHorizontalGroup(
            CapcomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        CapcomPanelLayout.setVerticalGroup(
            CapcomPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG4, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Capcom Dashboard", CapcomPanel);

        BG5.setBackground(new java.awt.Color(24, 3, 6));
        BG5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        SidePanel5.setBackground(new java.awt.Color(76, 12, 14));
        SidePanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_SquareEnix5.setBackground(new java.awt.Color(153, 25, 30));
        btn_SquareEnix5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_SquareEnix5MouseClicked(evt);
            }
        });

        jLabel69.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 255, 255));
        jLabel69.setText("Square Enix stats ");
        jLabel69.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel69MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_SquareEnix5Layout = new javax.swing.GroupLayout(btn_SquareEnix5);
        btn_SquareEnix5.setLayout(btn_SquareEnix5Layout);
        btn_SquareEnix5Layout.setHorizontalGroup(
            btn_SquareEnix5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_SquareEnix5Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel69)
                .addContainerGap(47, Short.MAX_VALUE))
        );
        btn_SquareEnix5Layout.setVerticalGroup(
            btn_SquareEnix5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_SquareEnix5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel69, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel5.add(btn_SquareEnix5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 330, 70));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Unimet 2023 ®");
        SidePanel5.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 690, -1, -1));

        jSeparator3.setBackground(new java.awt.Color(255, 255, 255));
        jSeparator3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        SidePanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 96, 260, 10));
        SidePanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 290, 290));

        btn_Capcom2.setBackground(new java.awt.Color(153, 25, 30));
        btn_Capcom2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_Capcom2MouseClicked(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 255, 255));
        jLabel70.setText("Capcom STATS");
        jLabel70.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel70MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_Capcom2Layout = new javax.swing.GroupLayout(btn_Capcom2);
        btn_Capcom2.setLayout(btn_Capcom2Layout);
        btn_Capcom2Layout.setHorizontalGroup(
            btn_Capcom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_Capcom2Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel70)
                .addContainerGap(88, Short.MAX_VALUE))
        );
        btn_Capcom2Layout.setVerticalGroup(
            btn_Capcom2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_Capcom2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel5.add(btn_Capcom2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        btn_configuration.setBackground(new java.awt.Color(153, 25, 30));
        btn_configuration.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_configurationMouseClicked(evt);
            }
        });

        jLabel71.setFont(new java.awt.Font("Alien Encounters", 0, 24)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("Configuration");
        jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel71MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btn_configurationLayout = new javax.swing.GroupLayout(btn_configuration);
        btn_configuration.setLayout(btn_configurationLayout);
        btn_configurationLayout.setHorizontalGroup(
            btn_configurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(btn_configurationLayout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel71)
                .addContainerGap(85, Short.MAX_VALUE))
        );
        btn_configurationLayout.setVerticalGroup(
            btn_configurationLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, btn_configurationLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                .addContainerGap())
        );

        SidePanel5.add(btn_configuration, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, -1, -1));
        SidePanel5.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, 80));

        jPanel6.setBackground(new java.awt.Color(153, 25, 30));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
        );

        SidePanel5.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 610, 300, 110));

        BG5.add(SidePanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 720));

        jPanel7.setBackground(new java.awt.Color(153, 25, 30));

        barrasuperior2.setBackground(new java.awt.Color(204, 34, 41));
        barrasuperior2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                barrasuperior2MouseDragged(evt);
            }
        });
        barrasuperior2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                barrasuperior2MousePressed(evt);
            }
        });

        exit2.setFont(new java.awt.Font("Alien Encounters", 1, 24)); // NOI18N
        exit2.setForeground(new java.awt.Color(255, 205, 16));
        exit2.setText("Exit");
        exit2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                exit2MousePressed(evt);
            }
        });

        javax.swing.GroupLayout barrasuperior2Layout = new javax.swing.GroupLayout(barrasuperior2);
        barrasuperior2.setLayout(barrasuperior2Layout);
        barrasuperior2Layout.setHorizontalGroup(
            barrasuperior2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, barrasuperior2Layout.createSequentialGroup()
                .addContainerGap(1226, Short.MAX_VALUE)
                .addComponent(exit2)
                .addGap(20, 20, 20))
        );
        barrasuperior2Layout.setVerticalGroup(
            barrasuperior2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barrasuperior2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exit2, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
        );

        jLabel72.setBackground(new java.awt.Color(84, 131, 176));
        jLabel72.setFont(new java.awt.Font("Alien Encounters", 1, 48)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(121, 171, 255));
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("Square Enix section");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(barrasuperior2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel72, javax.swing.GroupLayout.PREFERRED_SIZE, 539, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(316, 316, 316))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(barrasuperior2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel72)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BG5.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 1300, 120));

        panelIzquierdo2.setBackground(new java.awt.Color(76, 12, 14));

        jLabel73.setFont(new java.awt.Font("Alien Encounters", 1, 20)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 255, 255));
        jLabel73.setText("employee management");

        jLabel74.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("Developers qty :");

        jLabel75.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("Narrative developer:");

        jLabel76.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Level designer:");

        jLabel77.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setText("Sprite artist:");

        jLabel78.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Logic programmer :");

        jLabel79.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("DLC Developer:");

        jLabel80.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Director:");

        directorstateGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        directorstateGUI1.setForeground(new java.awt.Color(121, 171, 255));
        directorstateGUI1.setText("x");

        jLabel81.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Project Manager:");

        PMstateGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        PMstateGUI1.setForeground(new java.awt.Color(121, 171, 255));
        PMstateGUI1.setText("x");

        jLabel82.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("Integrators:");

        jLabel83.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("PM faults:");

        PMfaultsGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        PMfaultsGUI1.setForeground(new java.awt.Color(121, 171, 255));
        PMfaultsGUI1.setText("x");

        PMfaults$GUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        PMfaults$GUI1.setForeground(new java.awt.Color(121, 171, 255));
        PMfaults$GUI1.setText("x");

        jLabel84.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(255, 255, 255));
        jLabel84.setText("dolars");

        jLabel85.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(121, 171, 255));
        jLabel85.setText("Monitor");

        IntegratorQty3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        IntegratorQty3.setForeground(new java.awt.Color(255, 255, 255));
        IntegratorQty3.setText("X");

        plusIntegratorQty3.setText("+");
        plusIntegratorQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusIntegratorQty3ActionPerformed(evt);
            }
        });

        lessIntegratorQty3.setText("+");
        lessIntegratorQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessIntegratorQty3ActionPerformed(evt);
            }
        });

        lessDLCQty3.setText("+");
        lessDLCQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDLCQty3ActionPerformed(evt);
            }
        });

        plusDLCQty3.setText("-");
        plusDLCQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDLCQty3ActionPerformed(evt);
            }
        });

        DLCQty3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DLCQty3.setForeground(new java.awt.Color(255, 255, 255));
        DLCQty3.setText("X");

        LogicQty3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        LogicQty3.setForeground(new java.awt.Color(255, 255, 255));
        LogicQty3.setText("X");

        plusLogicQty3.setText("-");
        plusLogicQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLogicQty3ActionPerformed(evt);
            }
        });

        lessLogicQty3.setText("+");
        lessLogicQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessLogicQty3ActionPerformed(evt);
            }
        });

        lessSpriteQty3.setText("+");
        lessSpriteQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessSpriteQty3ActionPerformed(evt);
            }
        });

        plusSpriteQty3.setText("-");
        plusSpriteQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusSpriteQty3ActionPerformed(evt);
            }
        });

        SpriteQty3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        SpriteQty3.setForeground(new java.awt.Color(255, 255, 255));
        SpriteQty3.setText("X");

        LevelQty3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        LevelQty3.setForeground(new java.awt.Color(255, 255, 255));
        LevelQty3.setText("X");

        plusLevelQty3.setText("-");
        plusLevelQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusLevelQty3ActionPerformed(evt);
            }
        });

        lessLevelQty3.setText("+");
        lessLevelQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessLevelQty3ActionPerformed(evt);
            }
        });

        lessNarrativeQty3.setText("+");
        lessNarrativeQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessNarrativeQty3ActionPerformed(evt);
            }
        });

        plusNarrativeQty3.setText("-");
        plusNarrativeQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusNarrativeQty3ActionPerformed(evt);
            }
        });

        NarrativeQty3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        NarrativeQty3.setForeground(new java.awt.Color(255, 255, 255));
        NarrativeQty3.setText("X");

        DevQty3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DevQty3.setForeground(new java.awt.Color(255, 255, 255));
        DevQty3.setText("0");

        plusDevQty3.setText("-");
        plusDevQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plusDevQty3ActionPerformed(evt);
            }
        });

        lessDevQty3.setText("+");
        lessDevQty3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lessDevQty3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelIzquierdo2Layout = new javax.swing.GroupLayout(panelIzquierdo2);
        panelIzquierdo2.setLayout(panelIzquierdo2Layout);
        panelIzquierdo2Layout.setHorizontalGroup(
            panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzquierdo2Layout.createSequentialGroup()
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                .addComponent(DevQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(plusDevQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lessDevQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                        .addComponent(IntegratorQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusIntegratorQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessIntegratorQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                        .addComponent(LevelQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusLevelQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessLevelQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                        .addComponent(LogicQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusLogicQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessLogicQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                        .addComponent(SpriteQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusSpriteQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessSpriteQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                        .addComponent(NarrativeQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusNarrativeQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessNarrativeQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                        .addComponent(DLCQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(plusDLCQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lessDLCQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(7, 7, 7))
                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PMfaultsGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(PMfaults$GUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel84))
                            .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel73)
                                .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                                    .addComponent(jLabel74)
                                    .addGap(143, 143, 143))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel80, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel81, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(directorstateGUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(PMstateGUI1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel85, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelIzquierdo2Layout.setVerticalGroup(
            panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel73, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                        .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzquierdo2Layout.createSequentialGroup()
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessDevQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusDevQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DevQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessNarrativeQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusNarrativeQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NarrativeQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessLevelQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusLevelQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LevelQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessSpriteQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusSpriteQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SpriteQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessLogicQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusLogicQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LogicQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessDLCQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusDLCQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DLCQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lessIntegratorQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(plusIntegratorQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IntegratorQty3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(directorstateGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PMstateGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelIzquierdo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(PMfaultsGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PMfaults$GUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        BG5.add(panelIzquierdo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 120, 400, 600));

        panelCentro1.setBackground(new java.awt.Color(76, 12, 14));

        jLabel86.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel86.setForeground(new java.awt.Color(255, 255, 255));
        jLabel86.setText("Production");

        levelqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        levelqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        levelqtyGUI1.setText("x");

        jLabel87.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(255, 255, 255));
        jLabel87.setText("Days passed");

        jLabel88.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel88.setForeground(new java.awt.Color(255, 255, 255));
        jLabel88.setText("Next Deadline");

        DeadlineGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        DeadlineGUI1.setForeground(new java.awt.Color(255, 255, 255));
        DeadlineGUI1.setText("0");

        jLabel89.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(121, 171, 255));
        jLabel89.setText("max qty");

        jLabel90.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Sprite artist:");

        jLabel91.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Logic programmer :");

        jLabel92.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Narrative developer:");

        jLabel93.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 255, 255));
        jLabel93.setText("DLC Developer:");

        jLabel94.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(255, 255, 255));
        jLabel94.setText("Level designer:");

        dayspassedGUI2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        dayspassedGUI2.setForeground(new java.awt.Color(255, 255, 255));
        dayspassedGUI2.setText("0");

        logicqtyGUI2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        logicqtyGUI2.setForeground(new java.awt.Color(255, 255, 255));
        logicqtyGUI2.setText("x");

        spriteqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        spriteqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        spriteqtyGUI1.setText("x");

        dlcqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        dlcqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        dlcqtyGUI1.setText("x");

        narrativeqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        narrativeqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        narrativeqtyGUI1.setText("x");

        jLabel95.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(121, 171, 255));
        jLabel95.setText("Game parts");

        logicMAXqtyGUI3.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        logicMAXqtyGUI3.setForeground(new java.awt.Color(255, 255, 255));
        logicMAXqtyGUI3.setText("x");

        dlcMAXqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        dlcMAXqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        dlcMAXqtyGUI1.setText("x");

        spriteMAXqtyGUI4.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        spriteMAXqtyGUI4.setForeground(new java.awt.Color(255, 255, 255));
        spriteMAXqtyGUI4.setText("x");

        levelMAXqtyGUI2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        levelMAXqtyGUI2.setForeground(new java.awt.Color(255, 255, 255));
        levelMAXqtyGUI2.setText("x");

        narrativeMAXqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        narrativeMAXqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        narrativeMAXqtyGUI1.setText("x");

        jLabel96.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("DLC game:");

        readydlcGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        readydlcGUI1.setForeground(new java.awt.Color(255, 255, 255));
        readydlcGUI1.setText("x");

        jLabel97.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        jLabel97.setForeground(new java.awt.Color(255, 255, 255));
        jLabel97.setText("normal game:");

        readygamesqtyGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        readygamesqtyGUI1.setForeground(new java.awt.Color(255, 255, 255));
        readygamesqtyGUI1.setText("x");

        jLabel98.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel98.setForeground(new java.awt.Color(121, 171, 255));
        jLabel98.setText("Redy for sale");

        javax.swing.GroupLayout panelCentro1Layout = new javax.swing.GroupLayout(panelCentro1);
        panelCentro1.setLayout(panelCentro1Layout);
        panelCentro1Layout.setHorizontalGroup(
            panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentro1Layout.createSequentialGroup()
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCentro1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentro1Layout.createSequentialGroup()
                                .addGap(0, 11, Short.MAX_VALUE)
                                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addComponent(jLabel91)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(logicqtyGUI2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addComponent(jLabel90)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spriteqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addComponent(jLabel93)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(dlcqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addComponent(jLabel96)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readydlcGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addComponent(jLabel97)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(readygamesqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel98, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                                .addGap(136, 136, 136))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCentro1Layout.createSequentialGroup()
                                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel92)
                                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel89))
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(narrativeqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                        .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(spriteMAXqtyGUI4, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                                    .addComponent(logicMAXqtyGUI3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addComponent(dlcMAXqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(levelMAXqtyGUI2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(narrativeMAXqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(3, 3, 3))))
                            .addGroup(panelCentro1Layout.createSequentialGroup()
                                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addComponent(jLabel94)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(levelqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addComponent(jLabel88)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(DeadlineGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelCentro1Layout.createSequentialGroup()
                                        .addComponent(jLabel87)
                                        .addGap(18, 18, 18)
                                        .addComponent(dayspassedGUI2, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(panelCentro1Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14))
        );
        panelCentro1Layout.setVerticalGroup(
            panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCentro1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayspassedGUI2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(DeadlineGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel95, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(narrativeqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(narrativeMAXqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(levelMAXqtyGUI2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(levelqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spriteqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spriteMAXqtyGUI4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logicqtyGUI2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logicMAXqtyGUI3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dlcqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dlcMAXqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel96, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readydlcGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCentro1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(readygamesqtyGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        BG5.add(panelCentro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, 400, 600));

        panelDerecho2.setBackground(new java.awt.Color(76, 12, 14));

        jLabel99.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("stats");

        jLabel100.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(121, 171, 255));
        jLabel100.setText("total salaries:");

        totalsalariesGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        totalsalariesGUI1.setForeground(new java.awt.Color(255, 255, 255));
        totalsalariesGUI1.setText("0");

        jLabel101.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(121, 171, 255));
        jLabel101.setText("utility:");

        incomeGUI1.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        incomeGUI1.setForeground(new java.awt.Color(255, 255, 255));
        incomeGUI1.setText("0");

        jLabel105.setFont(new java.awt.Font("Alien Encounters", 1, 23)); // NOI18N
        jLabel105.setForeground(new java.awt.Color(121, 171, 255));
        jLabel105.setText("Raw income:");

        utilityGUI2.setFont(new java.awt.Font("Alien Encounters", 1, 18)); // NOI18N
        utilityGUI2.setForeground(new java.awt.Color(255, 255, 255));
        utilityGUI2.setText("0");

        javax.swing.GroupLayout panelDerecho2Layout = new javax.swing.GroupLayout(panelDerecho2);
        panelDerecho2.setLayout(panelDerecho2Layout);
        panelDerecho2Layout.setHorizontalGroup(
            panelDerecho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerecho2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(panelDerecho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelDerecho2Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerecho2Layout.createSequentialGroup()
                        .addComponent(jLabel101)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(utilityGUI2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerecho2Layout.createSequentialGroup()
                        .addComponent(jLabel100)
                        .addGap(18, 18, 18)
                        .addComponent(totalsalariesGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelDerecho2Layout.createSequentialGroup()
                        .addComponent(jLabel105)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(incomeGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(145, Short.MAX_VALUE))
        );
        panelDerecho2Layout.setVerticalGroup(
            panelDerecho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDerecho2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDerecho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalsalariesGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(panelDerecho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(utilityGUI2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(panelDerecho2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel105, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(incomeGUI1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(381, Short.MAX_VALUE))
        );

        BG5.add(panelDerecho2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 120, 400, -1));

        javax.swing.GroupLayout SquareEnixPanelLayout = new javax.swing.GroupLayout(SquareEnixPanel);
        SquareEnixPanel.setLayout(SquareEnixPanelLayout);
        SquareEnixPanelLayout.setHorizontalGroup(
            SquareEnixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG5, javax.swing.GroupLayout.DEFAULT_SIZE, 1620, Short.MAX_VALUE)
        );
        SquareEnixPanelLayout.setVerticalGroup(
            SquareEnixPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(BG5, javax.swing.GroupLayout.DEFAULT_SIZE, 729, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Square Enix Dasboard", SquareEnixPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
        SquareEnix v = new SquareEnix();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void btn_SquareEnix3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SquareEnix3MouseClicked
        // TODO add your handling code here:
        SquareEnix v = new SquareEnix();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_SquareEnix3MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
        Capcom v = new Capcom();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel14MouseClicked

    private void btn_CapcomMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CapcomMouseClicked
        // TODO add your handling code here:
        Capcom v = new Capcom();
        v.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btn_CapcomMouseClicked

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void btn_ConfigurationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ConfigurationMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_ConfigurationMouseClicked

    private void exitMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMousePressed

    private void barrasuperiorMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barrasuperiorMouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_barrasuperiorMouseDragged

    private void barrasuperiorMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barrasuperiorMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_barrasuperiorMousePressed

    private void jLabel34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel34MouseClicked
        // TODO add your handling code here:
        SquareEnix v = new SquareEnix();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel34MouseClicked

    private void btn_SquareEnix4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SquareEnix4MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_SquareEnix4MouseClicked

    private void jLabel35MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel35MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel35MouseClicked

    private void btn_Capcom1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Capcom1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_Capcom1MouseClicked

    private void jLabel36MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel36MouseClicked
        // TODO add your handling code here:
        Configuration v = new Configuration();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel36MouseClicked

    private void btn_Configuration1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Configuration1MouseClicked
        // TODO add your handling code here:
        Configuration v = new Configuration();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_Configuration1MouseClicked

    private void exit1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit1MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exit1MousePressed

    private void barrasuperior1MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barrasuperior1MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_barrasuperior1MouseDragged

    private void barrasuperior1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barrasuperior1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_barrasuperior1MousePressed

    private void jLabel69MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel69MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel69MouseClicked

    private void btn_SquareEnix5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_SquareEnix5MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btn_SquareEnix5MouseClicked

    private void jLabel70MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel70MouseClicked
        // TODO add your handling code here:
        Capcom v = new Capcom();
        v.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel70MouseClicked

    private void btn_Capcom2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_Capcom2MouseClicked
        // TODO add your handling code here:
        Capcom v = new Capcom();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_Capcom2MouseClicked

    private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseClicked
        // TODO add your handling code here:
        Configuration v = new Configuration();
        v.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel71MouseClicked

    private void btn_configurationMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_configurationMouseClicked
        // TODO add your handling code here:
        Configuration v = new Configuration();
        v.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_btn_configurationMouseClicked

    private void exit2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exit2MousePressed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exit2MousePressed

    private void barrasuperior2MouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barrasuperior2MouseDragged
        // TODO add your handling code here:
    }//GEN-LAST:event_barrasuperior2MouseDragged

    private void barrasuperior2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_barrasuperior2MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_barrasuperior2MousePressed

    private void spriteqtyGUIPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_spriteqtyGUIPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_spriteqtyGUIPropertyChange

    private void lessDevQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDevQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessDevQtyActionPerformed

    private void plusDevQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDevQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusDevQtyActionPerformed

    private void plusLevelQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLevelQtyActionPerformed
        if(this.capcom.getLevelDevs().getlSize() > 1){
            this.capcom.removeDev("LevelDesign");
        }
    }//GEN-LAST:event_plusLevelQtyActionPerformed

    private void lessLevelQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessLevelQtyActionPerformed
        GameDeveloper capDev = new GameDeveloper("LevelDesign", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessLevelQtyActionPerformed

    private void plusLogicQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLogicQtyActionPerformed
        if(this.capcom.getLogicDevs().getlSize() > 1){
            this.capcom.removeDev("GameLogic");
        }
    }//GEN-LAST:event_plusLogicQtyActionPerformed

    private void lessLogicQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessLogicQtyActionPerformed
        GameDeveloper capDev = new GameDeveloper("GameLogic", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessLogicQtyActionPerformed

    private void plusSpriteQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusSpriteQtyActionPerformed
        if(this.capcom.getSpriteDevs().getlSize() > 1){
            this.capcom.removeDev("SpriteArt");
        }
    }//GEN-LAST:event_plusSpriteQtyActionPerformed

    private void lessSpriteQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessSpriteQtyActionPerformed
        GameDeveloper capDev = new GameDeveloper("SpriteArt", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessSpriteQtyActionPerformed

    private void plusNarrativeQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusNarrativeQtyActionPerformed
        if(this.capcom.getScriptDevs().getlSize() > 1){
            this.capcom.removeDev("Narrative");
        }
    }//GEN-LAST:event_plusNarrativeQtyActionPerformed

    private void lessNarrativeQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessNarrativeQtyActionPerformed
        GameDeveloper capDev = new GameDeveloper("Narrative", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessNarrativeQtyActionPerformed

    private void plusDLCQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDLCQtyActionPerformed
        if(this.capcom.getDlcDevs().getlSize() > 1){
            this.capcom.removeDev("DLC");
        }
    }//GEN-LAST:event_plusDLCQtyActionPerformed

    private void lessDLCQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDLCQtyActionPerformed
        GameDeveloper capDev = new GameDeveloper("DLC", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessDLCQtyActionPerformed

    private void plusIntegratorQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusIntegratorQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusIntegratorQtyActionPerformed

    private void lessIntegratorQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessIntegratorQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessIntegratorQtyActionPerformed

    private void plusDayDurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDayDurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusDayDurActionPerformed

    private void lessDayDurActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDayDurActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessDayDurActionPerformed

    private void plusDeadLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDeadLineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusDeadLineActionPerformed

    private void lessDeadLineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDeadLineActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessDeadLineActionPerformed

    private void plusIntegratorQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusIntegratorQty1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusIntegratorQty1ActionPerformed

    private void lessIntegratorQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessIntegratorQty1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessIntegratorQty1ActionPerformed

    private void lessDLCQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDLCQty1ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("DLC", "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessDLCQty1ActionPerformed

    private void plusDLCQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDLCQty1ActionPerformed
        if(this.squareEnix.getDlcDevs().getlSize() > 1){
            this.squareEnix.removeDev("DLC");
        }
    }//GEN-LAST:event_plusDLCQty1ActionPerformed

    private void plusLogicQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLogicQty1ActionPerformed
        if(this.squareEnix.getLogicDevs().getlSize() > 1){
            this.squareEnix.removeDev("GameLogic");
        }
    }//GEN-LAST:event_plusLogicQty1ActionPerformed

    private void lessLogicQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessLogicQty1ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("GameLogic", "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessLogicQty1ActionPerformed

    private void lessSpriteQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessSpriteQty1ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("SpriteArt", "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessSpriteQty1ActionPerformed

    private void plusSpriteQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusSpriteQty1ActionPerformed
        if(this.squareEnix.getSpriteDevs().getlSize() > 1){
            this.squareEnix.removeDev("SpriteArt");
        }
    }//GEN-LAST:event_plusSpriteQty1ActionPerformed

    private void plusLevelQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLevelQty1ActionPerformed
        if(this.squareEnix.getLevelDevs().getlSize() > 1){
            this.squareEnix.removeDev("LevelDesign");
        }
    }//GEN-LAST:event_plusLevelQty1ActionPerformed

    private void lessLevelQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessLevelQty1ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("LevelDesign", "LevelDesign", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessLevelQty1ActionPerformed

    private void lessNarrativeQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessNarrativeQty1ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("Narrative", "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessNarrativeQty1ActionPerformed

    private void plusNarrativeQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusNarrativeQty1ActionPerformed
        if(this.squareEnix.getScriptDevs().getlSize() > 1){
            this.squareEnix.removeDev("Narrative");
        }
    }//GEN-LAST:event_plusNarrativeQty1ActionPerformed

    private void plusDevQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDevQty1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusDevQty1ActionPerformed

    private void lessDevQty1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDevQty1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessDevQty1ActionPerformed

    private void plusIntegratorQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusIntegratorQty2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusIntegratorQty2ActionPerformed

    private void lessIntegratorQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessIntegratorQty2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessIntegratorQty2ActionPerformed

    private void lessDLCQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDLCQty2ActionPerformed
        GameDeveloper capDev = new GameDeveloper("DLC", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessDLCQty2ActionPerformed

    private void plusDLCQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDLCQty2ActionPerformed
        if(this.capcom.getDlcDevs().getlSize() > 1){
            this.capcom.removeDev("DLC");
        }
    }//GEN-LAST:event_plusDLCQty2ActionPerformed

    private void plusLogicQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLogicQty2ActionPerformed
        if(this.capcom.getLogicDevs().getlSize() > 1){
            this.capcom.removeDev("GameLogic");
        }
    }//GEN-LAST:event_plusLogicQty2ActionPerformed

    private void lessLogicQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessLogicQty2ActionPerformed
        GameDeveloper capDev = new GameDeveloper("GameLogic", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessLogicQty2ActionPerformed

    private void lessSpriteQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessSpriteQty2ActionPerformed
        GameDeveloper capDev = new GameDeveloper("SpriteArt", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessSpriteQty2ActionPerformed

    private void plusSpriteQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusSpriteQty2ActionPerformed
        if(this.capcom.getSpriteDevs().getlSize() > 1){
            this.capcom.removeDev("SpriteArt");
        }
    }//GEN-LAST:event_plusSpriteQty2ActionPerformed

    private void plusLevelQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLevelQty2ActionPerformed
        if(this.capcom.getLevelDevs().getlSize() > 1){
            this.capcom.removeDev("LevelDesign");
        }
    }//GEN-LAST:event_plusLevelQty2ActionPerformed

    private void lessLevelQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessLevelQty2ActionPerformed
        GameDeveloper capDev = new GameDeveloper("LevelDesign", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessLevelQty2ActionPerformed

    private void lessNarrativeQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessNarrativeQty2ActionPerformed
        GameDeveloper capDev = new GameDeveloper("Narrative", "Capcom", dayDuration, capcom.getCompanyDrive(), capcom.getMutex());
        capcom.addDev(capDev);
    }//GEN-LAST:event_lessNarrativeQty2ActionPerformed

    private void plusNarrativeQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusNarrativeQty2ActionPerformed
        if(this.capcom.getScriptDevs().getlSize() > 1){
            this.capcom.removeDev("Narrative");
        }
    }//GEN-LAST:event_plusNarrativeQty2ActionPerformed

    private void plusDevQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDevQty2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusDevQty2ActionPerformed

    private void lessDevQty2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDevQty2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessDevQty2ActionPerformed

    private void plusIntegratorQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusIntegratorQty3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusIntegratorQty3ActionPerformed

    private void lessIntegratorQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessIntegratorQty3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessIntegratorQty3ActionPerformed

    private void lessDLCQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDLCQty3ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("DLC", "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessDLCQty3ActionPerformed

    private void plusDLCQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDLCQty3ActionPerformed
        if(this.squareEnix.getDlcDevs().getlSize() > 1){
            this.squareEnix.removeDev("DLC");
        }
    }//GEN-LAST:event_plusDLCQty3ActionPerformed

    private void plusLogicQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLogicQty3ActionPerformed
        if(this.squareEnix.getLogicDevs().getlSize() > 1){
            this.squareEnix.removeDev("GameLogic");
        }
    }//GEN-LAST:event_plusLogicQty3ActionPerformed

    private void lessLogicQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessLogicQty3ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("GameLogic", "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessLogicQty3ActionPerformed

    private void lessSpriteQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessSpriteQty3ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("SpriteArt", "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessSpriteQty3ActionPerformed

    private void plusSpriteQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusSpriteQty3ActionPerformed
        if(this.squareEnix.getSpriteDevs().getlSize() > 1){
            this.squareEnix.removeDev("SpriteArt");
        }
    }//GEN-LAST:event_plusSpriteQty3ActionPerformed

    private void plusLevelQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusLevelQty3ActionPerformed
        if(this.squareEnix.getLevelDevs().getlSize() > 1){
            this.squareEnix.removeDev("LevelDesign");
        }
    }//GEN-LAST:event_plusLevelQty3ActionPerformed

    private void lessLevelQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessLevelQty3ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("LevelDesign", "LevelDesign", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessLevelQty3ActionPerformed

    private void lessNarrativeQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessNarrativeQty3ActionPerformed
        GameDeveloper squareDev = new GameDeveloper("Narrative", "SquareEnix", dayDuration, squareEnix.getCompanyDrive(), squareEnix.getMutex());
        squareEnix.addDev(squareDev);
    }//GEN-LAST:event_lessNarrativeQty3ActionPerformed

    private void plusNarrativeQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusNarrativeQty3ActionPerformed
        if(this.squareEnix.getScriptDevs().getlSize() > 1){
            this.squareEnix.removeDev("Narrative");
        }
    }//GEN-LAST:event_plusNarrativeQty3ActionPerformed

    private void plusDevQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plusDevQty3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_plusDevQty3ActionPerformed

    private void lessDevQty3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lessDevQty3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lessDevQty3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BG3;
    private javax.swing.JPanel BG4;
    private javax.swing.JPanel BG5;
    private javax.swing.JPanel CapcomPanel;
    private javax.swing.JPanel ConfigPanel;
    private javax.swing.JLabel DLCQty;
    private javax.swing.JLabel DLCQty1;
    private javax.swing.JLabel DLCQty2;
    private javax.swing.JLabel DLCQty3;
    private javax.swing.JLabel DayDur;
    private javax.swing.JLabel DeadLineDays;
    private javax.swing.JLabel DeadlineGUI;
    private javax.swing.JLabel DeadlineGUI1;
    private javax.swing.JLabel DevQty;
    private javax.swing.JLabel DevQty1;
    private javax.swing.JLabel DevQty2;
    private javax.swing.JLabel DevQty3;
    private javax.swing.JLabel IntegratorQty;
    private javax.swing.JLabel IntegratorQty1;
    private javax.swing.JLabel IntegratorQty2;
    private javax.swing.JLabel IntegratorQty3;
    private javax.swing.JLabel LevelQty;
    private javax.swing.JLabel LevelQty1;
    private javax.swing.JLabel LevelQty2;
    private javax.swing.JLabel LevelQty3;
    private javax.swing.JLabel LogicQty;
    private javax.swing.JLabel LogicQty1;
    private javax.swing.JLabel LogicQty2;
    private javax.swing.JLabel LogicQty3;
    private javax.swing.JLabel NarrativeQty;
    private javax.swing.JLabel NarrativeQty1;
    private javax.swing.JLabel NarrativeQty2;
    private javax.swing.JLabel NarrativeQty3;
    private javax.swing.JLabel PMfaults$GUI;
    private javax.swing.JLabel PMfaults$GUI1;
    private javax.swing.JLabel PMfaultsGUI;
    private javax.swing.JLabel PMfaultsGUI1;
    private javax.swing.JLabel PMstateGUI;
    private javax.swing.JLabel PMstateGUI1;
    private javax.swing.JPanel SidePanel3;
    private javax.swing.JPanel SidePanel4;
    private javax.swing.JPanel SidePanel5;
    private javax.swing.JLabel SpriteQty;
    private javax.swing.JLabel SpriteQty1;
    private javax.swing.JLabel SpriteQty2;
    private javax.swing.JLabel SpriteQty3;
    private javax.swing.JPanel SquareEnixPanel;
    private javax.swing.JPanel barrasuperior;
    private javax.swing.JPanel barrasuperior1;
    private javax.swing.JPanel barrasuperior2;
    private javax.swing.JPanel btn_Capcom;
    private javax.swing.JPanel btn_Capcom1;
    private javax.swing.JPanel btn_Capcom2;
    private javax.swing.JPanel btn_Configuration;
    private javax.swing.JPanel btn_Configuration1;
    private javax.swing.JPanel btn_SquareEnix3;
    private javax.swing.JPanel btn_SquareEnix4;
    private javax.swing.JPanel btn_SquareEnix5;
    private javax.swing.JPanel btn_configuration;
    private javax.swing.JLabel dayspassedGUI1;
    private javax.swing.JLabel dayspassedGUI2;
    private javax.swing.JLabel directorstateGUI;
    private javax.swing.JLabel directorstateGUI1;
    private javax.swing.JLabel dlcMAXqtyGUI;
    private javax.swing.JLabel dlcMAXqtyGUI1;
    private javax.swing.JLabel dlcqtyGUI;
    private javax.swing.JLabel dlcqtyGUI1;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel exit1;
    private javax.swing.JLabel exit2;
    private javax.swing.JLabel incomeGUI;
    private javax.swing.JLabel incomeGUI1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton lessDLCQty;
    private javax.swing.JButton lessDLCQty1;
    private javax.swing.JButton lessDLCQty2;
    private javax.swing.JButton lessDLCQty3;
    private javax.swing.JButton lessDayDur;
    private javax.swing.JButton lessDeadLine;
    private javax.swing.JButton lessDevQty;
    private javax.swing.JButton lessDevQty1;
    private javax.swing.JButton lessDevQty2;
    private javax.swing.JButton lessDevQty3;
    private javax.swing.JButton lessIntegratorQty;
    private javax.swing.JButton lessIntegratorQty1;
    private javax.swing.JButton lessIntegratorQty2;
    private javax.swing.JButton lessIntegratorQty3;
    private javax.swing.JButton lessLevelQty;
    private javax.swing.JButton lessLevelQty1;
    private javax.swing.JButton lessLevelQty2;
    private javax.swing.JButton lessLevelQty3;
    private javax.swing.JButton lessLogicQty;
    private javax.swing.JButton lessLogicQty1;
    private javax.swing.JButton lessLogicQty2;
    private javax.swing.JButton lessLogicQty3;
    private javax.swing.JButton lessNarrativeQty;
    private javax.swing.JButton lessNarrativeQty1;
    private javax.swing.JButton lessNarrativeQty2;
    private javax.swing.JButton lessNarrativeQty3;
    private javax.swing.JButton lessSpriteQty;
    private javax.swing.JButton lessSpriteQty1;
    private javax.swing.JButton lessSpriteQty2;
    private javax.swing.JButton lessSpriteQty3;
    private javax.swing.JLabel levelMAXqtyGUI1;
    private javax.swing.JLabel levelMAXqtyGUI2;
    private javax.swing.JLabel levelqtyGUI;
    private javax.swing.JLabel levelqtyGUI1;
    private javax.swing.JLabel logicMAXqtyGUI2;
    private javax.swing.JLabel logicMAXqtyGUI3;
    private javax.swing.JLabel logicqtyGUI1;
    private javax.swing.JLabel logicqtyGUI2;
    private javax.swing.JLabel narrativeMAXqtyGUI;
    private javax.swing.JLabel narrativeMAXqtyGUI1;
    private javax.swing.JLabel narrativeqtyGUI;
    private javax.swing.JLabel narrativeqtyGUI1;
    private javax.swing.JPanel panelCentro;
    private javax.swing.JPanel panelCentro1;
    private javax.swing.JPanel panelDerecho;
    private javax.swing.JPanel panelDerecho1;
    private javax.swing.JPanel panelDerecho2;
    private javax.swing.JPanel panelDerecho3;
    private javax.swing.JPanel panelIzquierdo;
    private javax.swing.JPanel panelIzquierdo1;
    private javax.swing.JPanel panelIzquierdo2;
    private javax.swing.JButton plusDLCQty;
    private javax.swing.JButton plusDLCQty1;
    private javax.swing.JButton plusDLCQty2;
    private javax.swing.JButton plusDLCQty3;
    private javax.swing.JButton plusDayDur;
    private javax.swing.JButton plusDeadLine;
    private javax.swing.JButton plusDevQty;
    private javax.swing.JButton plusDevQty1;
    private javax.swing.JButton plusDevQty2;
    private javax.swing.JButton plusDevQty3;
    private javax.swing.JButton plusIntegratorQty;
    private javax.swing.JButton plusIntegratorQty1;
    private javax.swing.JButton plusIntegratorQty2;
    private javax.swing.JButton plusIntegratorQty3;
    private javax.swing.JButton plusLevelQty;
    private javax.swing.JButton plusLevelQty1;
    private javax.swing.JButton plusLevelQty2;
    private javax.swing.JButton plusLevelQty3;
    private javax.swing.JButton plusLogicQty;
    private javax.swing.JButton plusLogicQty1;
    private javax.swing.JButton plusLogicQty2;
    private javax.swing.JButton plusLogicQty3;
    private javax.swing.JButton plusNarrativeQty;
    private javax.swing.JButton plusNarrativeQty1;
    private javax.swing.JButton plusNarrativeQty2;
    private javax.swing.JButton plusNarrativeQty3;
    private javax.swing.JButton plusSpriteQty;
    private javax.swing.JButton plusSpriteQty1;
    private javax.swing.JButton plusSpriteQty2;
    private javax.swing.JButton plusSpriteQty3;
    private javax.swing.JLabel readydlcGUI;
    private javax.swing.JLabel readydlcGUI1;
    private javax.swing.JLabel readygamesqtyGUI;
    private javax.swing.JLabel readygamesqtyGUI1;
    private javax.swing.JButton saveButton;
    private javax.swing.JLabel spriteMAXqtyGUI3;
    private javax.swing.JLabel spriteMAXqtyGUI4;
    private javax.swing.JLabel spriteqtyGUI;
    private javax.swing.JLabel spriteqtyGUI1;
    private javax.swing.JLabel totalsalariesGUI;
    private javax.swing.JLabel totalsalariesGUI1;
    private javax.swing.JLabel utilityGUI;
    private javax.swing.JLabel utilityGUI2;
    // End of variables declaration//GEN-END:variables
}

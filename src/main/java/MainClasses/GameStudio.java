/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class GameStudio {
    private final GameDeveloper[] developers;
    private final ProjectManager pm;
    private final Director director;
    private final Drive drive;

    public GameStudio(GameDeveloper[] devs, ProjectManager pm, Director director, Drive drive) {
        this.developers = devs;
        this.pm = pm;
        this.director = director;
        this.drive = drive;
    }

    public GameDeveloper[] getDevelopers() {
        return developers;
    }

    public ProjectManager getPm() {
        return pm;
    }

    public Director getDirector() {
        return director;
    }

    public Drive getDrive() {
        return drive;
    }
    
    

}

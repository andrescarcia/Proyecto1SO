/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

/**
 *
 * @author andre
 */
public class SuperDrive {
    private volatile int producedQty;
    private int maxCapacity;
    private String gameComponent;
    private int initialProducerQty;

    public SuperDrive(String gameComponent, int producedQty, int maxCapacity, int initialProducerQty) {
        this.producedQty = producedQty;
        this.maxCapacity = maxCapacity;
        this.gameComponent = gameComponent;
        this.initialProducerQty = initialProducerQty;
    }

    public int getProducedQty() {
        return producedQty;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public String getGameComponent() {
        return gameComponent;
    }

    public int getInitialProducerQty() {
        return initialProducerQty;
    }

    public void setProducedQty(int producedQty) {
        this.producedQty = producedQty;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public void setGameComponent(String gameComponent) {
        this.gameComponent = gameComponent;
    }

    public void setInitialProducerQty(int initialProducerQty) {
        this.initialProducerQty = initialProducerQty;
    }
    


}

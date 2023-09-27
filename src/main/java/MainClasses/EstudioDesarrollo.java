/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import MainClasses.Desarrolladores.Desarrollador;

/**
 *
 * @author andre
 */
public class EstudioDesarrollo {
    private String nombre;
    private Lista<Desarrollador> listaDesarrolladores;
    private ProjectManager projectManager;
    private Director director;
    private double ganancias;
    private double costosOperativos;
    private Lista<Videojuego> listaVideojuegos;
    private int contadorVideojuegos;

    public EstudioDesarrollo(String nombre, ProjectManager projectManager, Director director) {
        this.nombre = nombre;
        this.listaDesarrolladores = new Lista<>();
        this.projectManager = projectManager;
        this.director = director;
        this.ganancias = 0;
        this.costosOperativos = 0;
        this.listaVideojuegos = new Lista<>();
        this.contadorVideojuegos = 0;
    }
    public void simularDia() {
        // Todos los desarrolladores trabajan
        for (int i = 0; i < listaDesarrolladores.tamano(); i++) {
            listaDesarrolladores.obtener(i).trabajar();
        }

        // El ProjectManager y el Director trabajan
        projectManager.trabajar();
        director.trabajar();

        // Actualizar ganancias y costos
        calcularGanancias();
        calcularCostosOperativos();
    }
    
    // Método para agregar un nuevo videojuego
    public void agregarVideojuego(Videojuego videojuego) {
        listaVideojuegos.agregar(videojuego);
        contadorVideojuegos++;
    }

    // Método para determinar si el próximo juego debe tener DLC
    public boolean debeTenerDLC() {
        if (nombre.equals("Capcom") && contadorVideojuegos % 4 == 3) {
            return true;
        } else if (nombre.equals("Square Enix") && contadorVideojuegos % 3 == 2) {
            return true;
        }
        return false;
    }

    // Método para agregar un desarrollador al estudio
    public void agregarDesarrollador(Desarrollador desarrollador) {
        listaDesarrolladores.agregar(desarrollador);
    }

    // Método para eliminar un desarrollador del estudio
    public void eliminarDesarrollador(Desarrollador desarrollador) {
    listaDesarrolladores.eliminar(desarrollador);
    }

    // Método para calcular las ganancias basadas en videojuegos vendidos
    public void calcularGanancias() {
        // Implementar lógica para calcular ganancias
    }

    // Método para calcular los costos operativos basados en sueldos y otros gastos
    public void calcularCostosOperativos() {
        // Implementar lógica para calcular costos
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getGanancias() {
        return ganancias;
    }

    public double getCostosOperativos() {
        return costosOperativos;
    }

    public Lista<Desarrollador> getListaDesarrolladores() {
        return listaDesarrolladores;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public Director getDirector() {
        return director;
    }

    public void setDirector(Director director) {
        this.director = director;
    }
}

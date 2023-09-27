/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MainClasses;

import MainClasses.Desarrolladores.Desarrollador;
import MainClasses.Drives.Guion;
import MainClasses.Drives.Nivel;
import MainClasses.Drives.SistemaJuego;
import MainClasses.Drives.Sprite;

/**
 *
 * @author andre
 */
public class EstudioDesarrollo {
    private String nombre;
    private Lista<Desarrollador> listaDesarrolladores;
    private Lista<Integrador> listaIntegradores; // Lista de integradores
    private ProjectManager projectManager;
    private Director director;
    private double ganancias;
    private double costosOperativos;
    private Lista<Videojuego> listaVideojuegos;
    private int contadorVideojuegos;
    private int videojuegosParaDLC;
    private Drive<Guion> driveGuion;
    private Drive<Nivel> driveNiveles;
    private Drive<Sprite> driveSprites;
    private Drive<SistemaJuego> driveSistemas;


    public EstudioDesarrollo(String nombre, ProjectManager projectManager, Director director, int videojuegosParaDLC) {
        this.nombre = nombre;
        this.listaDesarrolladores = new Lista<>();
        this.listaIntegradores = new Lista<>(); // Inicializar la lista de integradores
        this.projectManager = projectManager;
        this.director = director;
        this.ganancias = 0;
        this.costosOperativos = 0;
        this.listaVideojuegos = new Lista<>();
        this.contadorVideojuegos = 0;
        this.videojuegosParaDLC = videojuegosParaDLC;
         this.driveGuion = new Drive<>(100); // Capacidad máxima arbitraria, puedes ajustarla
        this.driveNiveles = new Drive<>(100);
        this.driveSprites = new Drive<>(100);
        this.driveSistemas = new Drive<>(100);
    }
    
    // Métodos getters para los Drives
    public Drive<Guion> getDriveGuion() {
        return driveGuion;
    }
    public Drive<Nivel> getDriveNiveles() {
        return driveNiveles;
    }

    public Drive<Sprite> getDriveSprites() {
        return driveSprites;
    }

    public Drive<SistemaJuego> getDriveSistemas() {
        return driveSistemas;
    }

    public void simularDia() {
        // Todos los desarrolladores trabajan
        for (int i = 0; i < listaDesarrolladores.tamano(); i++) {
            listaDesarrolladores.obtener(i).trabajar();
        }

        // Todos los integradores trabajan
        for (int i = 0; i < listaIntegradores.tamano(); i++) {
            listaIntegradores.obtener(i).trabajar();
        }

        // El ProjectManager y el Director trabajan
        projectManager.trabajar();
        director.trabajar();

        // Actualizar ganancias y costos
        calcularGanancias();
        calcularCostosOperativos();
    }
    
    
    // Método para agregar un integrador al estudio
    public void agregarIntegrador(Integrador integrador) {
        listaIntegradores.agregar(integrador);
    }

    // Método para simular el trabajo diario de los integradores
    public void simularTrabajoIntegradores() {
        for (int i = 0; i < listaIntegradores.tamano(); i++) {
            Integrador integrador = listaIntegradores.obtener(i);
            if (!integrador.estaTrabajando() && hayPartesSuficientesParaEnsamblaje()) {
                integrador.iniciarEnsamblaje();
            }
            integrador.trabajar();
        }
    }

    // Método que verifica si hay suficientes partes en los Drives de los desarrolladores para ensamblar un videojuego
    private boolean hayPartesSuficientesParaEnsamblaje() {
        // Lógica para verificar si hay suficientes partes en los Drives de los desarrolladores
        // Esto dependerá de las especificaciones de cada estudio (Capcom o Square Enix)

        // Ejemplo (esto es solo un esquema, la lógica real dependerá de las especificaciones exactas):
        if (nombre.equals("Capcom")) {
            // Verificar si hay 1 guión, 2 niveles, 6 sets de sprites, 5 sistemas de juego en los Drives
            // Si todas las partes están disponibles, retornar true
        } else if (nombre.equals("Square Enix")) {
            // Verificar si hay 1 guión, 1 nivel, 2 sets de sprites, 4 sistemas de juego en los Drives
            // Si todas las partes están disponibles, retornar true
        }
        return false; // Retornar false si no hay suficientes partes
    }

   

    // Método para eliminar un integrador del estudio
    public void eliminarIntegrador(Integrador integrador) {
        listaIntegradores.eliminar(integrador);
    }

    // Getters y Setters para listaIntegradores
    public Lista<Integrador> getListaIntegradores() {
        return listaIntegradores;
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

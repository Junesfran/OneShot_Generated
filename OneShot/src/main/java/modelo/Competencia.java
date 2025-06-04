/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Nestor y Asociados
 */
public class Competencia {
     private String nombre;
    private boolean especializado;

    public Competencia() {
    }

    public Competencia(String nombre, boolean especializado) {
        this.nombre = nombre;
        this.especializado = especializado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEspecializado() {
        return especializado;
    }

    public void setEspecializado(boolean especializado) {
        this.especializado = especializado;
    }
        @Override
    public String toString() {
        return "{\"nombre\"=\"" + nombre + "\",\"especializado\"=" + especializado + "}";
    }
    
}

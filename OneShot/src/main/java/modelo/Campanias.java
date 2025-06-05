/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Nestor y Asociados
 */
public class Campanias {
   private int id;
   private String nombre;
   private String descripcion;
   private Manual manual;
   private boolean archivada;
   private int imagen;
   
    public Campanias() {
    }

    public Campanias(int id, String nombre, String descripcion,Manual manual, boolean archivada, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.manual = manual;
        this.archivada = archivada;
        this.imagen = imagen;
    }

    public Campanias(String nombre, String descripcion, Manual manual, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.manual = manual;
        this.imagen = imagen;
    }

    public Campanias(String nombre, Manual manual) {
        this.nombre = nombre;
        this.manual = manual;
    }
    
    
   
   //GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isArchivada() {
        return archivada;
    }

    public void setArchivada(boolean archivada) {
        this.archivada = archivada;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public Manual getManual() {
        return manual;
    }

    public void setManual(Manual manual) {
        this.manual = manual;
    }

    
    public String join(){
        return "{\"id_campaña\":"+id;
    }
    
    
    public String mandarJSON() {
        return "{\"nombre\":\"" + nombre + "\", \"descripcion\":\"" + descripcion +"\", \"manual\":\"" + manual.getId() + "\", \"idImagen\":" + imagen;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
}

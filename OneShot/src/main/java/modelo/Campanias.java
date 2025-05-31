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
    private String rutaImagen;
    private String manual;
    private boolean master;

    public Campanias(int id, String nombre, String rutaImagen, String manual, boolean master) {
        this.id = id;
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        this.manual = manual;
        this.master = master;
    }

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

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

    public boolean isMaster() {
        return master;
    }

    public void setMaster(boolean master) {
        this.master = master;
    }

    @Override
    public String toString() {
        return "Campanias{" + "id=" + id + ", nombre=" + nombre + ", rutaImagen=" + rutaImagen + ", manual=" + manual + ", master=" + master + '}';
    }
    
     
    
}

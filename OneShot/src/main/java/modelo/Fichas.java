/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Nestor y Asociados
 */
public class Fichas {
    private String nombre;
    private String tipo;
    private String rasgo;
    private String descriptor;
    private String recursion;
    
    private int rango;
    private int esfuerzo;
    private int exp;
    
    private boolean aumentarC;
    private boolean perfeccion;
    private boolean esfuerzoExt;
    private boolean competenciaH;
    private boolean otros;
    
    private String recuperacion;
    
    
    private int reservaVigorMax;
    private int reservaVigorAct;
    private int ventajaVigor;
    private int reservaVelocidadMax;
    private int reservaVelocidadAct;
    private int ventajaVelocidad;
    private int reservaInteligenciaMax;
    private int reservaInteligenciaAct;
    private int ventajaInteligencia;
    
    private int armadura;
    private int dinero;
    
    private HashMap<String, Boolean> competencias;
    private List<String> Habilidades;
    private List<String> equipo;
    private List<String> dispositivo;
    private int maxDispositivo;
    
    private List<String> notas;

    public Fichas() {
    }
    
    //GETTERS Y SETTERS

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getRasgo() {
        return rasgo;
    }

    public void setRasgo(String rasgo) {
        this.rasgo = rasgo;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getRecursion() {
        return recursion;
    }

    public void setRecursion(String recursion) {
        this.recursion = recursion;
    }

    public int getRango() {
        return rango;
    }

    public void setRango(int rango) {
        this.rango = rango;
    }

    public int getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(int esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public boolean isAumentarC() {
        return aumentarC;
    }

    public void setAumentarC(boolean aumentarC) {
        this.aumentarC = aumentarC;
    }

    public boolean isPerfeccion() {
        return perfeccion;
    }

    public void setPerfeccion(boolean perfeccion) {
        this.perfeccion = perfeccion;
    }

    public boolean isEsfuerzoExt() {
        return esfuerzoExt;
    }

    public void setEsfuerzoExt(boolean esfuerzoExt) {
        this.esfuerzoExt = esfuerzoExt;
    }

    public boolean isCompetenciaH() {
        return competenciaH;
    }

    public void setCompetenciaH(boolean competenciaH) {
        this.competenciaH = competenciaH;
    }

    public boolean isOtros() {
        return otros;
    }

    public void setOtros(boolean otros) {
        this.otros = otros;
    }

    public String getRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(String recuperacion) {
        this.recuperacion = recuperacion;
    }

    public int getReservaVigorMax() {
        return reservaVigorMax;
    }

    public void setReservaVigorMax(int reservaVigorMax) {
        this.reservaVigorMax = reservaVigorMax;
    }

    public int getReservaVigorAct() {
        return reservaVigorAct;
    }

    public void setReservaVigorAct(int reservaVigorAct) {
        this.reservaVigorAct = reservaVigorAct;
    }

    public int getVentajaVigor() {
        return ventajaVigor;
    }

    public void setVentajaVigor(int ventajaVigor) {
        this.ventajaVigor = ventajaVigor;
    }

    public int getReservaVelocidadMax() {
        return reservaVelocidadMax;
    }

    public void setReservaVelocidadMax(int reservaVelocidadMax) {
        this.reservaVelocidadMax = reservaVelocidadMax;
    }

    public int getReservaVelocidadAct() {
        return reservaVelocidadAct;
    }

    public void setReservaVelocidadAct(int reservaVelocidadAct) {
        this.reservaVelocidadAct = reservaVelocidadAct;
    }

    public int getVentajaVelocidad() {
        return ventajaVelocidad;
    }

    public void setVentajaVelocidad(int ventajaVelocidad) {
        this.ventajaVelocidad = ventajaVelocidad;
    }

    public int getReservaInteligenciaMax() {
        return reservaInteligenciaMax;
    }

    public void setReservaInteligenciaMax(int reservaInteligenciaMax) {
        this.reservaInteligenciaMax = reservaInteligenciaMax;
    }

    public int getReservaInteligenciaAct() {
        return reservaInteligenciaAct;
    }

    public void setReservaInteligenciaAct(int reservaInteligenciaAct) {
        this.reservaInteligenciaAct = reservaInteligenciaAct;
    }

    public int getVentajaInteligencia() {
        return ventajaInteligencia;
    }

    public void setVentajaInteligencia(int ventajaInteligencia) {
        this.ventajaInteligencia = ventajaInteligencia;
    }

    public int getArmadura() {
        return armadura;
    }

    public void setArmadura(int armadura) {
        this.armadura = armadura;
    }

    public int getDinero() {
        return dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }

    public HashMap<String, Boolean> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(HashMap<String, Boolean> competencias) {
        this.competencias = competencias;
    }

    public List<String> getHabilidades() {
        return Habilidades;
    }

    public void setHabilidades(List<String> Habilidades) {
        this.Habilidades = Habilidades;
    }

    public List<String> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<String> equipo) {
        this.equipo = equipo;
    }

    public List<String> getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(List<String> dispositivo) {
        this.dispositivo = dispositivo;
    }

    public int getMaxDispositivo() {
        return maxDispositivo;
    }

    public void setMaxDispositivo(int maxDispositivo) {
        this.maxDispositivo = maxDispositivo;
    }

    public List<String> getNotas() {
        return notas;
    }

    public void setNotas(List<String> notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        String aux = "[";
        for (Map.Entry<String, Boolean> entry : competencias.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            aux += "{\"nombre\":\""+key+"\","
            + "\"espe\":\""+val+"\"}";
        }
        aux += "]";
        return "Fichas{" + "nombre\"=\"" + nombre + ", \"tipo\"=\"" + tipo + ", \"rasgo\"=\"" + rasgo + ", \"descriptor\"=\"" + descriptor + ", \"recursion\"=\"" + recursion + ", \"rango\"=\"" + rango + ", \"esfuerzo\"=\"" + esfuerzo + ", \"exp\"=\"" + exp + ", \"aumentarC\"=\"" + aumentarC + ", \"perfeccion\"=\"" + perfeccion + ", \"esfuerzoExt\"=\"" + esfuerzoExt + ", \"competenciaH\"=\"" + competenciaH + ", \"otros\"=\"" + otros + ", \"recuperacion\"=\"" + recuperacion + ", \"reservaVigorMax\"=\"" + reservaVigorMax + ", \"reservaVigorAct\"=\"" + reservaVigorAct + ", \"ventajaVigor\"=\"" + ventajaVigor + ", \"reservaVelocidadMax\"=\"" + reservaVelocidadMax + ", \"reservaVelocidadAct\"=\"" + reservaVelocidadAct + ", \"ventajaVelocidad\"=\"" + ventajaVelocidad + ", \"reservaInteligenciaMax\"=\"" + reservaInteligenciaMax + ", \"reservaInteligenciaAct\"=\"" + reservaInteligenciaAct + ", \"ventajaInteligencia\"=\"" + ventajaInteligencia + ", \"armadura\"=\"" + armadura + ", \"dinero\"=\"" + dinero + ", \"competencias\"=\"" + aux + ", \"Habilidades\"=\"" + Habilidades + ", \"equipo\"=\"" + equipo + ", \"dispositivo\"=\"" + dispositivo + ", \"maxDispositivo\"=\"" + maxDispositivo + ", \"notas\"=\"" + notas + "\"}";
    }
    
    
}

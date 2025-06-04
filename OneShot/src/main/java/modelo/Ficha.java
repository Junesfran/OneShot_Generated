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
public class Ficha {
    private int id;
    private String manual;
    
    private String nombre;
    private String tipo;
    private String rasgo;
    private String descriptor;
    private String vinculoD;
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
    
    private boolean accion;
    private boolean minutos;
    private boolean hora;
    private boolean horas;
    
    
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
    
    private List<Competencia> competencias;
    private List<String> habilidades;
    private List<String> equipo;
    private List<String> dispositivo;
    private int maxDispositivo;
    
    private String trasfondo;

    public Ficha() {
    }

    public Ficha(String manual, String nombre, String tipo, String rasgo, String descriptor, String vinculoD, String recursion, int rango, int esfuerzo, int exp, boolean aumentarC, boolean perfeccion, boolean esfuerzoExt, boolean competenciaH, boolean otros, String recuperacion, boolean accion, boolean minutos, boolean hora, boolean horas, int reservaVigorMax, int reservaVigorAct, int ventajaVigor, int reservaVelocidadMax, int reservaVelocidadAct, int ventajaVelocidad, int reservaInteligenciaMax, int reservaInteligenciaAct, int ventajaInteligencia, int armadura, int dinero, List<Competencia> competencias, List<String> habilidades, List<String> equipo, List<String> dispositivo, int maxDispositivo, String trasfondo) {
        this.manual = manual;
        this.nombre = nombre;
        this.tipo = tipo;
        this.rasgo = rasgo;
        this.descriptor = descriptor;
        this.vinculoD = vinculoD;
        this.recursion = recursion;
        this.rango = rango;
        this.esfuerzo = esfuerzo;
        this.exp = exp;
        this.aumentarC = aumentarC;
        this.perfeccion = perfeccion;
        this.esfuerzoExt = esfuerzoExt;
        this.competenciaH = competenciaH;
        this.otros = otros;
        this.recuperacion = recuperacion;
        this.accion = accion;
        this.minutos = minutos;
        this.hora = hora;
        this.horas = horas;
        this.reservaVigorMax = reservaVigorMax;
        this.reservaVigorAct = reservaVigorAct;
        this.ventajaVigor = ventajaVigor;
        this.reservaVelocidadMax = reservaVelocidadMax;
        this.reservaVelocidadAct = reservaVelocidadAct;
        this.ventajaVelocidad = ventajaVelocidad;
        this.reservaInteligenciaMax = reservaInteligenciaMax;
        this.reservaInteligenciaAct = reservaInteligenciaAct;
        this.ventajaInteligencia = ventajaInteligencia;
        this.armadura = armadura;
        this.dinero = dinero;
        this.competencias = competencias;
        this.habilidades = habilidades;
        this.equipo = equipo;
        this.dispositivo = dispositivo;
        this.maxDispositivo = maxDispositivo;
        this.trasfondo = trasfondo;
    }

    public Ficha(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    
    

    //GETTERS & SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getManual() {
        return manual;
    }

    public void setManual(String manual) {
        this.manual = manual;
    }

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

    public String getVinculoD() {
        return vinculoD;
    }

    public void setVinculoD(String vinculoD) {
        this.vinculoD = vinculoD;
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

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    public List<String> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<String> habilidades) {
        this.habilidades = habilidades;
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

    public String getTrasfondo() {
        return trasfondo;
    }

    public void setTrasfondo(String trasfondo) {
        this.trasfondo = trasfondo;
    }

    public boolean isAccion() {
        return accion;
    }

    public void setAccion(boolean accion) {
        this.accion = accion;
    }

    public boolean isMinutos() {
        return minutos;
    }

    public void setMinutos(boolean minutos) {
        this.minutos = minutos;
    }

    public boolean isHora() {
        return hora;
    }

    public void setHora(boolean hora) {
        this.hora = hora;
    }

    public boolean isHoras() {
        return horas;
    }

    public void setHoras(boolean horas) {
        this.horas = horas;
    }

    @Override
    public String toString() {
        return nombre;
    }
    
    
    
    public String DevolverJSON() {
        String auxDispo = "[";
        for (int i = 0; i < dispositivo.size(); i++) {
            auxDispo += "{\"nombre\":\"" + dispositivo.get(i) + "\"}";
            if (i < dispositivo.size() - 1) {
                auxDispo += ",";
            }
        }
        auxDispo += "]";

        String auxEquipo = "[";
        for (int i = 0; i < equipo.size(); i++) {
            auxDispo += "{\"nombre\":\"" + equipo.get(i) + "\"}";
            if (i < equipo.size() - 1) {
                auxEquipo += ",";
            }
        }
        auxEquipo += "]";
        
        String auxHabi = "[";
        for (int i = 0; i < habilidades.size(); i++) {
            auxDispo += "{\"nombre\":\"" + habilidades.get(i) + "\"}";
            if (i < habilidades.size() - 1) {
                auxHabi += ",";
            }
        }
        auxHabi += "]";

        String auxComp = "[";
        for (int i = 0; i < competencias.size(); i++) {
            auxComp += competencias.get(i).toString();
            if (i < competencias.size() - 1) {
                auxHabi += ",";
            }
        }
        auxComp += "]";
        
        return "{"
            + "\"Manual_id\" : \"" + manual + "\","
            + "\"accion\" : " + accion + ","
            + "\"armadura\" : " + armadura + ","
            + "\"aumentarC\" : " + aumentarC + ","
            + "\"clase\" : \"" + tipo + "\","
            + "\"competenciaH\" : " + competenciaH + ","
            + "\"descriptor\" : \"" + descriptor + "\","
            + "\"dinero\" : " + dinero + ","
            + "\"esfuerzo\" : " + esfuerzo + ","
            + "\"esfuerzoExtra\" : " + esfuerzoExt + ","
            + "\"experiencia\" : " + exp + ","
            + "\"hora\" : " + hora + ","
            + "\"horas\" : " + horas + ","
            + "\"inteligenciaAct\" : " + reservaInteligenciaAct + ","
            + "\"limiteDispositivos\" : " + maxDispositivo + ","
            + "\"maxDispositivos\" : " + maxDispositivo + ","
            + "\"minutos\" : " + minutos + ","
            + "\"nombre\" : \"" + nombre + "\","
            + "\"otros\" : " + otros + ","
            + "\"perfeccion\" : " + perfeccion + ","
            + "\"rango\" : " + rango + ","
            + "\"rasgo\" : \"" + rasgo + "\","
            + "\"recuperacion\" : \"" + recuperacion + "\","
            + "\"recursion\" : \"" + recursion + "\","
            + "\"reservaInteligenciaMax\" : " + reservaInteligenciaMax + ","
            + "\"reservaVelocidadMax\" : " + reservaVelocidadMax + ","
            + "\"reservaVigorMax\" : " + reservaVigorMax + ","
            + "\"tipo\" : \"" + tipo + "\","
            + "\"trasfondo\" : \"" + trasfondo + "\","
            + "\"velocidadAct\" : " + reservaVelocidadAct + ","
            + "\"ventajaInteligenciaMax\" : " + ventajaInteligencia + ","
            + "\"ventajaVelocidadMax\" : " + ventajaVelocidad + ","
            + "\"ventajaVigorMax\" : " + ventajaVigor + ","
            + "\"vigorAct\" : " + reservaVigorAct + ","
            + "\"vinculoDescriptor\" : \"" + vinculoD + "\","
            + "\"dispositivos\" : " + auxDispo + ","
            + "\"equipo\" : " + auxEquipo + ","
            + "\"capacidadesEspeciales\" : " + auxHabi + ","
            + "\"competencias\" : " + auxComp
            + "}";

    }

    
}

package modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

// Tipos de datos complejos de la ficha
class Competencia {

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
}

public class FichaDTO {

    private Integer id;
    private String Manual_id;
    private Integer Usuario_idUsuario;

    private String nombre;

    private String clase;
    private Integer rango;
    private String descriptor;
    private String vinculoDescriptor;
    // no era un <clase> <descriptor> que <accion>?
    private Boolean accion; // boolean?

    private String trasfondo;
    private String tipo;
    
    
    private String rasgo;

    private Boolean otros;
    private Boolean perfeccion;

    private Integer esfuerzo;
    private Boolean esfuerzoExtra;
    private Integer experiencia;

    private Integer armadura;

    private String recuperacion;
    private String recursion;

    private Boolean aumentarC;
    private Boolean competenciaH;
    private List<Competencia> competencias; // Dato compuesto

    // Aqui hay un objeto, no se que mas atributos tiene aparte de nombre
    private List<String> capacidades_especiales;

    // Lo mismo que capacidades especiales
    private List<String> equipo;
    private List<String> dispositivos;

    private Integer  maxDispositivos;
    private Integer limiteDispositivos;
    private Integer dinero;

    private Boolean hora;
    private Boolean horas;
    private Boolean minutos;

    // Stats
    private Integer reservaVigorMax;
    private Integer reservaVelocidadMax;
    private Integer reservaInteligenciaMax;

    private Integer vigorAct;
    private Integer velocidadAct;
    private Integer inteligenciaAct;
    
    private Integer ventajaVigorMax;
    private Integer ventajaVelocidadMax;
    private Integer ventajaInteligenciaMax;

    public FichaDTO() {
        // Poner por defecto
    }

    public FichaDTO(JSONObject obj) {
        id = obj.getInt("id");
        Manual_id = obj.getString("Manual_id");
        Usuario_idUsuario = obj.getInt("Usuario_idUsuario");
        nombre = obj.getString("nombre");
        clase = obj.getString("clase");
        rango = obj.getInt("rango");
        descriptor = obj.getString("descriptor");
        vinculoDescriptor = obj.getString("vinculoDescriptor");
        rasgo = obj.getString("rasgo");
        otros = obj.getBoolean("otros");
        perfeccion = obj.getBoolean("perfeccion");
        esfuerzo = obj.getInt("esfuerzo");
        esfuerzoExtra = obj.getBoolean("esfuerzoExtra");
        experiencia = obj.getInt("experiencia");
        armadura = obj.getInt("armadura");
        recuperacion = obj.getString("recuperacion");
        recursion = obj.getString("recursion");
        aumentarC = obj.getBoolean("aumentarC");
        competenciaH = obj.getBoolean("competenciaH");
        accion = obj.getBoolean("accion");
        tipo = obj.getString("tipo");
        trasfondo = obj.getString("trasfondo");
        
        competencias = new ArrayList<>();
        JSONArray competenciasJSON = obj.getJSONArray("competencias");
        for (int i = 0; i < competenciasJSON.length() ; i++) {
            JSONObject compJ = competenciasJSON.getJSONObject(i);
            
            Competencia comp = new Competencia(
                compJ.getString("nombre"),
                compJ.getBoolean("especializado")
            );

            competencias.add(comp);
        }
        
        capacidades_especiales = new ArrayList<>();
        JSONArray capacidadesJSON = obj.getJSONArray("capacidades_especiales");
        for (int i = 0; i < capacidadesJSON.length() ; i++) {
            String cap = capacidadesJSON.getJSONObject(i).getString("nombre");

            capacidades_especiales.add(cap);
        }
        // Lo mismo que capacidades especiales
        equipo = new ArrayList<>();
        JSONArray equipoJSON = obj.getJSONArray("equipo");
        for (int i = 0; i < equipoJSON.length() ; i++) {
            String cap = equipoJSON.getJSONObject(i).getString("nombre");

            equipo.add(cap);
        };
        
        dispositivos = new ArrayList<>();
        JSONArray disposJSON = obj.getJSONArray("dispositivos");
        for (int i = 0; i < disposJSON.length() ; i++) {
            String dis = disposJSON.getJSONObject(i).getString("nombre");

            dispositivos.add(dis);
        };
        
        maxDispositivos = obj.getInt("maxDispositivos");
        limiteDispositivos = obj.getInt("limiteDispositivos");
        dinero = obj.getInt("dinero");
        hora = obj.getBoolean("hora");
        horas = obj.getBoolean("horas");
        minutos = obj.getBoolean("minutos");
        // Stats
        reservaVigorMax = obj.getInt("reservaVigorMax");
        reservaVelocidadMax = obj.getInt("reservaVelocidadMax");
        reservaInteligenciaMax = obj.getInt("reservaInteligenciaMax");
        vigorAct = obj.getInt("vigorAct");
        velocidadAct = obj.getInt("velocidadAct");
        inteligenciaAct = obj.getInt("inteligenciaAct");
        ventajaVigorMax = obj.getInt("ventajaVigorMax");
        ventajaVelocidadMax = obj.getInt("ventajaVelocidadMax");
        ventajaInteligenciaMax = obj.getInt("ventajaInteligenciaMax");
    }

    public FichaDTO(String jsonParsear) {
        this(new JSONObject(jsonParsear));

    }
    
    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        
        obj.put("id", id);
        obj.put("Manual_id", Manual_id);
        
        obj.put("Usuario_idUsuario", Usuario_idUsuario);
        obj.put("nombre",nombre);
        obj.put("clase",clase);
        obj.put("rango",rango);
        obj.put("descriptor",descriptor);
        obj.put("vinculoDescriptor",vinculoDescriptor);
        obj.put("rasgo",rasgo);
        obj.put("otros",otros);
        obj.put("perfeccion",perfeccion);
        obj.put("esfuerzo",esfuerzo);
        obj.put("esfuerzoExtra",esfuerzoExtra);
        obj.put("experiencia",experiencia);
        obj.put("armadura",armadura);
        obj.put("recuperacion",recuperacion);
        obj.put("recursion",recursion);
        obj.put("aumentarC",aumentarC);
        obj.put("competenciaH",competenciaH);
        obj.put("accion", accion);
        obj.put("tipo", tipo);
        obj.put("trasfondo", trasfondo);
        
        JSONArray compJSON = new JSONArray();
        for (Competencia comp : competencias) {
            var jsonItem = new JSONObject();
            
            jsonItem.put("nombre", comp.getNombre());
            jsonItem.put("especializado", comp.isEspecializado());
            
            compJSON.put(jsonItem);
        }
        obj.put("competencias", compJSON);
        
        JSONArray capEspJSON = new JSONArray();
        for (String capEsp : capacidades_especiales) {
            JSONObject json = new JSONObject();
            json.put("nombre", capEsp);
            capEspJSON.put(json);
        }
        obj.put("capacidades_especiales", capEspJSON);
        
        JSONArray equipoJSON = new JSONArray();
        for (String eq : equipo) {
            JSONObject json = new JSONObject();
            json.put("nombre", eq);
            equipoJSON.put(json);
        }
        obj.put("equipo", equipoJSON);
        
        JSONArray dispoJSON = new JSONArray();
        for (String dis : dispositivos) {
            JSONObject json = new JSONObject();
            json.put("nombre", dis);
            dispoJSON.put(json);
        }
        obj.put("dispositivos", dispoJSON);
        
        obj.put("maxDispositivos", maxDispositivos);
        obj.put("limiteDispositivos", limiteDispositivos);
        obj.put("dinero", dinero);
        obj.put("hora", hora);
        obj.put("horas", horas);
        obj.put("minutos", minutos);
        
        // Stats
        obj.put("reservaVigorMax", reservaVigorMax);
        obj.put("reservaVelocidadMax", reservaVelocidadMax);
        obj.put("reservaInteligenciaMax", reservaInteligenciaMax);
        obj.put("vigorAct", vigorAct);
        obj.put("velocidadAct", velocidadAct);
        obj.put("inteligenciaAct", inteligenciaAct);
        obj.put("ventajaVigorMax", ventajaVigorMax);
        obj.put("ventajaVelocidadMax", ventajaVelocidadMax);
        obj.put("ventajaInteligenciaMax", ventajaInteligenciaMax);
        
        return obj.toString(1);
    }
    
    //<editor-fold desc="Getters y Setters">

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getManual_id() {
        return Manual_id;
    }

    public void setManual_id(String Manual_id) {
        this.Manual_id = Manual_id;
    }

    public Integer getUsuario_idUsuario() {
        return Usuario_idUsuario;
    }

    public void setUsuario_idUsuario(Integer Usuario_idUsuario) {
        this.Usuario_idUsuario = Usuario_idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public Integer getRango() {
        return rango;
    }

    public void setRango(Integer rango) {
        this.rango = rango;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getVinculoDescriptor() {
        return vinculoDescriptor;
    }

    public void setVinculoDescriptor(String vinculoDescriptor) {
        this.vinculoDescriptor = vinculoDescriptor;
    }

    public Boolean getAccion() {
        return accion;
    }

    public void setAccion(Boolean accion) {
        this.accion = accion;
    }

    public String getTrasfondo() {
        return trasfondo;
    }

    public void setTrasfondo(String trasfondo) {
        this.trasfondo = trasfondo;
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

    public Boolean getOtros() {
        return otros;
    }

    public void setOtros(Boolean otros) {
        this.otros = otros;
    }

    public Boolean getPerfeccion() {
        return perfeccion;
    }

    public void setPerfeccion(Boolean perfeccion) {
        this.perfeccion = perfeccion;
    }

    public Integer getEsfuerzo() {
        return esfuerzo;
    }

    public void setEsfuerzo(Integer esfuerzo) {
        this.esfuerzo = esfuerzo;
    }

    public Boolean getEsfuerzoExtra() {
        return esfuerzoExtra;
    }

    public void setEsfuerzoExtra(Boolean esfuerzoExtra) {
        this.esfuerzoExtra = esfuerzoExtra;
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(Integer experiencia) {
        this.experiencia = experiencia;
    }

    public Integer getArmadura() {
        return armadura;
    }

    public void setArmadura(Integer armadura) {
        this.armadura = armadura;
    }

    public String getRecuperacion() {
        return recuperacion;
    }

    public void setRecuperacion(String recuperacion) {
        this.recuperacion = recuperacion;
    }

    public String getRecursion() {
        return recursion;
    }

    public void setRecursion(String recursion) {
        this.recursion = recursion;
    }

    public Boolean getAumentarC() {
        return aumentarC;
    }

    public void setAumentarC(Boolean aumentarC) {
        this.aumentarC = aumentarC;
    }

    public Boolean getCompetenciaH() {
        return competenciaH;
    }

    public void setCompetenciaH(Boolean competenciaH) {
        this.competenciaH = competenciaH;
    }

    public List<Competencia> getCompetencias() {
        return competencias;
    }

    public void setCompetencias(List<Competencia> competencias) {
        this.competencias = competencias;
    }

    public List<String> getCapacidades_especiales() {
        return capacidades_especiales;
    }

    public void setCapacidades_especiales(List<String> capacidades_especiales) {
        this.capacidades_especiales = capacidades_especiales;
    }

    public List<String> getEquipo() {
        return equipo;
    }

    public void setEquipo(List<String> equipo) {
        this.equipo = equipo;
    }

    public List<String> getDispositivos() {
        return dispositivos;
    }

    public void setDispositivos(List<String> dispositivos) {
        this.dispositivos = dispositivos;
    }

    public Integer getMaxDispositivos() {
        return maxDispositivos;
    }

    public void setMaxDispositivos(Integer maxDispositivos) {
        this.maxDispositivos = maxDispositivos;
    }

    public Integer getLimiteDispositivos() {
        return limiteDispositivos;
    }

    public void setLimiteDispositivos(Integer limiteDispositivos) {
        this.limiteDispositivos = limiteDispositivos;
    }

    public Integer getDinero() {
        return dinero;
    }

    public void setDinero(Integer dinero) {
        this.dinero = dinero;
    }

    public Boolean getHora() {
        return hora;
    }

    public void setHora(Boolean hora) {
        this.hora = hora;
    }

    public Boolean getHoras() {
        return horas;
    }

    public void setHoras(Boolean horas) {
        this.horas = horas;
    }

    public Boolean getMinutos() {
        return minutos;
    }

    public void setMinutos(Boolean minutos) {
        this.minutos = minutos;
    }

    public Integer getReservaVigorMax() {
        return reservaVigorMax;
    }

    public void setReservaVigorMax(Integer reservaVigorMax) {
        this.reservaVigorMax = reservaVigorMax;
    }

    public Integer getReservaVelocidadMax() {
        return reservaVelocidadMax;
    }

    public void setReservaVelocidadMax(Integer reservaVelocidadMax) {
        this.reservaVelocidadMax = reservaVelocidadMax;
    }

    public Integer getReservaInteligenciaMax() {
        return reservaInteligenciaMax;
    }

    public void setReservaInteligenciaMax(Integer reservaInteligenciaMax) {
        this.reservaInteligenciaMax = reservaInteligenciaMax;
    }

    public Integer getVigorAct() {
        return vigorAct;
    }

    public void setVigorAct(Integer vigorAct) {
        this.vigorAct = vigorAct;
    }

    public Integer getVelocidadAct() {
        return velocidadAct;
    }

    public void setVelocidadAct(Integer velocidadAct) {
        this.velocidadAct = velocidadAct;
    }

    public Integer getInteligenciaAct() {
        return inteligenciaAct;
    }

    public void setInteligenciaAct(Integer inteligenciaAct) {
        this.inteligenciaAct = inteligenciaAct;
    }

    public Integer getVentajaVigorMax() {
        return ventajaVigorMax;
    }

    public void setVentajaVigorMax(Integer ventajaVigorMax) {
        this.ventajaVigorMax = ventajaVigorMax;
    }

    public Integer getVentajaVelocidadMax() {
        return ventajaVelocidadMax;
    }

    public void setVentajaVelocidadMax(Integer ventajaVelocidadMax) {
        this.ventajaVelocidadMax = ventajaVelocidadMax;
    }

    public Integer getVentajaInteligenciaMax() {
        return ventajaInteligenciaMax;
    }

    public void setVentajaInteligenciaMax(Integer ventajaInteligenciaMax) {
        this.ventajaInteligenciaMax = ventajaInteligenciaMax;
    }
    
    //</editor-fold>
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.oneshot.App;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.GridPane;
import modelo.Competencia;
import modelo.Ficha;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Nestor y Asociados
 */
public class FichaRepository {

        /**
     * Extrae una lista de competencias desde un GridPane, buscando los CheckBox seleccionados.
     * @param gp GridPane que contiene los CheckBox de competencias.
     * @return Lista de objetos Competencia.
     */
    public List<Competencia> sacarCompetencias(GridPane gp){
        List<Competencia> listC = new ArrayList<>();
        for (Node node : gp.getChildren()) {
            if (node instanceof CheckBox) {
                String nombre = ((CheckBox) node).getText();
                boolean espe = ((CheckBox) node).isSelected();
                Competencia c = new Competencia(nombre, espe);
                listC.add(c);
            }
        }
        return listC;
    }
    
        /**
     * Asocia una ficha a una campaña enviando una petición POST a la URL indicada.
     * @param url URL base del servicio.
     * @param idCamp ID de la campaña.
     * @param idFicha ID de la ficha.
     * @return Código de respuesta HTTP.
     */
    public int fichaCampania(String url, int idCamp, int idFicha) {
        int res = 0;
        url += "/campanyan/"+idCamp+"/fichas/"+idFicha;
        String aux = "";

        URL direc;
        try {
            direc = new URI(url).toURL();

            HttpURLConnection huc = (HttpURLConnection) direc.openConnection();
            huc.setRequestMethod("POST");

            huc.setRequestProperty("Content-Type", "application/json");
            huc.setRequestProperty("Accept", "application/json");
            huc.setRequestProperty("Authorization", "Bearer " + App.user.getToken());

            res = huc.getResponseCode();
            System.out.println(res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
        /**
     * Elimina una ficha enviando una petición DELETE a la URL indicada.
     * @param url URL base del servicio.
     * @param f Ficha a eliminar.
     */
    public void borrarFicha(String url, Ficha f) {
        if(f == null){
            return;
        }
        int res = 0;
        System.out.println(f.getId());
        url += "/the_strange/ficha/"+f.getId();
        String aux = "";

        URL direc;
        try {
            direc = new URI(url).toURL();

            HttpURLConnection huc = (HttpURLConnection) direc.openConnection();
            huc.setRequestMethod("DELETE");

            huc.setRequestProperty("Content-Type", "application/json");
            huc.setRequestProperty("Accept", "application/json");
            huc.setRequestProperty("Authorization", "Bearer " + App.user.getToken());

            res = huc.getResponseCode();
            System.out.println(res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Envía una ficha al servidor mediante una petición POST.
     * @param url URL base del servicio.
     * @param f Ficha a enviar.
     * @return Código de respuesta HTTP.
     */
    public int mandarFicha(String url, Ficha f) {
        int res = 0;
        url += "/the_strange/ficha";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;

        URL direc;
        try {
            direc = new URI(url).toURL();

            HttpURLConnection huc = (HttpURLConnection) direc.openConnection();
            huc.setRequestMethod("POST");

            huc.setRequestProperty("Content-Type", "application/json");
            huc.setRequestProperty("Accept", "application/json");
            huc.setRequestProperty("Authorization", "Bearer " + App.user.getToken());

            huc.setDoOutput(true);

            String inputLine = f.DevolverJSON();
            
            System.out.println(inputLine);
            
            os = huc.getOutputStream();
            byte[] input = inputLine.getBytes("utf-8");
            os.write(input, 0, input.length);

            res = huc.getResponseCode();
            System.out.println(res);
            if (res == 200) {
                br = new BufferedReader(new InputStreamReader(huc.getInputStream(), "utf-8"));

                StringBuilder respu = new StringBuilder();
                String linea = null;
                while ((linea = br.readLine()) != null) {
                    respu.append(linea.trim());
                }
                aux = respu.toString();
                JSONObject jobject = new JSONObject(aux);
                f.setId(jobject.getInt("id"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

        /**
     * Asigna una ficha a una campaña. (Método pendiente de implementación completa)
     * @param url URL base del servicio.
     * @param f Ficha a asignar.
     * @return Código de respuesta HTTP.
     */
    public int AsignarFichaCampania(String url, Ficha f) {
        int res = 0;
        //Pendiente de saber la ruta
        url += "/ficha";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;

        URL direc;
        try {
            direc = new URI(url).toURL();

            HttpURLConnection huc = (HttpURLConnection) direc.openConnection();
            huc.setRequestMethod("POST");

            huc.setRequestProperty("Content-Type", "application/json");
            huc.setRequestProperty("Accept", "application/json");
            huc.setRequestProperty("Authorization", "Bearer " + App.user.getToken());

            huc.setDoOutput(true);

            String inputLine = f.toString();

            os = huc.getOutputStream();
            byte[] input = inputLine.getBytes("utf-8");
            os.write(input, 0, input.length);

            int rest = huc.getResponseCode();
            if (rest == 200) {
                br = new BufferedReader(new InputStreamReader(huc.getInputStream(), "utf-8"));

                StringBuilder respu = new StringBuilder();
                String linea = null;
                while ((linea = br.readLine()) != null) {
                    respu.append(linea.trim());
                }
                aux = respu.toString();
                System.out.println(aux);
                JSONObject jobject = new JSONObject(aux);
                //Pendiente de ver que me devuelve;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }
    /**
     * Obtiene la lista de todas las fichas desde el servidor.
     * @param url URL base del servicio.
     * @return Lista de objetos Ficha.
     */
    public List<Ficha> listarFichas(String url){
        List<Ficha> fichas = new ArrayList<Ficha>();
        url += "/the_strange/ficha";
        int num = 0;
        
        JSONObject datos = sacarGeneral(url);
        if(datos == null){
            return fichas;
        }
        num = datos.getInt("kuantos");
        
        Ficha f = null;
        for (int i = 0; i < num; i++) {
            JSONObject dato = datos.getJSONArray("datos").getJSONObject(i);
            f = new Ficha(dato.getInt("id"), dato.getString("nombre"));
            fichas.add(f);
        }

        return fichas;
    }
        /**
     * Busca una ficha por su ID y la construye a partir de los datos recibidos del servidor.
     * @param url URL base del servicio.
     * @param id ID de la ficha a buscar.
     * @return Objeto Ficha con los datos obtenidos.
     */
    public Ficha buscarFichaPorId(String url, int id) {
        url += "/the_strange/ficha/"+id;
            
        JSONObject json = sacarGeneral(url);

        Ficha ficha = new Ficha(); // Constructor vacío o sin argumentos

        // Campos básicos
        ficha.setId(json.getInt("id"));
        ficha.setManual(json.getString("Manual_id"));
        ficha.setNombre(json.getString("nombre"));
        ficha.setTipo(json.getString("tipo"));
        ficha.setRasgo(json.getString("rasgo"));
        ficha.setDescriptor(json.getString("descriptor"));
        ficha.setVinculoD(json.getString("vinculoDescriptor"));
        ficha.setRecursion(json.getString("recursion"));
        ficha.setTrasfondo(json.getString("trasfondo"));

        // Campos numéricos
        ficha.setRango(json.getInt("rango"));
        ficha.setEsfuerzo(json.getInt("esfuerzo"));
        ficha.setExp(json.getInt("experiencia"));
        ficha.setArmadura(json.getInt("armadura"));
        ficha.setDinero(json.getInt("dinero"));
        ficha.setReservaVigorMax(json.getInt("reservaVigorMax"));
        ficha.setReservaVigorAct(json.getInt("vigorAct"));
        ficha.setVentajaVigor(json.getInt("ventajaVigorMax"));
        ficha.setReservaVelocidadMax(json.getInt("reservaVelocidadMax"));
        ficha.setReservaVelocidadAct(json.getInt("velocidadAct"));
        ficha.setVentajaVelocidad(json.getInt("ventajaVelocidadMax"));
        ficha.setReservaInteligenciaMax(json.getInt("reservaInteligenciaMax"));
        ficha.setReservaInteligenciaAct(json.getInt("inteligenciaAct"));
        ficha.setVentajaInteligencia(json.getInt("ventajaInteligenciaMax"));
        ficha.setMaxDispositivo(json.getInt("limiteDispositivos"));

        // Campos booleanos
        ficha.setAccion(json.getBoolean("accion"));
        ficha.setMinutos(json.getBoolean("minutos"));
        ficha.setHora(json.getBoolean("hora"));
        ficha.setHoras(json.getBoolean("horas"));
        ficha.setAumentarC(json.getBoolean("aumentarC"));
        ficha.setPerfeccion(json.getBoolean("perfeccion"));
        ficha.setEsfuerzoExt(json.getBoolean("esfuerzoExtra"));
        ficha.setCompetenciaH(json.getBoolean("competenciaH"));
        ficha.setOtros(json.getBoolean("otros"));

        // Campo string adicional
        ficha.setRecuperacion(json.getString("recuperacion"));

        // List<String> equipo
        JSONArray equipoArray = json.getJSONArray("equipo");
        List<String> equipoList = new ArrayList<>();
        for (int i = 0; i < equipoArray.length(); i++) {
            equipoList.add(equipoArray.getJSONObject(i).getString("nombre"));
        }
        ficha.setEquipo(equipoList);

        // List<String> dispositivo
        JSONArray dispoArray = json.getJSONArray("dispositivos");
        List<String> dispoList = new ArrayList<>();
        for (int i = 0; i < dispoArray.length(); i++) {
            dispoList.add(dispoArray.getJSONObject(i).getString("nombre"));
        }
        ficha.setDispositivo(dispoList);

        // List<String> habilidades (asumimos que "capacidades_especiales" es esto)
        JSONArray habilidadesArray = json.getJSONArray("capacidades_especiales");
        List<String> habilidadesList = new ArrayList<>();
        for (int i = 0; i < habilidadesArray.length(); i++) {
            habilidadesList.add(habilidadesArray.getJSONObject(i).getString("nombre"));
        }
        ficha.setHabilidades(habilidadesList);

        // List<Competencia>
        JSONArray compArray = json.getJSONArray("competencias");
        List<Competencia> compList = new ArrayList<>();
        for (int i = 0; i < compArray.length(); i++) {
            JSONObject c = compArray.getJSONObject(i);
            Competencia comp = new Competencia();
            comp.setNombre(c.getString("nombre"));
            comp.setEspecializado(c.getBoolean("especializado"));
            compList.add(comp);
        }
        ficha.setCompetencias(compList);

        int num = 0;
        return ficha;

    }
    
    
    /**
     * Lista las fichas asociadas a campañas. (Método pendiente de implementación completa)
     * @param url URL base del servicio.
     * @return Lista de objetos Ficha.
     */
    public List<Ficha> listarFichasCampanias(String url) {
        int num = 0;
        List<Ficha> fichas = new ArrayList<Ficha>();
        //No se sabe la ruta concreta
        url += "/ficha";
        JSONObject datos = sacarGeneral(url);

        num = datos.getInt("kuantos");
        Ficha f = null;
        for (int i = 0; i < num; i++) {
            JSONObject dato = datos.getJSONArray("datos").getJSONObject(i);

            //Pendiende de saber que devuelve y como está el constructor
            f = new Ficha();
            fichas.add(f);
        }
        return fichas;
    }

    private JSONObject sacarGeneral(String url) {
        JSONObject datos = null;
        int respu;
        String ruta = url.replaceAll(" ", "%20");
        try {
            System.out.println(ruta);
            URL direc = new URI(ruta).toURL();
            HttpURLConnection huc = (HttpURLConnection) direc.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("Authorization", "Bearer " + App.user.getToken());
            respu = huc.getResponseCode();
            System.out.println(respu);
            if (respu == 200) {
                StringBuilder info = new StringBuilder();

                Scanner scc = new Scanner(huc.getInputStream());

                while (scc.hasNext()) {
                    info.append(scc.nextLine());
                }

                datos = new JSONObject(String.valueOf(info));

                scc.close();
            } else if (respu == 404) {
                System.out.println("Página no encontrada");
            } else {
                System.out.println("Ha habido un fallo en la comunicación");
                System.out.println(respu);

            }
            
            huc.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datos;
    }
}

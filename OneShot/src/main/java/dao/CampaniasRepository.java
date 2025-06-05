/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mycompany.oneshot.App;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Campanias;
import modelo.Manual;
import org.json.JSONObject;

/**
 *
 * @author Nestor y Asociados
 */
public class CampaniasRepository {

    /**
     * Archiva una campaña enviando una petición POST a la URL especificada con el ID de la campaña.
     *
     * @param url URL base de la API.
     * @param id ID de la campaña a archivar.
     * @return Una cadena indicando el resultado de la operación ("success" o mensaje de error).
     */    
    public String archivar(String url, int id){
        String respuesta = "";
        int res = 0;
        url += "/campanyan/"+id+"/archivar";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;
        
          URL direc;
        try {
            direc = new URI(url).toURL();
        
          HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
          huc.setRequestMethod("POST");
          
          huc.setRequestProperty("Content-Type", "application/json");
          huc.setRequestProperty("Accept", "application/json");
          huc.setRequestProperty("Authorization", "Bearer "+App.user.getToken());

          huc.setDoOutput(true);
          os = huc.getOutputStream();
          
           res = huc.getResponseCode();
          if(res == 200){
            br = new BufferedReader(new InputStreamReader(huc.getInputStream(),"utf-8"));
          
            StringBuilder respu = new StringBuilder();
            String linea = null;
            while((linea = br.readLine())!= null){
                respu.append(linea.trim());
            }
            aux = respu.toString();
            
            JSONObject jobject = new JSONObject(aux);
            respuesta = jobject.getString("success");
          }else{
              respuesta = "Campaña no encontrada";
          }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return respuesta;
    }
        /**
     * Recupera una lista de manuales desde la API.
     *
     * @param url URL base de la API.
     * @return Una lista de objetos Manual obtenidos de la API.
     */
    public List<Manual> listarManuales(String url){
        List<Manual> manuales = new ArrayList<Manual>();
        url += "/manual";
        int num = 0;
        
        JSONObject datos = sacarGeneral(url);
        num = datos.getInt("kuantos");
        
        Manual m = null;
        for (int i = 0; i < num; i++) {
            JSONObject dato = datos.getJSONArray("datos").getJSONObject(i);
            m = new Manual(dato.getString("id"), dato.getString("nombre"));
            manuales.add(m);
        }

        return manuales;
    }
    /* * Envía una campaña al servidor realizando una petición POST con los datos de la campaña y la contraseña.
     *
     * @param url URL base de la API.
     * @param c Objeto Campanias con los datos de la campaña.
     * @param pwd Contraseña requerida para la operación.
     * @return Código de respuesta HTTP del servidor.
     */
    public int MandarCampaña(String url,Campanias c, String pwd){
        int res = 0;
        url += "/campanyan";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;
        
          URL direc;
        try {
            direc = new URI(url).toURL();
        
          HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
          huc.setRequestMethod("POST");
          
          huc.setRequestProperty("Content-Type", "application/json");
          huc.setRequestProperty("Accept", "application/json");
          huc.setRequestProperty("Authorization", "Bearer "+App.user.getToken());

          huc.setDoOutput(true);
          
          String inputLine = c.mandarJSON()+ ", \"contrasenyn\":\""+pwd+"\"}";
            System.out.println(inputLine);
            
          os = huc.getOutputStream();
          byte[] input = inputLine.getBytes("utf-8");
          os.write(input,0,input.length);
          
           res = huc.getResponseCode();
          if(res == 200){
            br = new BufferedReader(new InputStreamReader(huc.getInputStream(),"utf-8"));
          
            StringBuilder respu = new StringBuilder();
            String linea = null;
            while((linea = br.readLine())!= null){
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
     * Archiva una campaña enviando una petición POST con el ID de la campaña.
     *
     * @param url URL base de la API.
     * @param id ID de la campaña a archivar.
     * @return Código de respuesta HTTP del servidor.
     */
    public int ArchivarCampaña(String url, int id){
         int res = 0;
         //Pendiente de saber la ruta
        url += "/campania";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;
        
          URL direc;
        try {
            direc = new URI(url).toURL();
        
          HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
          huc.setRequestMethod("POST");
          
          huc.setRequestProperty("Content-Type", "application/json");
          huc.setRequestProperty("Accept", "application/json");
          huc.setRequestProperty("Authorization", "Bearer "+App.user.getToken());

          huc.setDoOutput(true);
          
          String inputLine = "{\"idCampaña\":\""+id+"\"}";
          
          os = huc.getOutputStream();
          byte[] input = inputLine.getBytes("utf-8");
          os.write(input,0,input.length);
          
          int rest = huc.getResponseCode();
          if(rest == 200){
            br = new BufferedReader(new InputStreamReader(huc.getInputStream(),"utf-8"));
          
            StringBuilder respu = new StringBuilder();
            String linea = null;
            while((linea = br.readLine())!= null){
                respu.append(linea.trim());
            }
            aux = respu.toString();
            JSONObject jobject = new JSONObject(aux);
            //Pendiente de ver que me devuelve;
          }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
        
     /**
     * Recupera una lista de todas las campañas desde la API.
     *
     * @param url URL base de la API.
     * @return Una lista de objetos Campanias que representan todas las campañas.
     */
    public List<Campanias> listaCampañas(String url){
        List<Campanias> campañas = new ArrayList<Campanias>();
        url += "/campanyan";
        int num = 0;
        
        JSONObject datos = sacarGeneral(url);
        num = datos.getInt("kuantos");
        
        Campanias c = null;
        for (int i = 0; i < num; i++) {
            JSONObject dato = datos.getJSONArray("datos").getJSONObject(i);
            
            Manual m = new Manual(dato.getString("manual_id"), "The Strange");
            c = new Campanias(dato.getInt("idCampaña"), dato.getString("nombre"), dato.getString("descripcion"),m, dato.getBoolean("archivada"), dato.getInt("imagen"));
            campañas.add(c);
        }

        return campañas;
    }
        /**
     * Recupera una lista de campañas que pertenecen al usuario actual desde la API.
     *
     * @param url URL base de la API.
     * @return Una lista de objetos Campanias que representan las campañas del usuario.
     */
    public List<Campanias> listaTusCampañas(String url){
        List<Campanias> campañas = new ArrayList<Campanias>();
        url += "/campanyan/mine";
        int num = 0;
        
        JSONObject datos = sacarGeneral(url);
        num = datos.getInt("kuantos");
        
        Campanias c = null;
        for (int i = 0; i < num; i++) {
            JSONObject dato = datos.getJSONArray("datos").getJSONObject(i);
            Manual m = new Manual(dato.getString("manual_id"), "The Strange");
            c = new Campanias(dato.getInt("idCampaña"), dato.getString("nombre"), dato.getString("descripcion"),m, dato.getBoolean("archivada"), dato.getInt("imagen"));
            campañas.add(c);
        }

        return campañas;
    }
    
        /**
     * Permite unirse a una campaña enviando una petición POST con los datos de la campaña y la contraseña.
     *
     * @param url URL base de la API.
     * @param c Objeto Campanias con los datos de la campaña.
     * @param pwd Contraseña requerida para unirse a la campaña.
     * @return Una cadena indicando el resultado de la operación ("success" o mensaje de error).
     */
    public String unirmeCampañas(String url, Campanias c, String pwd){
        String res = "";
         //Pendiente de saber la ruta
        url += "/campanyan/unirme";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;
        
          URL direc;
        try {
            direc = new URI(url).toURL();
        
          HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
          huc.setRequestMethod("POST");
          
          huc.setRequestProperty("Content-Type", "application/json");
          huc.setRequestProperty("Accept", "application/json");
          huc.setRequestProperty("Authorization", "Bearer "+App.user.getToken());

          huc.setDoOutput(true);
          
          String inputLine = c.join() + ", \"contrasenyan\":\""+pwd+"\"}";
          
            System.out.println(inputLine);
          
          os = huc.getOutputStream();
          byte[] input = inputLine.getBytes("utf-8");
          os.write(input,0,input.length);
          
          int rest = huc.getResponseCode();
            System.out.println(rest);
          if(rest == 200){
            br = new BufferedReader(new InputStreamReader(huc.getInputStream(),"utf-8"));
          
            StringBuilder respu = new StringBuilder();
            String linea = null;
            while((linea = br.readLine())!= null){
                respu.append(linea.trim());
            }
            aux = respu.toString();
              System.out.println(aux);
            JSONObject jobject = new JSONObject(aux);
            res = jobject.getString("success");
          }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
    }

    /**
     * Método auxiliar para realizar una petición GET a la URL especificada y parsear la respuesta como JSONObject.
     *
     * @param url URL a la que se envía la petición GET.
     * @return Un JSONObject con los datos de la respuesta, o null si ocurre un error.
     */
private JSONObject sacarGeneral(String url){
        JSONObject datos = null;
        int respu;
        String ruta = url.replaceAll(" ", "%20");
        try {
            URL direc = new URI(ruta).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("Authorization", "Bearer "+App.user.getToken());
            respu = huc.getResponseCode();
            System.out.println(respu);
            if(respu == 200){
                StringBuilder info = new StringBuilder();
                
                Scanner scc = new Scanner(huc.getInputStream());

                while (scc.hasNext()) {                    
                    info.append(scc.nextLine());     
                }

                datos = new JSONObject(String.valueOf(info));
                
                scc.close();
            }else if(respu == 404){
                System.out.println("Página no encontrada");
            }else{
                System.out.println("Ha habido un fallo en la comunicación");
                System.out.println(respu);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datos;
    }
}

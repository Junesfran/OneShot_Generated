package modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONObject;
import com.mycompany.oneshot.*;
import org.json.JSONArray;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nestor y Asociados
 */
public class TheStrangeRepository {
    
    public List<String> sacarRasgo(String url, String recursion, int num) throws NullPointerException{
        url += "/the_strange/recursion/"+recursion+"/rasgos";
        System.out.println(url);
        System.out.println("AQUI");
        String aux = "";
        List<String> textos = new ArrayList<>();
 
        JSONObject datos = sacarGeneral(url);
        
        JSONObject dato = datos.getJSONArray("datos").getJSONObject(num);
        
        aux += dato.getString("descripcion")+"\n";
        aux += "TRASFERIBLE\n";
        aux += dato.getBoolean("transferible")+"\n";

        textos.add(dato.getString("nombre"));
        textos.add(aux);
        
        return textos;
    }  
    
    public List<String> sacarRasgoRecursion(String url, String recursion) throws NullPointerException{
        url += "/the_strange/recursion/"+recursion+"/rasgos";
        
        int num = 0;
        List<String> rasgos = new ArrayList<>();
        JSONObject datos = sacarGeneral(url);
        
        num = datos.getInt("kuantos");
        for (int i = 0; i < num; i++) {
            rasgos.add(datos.getJSONArray("datos").getJSONObject(i).getString("nombre"));
        }

        return rasgos;
    }
    
    public List<String> sacarDescriptores(String url) throws NullPointerException{
        url += "/the_strange/descriptor";
        int num = 0;
        List<String> clases = new ArrayList<>();
        
        JSONObject datos = sacarGeneral(url);
        
        num = datos.getInt("kuantos");
        for (int i = 0; i < num; i++) {
            clases.add(datos.getJSONArray("datos").getJSONObject(i).getString("nombre"));
        }

        return clases;
    }
    
    public List<String> sacarClases(String url) throws NullPointerException{
        url += "/the_strange/clase";
        int num = 0;
        List<String> clases = new ArrayList<>();
        
        JSONObject datos = sacarGeneral(url);
        
        num = datos.getInt("kuantos");
        for (int i = 0; i < num; i++) {
            clases.add(datos.getJSONArray("datos").getJSONObject(i).getString("nombre"));
        }

        return clases;
    }  
    
    
    public List<String> sacarClase(String url, int clase) throws NullPointerException{
        url += "/the_strange/clase";
        System.out.println(url);
        System.out.println(clase);
        String aux = "";
        List<String> textos = new ArrayList<>();
 
        JSONObject datos = sacarGeneral(url);
        
        JSONObject dato = datos.getJSONArray("datos").getJSONObject(clase);
        
        aux += dato.getString("descripcion")+"\n";
        aux += "CANTIDAD DE DISPOSITIVOS BASE\n";
        aux += dato.getInt("minimoDispositivos")+"\n";
        aux += "TRASLACIÓN\n";
        aux += dato.getString("traslacion")+"\n";
        aux += "VINCULO INICIAL\n";
        
        String vinculos = dato.getString("vinculos").replace("/", "\n");
        vinculos = vinculos.replace("_", ". ");
        
        aux += vinculos;
        
        textos.add(dato.getString("nombre"));
        textos.add(aux);
        
        return textos;
    }  
    
    
    public List<String> sacarRecursion(String url, String recursion) throws NullPointerException{
        url += "/the_strange/recursion/"+recursion;
        String aux = "";
        List<String> textos = new ArrayList<>();
 
        JSONObject datos = sacarGeneral(url);
        
        aux += datos.getString("resumen")+"\n";
        aux += "FORMA\n";
        aux += datos.getString("forma")+"\n";
        aux += "CHISPA\n";
        aux += datos.getString("chispa")+"\n";
        aux += "CONEXIÓN THE STRANGE\n";
        aux += datos.getString("conexion_the_strange")+"\n";
        aux += "CONEXIÓN CON LA TIERRA\n";
        aux += datos.getString("conexion_tierra")+"\n";

        textos.add(datos.getString("nombre"));
        textos.add(aux);
        textos.add(datos.getInt("nivel")+" ("+datos.getInt("nivel")*3+")");
        
        return textos;
    }  
    
    
    public List<String> sacarCriatura(String url, String criatura) throws NullPointerException{
        url += "/the_strange/creaturador/"+criatura;
        String aux = "";
        List<String> textos = new ArrayList<>();
 
        JSONObject datos = sacarGeneral(url);
        
        aux += datos.getString("descripcion")+"\n";
        aux += "MOTIVACIÓN\n";
        aux += datos.getString("motivacion")+"\n";
        aux += "ENTORNO\n";
        aux += datos.getString("entorno")+"\n";
        aux += "SALUD\n";
        aux += datos.getInt("salud")+"\n";
        aux += "DAÑO QUE INFLINGE\n";
        aux += datos.getInt("daño")+"\n";
        aux += "MOVIMIENTO\n";
        aux += datos.getString("movimiento")+"\n";
        aux += "COMBATE\n";
        aux += datos.getString("combate")+"\n";
        aux += "INTERRACIÓN\n";
        aux += datos.getString("iteraccion")+"\n";
        aux += "USO\n";
        aux += datos.getString("uso")+"\n";
        aux += "BOTÍN\n";
        aux += datos.getString("botin")+"\n";

        textos.add(datos.getString("nombre"));
        textos.add(aux);
        textos.add(datos.getInt("nivel")+" ("+datos.getInt("nivel")*3+")");
        
        return textos;
    }  
    
      
    public List<String> sacarCriaturasRecursion(String url, String recursion) throws NullPointerException{
            url += "/the_strange/recursion/"+recursion+"/creaturas";

        
        int num = 0;
        List<String> clases = new ArrayList<>();
        

        JSONObject datos = sacarGeneral(url);
        
        num = datos.getInt("kuantos");
        for (int i = 0; i < num; i++) {
            clases.add(datos.getJSONArray("datos").getString(i));
        }

        return clases;
    }  
    
    public List<String> sacarRecursiones(String url){
        url += "/the_strange/recursion";
        int num = 0;
        List<String> clases = new ArrayList<>();
        
        JSONObject datos = sacarGeneral(url);
        
        num = datos.getInt("kuantos");
        for (int i = 0; i < num; i++) {
            clases.add(datos.getJSONArray("datos").getJSONObject(i).getString("nombre"));
        }

        return clases;
    }  
    
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

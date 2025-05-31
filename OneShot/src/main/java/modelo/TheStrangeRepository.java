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
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nestor y Asociados
 */
public class TheStrangeRepository {
    
    
        
    public List<String> sacarCriatura(String url, String criatura) throws NullPointerException{
        url += "/the_strange/creaturador/"+criatura;
        String aux = "";
        List<String> textos = new ArrayList<>();
 
        JSONObject datos = sacarGeneral(url);
        
        aux += datos.getString("descripcion")+"\n";
        aux += "Motivación:\n";
        aux += datos.getString("motivacion")+"\n";
        aux += "Entorno:\n";
        aux += datos.getString("entorno")+"\n";
        aux += "Salud:\n";
        aux += datos.getInt("salud")+"\n";
        aux += "Daño que Inflinge:\n";
        aux += datos.getInt("daño")+"\n";
        aux += "Movimiento:\n";
        aux += datos.getString("movimiento")+"\n";
        aux += "Combate:\n";
        aux += datos.getString("combate")+"\n";
        aux += "Interacción:\n";
        aux += datos.getString("iteraccion")+"\n";
        aux += "Uso:\n";
        aux += datos.getString("uso")+"\n";
        aux += "Botín:\n";
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
        try {
            URL direc = new URI(url).toURL();
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

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
    
    
    
    
    public int numeros(String url){
        int num = 0;
        int respu;
        try {
            URL direc = new URI(url).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiVkMvM2RpN2tOb3J0K043UktBOTIvN2VuaEM1TkEzL0FraHg4cEN3YmIyR2RYNDVxdHFRc2VsMlhtaFMyMTA5VHRMbWNiOUEvWUU4RjRTR1lVQ0ZkR3o2L3RHNlNuNGFRbHk5NFZwcGxNQ0ZSYmk4aG56YlllcFJKRVJTbnlrU2ZLdFQxcitjU3lHc3FJVmd5VmNlNlZBPT0ifQ.atmxl1oEAH_45l621Lm9u85TxmS9KiHZZp89U9vIkSI");
            respu = huc.getResponseCode();
            
            if(respu != 200){
                System.out.println("Ha habido un fallo en la comunicación");
            }else{
                StringBuilder info = new StringBuilder();
                
                Scanner sc = new Scanner(direc.openStream());

                while (sc.hasNext()) {                    
                    info.append(sc.nextLine());     
                }
                //ME pasan datos, transformar el stringBUilder en datos
                
                
                JSONObject datos = new JSONObject(String.valueOf(info));
                respu = datos.getInt("kuantos");
                
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return num;
    }
    
    public List<String> sacarRecursiones(String url){
        url += "/the_strange/recursion";
        int num = numeros(url);
        List<String> clases = new ArrayList<>();
        int respu;
        String[] cachos;
        String fin = "";
        try {
            URL direc = new URI(url).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("Authorization", "Bearer "+App.user.getToken());
            respu = huc.getResponseCode();
            
            if(respu != 200){
                System.out.println("Ha habido un fallo en la comunicación");
            }else{
                StringBuilder info = new StringBuilder();
                
                Scanner sc = new Scanner(direc.openStream());

                while (sc.hasNext()) {                    
                    info.append(sc.nextLine());     
                }
                //ME pasan datos, transformar el stringBUilder en datos
                
                
                JSONObject datos = new JSONObject(String.valueOf(info));
                for (int i = 0; i < num; i++) {
                    clases.add(datos.getJSONArray("datos").getJSONObject(i).getString("nombre"));
                }
                
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clases;
    } 
    public List<String> sacarGeneral(String url){
        url += "/the_strange/recursion";
        int num = numeros(url);
        List<String> clases = new ArrayList<>();
        int respu;
        String[] cachos;
        String fin = "";
        try {
            URL direc = new URI(url).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
            huc.setRequestProperty("Authorization", "Bearer "+App.user.getToken());
            respu = huc.getResponseCode();
            
            if(respu != 200){
                System.out.println("Ha habido un fallo en la comunicación");
            }else{
                StringBuilder info = new StringBuilder();
                
                Scanner sc = new Scanner(direc.openStream());

                while (sc.hasNext()) {                    
                    info.append(sc.nextLine());     
                }
                //ME pasan datos, transformar el stringBUilder en datos
                
                
                JSONObject datos = new JSONObject(String.valueOf(info));
                for (int i = 0; i < num; i++) {
                    clases.add(datos.getJSONArray("data").getJSONObject(i).getString("nombre"));
                }
                
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clases;
    } 
  
      
}

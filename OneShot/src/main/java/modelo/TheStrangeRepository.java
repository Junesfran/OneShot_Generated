package modelo;

import java.io.BufferedReader;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Nestor y Asociados
 */
public class TheStrangeRepository {
  public List<String> sacarGeneral(String url, int index){
        url += "/the_strange/recursion/Ardeyn";
        List<String> clases = new ArrayList<>();
        int respu;
        String[] cachos;
        String fin = "";
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
                for (int i = 0; i < index; i++) {
                    clases.add(datos.getJSONArray("results").getJSONObject(i).getString("index"));
                }
                
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clases;
    } 
  
    public String Registro(String url, String user, String pwd){
        url += "/user";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;
      try {
          URL direc = new URI(url).toURL();
          HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
          huc.setRequestMethod("POST");
          
          huc.setRequestProperty("Content-Type", "application/json");
          huc.setRequestProperty("Accept", "application/json");
          huc.setDoOutput(true);
          
          String inputLine = "{\"username\":"+user+",\"password\":"+pwd+"}";
          System.out.println(inputLine);
          os = huc.getOutputStream();
          byte[] input = inputLine.getBytes("utf-8");
          os.write(input,0,input.length);
          
          
          br = new BufferedReader(new InputStreamReader(huc.getInputStream(),"utf-8"));
          
          StringBuilder respu = new StringBuilder();
          String linea = null;
          while((linea = br.readLine())!= null){
              respu.append(linea.trim());
          }
          aux = respu.toString();
      } catch (Exception e) {
          e.printStackTrace();
      }
        return aux;
    }  
    
    public static String Login(String url, String user, String pwd){
        url += "/user/login";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;
      try {
          URL direc = new URI(url).toURL();
          HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
          huc.setRequestMethod("POST");
          
          huc.setRequestProperty("Content-Type", "application/json");
          huc.setRequestProperty("Accept", "application/json");
          huc.setDoOutput(true);
          
          String inputLine = "{\"username\":\""+user+"\",\"password\":\""+pwd+"\"}";
          
          os = huc.getOutputStream();
          byte[] input = inputLine.getBytes("utf-8");
          os.write(input,0,input.length);
          
          
          br = new BufferedReader(new InputStreamReader(huc.getInputStream(),"utf-8"));
          
          StringBuilder respu = new StringBuilder();
          String linea = null;
          while((linea = br.readLine())!= null){
              respu.append(linea.trim());
          }
          aux = respu.toString();
      } catch (Exception e) {
          e.printStackTrace();
      }
        return aux;
    }  
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import com.mycompany.oneshot.App;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author Nestor y Asociados
 */
public class UsuarioRepository {
    public int registro(String url, String user, String pwd){
        int rest = 0;
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
          
          
          
          String inputLine = "{\"username\":\""+user+"\",\"password\":\""+pwd+"\"}";
          System.out.println(inputLine);
          os = huc.getOutputStream();
          byte[] input = inputLine.getBytes("utf-8");
          os.write(input,0,input.length);
          
          rest = huc.getResponseCode();

      } catch (Exception e) {
          e.printStackTrace();
      }
        return rest;
    }  
    
    public static void Login(String url, String user, String pwd) throws IOException, URISyntaxException{
        url += "/user/login";
        String aux = "";
        BufferedReader br = null;
        OutputStream os = null;
        
          URL direc = new URI(url).toURL();
          HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
          huc.setRequestMethod("POST");
          
          huc.setRequestProperty("Content-Type", "application/json");
          huc.setRequestProperty("Accept", "application/json");
          huc.setDoOutput(true);
          
          String inputLine = "{\"username\":\""+user+"\",\"password\":\""+pwd+"\"}";
          System.out.println(inputLine);
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
            App.user.setToken(jobject.getString("token"));
            App.user.setUser(user);
          }else if(rest == 401){
              throw new IOException();
          }else if(rest == 404){
              System.out.println("Página no encontrada");
          }

    }
}

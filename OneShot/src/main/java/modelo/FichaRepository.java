/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

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
import org.json.JSONObject;

/**
 *
 * @author Nestor y Asociados
 */
public class FichaRepository {


   public int mandarFicha(String url, Ficha f){
       int res = 0;
        url += "/ficha";
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
          
          String inputLine = f.toString();
          
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
            System.out.println(aux);
            JSONObject jobject = new JSONObject(aux);
            //Pendiente de ver que me devuelve;
          }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
   }

   public int AsignarFichaCampania(String url, Ficha f){
       int res = 0;
       //Pendiente de saber la ruta
        url += "/ficha";
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
          
          String inputLine = f.toString();
          
          os = huc.getOutputStream();
          byte[] input = inputLine.getBytes("utf-8");
          os.write(input,0,input.length);
          
          res = huc.getResponseCode();
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
            //Pendiente de ver que me devuelve;
          }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;
   }
   
   
   
   public List<String> listarFichas(String url){
        int num = 0;
       List<Ficha> fichas = new ArrayList<Ficha>();
       //No se sabe la ruta concreta
        url += "/ficha";
       JSONObject datos = sacarGeneral(url);

       num = datos.getInt("kuantos");
       Ficha f = null;
       for(int i = 0; i < num; i++){
           JSONObject dato = datos.getJSONArray("datos").getJSONObject(i);

            //Pendiende de saber que devuelve y como está el constructor
           f = new Ficha();
           fichas.add(f);
       }
        return fichas;
   }


   public List<Ficha> listarFichasCampanias(String url){
       int num = 0;
       List<Ficha> fichas = new ArrayList<Ficha>();
       //No se sabe la ruta concreta
        url += "/ficha";
       JSONObject datos = sacarGeneral(url);

       num = datos.getInt("kuantos");
       Ficha f = null;
       for(int i = 0; i < num; i++){
           JSONObject dato = datos.getJSONArray("datos").getJSONObject(i);

            //Pendiende de saber que devuelve y como está el constructor
           f = new Ficha();
           fichas.add(f);
       }
        return fichas;
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
}

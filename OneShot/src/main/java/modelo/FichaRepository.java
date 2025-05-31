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
    //pendiente de cambiar como se guarda en la API
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
          
          //Pendiente a ver que le tengo que mandar
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
   
   public int numeros(String url){
            int num = 0;
            int respu;
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
                    respu = datos.getInt("kuantos");

                    sc.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return num;
        }  
   
   
   public List<String> listarFichas(String url){
       List<String> fichas = new ArrayList<String>();

        url += "/ficha";
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

                JSONObject datos = new JSONObject(String.valueOf(info));
                for (int i = 0; i < num; i++) {
                    clases.add(datos.getJSONArray("data").getJSONObject(i).getString("nombre"));
                }

                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fichas;
   }
   public List<String> listarFichasCampanias(String url){
       List<String> fichas = new ArrayList<String>();

        url += "/ficha";
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

                JSONObject datos = new JSONObject(String.valueOf(info));
                for (int i = 0; i < num; i++) {
                    clases.add(datos.getJSONArray("data").getJSONObject(i).getString("nombre"));
                }

                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fichas;
   }
}

package dao;

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
/** Obtiene el ID de usuario y ficha asociado al usuario actual a partir de una URL y un ID de campaña.
 * 
 * @param url URL base para la consulta.
 * @param id  ID de la campaña.
 * @return    ID de la ficha si existe, 0 en caso contrario.
 * @throws NullPointerException si la respuesta es nula.
 */
    public int sacarIdUser(String url, int id) throws NullPointerException{
        int aux = 0;
        url += "/campanyan/"+id+"/fichas";
        
        int num = 0;
        List<String> rasgos = new ArrayList<>();
        JSONArray datos = sacarGeneral2(url);
        
        num = datos.length();
        for (int i = 0; i < num; i++) {
            //Los nombres de usuarios son unique
            if(App.user.getUser().equals(datos.getJSONObject(i).getString("username"))){
                App.user.setId(datos.getJSONObject(i).getInt("user_id"));
                if(!datos.getJSONObject(i).isNull("ficha_id")){
                    aux = datos.getJSONObject(i).getInt("ficha_id");
                }
            }
        }
        return aux;
    }
    
/**
 * Obtiene un texto formateado con la información del equipo disponible.
 * 
 * @param url URL base para la consulta.
 * @return    Cadena de texto con los datos del equipo.
 * @throws NullPointerException si la respuesta es nula.
 */
    public String textEquipop(String url) throws NullPointerException{
        url += "/the_strange/equipo";
        String aux = "";
        List<String> textos = new ArrayList<>();
 
        JSONArray datos = sacarGeneral2(url);
        
        for (int i = 0; i < datos.length(); i++) {
            aux += datos.getJSONObject(i).getString("nombre")+"\n";
            aux += datos.getJSONObject(i).getString("peso")+"\t";
            aux += datos.getJSONObject(i).getInt("precio")+"\t";
            aux += datos.getJSONObject(i).getString("tipo")+"\n";
            aux += datos.getJSONObject(i).getString("nota")+"\n";
            aux += "-----------------------------------------------------------\n\n";
        }

        
        return aux;
    } 
    /**
 * Obtiene la descripción y vínculos de un descriptor específico.
 * 
 * @param url URL base para la consulta.
 * @param num Índice del descriptor a obtener.
 * @return    Lista con el nombre y la descripción formateada del descriptor.
 * @throws NullPointerException si la respuesta es nula.
 */
    public List<String> textDescriptor(String url, int num) throws NullPointerException{
        url += "/the_strange/descriptor";
        String aux = "";
        List<String> textos = new ArrayList<>();
 
        JSONObject datos = sacarGeneral(url);
        
        JSONObject dato = datos.getJSONArray("datos").getJSONObject(num);
        
        aux += dato.getString("descripcion")+"\n";
        aux += "VINCULO\n";
        
        String vinculos = dato.getString("vinculo").replace("/", "\n");
        vinculos = vinculos.replace("_", ". ");
        
        aux += vinculos;

        textos.add(dato.getString("nombre"));
        textos.add(aux);
        
        return textos;
    }  
    /**
 * Obtiene la información de un apartado específico de una recursión.
 * 
 * @param url       URL base para la consulta.
 * @param recursion Nombre de la recursión.
 * @param num       Índice del apartado a obtener.
 * @return          Lista con el título y el texto formateado del apartado.
 * @throws NullPointerException si la respuesta es nula.
 */
    public List<String> textApartado(String url, String recursion, int num) throws NullPointerException{
        url += "/the_strange/recursion/"+recursion;
        String aux = "";
        List<String> textos = new ArrayList<>();
 
        JSONObject datos = sacarGeneral(url);
        
        JSONObject dato = datos.getJSONArray("apartados").getJSONObject(num);
        
        aux += "TIPO\n";
        aux += dato.getString("tipo")+"\n";
        aux += "TEXTO\n";
        aux += dato.getString("texto")+"\n";

        textos.add(dato.getString("titulo"));
        textos.add(aux);
        
        return textos;
    }  
    
    /**
 * Obtiene la descripción y si es transferible un rasgo específico de una recursión.
 * 
 * @param url       URL base para la consulta.
 * @param recursion Nombre de la recursión.
 * @param num       Índice del rasgo a obtener.
 * @return          Lista con el nombre y la descripción formateada del rasgo.
 * @throws NullPointerException si la respuesta es nula.
 */
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
    /**
 * Obtiene la lista de nombres de rasgos de una recursión específica.
 * 
 * @param url       URL base para la consulta.
 * @param recursion Nombre de la recursión.
 * @return          Lista de nombres de rasgos.
 * @throws NullPointerException si la respuesta es nula.
 */
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
    /**
 * Obtiene la lista de nombres de descriptores disponibles.
 * 
 * @param url URL base para la consulta.
 * @return    Lista de nombres de descriptores.
 * @throws NullPointerException si la respuesta es nula.
 */
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
    /**
 * Obtiene la lista de nombres de clases disponibles.
 * 
 * @param url URL base para la consulta.
 * @return    Lista de nombres de clases.
 * @throws NullPointerException si la respuesta es nula.
 */
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
    
    
    /**
 * Obtiene la información detallada de una clase específica.
 * 
 * @param url   URL base para la consulta.
 * @param clase Índice de la clase a obtener.
 * @return      Lista con el nombre y la descripción formateada de la clase.
 * @throws NullPointerException si la respuesta es nula.
 */
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
    
    /**
 * Obtiene la información detallada de una recursión específica.
 * 
 * @param url       URL base para la consulta.
 * @param recursion Nombre de la recursión.
 * @return          Lista con el nombre, la descripción y el nivel de la recursión.
 * @throws NullPointerException si la respuesta es nula.
 */
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
    /**
 * Obtiene la lista de títulos de los apartados de una recursión específica.
 * 
 * @param url       URL base para la consulta.
 * @param recursion Nombre de la recursión.
 * @return          Lista de títulos de apartados.
 * @throws NullPointerException si la respuesta es nula.
 */
    public List<String> sacarApartados(String url, String recursion) throws NullPointerException{
        url += "/the_strange/recursion/"+recursion;
        int num = 0;
        List<String> apart = new ArrayList<>();
        

        JSONObject datos = sacarGeneral(url);
       JSONArray apartados = datos.getJSONArray("apartados");
        
        num = apartados.length();
        for (int i = 0; i < num; i++) {
            apart.add(apartados.getJSONObject(i).getString("titulo"));
        }
        System.out.println(apart);
        return apart;
    }  

    
    /**
 * Obtiene la información detallada de una criatura específica.
 * 
 * @param url      URL base para la consulta.
 * @param criatura Nombre de la criatura.
 * @return         Lista con el nombre, la descripción y el nivel de la criatura.
 * @throws NullPointerException si la respuesta es nula.
 */
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
    
      /**
 * Obtiene la lista de nombres de criaturas de una recursión específica.
 * 
 * @param url       URL base para la consulta.
 * @param recursion Nombre de la recursión.
 * @return          Lista de nombres de criaturas.
 * @throws NullPointerException si la respuesta es nula.
 */
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
    /**
 * Obtiene la lista de nombres de todas las recursiones disponibles.
 * 
 * @param url URL base para la consulta.
 * @return    Lista de nombres de recursiones.
 */
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
    
    /**
     * Hace lo mismo que los que sacanGenal pero este me devuelve un JSONArray
     * @param url
     * @return 
     */
    private JSONArray sacarGeneral2(String url){
        JSONArray datos = null;
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

                datos = new JSONArray(String.valueOf(info));
                
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

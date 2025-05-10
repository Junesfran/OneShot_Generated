/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Diurno
 */
public class ClasesRepository {
    
/**
 *
 * Este método llama a la API y le solicita todas las clases que estén disponibles
 * a través de index en vez de name, ya que de esta manera al no tener la primera
 * letra mayuscula pruedo utiliarlo para cargar la url mejor de la clase que se
 * seleccione.
 */
    public List<String> sacarGeneral(String url, int index){
        List<String> clases = new ArrayList<>();
        int respu;
        String[] cachos;
        String fin = "";
        try {
            URL direc = new URI(url).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
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

/**
 *
 * Este método solicita a la API que le devuelva el dato count donde tiene guardado
 * el total de clases que tiene registrada la API.
 */
    public int sacarNumero(String url){
        int index = 0;
        int respu;
        try {
            URL direc = new URI(url).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
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
                index = datos.getInt("count");
                
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return index;
    }
    
/**
 *
 *Este método solicita a la API y devolvera un entero con el dado necesario para calcular
 * la vida máxima según la clase seleccionada.
 */
    public int sacarHitPoint(String url, String clase){
        int supu = 0;
        int respu;
        String newUrl = url+"/"+clase;
        String fin = "";
        try {
            URL direc = new URI(newUrl).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
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
                supu = datos.getInt("hit_die");
                
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return supu;
    } 
/**
 *
 * Este método solicita a la API y devolverá una lista de Strings que devolvera a que es 
 * competente o hábil la clase que se haya seleccionado
 */
    public List<String> sacarCompetencias(String url, String clase){
        List<String> competencias = new ArrayList<>();
       
        String subUrl = url+"/"+clase;
        int respu;
        try {
            URL direc = new URI(subUrl).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
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
                int todos = datos.getJSONArray("proficiencies").length();
                for (int i = 0; i < todos; i++) {
                    competencias.add(datos.getJSONArray("proficiencies").getJSONObject(i).getString("name"));
                }
                
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return competencias;
    }
 /**
 *
 * Este método devuelve una lista con el equipamiento inicial de cada clase 
 * sacando datos de distintas partes de la API.
 * Primero saca los datos de los que es el kit inicial. Las cadenas de texto del
 * equipamiento inicial son spliteadas y si la segunda palabra es pack, es que 
 * es un pack con distintos items, por lo que se saca el nombre del pack y se 
 * llama a otro método que saca el contenido de los packs guardadolo en una segunda
 * lista. Si el equipo no era un pack se guarda en la lista principal que se devolvera.
 * Es caso de que haya algún item pero que su segunda palbra no fuera pack se guarda
 * directamente en la lista principal.
 * Después se llama a otro método que devuelva otro tipo de equipamiento donde 
 * tienes que elegir con que te tienes que quedar de entre dos opciones. Esto se
 * guarda en una tercera lista y se cargan los datos en la lista principal.
 */
    public List<String> sacarEquipamiento(String url, String clase){
        List<String> equipamiento = new ArrayList<>();
       
        String subUrl = url+"/"+clase;
        int respu;
        try {
            URL direc = new URI(subUrl).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
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
                int todos = datos.getJSONArray("starting_equipment").length();
                for (int i = 0; i < todos; i++) {
                    String equip = datos.getJSONArray("starting_equipment").getJSONObject(i).getJSONObject("equipment").getString("name");
                    String[] cachos = equip.split(" ");
                    if(cachos.length >= 2){
                        if(cachos[1].equalsIgnoreCase("Pack")){
                             String pack = sacarEquipamientoIndex(subUrl, i);
                             List<String> contenido = sacarContenidoEquipamiento(pack);
                             for (String c : contenido) {
                                 equipamiento.add(c);
                             }
                        }else{
                           equipamiento.add(equip); 
                        } 
                    }else{
                        equipamiento.add(equip); 
                    }   
                }
                List<String> eleccion = sacarEquipamientoEleccion(subUrl);
                for (String e : eleccion) {
                    equipamiento.add(e);
                }
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipamiento;
    }
    
 /**
 *
 * Este método es llamado por el método anterior y se ocupa de devolver el nombre
 * del pack del que se quiere saber que tiene dentro.
 */
    public String sacarEquipamientoIndex(String url,int index){
        String fin ="";
        int respu;
        try {
            URL direc = new URI(url).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
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
                    fin = datos.getJSONArray("starting_equipment").getJSONObject(index).getJSONObject("equipment").getString("index");
                
                System.out.println(fin);
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fin;
    }
    
/**
 *
 * Este método se mueve a la localización de la API donde se guarda el equipamiento
 * del pack que se desea saber que tiene y lo devuelve en forma de lista.
 */
    public List<String> sacarContenidoEquipamiento(String pack){
        String url = "https://www.dnd5eapi.co/api/equipment";
        List<String> equipamiento = new ArrayList<>();
       
        String subUrl = url+"/"+pack;
        String fin ="";
        int respu;
        try {
            URL direc = new URI(subUrl).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
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
                int todos = datos.getJSONArray("contents").length();
                for (int i = 0; i < todos; i++) {
                    equipamiento.add(datos.getJSONArray("contents").getJSONObject(i).getJSONObject("item").getString("name"));
                }
                
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipamiento;
    }
    
/**
 *
 * Este método se posiciona en la parte de la API del contenido de una clase, 
 * solicitando a esta y devolviento en forma de lista las decisiones de inventario
 * que van a parte del equipamiento inicial.
 */
    public List<String> sacarEquipamientoEleccion(String url){
        List<String> equipamiento = new ArrayList<>();
        String fin ="";
        int respu;
        try {
            URL direc = new URI(url).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
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
                int todos = datos.getJSONArray("starting_equipment_options").length();
                for (int i = 0; i < todos; i++) {
                    equipamiento.add(datos.getJSONArray("starting_equipment_options").getJSONObject(i).getString("desc"));
                }

                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return equipamiento;
    }
    
/**
 *
 * Este método indaga en el apartado de niveles de la clase que le solicite para
 * devolver que mejoras tiene con cada subida de nivel esa clase.
 * Lo devuelve como cadena de texto porque al meterlo en un TextBox es más sencillo
 * de darle formato al texto desde aquí.
 */
    public String sacarNiveles(String url, String clase){
        String subUrl = url+"/"+clase+"/levels";
        String fin ="Level\tbonus\tProf.\tfeature\n";
        fin += "----------------------------------------------------------------------------------------------------\n";
        int respu;
        try {
            URL direc = new URI(subUrl).toURL();
            HttpURLConnection huc = (HttpURLConnection)direc.openConnection();
            huc.setRequestMethod("GET");
            respu = huc.getResponseCode();
            
            if(respu != 200){
                System.out.println("Ha habido un fallo en la comunicación");
            }else{
                StringBuilder info = new StringBuilder();
                
                Scanner sc = new Scanner(direc.openStream());

                while (sc.hasNext()) {                    
                    info.append(sc.nextLine());     
                }

                JSONArray datos = new JSONArray(String.valueOf(info));
                int niveles = datos.length();
                for (int i = 0; i < niveles; i++) {
                    int todos = datos.getJSONObject(i).getJSONArray("features").length();
                    fin += datos.getJSONObject(i).getInt("level")+"\t";
                    fin += datos.getJSONObject(i).getInt("ability_score_bonuses")+"\t";
                    fin += datos.getJSONObject(i).getInt("prof_bonus")+"\t";
                    for (int j = 0; j < todos; j++) {
                        fin += datos.getJSONObject(i).getJSONArray("features").getJSONObject(j).getString("name")+", ";
                    }
                    fin += "\n";
                }
                sc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fin;
    }
    
/**
 *
 * Este método se mueve al apartado de los hechizos que tiene la clase seleccionada
 * en caso de que la tenga, sino, no devuelve nada.
 * Devuelve una cadena de texto porque al mostarlo en un textBox es más sencillo darle formato 
 * desde aquí.
 */
     public String sacarHechizo(String ruta, String clase){
        String fin ="Nivel\tHechizos\n";
        fin += "-----------------------------------------\n";
        String subRuta = ruta+"/"+clase+"/spells";
        int respu;
        try {
            URL url = new URI(subRuta).toURL();
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            respu = huc.getResponseCode();
            
            if(respu != 200){
                System.out.println("comunicación no encontrada");
            }else{
                StringBuilder sb = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                
                while(sc.hasNext()){
                    sb.append(sc.nextLine());
                }
                
                JSONObject datos = new JSONObject(String.valueOf(sb));
                int count = datos.getInt("count");
                int nivel;
                int reyPista = -1;
                List<String> hechi = new ArrayList<>();
                for (int i = 0; i < count; i++) {
                    nivel = datos.getJSONArray("results").getJSONObject(i).getInt("level");
                    if (nivel != reyPista) {
                        fin += "\n"+nivel;
                        reyPista = nivel;
                    }
                    fin += "\t "+datos.getJSONArray("results").getJSONObject(i).getString("name")+"\n";
                    //Comentado otro formato por si decido cambiarlo
//                    nivel = datos.getJSONArray("results").getJSONObject(i).getInt("level");
//                    if (nivel == reyPista) {
//                        hechi.add(datos.getJSONArray("results").getJSONObject(i).getString("name"));
//                    }else{
//                        fin += nivel-1+"\t";
//                        for (String h : hechi) {
//                            fin += h+", ";
//                        }
//                        fin += "\n";
//                        reyPista = nivel;
//                        i--;
//                        hechi.clear();
//                    }
                }
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return fin;
    }
}

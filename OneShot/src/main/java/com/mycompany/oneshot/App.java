package com.mycompany.oneshot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import modelo.*;

/**
 * Esta clase es generada automáticamente por Netbeans al crear
 * un proyecto con javax. 
 * Este funcionaria como principal que gestiona tanto el arranque
 * la aplicación así como de tener valores publicos estaticos que
 * se utilizan en todo el programa y se necesita que san accesible.
 */
public class App extends Application {

    private static Scene scene;
    //Datos generales
    public static Usuario user = new Usuario();
    
    //Las conexiones
    //public static String direc = "http://82.199.59.159:13013";
    public static String direc = "http://piola.cloudns.nz:13013";
    
    //Datos a la hora de entrar en campaña
    public static Campanias campañaAct;
    public static Ficha fichaACt;
    
    
/**
 *
 * Método que se escarga del cargar la primera vista de la 
 * aplicación así como de fijar las dimensiones de la aplicación
 * y evitar la redimensión de las ventanas.
 *
 */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"),1000,800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

/**
*
* Este método cuando es llamado cierra la ventana actual y abre la 
* la que le hayas especificado en el parámetro
* @param fxml - fxml de la ventana que es abierta por el programa
* @exception IOEXception - Este puede ser lanzado al no encontrar el fxml especificado o bien porque no existe o porque no está en 
 */
    static void setRoot(String fxml) throws IOException {
        Parent root = loadFXML(fxml);
        scene.setRoot(root);

        Stage stage = (Stage) scene.getWindow();
        stage.sizeToScene();
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
         launch();
        
//        try {
//            UsuarioRepository.Login(direc, "juan", "juan");
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (URISyntaxException ex) {
//            ex.printStackTrace();
//        }
//        
//        FichaRepository fRep = new FichaRepository();
//        
//        fRep.listarFichas(direc);
        
    }

}

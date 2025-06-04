package com.mycompany.oneshot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import modelo.FichaRepository;
import modelo.Usuario;
import modelo.UsuarioRepository;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Usuario user = new Usuario();
//    public static String direc = "http://82.199.59.159:13013";
    public static String direc = "http://piola.cloudns.nz:13013";
    public static final String manualID = "the_stange";
    //private String direc = "http://192.168.1.32:8080";
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"),1000,800);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

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
        // launch();
        
        try {
            UsuarioRepository.Login(direc, "juan", "juan");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }
        
        FichaRepository fRep = new FichaRepository();
        
        fRep.listarFichas(direc);
        
//        try {
//            c.Botones();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

}
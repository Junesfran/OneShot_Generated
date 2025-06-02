package com.mycompany.oneshot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import modelo.Usuario;
import vista.VistaPrincipal;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    public static Usuario user = new Usuario();
    public static String direc = "http://piola.cloudns.nz:13013";
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
        launch();
        VistaPrincipal vp = new VistaPrincipal();
        Controller c = new Controller();
        
//        try {
//            c.Botones();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
    }

}
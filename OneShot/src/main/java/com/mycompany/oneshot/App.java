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
    
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("login"), 640, 480);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
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
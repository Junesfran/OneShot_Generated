/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oneshot;

import dao.UsuarioRepository;
import dao.TheStrangeRepository;
import dao.CampaniasRepository;
import com.mycompany.oneshot.App;
import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modelo.*;

/**
 *
 * @author Nestor y Asociados
 */
public class Controller {
    private TheStrangeRepository tsRepo = new TheStrangeRepository();
    private CampaniasRepository cRepo = new CampaniasRepository();
    private UsuarioRepository uRepo = new UsuarioRepository();



    
//    private int puerto = 13013;
    
    @FXML
    private GridPane gridMaster;
    
    @FXML
    private GridPane gridJugador;
    
    @FXML
    private GridPane gridArchivado;

    @FXML
    private TabPane tabInicial;
    
    @FXML
    private TextField tNombre;
    
    @FXML
    private PasswordField tPassword;
    
    @FXML
    private Label lFallo;
    
    @FXML
    private TextField textNombreCamp;
    
    @FXML
    private PasswordField textPWDCamp;
    
    @FXML
    private ComboBox cManuales;
    
    /**
     * Método que gestiona el inicio de sesión del usuario.
     * Obtiene el nombre de usuario y la contraseña desde los campos de texto.
     * Si las credenciales manadadas a la API son válidas, cambia a la vista principal.
     * Ademas se guarda en una variable estatica del principal un token de sesión
     * que irá en todas las cabeceras de las peticiones, permitiendo saber que 
     * usuario está haciendo cada petición.
     * En caso de error de autenticación, muestra un mensaje de fallo en pantalla
     * 
     * 
     */
    @FXML
    private void login(){
        String user = tNombre.getText();
        String pwd = tPassword.getText();
        
        try{
            uRepo.Login(App.direc, user, pwd);
            System.out.println(App.user);
            App.setRoot("principal");
        } catch (IOException e) {
            lFallo.setVisible(true);
            lFallo.setText("Credenciales no admitidas");
            tPassword.setText("");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Método que gestiona el registro de un nuevo usuario.
     * Verifica que los campos de usuario y contraseña no estén vacíos.
     * Si los campos son válidos, intenta registrar al usuario mediante uRepo.
     * Muestra un mensaje dependiendo del resultado del intento de registro.
     *
     */
    @FXML
    private void sign(){
        String user = tNombre.getText();
        String pwd = tPassword.getText();
        if(pwd.equalsIgnoreCase("") || user.equalsIgnoreCase("") || pwd.equalsIgnoreCase(" ") || user.equalsIgnoreCase(" ")){
            tPassword.setText("");
            lFallo.setVisible(true);
            tNombre.setText(""); 
            lFallo.setText("Introduce valores en los campos");
        }else{
            int rest= uRepo.registro(App.direc, user, pwd);
            System.out.println(rest);
            tPassword.setText("");
            lFallo.setVisible(true);
            if(rest == 200){
               tNombre.setText(""); 
               lFallo.setText("Usuario Creado :D");
            }else{
                lFallo.setText("Credenciales ya pilladas");
            }
        }
        
        
        
        

    }
    
    @FXML
    public void editorCampania(){
        try {
            App.setRoot("createCampain");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void crearCampania() {
        try {
            App.setRoot("prueba");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

    
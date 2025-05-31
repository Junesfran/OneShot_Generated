/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oneshot;

import com.mycompany.oneshot.App;
import java.io.IOException;
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
    
    @FXML
    public void Botones() throws IOException {
        //Cambiar el valor inicial por el llamado a un método que me diga el
        //número de elementos
        int valorInicial = 10;
        int tab = tabInicial.getSelectionModel().getSelectedIndex();
        
        GridPane g = null;
        if(tab == 0){
            g = gridMaster;
            valorInicial = 8;
        }else if(tab == 1){
            g = gridJugador;
            valorInicial = 5;
        }else if(tab == 2){
            g = gridArchivado;
            valorInicial = 10;
        }else{
            System.out.println("POr ahí no amiga");
        }
        // Calcular columnas y filas para una cuadrícula lo más cuadrada posible
        int columnas = (int) Math.ceil(Math.sqrt(valorInicial));
        int filas = (int) Math.ceil((double) valorInicial / columnas);

        g.getChildren().clear(); // Limpiar el grid antes de agregar nuevos botones

        for (int i = 0; i < valorInicial; i++) {
            Button b = new Button("Botón " + (i + 1));

            b.setPrefWidth(200);
            b.setStyle(
                "-fx-background-color: #8e5d16;"+
                "-fx-border-radius: 5;"+
                "-fx-margin: 10;"
            );
            b.setMaxWidth(Double.MAX_VALUE);
            b.setMaxHeight(Double.MAX_VALUE);
            GridPane.setMargin(b, new Insets(5));
            b.setOnAction(e -> {
                System.out.println("Puta");
            });

            int row = i / columnas;
            int col = i % filas;

            g.add(b, col, row);
        }
    }
    
    @FXML
    private void login(){
        String user = tNombre.getText();
        String pwd = tPassword.getText();
        
        try{
            uRepo.Login(App.direc, user, pwd);
            System.out.println(App.user);
            App.setRoot("Separador");
        } catch (IOException e) {
            lFallo.setVisible(true);
            lFallo.setText("Credenciales no admitidas");
            tPassword.setText("");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
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

    
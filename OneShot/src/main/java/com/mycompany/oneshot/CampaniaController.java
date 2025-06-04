/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oneshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import modelo.*;

/**
 * Clase encargada de la gestión y llamado de todos los métodos relacionados
 * con las campañas.
 * 
 * @author Nestor y Asociados
 */
public class CampaniaController {

    private TheStrangeRepository tsRepo = new TheStrangeRepository();
    private CampaniasRepository cRepo = new CampaniasRepository();
    private UsuarioRepository uRepo = new UsuarioRepository();

    private String message = "Se advierte de que se ha advertido de lo que se tiene que advertir";
    private boolean archivar = false;
    
    //Mostrar mis campañas
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
    
        
    //Creación campaña
    @FXML
    private Label lUser2;
    
    @FXML
    private TextField textNombreCamp;
    
    @FXML
    private PasswordField passContrasenia;
    
    @FXML
    private ComboBox cbManual;
    
    @FXML
    private ComboBox cbImagen;
    
    @FXML
    private TextArea taDescripcion;
    
    
    
    @FXML
    private Label lNombreUser;
    
    @FXML
    private Pane pListaC;
    
    //Listar campañas
    @FXML
    private Label lUser3;
    
    
/**
 * Método encargado de mover la pestaña actual a la de login,
 * eliminando tambien las credenciales del usuario así como su
 * token de sesión para que no pueda acceder de nuevo por error
 * con un token invalido
 * 
 */
    @FXML
    public void volver(){
        try{
            App.user.logout();
            App.setRoot("login");  
        }catch(IOException e){
            e.printStackTrace();
        }
        
    }
   
/**
 * Método encargado de mover la pestaña actual a la vista principal de
 * las campañas del usuario.
 * 
 */
    @FXML
    public void volverPrincipal(){
        try{
            App.setRoot("principal");
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    public void fichas(){
        try{
            App.setRoot("ficha");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
/**
 *  Método que es llamado cada vez que se carga una nueva ventana fxml.
 * Como este controlador sirve para muchas ventanas, para evitar que haya
 * problemas de nulos, esta clase comprueba la existencia de cada elemento
 * antes de trabajar con el.
 * Se encarga en concreto de colocar el nombre del usuario en las distintas
 * ventanas y de llamar al método que nos muestra nuestras campañas.
 * 
 */
    @FXML
    public void initialize() {
        if(lUser2 != null){
            lUser2.setText(App.user.getUser());
            
            List<Integer> imagenes = new ArrayList<>();
            imagenes.add(1);
            imagenes.add(2);
            imagenes.add(3);
            imagenes.add(4);
            cbImagen.setItems(FXCollections.observableArrayList(imagenes));
            
            
            List<Manual> manuales = cRepo.listarManuales(App.direc);
            cbManual.setItems(FXCollections.observableArrayList(manuales));
            

        }else if(lUser3 != null){
            lUser3.setText(App.user.getUser());
        }
        
        if(lNombreUser != null){
            lNombreUser.setText(App.user.getUser());
        }
        if(pListaC != null){
            listadoCampanias();
        }
    }
    
    /**
     * Este método cambia el estado en el que se encuentra el booleano archivar
     * que cambia la funcionalidad de los botones de acceso de las campañas 
     * a archivar estas.
     */
    @FXML
    public void archivar(){
        if(archivar){
            archivar = false;
        }else{
            archivar = true;
        }
    }
    
/**
 * Este método llama al método del repositorio para campañas que nos 
 * devuelve una lista de objetos campaña con las campañas existentes.
 * Luego recorre en la lista de campañas y va instanciando en la vista dentro de un Pane
 * cada campaña existente, con un pequeño formulario con un Button y un PasswordField
 * para poder permitir el unirnos a esa campaña introduciendo su contraseña.
 * Podamos meternos o no en la campaña se nos notificará del resultado a través
 * de un popUp.
 * 
 */
    public void listadoCampanias(){
        List<Campanias> listaC = cRepo.listaCampañas(App.direc);
        int x = 10;
        int y = 14;
        
        for (Campanias c : listaC) {
            Pane pane = new Pane();
                pane.setLayoutX(x);
                pane.setLayoutY(y);
                pane.setPrefHeight(55.0);
                pane.setPrefWidth(980.0);
                pane.setStyle("-fx-background-color: #18425a;");

            Label label = new Label(c.getId()+" | "+c.getNombre());
                label.setLayoutX(14.0);
                label.setLayoutY(10.0);
                label.setTextFill(javafx.scene.paint.Color.WHITE);
                label.setFont(Font.font("System Bold", 25.0));


            PasswordField passwordField = new PasswordField();
                passwordField.setLayoutX(721.0);
                passwordField.setLayoutY(15.0);
                passwordField.setPrefHeight(25.0);
                passwordField.setPrefWidth(163.0);
                passwordField.setPromptText("Contraseña");
                passwordField.setId("lContrasenia");
            
                
            Button button = new Button("Entrar");
                button.setLayoutX(901.0);
                button.setLayoutY(15.0);
                button.getStyleClass().add("botones");
                button.getStylesheets().add(getClass().getResource("/estilos/styles.css").toExternalForm());
                button.setOnAction(e -> {
                        String aux = cRepo.unirmeCampañas(App.direc, c, passwordField.getText());
                        
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Advertencia");
                        alert.setHeaderText(null);
                        alert.setContentText(aux);
                        alert.showAndWait();
                        
                        passwordField.setText("");
                    });
            pane.getChildren().addAll(label, button, passwordField);
            pListaC.getChildren().add(pane);
            y += 55;
        }
    }
    
/**
 * Método que es llamado cada vez que carga un tab en la vista principal con todas tus 
 * campañas.
 * Este muestra dependiendo del tab tus campañas actuales o las archivadas, desplegandolas
 * en botones que al pulsarse llamen al método que nos mueva a la campaña solicitada,
 * en caso de que el atrubito archivar este a false, en caso de que esté a true 
 * archivará la campaña si eres master de está.
 * 
 * @exception IOException
 */
    @FXML
    public void Botones() throws IOException {
        //Cambiar el valor inicial por el llamado a un método que me diga el
        //número de elementos
        int valorInicial = 10;
        int tab = tabInicial.getSelectionModel().getSelectedIndex();
        List<Campanias> listaC = cRepo.listaTusCampañas(App.direc);
        
        GridPane g = null;
        for (Campanias campanyas : listaC) {
            if(tab == 0 || tab == 1){
                if(!campanyas.isArchivada()){
                    valorInicial++;
                }
            }else if(tab == 2){
                if(campanyas.isArchivada()){
                    valorInicial++;
                }
            }
        }
        
        if(tab == 0){
            g = gridMaster;
        }else if(tab == 1){
            g = gridJugador;
        }else if(tab == 2){
            g = gridArchivado;
        }
        
        // Calcular columnas y filas para una cuadrícula lo más cuadrada posible
        int columnas = (int) Math.ceil(Math.sqrt(valorInicial));
        int filas = (int) Math.ceil((double) valorInicial / columnas);

        g.getChildren().clear(); // Limpiar el grid antes de agregar nuevos botones

        int i = 0;
        for (Campanias campanyas : listaC) {
            if(tab == 0 || tab == 1){
                if(!campanyas.isArchivada()){
                    Button b = new Button(campanyas.getNombre());

                    b.setPrefWidth(200);
                    b.setStyle(
                        "-fx-background-color: #8e5d16;"+
                        "-fx-border-radius: 5;"+
                        "-fx-margin: 20;"
                    );
                    b.setMaxWidth(Double.MAX_VALUE);
                    b.setMaxHeight(Double.MAX_VALUE);
                    GridPane.setMargin(b, new Insets(5));
                    b.setOnAction(e -> {
                        if(archivar){
                            String aux = cRepo.archivar(App.direc, campanyas.getId());
                            
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Advertencia");
                            alert.setHeaderText(null);
                            alert.setContentText(aux);
                            alert.showAndWait();
                            
                            archivar = false;
                        }else{
                            entrarCampania(campanyas);
                        }
                        
                    });

                    int row = i / columnas;
                    int col = i % filas;

                    g.add(b, col, row);
                    i++;
                }
            }else if(tab == 2){
                if(campanyas.isArchivada()){
                    Button b = new Button(campanyas.getNombre());

                    b.setPrefWidth(200);
                    b.setStyle(
                        "-fx-background-color: #8e5d16;"+
                        "-fx-border-radius: 5;"+
                        "-fx-margin: 20;"
                    );
                    b.setMaxWidth(Double.MAX_VALUE);
                    b.setMaxHeight(Double.MAX_VALUE);
                    GridPane.setMargin(b, new Insets(5));
                    b.setOnAction(e -> {
                        entrarCampania(campanyas);
                    });

                    int row = i / columnas;
                    int col = i % filas;

                    g.add(b, col, row);
                    i++;
                }
            }
            
        }
    }


    
    @FXML
    public void entrarCampania(Campanias c){
        
        try {
            App.campañaAct = c;
            App.setRoot("campania");
        } catch (IOException ex) {
            App.campañaAct = null;
            ex.printStackTrace();
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
        Campanias c = new Campanias(textNombreCamp.getText(), taDescripcion.getText(),
                (Manual) cbManual.getSelectionModel().getSelectedItem() ,
                cbImagen.getSelectionModel().getSelectedIndex()+1);
        
        int aux = cRepo.MandarCampaña(App.direc, c, passContrasenia.getText());
        
        System.out.println(aux);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
        if(aux == 200){
            alert.setContentText("Campaña creada");
        }else{
            alert.setContentText("Ha habido un error");
        }
        alert.showAndWait();
        
        try {
            App.setRoot("prueba");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void buscar(){
        try {
            App.setRoot("busadorCampanias");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

    

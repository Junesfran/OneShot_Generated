/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oneshot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modelo.CampaniasRepository;
import modelo.TheStrangeRepository;
import modelo.UsuarioRepository;

/**
 *
 * @author Nestor y Asociados
 */
public class ConsultasController {
    private TheStrangeRepository tsRepo = new TheStrangeRepository();
    private CampaniasRepository cRepo = new CampaniasRepository();
    private UsuarioRepository uRepo = new UsuarioRepository();
    
    //Datos generales
    @FXML
    private Label lNombreUser;
    
    //Recursion
    @FXML
    private Pane consultarRecursion;
    
    @FXML
    private ComboBox cbRecursionCreatura;
    
    @FXML
    private ListView lbListaCriaturas;
    
    @FXML
    private Label lNombre;
    
    @FXML
    private TextFlow tfTexto;
    
    @FXML
    private Label lNivel;
    
    //Clases
    @FXML
    private Pane consultaClases;
    
    @FXML
    private ComboBox cbClases;
    
    @FXML
    private Label lNombreClase;
    
    @FXML
    private TextFlow tfTextoClase;
    
    //Rasgos
    @FXML
    private ListView lbRasgosRecursion;
    
    @FXML
    public void initialize() {
        lNombreUser.setText(App.user.getUser());
        
        
        if (cbRecursionCreatura != null) {
            List<String> lista = tsRepo.sacarRecursiones(App.direc);
            cbRecursionCreatura.setItems(FXCollections.observableArrayList(lista));
        }
        if(cbClases != null){
            List<String> lista = tsRepo.sacarClases(App.direc);
            cbClases.setItems(FXCollections.observableArrayList(lista));
        }
    }
    
    @FXML
    public void volver(){
        try {
            App.setRoot("principal");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @FXML
    public void recursion(){
        consultas();
        consultarRecursion.setVisible(true);
    }
    
    @FXML
    public void clase(){
        consultas();
        consultaClases.setVisible(true);
    }
    
    public void consultas(){
        consultaClases.setVisible(false);
        consultarRecursion.setVisible(false);
    }
    
    @FXML
    public void textoClase(){
        int clase = cbClases.getSelectionModel().getSelectedIndex(); 
        System.out.println(clase);
        List<String> lista = tsRepo.sacarClase(App.direc, clase);
        transformarTexto(lista,3,1);
    }
    
    @FXML
    public void listaCriatura(){
        String recursion = (String) cbRecursionCreatura.getValue(); 
        try {
            List<String> listaRecu = tsRepo.sacarRecursion(App.direc, recursion);
            transformarTexto(listaRecu,2,0);
            
            List<String> lista = tsRepo.sacarCriaturasRecursion(App.direc, recursion); 
            lbListaCriaturas.setItems(FXCollections.observableArrayList(lista));
            List<String> listaRasgo = tsRepo.sacarRasgoRecursion(App.direc, recursion); 
            lbRasgosRecursion.setItems(FXCollections.observableArrayList(listaRasgo));
        } catch (NullPointerException e) {
            lbListaCriaturas.getItems().clear();
            lbRasgosRecursion.getItems().clear();
        } 
    }
    
    @FXML
    public void textoCriatura(){
        String criatura = (String) lbListaCriaturas.getSelectionModel().getSelectedItem();
        System.out.println(criatura);
        List<String> lista = tsRepo.sacarCriatura(App.direc, criatura);
        transformarTexto(lista,1,0);
    }


    @FXML
    public void textoRasgo(){
        String recursion = (String) cbRecursionCreatura.getValue();
        int rasgo = lbRasgosRecursion.getSelectionModel().getSelectedIndex(); 
        System.out.println(rasgo);
        List<String> lista = tsRepo.sacarRasgo(App.direc, recursion, rasgo);
        transformarTexto(lista,4,0);
    }
    
    public void transformarTexto(List<String> texto, int num, int vista){
        List<Text> textos = new ArrayList<>();
        String aux = texto.get(1);
        Set<String> encabezados = null;
        if(num == 1){
            encabezados = Set.of(
                "MOTIVACIÓN", "ENTORNO", "SALUD", "DAÑO QUE INFLINGE",
                "MOVIMIENTO", "COMBATE", "INTERRACIÓN", "USO", "BOTÍN"
            );
        }else if(num == 2){
            encabezados = Set.of(
                "FORMA", "CHISPA", "CONEXIÓN THE STRANGE", "CONEXIÓN CON LA TIERRA"
            );
        }else if(num == 3){
            encabezados = Set.of(
                "CANTIDAD DE DISPOSITIVOS BASE", "TRASLACIÓN", "VINCULO INICIAL"
            );
        }else if(num == 4){
            encabezados = Set.of(
                "TRASFERIBLE"
            );
        }

        for (String linea : aux.split("\n")) {
            Text t = new Text(linea + "\n\n");

            if (encabezados.contains(linea.trim())) {
                t.setFill(Color.web("#225e87"));
                t.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
            } else {
                t.setFill(Color.BLACK);
                t.setStyle("-fx-font-size: 12px;");
            }

            textos.add(t);  
        }
        
        if(vista == 0){
            lNombre.setText(texto.get(0));

            tfTexto.getStyleClass().add("label-fijo");
            tfTexto.getChildren().clear();
            tfTexto.getChildren().addAll(textos);

            lNivel.setText(texto.get(2));
        }else if(vista == 1){
            lNombreClase.setText(texto.get(0));
            
            tfTextoClase.getStyleClass().add("label-fijo");
            tfTextoClase.getChildren().clear();
            tfTextoClase.getChildren().addAll(textos);
        }
        
    }
}

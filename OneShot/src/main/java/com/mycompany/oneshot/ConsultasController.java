/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oneshot;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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
    
    @FXML
    private ComboBox cbRecursionCreatura;
    
    @FXML
    private ListView lbListaCriaturas;
    
    @FXML
    private Label lNombre;
    
    @FXML
    private Label lTexto;
    
    @FXML
    private Label lNivel;
    
    
    
    @FXML
    public void initialize() {
        if (cbRecursionCreatura != null) {
            List<String> lista = tsRepo.sacarRecursiones(App.direc);
            cbRecursionCreatura.setItems(FXCollections.observableArrayList(lista));
        }
    }
    
    @FXML
    public void listaCriatura(){
        String recursion = (String) cbRecursionCreatura.getValue(); 
        try {
           List<String> lista = tsRepo.sacarCriaturasRecursion(App.direc, recursion); 
            lbListaCriaturas.setItems(FXCollections.observableArrayList(lista));
        } catch (NullPointerException e) {
            lbListaCriaturas.getItems().clear();
        } 
    }
    
    @FXML
    public void textoCriatura(){
        String criatura = (String) lbListaCriaturas.getSelectionModel().getSelectedItem();
        criatura = criatura.replaceAll(" ", "%20");
        System.out.println(criatura);
        List<String> lista = tsRepo.sacarCriatura(App.direc, criatura);
        lNombre.setText(lista.get(0));
        lTexto.setText(lista.get(1));
        lNivel.setText(lista.get(2));
    }
}

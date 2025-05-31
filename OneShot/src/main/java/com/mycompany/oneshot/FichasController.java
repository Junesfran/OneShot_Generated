/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.oneshot;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import modelo.*;
/**
 * FXML Controller class
 *
 * @author Nestor y Asociados
 */
public class FichasController {
    //Generales
    @FXML
    private TextField tNombre;
    
    @FXML
    private TextField tTipo;
    
    @FXML
    private TextField tRasgo;
    
    @FXML
    private TextField tDescriptor;
    
    @FXML
    private ComboBox cbRecursion;
    
    
    //Marcadores
    @FXML
    private TextField tRango;
    
    @FXML
    private TextField tEsfuerzo;
    
    @FXML
    private TextField tExp;
    
    
    //Subida de Nivel
    @FXML
    private CheckBox chAumentar;
    
    @FXML
    private CheckBox chPerfeccion;
    
    @FXML
    private CheckBox chEsfuerzo;
    
    @FXML
    private CheckBox chCompetencia;
    
    @FXML
    private CheckBox chOtros;
    
    //Recuperacion / Descanso
    @FXML
    private CheckBox chAccion;
    
    @FXML
    private CheckBox chHora;
    
    @FXML
    private CheckBox chMinutos;
    
    @FXML
    private CheckBox chHoras;
    
    @FXML
    private TextField tRecuperacion;
    
    
    //Estadisticas
    @FXML
    private TextField tReservaVigor;
    
    @FXML
    private TextField tVentajaVigor;
    
    @FXML
    private TextField tVigorAct;
    
    @FXML
    private TextField tReservaVelocidad;
    
    @FXML
    private TextField tVentajaVelocidad;
    
    @FXML
    private TextField tVelocidadAct;
    
    @FXML
    private TextField tReservaInteligencia;
    
    @FXML
    private TextField tVentajaInteligencia;
    
    @FXML
    private TextField tInteligenciaAct;
    
    //Equipo
    @FXML
    private TextField tArmadura;
    
    @FXML
    private TextField tDinero;
    
    @FXML
    private ListView lbEquipo;
    
    @FXML
    private TextField tDispositivos;
    
    @FXML
    private ListView lbDispositivos;
    
    
    //Capacidades Especiales
    @FXML
    private ListView lbCapacidadesEsp;
    
    //Trasfondo
    @FXML
    private TextArea taTrasfondo;
    
    
    @FXML
    public void nuevaFicha(){
        //Se crea una clase ficha con los datos
        String recursion = (String) cbRecursion.getValue();
        List<String> habilidades = new ArrayList<>(lbCapacidadesEsp.getItems());
        List<String> equipo = new ArrayList<>(lbEquipo.getItems());
        List<String> dispositivo = new ArrayList<>(lbDispositivos.getItems());
        
        HashMap<String, Boolean> competencias = new HashMap<String, Boolean>();
        
        String aux = taTrasfondo.getText();
        
        
        Ficha f = new Ficha(tNombre.getText(), tTipo.getText(), tRasgo.getText(), tDescriptor.getText(), recursion,
                Integer.parseInt(tRango.getText()), Integer.parseInt(tEsfuerzo.getText()), Integer.parseInt(tExp.getText()),
                chAumentar.isSelected(), chPerfeccion.isSelected(), chEsfuerzo.isSelected(), chCompetencia.isSelected(), chOtros.isSelected(),
                tRecuperacion.getText(),
                Integer.parseInt(tReservaVigor.getText()), Integer.parseInt(tVigorAct.getText()), Integer.parseInt(tVentajaVigor.getText()),
                Integer.parseInt(tReservaVelocidad.getText()), Integer.parseInt(tVelocidadAct.getText()), Integer.parseInt(tVentajaVelocidad.getText())
                , Integer.parseInt(tReservaInteligencia.getText()), Integer.parseInt(tInteligenciaAct.getText()), Integer.parseInt(tVentajaInteligencia.getText()),
                Integer.parseInt(tArmadura.getText()), Integer.parseInt(tDinero.getText()),
                competencias, habilidades,
                equipo, dispositivo, Integer.parseInt(tDispositivos.getText()),
                aux);
        //Se le pasa el objeto al método que lo mande, el toString está preparado
        //para mandar bien el json.
        
    }
}

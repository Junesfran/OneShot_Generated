/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.oneshot;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import modelo.*;
/**
 * FXML Controller class
 *
 * @author Nestor y Asociados
 */
public class FichasController {
    FichaRepository fRepo = new FichaRepository();
    
    
    //funcionalidad
    @FXML
    private ComboBox cbFichas;
    
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
    
    @FXML
    private GridPane gridComp;
    
    
    //Capacidades Especiales
    @FXML
    private ListView lbCapacidadesEsp;
    
    //Trasfondo
    @FXML
    private TextArea taTrasfondo;
    
    @FXML
    private TextArea taVinculo;
    
    @FXML
    private TextArea taNotas;
    
    @FXML
    public void initialize() {
        if(cbFichas != null){
            List<Ficha> fichas = fRepo.listarFichas(App.direc);
            cbFichas.setItems(FXCollections.observableArrayList(fichas));
        }
    }
    
    @FXML
    public void CrearFicha(){
        //Se crea una clase ficha con los datos
        String recursion = ((Manual) cbRecursion.getValue()).getId();
        List<String> habilidades = new ArrayList<>(lbCapacidadesEsp.getItems());
        List<String> equipo = new ArrayList<>(lbEquipo.getItems());
        List<String> dispositivo = new ArrayList<>(lbDispositivos.getItems());
        List<Competencia> listaC = fRepo.sacarCompetencias(gridComp);
        
        String aux = taTrasfondo.getText();
        
        // Strings
        String manualId = "the_stange";
        String nombre = tNombre.getText();
        String tipo = tTipo.getText();
        String rasgo = tRasgo.getText();
        String descriptor = tDescriptor.getText();
        String vinculoD = taVinculo.getText(); // nuevo parámetro
        String recursionStr = recursion;
        String recuperacion = tRecuperacion.getText();
        String trasfondo = taTrasfondo.getText(); // nuevo parámetro

        // Enteros
        int rango = Integer.parseInt(tRango.getText());
        int esfuerzo = Integer.parseInt(tEsfuerzo.getText());
        int experiencia = Integer.parseInt(tExp.getText());

        int reservaVigorMax = Integer.parseInt(tReservaVigor.getText());
        int reservaVigorAct = Integer.parseInt(tVigorAct.getText());
        int ventajaVigor = Integer.parseInt(tVentajaVigor.getText());

        int reservaVelocidadMax = Integer.parseInt(tReservaVelocidad.getText());
        int reservaVelocidadAct = Integer.parseInt(tVelocidadAct.getText());
        int ventajaVelocidad = Integer.parseInt(tVentajaVelocidad.getText());

        int reservaInteligenciaMax = Integer.parseInt(tReservaInteligencia.getText());
        int reservaInteligenciaAct = Integer.parseInt(tInteligenciaAct.getText());
        int ventajaInteligencia = Integer.parseInt(tVentajaInteligencia.getText());

        int armadura = Integer.parseInt(tArmadura.getText());
        int dinero = Integer.parseInt(tDinero.getText());
        int maxDispositivos = Integer.parseInt(tDispositivos.getText());

        // Booleans
        boolean aumentarC = chAumentar.isSelected();
        boolean perfeccion = chPerfeccion.isSelected();
        boolean esfuerzoExtra = chEsfuerzo.isSelected();
        boolean competenciaH = chCompetencia.isSelected();
        boolean otros = chOtros.isSelected();

        boolean accion = chAccion.isSelected();
        boolean minutos = chMinutos.isSelected();
        boolean hora = chHora.isSelected();
        boolean horas = chHoras.isSelected();
        
        
        Ficha f = new Ficha(
            manualId, nombre, tipo, rasgo, descriptor, vinculoD, recursionStr,
            rango, esfuerzo, experiencia,
            aumentarC, perfeccion, esfuerzoExtra, competenciaH, otros,
            recuperacion,
            accion, minutos, hora, horas,
            reservaVigorMax, reservaVigorAct, ventajaVigor,
            reservaVelocidadMax, reservaVelocidadAct, ventajaVelocidad,
            reservaInteligenciaMax, reservaInteligenciaAct, ventajaInteligencia,
            armadura, dinero,
            listaC, habilidades, equipo, dispositivo, maxDispositivos,
            trasfondo
        );
        
        int num = fRepo.mandarFicha(App.direc, f);
        
        if(num == 200){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Todo ha salido bien");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Algo ha salido mal");
            alert.showAndWait();
        }
        
    }

   
    public void añadirCompetencias(Competencia c) throws IOException {
        CheckBox check = new CheckBox(c.getNombre());
        
        if(c.isEspecializado()){
            check.setSelected(true);
        }
        
        int row = gridComp.getRowCount();
        row = gridComp.getChildren().size();
        
        GridPane.setRowIndex(check, row);
        GridPane.setColumnIndex(check, 0);
        gridComp.getChildren().add(check);
    }
    
    @FXML
    public void volverPrincipal(){
        try{
            App.setRoot("principal");
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    @FXML
    public void cargarDatos(){
        Ficha f = (Ficha) cbFichas.getSelectionModel().getSelectedItem();
        Ficha ficha = fRepo.buscarFichaPorId(App.direc, f.getId());
        
        System.out.println(ficha.DevolverJSON());
        // Generales
        tNombre.setText(ficha.getNombre());
        tTipo.setText(ficha.getTipo());
        tRasgo.setText(ficha.getRasgo());
        tDescriptor.setText(ficha.getDescriptor());
        taVinculo.setText(ficha.getVinculoD());
        cbRecursion.setValue(ficha.getRecursion());

        // Marcadores
        tRango.setText(String.valueOf(ficha.getRango()));
        tEsfuerzo.setText(String.valueOf(ficha.getEsfuerzo()));
        tExp.setText(String.valueOf(ficha.getExp()));

        // Subida de nivel
        chAumentar.setSelected(ficha.isAumentarC());
        chPerfeccion.setSelected(ficha.isPerfeccion());
        chEsfuerzo.setSelected(ficha.isEsfuerzoExt());
        chCompetencia.setSelected(ficha.isCompetenciaH());
        chOtros.setSelected(ficha.isOtros());

        // Recuperación / descanso
        chAccion.setSelected(ficha.isAccion());
        chHora.setSelected(ficha.isHora());
        chMinutos.setSelected(ficha.isMinutos());
        chHoras.setSelected(ficha.isHoras());
        tRecuperacion.setText(ficha.getRecuperacion());

        // Estadísticas
        tReservaVigor.setText(String.valueOf(ficha.getReservaVigorMax()));
        tVigorAct.setText(String.valueOf(ficha.getReservaVigorAct()));
        tVentajaVigor.setText(String.valueOf(ficha.getVentajaVigor()));

        tReservaVelocidad.setText(String.valueOf(ficha.getReservaVelocidadMax()));
        tVelocidadAct.setText(String.valueOf(ficha.getReservaVelocidadAct()));
        tVentajaVelocidad.setText(String.valueOf(ficha.getVentajaVelocidad()));

        tReservaInteligencia.setText(String.valueOf(ficha.getReservaInteligenciaMax()));
        tInteligenciaAct.setText(String.valueOf(ficha.getReservaInteligenciaAct()));
        tVentajaInteligencia.setText(String.valueOf(ficha.getVentajaInteligencia()));

        // Equipo
        tArmadura.setText(String.valueOf(ficha.getArmadura()));
        tDinero.setText(String.valueOf(ficha.getDinero()));

        // Lista de equipo
        lbEquipo.getItems().clear();
        lbEquipo.getItems().addAll(ficha.getEquipo());

        // Dispositivos
        tDispositivos.setText(String.valueOf(ficha.getMaxDispositivo()));
        lbDispositivos.getItems().clear();
        lbDispositivos.getItems().addAll(ficha.getDispositivo());

        // Capacidades especiales
        lbCapacidadesEsp.getItems().clear();
        lbCapacidadesEsp.getItems().addAll(ficha.getHabilidades());

        // Trasfondo
        taTrasfondo.setText(ficha.getTrasfondo());

        // Competencias
        gridComp.getChildren().clear();
        int row = 0;
        for (Competencia comp : ficha.getCompetencias()) {
            //Label nombre = new Label(comp.getNombre());
            CheckBox check = new CheckBox(comp.getNombre());
            check.setSelected(comp.isEspecializado());
            check.setDisable(true); // Opcional: si solo es visual
            gridComp.addRow(row++,check);
        }


    }
}

    

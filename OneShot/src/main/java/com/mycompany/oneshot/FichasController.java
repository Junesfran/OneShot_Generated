/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.oneshot;

import dao.TheStrangeRepository;
import dao.FichaRepository;
import dao.CampaniasRepository;
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
    TheStrangeRepository tsRepo = new TheStrangeRepository();
    CampaniasRepository cRepo = new CampaniasRepository();
    
    //funcionalidad
    @FXML
    private ComboBox cbFichas;

    @FXML
    private ComboBox cbCampanias;
    
    @FXML
    private TextField lCompetencias;
    
    @FXML
    private TextField lHabilidades;
    
    @FXML
    private TextField lEquipo;
    
    @FXML
    private TextField lDispo;
    
    
    
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
    private TextField cbRecursion;
    
    
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
    
    /**
 * Inicializa el controlador, cargando las listas de fichas y campañas en los ComboBox correspondientes.
 */
    @FXML
    public void initialize() {
        if(cbFichas != null){
            List<Ficha> fichas = fRepo.listarFichas(App.direc);
            cbFichas.setItems(FXCollections.observableArrayList(fichas));
        }
        if(cbCampanias != null){
            List<Campanias> listaC = cRepo.listaTusCampañas(App.direc);
            cbCampanias.setItems(FXCollections.observableArrayList(listaC));
        }
    }
    /**
 * Asigna la ficha seleccionada a la campaña seleccionada.
 */
     @FXML
     public void asignarCampania(){
        Campanias c = (Campanias)cbCampanias.getSelectionModel().getSelectedItem();
        Ficha fTemp = (Ficha)cbFichas.getSelectionModel().getSelectedItem();
        
        if(c == null || fTemp == null){
            return;
        }
         
        int num = fRepo.fichaCampania(App.direc, c.getId(), fTemp.getId());
         
        if(num == 200){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Asignada");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Advertencia");
            alert.setHeaderText(null);
            alert.setContentText("Algo ha salido mal");
            alert.showAndWait();
        }
     }
    
     /**
 * Limpia todos los campos del formulario y deselecciona la ficha actual.
 */
    @FXML
    public void limpiar(){
        // Generales
        tNombre.clear();
        tTipo.clear();
        tRasgo.clear();
        tDescriptor.clear();
        taVinculo.clear();
        cbRecursion.clear();

        // Marcadores
        tRango.clear();
        tEsfuerzo.clear();
        tExp.clear();

        // Subida de nivel
        chAumentar.setSelected(false);
        chPerfeccion.setSelected(false);
        chEsfuerzo.setSelected(false);
        chCompetencia.setSelected(false);
        chOtros.setSelected(false);

        // Recuperación / descanso
        chAccion.setSelected(false);
        chHora.setSelected(false);
        chMinutos.setSelected(false);
        chHoras.setSelected(false);
        tRecuperacion.clear();

        // Estadísticas
        tReservaVigor.clear();
        tVigorAct.clear();
        tVentajaVigor.clear();

        tReservaVelocidad.clear();
        tVelocidadAct.clear();
        tVentajaVelocidad.clear();

        tReservaInteligencia.clear();
        tInteligenciaAct.clear();
        tVentajaInteligencia.clear();

        // Equipo
        tArmadura.clear();
        tDinero.clear();
        lbEquipo.getItems().clear();

        // Dispositivos
        tDispositivos.clear();
        lbDispositivos.getItems().clear();

        // Capacidades especiales
        lbCapacidadesEsp.getItems().clear();

        // Trasfondo
        taTrasfondo.clear();

        // Competencias
        gridComp.getChildren().clear();
    
        cbFichas.getSelectionModel().clearSelection();
        System.out.println(cbFichas.getSelectionModel().getSelectedItem());
    }
    /**
 * Parsea un String a int de forma segura, devolviendo un valor por defecto si hay error.
 * @param texto Texto a convertir.
 * @param valorPorDefecto Valor a devolver si el texto no es válido.
 * @return El número parseado o el valor por defecto.
 */
    private int parseIntSeguro(String texto, int valorPorDefecto) {
        if (texto == null || texto.trim().isEmpty()) {
            return valorPorDefecto;
        }
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return valorPorDefecto;
        }
    }
/**
 * Crea una nueva ficha con los datos del formulario y la guarda en el repositorio.
 * Si hay una ficha seleccionada, la elimina antes de guardar la nueva.
 */
    @FXML
    public void CrearFicha(){
        //Se crea una clase ficha con los datos
        String recursion = cbRecursion.getText();
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

        int rango = parseIntSeguro(tRango.getText(), 0);
        int esfuerzo = parseIntSeguro(tEsfuerzo.getText(), 0);
        int experiencia = parseIntSeguro(tExp.getText(), 0);

        int reservaVigorMax = parseIntSeguro(tReservaVigor.getText(), 0);
        int reservaVigorAct = parseIntSeguro(tVigorAct.getText(), 0);
        int ventajaVigor = parseIntSeguro(tVentajaVigor.getText(), 0);

        int reservaVelocidadMax = parseIntSeguro(tReservaVelocidad.getText(), 0);
        int reservaVelocidadAct = parseIntSeguro(tVelocidadAct.getText(), 0);
        int ventajaVelocidad = parseIntSeguro(tVentajaVelocidad.getText(), 0);

        int reservaInteligenciaMax = parseIntSeguro(tReservaInteligencia.getText(), 0);
        int reservaInteligenciaAct = parseIntSeguro(tInteligenciaAct.getText(), 0);
        int ventajaInteligencia = parseIntSeguro(tVentajaInteligencia.getText(), 0);

        int armadura = parseIntSeguro(tArmadura.getText(), 0);
        int dinero = parseIntSeguro(tDinero.getText(), 0);
        int maxDispositivos = parseIntSeguro(tDispositivos.getText(), 0);


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
        Ficha fTemp = (Ficha)cbFichas.getSelectionModel().getSelectedItem();
        if(fTemp != null){
           fRepo.borrarFicha(App.direc, fTemp); 
        }
        
        int num = fRepo.mandarFicha(App.direc, f);
        
        if(num == 200){
            List<Ficha> fichas = fRepo.listarFichas(App.direc);
            cbFichas.setItems(FXCollections.observableArrayList(fichas));
            
            cbFichas.getSelectionModel().selectLast();
            
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

   /**
 * Añade una competencia al GridPane de competencias.
 * @param c Competencia a añadir.
 * @throws IOException Si ocurre un error al añadir.
 */
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
/**
 * Carga los datos de la ficha seleccionada en el formulario.
 */
    @FXML
    public void cargarDatos(){
        Ficha f = (Ficha) cbFichas.getSelectionModel().getSelectedItem();
        if(f == null){
            return;
        }
        Ficha ficha = fRepo.buscarFichaPorId(App.direc, f.getId());
        
        System.out.println(ficha.DevolverJSON());
        // Generales
        tNombre.setText(ficha.getNombre());
        tTipo.setText(ficha.getTipo());
        tRasgo.setText(ficha.getRasgo());
        tDescriptor.setText(ficha.getDescriptor());
        taVinculo.setText(ficha.getVinculoD());
        cbRecursion.setText(ficha.getRecursion());

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
            gridComp.addRow(row++,check);
        }


    }
    /**
 * Añade un nuevo dispositivo a la lista de dispositivos.
 */
    @FXML
    private void nuevoDispo(){
        String texto = lDispo.getText();
        if (!texto.isEmpty()) {
            lbDispositivos.getItems().add(texto);
            lDispo.clear();
        }
    }
    /**
    * Lo mismo que nuevoDispo
    */
    @FXML
    private void nuevoEquipo(){
        String texto = lEquipo.getText();
        if (!texto.isEmpty()) {
            lbEquipo.getItems().add(texto);
            lEquipo.clear();
        }
    }
        /**
    * Lo mismo que nuevoDispo
    */
    @FXML
    private void nuevaHabilidad(){
        String texto = lHabilidades.getText().trim();
        if (!texto.isEmpty()) {
            lbCapacidadesEsp.getItems().add(texto);
            lHabilidades.clear();
        }
    }
    /**
    * Lo mismo que nuevoDispo
    */
    @FXML
    private void nuevaCompetencia(){
        String texto = lCompetencias.getText().trim();
        if (!texto.isEmpty()) {
            Competencia c = new Competencia(texto, false);
            try {
                añadirCompetencias(c);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            lCompetencias.clear();
        }
    }
}

    

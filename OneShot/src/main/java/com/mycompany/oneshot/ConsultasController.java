/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oneshot;

import dao.UsuarioRepository;
import dao.TheStrangeRepository;
import dao.FichaRepository;
import dao.CampaniasRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import modelo.*;

/**
 * Clase dedicadad a la gestión de todo lo relacionada a la vista de una sola campaña,
 * donde se haran todas las consultas de manual así como de las fichas
 * 
 * @author Nestor y Asociados
 */
public class ConsultasController {
    private TheStrangeRepository tsRepo = new TheStrangeRepository();
    private CampaniasRepository cRepo = new CampaniasRepository();
    private UsuarioRepository uRepo = new UsuarioRepository();
    private FichaRepository fRepo = new FichaRepository();
    
    private int fichaId = 0;
    //Datos generales
    @FXML
    private ImageView ivCampaña;
    
    
    @FXML
    private Label lNombreUser;
    
    @FXML
    private Label lManual;
    
    @FXML
    private Label lFuncion;
    
    
    @FXML
    private Label lCampania;
    
    
    //Recursion
    @FXML
    private Pane consultarRecursion;
    
    @FXML
    private ComboBox cbRecursionCreatura;
    
    @FXML
    private ListView lbListaCriaturas;

    @FXML
    private ListView lbApartados;
    
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
    
    //Descriptor
    @FXML
    private ComboBox cbDescriptor;
    
    @FXML 
    private Pane consultaDescriptor;
    
    @FXML
    private Label lNombreD;
    
    @FXML
    private TextFlow tfTextoD;
    
    //Equipo
    @FXML
    private TextFlow tfEquipo;
    
    @FXML
    private Pane consultaEquipo;
    
    
    //Ficha
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
    
    @FXML
    private TextField lCompetencias;
    
    @FXML
    private TextField lHabilidades;
    
    @FXML
    private TextField lEquipo;
    
    @FXML
    private TextField lDispo;
    
    /**
     * Método de inicialización que se ejecuta automáticamente al cargar la vista asociada.
     * Se encarga de:
     * - Obtener el ID de ficha del usuario según la campaña activa.
     * - Mostrar información del usuario y la campaña en las etiquetas correspondientes.
     * - Llenar los ComboBox
     * - Cargar los datos adicionales de la ficha mediante el método cargarDatos().
     * - Mostrar el texto del equipo si el contenedor tfEquipo está presente.
     * 
     * @FXML
     */
    @FXML
    public void initialize() {
        fichaId = tsRepo.sacarIdUser(App.direc, App.campañaAct.getId());
        
        lNombreUser.setText(App.user.getUser());
        lManual.setText(App.campañaAct.getManual().getNombre());
        lFuncion.setText("Funcionar");
        lCampania.setText(App.campañaAct.getNombre());
        cargarDatos();
        if (cbRecursionCreatura != null) {
            List<String> lista = tsRepo.sacarRecursiones(App.direc);
            cbRecursionCreatura.setItems(FXCollections.observableArrayList(lista));
        }
        if(cbClases != null){
            List<String> lista = tsRepo.sacarClases(App.direc);
            cbClases.setItems(FXCollections.observableArrayList(lista));
        }
        if(cbDescriptor != null){
            List<String> lista = tsRepo.sacarDescriptores(App.direc);
            cbDescriptor.setItems(FXCollections.observableArrayList(lista));
        }
        if(tfEquipo != null){
            List<Text> textos = new ArrayList<>();
            String equipo = tsRepo.textEquipop(App.direc);
            Text t = new Text(equipo);
            textos.add(t);
            tfEquipo.getStyleClass().add("label-fijo");
            tfEquipo.getChildren().clear();
            tfEquipo.getChildren().addAll(textos);
        }
        
        
    }
 /**
 * Método encargado de mover la pestaña actual a la vista principal de
 * las campañas del usuario.
 * 
 */
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
    
    @FXML
    public void descriptor(){
        consultas();
        consultaDescriptor.setVisible(true);
    }

    @FXML
    public void equipo(){
        consultas();
        consultaEquipo.setVisible(true);
    }
    
    public void consultas(){
        consultaEquipo.setVisible(false);
        consultaDescriptor.setVisible(false);
        consultaClases.setVisible(false);
        consultarRecursion.setVisible(false);
    }
    
    /**
     * Método que se ejecuta al seleccionar una clase en el ComboBox.
     * Obtiene el índice de la clase seleccionada, recupera la información correspondiente
     * desde el repositorio y la transforma para mostrarla en el formato adecuado.
     * 
     * 
     */
    @FXML
    public void textoClase(){
        int clase = cbClases.getSelectionModel().getSelectedIndex(); 
        System.out.println(clase);
        List<String> lista = tsRepo.sacarClase(App.direc, clase);
        transformarTexto(lista,3,1);
    }
    
    /**
     * Método que se ejecuta al seleccionar una recursión en el ComboBox.
     * Recupera y transforma la información relacionada con la recursión seleccionada:
     * - Texto descriptivo de la recursión.
     * - Lista de criaturas asociadas.
     * - Rasgos característicos.
     * - Apartados relacionados.
     * Si ocurre un NullPointerException (por ejemplo, si no se selecciona nada),
     * limpia los elementos de las listas visuales.
     * 
     * 
     */
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
            
            List<String> listaApatados = tsRepo.sacarApartados(App.direc, recursion);
            lbApartados.setItems(FXCollections.observableArrayList(listaApatados));
        } catch (NullPointerException e) {
            lbListaCriaturas.getItems().clear();
            lbRasgosRecursion.getItems().clear();
            lbApartados.getItems().clear();
        } 
    }
   /**
    * Igual que textoClase
    */
    @FXML
    public void textoCriatura(){
        String criatura = (String) lbListaCriaturas.getSelectionModel().getSelectedItem();
        System.out.println(criatura);
        List<String> lista = tsRepo.sacarCriatura(App.direc, criatura);
        transformarTexto(lista,1,0);
    }

   /**
    * Igual que textoClase
    */
    @FXML
    public void textoRasgo(){
        String recursion = (String) cbRecursionCreatura.getValue();
        int rasgo = lbRasgosRecursion.getSelectionModel().getSelectedIndex(); 
        System.out.println(rasgo);
        List<String> lista = tsRepo.sacarRasgo(App.direc, recursion, rasgo);
        transformarTexto(lista,4,0);
    }
       /**
    * Igual que textoClase
    */
    @FXML
    public void textoApartados(){
        String recursion = (String) cbRecursionCreatura.getValue();
        int apartado = lbApartados.getSelectionModel().getSelectedIndex(); 
        System.out.println(apartado);
        List<String> lista = tsRepo.textApartado(App.direc, recursion, apartado);
        transformarTexto(lista,5,0);
    }
      /**
    * Igual que textoClase
    */
    @FXML
    public void textoDescriptor(){
        int num = cbDescriptor.getSelectionModel().getSelectedIndex();
        List<String> lista = tsRepo.textDescriptor(App.direc,num);
        transformarTexto(lista,6,2);
    }
    
      /**
     * Método encargado de transformar una lista de cadenas de texto en objetos `Text` formateados,
     * aplicando estilos específicos que necesito.
     * Luego, según la vista seleccionada, los textos se asignan al contenedor visual correspondiente
     * y se actualizan las etiquetas de nombre y nivel si corresponde.
     * 
     * @param texto Lista de cadenas que contiene: [0] nombre, [1] cuerpo del texto, [2] nivel (opcional).
     * @param num Indica el tipo de texto para determinar los encabezados a resaltar:
     *            1 = Criaturas, 2 = Recursiones, 3 = Clases, 4 = Habilidades, 5 = Objetos, 6 = Vínculos.
     * @param vista Determina dónde mostrar el contenido:
     *              0 = Vista general de criatura/recursión porque están en el mismo panel, 
     *              1 = Vista de clase, 
     *              2 = Vista de descriptor.
     */
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
        }else if(num == 5){
            encabezados = Set.of(
                "TIPO", "TEXTO"
            );
        }else if(num == 6){
            encabezados = Set.of(
                "VINCULO"
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

            lNivel.setText("");
            lNivel.setText(texto.get(2));
        }else if(vista == 1){
            lNombreClase.setText(texto.get(0));
            
            tfTextoClase.getStyleClass().add("label-fijo");
            tfTextoClase.getChildren().clear();
            tfTextoClase.getChildren().addAll(textos);
        }else if(vista == 2){
            lNombreD.setText(texto.get(0));
            
            tfTextoD.getStyleClass().add("label-fijo");
            tfTextoD.getChildren().clear();
            tfTextoD.getChildren().addAll(textos);
        }
        
    }
    
    /**
     * Limpia la lista de dispositivos
     * 
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
 * Lo mismo que nuevo Dispo
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
 * Lo mismo que nuevo Dispo
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
 * Lo mismo que nuevo Dispo
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

/**
     * Método que crea una instancia de ficha con los elementos introducidos en el formulario.
     * Con esa información y lo envía al repositorio correspondiente. 
     * Si ya existe una ficha previa con el mismo ID,
     * Luego asocia la ficha con la campaña activa.
     * Muestra una alerta con el resultado del proceso.
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
        
        Ficha fTemp = new Ficha();
        fTemp.setId(fichaId);
        fRepo.borrarFicha(App.direc, fTemp);
        
        int num = fRepo.mandarFicha(App.direc, f);
        
        if(num == 200){
            
            fRepo.fichaCampania(App.direc, App.campañaAct.getId(), f.getId());
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
    * Añade competencias al GridPane del formulario de la ficha
    * @param c .- Objeto competncia que le indique su nombre y si esta especializado
    * @throws IOException 
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

    /**
     * Introduce los datos de un objeto Ficha en el formulario de la Ficha
     */
    @FXML
    public void cargarDatos(){
        if(fichaId == 0){
            return;
        }
        Ficha ficha = fRepo.buscarFichaPorId(App.direc, fichaId);
        
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
}

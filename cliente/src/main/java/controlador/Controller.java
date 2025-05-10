/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;


import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import modelo.*;
import vista.*;

/**
 *
 * @author Néstor
 */
public class Controller {
    Vista v;
    Carga c;
    ClasesRepository cr;
    String url= "https://www.dnd5eapi.co/api/classes";

    /**
    *
    * Este es el constructor del controlador. Se ocupa de instanciar los parámetros,
    * así como de primero cargar la primera vista de carga para que cuando esté
    * cargado el ComboBox con las clases mostrar la vista completa.
    * Primero solicita a la Api el número que tiene recogido con la cantidad de
    * clases que hay, luego llama al método para cargarlas.
    * Tambien prepara un ActionLisener para estar atento a cuando eliger una clase
    * poder activar los eventos que mostrarán la información.
    * Cuando seleccionas una clase se recoge que has seleccionado y llama a los 
    * métodos que iran mostrando los datos.
    */
    public Controller(Vista v, Carga c, ClasesRepository cr) {
        this.v = v;
        this.c = c;
        this.cr = cr;
        int num = cr.sacarNumero(url);

        sacarClases(v.getCbClases(), num);
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        c.setVisible(false);
        v.setVisible(true);
        
        v.seleccionClase2(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                //Aqui va el evento
                JComboBox listaClases = v.getCbClases();
                String selec = (String) listaClases.getSelectedItem();
                
                caracteristicas();
                sacarHitDie(selec);
                sacarCompetencias(selec);
                sacarEquipamiento(selec);
                sacarNiveles(selec);
                sacarHechizos(selec);
            }
            
        });

    }
    /**
    *
    * Mete valores aleatoriaos del 9 al 20 en las caraceristicas
    */
    public void caracteristicas(){
        Random r = new Random();
        v.getjFuerza().setText(r.nextInt(9, 20)+"");
        v.getjDestre().setText(r.nextInt(9, 20)+"");
        v.getjConstitu().setText(r.nextInt(9, 20)+"");
        v.getjSab().setText(r.nextInt(9, 20)+"");
        v.getjInt().setText(r.nextInt(9, 20)+"");
        v.getjCarisma().setText(r.nextInt(9, 20)+"");
    }
    
    /**
    *
    * Se ocupa de llamar al método que solicite a la API las clases y miterlos en 
    * una lista para luego cargarlos en un ListBox de la vista
    */
    public void sacarClases(JComboBox listaClases, int index){
        String general = "";
        List<String> lista = cr.sacarGeneral(url, index);
        
        for (String clas : lista) {
            listaClases.addItem(clas);
        }
    }
    /**
    *
    *Este método carga en una lista el equipamiento base de la clase seleccionada
    *para posteriormente cargarlos en un modelo que muestre los datos en el ListBox
    * de la vista
    */
    public void sacarEquipamiento(String clase){
        JList<String> listaEquipa = v.getCbEqupamiento();
        DefaultListModel<String> modelo = new DefaultListModel<>();
        List<String> lista = cr.sacarEquipamiento(url, clase);
        
        for (String clas : lista) {
            modelo.addElement(clas);
        }
        listaEquipa.setModel(modelo);
    }
    
    /**
    *
    * Llama al método que saca los dados con los que se calcula la vida máxima 
    * y los muestra en un JLabel de la vista
    */
    public void sacarHitDie(String clase){
        int num = cr.sacarHitPoint(url, clase);
        JLabel aux = v.getlHitDie();
        aux.setText("1D"+num);
    }
    
    /**
    *
    *Este método llama al método que devuelve una lista con aquellas cosas con lo
    * que eres competente.
    * Con la lista cargo los datos en un modelo para mostrarlo en un ListBox de la 
    * lista. Los dos últimos datos los muestro a parte porque son las tiradas
    * de salvación y el modificador de daño con hechizos.
    */
    public void sacarCompetencias(String clase){
        JList<String> lista = v.getListCompetencias();
        DefaultListModel<String> modelo = new DefaultListModel<>();
        JList<String> listaSalv = v.getListaSalvacion();
        DefaultListModel<String> modeloSalv = new DefaultListModel<>();
        
        List<String> compe = cr.sacarCompetencias(url, clase);
        
        for (int i = 0; i < compe.size(); i++) {
            if (i < compe.size()-2) {
                modelo.addElement(compe.get(i));
            }else{
                modeloSalv.addElement(compe.get(i));
            }
        }
        lista.setModel(modelo);
        listaSalv.setModel(modeloSalv);
    }
    
    /**
    *
    *Este método llama al método que le solicita a la API que mejoras tienes en 
    * cada nivel y lo muestra en un ListBox
    */
    public void sacarNiveles(String clase){
        JTextArea texta = v.getTextaNiveles();
        texta.setText(cr.sacarNiveles(url, clase));
    }
    
    /**
    *
    *Este método llama al método que le solicita a la API que hechizos consigues 
    * en cada nivel y los muestra en un ListBox de la vista.
    */
    
    public void sacarHechizos(String clase){
        JTextArea texta = v.getTextaHechizo();
        texta.setText(cr.sacarHechizo(url, clase));
    }
    //AMONGUS
}

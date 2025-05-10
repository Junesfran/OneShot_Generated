/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package principal;

import controlador.Controller;
import javax.swing.JFrame;
import modelo.ClasesRepository;
import vista.*;


/**
 *
 * @author Diurno
 */
public class Psp18 {

/**
 *
 * El principal instancia los componentes que se van a utilizar y le da visibilidad
 * a la pantalla de carga mientras cargan las clases que se van a seleccionar.
 * Al final llama al controler donde estará toda la funcionalidad.
 */
    public static void main(String[] args) {
        Vista v = new Vista();
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setVisible(false);
        ClasesRepository cr = new ClasesRepository();
        
        Carga v2 = new Carga();
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v2.setVisible(true);
        
        Controller c = new Controller(v, v2, cr);
    }
}

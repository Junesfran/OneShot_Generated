/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Nestor y Asociados
 */
public class VistaPrincipal {
    public void listaCampanias(int valorInicial, GridPane grip){
        // Calcular columnas y filas para una cuadrícula lo más cuadrada posible
        int columnas = (int) Math.ceil(Math.sqrt(valorInicial));
        int filas = (int) Math.ceil((double) valorInicial / columnas);

        
        grip.getChildren().clear(); // Limpiar el grid antes de agregar nuevos botones

        for (int i = 0; i < valorInicial; i++) {
            Button b = new Button("Botón " + (i + 1));

            b.setPrefWidth(200);
            b.setStyle(
                "-fx-background-color: #8e5d16;"
            );
            b.setOnAction(e -> {
                System.out.println("Clic en " + b.getText());
            });

            int row = Math.round(i / columnas);
            int col = i % filas;

            grip.add(b, col, row);
        }
    }
}

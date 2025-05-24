/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oneshot;

import com.mycompany.oneshot.App;
import java.io.IOException;
import javafx.fxml.FXML;
import modelo.TheStrangeRepository;

/**
 *
 * @author Nestor y Asociados
 */
public class Controller {
    TheStrangeRepository tsRepo;
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}

    
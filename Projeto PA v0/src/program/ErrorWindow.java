/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author AndreLaptop
 */
public class ErrorWindow {

    public ErrorWindow(String warningMessage, String header, String body) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(warningMessage);
        alert.setHeaderText(header);
        alert.setContentText(body);

        alert.showAndWait();
    }

}

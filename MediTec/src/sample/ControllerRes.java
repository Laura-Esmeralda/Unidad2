package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import static sample.Interprete.Compilador.consola;
import static sample.Interprete.Compilador.imagenRes;

public class ControllerRes {
    @FXML VBox result;
    public void obtenerRes(ActionEvent e) {
        TextArea textArea=new TextArea(consola.getText());
        textArea.getText();
    }
}

package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML TextField txtText;
    @FXML ListView listEje;
    @FXML Label lblResultado;
    @FXML protected void initialize(){
        listEje.getItems().addAll("Ejercicio 1", "Ejercicio 2", "Validar Grupo", "Palabra con guión");
    }
    public void procesar(ActionEvent event){
        int seleccion=listEje.getSelectionModel().getSelectedIndex();
        String texto=txtText.getText();
        String expresion="";
        switch (seleccion){
            case 0:{
                expresion="^[e]([a-zA-Z0-9]{3,})[q|w]$";
                break;
            }
            case 1:{
                expresion="\\+([a-zA-Z]+)";
                break;
            }
            case 2:{
                expresion="^(ISC)-([1-9])[A|B]$";
                break;
            }
            case 3:{
                expresion="([a-zA-Z]{1,})[_]([a-zA-Z]{1,})";
                break;
            }
        }
        Pattern patron=Pattern.compile(expresion);
        Matcher matcher=patron.matcher(texto);
        if (matcher.matches()){
            lblResultado.setText("Si cumple");
        }else{
            lblResultado.setText("No cumple");
        }
    }
}

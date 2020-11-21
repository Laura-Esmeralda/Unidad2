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
        listEje.getItems().addAll("Ejercicio 1", "Ejercicio 2", "Validar Grupo", "Palabra con guión",
                "Ejercicio 5", "palabra con count", "dos nombres", "MAC address", "Ejercicio 9", "número binario",
                "número hexadecimal", "Operación", "número de control", "CURP");
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
            case 4:{
                expresion="^[^db]([a-zA-Z]{3,})";
                break;
            }
            case 5:{
                expresion="([a-zA-Z]{3,})[count]$";
                break;
            }
            case 6:{
                expresion="([a-zA-Z]{1,10})[ ]([a-zA-Z]{1,10})";
                break;
            }
            case 7:{
                expresion="([0-9]{1,})[.]([0-9]{1,})[.]([0-9]{1,})[.]([0-9]{1,})";
                break;
            }
            case 8:{
                expresion="(^[A-Z]{1})([a-zA-Z]{1,})([a-zA-Z]{1}$)";
                break;
            }
            case 9:{
                expresion="([0-1]{8})";
                break;
            }
            case 10:{
                expresion="([0-9A-F]{1,})";
                break;
            }
            case 11:{
                expresion="(^[0-9]{1})[-|+|*|/|^]([0-9]{1})";
                break;
            }
            case 12:{
                expresion="(^[0-9]{2})([CG|cg]{2})([0-9]{4})";
                break;
            }
            case 13:{
                expresion="(^[A-Z]{4})([0-9]{6})([A-Z]{6})([0-9]{2})";
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

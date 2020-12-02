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
                "número hexadecimal", "Operación", "número de control", "CURP", "X cantidad de pesos",
                "numero de tarjeta de credito", "etiqueta html", "color hexadecimal", "link", "Query Insert into",
                "sentencia if", "salto de linea", "url youtube");
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
            case 14:{
                expresion="(^[$]{1})([0-9]{1,})([.]{1})([0-9]{2})([mxn]{3}$)";
                break;
            }
            case 15:{
                expresion="([0-9]{4})[ ]([0-9]{4})[ ]([0-9]{4})[ ]([0-9]{4})";
                break;
            }
            case 16:{
                expresion="(^[<title>]{7})([a-zA-Z]{1,})([</title>]{8}$)";
                break;
            }
            case 17:{
                expresion="(^[#]{1})([A-Z0-9]{6})";
                break;
            }
            case 18:{
                expresion="(^[http]{4})[:][/][/]([a-zA-Z0-9]{1,})([.com]{4}$)";
                break;
            }
            case 19:{
                expresion="(^[INSERT INTO]{11})[ ]([a-zA-Z ]{1,})[(]([a-zA-Z0-9, ]{1,})[)]";
                break;
            }
            case 20:{
                expresion="(^[if(]{3})([-a-zA-Z=+.]{1,})[){ }]{4}";
                break;
            }
            case 21:{
                //salto de linea
                expresion="([a-zA-Z]{1,})\n([a-zA-Z]{1,})";
                break;
            }
            case 22:{
                expresion="(^[http]{4})[:][/][/]([www.youtube.com/]{16})([a-zA-Z0-9=?]{1,})";
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

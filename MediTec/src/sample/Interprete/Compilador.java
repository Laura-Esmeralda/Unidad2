package sample.Interprete;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.Main;

import java.io.IOException;

import static sample.Interprete.tiposToken.*;

public class Compilador {
    //String texto="";
    public static TextArea consola;
    public static AnchorPane imagenRes;
    public Compilador(TextArea tx,AnchorPane imagenRes){
        this.consola=tx;
        this.imagenRes=imagenRes;
    }
    public boolean compilar(String renglon){
        boolean errores=false;
        if (renglon.contains("print")){
            int i1=renglon.indexOf("(")+1;
            int i2=renglon.length()-1;
            String texto=renglon.substring(i1,i2);
            this.consola.appendText("\n "+texto);
        }else if (renglon.contains("declarar")){
            String[] arreglo=renglon.split(" ");
            if (validar(arreglo[1].trim())){
                String tipoToken="";
                if (arreglo[2].equals("sintoma")){
                    tipoToken=SINTOMA;
                }else{
                    tipoToken=MEDICAMENTO;
                }
                Token token=new Token(tipoToken,arreglo[1].trim());
                arrayToken.add(token);
            }else{
                this.consola.appendText("\n la variable con el nombre "+arreglo[1]+" ya esta declarada");
            }

        }else if (renglon.contains("tomar")){
            int i1=renglon.indexOf("(")+1;
            int i2=renglon.length()-1;
            String texto=renglon.substring(i1,i2).trim();
            if (validar(texto)){
                Token t1=buscarToken(texto);
                Alert alert=new Alert(Alert.AlertType.INFORMATION,t1.getNombre(),ButtonType.OK);
                alert.show();
            }else {
                this.consola.appendText("\n la variable con el nombre"+texto+" no esta declarada");
            }
            this.consola.appendText("\n "+texto);
        }else if (renglon.contains("tener")) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"solo necesitas descansar por" +
                    "un momento",ButtonType.OK);
            alert.show();
        }else if (renglon.contains("tratar")) {
            imagenRes.setStyle("pastillas");
        }else if (renglon.contains("prueba")) {
            try {
                Stage etapa1=new Stage();
                Parent etapa_2= FXMLLoader.load(getClass().getResource("res.fxml"));
                etapa1.setScene(new Scene(etapa_2));
                etapa1.show();
                Main.etapa.hide();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return errores;
    }
    public boolean validar(String nombre){
        Boolean existe=false;
        for (int x=0;x<arrayToken.size();x++){
            if (arrayToken.get(x).getNombre().equals(nombre)){
                existe=true;
            }
        }
        return existe;
    }
    public Token buscarToken(String nombre){
        Token existe=null;
        for (int x=0;x<arrayToken.size();x++){
            if (arrayToken.get(x).getNombre().equals(nombre)){
                return arrayToken.get(x);
            }
        }
        return existe;
    }
}

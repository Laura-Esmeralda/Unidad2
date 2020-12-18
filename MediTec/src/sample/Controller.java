package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.fxmisc.flowless.VirtualizedScrollPane;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.fxmisc.richtext.model.StyleSpans;
import org.fxmisc.richtext.model.StyleSpansBuilder;
import org.reactfx.EventStream;
import org.reactfx.Subscription;

import java.awt.*;
import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    @FXML VBox centro;
    @FXML TextArea consola;
    private CodeArea codeArea;
    private ExecutorService executor;
    @FXML protected void initialize(){
        executor =  Executors.newSingleThreadExecutor();
        codeArea =  new CodeArea();
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea));
        Subscription cleanupWhenDone = codeArea.multiPlainChanges()
                .successionEnds (Duration.ofMillis( 500))
                .supplyTask(this::computeHighlightingAsync)
                .awaitLatest(codeArea.multiPlainChanges())
                .filterMap(t ->{
                    if(t.isSuccess()){
                        return Optional.of(t.get());
                    } else {
                        t.getFailure().printStackTrace();
                        return Optional.empty();
                    }
                }) EventStream<StyleSpans<Collection<String>>>
                .subscribe(this::applyHighlighting);

        // llamar cuando ya no lo necesite:

        codeArea.replaceText( 0 , 0 , sampleCode);
        StackPane pane=new StackPane(new VirtualizedScrollPane<>(codeArea));
        pane.setPrefSize(600,600);
        centro.getChildren().add(new StackPane( new VirtualizedScrollPane<>(codeArea)));
    }
    public void compilar(ActionEvent event){
        System.out.println("asdf");

    }
    private static final String[] KEYWORDS = new String[] {
                " resumen " , " afirmar " , " booleano " , " romper " , " byte " ,
                " caso " , " captura " , " char " , " clase " , " const " ,
                " continuar " , " predeterminado " , " hacer " , " duplicar " , " si no " ,
                " enumeración " , " extiende " , " final " , " finalmente " , " flotante " ,
                " para " , " ir a " , " si " , " implementa " , " importar " ,
                " instancia de " , " int " , " interfaz " , " largo " , " nativo " ,
                " nuevo " , " paquete " , " privado " , " protegido " , " público " ,
                " retorno " , " corto " , " estático " , " estrictofp " , " super " ,
                " cambiar " , " sincronizado " , " esto " , " lanzar " , " lanzar " ,
                " transitorio " , " probar " , " vacío " , " volátil " , " mientras "
    };
    private static final String KEYWORD_PATTERN  =  "\\b("  + String.join( "|", KEYWORDS) +  ")\\ b";
    private static final String PAREN_PATTER = "\\(|\\)";
    private static final String BRACE_PATTERN = "\\{|\\}";
    private static final String BRACKET_PATTERN = "\\[|\\]";
    private static final String SEMICOLON_PATTERN = "\\;";
    private static final String STRING_PATTERN = "\"([^\"\\\\ ]| \\\\ .)*\"";
    private static final String COMMENT_PATTERN = "//[^\n]* " + " | " + " /\\*(.|\\R)*?\\*/";

    private static final Pattern Patters= Pattern.compile(
            " (? <KEYWORD> "  +  KEYWORD_PATTERN  +  " ) "
            +  " | (? <PAREN> "  +  PAREN_PATTER  +  " ) "
            +  " | (? <BRACE> "  +  BRACE_PATTERN  +  " ) "
            +  " | (? <BRACKET> "  +  BRACKET_PATTERN  +  " ) "
            +  " | (? <SEMICOLON> "  +  SEMICOLON_PATTERN  +  " ) "
            +  " | (? <STRING> "  +  STRING_PATTERN  +  " ) "
            +  " | (? <COMENTARIO> "  +  COMMENT_PATTERN  +  " ) "
    );

    private static final String sampleCode =  String.join ( "\n", new String[] {
                " paquete com.example; " ,
                " " ,
                " importar java.util. *; " ,
                " " ,
                " la clase pública Foo extiende la barra implementa Baz { " ,
                " " ,
                "     / * " ,
                "      * comentario de varias líneas " ,
                "      * / " ,
                "     public static void main (String [] args) { " ,
                "         // comentario de una sola línea " ,
                "         para (String arg: args) { " ,
                "             if (arg.length ()! = 0) " ,
                "                 System.out.println (arg); " ,
                "             más " ,
                "                 System.err.println ( \" Advertencia: cadena vacía como argumento \" );",
                "         }" ,
                "     }" ,
                "",
                "}"
    });

    private Task<StyleSpans<Collection<String>>> computeHighlightingAsync(){
        String text = codeArea.getText();
        Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
            @Override
            protected StyleSpans<Collection<String>>  call() throws Exception{
                return computeHighlighting(text);
            }
        };
        executor.execute(task);
        return task;
    }

    private void applyHighlighting(StyleSpans<Collection<String>>  highlighting){
        codeArea.setStyleSpans( 0 , highlighting);
    }

    private static StyleSpans<Collection<String>> computeHighlighting(String text) {
        Matcher matcher= PATTERN.matcher(text);
        int lastKwEnd =  0 ;
        StyleSpansBuilder< Collection < String > > spansBuilder
                =  new StyleSpansBuilder <> ();
        while (matcher.find()) {
            String styleClass =
                    matcher.group( "KEYWORD" ) !=  null  ?  " palabra clave "  :
                            matcher.group( " PAREN " ) !=  null  ?  " paren "  :
                                    matcher.group( " BRACE " ) !=  null  ?  " corsé "  :
                                            matcher.group(" SOPORTE " ) !=  null  ?  " soporte "  :
                                                    matcher.group(" SEMICOLON " ) !=  null  ?  " punto y coma "  :
                                                            matcher.group( "STRING" ) !=  null  ?  " cadena "  :
                                                                    matcher.group(" COMENTARIO " ) !=  null  ?  " comentario "  :
                                                                            null; /* nunca sucede */  assert styleClass !=  null ;
            spansBuilder.add(Collections.emptyList(),matcher.start() - lastKwEnd);
            spansBuilder.add(Collections.singleton(styleClass),matcher.end() - matcher.start());
            lastKwEnd = matcher.end();
        }
        spansBuilder.add(Collections.emptyList(),text.length() - lastKwEnd);
        return spansBuilder.create();
    }
}

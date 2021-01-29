package sample.Configs;

import java.util.regex.Pattern;

public class Configs {
    public static final String[] KEYWORDS = new String[] {
            "dolor de cabeza", "dolor de garganta", "tos", "fiebre", "dolor muscular",
            "calentura", "sarpullido","mareos", "dolor de muela", "dolor de estomago",
            "cólicos", "náuseas", "sueño", "presión alta", "inflamación", "congestionamiento",
            "gripe", "infección", "aspirina", "ibuprofeno", "paracetamol", "diclofenaco",
            "sal de uvas picot", "melox plus", "omeprazol", "buscapina", "flanax", "sincol",
            "fenilefrina", "clorfenamina", "amoxicilina", "bicarbonato de sodio", "mecilizina",
            "enalapril", "ginseng", "mililitros", "miligramos", "cucharada", "cucharadas", "pastilla",
            "pastillas", "sintoma", "sintomas", "medicamento", "medicamentos","medida","cantidad","a","b",
            "reposo", "tratamiento"
    };

    public static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + ")\\b";
    public static final String PAREN_PATTERN = "\\(|\\)";
    public static final String BRACE_PATTERN = "\\{|\\}";
    public static final String BRACKET_PATTERN = "\\[|\\]";
    public static final String SEMICOLON_PATTERN = "\\;";
    public static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
    public static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

    public static final Pattern PATTERN = Pattern.compile(
            "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
                    + "|(?<PAREN>" + PAREN_PATTERN + ")"
                    + "|(?<BRACE>" + BRACE_PATTERN + ")"
                    + "|(?<BRACKET>" + BRACKET_PATTERN + ")"
                    + "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
                    + "|(?<STRING>" + STRING_PATTERN + ")"
                    + "|(?<COMMENT>" + COMMENT_PATTERN + ")"
    );

    public static final String sampleCode = String.join("\n", new String[] {
            "declarar tos como sintoma;",
            "declarar paracetamol como medicamento;",
            "declarar mililitros como medida;",
            "declarar 1 cucharada como cantidad;",
            "tomar 2 pastillas de diclofenaco;",
            "tomar 2 pastillas de diclofenaco y 5 mililitros de amoxicilina;",
            "declarar cólicos y náuseas como sintomas;",
            "declarar sal de uvas picot y melox plus como medicamentos;",
            "si tiene tos y fiebre entonces tomar 2 pastillas de paracetamol;",
            "tomar a en caso de b;",
            "en caso de b tomar a ;",
            "print(10)"

    });
    public static String[] expresiones={
            "declarar [a-z]{1,} como (sintoma|medicamento|medida);",
            "declarar [a-z]{6} como (tratamiento);",
            "tomar [0-9]{1,} [a-z]{1,} de [a-z]{1,};",
            "declarar [0-9]{1,} como cantidad;",
            "tomar [0-9]{1,} [a-z]{1,} de [a-z]{1,} y [0-9]{1,} [a-z]{1,} de [a-z]{1,};",
            "declarar [a-z]{1,} y [a-z]{1,} como sintomas;",
            "declarar [a-z]{1,} y [a-z]{1,} como medicamentos;",
            "si tiene [a-z]{1,} y [a-z]{1,} entonces tomar [0-9]{1,} [a-z]{1,} de [a-z]{1,};",
            "tomar [a-z]{1,} en caso de [a-z]{1,};",
            "en caso de [a-z]{1,} tomar [a-z]{1,};",
                "print[(][a-z0-9]{1,}[)]",
                    "tomar[(][0-9]{1,}[)] [a-z]{1,} de [a-z]{1,} en caso de [a-z]{1,};",
                        "tener [a-z]{6} como (tratamiento)",
                            "tratar con [a-z]{1,}",
                                "prueba con [0-9]{1,} de [a-z]{1,} si tiene [a-z]{1,} y [a-z]{1,}"

    };
}

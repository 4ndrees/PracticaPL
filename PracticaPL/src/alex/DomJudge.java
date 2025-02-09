package alex;
import alex.AlexOperations.ECaracterInesperado;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoTiny;
import alex.ClaseLexica;
import alex.UnidadLexica;

public class DomJudge {
	private static void imprime(UnidadLexica unidad) {
		switch(unidad.clase()) {
		   case IDENTIFICADOR: case LITERAL_ENT: case LITERAL_REAL: case CADENA: System.out.println(unidad.lexema()); break;
                   default: System.out.println(unidad.clase().getImage());
		}
	}	

    public static void main(String[] args) throws FileNotFoundException, IOException {
    	//Reader input  = new InputStreamReader(System.in);
    	Reader input  = new InputStreamReader(new FileInputStream(args[0]));
    	AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
    	UnidadLexica unidad = null;
    	boolean error;
     	do {
     		error = false;  
     		try {  
     			unidad = al.yylex();
     			imprime(unidad);
     		}
     		catch(ECaracterInesperado e) {
     			System.out.println("ERROR");
     			error = true;
     		}
     	}
     	while (error || unidad.clase() != ClaseLexica.EOF);
    }        
} 

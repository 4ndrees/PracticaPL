package alex;

public class AlexOperations {
	public static class ECaracterInesperado extends RuntimeException {
		public ECaracterInesperado(String msg) {
			super(msg);
	    }
	}  
	    
	private AnalizadorLexicoTiny alex;
	public AlexOperations(AnalizadorLexicoTiny alex) {
		this.alex = alex;   
	}
	public UnidadLexica unidadModulo() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MODULO);
	}
	public UnidadLexica unidadAmpersand() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AMPERSAND);
	}
	public UnidadLexica unidadPAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_APERTURA);
	}
	public UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PARENTESIS_CIERRE);
	}
	public UnidadLexica unidadMul() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MUL);
	}
	public UnidadLexica unidadSuma() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAS);
	}
	public UnidadLexica operadorComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.COMA);
	}
	public UnidadLexica unidadResta() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOS);
	}
	public UnidadLexica operadorPunto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO);
	}
	public UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DIV);
	}
	public UnidadLexica unidadLiteralEntero() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITERAL_ENT, alex.lexema());
	}
	public UnidadLexica operadorPuntoYComa() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PUNTO_Y_COMA);
	}
	public UnidadLexica unidadMenor() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR);
	}
	public UnidadLexica unidadMayor() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR);
	}
	public UnidadLexica unidadAsignacion() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ASIGNACION);
	}
	public UnidadLexica unidadArroba() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ARROBA);
	}
	public UnidadLexica unidadId() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.IDENTIFICADOR, alex.lexema());
	}
	public UnidadLexica unidadCorcheteAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_APERTURA);
	}
	public UnidadLexica unidadCorcheteCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CORCHETE_CIERRE);
	}
	public UnidadLexica unidadAcentoCircunflejo() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ACENTO_CIRCUNFLEJO);
	}
	public UnidadLexica unidadLlaveAp() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_APERTURA);
	}
	public UnidadLexica unidadLlaveCierre() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.LLAVE_CIERRE);
	}
	public UnidadLexica unidadDistinto() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DISTINTO);
	}
	public UnidadLexica unidadCadena() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.CADENA, alex.lexema());
	}
	public UnidadLexica unidadDobleAmpersand() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DOBLE_AMPERSAND);
	}
	public UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MENOR_IGUAL);
	}
	public UnidadLexica unidadIgualdad() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IGUALDAD);
	}
	public UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.MAYOR_IGUAL);
	}
	public UnidadLexica unidadIf() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.IF);
	}
	public UnidadLexica unidadNl() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NL);
	}
	public UnidadLexica unidadOr() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.OR);
	}
	public UnidadLexica unidadLiteralReal() {
		return new UnidadLexicaMultivaluada(alex.fila(), alex.columna(), ClaseLexica.LITERAL_REAL, alex.lexema());
	}
	public UnidadLexica unidadAnd() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.AND);
	}
	public UnidadLexica unidadInt() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.INT);
	}
	public UnidadLexica unidadNew() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NEW);
	}
	public UnidadLexica unidadNot() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NOT);
	}
	public UnidadLexica unidadBool() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.BOOL);
	}
	public UnidadLexica unidadCall() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.CALL);
	}
	public UnidadLexica unidadElse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.ELSE);
	}
	public UnidadLexica unidadNull() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.NULL);
	}
	public UnidadLexica unidadProc() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.PROC);
	}
	public UnidadLexica unidadRead() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.READ);
	}
	public UnidadLexica unidadReal() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.REAL);
	}
	public UnidadLexica unidadTrue() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TRUE);
	}
	public UnidadLexica unidadType() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.TYPE);
	}
	public UnidadLexica unidadFalse() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.FALSE);
	}
	public UnidadLexica unidadWhile() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WHILE);
	}
	public UnidadLexica unidadWrite() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.WRITE);
	}
	public UnidadLexica unidadDelete() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.DELETE);
	}
	public UnidadLexica unidadString() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRING);
	}
	public UnidadLexica unidadStruct() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(), ClaseLexica.STRUCT);
	}
	public UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(alex.fila(), alex.columna(),ClaseLexica.EOF);
		}
	public void error() {
		System.err.println("***"+alex.fila()+" Caracter inexperado: "+alex.lexema());
		System.exit(1);
	}
}

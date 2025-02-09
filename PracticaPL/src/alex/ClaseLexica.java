package alex;

public enum ClaseLexica {
	CADENA,
	IDENTIFICADOR,
	LITERAL_ENT,
	LITERAL_REAL,
	INT("<int>"),
	REAL("<real>"),
	BOOL("<bool>"),
	STRING("<string>"),
	NULL("<null>"),
	PROC("<proc>"),
	TRUE("<true>"),
	FALSE("<false>"),
	IF("<if>"),
	ELSE("<else>"),
	WHILE("<while>"),
	STRUCT("<struct>"),
	NEW("<new>"),
	DELETE("<delete>"),
	READ("<read>"),
	WRITE("<write>"),
	NL("<nl>"),
	TYPE("<type>"),
	CALL("<call>"),
	AND("<and>"),
	OR("<or>"),
	NOT("<not>"),
	AMPERSAND("&"),
	DOBLE_AMPERSAND("&&"),
	ARROBA("@"),
	PUNTO_Y_COMA(";"),
	LLAVE_APERTURA("{"),
	LLAVE_CIERRE("}"),
	ACENTO_CIRCUNFLEJO("^"),
	CORCHETE_APERTURA("["),
	CORCHETE_CIERRE("]"),
	PARENTESIS_APERTURA("("),
	PARENTESIS_CIERRE(")"),
	COMA(","),
	PUNTO("."),
	MAS("+"),
	MENOS("-"),
	MUL("*"),
	DIV("/"),
	MODULO("%"),
	MENOR("<"),
	MAYOR(">"),
	MENOR_IGUAL("<="),
	MAYOR_IGUAL(">="),
	IGUALDAD("=="),
	DISTINTO("!="),
	ASIGNACION("="),
	EOF("EOF");
	
	private String image;
	
	public String getImage() {
		return image;
	}
	
	private ClaseLexica() {
		image = toString();
	}
	
	private ClaseLexica(String image) {
		this.image = image;  
	}
}

package alex;

public enum ClaseLexica {
	CADENA,
	IDENTIFICADOR,
	LITERAL_ENT,
	LITERAL_REAL,
	INT,
	REAL,
	BOOL,
	STRING,
	NULL,
	PROC,
	TRUE,
	FALSE,
	IF,
	ELSE,
	WHILE,
	STRUCT,
	NEW,
	DELETE,
	READ,
	WRITE,
	NL,
	TYPE,
	CALL,
	AMPERSAND,
	DOBLE_AMPERSAND,
	ARROBA,
	PUNTO_Y_COMA,
	LLAVES_APERTURA,
	LLAVES_CIERRE,
	ACENTO_CIRCUNFLEJO,
	CORCHETE_APERTURA,
	CORCHETE_CIERRE,
	PARENTESIS_APERTURA,
	PARENTESIS_CIERRE,
	COMA,
	PUNTO,
	AND,
	OR,
	NOT,
	MAS,
	MENOS,
	POR,
	DIV,
	MODULO,
	MENOR,
	MAYOR,
	MENOR_IGUAL,
	MAYOR_IGUAL,
	IGUALDAD,
	DISTINTO,
	ASIGNACION,
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

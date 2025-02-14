package ImplementacionTiny0.alex;

public enum ClaseLexica {
	SEPARADOR(" "), 
	EOF("EOF"),
	MAS("+"), 
	MENOS("-"), 
	MENOSUNITARIO("-"), 
	POR("*"), 
	DIV("/"), 
	IGUAL("="), 
	MENOR("<"),
    MAYOR(">"), 
    MENORIGUAL("<="), 
    MAYORIGUAL(">="), 
    IGUALDAD("=="), 
    DISTINTO("!="), 
    PAPERTURA("("), 
    PCIERRE(")"), 
    IDEN, 
    INT, 
    REAL, 
    BOOL, 
    TRUE("true"), 
    FALSE("false"), 
    AND("and"), 
    OR("or"), 
    NOT("not"), 
    EXPONENCIAL("^"), 
    EXPONENCIAL0("^"), 
    SECCION("@@"), 
    ARROBA("@"), 
    PUNTOYCOMA(";"), 
    ABRELLAVE("{"), 
    CIERRELLAVE("}");
	
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

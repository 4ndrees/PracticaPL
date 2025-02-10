package ImplementacionTiny0.alex;

public enum ClaseLexica {
	SEPARADOR, MAS, MENOS, MENOSUNITARIO, POR, DIV, IGUAL, MENOR,
	MAYOR, MENORIGUAL, MAYORIGUAL, IGUALDAD, DISTINTO, PAPERTURA, 
	PCIERRE, ENT, NUMREAL, IDEN, EOF, INT, REAL("<real>"), BOOL, TRUE,
	FALSE, AND, OR, NOT, EXPONENCIAL, EXPONENCIAL0, SECCION, 
	ARROBA, PUNTOYCOMA, ABRELLAVE("{"), CIERRELLAVE;
	
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

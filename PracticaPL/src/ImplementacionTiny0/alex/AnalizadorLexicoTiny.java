package alex;

import java.io.FileInputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.IOException;

public class AnalizadorLexicoTiny {
    
	public static class ECaracterInesperado extends RuntimeException {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ECaracterInesperado(String msg) {
			super(msg);
		}
	}; 

	private Reader input;
	private StringBuffer lex;
	private int sigCar;
	private int filaInicio;
	private int columnaInicio;
	private int filaActual;
	private int columnaActual;
	private static String NL = System.getProperty("line.separator");
   
	private static enum Estado {
		INICIO, REC_POR, REC_DIV, REC_PAPERTURA, REC_PCIERR, REC_IGUAL,
		REC_MAS, REC_MENOS, REC_ID, REC_ENT, REC_0, REC_IDEC, REC_DEC,
		REC_0DEC, REC_COM, REC_EOF, REC_MENOR, REC_MAYOR, REC_MENORIGUAL,
		REC_MAYORIGUAL, REC_MENOS_U, REC_MENOS_UNITARIO, REC_IGUALDAD, 
		REC_EXCLAMACION, REC_DISTINTO, REC_IEXPL, REC_EXP, REC_IEXPS, 
		REC_EXP0, REC_AMPERSAN, REC_SECCION, REC_ARROBA, REC_PUNTOYCOMA, 
		REC_ABRELLAVE, REC_CIERRALLAVE
	}

	private Estado estado;

	public AnalizadorLexicoTiny(Reader input) throws IOException {
		this.input = input;
		lex = new StringBuffer();
		sigCar = input.read();
		filaActual=1;
		columnaActual=1;
	}
   
	public UnidadLexica sigToken() throws IOException {
		estado = Estado.INICIO;
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		lex.delete(0,lex.length());
		while(true) {
			switch(estado) {
			case INICIO: 
				if(hayLetra() || hay_())  transita(Estado.REC_ID);
				else if (hayDigitoPos()) transita(Estado.REC_ENT);
				else if (hayCero()) transita(Estado.REC_0);
				else if (haySuma()) transita(Estado.REC_MAS);
				else if (hayResta()) transita(Estado.REC_MENOS);
				else if (hayMul()) transita(Estado.REC_POR);
				else if (hayDiv()) transita(Estado.REC_DIV);
				else if (hayAmpersan()) transita(Estado.REC_AMPERSAN);
				else if (hayArroba()) transita(Estado.REC_ARROBA);
				else if (hayPuntoYComa()) transita(Estado.REC_PUNTOYCOMA);
				else if (hayALlave()) transita(Estado.REC_ABRELLAVE);
				else if (hayCLlave()) transita(Estado.REC_CIERRALLAVE);
				else if (hayMenor()) transita(Estado.REC_MENOR);
				else if (hayMayor()) transita(Estado.REC_MAYOR);
				else if (hayExc()) transita(Estado.REC_EXCLAMACION);
				else if (hayPAp()) transita(Estado.REC_PAPERTURA);
				else if (hayPCierre()) transita(Estado.REC_PCIERR);
				else if (hayIgual()) transita(Estado.REC_IGUAL);
				else if (hayAlmohadilla()) transitaIgnorando(Estado.REC_COM);
				else if (haySep()) transitaIgnorando(Estado.INICIO);
				else if (hayEOF()) transita(Estado.REC_EOF);
				else error();
				break;
			case REC_ID: 
				if (hayLetra() || hayDigito() || hay_()) transita(Estado.REC_ID);
				else return unidadId();               
				break;
			case REC_ENT:
				if (hayDigito()) transita(Estado.REC_ENT);
				else if (hayPunto()) transita(Estado.REC_IDEC);
				else if (hayExp()) transita(Estado.REC_IEXPL);
				else return unidadEnt();
				break;
			case REC_0:
				if (hayPunto()) transita(Estado.REC_IDEC);
				else return unidadEnt();
				break;
			case REC_MAS:
				if (hayDigitoPos()) transita(Estado.REC_ENT);
				else if(hayCero()) transita(Estado.REC_0);
				else return unidadMas();
				break;
			case REC_MENOS: 
				if (hayDigitoPos()) transita(Estado.REC_ENT);
				else if(hayCero()) transita(Estado.REC_0);
				else return unidadMenos();
				break;
			case REC_POR: return unidadPor();
			case REC_DIV: return unidadDiv();
			case REC_AMPERSAN:
				if (hayAmpersan()) transita(Estado.REC_SECCION);
				else error();
				break;
			case REC_SECCION: return unidadSeccion();
			case REC_ARROBA: return unidadArroba();
			case REC_PUNTOYCOMA: return unidadPuntoYComa();
			case REC_ABRELLAVE: return unidadALlave();
			case REC_CIERRALLAVE: return unidadCLlave();
			case REC_PAPERTURA: 
				if (hayResta()) transita(Estado.REC_MENOS_U);
				else return unidadPAPERTURA();
				break;
			case REC_MENOS_U:
				if (hayPCierre()) transita(Estado.REC_MENOS_UNITARIO);
				else error();
				break;
			case REC_MENOS_UNITARIO: return unidadMenosUnitario();
			case REC_PCIERR: return unidadPCierre();
			case REC_IGUAL: 
				if (hayIgual()) transita(Estado.REC_IGUALDAD);
				else return unidadIgual();
				break;
			case REC_IGUALDAD: return unidadIgualdad();
			case REC_MENOR:
				if (hayIgual()) transita(Estado.REC_MENORIGUAL);
				else return unidadMenor();
				break;
			case REC_MAYOR:
				if (hayIgual()) transita(Estado.REC_MAYORIGUAL);
				else return unidadMayor();
				break;
			case REC_MENORIGUAL: return unidadMenorIgual();
			case REC_MAYORIGUAL: return unidadMayorIgual();
			case REC_EXCLAMACION:
				if (hayIgual()) transita(Estado.REC_DISTINTO);
				else error();
				break;
			case REC_DISTINTO: return unidadDistinto();
			case REC_COM: 
				if (hayNL()) transitaIgnorando(Estado.INICIO);
				else if (hayEOF()) transita(Estado.REC_EOF);
				else transitaIgnorando(Estado.REC_COM);
				break;
			case REC_EOF: return unidadEof();
			case REC_IDEC:
				if (hayDigito()) transita(Estado.REC_DEC);
				else error();
				break;
			case REC_DEC: 
				if (hayDigitoPos()) transita(Estado.REC_DEC);
				else if (hayCero()) transita(Estado.REC_0DEC);
				else if (hayExp()) transita(Estado.REC_IEXPL);
				else return unidadReal();
				break;
			case REC_0DEC: 
				if (hayDigitoPos()) transita(Estado.REC_DEC);
				else if (hayCero()) transita(Estado.REC_0DEC);
				else error();
				break;
			case REC_IEXPL:
				if (hayDigitoPos()) transita(Estado.REC_EXP);
				else if (haySuma() || hayResta()) transita(Estado.REC_IEXPS);
				else if (hayCero()) transita(Estado.REC_EXP0);
				else error();
				break;
			case REC_IEXPS:
				if (hayDigitoPos()) transita(Estado.REC_EXP);
				else if (hayCero()) transita(Estado.REC_EXP0);
				else error();
				break;
			case REC_EXP:
				if (hayDigito()) transita(Estado.REC_EXP);
				else return unidadExp();
				break;
			case REC_EXP0: return unidadExp0();
			}
		}    
	}	

	private void transita(Estado sig) throws IOException {
		lex.append((char)sigCar);
		sigCar();         
		estado = sig;
	}
   
	private void transitaIgnorando(Estado sig) throws IOException {
		sigCar();         
		filaInicio = filaActual;
		columnaInicio = columnaActual;
		estado = sig;
	}
	
	private void sigCar() throws IOException {
		sigCar = input.read();
		if (sigCar == NL.charAt(0)) saltaFinDeLinea();
		if (sigCar == '\n') {
			filaActual++;
			columnaActual=0;
		}
		else {
			columnaActual++;  
		}
	}
	
	private void saltaFinDeLinea() throws IOException {
		for (int i=1; i < NL.length(); i++) {
			sigCar = input.read();
			if (sigCar != NL.charAt(i)) error();
		}
		sigCar = '\n';
	}
   
	private boolean hayLetra() {return sigCar >= 'a' && sigCar <= 'z' ||
                                      sigCar >= 'A' && sigCar <= 'Z';}
	private boolean hayDigitoPos() {return sigCar >= '1' && sigCar <= '9';}
	private boolean hayCero() {return sigCar == '0';}
	private boolean hayDigito() {return hayDigitoPos() || hayCero();}
	private boolean haySuma() {return sigCar == '+';}
	private boolean hayResta() {return sigCar == '-';}
	private boolean hayMul() {return sigCar == '*';}
	private boolean hayDiv() {return sigCar == '/';}
	private boolean hayPAp() {return sigCar == '(';}
	private boolean hayPCierre() {return sigCar == ')';}
	private boolean hayIgual() {return sigCar == '=';}
	private boolean hayPunto() {return sigCar == '.';}
	private boolean hayAlmohadilla() {return sigCar == '#';}
	private boolean haySep() {return sigCar == ' ' || sigCar == '\t' || sigCar=='\n';}
	private boolean hayNL() {return sigCar == '\r' || sigCar == '\b' || sigCar == '\n';}
	private boolean hayEOF() {return sigCar == -1;}
	
	private boolean hay_() {return sigCar == '_';}
	private boolean hayMenor() {return sigCar == '<';}
	private boolean hayMayor() {return sigCar == '>';}
	private boolean hayExc() {return sigCar == '!';}
	private boolean hayExp() {return sigCar == 'e' || sigCar == 'E';}
	private boolean hayAmpersan() {return sigCar == '&';};
	private boolean hayArroba() {return sigCar == '@';};
	private boolean hayPuntoYComa() {return sigCar == ';';};
	private boolean hayALlave() {return sigCar == '{';}
	private boolean hayCLlave() {return sigCar == '}';}
	
	private UnidadLexica unidadId() {
		switch(lex.toString()) {
			case "int":  
				return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.INT);
			case "real":    
				return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.REAL);
			case "bool":    
				return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.BOOL);
			case "and":    
				return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.AND);
			case "or":    
				return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.OR);
			case "not":    
				return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.NOT);
			case "true":    
				return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.TRUE);
			case "false":    
				return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.FALSE);
			default:    
				return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.IDEN,lex.toString());     
		}
	}
	
	private UnidadLexica unidadEnt() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.ENT,lex.toString());     
	} 
	
	private UnidadLexica unidadReal() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.REAL,lex.toString());     
	}    
   
	private UnidadLexica unidadMas() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MAS);     
	} 
	
	private UnidadLexica unidadMenos() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.MENOS);     
	} 
	
	private UnidadLexica unidadPor() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.POR);     
	} 
	
	private UnidadLexica unidadDiv() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.DIV);     
	} 
	
	private UnidadLexica unidadPAPERTURA() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PAPERTURA);     
	}
	
	private UnidadLexica unidadPCierre() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.PCIERRE);     
	} 
	
	private UnidadLexica unidadIgual() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.IGUAL);     
	}  
	
	
	private UnidadLexica unidadEof() {
		return new UnidadLexicaUnivaluada(filaInicio,columnaInicio,ClaseLexica.EOF);     
	} 
	
	private UnidadLexica unidadMenor() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.MENOR,lex.toString());     
	} 
	
	private UnidadLexica unidadMayor() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.MAYOR,lex.toString());     
	} 
	
	private UnidadLexica unidadMenorIgual() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.MENORIGUAL,lex.toString());     
	} 
	
	private UnidadLexica unidadMayorIgual() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.MAYORIGUAL,lex.toString());     
	}
	
	private UnidadLexica unidadIgualdad() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.IGUALDAD,lex.toString());     
	} 
	
	private UnidadLexica unidadMenosUnitario() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.MENOSUNITARIO,lex.toString());     
	} 
	
	private UnidadLexica unidadDistinto() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.DISTINTO,lex.toString());     
	} 
	
	private UnidadLexica unidadExp() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.EXPONENCIAL,lex.toString());     
	}
	
	private UnidadLexica unidadExp0() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.EXPONENCIAL0,lex.toString());     
	}
	
	private UnidadLexica unidadSeccion() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.SECCION,lex.toString());     
	}
	
	private UnidadLexica unidadArroba() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.ARROBA,lex.toString());
	}
	
	private UnidadLexica unidadPuntoYComa() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.PUNTOYCOMA,lex.toString());
	}
	
	private UnidadLexica unidadALlave() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.ABRELLAVE,lex.toString());
	}
	
	private UnidadLexica unidadCLlave() {
		return new UnidadLexicaMultivaluada(filaInicio,columnaInicio,ClaseLexica.CIERRELLAVE,lex.toString());
	}
	
	private void error() {
		int curCar = sigCar;
		try{
			sigCar();
		}
		catch(IOException e) {}
		throw new ECaracterInesperado("("+filaActual+','+columnaActual+"):Caracter inexperado:"+(char)curCar); 
	}

	public static void main(String arg[]) throws IOException {
	   	Reader input = new InputStreamReader(new FileInputStream("prueba.txt"));
	   	AnalizadorLexicoTiny al = new AnalizadorLexicoTiny(input);
	   	UnidadLexica unidad;
	   	do {
	   		unidad = al.sigToken();
	   		System.out.println(unidad);
	   	}
	   	while (unidad.clase() != ClaseLexica.EOF);
		} 
}
package alex;

%%
%line
%column
%class AnalizadorLexicoTiny
%type UnidadLexica
%unicode
%public

%{
	private AlexOperations ops;
	public String lexema() {return yytext();}
	public int fila() {return yyline+1;}
	public int columna() {return yycolumn+1;}
%}

%eofval{
	return ops.unidadEof();
%eofval}

%init{
	ops = new AlexOperations(this);
%init}

letra = ([A-Z]|[a-z])
numeroPositivo = [1-9]
numero = ({numeroPositivo}|0)
parteEntera = ({numeroPositivo}{numero}*|0)
parteDecimal = \.(({numero}*{numeroPositivo})|0)
parteExp = (e|E)(\+|\-)?{parteEntera}
cadena = \"[^\"]*\"
identificador = ({letra}|\_)({letra}|{numero}|\_)*
literalEntero = (\+|\-)?{parteEntera}
literalReal = {literalEntero}({parteExp}|{parteDecimal}|{parteDecimal}{parteExp})
int = (i|I)(n|N)(t|T)  
real = (r|R)(e|E)(a|A)(l|L)  
bool = (b|B)(o|O)(o|O)(l|L)  
string = (s|S)(t|T)(r|R)(i|I)(n|N)(g|G)  
and = (a|A)(n|N)(d|D)  
or = (o|O)(r|R)  
not = (n|N)(o|O)(t|T)  
null = (n|N)(u|U)(l|L)(l|L)  
proc = (p|P)(r|R)(o|O)(c|C)  
true = (t|T)(r|R)(u|U)(e|E)  
false = (f|F)(a|A)(l|L)(s|S)(e|E)  
if = (i|I)(f|F)  
else = (e|E)(l|L)(s|S)(e|E)  
while = (w|W)(h|H)(i|I)(l|L)(e|E)  
struct = (s|S)(t|T)(r|R)(u|U)(c|C)(t|T)  
new = (n|N)(e|E)(w|W)  
delete = (d|D)(e|E)(l|L)(e|E)(t|T)(e|E)  
read = (r|R)(e|E)(a|A)(d|D)  
write = (w|W)(r|R)(i|I)(t|T)(e|E)  
nl = (n|N)(l|L)  
type = (t|T)(y|Y)(p|P)(e|E)  
call = (c|C)(a|A)(l|L)(l|L)  
ampersand = &
dobleAmpersand = &&
arroba = @
puntoYComa = \;
llaveApertura = \{
llaveCierre = \}
acentoCircunflejo = \^
corcheteApertura = \[
corcheteCierre = \]
parentesisApertura = \(
parentesisCierre = \)
coma = \,
punto = \.
mas = \+
menos = \-
por = \*
div = \/
modulo = \% 
menor = \<
mayor = \>
menorIgual = \<\=
mayorIgual = \>\=
igualdad = \=\=
distinto = \!\=
asignacion = \=
separador = [ \b\r\t\n]
comentario = ##[^\n]*

%%
{separador} {}
{comentario} {}
{int} {return ops.unidadInt();}
{real} {return ops.unidadReal();}
{bool} {return ops.unidadBool();}
{string} {return ops.unidadString();}
{and} {return ops.unidadAnd();}
{or} {return ops.unidadOr();}
{not} {return ops.unidadNot();}
{null} {return ops.unidadNull();}
{proc} {return ops.unidadProc();}
{true} {return ops.unidadTrue();}
{false} {return ops.unidadFalse();}
{if} {return ops.unidadIf();}
{else} {return ops.unidadElse();}
{while} {return ops.unidadWhile();}
{struct} {return ops.unidadStruct();}
{new} {return ops.unidadNew();}
{delete} {return ops.unidadDelete();}
{read} {return ops.unidadRead();}
{write} {return ops.unidadWrite();}
{nl} {return ops.unidadNl();}
{type} {return ops.unidadType();}
{call} {return ops.unidadCall();}
{cadena} {return ops.unidadCadena();}
{identificador} {return ops.unidadId();}
{literalEntero} {return ops.unidadLiteralEntero();}
{literalReal} {return ops.unidadLiteralReal();}
{mas} {return ops.unidadSuma();}
{menos} {return ops.unidadResta();}
{por} {return ops.unidadMul();}
{div} {return ops.unidadDiv();}
{modulo} {return ops.unidadModulo();}
{menor} {return ops.unidadMenor();}
{mayor} {return ops.unidadMayor();}
{menorIgual} {return ops.unidadMenorIgual();}
{mayorIgual} {return ops.unidadMayorIgual();}
{igualdad} {return ops.unidadIgualdad();}
{distinto} {return ops.unidadDistinto();}
{asignacion} {return ops.unidadAsignacion();}
{ampersand} {return ops.unidadAmpersand();}
{dobleAmpersand} {return ops.unidadDobleAmpersand();}
{arroba} {return ops.unidadArroba();}
{puntoYComa} {return ops.operadorPuntoYComa();}
{llaveApertura} {return ops.unidadLlaveAp();}
{llaveCierre} {return ops.unidadLlaveCierre();}
{acentoCircunflejo} {return ops.unidadAcentoCircunflejo();}
{corcheteApertura} {return ops.unidadCorcheteAp();}
{corcheteCierre} {return ops.unidadCorcheteCierre();}
{parentesisApertura} {return ops.unidadPAp();}
{parentesisCierre} {return ops.unidadPCierre();}
{coma} {return ops.operadorComa();}
{punto} {return ops.operadorPunto();}
[^] {ops.error();}
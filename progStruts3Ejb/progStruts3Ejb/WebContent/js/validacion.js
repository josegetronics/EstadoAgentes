//-----------------------------------------------------------
//-- Definimos el prototipo del método para el objeto String

//-----------------------------------------------------------
String.prototype.Trim = String_trim;



//----------------------------------------------------------------------
//     Función : validacionAlfaNum
// Descripción : Comprueba que en el texto sólo hay letras sin acentuar,
//               los caracteres extendidos (ñÑçÇ) o dígitos
//  Parámetros : Cadena con el texto a analizar
//     Retorno : false / true
//       Notas : -
//----------------------------------------------------------------------
function validacionAlfaNum( cTexto ) {
	// Recorremos los datos introducidos
	for ( nPos = 0; nPos < cTexto.length; nPos++ ) {
		var cCaracter = cTexto.charAt( nPos );
		if ( !( (cCaracter >= '0' && cCaracter <= '9') || (cCaracter >= 'a' && cCaracter <= 'z') ||
				 (cCaracter >= 'A' && cCaracter <= 'Z') ||	(cCaracter == 'ñ' || cCaracter == 'Ñ' ) ||
				 (cCaracter == 'ç' || cCaracter == 'Ç') || (cCaracter == ' ' ) ||
				 (cCaracter == 'Á' || cCaracter == 'á') || (cCaracter == 'É' || cCaracter == 'é') ||
				 (cCaracter == 'Ï' || cCaracter == 'í') || (cCaracter == 'Ö' || cCaracter == 'ó') ||
				 (cCaracter == 'Ú' || cCaracter == 'ú') ||
				 (cCaracter == '@' || cCaracter == ',') || cCaracter == '.'))
		{
			return false;
		}
	}
	return true;
}



//----------------------------------------------------------------------
//     Función : validacionFichero
// Descripción : Comprueba que el fichero no tiene una extensión prohibida
//  Parámetros : Cadena con el nombre del fichero
//     Retorno : false / true
//       Notas : -
//----------------------------------------------------------------------
function validacionFichero( cTexto ) {
	extension = cTexto.substring(cTexto.lastIndexOf(".") + 1);

	if (extension.toUpperCase() == 'COM' ||
	    extension.toUpperCase() == 'EXE' ||
	    extension.toUpperCase() == 'VBS' ||
	    extension.toUpperCase() == 'SCR' ||
	    extension.toUpperCase() == 'PIF' ){
		return false;
	}
	else{
		return true;
	}
}



//----------------------------------------------------------------------
// Descripción : Comprobación básica de que todos los caracteres de la
//               cadena son sólo uno de estos "0123456789+-.,() "
//  Parámetros : Cadena con en número a comprobar
//     Retorno : false / true
//----------------------------------------------------------------------
function validacionNumerico( cNumero ) {
	// Recorremos los datos introducidos
	for ( nPos = 0; nPos < cNumero.length; nPos++ ) {
		var cCaracter = cNumero.charAt( nPos )
		if ( isNaN( parseInt( cCaracter ) )
		     && cCaracter != "-"
		     && cCaracter != "+"
		     && cCaracter != "."
		     && cCaracter != ","
		     && cCaracter != "("
		     && cCaracter != ")"
		     && cCaracter != " " ) {
			return false;
		}
	}
	return true;
}



//----------------------------------------------------------------------
//     Función : controlNumCaracteres
// Descripción : Control del máximo número de caracteres en un Textarea (puede estar dentro de  0 , 1 o 2 capas)
//  Parámetros : maxNumCaracteres, nameTextArea, nameFormulario, nameCapa
//	         maxNumCaracteres : Máximo número de caracteres permitidos en el TextArea
//		 nameTextArea 	  : Name del TextArea
//		 nameFormulario   : Name del Formulario
//		 nameCapa         : Name de la Capa
//		 nameCapa2        : Name de la 2ª Capa
//     Retorno : false / true
//       Notas : Si el formulario no tiene capas los parámetros nameCapa  y nameCapa2 no serán necesarios. Si solo tiene una capa, no se pasará nameCapa2
//----------------------------------------------------------------------
function controlNumCaracteres(maxNumCaracteres,nameTextArea, nameFormulario, nameCapa, nameCapa2)
{
	var form_div = "";
	if (navigator.appName == 'Netscape' && nameCapa != null && nameCapa2 != null)
	{
		//netscape
		form_div = 'document.layers["' + nameCapa + '"].document.layers["' + nameCapa2 + '"].document.' + nameFormulario;
	}
	else if (navigator.appName == 'Netscape' && nameCapa != null && nameCapa2 == null)
	{
		//netscape
		form_div = 'document.layers["' + nameCapa + '"].document.' + nameFormulario;
	}
	else
	{
		//explorer
		form_div = 'document.' + nameFormulario;
	}
	if (eval(form_div + '.' + nameTextArea + '.value.length') > maxNumCaracteres)
	{
		alert("El máximo número de caracteres permitidos es " + maxNumCaracteres);
		eval (form_div + '.' + nameTextArea + '.focus');
		return false;
	}
	else {
		return true;
	}
}



//----------------------------------------------------------------------
//     Función : validacionFechaSlash
// Descripción : Comprobación básica de que todos los caracteres de la
//               cadena son sólo uno de estos "0123456789/ "
//  Parámetros : Cadena con en número a comprobar
//     Retorno : false / true
//       Notas : -
//----------------------------------------------------------------------
function validacionFechaSlash( cFecha ) {
	// Recorremos los datos introducidos
	for ( nPos = 0; nPos < cFecha.length; nPos++ ) {
		var cCaracter = cFecha.charAt( nPos )
		if ( isNaN( parseInt( cCaracter ) )
		     && cCaracter != "/")
		{
			return false;
		}
	}
	return true;
}



//----------------------------------------------------------------------
//
//     Función : validacionMail()
// Descripción : Comprueba que una determinada cadena tiene formato de
//               dirección de correo electrónico
//  Parámetros : cMail - Cadena a comprobar
//     Retorno : true / false
//     Ejemplo : <input type="text" onBlur="return CheckMail( this.value )">
//
//----------------------------------------------------------------------
function validacionMail( cMail ) {
	// Eliminar los blancos por delante y por detrás
	//cMail = cMail.Trim();
	// Comprobar que se tiene una arroba y esta no está al principio
	var nPos1 = cMail.indexOf("@");
	if ( nPos1 < 1 ){
		return false;
	}

	// Comprobar que se termina con un dominio
	var nPos2 = cMail.lastIndexOf(".");
	if (   nPos2 < nPos1
		|| nPos2 == nPos1+1
		|| (nPos2 + 3 > cMail.length) ) {
		return false;
	}

	// Comprobar que no contiene blancos entre medias
	if ( cMail.indexOf( " " ) != -1 ) {
		return false
	}

	// El formato parece correcto
	return true;
}



/*
//----------------------------------------------------------------------
//
//     Función : validacionDigito
// Descripción : Comprobación básica de que todos los caracteres de la
//               cadena son sólo uno de estos "0123456789"
//  Parámetros : Cadena con en número a comprobar
//     Retorno : false / true
//       Notas : -
//
//----------------------------------------------------------------------
function validacionDigito( cNumero ) {
	// Recorremos los datos introducidos
	for ( nPos = 0; nPos < cNumero.length; nPos++ ) {
		var cCaracter = cNumero.charAt( nPos )
		if ( isNaN( parseInt( cCaracter ) ) ) {
			return false;
		}
	}
	return true;
}
*/
//----------------------------------------------------------------------
//
//     Función : validacionDigito
// Descripción : Comprobación del formato de un numero entero;
//			  se puede emplear el punto como signo de millares
//  Parámetros : Cadena con en número a comprobar
//     Retorno : false / true
//       Notas : -
//
//----------------------------------------------------------------------
function validacionDigito( cNumero ) {
/*
	formatoCorrecto = validacionFormatoNumero(cNumero);
	for ( nPos = 0; nPos < cNumero.length; nPos++ ) {
		var cCaracter = cNumero.charAt( nPos );
		if (isNaN( parseInt( cCaracter ) ) && cCaracter != ".") {
			return false;
		}
	}
	return formatoCorrecto;
*/
	var iDots
	var iCounter;

	iDots = 0;
	iCounter = 0;


	// Quitamos los ceros que tenga delante del número
	cantidadd=""
	controll=false
	for(i=0; i<cNumero.length; i++)
	{
		if (controll==false)
		{
			if(cNumero.substr(i,1)!="0")
			{
				if(cNumero.substr(i,1)=="," || cNumero.substr(i,1)==".")
				{
					cantidadd = "0" + cNumero.substr(i,1);
				}
				else
				{
					cantidadd = cNumero.substr(i,1);
				}
				controll=true;
			}
		}
		else
		{
			cantidadd = cantidadd + cNumero.substr(i,1);
		}
	}
	cNumero=cantidadd


	// Validamos sobre el nuevo contenido
	for (iIndex = 0; iIndex < cNumero.length; iIndex++)
	{
		if (iDots == 1)
		{
			if (iCounter > 3)
			{
				return false;
			}
			iCounter++;
		}
		switch (cNumero.charAt(iIndex)){
			case "0":
				break;
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			case "6":
				break;
			case "7":
				break;
			case "8":
				break;
			case "9":
				break;
			case ".":
			{
				if (iIndex == 0)
				{
					return false;
				}
				if ((iDots == 1) && ((iCounter != 0) && (iCounter != 4)))
				{
					return false;
				}
				else
				{
					iDots = 1;
					iCounter = 0;
				}
				break;
			}
			case " ":
			{
				return false;
			}
			case ",":
			{
				return false;
			}
			default:
			{
				return false;
			}
		}
	}
	if ((iCounter != 0) && (iCounter != 3))
	{
		return false;
	}
	return true;
}



/*
//----------------------------------------------------------------------
//     Función : validacionFormatoNumero
// Descripción : Comprobación básica de que todos los caracteres de la
//               cadena son sólo uno de estos "0123456789 ," además sólo tiene que tener una coma
//  Parámetros : Cadena con el número a comprobar
//     Retorno : false / true
//       Notas : -
//----------------------------------------------------------------------
function validacionFormatoNumero( cNumero ) {
	// Recorremos los datos introducidos
	var numComas = 0;
	for ( nPos = 0; nPos < cNumero.length; nPos++ ) {
		var cCaracter = cNumero.charAt( nPos );
		if ( isNaN( parseInt( cCaracter ) ) && cCaracter != ","  && cCaracter != ".") {
			return false;
		}
		if (cCaracter == "," || cCaracter == ".") {
			numComas ++;
			if (numComas > 1){
				return false;
			}
		}

	}
	return true;
}
*/
//----------------------------------------------------------------------
//     Función : validacionFormatoNumero
// Descripción : Comprobación de que el numero cumpla el formato de numero real (con decimales);
//			  utilizando como signo decimal la coma, y pudiendose utilizar el punto como signo de
//			  millares.
//  Parámetros : Cadena con el número a comprobar
//     Retorno : false / true
//       Notas : Sólo se permitirán dos decimales
//----------------------------------------------------------------------
function validacionFormatoNumero(valorCampo, nombreCampoLiteral)
{
	// Mensajes de posibles errores
	ERRXXX = "Formato incorrecto del parámetro ";
	ERR002 = ". El formato debe ser X.XXX,YY";
	ERR003 = ". Sólo puede introducir números y los caracteres de punto o coma";
	ERR004 = ". Sólo puede introducir una coma";
	ERR005 = ". El punto es el separador de miles";
	ERR006 = ". La coma no puede ser el primer caracter";
	ERR007 = ". No debe introducir espacios en el importe";
	ERR008 = ". El punto es el separador de miles";
	ERR009 = ". El punto no puede ser el primer caracter";
	ERR010 = ". Sólo puede introducir 2 decimales";
	ERR011 = ". El punto es el separador de miles (X.XXX,YY)";


	var iCommas;
	var iDots
	var iCounter;
	var iDec;

	iDec = 0;
	iCommas = 0;
	iDots = 0;
	iCounter = 0;


	// Quitamos los ceros que tenga delante del número
	cantidadd=""
	controll=false
	for(i=0; i<valorCampo.length; i++)
	{
		if (controll==false)
		{
			if(valorCampo.substr(i,1)!="0")
			{
				if(valorCampo.substr(i,1)=="," || valorCampo.substr(i,1)==".")
				{
					cantidadd = "0" + valorCampo.substr(i,1);
				}
				else
				{
					cantidadd = valorCampo.substr(i,1);
				}
				controll=true;
			}
		}
		else
		{
			cantidadd = cantidadd + valorCampo.substr(i,1);
		}
	}
	valorCampo=cantidadd


	// Componemos la parte inicial del mensaje de error por si fuese necesario
	msgError = ERRXXX + nombreCampoLiteral;


	// Validamos sobre el nuevo contenido
	for (iIndex = 0; iIndex < valorCampo.length; iIndex++)
	{
		if (iCommas > 0)
			iDec = iDec + 1;
		if (iDots == 1)
		{
			if (iCounter > 3)
			{
				alert (msgError + ERR011);
				return false;
			}
			iCounter++;
		}
		if (iDec > 2)
		{
			alert (msgError + ERR010);
			return false;
		}
		switch (valorCampo.charAt(iIndex)){
			case "0":
				break;
			case "1":
				break;
			case "2":
				break;
			case "3":
				break;
			case "4":
				break;
			case "5":
				break;
			case "6":
				break;
			case "7":
				break;
			case "8":
				break;
			case "9":
				break;
			case ".":
			{
				if (iIndex == 0)
				{
					alert(msgError + ERR009);
					return false;
				}
				if ((iDots == 1) && ((iCounter != 0) && (iCounter != 4)) || (iCommas > 0))
				{
					alert(msgError + ERR008);
					return false;
				}
				else
				{
					iDots = 1;
					iCounter = 0;
				}
				break;
			}
			case " ":
			{
				alert(msgError + ERR007);
				return false;
			}
			case ",":
			{
				if (iIndex == 0)
				{
					alert(msgError + ERR006);
					return false;
				}
				if ((iDots == 1) && ((iCounter != 0) && (iCounter != 4)))
				{
					alert(msgError + ERR005);
					return false;
				};
				iDots = 0;
				iCounter = 0;
				iCommas = iCommas + 1;
				if (iCommas > 1)
				{
					alert(msgError + ERR004);
					return false;
				}
				break;
			}
			default:
			{
				alert(msgError + ERR003);
				return false;
			}
		}
	}
	if ((iCounter != 0) && (iCounter != 3))
	{
		alert(msgError + ERR002);
		return false;
	}
	return true;
}




//----------------------------------------------------------------------
//
//     Función : validacionFecha
// Descripción : Realiza la validación de un dato tipo fecha
//  Parámetros : Cadena con la fecha a trasformar
//     Retorno : true / false
//       Notas : - Admite los formatos de entrada siguientes:
//                     d/m/yy
//                     dd/mm/yy
//                     dd/mm/yyyy
//               - En el caso de incluir solo dos dígitos para el año,
//                 se interpreta como fecha del año 2000 los inferiores
//                 o igual a 30.
//
//----------------------------------------------------------------------
function validacionFecha( cFecha ) {


	// Comprobación de tamaño
	if ( cFecha.length < 6 || cFecha.length > 10 ) {
		return false;
	}

	if (cFecha.length == 6)
	{
		if (!validacionNumerico(cFecha.substring(0,1)))
		{
			return false;
		}
		if (!validacionNumerico(cFecha.substring(2,3)))
		{
			return false;
		}
		if (!validacionNumerico(cFecha.substring(4)))
		{
			return false;
		}
	}

	if ((cFecha.length == 10))
	{
		if (!validacionNumerico(cFecha.substring(0,2)))
		{
			return false;
		}
		if (!validacionNumerico(cFecha.substring(3,5)))
		{
			return false;
		}
		if (!validacionNumerico(cFecha.substring(6)))
		{
			return false;
		}
	}
	// Buscar primer separador de la fecha
	var nSeparador1 = cFecha.indexOf( "/", 0 )

	if ( nSeparador1 < 1 || nSeparador1 > 2 ) {
		return false;
	}

	// Obtener el día
	var cDia = cFecha.substring(0, nSeparador1)


	// Buscar el segundo separador de la fecha
	var nSeparador2 = cFecha.indexOf( "/", nSeparador1+1 )

	if ( nSeparador2 < nSeparador1+2 || nSeparador2 > nSeparador1+3 ) {
		return false;
	}

	// Obtener el mes
	var cMes = cFecha.substring(nSeparador1+1, nSeparador2)


	// Obtener el año
	var cYear = cFecha.substring(nSeparador2+1, cFecha.length)


	// Normalización del año
	if ( cYear.length == 1 || cYear.length == 3 ) {
		return false;
	}
	//¡¡¡¡--Cuidado con la funcionalidad de esto para nacimientos anteriores a 1929.---!!!!
	if ( cYear.length == 2 ) {
		if ( parseInt( cYear ) > 29 ) {
			cYear = "19" + cYear
		} else {
			cYear = "20" + cYear
		}
	}

	// Comprobación del mes
	if ( cMes < 1 || cMes >12 ) {
		return false;
	}

	// Comprobación básica del día
	if ( cDia < 1 || cDia >31) {
		return false;
	}

	// Comprobación del día en los meses con 30 días
	if ( (cMes==4 || cMes==6 || cMes==9 || cMes==11) && (cDia == 31 ) ) {
		return false;
	}

	// Comprobación del mes de febrero teniendo en cuenta los bisiestos
	if ( cMes==2 ){
		if ( cDia > 29 ) {
			return "";
		}
		if ( ( cYear / 4 == parseInt( cYear / 4 ) )
		     && ! ( ( cYear / 100 == parseInt( cYear / 100 ) )
                         && ( cYear / 400 != parseInt( cYear / 400 ) ) ) ) {
			var bBisiesto = true;
		} else {
			var bBisiesto = false;
		}
		if ( ! bBisiesto && cDia==29 ) {
			return false;
		}
	}

	// Retorno Ok
	return true;

}


function formatoDDMMYYYY( cFecha )
{
	// Buscar primer separador de la fecha
	var nSeparador1 = cFecha.indexOf( "/", 0 );
	// Obtener el día
	var cDia = cFecha.substring(0, nSeparador1);
	if(cDia.length==1)
	{
		cDia = "0" + cDia;
	}
	// Buscar el segundo separador de la fecha
	var nSeparador2 = cFecha.indexOf( "/", nSeparador1+1 );
	// Obtener el mes
	var cMes = cFecha.substring(nSeparador1+1, nSeparador2);
	if(cMes.length==1)
	{
		cMes = "0" + cMes;
	}
	// Obtener el año
	var cYear = cFecha.substring(nSeparador2+1, cFecha.length)
	if(cYear.length==2)
	{
		cYear = "20" + cYear;
	}
	var sFechaValida = cDia + "/" + cMes + "/" + cYear;
	return sFechaValida;
}



//----------------------------------------------------------------------
//
//     Función : CheckModificar
// Descripción : Comprobación básica de que todos los caracteres de la
//               cadena son validos.
//  Parámetros : Cadena con en texto a comprobar
//     Retorno : false / true
//       Notas : -
//
//----------------------------------------------------------------------
function CheckModificar( cTexto )
{
	// Recorremos los datos introducidos
	for ( nPos = 0; nPos < cTexto.length; nPos++ )
	{
		var cCaracter = cTexto.charAt( nPos )

		if (
				cCaracter == "-"
		     || cCaracter == "+"
		     || cCaracter == '#'
		     || cCaracter == '&'
		     || cCaracter == '%'
		     || cCaracter == '"'
		     || cCaracter == "'"
		     || cCaracter == "."
		     || cCaracter == ","
		     || cCaracter == "("
		     || cCaracter == ")"
			 || cCaracter == 'ç'
			 || cCaracter == 'Ç' )
		{
			return false;
		}
	}

	return true;
}



//----------------------------------------------------------------------
//      Método : Trim
// Descripción : Añade un método Trim al objeto String de JavaScript
//               para eliminar blancos por delante y por detrás de una
//               cadena
//  Parámetros : -
//     Retorno : Cadena sin espacios por delante y por detras del texto
//       Notas : -
//----------------------------------------------------------------------

function String_trim(Cadena)
{
	// Recorremos los datos introducidos
	var lon;
	var p1;
	var p2;
	var c;
	var buffer;

	lon=Cadena.length;
	p1=0;
	c=Cadena.charAt(p1);

	while (p1<=lon-1 && c==" ")
	{
		p1++;
		c=Cadena.charAt(p1);
	}

	p2=lon;
	c=Cadena.charAt(p2-1);

	while (p2>=0 && c==" ")
	{
		p2--;
		c=Cadena.charAt(p2-1);
	}

	if (p1>=p2)
		buffer="";
	  else
	    buffer=Cadena.substr(p1,p2-p1);

	return buffer;
}



//----------------------------------------------------------------------
//     Función : validaCheckPulsado
// Descripción : Valida si de los checkbox de un formulario hay alguno
//					pulsado antes de enviar el formulario
//  Parámetros : El nombre del formulario
//     Retorno : true / false
//       Notas : Solo se utilizará si es obligatorio que se seleccione
//				 algún ckeckbox
//----------------------------------------------------------------------
function validaCheckPulsado(cNombreFormulario)
{

	var iNumElem=0;

	for (i=0;i<eval("document." + cNombreFormulario + ".length");i++)
	{
			if(eval("document." + cNombreFormulario + "[i].type=='checkbox'") && eval("document." + cNombreFormulario + "[i].checked==true"))
			{
				iNumElem++;
			}
	}
	if (iNumElem!=0)
	{
		return true;
	}
	else
	{
		return false;
	}
}

//----------------------------------------------------------------------
//     Función : existeCheckMarcado
// Descripción : Valida si el checkbox del formulario tiene algun elemento seleccionado
//
//  Parámetros : El nombre del formulario, nombre del checkbox
//     Retorno : true / false
//       Notas : Solo se utilizará si es obligatorio que se seleccione
//				 algún elemento del ckeckbox
//----------------------------------------------------------------------
function existeCheckMarcado(nameForm, nameCheckbox){
   var chequeado = false;
   var checkbox = eval("document." + nameForm + "." + nameCheckbox);
   if(typeof(checkbox) != 'undefined'){
      if (typeof(checkbox.length) == 'undefined'){
         chequeado = (checkbox.checked);
      }
      else {
      	  for (var i=0; i<checkbox.length && !chequeado; i++){
             chequeado = (checkbox[i].checked);
         }
      }
   }
   return chequeado;
}




//----------------------------------------------------------------------
//     Función : validaOptionPulsado
// Descripción : Valida si de los option de un formulario hay alguno
//					pulsado antes de enviar el formulario
//  Parámetros : El nombre del formulario
//     Retorno : true / false
//       Notas : Solo se utilizará si es obligatorio que se seleccione
//				 algún radioButton
//----------------------------------------------------------------------
function validaOptionPulsado(cNombreFormulario)
{

	var iNumElem=0;
	for (i=0;i<eval("document." + cNombreFormulario + ".length");i++)
	{
			if(eval("document." + cNombreFormulario + "[i].type=='radio'") && eval("document." + cNombreFormulario + "[i].checked==true"))
			{
				iNumElem++;
			}
	}

	if (iNumElem!=0)
	{
		return true;
	}
	else
	{
		return false;
	}
}



//----------------------------------------------------------------------
//     Función : validaOptionConcretoPulsado
// Descripción : Valida si alguna de las opciones de un option está pulsada
//  Parámetros : El nombre del formulario, el nombre del radioButton
//     Retorno : true / false
//       Notas : Solo se utilizará si es obligatorio que se seleccione
//				 algún radioButton
//----------------------------------------------------------------------
function validaOptionConcretoPulsado(cNombreFormulario, cNombreOption)
{
     if(typeof(eval("document." + cNombreFormulario + "." + cNombreOption + ".length")) == "undefined"){
       return (eval("document." + cNombreFormulario + "." + cNombreOption + ".checked==true"));
     }
     else{
 	val = (eval("document." + cNombreFormulario + "." + cNombreOption + ".length"));
 	for (i=0; i<val; i++){
 		 if(eval("document." + cNombreFormulario + "." + cNombreOption + "[i].checked==true"))
  		 return true;
          }
 	}
 return false;

}




//----------------------------------------------------------------------
//     Función : validaCampoObligatorio
// Descripción : Valida si en un campo obligatorio de un formulario
//				 se ha introducido algún valor y no son blancos.
//  Parámetros : El nombre del formulario y el nombre del campo (String)
//     Retorno : true / false
//       Notas : Solo se utilizará si es obligatorio que se rellene
//				 el campo texto
//----------------------------------------------------------------------
function validaCampoObligatorio(cNombreFormulario,cTexto)
{
	var texto=String_trim(eval("document." + cNombreFormulario + "." + cTexto + ".value"));

	if(texto!=''){
		return true;
	}
	else {
		return false;
	}
}



//----------------------------------------------------------------------
//     Función : validaDNI
//
// Descripción : Valida un campo DNI de un formulario, puede devolver true /false
//				 si deseamos chequear que el DNI introducido es válido o no. O de-
//				 volver el DNI después de chequear que la letra es válida o ponersela
//				 si el usuario no la introdujo.
//
//  Parámetros : La cadena formada por el DNI (String) y true si deseamos validar solo
//				 la validez del DNI en el formulario(devolverá true / false) o false si
//				 queremos chequear su validez y caso de no ser válido ponerle nosotros
//				 una letra válida y devolver el DNI con su letra correcta.
//
//     Retorno : true / false(si la función recibe como parámetro true) o (String)DNI
//					(si la función recibe como parámetro false)
//       Notas :
//----------------------------------------------------------------------
function validaDNI(DNI,retornoDNI)
{
	var validoDNI;
	var ultimoCaracter;
	ultimoCaracter=DNI.charAt(DNI.length - 1);

	if(retornoDNI)
	{
		if(isNaN(parseInt(ultimoCaracter)))
		{
			if(validaLetraDNI(DNI,true))
			{
				return DNI;
			}
			else
			{
				DNI=DNI.substring(0, DNI.length-1);
				validoDNI=validaLetraDNI(DNI,false);
				return validoDNI;
			}
		}
		else
		{
			validoDNI=validaLetraDNI(DNI,false);
			return validoDNI;
		}
	}
	else
	{
		if(isNaN(parseInt(ultimoCaracter)))
		{
			if(validaLetraDNI(DNI,true))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
}



//----------------------------------------------------------------------
//     Función : validaLetraDNI
// Descripción : Valida si la letra del DNI introducida por el usuario
//				 es correcta
//  Parámetros : La cadena formada por el DNI (String)
//     Retorno : true / false
//       Notas :
//----------------------------------------------------------------------
function validaLetraDNI(DNI,reciboLetra)
{
	var letraObtenida="";
	var secLetras="TRWAGMYFPDXBNJZSQVHLCKET";
	var numeroDNI=0;
	var resultado=0;
	var letra="";
	if (reciboLetra)
	{
		//Obtiene la letra del DNI introducido por el usuario.
		letraObtenida=DNI.charAt(DNI.length-1);

		//Obtiene el número de DNI introducido por el usuario
		numeroDNI=DNI.substring(0, DNI.length-1);

		//Aplica el algoritmo para obtener la letra real corrspondiente a ese número de DNI.
		resultado=parseInt(numeroDNI)%23+1;

		//Recoge la letra de la secuencia de letras válidas
		letra=secLetras.charAt( resultado-1 )

		//Compara letra introducida por el usuario y letra obtenida del algoritmo.
		if(letraObtenida.toUpperCase()==letra)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	else
	{

		resultado=parseInt(DNI)%23+1;
		//Recoge la letra de la secuencia de letras válidas
		letra=secLetras.charAt( resultado-1 );
		return DNI + letra;
	}
}



//----------------------------------------------------------------------
//     Función : validaSelectObligatorio
// Descripción : Valida si en un Select del formulario, se ha cambiado su
//				 valor, o por el contrario se ha dejado el valor que tiene
//				 por defecto.
//  Parámetros : El nombre del formulario y el nombre del Select (String)
//     Retorno : true / false
//       Notas : Solo se utilizará si es obligatorio que se le asigne un
//				 valor al campo Select.
//----------------------------------------------------------------------
function validaSelectObligatorio(cNombreFormulario,cNombre)
{
	return( eval("document." + cNombreFormulario + "." + cNombre + ".options[document." + cNombreFormulario + "." + cNombre + ".selectedIndex].defaultSelected"));
}



//----------------------------------------------------------------------
//     Función : maximoCombo
// Descripción : Indica con true si el combo tiene más entradas que el máximo permitido
//  Parámetros : max = Número máximo de entradas
//		 combo = Lista múltiple
//	 Resultado : true (si se supera el maximo) / false (si no se supera el maximo)
//----------------------------------------------------------------------
function maximoCombo(cFormulario,cCombo, max){
	var campo = eval("document."+cFormulario+"."+cCombo);
	if(campo.length > max) return true;
	else  return false;
}



//----------------------------------------------------------------------
//     Función : minimoCombo
// Descripción : Indica con true si el combo tiene menos entradas que el minimo permitido
//  Parámetros : min = Número minimo de entradas
//		 combo = Lista múltiple
//	 Resultado : true (si es inferior al minimo) / false (si no es inferior al minimo)
//----------------------------------------------------------------------
function minimoCombo(cFormulario,cCombo, min){
	var campo = eval("document."+cFormulario+"."+cCombo);
	if(campo.length < (min))
		return true;
	else
		return false;
}



//--------------------------------------------------------------------------------------
// Funcion:		habilitarCampos
// Autor:		Rafael Rodriguez  (26/11/2002)
// Descripcion:	Habilita/Deshabilita los campos indicados de un formulario
// Parametros:	El nombre del formulario (sin la palabra 'document')
//				Un Array de nombres de campos
//				true (para habilitar) / false  (para deshabilitar)
// Retorno:		No devuelve nada
//--------------------------------------------------------------------------------------
function habilitarCampos(formulario,listaCampos, accionHabilitar){
	for (var i=0; i<listaCampos.length; i++){
		if (accionHabilitar){
			eval("document."+formulario+"."+listaCampos[i]+".onfocus = document."+formulario+"."+listaCampos[i]+".onfocus = null");
			eval("document."+formulario+"."+listaCampos[i]+".disabled = false");
			if (typeof(eval("document."+formulario+"."+listaCampos[i]+".style")) != 'undefined')
				eval("document."+formulario+"."+listaCampos[i]+".style.background = 'white'");
		}else{
			eval("document."+formulario+"."+listaCampos[i]+".onfocus = document."+formulario+"."+listaCampos[i]+".blur");
			eval("document."+formulario+"."+listaCampos[i]+".disabled = true");
			//eval("document."+formulario+"."+listaCampos[i]+".style.background = '#eeeeee'");
			if (typeof(eval("document."+formulario+"."+listaCampos[i]+".style")) != 'undefined')
				eval("document."+formulario+"."+listaCampos[i]+".style.background = '#eeeeee'");
		}
	}
} // fin habilitarCampos



//--------------------------------------------------------------------------------------
// Función     : campoHabilitado
// Autor       : Rafael Rodriguez (26/11/2002)
// Descripción : Comprueba si el campo que se le pasa esta o no habilitado
//  Parámetros : El nombre del formulario y el nombre del campo (select/input)
//     Retorno : true / false
//--------------------------------------------------------------------------------------
function campoHabilitado(formulario, campo){
	var habilitadoNS = eval("document."+formulario+"."+campo+".onfocus");
	var habilitadoIE = eval("document."+formulario+"."+campo+".disabled");

	if ((habilitadoNS == null) || (habilitadoIE == false))
		return true;
	else
		return false;
}



//--------------------------------------------------------------------------------------
// Función     : comprobarLongitudCampo
// Autor       : Rafael Rodriguez (02/01/2003)
// Descripción : Comprueba el tamaño del contenido de un campo del formulario
//  Parámetros : El nombre del formulario y el nombre del campo
//				 El tamaño maximo de caracteres
//				 El mensaje de alerta, en caso de supere el tamaño maximo de caracteres
//     Retorno : true / false
//--------------------------------------------------------------------------------------
function comprobarLongitudCampo(formulario,campo, topeChar, mensaje){
	var texto = eval("document."+formulario+"."+campo+".value");

	if (texto.length > parseInt(topeChar)){
		var nuevoTexto = texto.substring(0,parseInt(topeChar));
		var micampo = eval("document."+formulario+"."+campo);
		micampo.value = nuevoTexto;
		alert(mensaje);
		return false;
	}
	else
		return true;
}



//--------------------------------------------------------------------------------------
// Autor:		Rafael Rodriguez  (25/11/2002)
// Descripcion:	Valida, uno por uno, todos los campos de un formulario
// Parametros:	El nombre del formulario (sin la palabra 'document')
//				Un array de arrays. Cada array ha de contener las siguientes columnas de texto:
//					- Tipo de campo: "TEXT" (para campos de texto), "SELECT" (para combos)
//					- Nombre del campo
//					- Tipo de validacion (una de estas constantes):
//						"OB" (obligatorio),	  "FL" (numero flotante), "EN" (numero entero)
//						"AN" (alfanumerico),  "FE" (fecha),		 	  "DNI" (numero DNI)
//						"LE" (letra del DNI), "MA" (e-mail)
//						"MX,numero" (*), "MI,numero" (*)			  "TL"  (telefono)
//					- Mensaje que define el error del campo, en caso de que se produzca.
//
// Retorno:		true / false
// Notas:		Muestra un mensaje 'alert' para el primer campo erroneo que encuentre.
//				(*) "MX,numero" comprueba que el combos no contenga mas elementos que
//					un numero maximo (numero).
//					"MI,numero" comprueba que el combos tiene un minimo de elementos.
//--------------------------------------------------------------------------------------
function validarFormulario(formulario,camposValidar){
	var vectorCampo;		// Registro con los datos del campo
	var campoSelect;		// Valor que devuelve el select (true/false)
	var campo;				// valor que devuelve un campo de texto
	var correcto;			// Valor (true/false) de la validacion de un campo
	var comprobarCampo;		// 'true' si hay que comprobar campo obligatoriamente
	var valorNumerico;		// Array con los campos [ETIQUETA,numero]

	for (var i=0; i<camposValidar.length; i++){
		vectorCampo = camposValidar[i];
		comprobarCampo = true;
		// Obtener el valor en funcion del tipo de campo
		if (vectorCampo[0] == "SELECT"){
			// En caso de ser un [MX,max] o [MI,min] hay que obtener la etiqueta MX (MI) y el maximo (minimo) valor
			valorNumerico = vectorCampo[2].split(",");
			if (valorNumerico[0] == "MX")
				correcto = !maximoCombo(formulario,vectorCampo[1],valorNumerico[1]);
			else if (valorNumerico[0] == "MI")
				correcto = !minimoCombo(formulario,vectorCampo[1],valorNumerico[1]);
			else {
				//correcto = eval("document." + formulario +"."+ vectorCampo[1] + ".selectedIndex > 0");
				campo = "document." + formulario +"."+ vectorCampo[1];
				correcto = eval(campo + "[" + campo + ".selectedIndex].value != ''");
			}
		}
		else if (vectorCampo[0] == "TEXT"){

			campo=eval("document."+formulario + "." + vectorCampo[1] + ".value");
			if (Trim(campo) == '') correcto = false;
			else correcto = true;
		}

		// VALIDAR CAMPOS OBLIGATORIOS
		if (vectorCampo[2] == "OB"){
			if (!correcto){
				alert(vectorCampo[3]);
				eval("document."+formulario+"."+vectorCampo[1]+".focus()");
				return false;
			}
		}
		else {
			if ((campo == '') || (!campoHabilitado(formulario,vectorCampo[1])))
				comprobarCampo = false;
		}

		// VALIDAR CAMPOS DE TIPO FLOTANTE
		if ((vectorCampo[2] == "FL") && (comprobarCampo))
			correcto = validacionFormatoNumero(campo, vectorCampo[3]);

		// VALIDAR CAMPOS DE TIPO ENTERO
		if ((vectorCampo[2] == "EN") && (comprobarCampo))
			correcto = validacionDigito(campo);

		// VALIDAR CAMPOS ALFANUMERICOS
		if ((vectorCampo[2] == "AN") && (comprobarCampo))
			correcto = validacionAlfaNum(campo);

		// VALIDAR CAMPOS DE TIPO E-MAIL
		if ((vectorCampo[2] == "MA") && (comprobarCampo))
			correcto = validacionMail(campo);
		// VALIDAR CAMPOS DE TIPO FECHA
		if ((vectorCampo[2] == "FE") && (comprobarCampo))
			correcto = validacionFecha(campo);

		// VALIDAR CAMPOS DE TIPO DNI
		if ((vectorCampo[2] == "DNI") && (comprobarCampo))
			correcto = validaDNI(campo);

		// VALIDAR LA LETRA DEL DNI
		if ((vectorCampo[2] == "LE") && (comprobarCampo))
			correcto = validaLetraDNI(campo);

		// VALIDAR CAMPOS DE TIPO TELEFONO
		if ((vectorCampo[2] == "TL") && (comprobarCampo))
			correcto = validacionTelefono(campo);

		//Mostrar mensaje de alerta si se ha encuentrado un campo incorrecto
		if ((!correcto) && (comprobarCampo)){
			if(vectorCampo[2] != "FL"){
				alert(vectorCampo[3]);
			}
			eval("document."+formulario+"."+vectorCampo[1]+".focus()");
			return false;
		}
	} //for
	return true;
}



//--------------------------------------------------------------------------------------
// Funcion:		validarFormularioSin
// Descripcion:	identico a la fucion validarFormulario pero sin hacer focus()
//--------------------------------------------------------------------------------------
function validarFormularioSin(formulario,camposValidar){

	var vectorCampo;		// Registro con los datos del campo
	var campoSelect;		// Valor que devuelve el select (true/false)
	var campo;				// valor que devuelve un campo de texto
	var correcto;			// Valor (true/false) de la validacion de un campo
	var comprobarCampo;		// 'true' si hay que comprobar campo obligatoriamente
	var valorNumerico;		// Array con los campos [ETIQUETA,numero]

	for (var i=0; i<camposValidar.length; i++){
		vectorCampo = camposValidar[i];
		comprobarCampo = true;

		// Obtener el valor en funcion del tipo de campo
		if (vectorCampo[0] == "SELECT"){
			// En caso de ser un [MX,max] o [MI,min] hay que obtener la etiqueta MX (MI) y el maximo (minimo) valor
			valorNumerico = vectorCampo[2].split(",");
			if (valorNumerico[0] == "MX")
				correcto = !maximoCombo(formulario,vectorCampo[1],valorNumerico[1]);
			else if (valorNumerico[0] == "MI"){
				correcto = !minimoCombo(formulario,vectorCampo[1],valorNumerico[1]);
			}
			else
				//correcto = !eval("document." + formulario +"."+ vectorCampo[1] + ".options[document." + formulario +"."+vectorCampo[1] + ".selectedIndex].defaultSelected");
				correcto = eval("document." + formulario +"."+ vectorCampo[1] + ".selectedIndex > 0");
		}

		if (vectorCampo[0] == "TEXT"){
			campo=eval("document."+formulario + "." + vectorCampo[1] + ".value");
			if (campo == '') correcto = false;
			else correcto = true;
		}

		// VALIDAR CAMPOS OBLIGATORIOS
		if (vectorCampo[2] == "OB"){
			if (!correcto){
				alert(vectorCampo[3]);
				return false;
			}
		}else
			if ((campo == '') || (!campoHabilitado(formulario,vectorCampo[1])))
				comprobarCampo = false;

		// VALIDAR CAMPOS DE TIPO FLOTANTE
		if ((vectorCampo[2] == "FL") && (comprobarCampo))
			correcto = validacionFormatoNumero(campo);

		// VALIDAR CAMPOS DE TIPO ENTERO
		if ((vectorCampo[2] == "EN") && (comprobarCampo))
			correcto = validacionDigito(campo);

		// VALIDAR CAMPOS ALFANUMERICOS
		if ((vectorCampo[2] == "AN") && (comprobarCampo))
			correcto = validacionAlfaNum(campo);

		// VALIDAR CAMPOS DE TIPO E-MAIL
		if ((vectorCampo[2] == "MA") && (comprobarCampo))
			correcto = validacionMail(campo);

		// VALIDAR CAMPOS DE TIPO FECHA
		if ((vectorCampo[2] == "FE") && (comprobarCampo))
			correcto = validacionFecha(campo);

		// VALIDAR CAMPOS DE TIPO DNI
		if ((vectorCampo[2] == "DNI") && (comprobarCampo))
			correcto = validaDNI(campo);

		// VALIDAR LA LETRA DEL DNI
		if ((vectorCampo[2] == "LE") && (comprobarCampo))
			correcto = validaLetraDNI(campo);

		// VALIDAR CAMPOS DE TIPO TELEFONO
		if ((vectorCampo[2] == "TL") && (comprobarCampo))
			correcto = validacionTelefono(campo);

		//Mostrar mensaje de alerta si se ha encuentrado un campo incorrecto
		if ((!correcto) && (comprobarCampo)){
			alert(vectorCampo[3]);
			return false;
		}


	} //for

	return true;
}



//--------------------------------------------------------------------------------------
// Descripcion:	Habilita/Deshabilita el formulario
// Parametros:	formulario
//				true (para habilitar) / false  (para deshabilitar)
// Retorno:		No devuelve nada
//--------------------------------------------------------------------------------------
function habilitarFormulario(formulario,accionHabilitar){
	for(i=0;i<formulario.elements.length;i++){
		if (accionHabilitar){
			formulario.elements[i].onfocus = formulario.elements[i].onfocus = null;
			formulario.elements[i].disabled = false;
			if (typeof(eval("formulario.elements[i].style")) != 'undefined')
				eval("formulario.elements[i].style.background = 'white'");
		}
		else{
			formulario.elements[i].onfocus = formulario.elements[i].blur;
			formulario.elements[i].disabled = true;
			if (typeof(eval("formulario.elements[i].style")) != 'undefined')
				eval("formulario.elements[i].style.background = '#eeeeee'");
		}
	}
}



//----------------------------------------------------------------------
// Descripción : Comprobación básica de que todos los caracteres de la
//               cadena son sólo uno de estos "0123456789 -() "
//  Parámetros : Cadena con en número a comprobar
//     Retorno : false / true
//----------------------------------------------------------------------
function validacionTelefono( cCadena ) {
	// Recorremos los datos introducidos
	for ( nPos = 0; nPos < cCadena.length; nPos++ ) {
		var cCaracter = cCadena.charAt( nPos )
		if ( isNaN( parseInt( cCaracter ) )
		     && cCaracter != "-"
		     && cCaracter != "("
		     && cCaracter != ")"
		     && cCaracter != " " ) {
			return false;
		}
	}
	return true;
}

function Trim(TRIM_VALUE){
	if(TRIM_VALUE.length < 1){
		return"";
	}
	TRIM_VALUE = RTrim(TRIM_VALUE);
	TRIM_VALUE = LTrim(TRIM_VALUE);
	if(TRIM_VALUE==""){
		return "";
	}
	else{
		return TRIM_VALUE;
	}
}

function RTrim(VALUE){
	var w_space = String.fromCharCode(32);
	var v_length = VALUE.length;
	var strTemp = "";
	if(v_length < 0){
		return"";
	}
	var iTemp = v_length -1;
	while(iTemp > -1){
		if(VALUE.charAt(iTemp) == w_space){}
		else{
			strTemp = VALUE.substring(0,iTemp +1);
			break;
		}
		iTemp = iTemp-1;
	}
	return strTemp;
}

function LTrim(VALUE){
	var w_space = String.fromCharCode(32);
	if(v_length < 1){
		return"";
	}
	var v_length = VALUE.length;
	var strTemp = "";
	var iTemp = 0;
	while(iTemp < v_length){
		if(VALUE.charAt(iTemp) == w_space){}
		else{
			strTemp = VALUE.substring(iTemp,v_length);
			break;
		}
		iTemp = iTemp + 1;
	}
	return strTemp;
}

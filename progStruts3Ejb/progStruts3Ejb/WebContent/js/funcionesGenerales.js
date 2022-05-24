//----------------------------------------------------------------------
// Descripción : Añade en una lista múltiple una nueva entrada,
//  Parámetros : texto = Texto de la nueva entrada
//		 valor = Valor de la nueva entrada
//		 combo = Lista múltiple
//       campoHidden = Campo en el que se rellenara la lista de codigos.
//		 textoHidden = Campo en el que se rellenara la lista de descripciones.
//----------------------------------------------------------------------
function mas(texto, valor, combo, campoHidden, textoHidden, repetido){
	var contenido = false;
	if (repetido == null || repetido!='1'){
		for (i=0; i<combo.length; i++){
			if (combo.options[i].value == valor){
				contenido = true;
			}
		}
	}
	if (!contenido){
		if (valor != null && valor != ""){
			if (campoHidden != null){
				rellenarHidden (campoHidden, valor);
			}
			if (textoHidden != null){
				rellenarHiddenConComa(textoHidden,texto);
			}
			combo.length=combo.length+1;
			if(texto=="" || texto==null){
				alert("Debe escribir algo antes de pulsar '+'");
				return;
			}else{
				for(i=0;i<combo.length;i++){
					if(combo.options[i].value=="" || combo.options[i].value.length==0){
						combo.options[i].value=valor;
						combo.options[i].text=texto;
						return;
					}
				}
			}
		}
	}
}



//----------------------------------------------------------------------
// Descripción : Concatena un nuevo texto al valor que tiene un campo hidden
//  Parámetros : campoOculto = Campo hidden que contendrá los valores
//		 textoIncorporar = valor del texto que se añade
//----------------------------------------------------------------------
function rellenarHidden(campoOculto,textoIncorporar){
	campoOculto.value+=  textoIncorporar +"|" ;
}



//----------------------------------------------------------------------
// Descripción : Concatena un nuevo texto al valor que tiene un campo hidden
//  Parámetros : campoOculto = Campo hidden que contendrá los valores
//		 textoIncorporar = valor del texto que se añade
//----------------------------------------------------------------------
function rellenarHiddenConComa(textoOculto,textoIncorporar){
	textoOculto.value+=  textoIncorporar +"; " ;
}



//----------------------------------------------------------------------
// Descripción : Elimina de una lista el valor seleccionado y rellena un hidden con los valores que
//			     premanecen en la lista seguidos por |
//  Parámetros : combo = Lista múltiple
//		         campoOculto = Campo hidden que contendrá los valores
//----------------------------------------------------------------------
function menos(combo, campoOculto, textoHidden){
var seleccion = false
	for(i=0;i<combo.length;i++){
		if (combo.options[i].selected == true)
			seleccion = true;
	}
	if (seleccion){
		if(combo.options[combo.selectedIndex].value!="" && combo.options[combo.selectedIndex].value!=null){
			for(i=0;i<combo.length;i++){
				if(combo.selectedIndex>-1){
					combo.options[combo.selectedIndex].value="";
					combo.options[combo.selectedIndex].text=" ";
				}
			}
			for(j=combo.selectedIndex;j<combo.length-1;j++){
				if(combo.options[j]!=combo.length-1){
					combo.options[j].value=combo.options[j+1].value;
					combo.options[j].text=combo.options[j+1].text;
				}
			}
			combo.length=combo.length-1;
			campoOculto.value="";
			if (textoHidden != null){
				textoHidden.value="";
			}
			for(k=0;k<combo.length;k++){
				var valorAlHidden = combo.options[k].value;
				var textoAlHidden = combo.options[k].text;
				if(valorAlHidden!="" && valorAlHidden!=null){
					campoOculto.value += valorAlHidden + "|";
					if (textoHidden != null){
						textoHidden.value += textoAlHidden + ";";
					}
				}
			}
		}
	}
}



//----------------------------------------------------------------------
// Descripción : Elimina de una lista el valor seleccionado y rellena un hidden con los valores que
//			     premanecen en la lista seguidos por |
//  Parámetros : combo = Lista múltiple
//		 campoOculto = Campo hidden que contendrá los valores
//		 valor = Valor que hay que buscar en la combo para eliminar
//----------------------------------------------------------------------
function menosDesdeValor(combo, campoOculto, valor){
	var posicion;
	if(valor!="" && valor!=null){
		for(i=0;i<combo.length;i++){
			if(combo[i].value == valor){
				posicion = i;
				combo[i].value = "";
				combo[i].text = "";
			}
		}
		for(j=posicion;j<combo.length-1;j++){
			if(combo.options[j]!=combo.length-1){
				combo.options[j].value=combo.options[j+1].value;
				combo.options[j].text=combo.options[j+1].text;
			}
		}
		combo.length=combo.length-1;
		campoOculto.value="";
		for(k=0;k<combo.length;k++){
			var valorAlHidden = combo.options[k].value;
			if(valorAlHidden!="" && valorAlHidden!=null){
				campoOculto.value += valorAlHidden + "|";
			}
		}
	}
}



//----------------------------------------------------------------------
// Descripción : Elimina de una lista el valor seleccionado y rellena un hidden con los valores que
//			     premanecen en la lista seguidos por |
//  Parámetros : combo = Lista múltiple
//		         campoOculto = Campo hidden que contendrá los valores
//			 usuario = Indica si es un usuario (1) o una lista (0)
//----------------------------------------------------------------------
function menosDest(combo, campoOculto, usuario){
var seleccion = false
	for(i=0;i<combo.length;i++){
		if (combo.options[i].selected == true)
			seleccion = true;
	}
	if (seleccion){
		if(combo.options[combo.selectedIndex].value!="" && combo.options[combo.selectedIndex].value!=null){
			for(i=0;i<combo.length;i++){
				if(combo.selectedIndex>-1){
					combo.options[combo.selectedIndex].value="";
					combo.options[combo.selectedIndex].text=" ";
				}
			}
			for(j=combo.selectedIndex;j<combo.length-1;j++){
				if(combo.options[j]!=combo.length-1){
					combo.options[j].value=combo.options[j+1].value;
					combo.options[j].text=combo.options[j+1].text;
				}
			}
			combo.length=combo.length-1;
			campoOculto.value="";
			for(k=0;k<combo.length;k++){
				var valorAlHidden = combo.options[k].value;
				var textoAlHidden = combo.options[k].text;
				if(valorAlHidden!="" && valorAlHidden!=null){
					pos = textoAlHidden.indexOf('[');
					if ((pos == 0 && usuario == 0) || (pos != 0 && usuario == 1)){
						campoOculto.value += valorAlHidden + "|";
					}
				}
			}

		}
	}
}



//-------------------------------------------------------------------------------------
// Descripcion: Muestra una pantalla de confirmacion y vuelve a la pagina de inicio
// Parametros  : El mensaje de confirmacion
//-------------------------------------------------------------------------------------
function salirSinGrabar(cTexto){
	if (confirm(cTexto))
		document.location.href="/cfc/loginAction.do";
}



//-------------------------------------------------------------------------------------
// Descripcion : Nos lleva a la URL especificada
// Parametros  : La pagina de destino
//-------------------------------------------------------------------------------------
function irAPagina(pagina){
	document.location=pagina;
}



//-------------------------------------------------------------------------------------
// Descripcion : Mueve uno o varios elementos de un 'select' multiple a otro 'select' multiple
// Parametros  : El nombre del select multiple de origen
//				 El nombre del select multiple de destino
//				 moverTodo: true (mueve todo) / false (mueve solo lo seleccionado)
//-------------------------------------------------------------------------------------
function moverLista( listaInicial, listaDestino, moverTodo ){

	if (  ( listaInicial.selectedIndex == -1 ) && ( moverTodo == false ))
		return;

	newlistaDestino = new Array( listaDestino.options.length );
	var len = 0;
	for( len = 0; len < listaDestino.options.length; len++ ){
		if ( listaDestino.options[ len ] != null ){
			newlistaDestino[ len ] = new Option( listaDestino.options[ len ].text, listaDestino.options[ len ].value, listaDestino.options[ len ].defaultSelected, listaDestino.options[ len ].selected );
		}
	}

	for( var i = 0; i < listaInicial.options.length; i++ ){
		if ( listaInicial.options[i] != null && ( listaInicial.options[i].selected == true || moverTodo ) ){
			newlistaDestino[ len ] = new Option( listaInicial.options[i].text, listaInicial.options[i].value, listaInicial.options[i].defaultSelected, listaInicial.options[i].selected );
			len++;
		}
	}
	for ( var j = 0; j < newlistaDestino.length; j++ ){
		if ( newlistaDestino[ j ] != null ){
			listaDestino.options[ j ] = newlistaDestino[ j ];
		}
	}
	for( var i = listaInicial.options.length - 1; i >= 0; i-- ){
		if ( listaInicial.options[i] != null && ( listaInicial.options[i].selected == true || moverTodo )){
			listaInicial.options[i]       = null;
		}
	}
	return;
}



//-------------------------------------------------------------------------------------
// Descripcion : Genera una cadena con todos los elementos del combos 'listaDestino' separados por '|'
// Parametros  : El nombre del formulario
// Devuelve    : La cadena con los elementos del combos contatenados por '|'
//-------------------------------------------------------------------------------------
function guardarCamposSeleccionados(formulario,combo){
	var temp = "";
	var longitud = eval("document." + formulario+"."+combo+".length");
	for (i = 0; i < longitud; i++)
		temp = temp + eval("document." + formulario+"."+combo+".options[i].value") + "|";

	return temp;
}



//-------------------------------------------------------------------------------------
// Descripcion : Limpia el formulario especificado, con una previa confirmacion
// Parametros  : El nombre del formulario y el mensaje de confirmacion
//-------------------------------------------------------------------------------------
function limpiarFormulario(formulario,mensaje){
	if (confirm(mensaje)){
		eval("document."+formulario+".reset()");
		return true;
	}
	return false;
}



//-------------------------------------------------------------------------------------
// Descripcion : Pone el foco en el campo especificado del formulario
// Parametros  : El nombre del formulario y el campo que recibe el foco
//-------------------------------------------------------------------------------------
function ponerFoco(formulario,campo){
	eval("document." +  formulario + "." + campo + ".focus()");
}

function vaciarCampo(campo){
	campo.value="";
}



//----------------------------------------------------------------------
// Descripción : Anade o reemplaza la lista codigos al valor de la opcion seleccionada
//  Parámetros : combo = Lista múltiple
//		 campoCodigos = Campo hidden que contiene la lista de codigos
//----------------------------------------------------------------------
function anadirCodigos(campoCodigos,combo){
	if(combo.selectedIndex!=-1)	{
		combo.options[combo.selectedIndex].codigosTes=campoCodigos;
	}
}

function seleccionarTodos(checkall,lista){
	if(checkall.checked){
		if (typeof(lista) == "undefined"){
			//No se ha mostrado ningun registro.
		}else{
			if (typeof(lista.length) == "undefined"){
				//Se ha mostrado un registro.
				lista.checked = true;
			}
			else{
				for(var i=0;i<lista.length;i++)
					lista[i].checked = true;
			}
		}
	}
	else{
		if (typeof(lista) == "undefined"){
			//No se ha mostrado ningun registro.
		}
		else{
			if (typeof(lista.length) == "undefined"){
				//Se ha mostrado un registro.
				lista.checked = false;
			}
			else{
				for(var i=0;i<lista.length;i++){
					lista[i].checked = false;
				}
			}
		}
	}
}



//-------------------------------------------------------------------------------------
// Descripcion : Activa/Desactiva la imagen que permite seleccionar una fecha
// Parametros  : El combo que indica el tipo de estado de la actividad y (opcionalmente) la imagen
//-------------------------------------------------------------------------------------
function activarFecha(combo, imagen, campoFecha){
	var estado = combo[combo.selectedIndex].value;
	if (estado == "1") {
		imagen.src="images/calendario2.gif";
       }
	else {
	       imagen.src="images/calendario.gif";
	}
        habilitaCampoDependiente(combo, "1", campoFecha);
}



//-------------------------------------------------------------------------------------
// Descripcion : Permite seleccionar el calendario si el codigo de fecha es distinto de 1 ('Pendiente de Acreditar')
// Parametros  : El combo que indica el tipo de estado de la actividad y el campo donde se guardará la fecha obtenida
//-------------------------------------------------------------------------------------
function activarFecha2(combo, campoFecha){
	var estado = combo[combo.selectedIndex].value;

	if (estado != "1")
		location.href= "javascript:show_calendar('" + campoFecha + "');";
}

function checkNum(quecontrol){
	for (cont=0;cont<quecontrol.value.length;cont++){
		if (!((quecontrol.value.charAt(cont)=='0')||
		(quecontrol.value.charAt(cont)=='1')||
		(quecontrol.value.charAt(cont)=='2')||
		(quecontrol.value.charAt(cont)=='3')||
		(quecontrol.value.charAt(cont)=='4')||
		(quecontrol.value.charAt(cont)=='5')||
		(quecontrol.value.charAt(cont)=='6')||
		(quecontrol.value.charAt(cont)=='7')||
		(quecontrol.value.charAt(cont)=='8')||
		(quecontrol.value.charAt(cont)=='9')))
		quecontrol.value = quecontrol.value.substring(0,cont)
	}
}



//-------------------------------------------------------------------------------------
// Descripcion : Prepara el termino para enviarlo por URL reemplaza ' ' por '*'
//-------------------------------------------------------------------------------------
function sustituir(termino){
	var terminofinal="";
	for(var i=0;i<termino.length;i++){
		if(termino.charAt(i) ==' ')
			terminofinal+="+";
		else
			terminofinal+=termino.charAt(i);
	}
	return terminofinal;
}



function openChildNS(file,window, wi, he){
	var resiz = 'no';
	var caracteristicas = 'resizable=' + resiz + ',width=' + wi + ',height=' + he + ',scrollbars=yes,menubar=no';
	childWindow=open(file,window,caracteristicas);

	if (childWindow.opener == null)
		childWindow.opener = self;
}


function LTrim(str){
	for (var i=0; str.charAt(i)==" "; i++);
  	return str.substring(i,str.length);
}

function RTrim(str) {
	for (var i=str.length-1; str.charAt(i)==" "; i--);
	return str.substring(0,i+1);
}

function Trim(str) {
	return LTrim(RTrim(str));
}



//----------------------------------------------------------------------
// Descripción : Abre una ventana que no pierde el foco-No se puede minimizar
//  Parámetros : ventana = Path de la ventana
//----------------------------------------------------------------------
function abrirPopUp(ventana){
	if (document.all){
		window.showModalDialog(ventana,window,"status:no;dialogWidth:250px;dialogHeight:250px;scroll:no;help:no;");
	}else{
		nv=window.open(ventana,'CFC','width=250,height=250,status=no,scrollbars=no,location=no,resizable=no;');
		nv.focus();
	}
}



//-------------------------------------------------------------------------------------
// Habilita o deshabilita la imagen indicada
// parametros:  formulario = nombre del formulario
//				nombreImg = nombre (sin indicar el numero final) de la imagen
//				numImg = numero de imagenes con ese nombre (aunque con distinta numeracion)
//				habilitar = true/false
function habilitarImagenes(formulario,nombreImg, numImg, habilitar){
	var src, srcActivo, srcDesactivo;

	if (nombreImg == "mas"){
		srcActivo = "images/anadir.gif";
		srcDesactivo = "images/anadir2.gif";
	}
	else if (nombreImg == "menos"){
		srcActivo = "images/eliminar.gif";
		srcDesactivo = "images/eliminar2.gif";
	}
	else if (nombreImg == "lupa"){
		srcActivo = "images/buscar.gif";
		srcDesactivo = "images/buscar2.gif";
	}
	else if (nombreImg == "fecha"){
		srcActivo = "images/calendario.gif";
		srcDesactivo = "images/calendario2.gif";
	}
	else if (nombreImg == "tesau"){
		srcActivo = "images/tesauro.gif";
		srcDesactivo = "images/tesauro2.gif";
	}

	if (habilitar)
		src = srcActivo;
	else
		src = srcDesactivo;

	for (i=1; i<= numImg; i++)
		eval("document."+formulario + "." + nombreImg + i + ".src = '" + src + "';");
}



// Ejecutar una funcion que depende de un parametro
function ejecutarFuncion(miFuncion,ejecutar){
	if (ejecutar)
		eval(miFuncion);
}



//----------------------------------------------------------------------
//     Función : openChild
// Descripción : Abre una ventana
//  Parámetros : file = Página a cargar
//		 windowName = nombre de la ventana
//		 wi = Ancho
//		 he = Altura
//		 res = resizable
//		 scr = 1-> con scroll, 0-> sin scroll
//----------------------------------------------------------------------
function openChild(file, windowName, wi, he, res, scr , barra)
{
	fecha = new Date();
	var scrollBar = 'no';
	var barraMenu = 'no';
	var resiz = 'no';
	var PosX = (screen.availWidth - wi)/2;
	var PosY = (screen.availHeight - he)/2;

	if (res != null && res == 1){
		var resiz = 'yes';
	}
	if (scr != null && scr==1)
		scrollBar = 'yes';
	if (barra == 1)
	barraMenu = 'yes';
	var caracteristicas = 'resizable=' + resiz + ',width=' + wi + ',height=' + he + ',left=' + PosX + ',top=' + PosY + ',scrollbars=' + scrollBar + ',menubar='+barraMenu + ',status=yes';

	if (file.indexOf("?") >= 0){
		file += "&uniqueUrl="+fecha.getTime();
	}
	childWindow = window.open(file, windowName, caracteristicas);
	if (childWindow != null){
		if (childWindow.opener == null){
			 childWindow.opener = self;
		}
	 childWindow.focus();
	}
       else{
	  	alert("Usted tiene bloqueados los elementos emergentes. Estos son necesarios para el correcto funcionamiento de la aplicaci\xF3n.");
	  	alert ("Si su sistema operativo es Windows XP SP2 pulse en 'Herramientas', 'Bloqueador de elementos emergentes', 'Permitir siempre elementos emergentes de este sitio'. Si no apareciese esta \xFAltima opci\xF3n pulsar en 'Configuraci\xF3n ...'. En la ventana que se le abre inserte sipes.msc.es dentro de 'Direcci\xF3n del sitio Web que desea permitir', pulse 'Agregar' y cierre la ventana.");
	}
}

//----------------------------------------------------------------------
//     Función : openChildOculto
// Descripción : Abre una ventana
//  Parámetros : file = Página a cargar
//		 window = nombre de la ventana
//----------------------------------------------------------------------
function openChildOculto(file, windowName)
{
	fecha = new Date();
	var PosX = 5000;
	var PosY = 300;

	var caracteristicas = 'width=1,height=1,left=' + PosX + ',top=' + PosY;
	if (file.indexOf("?") >= 0){
		file += "&uniqueUrl="+fecha.getTime();
	}
	childWindow = window.open(file, windowName, caracteristicas);
	if (childWindow != null){
		if (childWindow.opener == null){
			 childWindow.opener = self;
		}
		childWindow.focus();
	}
       else{
	  	alert("Usted tiene bloqueados los elementos emergentes. Estos son necesarios para el correcto funcionamiento de la aplicaci\xF3n.");
	  	alert ("Si su sistema operativo es Windows XP SP2 pulse en 'Herramientas', 'Bloqueador de elementos emergentes', 'Permitir siempre elementos emergentes de este sitio'. Si no apareciese esta \xFAltima opci\xF3n pulsar en 'Configuraci\xF3n ...'. En la ventana que se le abre inserte sipes.msc.es dentro de 'Direcci\xF3n del sitio Web que desea permitir', pulse 'Agregar' y cierre la ventana.");
	}
}

//--------------------------------------------------------------------------------------
// Funcion:		habilitarCampos
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
			if (typeof(eval("document."+formulario+"."+listaCampos[i]+".style")) != 'undefined')
				eval("document."+formulario+"."+listaCampos[i]+".style.background = '#eeeeee'");
		}
	}
} // fin habilitarCampos





// Habilita o deshabilita el campo 'campoDependiente' dependiendo si el valor de 'combo' seleccionado es 'codigoHabilita'
//NOTA: No está probada
function habilitaCampoDependiente(combo, codigoHabilita, campoDependiente){
	var codigoCombo = combo[combo.selectedIndex].value;
	var formulario = combo.form;

	listaCampos = new Array (campoDependiente.name);
	if (codigoCombo == codigoHabilita){
		habilitarCampos(formulario.name, listaCampos, false);
	}else {
		habilitarCampos(formulario.name, listaCampos, true);
	}
}



// Comprueba que la extensión del fichero sea la deseada
function comprobarExtension(nombreFichero, extension){
	var posAux = 0;
	var posicionPunto = 0;
	var nombre = nombreFichero;

	do{
		posicionPunto = nombre.indexOf(".");
		if (posicionPunto >= 0){
			posAux = posicionPunto+1;
			nombre = nombre.substring(posAux, nombre.length);
		}
	} while(posicionPunto >= 0);

	if ((posAux == 0) || (nombre != extension))
	{
		alert("El fichero debe ser ."+extension);
		return false;
	}
	else
		return true;
}



//----------------------------------------------------------------------
//     Función : replaceSubstring
// Descripción : Recorre el String de entrada y remplaza cada una de las ocurrencias de fromString con toString
//  Parámetros : inputString = String que se quiere parsear
//		 fromString = Substring que se quiere sustituir
//		 toString = Substring por el que se va a sustituir el fromSrting
//----------------------------------------------------------------------
function replaceSubstring(inputString, fromString, toString) {
   var temp = inputString;
   if (fromString == "") {
      return inputString;
   }
   if (toString.indexOf(fromString) == -1) { // If the string being replaced is not a part of the replacement string (normal situation)
      while (temp.indexOf(fromString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(fromString));
         var toTheRight = temp.substring(temp.indexOf(fromString)+fromString.length, temp.length);
         temp = toTheLeft + toString + toTheRight;
      }
   }
   else { // String being replaced is part of replacement string (like "+" being replaced with "++") - prevent an infinite loop
      var midStrings = new Array("~", "`", "_", "^", "#");
      var midStringLen = 1;
      var midString = "";
      // Find a string that doesn't exist in the inputString to be used
      // as an "inbetween" string
      while (midString == "") {
         for (var i=0; i < midStrings.length; i++) {
            var tempMidString = "";
            for (var j=0; j < midStringLen; j++) {
            	tempMidString += midStrings[i];
            }
            if (fromString.indexOf(tempMidString) == -1) {
               midString = tempMidString;
               i = midStrings.length + 1;
            }
         }
      } // Keep on going until we build an "inbetween" string that doesn't exist
      // Now go through and do two replaces - first, replace the "fromString" with the "inbetween" string
      while (temp.indexOf(fromString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(fromString));
         var toTheRight = temp.substring(temp.indexOf(fromString)+fromString.length, temp.length);
         temp = toTheLeft + midString + toTheRight;
      }
      // Next, replace the "inbetween" string with the "toString"
      while (temp.indexOf(midString) != -1) {
         var toTheLeft = temp.substring(0, temp.indexOf(midString));
         var toTheRight = temp.substring(temp.indexOf(midString)+midString.length, temp.length);
         temp = toTheLeft + toString + toTheRight;
      }
   } // Ends the check to see if the string being replaced is part of the replacement string or not
   return temp; // Send the updated string back to the user
} // Ends the "replaceSubstring" function




//----------------------------------------------------------------------
//     Función : pasarFocoCampoSiguiente
// Descripción : Comprueba si se ha alcanzado la longitud 'longMax' en 'campo' del formulario,
//               y en tal caso pasa el foco al campo con nombre 'nombreCampoSiguiente'
//  Parámetros : campo = Campo al que se quiere comprobar su longitud
//		 longMax = Longitud máxima que puede alcanzar el contenido de 'campo'
//		 nameCampoSiguiente = Nombre del campo al que se pasaría el foco si se alcanza la
//		    longitud máxima en 'campo'
//----------------------------------------------------------------------
function pasarFocoCampoSiguiente (campo, longMax, nameCampoSiguiente){
   var valorCampo = campo.value;
   if (valorCampo.length == longMax) {
      var formulario = campo.form.name;
      var campoSiguiente = eval("document."+formulario+"."+nameCampoSiguiente);
      campoSiguiente.focus();
   }
}


//----------------------------------------------------------------------
//     Función : compararFechas
// Descripción : Compara las dos fechas introducidas y devuelve cual de ellas es
//		 menor; los valores devueltos serán:
//		 0  -->  Las dos fechas son iguales
//		 1  -->  La primera fecha es menor a la segunda
//		 2  -->  La segunda fecha es menor a la primera
//  Parámetros : fecha1 = Primera fecha a comparar
//		 fecha2 = Segunda fecha a comparar
//----------------------------------------------------------------------
function compararFechas(fecha1, fecha2){
   String1 = fecha1;
   String2 = fecha2;

   // Si los dias y los meses llegan con un valor menor que 10
   // Se concatena un 0 a cada valor dentro del string
   if (String1.substring(1,2)=="/") {
      String1="0"+String1
   }
   if (String1.substring(4,5)=="/"){
      String1=String1.substring(0,3)+"0"+String1.substring(3,9)
   }

   if (String2.substring(1,2)=="/") {
      String2="0"+String2
   }
   if (String2.substring(4,5)=="/"){
      String2=String2.substring(0,3)+"0"+String2.substring(3,9)
   }

   dia1=String1.substring(0,2);
   mes1=String1.substring(3,5);
   anyo1=String1.substring(6,10);
   dia2=String2.substring(0,2);
   mes2=String2.substring(3,5);
   anyo2=String2.substring(6,10);


   if (dia1 == "08") // parseInt("08") == 10 base octogonal
      dia1 = "8";
   if (dia1 == '09') // parseInt("09") == 11 base octogonal
      dia1 = "9";
   if (mes1 == "08") // parseInt("08") == 10 base octogonal
      mes1 = "8";
   if (mes1 == "09") // parseInt("09") == 11 base octogonal
      mes1 = "9";
   if (dia2 == "08") // parseInt("08") == 10 base octogonal
      dia2 = "8";
   if (dia2 == '09') // parseInt("09") == 11 base octogonal
      dia2 = "9";
   if (mes2 == "08") // parseInt("08") == 10 base octogonal
      mes2 = "8";
   if (mes2 == "09") // parseInt("09") == 11 base octogonal
      mes2 = "9";

   dia1=parseInt(dia1);
   dia2=parseInt(dia2);
   mes1=parseInt(mes1);
   mes2=parseInt(mes2);
   anyo1=parseInt(anyo1);
   anyo2=parseInt(anyo2);

   if (anyo1>anyo2){
      return 2;
   }
   else if ((anyo1==anyo2) && (mes1>mes2)){
      return 2;
   }
   else if ((anyo1==anyo2) && (mes1==mes2) && (dia1>dia2)){
      return 2;
   }
   else if ((anyo1==anyo2) && (mes1==mes2) && (dia1==dia2)){
      return 0;
   }
   else{
      return 1;
   }
}
/*
function compararFechas(fecha1, fecha2){
   var auxfec1=Date.parse(fecha1);
   var auxfec2=Date.parse(fecha2);

   if (auxfec1<auxfec2){
      return 1;
   }
   else if (auxfec1==auxfec2){
      return 0;
   }
   else if (auxfec1>auxfec2){
      return 2;
   }
}
*/

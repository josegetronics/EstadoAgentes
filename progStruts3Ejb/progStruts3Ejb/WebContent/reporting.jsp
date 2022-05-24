<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=windows-1252" %>

<script type="text/javascript" src ="<%=request.getContextPath()%>/js/CalendarPopup.js"></script>
<script language="javascript">document.write(getCalendarStyles());</script>
<script type="text/javascript" src ="<%=request.getContextPath()%>/js/funcionesGenerales.js"></script>
<script type="text/javascript" src ="<%=request.getContextPath()%>/js/validacion.js"></script>

<script type="text/javascript">
                  var calendario = new CalendarPopup("overDiv");
                  calendario.setMonthNames('Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre');
                  calendario.setDayHeaders('D','L','M','X','J','V','S');
                  calendario.setWeekStartDay(1);
                  calendario.setTodayText("Hoy");
                  calendario.showYearNavigation();
                  calendario.showYearNavigationInput();
                  calendario.offsetX = 0;
</script>



<html:html>


<script>
function comprobarFechas(){
  if(document.reportingForm.initialDate.value.length != 10){
  	alert("La Fecha Inicial no puede ser menor de 10 caracteres.");
  }
  else{
    if(!validacionFecha(document.reportingForm.initialDate.value)){
        alert("La Fecha Inicial no es correcta.");
    }
    else{
        if(document.reportingForm.finalDate.value.length != 10){
  	    alert("La Fecha Final no puede ser menor de 10 caracteres.");
        }
        else{
            if(!validacionFecha(document.reportingForm.finalDate.value)){
                alert("La Fecha Final no es correcta.");
            }
            else{
              if(!document.reportingForm.reportIdServicio[0].checked && !document.reportingForm.reportIdServicio[1].checked){
                alert("Debe seleccionar una aplicacion.");
              }
              else{
                document.forms["reportingForm"].submit();
              }
            }
        }
    }
  }
}
</script>


<body>

<table border="0" align="center" width="500">

   <tr>
     <td height="50">&nbsp;
     </td>
   </tr>

   <tr>
    <td colspan="2" align="center">REPORTING</td>
   </tr>

   <tr>
     <td height="20">&nbsp;
     </td>
   </tr>

   <tr>
    <td>
      <table cellpadding="1" cellspacing="0" border="1" bgcolor="#FFFFFF" style="border-collapse:collapse" align="center" width="350">
        <tr>
          <td>
            <table border="0" align="center" cellspacing="3" width="100%">

              <!-- -->
              <html:form action="/reportingAction.do" method="post">
              <!-- -->
                <tr>
                  <td width="30%"/>
                  <td height="15" width="20%" />
                  <td width="20%" />
                  <td width="30%"/>
  		</tr>

                <tr>
                  <td width="30%"/>
                  <td height="15" align="left" width="20%">Fecha Inicial:</td>
                  <td align="left" width="20%">
                  	<!--<html:text property="initialDate" value="01/02/2009" size="10" maxlength="10"/>
                  	-->
                  	<html:text property="initialDate" styleId="initialDate" readonly="true" styleClass="gris"/><img src="<%=request.getContextPath()%>/images/boton_calendario.gif" border="0" onclick="javascript: calendario.select(reportingForm.initialDate,'initialDate','dd/MM/yyyy');return false;">
                  </td>
                  <td width="30%"/>
  		</tr>

                <tr>
                  <td colspan="4" align="center">
                  	<font color="red"><html:errors property="initialDate"/></font>
                  </td>
                </tr>

                <tr>
                  <td width="30%" />
                  <td height="15" align="left" width="20%">Fecha Final: </td>
                  <td align="left" width="20%">
                  	<!--<html:text property="finalDate" value="01/02/2009" size="10" maxlength="10"/>&nbsp;
                  	-->
                  	<html:text property="finalDate" styleId="finalDate" readonly="true" styleClass="gris"/><img src="<%=request.getContextPath()%>/images/boton_calendario.gif" border="0" onclick="javascript: calendario.select(reportingForm.finalDate,'finalDate','dd/MM/yyyy');return false;">
                  </td>
                  <td width="30%"/>
                </tr>

                <tr>
                  <td colspan="4" align="center">
                  	<font color="red"><html:errors property="finalDate" /></font>
                  </td>
                </tr>

                <tr>
                  <td colspan="4" align="left">
                  	<html:radio property="reportIdServicio" value="1">Tarjeta</html:radio>&nbsp;
                  <td/>
                </tr>

                <tr>
                  <td colspan="4" align="left">
                  	<html:radio  property="reportIdServicio" value="2">Fondo</html:radio>
                  <td/>
                </tr>


                <tr>
                  <td width="30%"/>
                  <td height="15" width="20%" />
                  <td width="20%" />
                  <td width="30%"/>
  		</tr>

 		<tr>
                  <td colspan="4" align="center">
                  	<html:button property="reporting" value="reporting" onclick="javascript:comprobarFechas()" />
                  </td>
                </tr>

                <tr>
                  <td width="20%"/>
                  <td height="15" align="left" width="30%" />
                  <td width="30%" />
                  <td width="20%"/>
  		</tr>

              <!-- -->
              </html:form>
              <!-- -->

            </table>
          </td>
        </tr>
      </table>
    </td>
  </tr>
</table>

<div id="overDiv" style="position:absolute; visibility:hidden; background-color:#FFFFFF;"></div>


</body>
</html:html>

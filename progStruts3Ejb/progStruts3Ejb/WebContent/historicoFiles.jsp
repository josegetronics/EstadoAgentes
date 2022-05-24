<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=windows-1252" %>


<script>
function pushButton(valor){
	document.historicoForm.nameFile.value = valor;
        document.forms["historicoForm"].submit();
}
</script>

<html:html>

<body>

<table border="0" align="center" width="600">

   <tr>
     <td height="50">&nbsp;
     </td>
   </tr>

   <tr>
    <td colspan="2" align="center"></b>HISTORICO</b></td>
   </tr>

   <tr>
     <td height="20">&nbsp;
     </td>
   </tr>

   <tr>
    <td>
      <table cellpadding="1" cellspacing="0" border="1" bgcolor="#FFFFFF" style="border-collapse:collapse" align="center" width="450">
        <tr>
          <td>
            <table border="1" align="center" cellspacing="3" width="100%">

              <!-- -->
              <html:form action="/descargaAction.do" method="post">
              <!-- -->

		<html:hidden  property="nameFile" value=""/>

                <tr>
                  <td width="50%"></td>
                  <td height="15" width="50%"></td>
  		</tr>

                <tr>
                  <td width="50%"><b>YEAR-MOUNTH</b></td>
                  <td height="15" width="50%"><b>REPORTING</b></td>
  		</tr>

                <logic:iterate id="registro" property="fileNames" name="PageBean" type="snsadmin.controller.bean.RegistroBean">
              		<tr class="tablaValores">
			    <td width="50%"   class="valoresTabla" valign="top" ><bean:write name="registro" property="date"/></td>
                            <td width="50%" class="valoresTabla" valign="top"  onclick="javascript:pushButton('<bean:write name="registro" property="codigo"/>')"><font color="blue"><bean:write name="registro" property="descripcion"/></font></td>
              		</tr>
                </logic:iterate>

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


</body>
</html:html>








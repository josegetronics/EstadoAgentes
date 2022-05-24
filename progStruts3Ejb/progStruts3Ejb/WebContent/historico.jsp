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

<table border="0" align="center" width="500">

   <tr>
     <td height="50">&nbsp;
     </td>
   </tr>

   <tr>
    <td colspan="2" align="center">Menu Historico</td>
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
              <html:form action="/historicoAction.do" method="post">
              <!-- -->

		<html:hidden  property="nameFile" value="-1"/>

                <tr>
                  <td width="30%"/>
                  <td height="15" width="20%" />
                  <td width="20%" />
                  <td width="30%"/>
  		</tr>

 		<tr>
                  <td colspan="4" align="center">
                  	<html:button property="boton" value="Historico Ficheros" onclick="javascript:pushButton('prueba')" />
                  </td>
                </tr>

                <tr>
                  <td width="30%"/>
                  <td height="15" width="20%" />
                  <td width="20%" />
                  <td width="30%"/>
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


</body>
</html:html>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ page contentType="text/html; charset=windows-1252" %>





<html:html>


<script>
function comprobarLogin(){

  if(document.loginActionForm.usuario.value==""){
  	alert("El usuario no puede ser vacio.");
  }
  else{
    if(document.loginActionForm.password.value==""){
        alert("El password no puede ser vacio.");
    }
    else{
    	document.forms["loginActionForm"].submit();
    }
  }
}
</script>


<body>


<table border="0" align="center" width="300">

   <tr>
     <td height="50">&nbsp;
     </td>
   </tr>

   <tr>
    <td colspan="2" align="center">REGISTRO</td>
   </tr>

   <tr>
     <td height="20">&nbsp;
     </td>
   </tr>

   <tr>
    <td>
      <table cellpadding="1" cellspacing="0" border="1" bgcolor="#FFFFFF" style="border-collapse:collapse" align="center" width="300">

        <tr>
          <td>
            <table border="0" align="center" cellspacing="3" width="100%">

              <!-- -->
              <html:form action="/loginAction.do" method="post">
              <!-- -->

                <tr>
                  <td width="30%"/>
                  <td height="15" width="20%" />
                  <td width="20%" />
                  <td width="30%"/>
  		</tr>

                <tr>
                  <td width="30%"/>
                  <td height="15" align="left" width="20%">Usuario:</td>
                  <td align="left" width="20%">
                  	<html:text property="usuario" value="ant" size="20" maxlength="20"/>
                  </td>
                  <td width="30%"/>
  		</tr>

                <tr>
                  <td colspan="4" align="center">
                  	<font color="red"><html:errors property="usuario"/></font>
                  </td>
                </tr>

                <tr>
                  <td width="30%"/>
                  <td height="15" align="left" width="20%">Contrase&ntilde;a:</td>
                  <td align="left" width="20%">
                  	<html:password property="password" size="20" maxlength="20" value="ant0" />&nbsp;
                  </td>
                  <td width="30%"/>
                </tr>

                <tr>
                  <td colspan="4" align="center">
                  	<font color="red"><html:errors property="password"/></font>
                  </td>
                </tr>

 		<tr>
                  <td colspan="4" align="center">
                    <html:button value="inicio" property="inicio" onclick="javascript:comprobarLogin()" />
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

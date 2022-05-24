<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>

<html:html>
<body>

<table border="0" align="center" width="300">

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
      <table cellpadding="1" cellspacing="0" border="0" bgcolor="#FFFFFF" style="border-collapse:collapse" align="center" width="300">

        <tr>
          <td colspan="2"  height="15" />
        </tr>

        <tr>
          <td>
            <table border="1" align="center" cellspacing="3" width="100%" bgcolor="#FFFFFF" style="border-collapse:collapse" >

                <tr>
                  <td height="15"/>
                  	&nbsp;&nbsp;<bean:write name="PageBean" property="mensaje"/>&nbsp;&nbsp;
                  </td>
                </tr>

            </table>
          </td>
        </tr>

        <tr>
          <td colspan="2"  height="15" />
        </tr>

        <tr>
          <td colspan="2"  height="15" align="center">
            <input type="button" name="volver" onclick="javaScript:document.location.href='<bean:write name="PageBean" property="enlace"/>'" value="Volver">
          </td>
        </tr>

        <tr>
          <td colspan="2"  height="15" />
        </tr>

      </table>
    </td>
   </tr>
</table>

</body>
</html:html>

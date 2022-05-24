/* compiled from JSP: /reporting.jsp
*
* This code was automatically generated at 14:48:23 on 27-feb-2009
* by weblogic.servlet.jsp.Jsp2Java -- do not edit.
*/

package jsp_servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;

// User imports


// built-in init parameters:
// boolean             _verbose -- wants debugging

// Well-known variables:
// JspWriter out                  -- to write to the browser
// HttpServletRequest  request    -- the request object.
// HttpServletResponse response   -- the response object.
// PageContext pageContext        -- the page context for this JSP
// HttpSession session            -- the session object for the client (if any)
// ServletContext application     -- The servlet (application) context
// ServletConfig config           -- The ServletConfig for this JSP
// Object page                    -- the instance of this page's implementation class (i.e., 'this')

/**
* This code was automatically generated at 14:48:23 on 27-feb-2009
* by weblogic.servlet.jsp.Jsp2Java -- do not edit.
*
* Copyright (c) 2009 by BEA Systems, Inc. All Rights Reserved.
*/
public final class __reporting
extends
weblogic.servlet.jsp.JspBase
implements weblogic.servlet.jsp.StaleIndicator
{
  
  private static void _releaseTags(javax.servlet.jsp.tagext.Tag t) {
    while (t != null) {
      javax.servlet.jsp.tagext.Tag tmp = t;
      t = t.getParent();
      try { tmp.release(); } catch (Exception ignore) {}
    }
  }
  
  
  // StaleIndicator interface
  public boolean _isStale() {
    weblogic.servlet.jsp.StaleChecker sci =(weblogic.servlet.jsp.StaleChecker)(getServletConfig().getServletContext());
    java.io.File f = null;
    long lastModWhenBuilt = 0L;
    if (sci.isResourceStale("/reporting.jsp", 1235735566984L, "8.1.6.0", "Europe/Paris")) return true;
    return false;
  }
  
  public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
    java.io.File f = null;
    long lastModWhenBuilt = 0L;
    if (sci.isResourceStale("/reporting.jsp", 1235735566984L, "8.1.6.0", "Europe/Paris")) return true;
    return false;
  }
  
  
  private static void _writeText(ServletResponse rsp, JspWriter out, String block, byte[] blockBytes) throws IOException {
    if (!_WL_ENCODED_BYTES_OK || _hasEncodingChanged(rsp)) {
      out.print(block);
    } else {
      ((weblogic.servlet.jsp.ByteWriter)out).write(blockBytes, block);
    } 
  }
  
  private static boolean _hasEncodingChanged(ServletResponse rsp) {
    String encoding = rsp.getCharacterEncoding();
    if ("ISO-8859-1".equals(encoding)  || "Cp1252".equals(encoding) || "ISO8859_1".equals(encoding) || "ASCII".equals(encoding)) {
      return false;
    }
    if (_WL_ORIGINAL_ENCODING.equals(encoding)) {
      return false;
    }
    return true;
  }
  
  private static boolean _WL_ENCODED_BYTES_OK = true;
  
  private static final String _WL_ORIGINAL_ENCODING = "Cp1252";
  
  private static byte[] _getBytes(String block) {
    try {
      return block.getBytes(_WL_ORIGINAL_ENCODING);
    } catch (java.io.UnsupportedEncodingException u) {
      _WL_ENCODED_BYTES_OK = false;
    }
    return null;
  }
  private final static String _wl_block0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n<script type=\"text/javascript\" src =\"";
  private final static byte[] _wl_block0Bytes = _getBytes(_wl_block0);
  private final static String _wl_block1 = "/js/funcionesGenerales.js\"></script>\r\n<script type=\"text/javascript\" src =\"";
  private final static byte[] _wl_block1Bytes = _getBytes(_wl_block1);
  private final static String _wl_block2 = "/js/validacion.js\"></script>\r\n\r\n";
  private final static byte[] _wl_block2Bytes = _getBytes(_wl_block2);
  private final static String _wl_block3 = "\r\n\r\n\r\n<script>\r\nfunction comprobarFechas(){\r\n  if(document.reportingForm.initialDate.value.length != 10){\r\n  \talert(\"La Fecha Inicial no puede ser menor de 10 caracteres.\");\r\n  }\r\n  else{\r\n    if(!validacionFecha(document.reportingForm.initialDate.value)){\r\n        alert(\"La Fecha Inicial no es correcta.\");\r\n    }\r\n    else{\r\n        if(document.reportingForm.finalDate.value.length != 10){\r\n  \t    alert(\"La Fecha Final no puede ser menor de 10 caracteres.\");\r\n        }\r\n        else{\r\n            if(!validacionFecha(document.reportingForm.finalDate.value)){\r\n                alert(\"La Fecha Final no es correcta.\");\r\n            }\r\n            else{\r\n              if(!document.reportingForm.reportIdServicio[0].checked && !document.reportingForm.reportIdServicio[1].checked){\r\n                alert(\"Debe seleccionar una aplicacion.\");\r\n              }\r\n              else{\r\n                document.forms[\"reportingForm\"].submit();\r\n              }\r\n            }\r\n        }\r\n    }\r\n  }\r\n}\r\n</script>\r\n\r\n\r\n<body>\r\n\r\n<table border=\"0\" align=\"center\" width=\"500\">\r\n\r\n   <tr>\r\n     <td height=\"50\">&nbsp;\r\n     </td>\r\n   </tr>\r\n\r\n   <tr>\r\n    <td colspan=\"2\" align=\"center\">REPORTING</td>\r\n   </tr>\r\n\r\n   <tr>\r\n     <td height=\"20\">&nbsp;\r\n     </td>\r\n   </tr>\r\n\r\n   <tr>\r\n    <td>\r\n      <table cellpadding=\"1\" cellspacing=\"0\" border=\"1\" bgcolor=\"#FFFFFF\" style=\"border-collapse:collapse\" align=\"center\" width=\"350\">\r\n        <tr>\r\n          <td>\r\n            <table border=\"0\" align=\"center\" cellspacing=\"3\" width=\"100%\">\r\n\r\n              <!-- -->\r\n              ";
  private final static byte[] _wl_block3Bytes = _getBytes(_wl_block3);
  private final static String _wl_block4 = "\r\n              <!-- -->\r\n                <tr>\r\n                  <td width=\"30%\"/>\r\n                  <td height=\"15\" width=\"20%\" />\r\n                  <td width=\"20%\" />\r\n                  <td width=\"30%\"/>\r\n  \t\t</tr>\r\n\r\n                <tr>\r\n                  <td width=\"30%\"/>\r\n                  <td height=\"15\" align=\"left\" width=\"20%\">Fecha Inicial:</td>\r\n                  <td align=\"left\" width=\"20%\">\r\n                  \t";
  private final static byte[] _wl_block4Bytes = _getBytes(_wl_block4);
  private final static String _wl_block5 = "\r\n                  </td>\r\n                  <td width=\"30%\"/>\r\n  \t\t</tr>\r\n\r\n                <tr>\r\n                  <td colspan=\"4\" align=\"center\">\r\n                  \t<font color=\"red\">";
  private final static byte[] _wl_block5Bytes = _getBytes(_wl_block5);
  private final static String _wl_block6 = "</font>\r\n                  </td>\r\n                </tr>\r\n\r\n                <tr>\r\n                  <td width=\"30%\" />\r\n                  <td height=\"15\" align=\"left\" width=\"20%\">Fecha Final: </td>\r\n                  <td align=\"left\" width=\"20%\">\r\n                  \t";
  private final static byte[] _wl_block6Bytes = _getBytes(_wl_block6);
  private final static String _wl_block7 = "&nbsp;\r\n                  </td>\r\n                  <td width=\"30%\"/>\r\n                </tr>\r\n\r\n                <tr>\r\n                  <td colspan=\"4\" align=\"center\">\r\n                  \t<font color=\"red\">";
  private final static byte[] _wl_block7Bytes = _getBytes(_wl_block7);
  private final static String _wl_block8 = "</font>\r\n                  </td>\r\n                </tr>\r\n\r\n                <tr>\r\n                  <td colspan=\"4\" align=\"left\">\r\n                  \t";
  private final static byte[] _wl_block8Bytes = _getBytes(_wl_block8);
  private final static String _wl_block9 = "&nbsp;\r\n                  <td/>\r\n                </tr>\r\n\r\n                <tr>\r\n                  <td colspan=\"4\" align=\"left\">\r\n                  \t";
  private final static byte[] _wl_block9Bytes = _getBytes(_wl_block9);
  private final static String _wl_block10 = "\r\n                  <td/>\r\n                </tr>\r\n\r\n\r\n                <tr>\r\n                  <td width=\"30%\"/>\r\n                  <td height=\"15\" width=\"20%\" />\r\n                  <td width=\"20%\" />\r\n                  <td width=\"30%\"/>\r\n  \t\t</tr>\r\n\r\n \t\t<tr>\r\n                  <td colspan=\"4\" align=\"center\">\r\n                  \t";
  private final static byte[] _wl_block10Bytes = _getBytes(_wl_block10);
  private final static String _wl_block11 = "\r\n                  </td>\r\n                </tr>\r\n\r\n                <tr>\r\n                  <td width=\"20%\"/>\r\n                  <td height=\"15\" align=\"left\" width=\"30%\" />\r\n                  <td width=\"30%\" />\r\n                  <td width=\"20%\"/>\r\n  \t\t</tr>\r\n\r\n              <!-- -->\r\n              ";
  private final static byte[] _wl_block11Bytes = _getBytes(_wl_block11);
  private final static String _wl_block12 = "\r\n              <!-- -->\r\n\r\n            </table>\r\n          </td>\r\n        </tr>\r\n      </table>\r\n    </td>\r\n  </tr>\r\n</table>\r\n\r\n\r\n</body>\r\n";
  private final static byte[] _wl_block12Bytes = _getBytes(_wl_block12);
  private final static String _wl_block13 = "\r\n";
  private final static byte[] _wl_block13Bytes = _getBytes(_wl_block13);
  
  
  public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException 
  {  
    
    // declare and set well-known variables:
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.ServletContext application = config.getServletContext();
    javax.servlet.jsp.tagext.Tag _activeTag = null;
    // variables for Tag extension protocol
    int _int0 = 0, _int1 = 0, _int2 = 0;
    org.apache.struts.taglib.html.ButtonTag _html_button0 = null;
    org.apache.struts.taglib.html.ErrorsTag _html_errors0 = null;
    org.apache.struts.taglib.html.TextTag _html_text0 = null;
    org.apache.struts.taglib.html.FormTag _html_form0 = null;
    org.apache.struts.taglib.html.HtmlTag _html_html0 = null;
    org.apache.struts.taglib.html.RadioTag _html_radio0 = null;
    
    Object page = this;
    javax.servlet.jsp.JspWriter out;
    javax.servlet.jsp.PageContext pageContext =
    javax.servlet.jsp.JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
    
    response.setHeader("Content-Type", "text/html; charset=windows-1252");
    out = pageContext.getOut();
    JspWriter _originalOut = out;
    
    javax.servlet.http.HttpSession session = request.getSession(true);
    
    
    
    try { // error page try block
      
      response.setContentType("text/html; charset=windows-1252");
      
      _writeText(response, out, _wl_block0, _wl_block0Bytes);
      out.print(String.valueOf(request.getContextPath()));  //[ /reporting.jsp; Line: 8]
      _writeText(response, out, _wl_block1, _wl_block1Bytes);
      out.print(String.valueOf(request.getContextPath()));  //[ /reporting.jsp; Line: 9]
      _writeText(response, out, _wl_block2, _wl_block2Bytes);
      //^%$__TAG_CODEGEN : begin html custom tag block... //[ /reporting.jsp; Line: 11]
      /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 11]
      if (_html_html0 == null) _html_html0 = new org.apache.struts.taglib.html.HtmlTag(); //[ /reporting.jsp; Line: 11]
      _html_html0.setPageContext(pageContext); //[ /reporting.jsp; Line: 11]
      _html_html0.setParent((javax.servlet.jsp.tagext.Tag)null); //[ /reporting.jsp; Line: 11]
      _activeTag = _html_html0; //[ /reporting.jsp; Line: 11]
      _int0 = _html_html0.doStartTag(); //[ /reporting.jsp; Line: 11]
      if (_int0 == BodyTag.EVAL_BODY_BUFFERED) { //[ /reporting.jsp; Line: 11]
        throw new JspTagException("Since tag class org.apache.struts.taglib.html.HtmlTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /reporting.jsp; Line: 11]
      } //[ /reporting.jsp; Line: 11]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 11]
      if (_int0 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /reporting.jsp; Line: 11]
        do { //[ /reporting.jsp; Line: 11]
          /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /reporting.jsp; Line: 11]
          /*** declare & sync NESTED TagExtra Vars here ***/ //[ /reporting.jsp; Line: 11]
          _writeText(response, out, _wl_block3, _wl_block3Bytes);
          //^%$__TAG_CODEGEN : begin form custom tag block... //[ /reporting.jsp; Line: 72]
          /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 72]
          if (_html_form0 == null) _html_form0 = new org.apache.struts.taglib.html.FormTag(); //[ /reporting.jsp; Line: 72]
          _html_form0.setPageContext(pageContext); //[ /reporting.jsp; Line: 72]
          _html_form0.setParent((javax.servlet.jsp.tagext.Tag)_html_html0); //[ /reporting.jsp; Line: 72]
          _activeTag = _html_form0; //[ /reporting.jsp; Line: 72]
          _html_form0.setAction(weblogic.utils.StringUtils.valueOf("/reportingAction.do")); //[ /reporting.jsp; Line: 72]
          _html_form0.setMethod(weblogic.utils.StringUtils.valueOf("post")); //[ /reporting.jsp; Line: 72]
          _int1 = _html_form0.doStartTag(); //[ /reporting.jsp; Line: 72]
          if (_int1 == BodyTag.EVAL_BODY_BUFFERED) { //[ /reporting.jsp; Line: 72]
            throw new JspTagException("Since tag class org.apache.struts.taglib.html.FormTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /reporting.jsp; Line: 72]
          } //[ /reporting.jsp; Line: 72]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 72]
          if (_int1 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /reporting.jsp; Line: 72]
            do { //[ /reporting.jsp; Line: 72]
              /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /reporting.jsp; Line: 72]
              /*** declare & sync NESTED TagExtra Vars here ***/ //[ /reporting.jsp; Line: 72]
              _writeText(response, out, _wl_block4, _wl_block4Bytes);
              //^%$__TAG_CODEGEN : begin text custom tag block... //[ /reporting.jsp; Line: 85]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 85]
              if (_html_text0 == null) _html_text0 = new org.apache.struts.taglib.html.TextTag(); //[ /reporting.jsp; Line: 85]
              _html_text0.setPageContext(pageContext); //[ /reporting.jsp; Line: 85]
              _html_text0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /reporting.jsp; Line: 85]
              _activeTag = _html_text0; //[ /reporting.jsp; Line: 85]
              _html_text0.setProperty(weblogic.utils.StringUtils.valueOf("initialDate")); //[ /reporting.jsp; Line: 85]
              _html_text0.setValue(weblogic.utils.StringUtils.valueOf("01/02/2009")); //[ /reporting.jsp; Line: 85]
              _html_text0.setSize(weblogic.utils.StringUtils.valueOf("10")); //[ /reporting.jsp; Line: 85]
              _html_text0.setMaxlength(weblogic.utils.StringUtils.valueOf("10")); //[ /reporting.jsp; Line: 85]
              _int2 = _html_text0.doStartTag(); //[ /reporting.jsp; Line: 85]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 85]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_text0, _int2, true); //[ /reporting.jsp; Line: 85]
              if (_html_text0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_text0); return; } //[ /reporting.jsp; Line: 85]
              _activeTag = _html_text0.getParent(); _html_text0.release(); //[ /reporting.jsp; Line: 85]
              //end text custom tag.... //[ /reporting.jsp; Line: 85]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 85]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 85]
              _writeText(response, out, _wl_block5, _wl_block5Bytes);
              //^%$__TAG_CODEGEN : begin errors custom tag block... //[ /reporting.jsp; Line: 92]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 92]
              if (_html_errors0 == null) _html_errors0 = new org.apache.struts.taglib.html.ErrorsTag(); //[ /reporting.jsp; Line: 92]
              _html_errors0.setPageContext(pageContext); //[ /reporting.jsp; Line: 92]
              _html_errors0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /reporting.jsp; Line: 92]
              _activeTag = _html_errors0; //[ /reporting.jsp; Line: 92]
              _html_errors0.setProperty(weblogic.utils.StringUtils.valueOf("initialDate")); //[ /reporting.jsp; Line: 92]
              _int2 = _html_errors0.doStartTag(); //[ /reporting.jsp; Line: 92]
              if (_int2 == BodyTag.EVAL_BODY_BUFFERED) { //[ /reporting.jsp; Line: 92]
                throw new JspTagException("Since tag class org.apache.struts.taglib.html.ErrorsTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /reporting.jsp; Line: 92]
              } //[ /reporting.jsp; Line: 92]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 92]
              _int2 = _html_errors0.doEndTag(); //[ /reporting.jsp; Line: 92]
              if (_int2 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_errors0); return; } //[ /reporting.jsp; Line: 92]
              _activeTag = _html_errors0.getParent(); _html_errors0.release(); //[ /reporting.jsp; Line: 92]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 92]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 92]
              _writeText(response, out, _wl_block6, _wl_block6Bytes);
              //^%$__TAG_CODEGEN : begin text custom tag block... //[ /reporting.jsp; Line: 100]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 100]
              if (_html_text0 == null) _html_text0 = new org.apache.struts.taglib.html.TextTag(); //[ /reporting.jsp; Line: 100]
              _html_text0.setPageContext(pageContext); //[ /reporting.jsp; Line: 100]
              _html_text0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /reporting.jsp; Line: 100]
              _activeTag = _html_text0; //[ /reporting.jsp; Line: 100]
              _html_text0.setProperty(weblogic.utils.StringUtils.valueOf("finalDate")); //[ /reporting.jsp; Line: 100]
              _html_text0.setValue(weblogic.utils.StringUtils.valueOf("01/02/2009")); //[ /reporting.jsp; Line: 100]
              _html_text0.setSize(weblogic.utils.StringUtils.valueOf("10")); //[ /reporting.jsp; Line: 100]
              _html_text0.setMaxlength(weblogic.utils.StringUtils.valueOf("10")); //[ /reporting.jsp; Line: 100]
              _int2 = _html_text0.doStartTag(); //[ /reporting.jsp; Line: 100]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 100]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_text0, _int2, true); //[ /reporting.jsp; Line: 100]
              if (_html_text0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_text0); return; } //[ /reporting.jsp; Line: 100]
              _activeTag = _html_text0.getParent(); _html_text0.release(); //[ /reporting.jsp; Line: 100]
              //end text custom tag.... //[ /reporting.jsp; Line: 100]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 100]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 100]
              _writeText(response, out, _wl_block7, _wl_block7Bytes);
              //^%$__TAG_CODEGEN : begin errors custom tag block... //[ /reporting.jsp; Line: 107]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 107]
              if (_html_errors0 == null) _html_errors0 = new org.apache.struts.taglib.html.ErrorsTag(); //[ /reporting.jsp; Line: 107]
              _html_errors0.setPageContext(pageContext); //[ /reporting.jsp; Line: 107]
              _html_errors0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /reporting.jsp; Line: 107]
              _activeTag = _html_errors0; //[ /reporting.jsp; Line: 107]
              _html_errors0.setProperty(weblogic.utils.StringUtils.valueOf("finalDate")); //[ /reporting.jsp; Line: 107]
              _int2 = _html_errors0.doStartTag(); //[ /reporting.jsp; Line: 107]
              if (_int2 == BodyTag.EVAL_BODY_BUFFERED) { //[ /reporting.jsp; Line: 107]
                throw new JspTagException("Since tag class org.apache.struts.taglib.html.ErrorsTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /reporting.jsp; Line: 107]
              } //[ /reporting.jsp; Line: 107]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 107]
              _int2 = _html_errors0.doEndTag(); //[ /reporting.jsp; Line: 107]
              if (_int2 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_errors0); return; } //[ /reporting.jsp; Line: 107]
              _activeTag = _html_errors0.getParent(); _html_errors0.release(); //[ /reporting.jsp; Line: 107]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 107]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 107]
              _writeText(response, out, _wl_block8, _wl_block8Bytes);
              //^%$__TAG_CODEGEN : begin radio custom tag block... //[ /reporting.jsp; Line: 113]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 113]
              if (_html_radio0 == null) _html_radio0 = new org.apache.struts.taglib.html.RadioTag(); //[ /reporting.jsp; Line: 113]
              _html_radio0.setPageContext(pageContext); //[ /reporting.jsp; Line: 113]
              _html_radio0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /reporting.jsp; Line: 113]
              _activeTag = _html_radio0; //[ /reporting.jsp; Line: 113]
              _html_radio0.setProperty(weblogic.utils.StringUtils.valueOf("reportIdServicio")); //[ /reporting.jsp; Line: 113]
              _html_radio0.setValue(weblogic.utils.StringUtils.valueOf("1")); //[ /reporting.jsp; Line: 113]
              _int2 = _html_radio0.doStartTag(); //[ /reporting.jsp; Line: 113]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 113]
              if (_int2 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /reporting.jsp; Line: 113]
                if (_int2 == BodyTag.EVAL_BODY_BUFFERED) { //[ /reporting.jsp; Line: 113]
                  out = pageContext.pushBody(); //[ /reporting.jsp; Line: 113]
                  _html_radio0.setBodyContent((BodyContent)out); //[ /reporting.jsp; Line: 113]
                  _html_radio0.doInitBody(); //[ /reporting.jsp; Line: 113]
                } //[ /reporting.jsp; Line: 113]
                try { // _html_radio0 popBody() try/finally.. //[ /reporting.jsp; Line: 113]
                  do { //[ /reporting.jsp; Line: 113]
                    /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /reporting.jsp; Line: 113]
                    /*** declare & sync NESTED TagExtra Vars here ***/ //[ /reporting.jsp; Line: 113]
                    out.print("Tarjeta");
                    //^%$__TAG_CODEGEN  //[ /reporting.jsp; Line: 113]
                  } while (_html_radio0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /reporting.jsp; Line: 113]
                } finally { // _html_radio0 popBody() finally... //[ /reporting.jsp; Line: 113]
                  if (_int2 == BodyTag.EVAL_BODY_BUFFERED) out = pageContext.popBody(); //[ /reporting.jsp; Line: 113]
                } //[ /reporting.jsp; Line: 113]
              } // end !SKIP_BODY //[ /reporting.jsp; Line: 113]
              if (_html_radio0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_radio0); return; } //[ /reporting.jsp; Line: 113]
              _activeTag = _html_radio0.getParent(); _html_radio0.release(); //[ /reporting.jsp; Line: 113]
              //end radio custom tag.... //[ /reporting.jsp; Line: 113]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 113]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 113]
              _writeText(response, out, _wl_block9, _wl_block9Bytes);
              //^%$__TAG_CODEGEN : begin radio custom tag block... //[ /reporting.jsp; Line: 119]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 119]
              if (_html_radio0 == null) _html_radio0 = new org.apache.struts.taglib.html.RadioTag(); //[ /reporting.jsp; Line: 119]
              _html_radio0.setPageContext(pageContext); //[ /reporting.jsp; Line: 119]
              _html_radio0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /reporting.jsp; Line: 119]
              _activeTag = _html_radio0; //[ /reporting.jsp; Line: 119]
              _html_radio0.setProperty(weblogic.utils.StringUtils.valueOf("reportIdServicio")); //[ /reporting.jsp; Line: 119]
              _html_radio0.setValue(weblogic.utils.StringUtils.valueOf("2")); //[ /reporting.jsp; Line: 119]
              _int2 = _html_radio0.doStartTag(); //[ /reporting.jsp; Line: 119]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 119]
              if (_int2 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /reporting.jsp; Line: 119]
                if (_int2 == BodyTag.EVAL_BODY_BUFFERED) { //[ /reporting.jsp; Line: 119]
                  out = pageContext.pushBody(); //[ /reporting.jsp; Line: 119]
                  _html_radio0.setBodyContent((BodyContent)out); //[ /reporting.jsp; Line: 119]
                  _html_radio0.doInitBody(); //[ /reporting.jsp; Line: 119]
                } //[ /reporting.jsp; Line: 119]
                try { // _html_radio0 popBody() try/finally.. //[ /reporting.jsp; Line: 119]
                  do { //[ /reporting.jsp; Line: 119]
                    /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /reporting.jsp; Line: 119]
                    /*** declare & sync NESTED TagExtra Vars here ***/ //[ /reporting.jsp; Line: 119]
                    out.print("Fondo");
                    //^%$__TAG_CODEGEN  //[ /reporting.jsp; Line: 119]
                  } while (_html_radio0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /reporting.jsp; Line: 119]
                } finally { // _html_radio0 popBody() finally... //[ /reporting.jsp; Line: 119]
                  if (_int2 == BodyTag.EVAL_BODY_BUFFERED) out = pageContext.popBody(); //[ /reporting.jsp; Line: 119]
                } //[ /reporting.jsp; Line: 119]
              } // end !SKIP_BODY //[ /reporting.jsp; Line: 119]
              if (_html_radio0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_radio0); return; } //[ /reporting.jsp; Line: 119]
              _activeTag = _html_radio0.getParent(); _html_radio0.release(); //[ /reporting.jsp; Line: 119]
              //end radio custom tag.... //[ /reporting.jsp; Line: 119]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 119]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 119]
              _writeText(response, out, _wl_block10, _wl_block10Bytes);
              //^%$__TAG_CODEGEN : begin button custom tag block... //[ /reporting.jsp; Line: 133]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 133]
              if (_html_button0 == null) _html_button0 = new org.apache.struts.taglib.html.ButtonTag(); //[ /reporting.jsp; Line: 133]
              _html_button0.setPageContext(pageContext); //[ /reporting.jsp; Line: 133]
              _html_button0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /reporting.jsp; Line: 133]
              _activeTag = _html_button0; //[ /reporting.jsp; Line: 133]
              _html_button0.setProperty(weblogic.utils.StringUtils.valueOf("reporting")); //[ /reporting.jsp; Line: 133]
              _html_button0.setValue(weblogic.utils.StringUtils.valueOf("reporting")); //[ /reporting.jsp; Line: 133]
              _html_button0.setOnclick(weblogic.utils.StringUtils.valueOf("javascript:comprobarFechas()")); //[ /reporting.jsp; Line: 133]
              _int2 = _html_button0.doStartTag(); //[ /reporting.jsp; Line: 133]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 133]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_button0, _int2, true); //[ /reporting.jsp; Line: 133]
              if (_html_button0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_button0); return; } //[ /reporting.jsp; Line: 133]
              _activeTag = _html_button0.getParent(); _html_button0.release(); //[ /reporting.jsp; Line: 133]
              //end button custom tag.... //[ /reporting.jsp; Line: 133]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 133]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 133]
              _writeText(response, out, _wl_block11, _wl_block11Bytes);
              //^%$__TAG_CODEGEN  //[ /reporting.jsp; Line: 145]
            } while (_html_form0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /reporting.jsp; Line: 145]
          } // end !SKIP_BODY //[ /reporting.jsp; Line: 145]
          if (_html_form0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_form0); return; } //[ /reporting.jsp; Line: 145]
          _activeTag = _html_form0.getParent(); _html_form0.release(); //[ /reporting.jsp; Line: 145]
          //end form custom tag.... //[ /reporting.jsp; Line: 145]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 145]
          /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 145]
          _writeText(response, out, _wl_block12, _wl_block12Bytes);
          //^%$__TAG_CODEGEN  //[ /reporting.jsp; Line: 158]
        } while (_html_html0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /reporting.jsp; Line: 158]
      } // end !SKIP_BODY //[ /reporting.jsp; Line: 158]
      if (_html_html0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_html0); return; } //[ /reporting.jsp; Line: 158]
      _activeTag = _html_html0.getParent(); _html_html0.release(); //[ /reporting.jsp; Line: 158]
      //end html custom tag.... //[ /reporting.jsp; Line: 158]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /reporting.jsp; Line: 158]
      /*** declare & sync AT_END TagExtra Vars here ***/ //[ /reporting.jsp; Line: 158]
      _writeText(response, out, _wl_block13, _wl_block13Bytes);
    } catch (Throwable __ee) {
      while (out != null && out != _originalOut) out = pageContext.popBody();
      _releaseTags(_activeTag);
      pageContext.handlePageException((Throwable)__ee);
    }
    
    
    //before final close brace...
  }
  
  
}

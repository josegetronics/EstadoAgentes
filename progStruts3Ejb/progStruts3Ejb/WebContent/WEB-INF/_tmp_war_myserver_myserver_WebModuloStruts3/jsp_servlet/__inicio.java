/* compiled from JSP: /inicio.jsp
*
* This code was automatically generated at 18:31:24 on 08-ene-2009
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
* This code was automatically generated at 18:31:24 on 08-ene-2009
* by weblogic.servlet.jsp.Jsp2Java -- do not edit.
*
* Copyright (c) 2009 by BEA Systems, Inc. All Rights Reserved.
*/
public final class __inicio
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
    if (sci.isResourceStale("/inicio.jsp", 1180004830280L, "8.1.6.0", "Europe/Paris")) return true;
    return false;
  }
  
  public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
    java.io.File f = null;
    long lastModWhenBuilt = 0L;
    if (sci.isResourceStale("/inicio.jsp", 1180004830280L, "8.1.6.0", "Europe/Paris")) return true;
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
  private final static String _wl_block0 = "\n\n\n\n\n\n\n\n\n\n";
  private final static byte[] _wl_block0Bytes = _getBytes(_wl_block0);
  private final static String _wl_block1 = "\n\n\n<script>\nfunction comprobarLogin(){\n\n  if(document.loginActionForm.usuario.value==\"\"){\n  \talert(\"El usuario no puede ser vacio.\");\n  }\n  else{\n    if(document.loginActionForm.password.value==\"\"){\n        alert(\"El password no puede ser vacio.\");\n    }\n    else{\n    \tdocument.forms[\"loginActionForm\"].submit();\n    }\n  }\n}\n</script>\n\n\n<body>\n\n\n<table border=\"0\" align=\"center\" width=\"300\">\n\n   <tr>\n     <td height=\"50\">&nbsp;\n     </td>\n   </tr>\n\n   <tr>\n    <td colspan=\"2\" align=\"center\">REGISTRO</td>\n   </tr>\n\n   <tr>\n     <td height=\"20\">&nbsp;\n     </td>\n   </tr>\n\n   <tr>\n    <td>\n      <table cellpadding=\"1\" cellspacing=\"0\" border=\"1\" bgcolor=\"#FFFFFF\" style=\"border-collapse:collapse\" align=\"center\" width=\"300\">\n\n        <tr>\n          <td>\n            <table border=\"0\" align=\"center\" cellspacing=\"3\" width=\"100%\">\n\n              <!-- -->\n              ";
  private final static byte[] _wl_block1Bytes = _getBytes(_wl_block1);
  private final static String _wl_block2 = "\n              <!-- -->\n\n                <tr>\n                  <td width=\"30%\"/>\n                  <td height=\"15\" width=\"20%\" />\n                  <td width=\"20%\" />\n                  <td width=\"30%\"/>\n  \t\t</tr>\n\n                <tr>\n                  <td width=\"30%\"/>\n                  <td height=\"15\" align=\"left\" width=\"20%\">Usuario:</td>\n                  <td align=\"left\" width=\"20%\">\n                  \t";
  private final static byte[] _wl_block2Bytes = _getBytes(_wl_block2);
  private final static String _wl_block3 = "\n                  </td>\n                  <td width=\"30%\"/>\n  \t\t</tr>\n\n                <tr>\n                  <td colspan=\"4\" align=\"center\">\n                  \t<font color=\"red\">";
  private final static byte[] _wl_block3Bytes = _getBytes(_wl_block3);
  private final static String _wl_block4 = "</font>\n                  </td>\n                </tr>\n\n                <tr>\n                  <td width=\"30%\"/>\n                  <td height=\"15\" align=\"left\" width=\"20%\">Contrase&ntilde;a:</td>\n                  <td align=\"left\" width=\"20%\">\n                  \t";
  private final static byte[] _wl_block4Bytes = _getBytes(_wl_block4);
  private final static String _wl_block5 = "&nbsp;\n                  </td>\n                  <td width=\"30%\"/>\n                </tr>\n\n                <tr>\n                  <td colspan=\"4\" align=\"center\">\n                  \t<font color=\"red\">";
  private final static byte[] _wl_block5Bytes = _getBytes(_wl_block5);
  private final static String _wl_block6 = "</font>\n                  </td>\n                </tr>\n\n \t\t<tr>\n                  <td colspan=\"4\" align=\"center\">\n                    ";
  private final static byte[] _wl_block6Bytes = _getBytes(_wl_block6);
  private final static String _wl_block7 = "\n                  </td>\n                </tr>\n\n                <tr>\n                  <td width=\"30%\"/>\n                  <td height=\"15\" width=\"20%\" />\n                  <td width=\"20%\" />\n                  <td width=\"30%\"/>\n  \t\t</tr>\n\n              <!-- -->\n              ";
  private final static byte[] _wl_block7Bytes = _getBytes(_wl_block7);
  private final static String _wl_block8 = "\n              <!-- -->\n\n            </table>\n          </td>\n        </tr>\n      </table>\n    </td>\n  </tr>\n</table>\n\n\n</body>\n";
  private final static byte[] _wl_block8Bytes = _getBytes(_wl_block8);
  private final static String _wl_block9 = "\n";
  private final static byte[] _wl_block9Bytes = _getBytes(_wl_block9);
  
  
  public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException 
  {  
    
    // declare and set well-known variables:
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.ServletContext application = config.getServletContext();
    javax.servlet.jsp.tagext.Tag _activeTag = null;
    // variables for Tag extension protocol
    int _int0 = 0, _int1 = 0, _int2 = 0;
    org.apache.struts.taglib.html.PasswordTag _html_password0 = null;
    org.apache.struts.taglib.html.ButtonTag _html_button0 = null;
    org.apache.struts.taglib.html.ErrorsTag _html_errors0 = null;
    org.apache.struts.taglib.html.TextTag _html_text0 = null;
    org.apache.struts.taglib.html.FormTag _html_form0 = null;
    org.apache.struts.taglib.html.HtmlTag _html_html0 = null;
    
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
      //^%$__TAG_CODEGEN : begin html custom tag block... //[ /inicio.jsp; Line: 11]
      /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 11]
      if (_html_html0 == null) _html_html0 = new org.apache.struts.taglib.html.HtmlTag(); //[ /inicio.jsp; Line: 11]
      _html_html0.setPageContext(pageContext); //[ /inicio.jsp; Line: 11]
      _html_html0.setParent((javax.servlet.jsp.tagext.Tag)null); //[ /inicio.jsp; Line: 11]
      _activeTag = _html_html0; //[ /inicio.jsp; Line: 11]
      _int0 = _html_html0.doStartTag(); //[ /inicio.jsp; Line: 11]
      if (_int0 == BodyTag.EVAL_BODY_BUFFERED) { //[ /inicio.jsp; Line: 11]
        throw new JspTagException("Since tag class org.apache.struts.taglib.html.HtmlTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /inicio.jsp; Line: 11]
      } //[ /inicio.jsp; Line: 11]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 11]
      if (_int0 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /inicio.jsp; Line: 11]
        do { //[ /inicio.jsp; Line: 11]
          /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /inicio.jsp; Line: 11]
          /*** declare & sync NESTED TagExtra Vars here ***/ //[ /inicio.jsp; Line: 11]
          _writeText(response, out, _wl_block1, _wl_block1Bytes);
          //^%$__TAG_CODEGEN : begin form custom tag block... //[ /inicio.jsp; Line: 60]
          /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 60]
          if (_html_form0 == null) _html_form0 = new org.apache.struts.taglib.html.FormTag(); //[ /inicio.jsp; Line: 60]
          _html_form0.setPageContext(pageContext); //[ /inicio.jsp; Line: 60]
          _html_form0.setParent((javax.servlet.jsp.tagext.Tag)_html_html0); //[ /inicio.jsp; Line: 60]
          _activeTag = _html_form0; //[ /inicio.jsp; Line: 60]
          _html_form0.setAction(weblogic.utils.StringUtils.valueOf("/loginAction.do")); //[ /inicio.jsp; Line: 60]
          _html_form0.setMethod(weblogic.utils.StringUtils.valueOf("post")); //[ /inicio.jsp; Line: 60]
          _int1 = _html_form0.doStartTag(); //[ /inicio.jsp; Line: 60]
          if (_int1 == BodyTag.EVAL_BODY_BUFFERED) { //[ /inicio.jsp; Line: 60]
            throw new JspTagException("Since tag class org.apache.struts.taglib.html.FormTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /inicio.jsp; Line: 60]
          } //[ /inicio.jsp; Line: 60]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 60]
          if (_int1 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /inicio.jsp; Line: 60]
            do { //[ /inicio.jsp; Line: 60]
              /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /inicio.jsp; Line: 60]
              /*** declare & sync NESTED TagExtra Vars here ***/ //[ /inicio.jsp; Line: 60]
              _writeText(response, out, _wl_block2, _wl_block2Bytes);
              //^%$__TAG_CODEGEN : begin text custom tag block... //[ /inicio.jsp; Line: 74]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 74]
              if (_html_text0 == null) _html_text0 = new org.apache.struts.taglib.html.TextTag(); //[ /inicio.jsp; Line: 74]
              _html_text0.setPageContext(pageContext); //[ /inicio.jsp; Line: 74]
              _html_text0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /inicio.jsp; Line: 74]
              _activeTag = _html_text0; //[ /inicio.jsp; Line: 74]
              _html_text0.setProperty(weblogic.utils.StringUtils.valueOf("usuario")); //[ /inicio.jsp; Line: 74]
              _html_text0.setValue(weblogic.utils.StringUtils.valueOf("ant")); //[ /inicio.jsp; Line: 74]
              _html_text0.setSize(weblogic.utils.StringUtils.valueOf("20")); //[ /inicio.jsp; Line: 74]
              _html_text0.setMaxlength(weblogic.utils.StringUtils.valueOf("20")); //[ /inicio.jsp; Line: 74]
              _int2 = _html_text0.doStartTag(); //[ /inicio.jsp; Line: 74]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 74]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_text0, _int2, true); //[ /inicio.jsp; Line: 74]
              if (_html_text0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_text0); return; } //[ /inicio.jsp; Line: 74]
              _activeTag = _html_text0.getParent(); _html_text0.release(); //[ /inicio.jsp; Line: 74]
              //end text custom tag.... //[ /inicio.jsp; Line: 74]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 74]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /inicio.jsp; Line: 74]
              _writeText(response, out, _wl_block3, _wl_block3Bytes);
              //^%$__TAG_CODEGEN : begin errors custom tag block... //[ /inicio.jsp; Line: 81]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 81]
              if (_html_errors0 == null) _html_errors0 = new org.apache.struts.taglib.html.ErrorsTag(); //[ /inicio.jsp; Line: 81]
              _html_errors0.setPageContext(pageContext); //[ /inicio.jsp; Line: 81]
              _html_errors0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /inicio.jsp; Line: 81]
              _activeTag = _html_errors0; //[ /inicio.jsp; Line: 81]
              _html_errors0.setProperty(weblogic.utils.StringUtils.valueOf("usuario")); //[ /inicio.jsp; Line: 81]
              _int2 = _html_errors0.doStartTag(); //[ /inicio.jsp; Line: 81]
              if (_int2 == BodyTag.EVAL_BODY_BUFFERED) { //[ /inicio.jsp; Line: 81]
                throw new JspTagException("Since tag class org.apache.struts.taglib.html.ErrorsTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /inicio.jsp; Line: 81]
              } //[ /inicio.jsp; Line: 81]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 81]
              _int2 = _html_errors0.doEndTag(); //[ /inicio.jsp; Line: 81]
              if (_int2 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_errors0); return; } //[ /inicio.jsp; Line: 81]
              _activeTag = _html_errors0.getParent(); _html_errors0.release(); //[ /inicio.jsp; Line: 81]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 81]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /inicio.jsp; Line: 81]
              _writeText(response, out, _wl_block4, _wl_block4Bytes);
              //^%$__TAG_CODEGEN : begin password custom tag block... //[ /inicio.jsp; Line: 89]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 89]
              if (_html_password0 == null) _html_password0 = new org.apache.struts.taglib.html.PasswordTag(); //[ /inicio.jsp; Line: 89]
              _html_password0.setPageContext(pageContext); //[ /inicio.jsp; Line: 89]
              _html_password0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /inicio.jsp; Line: 89]
              _activeTag = _html_password0; //[ /inicio.jsp; Line: 89]
              _html_password0.setProperty(weblogic.utils.StringUtils.valueOf("password")); //[ /inicio.jsp; Line: 89]
              _html_password0.setSize(weblogic.utils.StringUtils.valueOf("20")); //[ /inicio.jsp; Line: 89]
              _html_password0.setMaxlength(weblogic.utils.StringUtils.valueOf("20")); //[ /inicio.jsp; Line: 89]
              _html_password0.setValue(weblogic.utils.StringUtils.valueOf("ant0")); //[ /inicio.jsp; Line: 89]
              _int2 = _html_password0.doStartTag(); //[ /inicio.jsp; Line: 89]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 89]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_password0, _int2, true); //[ /inicio.jsp; Line: 89]
              if (_html_password0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_password0); return; } //[ /inicio.jsp; Line: 89]
              _activeTag = _html_password0.getParent(); _html_password0.release(); //[ /inicio.jsp; Line: 89]
              //end password custom tag.... //[ /inicio.jsp; Line: 89]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 89]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /inicio.jsp; Line: 89]
              _writeText(response, out, _wl_block5, _wl_block5Bytes);
              //^%$__TAG_CODEGEN : begin errors custom tag block... //[ /inicio.jsp; Line: 96]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 96]
              if (_html_errors0 == null) _html_errors0 = new org.apache.struts.taglib.html.ErrorsTag(); //[ /inicio.jsp; Line: 96]
              _html_errors0.setPageContext(pageContext); //[ /inicio.jsp; Line: 96]
              _html_errors0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /inicio.jsp; Line: 96]
              _activeTag = _html_errors0; //[ /inicio.jsp; Line: 96]
              _html_errors0.setProperty(weblogic.utils.StringUtils.valueOf("password")); //[ /inicio.jsp; Line: 96]
              _int2 = _html_errors0.doStartTag(); //[ /inicio.jsp; Line: 96]
              if (_int2 == BodyTag.EVAL_BODY_BUFFERED) { //[ /inicio.jsp; Line: 96]
                throw new JspTagException("Since tag class org.apache.struts.taglib.html.ErrorsTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /inicio.jsp; Line: 96]
              } //[ /inicio.jsp; Line: 96]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 96]
              _int2 = _html_errors0.doEndTag(); //[ /inicio.jsp; Line: 96]
              if (_int2 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_errors0); return; } //[ /inicio.jsp; Line: 96]
              _activeTag = _html_errors0.getParent(); _html_errors0.release(); //[ /inicio.jsp; Line: 96]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 96]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /inicio.jsp; Line: 96]
              _writeText(response, out, _wl_block6, _wl_block6Bytes);
              //^%$__TAG_CODEGEN : begin button custom tag block... //[ /inicio.jsp; Line: 102]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 102]
              if (_html_button0 == null) _html_button0 = new org.apache.struts.taglib.html.ButtonTag(); //[ /inicio.jsp; Line: 102]
              _html_button0.setPageContext(pageContext); //[ /inicio.jsp; Line: 102]
              _html_button0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /inicio.jsp; Line: 102]
              _activeTag = _html_button0; //[ /inicio.jsp; Line: 102]
              _html_button0.setValue(weblogic.utils.StringUtils.valueOf("inicio")); //[ /inicio.jsp; Line: 102]
              _html_button0.setProperty(weblogic.utils.StringUtils.valueOf("inicio")); //[ /inicio.jsp; Line: 102]
              _html_button0.setOnclick(weblogic.utils.StringUtils.valueOf("javascript:comprobarLogin()")); //[ /inicio.jsp; Line: 102]
              _int2 = _html_button0.doStartTag(); //[ /inicio.jsp; Line: 102]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 102]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_button0, _int2, true); //[ /inicio.jsp; Line: 102]
              if (_html_button0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_button0); return; } //[ /inicio.jsp; Line: 102]
              _activeTag = _html_button0.getParent(); _html_button0.release(); //[ /inicio.jsp; Line: 102]
              //end button custom tag.... //[ /inicio.jsp; Line: 102]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 102]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /inicio.jsp; Line: 102]
              _writeText(response, out, _wl_block7, _wl_block7Bytes);
              //^%$__TAG_CODEGEN  //[ /inicio.jsp; Line: 114]
            } while (_html_form0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /inicio.jsp; Line: 114]
          } // end !SKIP_BODY //[ /inicio.jsp; Line: 114]
          if (_html_form0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_form0); return; } //[ /inicio.jsp; Line: 114]
          _activeTag = _html_form0.getParent(); _html_form0.release(); //[ /inicio.jsp; Line: 114]
          //end form custom tag.... //[ /inicio.jsp; Line: 114]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 114]
          /*** declare & sync AT_END TagExtra Vars here ***/ //[ /inicio.jsp; Line: 114]
          _writeText(response, out, _wl_block8, _wl_block8Bytes);
          //^%$__TAG_CODEGEN  //[ /inicio.jsp; Line: 127]
        } while (_html_html0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /inicio.jsp; Line: 127]
      } // end !SKIP_BODY //[ /inicio.jsp; Line: 127]
      if (_html_html0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_html0); return; } //[ /inicio.jsp; Line: 127]
      _activeTag = _html_html0.getParent(); _html_html0.release(); //[ /inicio.jsp; Line: 127]
      //end html custom tag.... //[ /inicio.jsp; Line: 127]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /inicio.jsp; Line: 127]
      /*** declare & sync AT_END TagExtra Vars here ***/ //[ /inicio.jsp; Line: 127]
      _writeText(response, out, _wl_block9, _wl_block9Bytes);
    } catch (Throwable __ee) {
      while (out != null && out != _originalOut) out = pageContext.popBody();
      _releaseTags(_activeTag);
      pageContext.handlePageException((Throwable)__ee);
    }
    
    
    //before final close brace...
  }
  
  
}

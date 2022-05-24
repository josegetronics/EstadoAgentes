/* compiled from JSP: /menuInicio.jsp
*
* This code was automatically generated at 17:28:51 on 31-mar-2008
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
* This code was automatically generated at 17:28:51 on 31-mar-2008
* by weblogic.servlet.jsp.Jsp2Java -- do not edit.
*
* Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
*/
public final class __menuinicio
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
    if (sci.isResourceStale("/menuInicio.jsp", 1206977083026L, "8.1.6.0", "Europe/Paris")) return true;
    return false;
  }
  
  public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
    java.io.File f = null;
    long lastModWhenBuilt = 0L;
    if (sci.isResourceStale("/menuInicio.jsp", 1206977083026L, "8.1.6.0", "Europe/Paris")) return true;
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
  private final static String _wl_block0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n<script>\r\nfunction pushButton(valor){\r\n\tdocument.menuForm.option.value = valor;\r\n        document.forms[\"menuForm\"].submit();\r\n}\r\n</script>\r\n\r\n";
  private final static byte[] _wl_block0Bytes = _getBytes(_wl_block0);
  private final static String _wl_block1 = "\r\n\r\n<body>\r\n\r\n<table border=\"0\" align=\"center\" width=\"500\">\r\n\r\n   <tr>\r\n     <td height=\"50\">&nbsp;\r\n     </td>\r\n   </tr>\r\n\r\n   <tr>\r\n    <td colspan=\"2\" align=\"center\">MENU</td>\r\n   </tr>\r\n\r\n   <tr>\r\n     <td height=\"20\">&nbsp;\r\n     </td>\r\n   </tr>\r\n\r\n   <tr>\r\n    <td>\r\n      <table cellpadding=\"1\" cellspacing=\"0\" border=\"1\" bgcolor=\"#FFFFFF\" style=\"border-collapse:collapse\" align=\"center\" width=\"350\">\r\n        <tr>\r\n          <td>\r\n            <table border=\"0\" align=\"center\" cellspacing=\"3\" width=\"100%\">\r\n\r\n              <!-- -->\r\n              ";
  private final static byte[] _wl_block1Bytes = _getBytes(_wl_block1);
  private final static String _wl_block2 = "\r\n              <!-- -->\r\n\r\n                ";
  private final static byte[] _wl_block2Bytes = _getBytes(_wl_block2);
  private final static String _wl_block3 = "\r\n\r\n                <tr>\r\n                  <td width=\"30%\"/>\r\n                  <td height=\"15\" width=\"20%\" />\r\n                  <td width=\"20%\" />\r\n                  <td width=\"30%\"/>\r\n  \t\t</tr>\r\n\r\n \t\t<tr>\r\n                  <td colspan=\"4\" align=\"center\">\r\n                  \t";
  private final static byte[] _wl_block3Bytes = _getBytes(_wl_block3);
  private final static String _wl_block4 = "\r\n                  </td>\r\n                </tr>\r\n\r\n                <tr>\r\n                  <td width=\"30%\"/>\r\n                  <td height=\"15\" width=\"20%\" />\r\n                  <td width=\"20%\" />\r\n                  <td width=\"30%\"/>\r\n  \t\t</tr>\r\n\r\n \t\t<tr>\r\n                  <td colspan=\"4\" align=\"center\">\r\n                  \t";
  private final static byte[] _wl_block4Bytes = _getBytes(_wl_block4);
  private final static String _wl_block5 = "\r\n                  </td>\r\n                </tr>\r\n\r\n                <tr>\r\n                  <td width=\"20%\"/>\r\n                  <td height=\"15\" align=\"left\" width=\"30%\" />\r\n                  <td width=\"30%\" />\r\n                  <td width=\"20%\"/>\r\n  \t\t</tr>\r\n\r\n              <!-- -->\r\n              ";
  private final static byte[] _wl_block5Bytes = _getBytes(_wl_block5);
  private final static String _wl_block6 = "\r\n              <!-- -->\r\n\r\n            </table>\r\n          </td>\r\n        </tr>\r\n      </table>\r\n    </td>\r\n  </tr>\r\n</table>\r\n\r\n\r\n</body>\r\n";
  private final static byte[] _wl_block6Bytes = _getBytes(_wl_block6);
  private final static String _wl_block7 = "\r\n";
  private final static byte[] _wl_block7Bytes = _getBytes(_wl_block7);
  
  
  public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException 
  {  
    
    // declare and set well-known variables:
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.ServletContext application = config.getServletContext();
    javax.servlet.jsp.tagext.Tag _activeTag = null;
    // variables for Tag extension protocol
    int _int0 = 0, _int1 = 0, _int2 = 0;
    org.apache.struts.taglib.html.ButtonTag _html_button0 = null;
    org.apache.struts.taglib.html.HiddenTag _html_hidden0 = null;
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
      //^%$__TAG_CODEGEN : begin html custom tag block... //[ /menuInicio.jsp; Line: 15]
      /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 15]
      if (_html_html0 == null) _html_html0 = new org.apache.struts.taglib.html.HtmlTag(); //[ /menuInicio.jsp; Line: 15]
      _html_html0.setPageContext(pageContext); //[ /menuInicio.jsp; Line: 15]
      _html_html0.setParent((javax.servlet.jsp.tagext.Tag)null); //[ /menuInicio.jsp; Line: 15]
      _activeTag = _html_html0; //[ /menuInicio.jsp; Line: 15]
      _int0 = _html_html0.doStartTag(); //[ /menuInicio.jsp; Line: 15]
      if (_int0 == BodyTag.EVAL_BODY_BUFFERED) { //[ /menuInicio.jsp; Line: 15]
        throw new JspTagException("Since tag class org.apache.struts.taglib.html.HtmlTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /menuInicio.jsp; Line: 15]
      } //[ /menuInicio.jsp; Line: 15]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 15]
      if (_int0 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /menuInicio.jsp; Line: 15]
        do { //[ /menuInicio.jsp; Line: 15]
          /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /menuInicio.jsp; Line: 15]
          /*** declare & sync NESTED TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 15]
          _writeText(response, out, _wl_block1, _wl_block1Bytes);
          //^%$__TAG_CODEGEN : begin form custom tag block... //[ /menuInicio.jsp; Line: 43]
          /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 43]
          if (_html_form0 == null) _html_form0 = new org.apache.struts.taglib.html.FormTag(); //[ /menuInicio.jsp; Line: 43]
          _html_form0.setPageContext(pageContext); //[ /menuInicio.jsp; Line: 43]
          _html_form0.setParent((javax.servlet.jsp.tagext.Tag)_html_html0); //[ /menuInicio.jsp; Line: 43]
          _activeTag = _html_form0; //[ /menuInicio.jsp; Line: 43]
          _html_form0.setAction(weblogic.utils.StringUtils.valueOf("/menuAction.do")); //[ /menuInicio.jsp; Line: 43]
          _html_form0.setMethod(weblogic.utils.StringUtils.valueOf("post")); //[ /menuInicio.jsp; Line: 43]
          _int1 = _html_form0.doStartTag(); //[ /menuInicio.jsp; Line: 43]
          if (_int1 == BodyTag.EVAL_BODY_BUFFERED) { //[ /menuInicio.jsp; Line: 43]
            throw new JspTagException("Since tag class org.apache.struts.taglib.html.FormTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /menuInicio.jsp; Line: 43]
          } //[ /menuInicio.jsp; Line: 43]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 43]
          if (_int1 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /menuInicio.jsp; Line: 43]
            do { //[ /menuInicio.jsp; Line: 43]
              /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /menuInicio.jsp; Line: 43]
              /*** declare & sync NESTED TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 43]
              _writeText(response, out, _wl_block2, _wl_block2Bytes);
              //^%$__TAG_CODEGEN : begin hidden custom tag block... //[ /menuInicio.jsp; Line: 46]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 46]
              if (_html_hidden0 == null) _html_hidden0 = new org.apache.struts.taglib.html.HiddenTag(); //[ /menuInicio.jsp; Line: 46]
              _html_hidden0.setPageContext(pageContext); //[ /menuInicio.jsp; Line: 46]
              _html_hidden0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /menuInicio.jsp; Line: 46]
              _activeTag = _html_hidden0; //[ /menuInicio.jsp; Line: 46]
              _html_hidden0.setProperty(weblogic.utils.StringUtils.valueOf("option")); //[ /menuInicio.jsp; Line: 46]
              _html_hidden0.setValue(weblogic.utils.StringUtils.valueOf("-1")); //[ /menuInicio.jsp; Line: 46]
              _int2 = _html_hidden0.doStartTag(); //[ /menuInicio.jsp; Line: 46]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 46]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_hidden0, _int2, true); //[ /menuInicio.jsp; Line: 46]
              if (_html_hidden0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_hidden0); return; } //[ /menuInicio.jsp; Line: 46]
              _activeTag = _html_hidden0.getParent(); _html_hidden0.release(); //[ /menuInicio.jsp; Line: 46]
              //end hidden custom tag.... //[ /menuInicio.jsp; Line: 46]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 46]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 46]
              _writeText(response, out, _wl_block3, _wl_block3Bytes);
              //^%$__TAG_CODEGEN : begin button custom tag block... //[ /menuInicio.jsp; Line: 57]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 57]
              if (_html_button0 == null) _html_button0 = new org.apache.struts.taglib.html.ButtonTag(); //[ /menuInicio.jsp; Line: 57]
              _html_button0.setPageContext(pageContext); //[ /menuInicio.jsp; Line: 57]
              _html_button0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /menuInicio.jsp; Line: 57]
              _activeTag = _html_button0; //[ /menuInicio.jsp; Line: 57]
              _html_button0.setProperty(weblogic.utils.StringUtils.valueOf("boton")); //[ /menuInicio.jsp; Line: 57]
              _html_button0.setValue(weblogic.utils.StringUtils.valueOf("reporting")); //[ /menuInicio.jsp; Line: 57]
              _html_button0.setOnclick(weblogic.utils.StringUtils.valueOf("javascript:pushButton(\'1\')")); //[ /menuInicio.jsp; Line: 57]
              _int2 = _html_button0.doStartTag(); //[ /menuInicio.jsp; Line: 57]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 57]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_button0, _int2, true); //[ /menuInicio.jsp; Line: 57]
              if (_html_button0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_button0); return; } //[ /menuInicio.jsp; Line: 57]
              _activeTag = _html_button0.getParent(); _html_button0.release(); //[ /menuInicio.jsp; Line: 57]
              //end button custom tag.... //[ /menuInicio.jsp; Line: 57]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 57]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 57]
              _writeText(response, out, _wl_block4, _wl_block4Bytes);
              //^%$__TAG_CODEGEN : begin button custom tag block... //[ /menuInicio.jsp; Line: 70]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 70]
              if (_html_button0 == null) _html_button0 = new org.apache.struts.taglib.html.ButtonTag(); //[ /menuInicio.jsp; Line: 70]
              _html_button0.setPageContext(pageContext); //[ /menuInicio.jsp; Line: 70]
              _html_button0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /menuInicio.jsp; Line: 70]
              _activeTag = _html_button0; //[ /menuInicio.jsp; Line: 70]
              _html_button0.setProperty(weblogic.utils.StringUtils.valueOf("boton")); //[ /menuInicio.jsp; Line: 70]
              _html_button0.setValue(weblogic.utils.StringUtils.valueOf("historico")); //[ /menuInicio.jsp; Line: 70]
              _html_button0.setOnclick(weblogic.utils.StringUtils.valueOf("javascript:pushButton(\'2\')")); //[ /menuInicio.jsp; Line: 70]
              _int2 = _html_button0.doStartTag(); //[ /menuInicio.jsp; Line: 70]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 70]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_button0, _int2, true); //[ /menuInicio.jsp; Line: 70]
              if (_html_button0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_button0); return; } //[ /menuInicio.jsp; Line: 70]
              _activeTag = _html_button0.getParent(); _html_button0.release(); //[ /menuInicio.jsp; Line: 70]
              //end button custom tag.... //[ /menuInicio.jsp; Line: 70]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 70]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 70]
              _writeText(response, out, _wl_block5, _wl_block5Bytes);
              //^%$__TAG_CODEGEN  //[ /menuInicio.jsp; Line: 82]
            } while (_html_form0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /menuInicio.jsp; Line: 82]
          } // end !SKIP_BODY //[ /menuInicio.jsp; Line: 82]
          if (_html_form0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_form0); return; } //[ /menuInicio.jsp; Line: 82]
          _activeTag = _html_form0.getParent(); _html_form0.release(); //[ /menuInicio.jsp; Line: 82]
          //end form custom tag.... //[ /menuInicio.jsp; Line: 82]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 82]
          /*** declare & sync AT_END TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 82]
          _writeText(response, out, _wl_block6, _wl_block6Bytes);
          //^%$__TAG_CODEGEN  //[ /menuInicio.jsp; Line: 95]
        } while (_html_html0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /menuInicio.jsp; Line: 95]
      } // end !SKIP_BODY //[ /menuInicio.jsp; Line: 95]
      if (_html_html0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_html0); return; } //[ /menuInicio.jsp; Line: 95]
      _activeTag = _html_html0.getParent(); _html_html0.release(); //[ /menuInicio.jsp; Line: 95]
      //end html custom tag.... //[ /menuInicio.jsp; Line: 95]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 95]
      /*** declare & sync AT_END TagExtra Vars here ***/ //[ /menuInicio.jsp; Line: 95]
      _writeText(response, out, _wl_block7, _wl_block7Bytes);
    } catch (Throwable __ee) {
      while (out != null && out != _originalOut) out = pageContext.popBody();
      _releaseTags(_activeTag);
      pageContext.handlePageException((Throwable)__ee);
    }
    
    
    //before final close brace...
  }
  
  
}

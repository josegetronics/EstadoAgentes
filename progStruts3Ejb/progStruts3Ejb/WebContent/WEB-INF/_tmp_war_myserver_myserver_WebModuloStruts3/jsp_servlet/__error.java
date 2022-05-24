/* compiled from JSP: /error.jsp
*
* This code was automatically generated at 18:54:53 on 31-mar-2008
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
* This code was automatically generated at 18:54:53 on 31-mar-2008
* by weblogic.servlet.jsp.Jsp2Java -- do not edit.
*
* Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
*/
public final class __error
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
    if (sci.isResourceStale("/error.jsp", 1179766224789L, "8.1.6.0", "Europe/Paris")) return true;
    return false;
  }
  
  public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
    java.io.File f = null;
    long lastModWhenBuilt = 0L;
    if (sci.isResourceStale("/error.jsp", 1179766224789L, "8.1.6.0", "Europe/Paris")) return true;
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
  private final static String _wl_block0 = "\r\n\r\n\r\n\r\n\r\n";
  private final static byte[] _wl_block0Bytes = _getBytes(_wl_block0);
  private final static String _wl_block1 = "\r\n<body>\r\n\r\n<table border=\"0\" align=\"center\" width=\"300\">\r\n\r\n   <tr>\r\n     <td height=\"50\">&nbsp;\r\n     </td>\r\n   </tr>\r\n\r\n   <tr>\r\n    <td colspan=\"2\" align=\"center\">ERROR AL REALIZAR EL REPORTING</td>\r\n   </tr>\r\n\r\n   <tr>\r\n     <td height=\"20\">&nbsp;\r\n     </td>\r\n   </tr>\r\n\r\n   <tr>\r\n    <td>\r\n      <table cellpadding=\"1\" cellspacing=\"0\" border=\"0\" bgcolor=\"#FFFFFF\" style=\"border-collapse:collapse\" align=\"center\" width=\"300\">\r\n\r\n        <tr>\r\n          <td colspan=\"2\"  height=\"15\" />\r\n        </tr>\r\n\r\n        <tr>\r\n          <td>\r\n            <table border=\"1\" align=\"center\" cellspacing=\"3\" width=\"100%\" bgcolor=\"#FFFFFF\" style=\"border-collapse:collapse\" >\r\n\r\n                <tr>\r\n                  <td height=\"15\"/>\r\n                  \t&nbsp;&nbsp;";
  private final static byte[] _wl_block1Bytes = _getBytes(_wl_block1);
  private final static String _wl_block2 = "&nbsp;\r\n                  </td>\r\n                </tr>\r\n\r\n            </table>\r\n          </td>\r\n        </tr>\r\n\r\n        <tr>\r\n          <td colspan=\"2\"  height=\"15\" />\r\n        </tr>\r\n\r\n        <tr>\r\n          <td colspan=\"2\"  height=\"15\" align=\"center\">\r\n            <input type=\"button\" name=\"volver\" onclick=\"javaScript:document.location.href=\'";
  private final static byte[] _wl_block2Bytes = _getBytes(_wl_block2);
  private final static String _wl_block3 = "\'\" value=\"Volver\">\r\n          </td>\r\n        </tr>\r\n\r\n\r\n        <tr>\r\n          <td colspan=\"2\"  height=\"15\" />\r\n        </tr>\r\n\r\n      </table>\r\n    </td>\r\n   </tr>\r\n</table>\r\n\r\n\r\n</body>\r\n";
  private final static byte[] _wl_block3Bytes = _getBytes(_wl_block3);
  private final static String _wl_block4 = "\r\n";
  private final static byte[] _wl_block4Bytes = _getBytes(_wl_block4);
  
  
  public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException 
  {  
    
    // declare and set well-known variables:
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.ServletContext application = config.getServletContext();
    javax.servlet.jsp.tagext.Tag _activeTag = null;
    // variables for Tag extension protocol
    int _int0 = 0, _int1 = 0;
    org.apache.struts.taglib.bean.WriteTag _bean_write0 = null;
    org.apache.struts.taglib.html.HtmlTag _html_html0 = null;
    
    Object page = this;
    javax.servlet.jsp.JspWriter out;
    javax.servlet.jsp.PageContext pageContext =
    javax.servlet.jsp.JspFactory.getDefaultFactory().getPageContext(this, request, response, null, true, 8192, true);
    
    out = pageContext.getOut();
    JspWriter _originalOut = out;
    
    javax.servlet.http.HttpSession session = request.getSession(true);
    
    
    
    try { // error page try block
      
      _writeText(response, out, _wl_block0, _wl_block0Bytes);
      //^%$__TAG_CODEGEN : begin html custom tag block... //[ /error.jsp; Line: 6]
      /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 6]
      if (_html_html0 == null) _html_html0 = new org.apache.struts.taglib.html.HtmlTag(); //[ /error.jsp; Line: 6]
      _html_html0.setPageContext(pageContext); //[ /error.jsp; Line: 6]
      _html_html0.setParent((javax.servlet.jsp.tagext.Tag)null); //[ /error.jsp; Line: 6]
      _activeTag = _html_html0; //[ /error.jsp; Line: 6]
      _int0 = _html_html0.doStartTag(); //[ /error.jsp; Line: 6]
      if (_int0 == BodyTag.EVAL_BODY_BUFFERED) { //[ /error.jsp; Line: 6]
        throw new JspTagException("Since tag class org.apache.struts.taglib.html.HtmlTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /error.jsp; Line: 6]
      } //[ /error.jsp; Line: 6]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 6]
      if (_int0 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /error.jsp; Line: 6]
        do { //[ /error.jsp; Line: 6]
          /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /error.jsp; Line: 6]
          /*** declare & sync NESTED TagExtra Vars here ***/ //[ /error.jsp; Line: 6]
          _writeText(response, out, _wl_block1, _wl_block1Bytes);
          //^%$__TAG_CODEGEN : begin write custom tag block... //[ /error.jsp; Line: 39]
          /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 39]
          if (_bean_write0 == null) _bean_write0 = new org.apache.struts.taglib.bean.WriteTag(); //[ /error.jsp; Line: 39]
          _bean_write0.setPageContext(pageContext); //[ /error.jsp; Line: 39]
          _bean_write0.setParent((javax.servlet.jsp.tagext.Tag)_html_html0); //[ /error.jsp; Line: 39]
          _activeTag = _bean_write0; //[ /error.jsp; Line: 39]
          _bean_write0.setName(weblogic.utils.StringUtils.valueOf("PageBean")); //[ /error.jsp; Line: 39]
          _bean_write0.setProperty(weblogic.utils.StringUtils.valueOf("descripcionError")); //[ /error.jsp; Line: 39]
          _int1 = _bean_write0.doStartTag(); //[ /error.jsp; Line: 39]
          if (_int1 == BodyTag.EVAL_BODY_BUFFERED) { //[ /error.jsp; Line: 39]
            throw new JspTagException("Since tag class org.apache.struts.taglib.bean.WriteTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /error.jsp; Line: 39]
          } //[ /error.jsp; Line: 39]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 39]
          _int1 = _bean_write0.doEndTag(); //[ /error.jsp; Line: 39]
          if (_int1 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_bean_write0); return; } //[ /error.jsp; Line: 39]
          _activeTag = _bean_write0.getParent(); _bean_write0.release(); //[ /error.jsp; Line: 39]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 39]
          /*** declare & sync AT_END TagExtra Vars here ***/ //[ /error.jsp; Line: 39]
          _writeText(response, out, _wl_block2, _wl_block2Bytes);
          //^%$__TAG_CODEGEN : begin write custom tag block... //[ /error.jsp; Line: 53]
          /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 53]
          if (_bean_write0 == null) _bean_write0 = new org.apache.struts.taglib.bean.WriteTag(); //[ /error.jsp; Line: 53]
          _bean_write0.setPageContext(pageContext); //[ /error.jsp; Line: 53]
          _bean_write0.setParent((javax.servlet.jsp.tagext.Tag)_html_html0); //[ /error.jsp; Line: 53]
          _activeTag = _bean_write0; //[ /error.jsp; Line: 53]
          _bean_write0.setName(weblogic.utils.StringUtils.valueOf("PageBean")); //[ /error.jsp; Line: 53]
          _bean_write0.setProperty(weblogic.utils.StringUtils.valueOf("enlace")); //[ /error.jsp; Line: 53]
          _int1 = _bean_write0.doStartTag(); //[ /error.jsp; Line: 53]
          if (_int1 == BodyTag.EVAL_BODY_BUFFERED) { //[ /error.jsp; Line: 53]
            throw new JspTagException("Since tag class org.apache.struts.taglib.bean.WriteTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /error.jsp; Line: 53]
          } //[ /error.jsp; Line: 53]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 53]
          _int1 = _bean_write0.doEndTag(); //[ /error.jsp; Line: 53]
          if (_int1 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_bean_write0); return; } //[ /error.jsp; Line: 53]
          _activeTag = _bean_write0.getParent(); _bean_write0.release(); //[ /error.jsp; Line: 53]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 53]
          /*** declare & sync AT_END TagExtra Vars here ***/ //[ /error.jsp; Line: 53]
          _writeText(response, out, _wl_block3, _wl_block3Bytes);
          //^%$__TAG_CODEGEN  //[ /error.jsp; Line: 69]
        } while (_html_html0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /error.jsp; Line: 69]
      } // end !SKIP_BODY //[ /error.jsp; Line: 69]
      if (_html_html0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_html0); return; } //[ /error.jsp; Line: 69]
      _activeTag = _html_html0.getParent(); _html_html0.release(); //[ /error.jsp; Line: 69]
      //end html custom tag.... //[ /error.jsp; Line: 69]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /error.jsp; Line: 69]
      /*** declare & sync AT_END TagExtra Vars here ***/ //[ /error.jsp; Line: 69]
      _writeText(response, out, _wl_block4, _wl_block4Bytes);
    } catch (Throwable __ee) {
      while (out != null && out != _originalOut) out = pageContext.popBody();
      _releaseTags(_activeTag);
      pageContext.handlePageException((Throwable)__ee);
    }
    
    
    //before final close brace...
  }
  
  
}

/* compiled from JSP: /resultado2.jsp
*
* This code was automatically generated at 17:45:58 on 21-may-2007
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
* This code was automatically generated at 17:45:58 on 21-may-2007
* by weblogic.servlet.jsp.Jsp2Java -- do not edit.
*
* Copyright (c) 2007 by BEA Systems, Inc. All Rights Reserved.
*/
public final class __resultado2
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
    if (sci.isResourceStale("/resultado2.jsp", 1165221285371L, "8.1.6.0", "Europe/Paris")) return true;
    return false;
  }
  
  public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
    java.io.File f = null;
    long lastModWhenBuilt = 0L;
    if (sci.isResourceStale("/resultado2.jsp", 1165221285371L, "8.1.6.0", "Europe/Paris")) return true;
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
  private final static String _wl_block1 = "\r\n<HEAD>\r\n<TITLE>  Fail  </TITLE>\r\n\r\n";
  private final static byte[] _wl_block1Bytes = _getBytes(_wl_block1);
  private final static String _wl_block2 = "\r\n</HEAD>\r\n<BODY>\r\n\r\n<h3>FAIL</h3>\r\n\r\n";
  private final static byte[] _wl_block2Bytes = _getBytes(_wl_block2);
  private final static String _wl_block3 = "\r\n\r\n    <TABLE cellSpacing=0 cellPadding=5 width=\"100%\" align=center border=0>\r\n        <TBODY>\r\n          <TR align=right>\r\n            <TD align=\"center\">\r\n            \t";
  private final static byte[] _wl_block3Bytes = _getBytes(_wl_block3);
  private final static String _wl_block4 = "\r\n            </TD>\r\n          </TR>\r\n        </TBODY>\r\n    </TABLE>\r\n\r\n</BODY>\r\n";
  private final static byte[] _wl_block4Bytes = _getBytes(_wl_block4);
  private final static String _wl_block5 = "\r\n";
  private final static byte[] _wl_block5Bytes = _getBytes(_wl_block5);
  
  
  public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException 
  {  
    
    // declare and set well-known variables:
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.ServletContext application = config.getServletContext();
    javax.servlet.jsp.tagext.Tag _activeTag = null;
    // variables for Tag extension protocol
    int _int0 = 0, _int1 = 0;
    org.apache.struts.taglib.html.BaseTag _html_base0 = null;
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
      //^%$__TAG_CODEGEN : begin html custom tag block... //[ /resultado2.jsp; Line: 6]
      /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 6]
      if (_html_html0 == null) _html_html0 = new org.apache.struts.taglib.html.HtmlTag(); //[ /resultado2.jsp; Line: 6]
      _html_html0.setPageContext(pageContext); //[ /resultado2.jsp; Line: 6]
      _html_html0.setParent((javax.servlet.jsp.tagext.Tag)null); //[ /resultado2.jsp; Line: 6]
      _activeTag = _html_html0; //[ /resultado2.jsp; Line: 6]
      _html_html0.setLocale(Boolean.valueOf(weblogic.utils.StringUtils.valueOf("true")).booleanValue()); //[ /resultado2.jsp; Line: 6]
      _int0 = _html_html0.doStartTag(); //[ /resultado2.jsp; Line: 6]
      if (_int0 == BodyTag.EVAL_BODY_BUFFERED) { //[ /resultado2.jsp; Line: 6]
        throw new JspTagException("Since tag class org.apache.struts.taglib.html.HtmlTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /resultado2.jsp; Line: 6]
      } //[ /resultado2.jsp; Line: 6]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 6]
      if (_int0 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /resultado2.jsp; Line: 6]
        do { //[ /resultado2.jsp; Line: 6]
          /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /resultado2.jsp; Line: 6]
          /*** declare & sync NESTED TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 6]
          _writeText(response, out, _wl_block1, _wl_block1Bytes);
          //^%$__TAG_CODEGEN : begin base custom tag block... //[ /resultado2.jsp; Line: 10]
          /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 10]
          if (_html_base0 == null) _html_base0 = new org.apache.struts.taglib.html.BaseTag(); //[ /resultado2.jsp; Line: 10]
          _html_base0.setPageContext(pageContext); //[ /resultado2.jsp; Line: 10]
          _html_base0.setParent((javax.servlet.jsp.tagext.Tag)_html_html0); //[ /resultado2.jsp; Line: 10]
          _activeTag = _html_base0; //[ /resultado2.jsp; Line: 10]
          _int1 = _html_base0.doStartTag(); //[ /resultado2.jsp; Line: 10]
          if (_int1 == BodyTag.EVAL_BODY_BUFFERED) { //[ /resultado2.jsp; Line: 10]
            throw new JspTagException("Since tag class org.apache.struts.taglib.html.BaseTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /resultado2.jsp; Line: 10]
          } //[ /resultado2.jsp; Line: 10]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 10]
          _int1 = _html_base0.doEndTag(); //[ /resultado2.jsp; Line: 10]
          if (_int1 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_base0); return; } //[ /resultado2.jsp; Line: 10]
          _activeTag = _html_base0.getParent(); _html_base0.release(); //[ /resultado2.jsp; Line: 10]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 10]
          /*** declare & sync AT_END TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 10]
          _writeText(response, out, _wl_block2, _wl_block2Bytes);
          //[ /resultado2.jsp; Line: 16]
          String mensaje = null; //[ /resultado2.jsp; Line: 17]
          mensaje = (String)request.getAttribute("Mensaje"); //[ /resultado2.jsp; Line: 18]
          request.removeAttribute("Mensaje"); //[ /resultado2.jsp; Line: 19]
          _writeText(response, out, _wl_block3, _wl_block3Bytes);
          out.print(String.valueOf(mensaje));  //[ /resultado2.jsp; Line: 26]
          _writeText(response, out, _wl_block4, _wl_block4Bytes);
          //^%$__TAG_CODEGEN  //[ /resultado2.jsp; Line: 33]
        } while (_html_html0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /resultado2.jsp; Line: 33]
      } // end !SKIP_BODY //[ /resultado2.jsp; Line: 33]
      if (_html_html0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_html0); return; } //[ /resultado2.jsp; Line: 33]
      _activeTag = _html_html0.getParent(); _html_html0.release(); //[ /resultado2.jsp; Line: 33]
      //end html custom tag.... //[ /resultado2.jsp; Line: 33]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 33]
      /*** declare & sync AT_END TagExtra Vars here ***/ //[ /resultado2.jsp; Line: 33]
      _writeText(response, out, _wl_block5, _wl_block5Bytes);
    } catch (Throwable __ee) {
      while (out != null && out != _originalOut) out = pageContext.popBody();
      _releaseTags(_activeTag);
      pageContext.handlePageException((Throwable)__ee);
    }
    
    
    //before final close brace...
  }
  
  
}

/* compiled from JSP: /resultado_final.jsp
*
* This code was automatically generated at 9:36:45 on 04-dic-2006
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
* This code was automatically generated at 9:36:45 on 04-dic-2006
* by weblogic.servlet.jsp.Jsp2Java -- do not edit.
*
* Copyright (c) 2006 by BEA Systems, Inc. All Rights Reserved.
*/
public final class __resultado_final
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
    if (sci.isResourceStale("/resultado_final.jsp", 1165221338525L, "8.1.6.0", "Europe/Paris")) return true;
    return false;
  }
  
  public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
    java.io.File f = null;
    long lastModWhenBuilt = 0L;
    if (sci.isResourceStale("/resultado_final.jsp", 1165221338525L, "8.1.6.0", "Europe/Paris")) return true;
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
  private final static String _wl_block0 = "\r\n\r\n\r\n\r\n\r\n\r\n";
  private final static byte[] _wl_block0Bytes = _getBytes(_wl_block0);
  private final static String _wl_block1 = "\r\n<HEAD>\r\n<TITLE>   Sucess    </TITLE>\r\n\r\n</HEAD>\r\n<BODY>\r\n\r\n<center>\r\n\r\n<h3>Final</h3>\r\n\r\n";
  private final static byte[] _wl_block1Bytes = _getBytes(_wl_block1);
  private final static String _wl_block2 = "\r\n\r\n\r\n\r\n    <TABLE cellSpacing=0 cellPadding=5 width=\"100%\" align=center border=0>\r\n        <TBODY>\r\n          <TR align=right>\r\n            <TD align=\"center\">\r\n            \t";
  private final static byte[] _wl_block2Bytes = _getBytes(_wl_block2);
  private final static String _wl_block3 = "\r\n            </TD>\r\n          </TR>\r\n        </TBODY>\r\n    </TABLE>\r\n\r\n\r\n</BODY>\r\n";
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
    int _int0 = 0;
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
      //^%$__TAG_CODEGEN : begin html custom tag block... //[ /resultado_final.jsp; Line: 7]
      /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /resultado_final.jsp; Line: 7]
      if (_html_html0 == null) _html_html0 = new org.apache.struts.taglib.html.HtmlTag(); //[ /resultado_final.jsp; Line: 7]
      _html_html0.setPageContext(pageContext); //[ /resultado_final.jsp; Line: 7]
      _html_html0.setParent((javax.servlet.jsp.tagext.Tag)null); //[ /resultado_final.jsp; Line: 7]
      _activeTag = _html_html0; //[ /resultado_final.jsp; Line: 7]
      _int0 = _html_html0.doStartTag(); //[ /resultado_final.jsp; Line: 7]
      if (_int0 == BodyTag.EVAL_BODY_BUFFERED) { //[ /resultado_final.jsp; Line: 7]
        throw new JspTagException("Since tag class org.apache.struts.taglib.html.HtmlTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /resultado_final.jsp; Line: 7]
      } //[ /resultado_final.jsp; Line: 7]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /resultado_final.jsp; Line: 7]
      if (_int0 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /resultado_final.jsp; Line: 7]
        do { //[ /resultado_final.jsp; Line: 7]
          /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /resultado_final.jsp; Line: 7]
          /*** declare & sync NESTED TagExtra Vars here ***/ //[ /resultado_final.jsp; Line: 7]
          _writeText(response, out, _wl_block1, _wl_block1Bytes);
          //[ /resultado_final.jsp; Line: 18]
          String mensaje = null; //[ /resultado_final.jsp; Line: 19]
          mensaje = (String)request.getAttribute("Mensaje"); //[ /resultado_final.jsp; Line: 20]
          request.removeAttribute("Mensaje"); //[ /resultado_final.jsp; Line: 21]
          _writeText(response, out, _wl_block2, _wl_block2Bytes);
          out.print(String.valueOf(mensaje));  //[ /resultado_final.jsp; Line: 30]
          _writeText(response, out, _wl_block3, _wl_block3Bytes);
          //^%$__TAG_CODEGEN  //[ /resultado_final.jsp; Line: 38]
        } while (_html_html0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /resultado_final.jsp; Line: 38]
      } // end !SKIP_BODY //[ /resultado_final.jsp; Line: 38]
      if (_html_html0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_html0); return; } //[ /resultado_final.jsp; Line: 38]
      _activeTag = _html_html0.getParent(); _html_html0.release(); //[ /resultado_final.jsp; Line: 38]
      //end html custom tag.... //[ /resultado_final.jsp; Line: 38]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /resultado_final.jsp; Line: 38]
      /*** declare & sync AT_END TagExtra Vars here ***/ //[ /resultado_final.jsp; Line: 38]
      _writeText(response, out, _wl_block4, _wl_block4Bytes);
    } catch (Throwable __ee) {
      while (out != null && out != _originalOut) out = pageContext.popBody();
      _releaseTags(_activeTag);
      pageContext.handlePageException((Throwable)__ee);
    }
    
    
    //before final close brace...
  }
  
  
}

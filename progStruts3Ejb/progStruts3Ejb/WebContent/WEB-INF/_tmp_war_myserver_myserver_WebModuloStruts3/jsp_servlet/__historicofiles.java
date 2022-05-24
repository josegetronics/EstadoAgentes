/* compiled from JSP: /historicoFiles.jsp
*
* This code was automatically generated at 13:41:32 on 01-abr-2008
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
* This code was automatically generated at 13:41:32 on 01-abr-2008
* by weblogic.servlet.jsp.Jsp2Java -- do not edit.
*
* Copyright (c) 2008 by BEA Systems, Inc. All Rights Reserved.
*/
public final class __historicofiles
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
    if (sci.isResourceStale("/historicoFiles.jsp", 1207050086481L, "8.1.6.0", "Europe/Paris")) return true;
    return false;
  }
  
  public static boolean _staticIsStale(weblogic.servlet.jsp.StaleChecker sci) {
    java.io.File f = null;
    long lastModWhenBuilt = 0L;
    if (sci.isResourceStale("/historicoFiles.jsp", 1207050086481L, "8.1.6.0", "Europe/Paris")) return true;
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
  private final static String _wl_block0 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n<script>\r\nfunction pushButton(valor){\r\n\tdocument.historicoForm.nameFile.value = valor;\r\n        document.forms[\"historicoForm\"].submit();\r\n}\r\n</script>\r\n\r\n";
  private final static byte[] _wl_block0Bytes = _getBytes(_wl_block0);
  private final static String _wl_block1 = "\r\n\r\n<body>\r\n\r\n<table border=\"0\" align=\"center\" width=\"600\">\r\n\r\n   <tr>\r\n     <td height=\"50\">&nbsp;\r\n     </td>\r\n   </tr>\r\n\r\n   <tr>\r\n    <td colspan=\"2\" align=\"center\"></b>HISTORICO</b></td>\r\n   </tr>\r\n\r\n   <tr>\r\n     <td height=\"20\">&nbsp;\r\n     </td>\r\n   </tr>\r\n\r\n   <tr>\r\n    <td>\r\n      <table cellpadding=\"1\" cellspacing=\"0\" border=\"1\" bgcolor=\"#FFFFFF\" style=\"border-collapse:collapse\" align=\"center\" width=\"450\">\r\n        <tr>\r\n          <td>\r\n            <table border=\"1\" align=\"center\" cellspacing=\"3\" width=\"100%\">\r\n\r\n              <!-- -->\r\n              ";
  private final static byte[] _wl_block1Bytes = _getBytes(_wl_block1);
  private final static String _wl_block2 = "\r\n              <!-- -->\r\n\r\n\t\t";
  private final static byte[] _wl_block2Bytes = _getBytes(_wl_block2);
  private final static String _wl_block3 = "\r\n\r\n                <tr>\r\n                  <td width=\"50%\"></td>\r\n                  <td height=\"15\" width=\"50%\"></td>\r\n  \t\t</tr>\r\n\r\n                <tr>\r\n                  <td width=\"50%\"><b>YEAR-MOUNTH</b></td>\r\n                  <td height=\"15\" width=\"50%\"><b>REPORTING</b></td>\r\n  \t\t</tr>\r\n\r\n                ";
  private final static byte[] _wl_block3Bytes = _getBytes(_wl_block3);
  private final static String _wl_block4 = "\r\n\r\n              <!-- -->\r\n              ";
  private final static byte[] _wl_block4Bytes = _getBytes(_wl_block4);
  private final static String _wl_block5 = "\r\n              <!-- -->\r\n\r\n            </table>\r\n          </td>\r\n        </tr>\r\n      </table>\r\n    </td>\r\n  </tr>\r\n</table>\r\n\r\n\r\n</body>\r\n";
  private final static byte[] _wl_block5Bytes = _getBytes(_wl_block5);
  private final static String _wl_block6 = "\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n";
  private final static byte[] _wl_block6Bytes = _getBytes(_wl_block6);
  
  
  public void _jspService(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws java.io.IOException, javax.servlet.ServletException 
  {  
    
    // declare and set well-known variables:
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.ServletContext application = config.getServletContext();
    javax.servlet.jsp.tagext.Tag _activeTag = null;
    // variables for Tag extension protocol
    int _int0 = 0, _int1 = 0, _int2 = 0, _int3 = 0;
    org.apache.struts.taglib.bean.WriteTag _bean_write0 = null;
    org.apache.struts.taglib.logic.IterateTag _logic_iterate0 = null;
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
      //^%$__TAG_CODEGEN : begin html custom tag block... //[ /historicoFiles.jsp; Line: 15]
      /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 15]
      if (_html_html0 == null) _html_html0 = new org.apache.struts.taglib.html.HtmlTag(); //[ /historicoFiles.jsp; Line: 15]
      _html_html0.setPageContext(pageContext); //[ /historicoFiles.jsp; Line: 15]
      _html_html0.setParent((javax.servlet.jsp.tagext.Tag)null); //[ /historicoFiles.jsp; Line: 15]
      _activeTag = _html_html0; //[ /historicoFiles.jsp; Line: 15]
      _int0 = _html_html0.doStartTag(); //[ /historicoFiles.jsp; Line: 15]
      if (_int0 == BodyTag.EVAL_BODY_BUFFERED) { //[ /historicoFiles.jsp; Line: 15]
        throw new JspTagException("Since tag class org.apache.struts.taglib.html.HtmlTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /historicoFiles.jsp; Line: 15]
      } //[ /historicoFiles.jsp; Line: 15]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 15]
      if (_int0 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /historicoFiles.jsp; Line: 15]
        do { //[ /historicoFiles.jsp; Line: 15]
          /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /historicoFiles.jsp; Line: 15]
          /*** declare & sync NESTED TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 15]
          _writeText(response, out, _wl_block1, _wl_block1Bytes);
          //^%$__TAG_CODEGEN : begin form custom tag block... //[ /historicoFiles.jsp; Line: 43]
          /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 43]
          if (_html_form0 == null) _html_form0 = new org.apache.struts.taglib.html.FormTag(); //[ /historicoFiles.jsp; Line: 43]
          _html_form0.setPageContext(pageContext); //[ /historicoFiles.jsp; Line: 43]
          _html_form0.setParent((javax.servlet.jsp.tagext.Tag)_html_html0); //[ /historicoFiles.jsp; Line: 43]
          _activeTag = _html_form0; //[ /historicoFiles.jsp; Line: 43]
          _html_form0.setAction(weblogic.utils.StringUtils.valueOf("/descargaAction.do")); //[ /historicoFiles.jsp; Line: 43]
          _html_form0.setMethod(weblogic.utils.StringUtils.valueOf("post")); //[ /historicoFiles.jsp; Line: 43]
          _int1 = _html_form0.doStartTag(); //[ /historicoFiles.jsp; Line: 43]
          if (_int1 == BodyTag.EVAL_BODY_BUFFERED) { //[ /historicoFiles.jsp; Line: 43]
            throw new JspTagException("Since tag class org.apache.struts.taglib.html.FormTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /historicoFiles.jsp; Line: 43]
          } //[ /historicoFiles.jsp; Line: 43]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 43]
          if (_int1 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /historicoFiles.jsp; Line: 43]
            do { //[ /historicoFiles.jsp; Line: 43]
              /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /historicoFiles.jsp; Line: 43]
              /*** declare & sync NESTED TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 43]
              _writeText(response, out, _wl_block2, _wl_block2Bytes);
              //^%$__TAG_CODEGEN : begin hidden custom tag block... //[ /historicoFiles.jsp; Line: 46]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 46]
              if (_html_hidden0 == null) _html_hidden0 = new org.apache.struts.taglib.html.HiddenTag(); //[ /historicoFiles.jsp; Line: 46]
              _html_hidden0.setPageContext(pageContext); //[ /historicoFiles.jsp; Line: 46]
              _html_hidden0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /historicoFiles.jsp; Line: 46]
              _activeTag = _html_hidden0; //[ /historicoFiles.jsp; Line: 46]
              _html_hidden0.setProperty(weblogic.utils.StringUtils.valueOf("nameFile")); //[ /historicoFiles.jsp; Line: 46]
              _html_hidden0.setValue(weblogic.utils.StringUtils.valueOf("")); //[ /historicoFiles.jsp; Line: 46]
              _int2 = _html_hidden0.doStartTag(); //[ /historicoFiles.jsp; Line: 46]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 46]
              weblogic.servlet.jsp.StandardTagLib.fakeEmptyBodyTag(pageContext, _html_hidden0, _int2, true); //[ /historicoFiles.jsp; Line: 46]
              if (_html_hidden0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_hidden0); return; } //[ /historicoFiles.jsp; Line: 46]
              _activeTag = _html_hidden0.getParent(); _html_hidden0.release(); //[ /historicoFiles.jsp; Line: 46]
              //end hidden custom tag.... //[ /historicoFiles.jsp; Line: 46]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 46]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 46]
              _writeText(response, out, _wl_block3, _wl_block3Bytes);
              //^%$__TAG_CODEGEN : begin iterate custom tag block... //[ /historicoFiles.jsp; Line: 58]
              /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 58]
              if (_logic_iterate0 == null) _logic_iterate0 = new org.apache.struts.taglib.logic.IterateTag(); //[ /historicoFiles.jsp; Line: 58]
              _logic_iterate0.setPageContext(pageContext); //[ /historicoFiles.jsp; Line: 58]
              _logic_iterate0.setParent((javax.servlet.jsp.tagext.Tag)_html_form0); //[ /historicoFiles.jsp; Line: 58]
              _activeTag = _logic_iterate0; //[ /historicoFiles.jsp; Line: 58]
              _logic_iterate0.setId(weblogic.utils.StringUtils.valueOf("registro")); //[ /historicoFiles.jsp; Line: 58]
              _logic_iterate0.setProperty(weblogic.utils.StringUtils.valueOf("fileNames")); //[ /historicoFiles.jsp; Line: 58]
              _logic_iterate0.setName(weblogic.utils.StringUtils.valueOf("PageBean")); //[ /historicoFiles.jsp; Line: 58]
              _logic_iterate0.setType(weblogic.utils.StringUtils.valueOf("snsadmin.controller.bean.RegistroBean")); //[ /historicoFiles.jsp; Line: 58]
              _int2 = _logic_iterate0.doStartTag(); //[ /historicoFiles.jsp; Line: 58]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 58]
              if (_int2 != Tag.SKIP_BODY) { // begin !SKIP_BODY... //[ /historicoFiles.jsp; Line: 58]
                if (_int2 == BodyTag.EVAL_BODY_BUFFERED) { //[ /historicoFiles.jsp; Line: 58]
                  out = pageContext.pushBody(); //[ /historicoFiles.jsp; Line: 58]
                  _logic_iterate0.setBodyContent((BodyContent)out); //[ /historicoFiles.jsp; Line: 58]
                  _logic_iterate0.doInitBody(); //[ /historicoFiles.jsp; Line: 58]
                } //[ /historicoFiles.jsp; Line: 58]
                try { // _logic_iterate0 popBody() try/finally.. //[ /historicoFiles.jsp; Line: 58]
                  do { //[ /historicoFiles.jsp; Line: 58]
                    /*** sync AT_BEGIN Vars after doInitBody ***/ //[ /historicoFiles.jsp; Line: 58]
                    /*** declare & sync NESTED TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 58]
                    snsadmin.controller.bean.RegistroBean registro = null; //[ /historicoFiles.jsp; Line: 58]
                    registro = (snsadmin.controller.bean.RegistroBean)pageContext.findAttribute("registro"); //[ /historicoFiles.jsp; Line: 58]
                    out.print("\r\n              \t\t<tr class=\"tablaValores\">\r\n\t\t\t    <td width=\"50%\"   class=\"valoresTabla\" valign=\"top\" >");
                    //^%$__TAG_CODEGEN : begin write custom tag block... //[ /historicoFiles.jsp; Line: 60]
                    /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 60]
                    if (_bean_write0 == null) _bean_write0 = new org.apache.struts.taglib.bean.WriteTag(); //[ /historicoFiles.jsp; Line: 60]
                    _bean_write0.setPageContext(pageContext); //[ /historicoFiles.jsp; Line: 60]
                    _bean_write0.setParent((javax.servlet.jsp.tagext.Tag)_logic_iterate0); //[ /historicoFiles.jsp; Line: 60]
                    _activeTag = _bean_write0; //[ /historicoFiles.jsp; Line: 60]
                    _bean_write0.setName(weblogic.utils.StringUtils.valueOf("registro")); //[ /historicoFiles.jsp; Line: 60]
                    _bean_write0.setProperty(weblogic.utils.StringUtils.valueOf("date")); //[ /historicoFiles.jsp; Line: 60]
                    _int3 = _bean_write0.doStartTag(); //[ /historicoFiles.jsp; Line: 60]
                    if (_int3 == BodyTag.EVAL_BODY_BUFFERED) { //[ /historicoFiles.jsp; Line: 60]
                      throw new JspTagException("Since tag class org.apache.struts.taglib.bean.WriteTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /historicoFiles.jsp; Line: 60]
                    } //[ /historicoFiles.jsp; Line: 60]
                    /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 60]
                    _int3 = _bean_write0.doEndTag(); //[ /historicoFiles.jsp; Line: 60]
                    if (_int3 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_bean_write0); return; } //[ /historicoFiles.jsp; Line: 60]
                    _activeTag = _bean_write0.getParent(); _bean_write0.release(); //[ /historicoFiles.jsp; Line: 60]
                    /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 60]
                    /*** declare & sync AT_END TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 60]
                    out.print("</td>\r\n                            <td width=\"50%\" class=\"valoresTabla\" valign=\"top\"  onclick=\"javascript:pushButton(\'");
                    //^%$__TAG_CODEGEN : begin write custom tag block... //[ /historicoFiles.jsp; Line: 61]
                    /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 61]
                    if (_bean_write0 == null) _bean_write0 = new org.apache.struts.taglib.bean.WriteTag(); //[ /historicoFiles.jsp; Line: 61]
                    _bean_write0.setPageContext(pageContext); //[ /historicoFiles.jsp; Line: 61]
                    _bean_write0.setParent((javax.servlet.jsp.tagext.Tag)_logic_iterate0); //[ /historicoFiles.jsp; Line: 61]
                    _activeTag = _bean_write0; //[ /historicoFiles.jsp; Line: 61]
                    _bean_write0.setName(weblogic.utils.StringUtils.valueOf("registro")); //[ /historicoFiles.jsp; Line: 61]
                    _bean_write0.setProperty(weblogic.utils.StringUtils.valueOf("codigo")); //[ /historicoFiles.jsp; Line: 61]
                    _int3 = _bean_write0.doStartTag(); //[ /historicoFiles.jsp; Line: 61]
                    if (_int3 == BodyTag.EVAL_BODY_BUFFERED) { //[ /historicoFiles.jsp; Line: 61]
                      throw new JspTagException("Since tag class org.apache.struts.taglib.bean.WriteTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /historicoFiles.jsp; Line: 61]
                    } //[ /historicoFiles.jsp; Line: 61]
                    /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 61]
                    _int3 = _bean_write0.doEndTag(); //[ /historicoFiles.jsp; Line: 61]
                    if (_int3 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_bean_write0); return; } //[ /historicoFiles.jsp; Line: 61]
                    _activeTag = _bean_write0.getParent(); _bean_write0.release(); //[ /historicoFiles.jsp; Line: 61]
                    /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 61]
                    /*** declare & sync AT_END TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 61]
                    out.print("\')\"><font color=\"blue\">");
                    //^%$__TAG_CODEGEN : begin write custom tag block... //[ /historicoFiles.jsp; Line: 61]
                    /*** declare AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 61]
                    if (_bean_write0 == null) _bean_write0 = new org.apache.struts.taglib.bean.WriteTag(); //[ /historicoFiles.jsp; Line: 61]
                    _bean_write0.setPageContext(pageContext); //[ /historicoFiles.jsp; Line: 61]
                    _bean_write0.setParent((javax.servlet.jsp.tagext.Tag)_logic_iterate0); //[ /historicoFiles.jsp; Line: 61]
                    _activeTag = _bean_write0; //[ /historicoFiles.jsp; Line: 61]
                    _bean_write0.setName(weblogic.utils.StringUtils.valueOf("registro")); //[ /historicoFiles.jsp; Line: 61]
                    _bean_write0.setProperty(weblogic.utils.StringUtils.valueOf("descripcion")); //[ /historicoFiles.jsp; Line: 61]
                    _int3 = _bean_write0.doStartTag(); //[ /historicoFiles.jsp; Line: 61]
                    if (_int3 == BodyTag.EVAL_BODY_BUFFERED) { //[ /historicoFiles.jsp; Line: 61]
                      throw new JspTagException("Since tag class org.apache.struts.taglib.bean.WriteTag does not implement BodyTag, it cannot return BodyTag.EVAL_BODY_BUFFERED"); //[ /historicoFiles.jsp; Line: 61]
                    } //[ /historicoFiles.jsp; Line: 61]
                    /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 61]
                    _int3 = _bean_write0.doEndTag(); //[ /historicoFiles.jsp; Line: 61]
                    if (_int3 == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_bean_write0); return; } //[ /historicoFiles.jsp; Line: 61]
                    _activeTag = _bean_write0.getParent(); _bean_write0.release(); //[ /historicoFiles.jsp; Line: 61]
                    /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 61]
                    /*** declare & sync AT_END TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 61]
                    out.print("</font></td>\r\n              \t\t</tr>\r\n                ");
                    //^%$__TAG_CODEGEN  //[ /historicoFiles.jsp; Line: 63]
                  } while (_logic_iterate0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /historicoFiles.jsp; Line: 63]
                } finally { // _logic_iterate0 popBody() finally... //[ /historicoFiles.jsp; Line: 63]
                  if (_int2 == BodyTag.EVAL_BODY_BUFFERED) out = pageContext.popBody(); //[ /historicoFiles.jsp; Line: 63]
                } //[ /historicoFiles.jsp; Line: 63]
              } // end !SKIP_BODY //[ /historicoFiles.jsp; Line: 63]
              if (_logic_iterate0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_logic_iterate0); return; } //[ /historicoFiles.jsp; Line: 63]
              _activeTag = _logic_iterate0.getParent(); _logic_iterate0.release(); //[ /historicoFiles.jsp; Line: 63]
              //end iterate custom tag.... //[ /historicoFiles.jsp; Line: 63]
              /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 63]
              /*** declare & sync AT_END TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 63]
              _writeText(response, out, _wl_block4, _wl_block4Bytes);
              //^%$__TAG_CODEGEN  //[ /historicoFiles.jsp; Line: 66]
            } while (_html_form0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /historicoFiles.jsp; Line: 66]
          } // end !SKIP_BODY //[ /historicoFiles.jsp; Line: 66]
          if (_html_form0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_form0); return; } //[ /historicoFiles.jsp; Line: 66]
          _activeTag = _html_form0.getParent(); _html_form0.release(); //[ /historicoFiles.jsp; Line: 66]
          //end form custom tag.... //[ /historicoFiles.jsp; Line: 66]
          /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 66]
          /*** declare & sync AT_END TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 66]
          _writeText(response, out, _wl_block5, _wl_block5Bytes);
          //^%$__TAG_CODEGEN  //[ /historicoFiles.jsp; Line: 79]
        } while (_html_html0.doAfterBody() == IterationTag.EVAL_BODY_AGAIN); //[ /historicoFiles.jsp; Line: 79]
      } // end !SKIP_BODY //[ /historicoFiles.jsp; Line: 79]
      if (_html_html0.doEndTag() == Tag.SKIP_PAGE) { _activeTag = null; _releaseTags(_html_html0); return; } //[ /historicoFiles.jsp; Line: 79]
      _activeTag = _html_html0.getParent(); _html_html0.release(); //[ /historicoFiles.jsp; Line: 79]
      //end html custom tag.... //[ /historicoFiles.jsp; Line: 79]
      /*** sync AT_BEGIN TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 79]
      /*** declare & sync AT_END TagExtra Vars here ***/ //[ /historicoFiles.jsp; Line: 79]
      _writeText(response, out, _wl_block6, _wl_block6Bytes);
    } catch (Throwable __ee) {
      while (out != null && out != _originalOut) out = pageContext.popBody();
      _releaseTags(_activeTag);
      pageContext.handlePageException((Throwable)__ee);
    }
    
    
    //before final close brace...
  }
  
  
}

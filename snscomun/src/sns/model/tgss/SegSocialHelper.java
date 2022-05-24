package sns.model.tgss;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

import sns.bd.AccesoBd;
import sns.exception.UsuarioSegSocialException;

public class SegSocialHelper {
  private static final String TABLA_SEGURIDAD_SOCIAL="SEGSOCIALFINAL";

  public SegSocialHelper() {
  }

  public SegSocialNafTitularBean findByNafDniLetras(String naf,String dni,String raizAp) throws
      Exception {
    AccesoBd bd=null;
    try {
      bd=new AccesoBd();
      return findByNafDniLetras(naf,dni,raizAp,bd);
    }catch (Exception e) {
      throw e;
    }finally  {
      if (bd!=null) {
        bd.cerrar();
      }
    }
  }

  public SegSocialNafTitularBean findByNafPasaporteLetras(String naf,String pasaporte,String raizAp) throws Exception {
    AccesoBd bd=null;
    try {
      bd=new AccesoBd();
      return findByNafPasaporteLetras(naf,pasaporte,raizAp,bd);
    }catch (Exception e) {
      throw e;
    }finally  {
      if (bd!=null) {
        bd.cerrar();
      }
    }
  }

  public SegSocialNafTitularBean findByPrimaryKey(String naf) throws Exception {
    AccesoBd bd=null;
    try {
      bd=new AccesoBd();
      return findByPrimaryKey(naf,bd);
    }catch (Exception e) {
      throw e;
    }finally  {
      if (bd!=null) {
        bd.cerrar();
      }
    }
  }

  public Collection findByDniLetras(String dni,String raizAp) throws Exception {
    AccesoBd bd=null;
    try {
      bd=new AccesoBd();
      return findByDniLetras(dni,raizAp,bd);
    }catch (Exception e) {
      throw e;
    }finally  {
      if (bd!=null) {
        bd.cerrar();
      }
    }
  }

  public Collection findByPasaporteLetras(String pasaporte,String raizAp) throws
      Exception {
    AccesoBd bd=null;
    try {
      bd=new AccesoBd();
      return findByPasaporteLetras(pasaporte,raizAp,bd);
    }catch (Exception e) {
      throw e;
    }finally  {
      if (bd!=null) {
        bd.cerrar();
      }
    }
  }

  public SegSocialNafTitularBean findByNafDniLetras(String naf,String dni,String raizAp,AccesoBd bd) throws Exception {
	  SegSocialNafTitularBean segsocialBean=new SegSocialNafTitularBean();
    String query="select * from " + TABLA_SEGURIDAD_SOCIAL + " where NAF = ? and DNI_NIE = ? and RAIZAP = ?";
    HashMap hParam=new HashMap();
    hParam.put("1",naf);
    hParam.put("2",dni);
    hParam.put("3",raizAp);
    Vector vResp=bd.consulta(query,hParam);
    if (!vResp.isEmpty()) {
      segsocialBean=new SegSocialNafTitularBean((HashMap)vResp.elementAt(0));
    }else {
      throw new UsuarioSegSocialException("Usuario no encontrado para naf, dni,raizap -> [" + naf + "," + dni + "," + raizAp + "]");
    }
    return segsocialBean;
  }

  public SegSocialNafTitularBean findByNafPasaporteLetras(String naf,String pasaporte,String raizAp,AccesoBd bd) throws
      Exception {
	  SegSocialNafTitularBean segsocialBean=new SegSocialNafTitularBean();
    String query="select * from " + TABLA_SEGURIDAD_SOCIAL + " where NAF = ? and PASAPORTE = ? and RAIZAP = ?";
    HashMap hParam=new HashMap();
    hParam.put("1",naf);
    hParam.put("2",pasaporte);
    hParam.put("3",raizAp);
    Vector vResp=bd.consulta(query,hParam);
    if (!vResp.isEmpty()) {
      segsocialBean=new SegSocialNafTitularBean((HashMap)vResp.elementAt(0));
    }else {
      throw new UsuarioSegSocialException("Usuario no encontrado para naf, pasaporte,raizap -> [" + naf + "," + pasaporte + "," + raizAp + "]");
    }
    return segsocialBean;
  }

  public SegSocialNafTitularBean findByPrimaryKey(String naf,AccesoBd bd) throws
      Exception {
	  SegSocialNafTitularBean segsocialBean=new SegSocialNafTitularBean();
    String query="select * from " + TABLA_SEGURIDAD_SOCIAL + " where NAF = ?";
    HashMap hParam=new HashMap();
    hParam.put("1",naf);
    Vector vResp=bd.consulta(query,hParam);
    if (!vResp.isEmpty()) {
      segsocialBean=new SegSocialNafTitularBean((HashMap)vResp.elementAt(0));
    }else {
      throw new UsuarioSegSocialException("Usuario no encontrado para naf -> [" + naf + "]");
    }
    return segsocialBean;
  }

  public Collection findByDniLetras(String dni,String raizAp,AccesoBd bd) throws
      Exception {
    String query="select * from " + TABLA_SEGURIDAD_SOCIAL + " where DNI_NIE = ? and RAIZAP = ?";
    HashMap hParam=new HashMap();
    hParam.put("1",dni);
    hParam.put("2",raizAp);
    Vector vResp=bd.consulta(query,hParam);
    return vResp;
  }

  public Collection findByPasaporteLetras(String pasaporte,String raizAp,AccesoBd bd) throws Exception {
    String query="select * from " + TABLA_SEGURIDAD_SOCIAL + " where PASAPORTE = ? and RAIZAP = ?";
    HashMap hParam=new HashMap();
    hParam.put("1",pasaporte);
    hParam.put("2",raizAp);
    Vector vResp=bd.consulta(query,hParam);
    return vResp;
  }

}

package sns.general.bean;

import gasai.util.Misc;

import java.util.HashMap;


public class RegistroBean implements java.io.Serializable {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String codigo;
   private String descripcion;
   private HashMap parametros=new HashMap();


   public RegistroBean() {
   }


   public RegistroBean(java.util.HashMap hd) {
     this.codigo = Misc.nz(hd.get("CODIGO"));
     this.descripcion = Misc.nz(hd.get("DESCRIPCION"));
   }

   public String getDescripcion() {
      return descripcion;
   }

   public void setCodigo(String codigo) {
      this.codigo = codigo;
   }

   public void setDescripcion(String descripcion) {
      this.descripcion = descripcion;
   }

  public void setParametros(HashMap parametros) {
    this.parametros = parametros;
  }

  public String getCodigo() {
      return codigo;
   }

  public HashMap getParametros() {
    return parametros;
  }

}

package sns.bd;
/****************************************************** /
Clase generada con GeneraBean de gasai.jar
/ ******************************************************/
public class SecuenciaBean { 

  private java.math.BigDecimal codigo;

  public SecuenciaBean(java.util.HashMap hd) {
	 this.codigo=(java.math.BigDecimal)hd.get("CODIGO");
  }

  public java.math.BigDecimal getCodigo() {
	 return this.codigo;
  }
  public void setCodigo(java.math.BigDecimal codigo) {
	 this.codigo=codigo;
  }

}

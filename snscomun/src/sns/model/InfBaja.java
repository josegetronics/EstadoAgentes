package sns.model;

public class InfBaja implements java.io.Serializable {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private String codSns="";
  private int encontradoEnComunidad;
  private boolean baja;
  private int[] numNotCcaa=new int[20];
  private int[] numNotCcaaResidencia=new int[20];
  private int[] numNotCcaaDefuncion=new int[20];


  public Object[] getAllArrays() {
    Object[] salida=new Object[6];
    salida[0]=this.codSns;
    salida[1]=new Integer(this.encontradoEnComunidad);
    salida[2]=new Boolean(this.baja);
    salida[3]=this.numNotCcaa;
    salida[4]=this.numNotCcaaResidencia;
    salida[5]=this.numNotCcaaDefuncion;
    return salida;
  }


  public String getCodSns() {
    return codSns;
  }



  public void addNot(int ca) {
    this.numNotCcaa[ca]+=1;
  }

  public void addNotResidencia(int ca) {
    this.numNotCcaaResidencia[ca]+=1;
  }

  public void addNotDefuncion(int ca) {
    this.numNotCcaaDefuncion[ca]+=1;
  }

  public int[] getNumNotCcaa() {
    return this.numNotCcaa;
  }

  public boolean isBaja() {
    return baja;
  }

  public int[] getNumNotCcaaDefuncion() {
    return numNotCcaaDefuncion;
  }

  public int[] getNumNotCcaaResidencia() {
    return numNotCcaaResidencia;
  }

  public int getEncontradoEnComunidad() {
    return encontradoEnComunidad;
  }

  public void setCodSns(String codSns) {
    this.codSns = codSns;
  }

  public void setNumNotCcaa(int[] numNotCcaa) {
    this.numNotCcaa = numNotCcaa;
  }

  public void setBaja(boolean baja) {
    this.baja = baja;
  }

  public void setNumNotCcaaDefuncion(int[] numNotCcaaDefuncion) {
    this.numNotCcaaDefuncion = numNotCcaaDefuncion;
  }

  public void setNumNotCcaaResidencia(int[] numNotCcaaResidencia) {
    this.numNotCcaaResidencia = numNotCcaaResidencia;
  }

  public void setEncontradoEnComunidad(int encontradoEnComunidad) {
    this.encontradoEnComunidad = encontradoEnComunidad;
  }

  public InfBaja() {
  }

  public InfBaja(Object[] objs){
    this.codSns=(String)objs[0];
    this.encontradoEnComunidad=((Integer)objs[1]).intValue();
    this.baja=((Boolean)objs[2]).booleanValue();
    this.numNotCcaa=(int[])objs[3];
    this.numNotCcaaResidencia=(int[])objs[4];
    this.numNotCcaaDefuncion=(int[])objs[5];
  }
}

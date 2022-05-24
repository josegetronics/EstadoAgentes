package sns.model;

/**
 * Contiene informacion de los agentes para el modulo de administracion,
 * para utilizar en la generacion de reporting
 *
 * <p>Empresa: </p>
 * @author sin atribuir
 * @version 1.0
 */
public class AgenteBean {

  static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(AgenteBean.class);

  // codigo del agente
  private int codAgente = -1;
  //
  private String descripcion;
  // Nombre de la comunidad obtenida en Inicializacion
  // Se obtiene a partir de la HahMap MAP_NOMBRES_COMUNIDADES  (MADRID - Madrid).
  private String descripcion2;


  public AgenteBean() {
  }


  public String getDescripcion() {
    return descripcion;
  }

  public String getDescripcion2() {
    return descripcion2;
  }

  public void setCodAgente(int codAgente) {
    this.codAgente = codAgente;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }

  public void setDescripcion2(String descripcion2) {
    this.descripcion2 = descripcion2;
  }

  public int getCodAgente() {
    return codAgente;
  }


  public void view (){
    logger.debug("codAg: " + codAgente + ", desc: " + descripcion + ", desc2: " + descripcion2);
  }



}

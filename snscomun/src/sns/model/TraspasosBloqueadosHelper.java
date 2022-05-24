package sns.model;

import gasai.bd.AccesoBD;
import gasai.util.Misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import sns.bd.AccesoBd;
import sns.bd.AccesoBdTx;
import sns.config.Constantes;
import sns.config.Inicializacion;
import sns.exception.TraspasoBloqueadoException;
import sns.exception.TraspasoBloqueadoUsuarioEnListaNegraException;
import sns.general.traspasos.bean.FicheroListaBlancaBean;
import sns.general.traspasos.bean.ListaBlancaBean;
import sns.general.traspasos.bean.ListaNegraBean;
import sns.model.inss.IInssHelper;
import sns.model.inss.ISnsHelper;
import sns.model.inss.InssHelperFactory;
import sns.model.inss.SnsHelperFactory;

public class TraspasosBloqueadosHelper {
  static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(TraspasosBloqueadosHelper.class);
  public TraspasosBloqueadosHelper() {
  }

  public NotificacionTraspasosBloqueados revisar(BigDecimal agente,Integer operacionMaestra,String codUsuarioSns,ListaCamposAfectados listaCamposAfectados) throws Exception {
   String asuntoEmail = "";
   String cuerpoEmail="";
   NotificacionTraspasosBloqueados mailBean=new NotificacionTraspasosBloqueados();

    long contadorCambios=0;
    if (listaCamposAfectados.existeCampo(CamposAfectados.APELLIDO1)) {
      contadorCambios++;
    }
    if (listaCamposAfectados.existeCampo(CamposAfectados.APELLIDO2)) {
      contadorCambios++;
    }
    if (listaCamposAfectados.existeCampo(CamposAfectados.NOMBRE)) {
      contadorCambios++;
    }
    if (listaCamposAfectados.existeCampo(CamposAfectados.FECHA_NAC)) {
      contadorCambios++;
    }
    if (listaCamposAfectados.existeCampo(CamposAfectados.SEXO)) {
      contadorCambios++;
    }

    if (listaCamposAfectados.existeCampo(CamposAfectados.NAF)) {
      if (Inicializacion.REVISAR_NAF) {
        contadorCambios++;
      }
    }

    if (listaCamposAfectados.existeCampo(CamposAfectados.DNI)) {
      if (Inicializacion.REVISAR_DNI) {
        contadorCambios++;
      }
    }

    if (listaCamposAfectados.existeCampo(CamposAfectados.NAF_TITULAR)) {
      if (Inicializacion.REVISAR_NAF_TITULAR) {
        contadorCambios++;
      }
    }

    boolean estaEnListaBlanca=false;
    boolean estaEnListaNegra=false;
      AccesoBdTx bd = null;
      try {
        bd=new AccesoBdTx();
        estaEnListaNegra = comprobarListaNegra(agente,operacionMaestra,codUsuarioSns,bd);
        if (estaEnListaNegra) {
          //si esta activo el bloqueo
          if (Inicializacion.BLOQUEAR_TRASPASOS_LISTA_NEGRA) {

            //si esta activo el flag de notificar
            if (Inicializacion.NOTIFICAR_BLOQUEAR_TRASPASOS_LISTA_NEGRA) {
              asuntoEmail = "Traspaso en Lista Negra bloqueado";
              cuerpoEmail = "Se ha bloqueado el traspaso del agente -> [" +
                  agente +
                  "] para el codOperacion,codUsuarioSns -> [" + operacionMaestra +
                  "," + codUsuarioSns +
                  "]";

            }
            //devolvemos error y bloqueamos el traspaso
            throw new TraspasoBloqueadoUsuarioEnListaNegraException();
          }

        }

        estaEnListaBlanca = comprobarListaBlanca(agente, operacionMaestra,
                                                 codUsuarioSns, bd);
        if (estaEnListaBlanca
            || (!estaEnListaBlanca && contadorCambios >= Inicializacion.NUMERO_MAXIMO_CAMBIOS_BLOQUEAR_TRASPASO)) {

          logger.debug("[estaEnListaBlanca,contadorCambios] -> [" +
                       estaEnListaBlanca + "," + contadorCambios + "]");

          registrarBd(agente, operacionMaestra, listaCamposAfectados,
                      codUsuarioSns, estaEnListaBlanca, bd);
        }
//        bd.commit();
      }
      catch (Exception ex) {
        try {
//          bd.rollback();
        }catch (Exception e) {
          logger.error("Error intentando hacer el rollback en el traspaso");
        }
        logger.error(
            "Error registrando el traspaso bloqueado para la operacion [" +
            operacionMaestra + "]", ex);
        throw ex;
      }
      finally {
        if (bd != null) {
          bd.cerrar();
        }
      }

      if (estaEnListaBlanca
    	  || contadorCambios>=Inicializacion.NUMERO_MAXIMO_CAMBIOS_BLOQUEAR_TRASPASO) {
        if (Inicializacion.NOTIFICAR_BLOQUEAR_TRASPASOS_ERRONEOS) {

          //si no hay que bloquear solo se notifica del error
          if (!Inicializacion.BLOQUEAR_TRASPASOS_ERRONEOS) {
            asuntoEmail = "Traspaso erroneo";
            cuerpoEmail="Se ha producido un traspaso erroneo cometido por el agente -> [" + agente +
              "] para el codOperacion,codUsuarioSns -> [" + operacionMaestra + "," + codUsuarioSns +
              "]. Los campos afectados son [" + listaCamposAfectados.getIdCampos() +
              "]";
          }else{
            if (!estaEnListaBlanca) {
              asuntoEmail = "Traspaso bloqueado";
              cuerpoEmail="Se ha bloqueado el traspaso del agente -> [" + agente +
                  "] para el codOperacion,codUsuarioSns -> [" + operacionMaestra + "," + codUsuarioSns +
                  "]. Los campos afectados son [" + listaCamposAfectados.getIdCampos() +
                  "]";
              if (sns.config.Inicializacion.ACTIVO_REVISAR_TRASPASOS) {
                cuerpoEmail += " Puede revisar el bloque accediendo a este link " + sns.config.Inicializacion.URL_REVISAR_TRASPASOS + operacionMaestra;
              }

            }else{
              asuntoEmail = "Traspaso desbloqueado";
              cuerpoEmail="Se ha desbloqueado el traspaso del agente -> [" + agente + "] para el codOperacion,codUsuarioSns -> [" + operacionMaestra + "," + codUsuarioSns +
                  "]. Los campos afectados son [" + listaCamposAfectados.getIdCampos() +
                  "]";
            }
          }

          mailBean.setAsunto(asuntoEmail);
          mailBean.setCuerpo(cuerpoEmail);
          
        }
      }

      if (contadorCambios>=Inicializacion.NUMERO_MAXIMO_CAMBIOS_BLOQUEAR_TRASPASO
          && Inicializacion.BLOQUEAR_TRASPASOS_ERRONEOS) {
        //si no esta en la lista blanca hay que bloquear
        if (!estaEnListaBlanca) {
          //metemos automaticamente en la lista negra el bloqueo
          if (sns.config.Inicializacion.BLOQUEAR_TRASPASOS_AUTOMATICAMENTE) {
            ListaNegraBean listaNegraBean=new ListaNegraBean();
            listaNegraBean.setActivar(true);
            listaNegraBean.setCodTipoAnotacionListaNegra(Constantes.LISTA_NEGRA_TIPO_ANOTACION_AUTOMATICA);
            listaNegraBean.setCodAgente(Misc.nz(agente));
            listaNegraBean.setCodUsusarioSns(codUsuarioSns);
            insertarListaNegra(listaNegraBean);
          }
          throw new TraspasoBloqueadoException(mailBean);
        }
      }
    return mailBean;
  }
  
  /**
   * Comprueba si el agente solicitante tiene registro en la lista blanca. Si es
   * asi se actualiza la fecha_realizado con la del sistema
   *
   * @param agente BigDecimal
   * @param operacionMaestra Integer
   * @param codUsuarioSns String
   * @param bd AccesoBd
   * @throws Exception
   * @return String
   */
  private boolean comprobarListaBlanca(BigDecimal agente,
                                       Integer operacionMaestra,
                                       String codUsuarioSns,
                                      AccesoBD bd) throws Exception {
    String query="update TRASPASOS_LISTA_BLANCA "
    + "set FECHA_REALIZADO=sysdate, "
    + "COD_OPERACION = ? "
    + "where COD_AGENTE_SOLICITANTE=? "
    + "and COD_USUARIO_SNS=? "
    + "and FECHA_REALIZADO is null";

    HashMap hParam=new HashMap();

    hParam.put("1",operacionMaestra);
    hParam.put("2",agente);
    hParam.put("3",codUsuarioSns);

    Vector vDatos=bd.actualizaSinCommit(query,hParam);
    logger.debug("comprobarListaBlanca -> " + vDatos);
    if (vDatos.size()>0) {
      Integer numAfectados=(Integer)vDatos.elementAt(0);
      return numAfectados.intValue()>0;
    }

    return false;

  }

  /**
   * Comprueba si el agente solicitante tiene registro en la lista blanca. Si es
   * asi se actualiza la fecha_realizado con la del sistema
   *
   * @param agente BigDecimal
   * @param operacionMaestra Integer
   * @param codUsuarioSns String
   * @param bd AccesoBd
   * @throws Exception
   * @return String
   */
  private boolean comprobarListaNegra(BigDecimal agente,
                                       Integer operacionMaestra,
                                       String codUsuarioSns,
                                      AccesoBD bd) throws Exception {

   String query="select T.* from TRASPASOS_LISTA_NEGRA T "
    + "where (COD_AGENTE_SOLICITANTE=? OR COD_AGENTE_SOLICITANTE IS NULL) "
    + "and COD_USUARIO_SNS=? "
    + "and ACTIVO = ? "
    + "order by COD_AGENTE_SOLICITANTE";


    HashMap hParam=new HashMap();

    hParam.put("1",agente);
    hParam.put("2",codUsuarioSns);
    hParam.put("3",sns.config.Constantes.LISTA_NEGRA_ACTIVADO);

    Vector vDatos = bd.consulta(query, hParam);
    if (vDatos.size() > 0) {
      HashMap hDatos = (HashMap) vDatos.elementAt(0);

      logger.debug("comprobarListaNegra -> " + hDatos);

      String codListaNegra = Misc.nz(hDatos.get("COD_LISTA_NEGRA"));

      if (Inicializacion.BLOQUEAR_TRASPASOS_LISTA_NEGRA) {

        String queryInsert = "insert into TRASPASOS_LISTA_NEGRA_DETALLE "
            + " (COD_LISTA_NEGRA_DETALLE,COD_AGENTE_SOLICITANTE,FECHA_INTENTO,COD_LISTA_NEGRA,COD_OPERACION) "
            + " values (SEQ_TRASPASOS_LISTA_NEGRA_DET.nextval,?,sysdate,?,?) ";

        hParam = new HashMap();
        hParam.put("1", agente);
        hParam.put("2", codListaNegra);
        hParam.put("3", operacionMaestra);

        bd.actualizaSinCommit(queryInsert, hParam);
      }

      return true;
    }

    return false;

  }

  private void registrarBd(BigDecimal agente,Integer operacionMaestra,
                           ListaCamposAfectados listaCamposAfectados,
                           String codUsuarioSns,
                           boolean estaListaBlanca,AccesoBD bd) throws
      Exception {

      String queryInsTraspaso="insert into TRASPASOS_BLOQUEADOS (COD_OPERACION,COD_AGENTE_ORIGEN,FECHA_OPERACION,BLOQUEO_ACTIVO,COD_USUARIO_SNS) values (?,?,sysdate,?,?)";
      String queryInsTraspasoCampos="insert into TRASPASOS_BLOQUEADOS_CAMPOS (COD_OPERACION,NOMBRE_CAMPO,VALOR_ANTERIOR,VALOR_NUEVO) values (?,?,?,?)";
      HashMap hParam=new HashMap();
      hParam.put("1",operacionMaestra);
      hParam.put("2",agente);
      String bloqueActivo="0";
      if (Inicializacion.BLOQUEAR_TRASPASOS_ERRONEOS) {
        if (!estaListaBlanca) {
          bloqueActivo = "1";
        }
      }
      hParam.put("3",bloqueActivo);
      hParam.put("4",codUsuarioSns);

      bd.actualizaSinCommit(queryInsTraspaso,hParam);
      Vector vListaCamposAfectados = listaCamposAfectados.getListaCamposAfectados();
      for (int i=0;i<vListaCamposAfectados.size();i++) {
        CamposAfectados camposAfectados=(CamposAfectados)vListaCamposAfectados.elementAt(i);
        if (esCampoParaAuditar(camposAfectados)) {
          hParam = new HashMap();
          hParam.put("1", operacionMaestra);
          hParam.put("2", camposAfectados.getIdCampo());
          hParam.put("3", camposAfectados.getValorAnterior());
          hParam.put("4", camposAfectados.getValorSolicitud());
          try {
            bd.actualizaSinCommit(queryInsTraspasoCampos, hParam);
          }
          catch (SQLException e) {
            if (e.getErrorCode() == 1) {
              logger.error("Campo repetido para la operacion [" +
                           operacionMaestra + "] lista de campos -> [" +
                           listaCamposAfectados.getIdCampos() + "]");
            }
          }
        }
      }
    }

    private boolean esCampoParaAuditar(CamposAfectados camposAfectados) {
      if (camposAfectados.getIdCampo().equals(CamposAfectados.NOMBRE)
            || camposAfectados.getIdCampo().equals(CamposAfectados.APELLIDO1)
            || camposAfectados.getIdCampo().equals(CamposAfectados.APELLIDO2)
            || camposAfectados.getIdCampo().equals(CamposAfectados.FECHA_NAC)
            || camposAfectados.getIdCampo().equals(CamposAfectados.SEXO)
            || camposAfectados.getIdCampo().equals(CamposAfectados.NAF)
            || camposAfectados.getIdCampo().equals(CamposAfectados.DNI)
            || camposAfectados.getIdCampo().equals(CamposAfectados.NAF_TITULAR)
            ) {
          return true;
      }
      return false;
    }

    public gasai.util.out.Salida insertarListaNegra(ListaNegraBean traspasoListaNegraBean) {
      //
      AccesoBd bd = null;
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        //logger.debug("INICIO");
        bd = new AccesoBd();
        salida = this.insertarListaNegraTx(bd, traspasoListaNegraBean);
        if(!salida.getError()) {
          logger.debug("COMMIT");
          bd.commit();
        }
        else {
           logger.debug("ROLLBACK");
           bd.rollback();
        }
        //logger.debug("FIN");
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      finally {
        if (bd != null) {
          bd.cerrar();
        }
      }
      return salida;
    }


    public gasai.util.out.Salida insertarListaNegraTx(AccesoBd bd, ListaNegraBean listaNegraBean) {
      //
      String query                 = "";
      HashMap parametros           = new HashMap();
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        //logger.debug("INICIO");
        StringBuffer stringBufferInsert = new StringBuffer();
        stringBufferInsert.append(" INSERT INTO TRASPASOS_LISTA_NEGRA (COD_LISTA_NEGRA, COD_USUARIO_SNS, COD_AGENTE_SOLICITANTE, FECHA_PETICION,ACTIVO,COD_TIPO_ANOTACION) ");
        stringBufferInsert.append(" VALUES (SEQ_TRASPASOS_LISTA_NEGRA.NEXTVAL, ?, ?, SYSDATE, ?,?) ");
        query = stringBufferInsert.toString();
        //
        parametros.put("1", Misc.nz(listaNegraBean.getCodUsusarioSns()));
        parametros.put("2", listaNegraBean.getCodAgente());
        parametros.put("3", listaNegraBean.isActivar() ? sns.config.Constantes.LISTA_NEGRA_ACTIVADO : sns.config.Constantes.LISTA_NEGRA_NO_ACTIVADO);
        parametros.put("4",listaNegraBean.getCodTipoAnotacionListaNegra());
        //
        Vector vectorBD = bd.actualizaSinCommit(query, parametros);
        //
        if (vectorBD != null && vectorBD.size() > 0) {
          salida.setOk();
        }
        else {
          salida.setError(true);
        }
        //logger.debug("FIN");
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        logger.error("query:      " + query);
        logger.error("parametros: " + parametros.toString());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      return salida;
    }


    public gasai.util.out.Salida insertarListaBlanca(ListaBlancaBean traspasoListaBlancaBean) {
      //
      AccesoBd bd = null;
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        //logger.debug("INICIO");
        bd = new AccesoBd();
        salida = this.insertarListaBlancaTx(bd, traspasoListaBlancaBean);
        /**
         * al insertar en la lista blanca, tambien desvinculamos del INSS
         */
        IInssHelper inssHelper=InssHelperFactory.getInstance();
        ISnsHelper snsHelper=SnsHelperFactory.getInstance();
        /**
         * Creamos la operacion en el registro operaciones
         */
        Integer codOperacion=snsHelper.crearRegistroOperacion("Insercion Lista Blanca", bd);
        /**
         * desvinculamos el codSNS del INSS
         */
        inssHelper.desvincularCodSns(traspasoListaBlancaBean.getCodUsusarioSns(), codOperacion,Constantes.INSS_HISTORICO_PROCEDENCIA_OTRO_ID_INSS, bd);
        if(!salida.getError()) {
          logger.debug("COMMIT");
          bd.commit();
        }
        else {
           logger.debug("ROLLBACK");
           bd.rollback();
        }
        //logger.debug("FIN");
      }
      catch (Exception e) {
    	  
    	  try {
			bd.rollback();
		} catch (SQLException e1) {
	          logger.error("No se ha podido realizar el rollback",e1);
		}
        logger.error("Exception:  " + e.getMessage());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      finally {
        if (bd != null) {
          bd.cerrar();
        }
      }
      return salida;
    }


    public gasai.util.out.Salida insertarListaBlancaTx(AccesoBd bd, ListaBlancaBean listaBlancaBean) {
      //
      String query                 = "";
      HashMap parametros           = new HashMap();
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        //logger.debug("INICIO");
        ListaNegraBean listaNegraBean=new ListaNegraBean();
        listaNegraBean.setCodAgente(listaBlancaBean.getCodAgente());
        listaNegraBean.setCodUsusarioSns(listaBlancaBean.getCodUsusarioSns());
        desactivarTraspasoListaNegraTx(bd,listaNegraBean);

        StringBuffer stringBufferInsert = new StringBuffer();
        stringBufferInsert.append(" INSERT INTO TRASPASOS_LISTA_BLANCA (COD_LISTA_BLANCA, COD_USUARIO_SNS, COD_MOTIVO_TRASPASO, COD_AGENTE_SOLICITANTE, FECHA_PETICION) ");
        stringBufferInsert.append(" VALUES (SEQ_TRASPASOS_LISTA_BLANCA.NEXTVAL, ?, ?, ?, SYSDATE) ");
        query = stringBufferInsert.toString();
        //
        parametros.put("1", Misc.nz(listaBlancaBean.getCodUsusarioSns()));
        parametros.put("2", new Integer(listaBlancaBean.getCodMotivoTraspaso()));
        parametros.put("3", new Integer(listaBlancaBean.getCodAgente()));
        //
        Vector vectorBD = bd.actualizaSinCommit(query, parametros);
        //
        if (vectorBD != null && vectorBD.size() > 0) {
          salida.setOk();
        }
        else {
          salida.setError(true);
        }
        //logger.debug("FIN");
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        logger.error("query:      " + query);
        logger.error("parametros: " + parametros.toString());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      return salida;
    }


    public gasai.util.out.Salida insertarFicheroListaBlanca(FicheroListaBlancaBean ficheroListaBlancaBean) {
      //
      int contadorRegistros         = 0;
      AccesoBd accesoBd             = null;
      BufferedReader bufferedReader = null;
      FileReader fileReader         = null;
      gasai.util.out.Salida salida  = new gasai.util.out.Salida();
      //
      try {
        logger.debug("INICIO");
        accesoBd       = new AccesoBd();
        fileReader     = new FileReader(Misc.nz(ficheroListaBlancaBean.getRutaFicheroNormalizado()));
        bufferedReader = new BufferedReader(fileReader);
        //
        String line = "";
        while ((line = bufferedReader.readLine())!=null && !salida.getError()) {
          contadorRegistros++;
          if (contadorRegistros % 1 == 0) {
            // Numero de registros procesados
            logger.debug("Registros procesados: " + contadorRegistros);
          }
          if(!Misc.esVacio(line)) {
            String[] array = line.trim().split("\\|");
            ListaBlancaBean listaBlancaBean = new ListaBlancaBean(array);
            //
            salida = this.comprobarValoresListaBlancaTx(accesoBd, listaBlancaBean, contadorRegistros);
            ficheroListaBlancaBean.setMensaje(Misc.nz(salida.getValor()));
            //
            if (!salida.getError()) {
              salida = this.insertarListaBlancaTx(accesoBd, listaBlancaBean);
            }
          }
        }
        if(!salida.getError()) {
          accesoBd.commit();
          logger.debug(" COMMIT ");
          salida.setOk();
          ficheroListaBlancaBean.setMensaje("Los registros del fichero se insertaron correctamente. ");
        }
        else {
           accesoBd.rollback();
           logger.debug(" ROLLBACK ");
        }
        logger.debug("FIN");
      }
      catch (Exception e) {
        //
        try {
          accesoBd.rollback();
          logger.debug(" ROLLBACK ");
        }
        catch (SQLException ex) {
        }
        logger.error("Exception: " + e.getMessage());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      finally {
        try {
          if (fileReader != null) {
            fileReader.close();
          }
        }
        catch (Exception e1) {
          fileReader = null;
        }
        try {
          if (bufferedReader != null) {
            bufferedReader.close();
          }
        }
        catch (Exception e2) {
          bufferedReader = null;
        }
        if (accesoBd != null) {
          accesoBd.cerrar();
        }
      }
      return salida;
    }


    public gasai.util.out.Salida comprobarValoresListaBlancaTx(AccesoBd accesoBd, ListaBlancaBean listaBlancaBean, int numRegistro) {
      //
      boolean hayError             = false;
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        //logger.debug("INICIO");
        String codUsuarioSns = Misc.nz(listaBlancaBean.getCodUsusarioSns());
        String codMotivo     = Misc.nz(listaBlancaBean.getCodMotivoTraspaso());
        String codAgente     = Misc.nz(listaBlancaBean.getCodAgente());
        //logger.debug("codUsuarioSns: " + codUsuarioSns + ", codMotivo: " + codMotivo + ", codAgente: " + codAgente);
        StringBuffer stringBufferMensajeError = new StringBuffer();
        stringBufferMensajeError.append("Registro " + numRegistro + ". ");
        //
        if(!Misc.esVacio(codUsuarioSns)) {
          salida = this.getUsuarioTx(accesoBd, codUsuarioSns);
          if(!salida.getError()) {
            if(Misc.esVacio(salida.getValor())) {
              hayError = true;
              stringBufferMensajeError.append("El COD_USUARIO_SNS no pertenece al SNS. ");
            }
          }
        }
        else {
          hayError = true;
          stringBufferMensajeError.append("No contiene COD_USUARIO_SNS. ");
        }
        if(!Misc.esVacio(codMotivo)) {
          if(!Inicializacion.HASH_TRASPASOS_MOTIVOS.containsKey(codMotivo)) {
            stringBufferMensajeError.append("El COD_MOTIVO_TRASPASO no es correcto. ");
            hayError = true;
          }
        }
        else {
          hayError = true;
          stringBufferMensajeError.append("No contiene COD_MOTIVO_TRASPASO. ");
        }
        if(!Misc.esVacio(codAgente)) {
          if(!Inicializacion.HASH_AGENTES_TRASPASOS.containsKey(codAgente)) {
            stringBufferMensajeError.append("El COD_AGENTE_SOLICITANTE no es correcto. ");
            hayError = true;
          }
        }
        else {
          hayError = true;
          stringBufferMensajeError.append("No contiene COD_AGENTE_SOLICITANTE. ");
        }
        if(hayError) {
          String mensajeError = stringBufferMensajeError.toString();
          logger.debug(mensajeError);
          salida.setError(true);
          salida.setValor(mensajeError);
        }
        else {
          salida.setOk();
        }
        logger.debug("FIN");
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      return salida;
    }


    public gasai.util.out.Salida getUsuario(String codUsuarioSns) {
      //
      AccesoBd bd                  = null;
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        bd = new AccesoBd();
        salida = this.getUsuarioTx(bd, codUsuarioSns);
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      finally {
        if (bd != null) {
          bd.cerrar();
        }
      }
      return salida;
    }


    public gasai.util.out.Salida getUsuarioTx(AccesoBd accesoBd, String codUsuarioSns) {
      //
      String query                 = "";
      HashMap parametros           = new HashMap();
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        //logger.debug("INICIO");
        StringBuffer sbQuery = new StringBuffer();
        sbQuery.append("SELECT COD_USUARIO_SNS ");
        sbQuery.append(" FROM  USUARIOS ");
        sbQuery.append(" WHERE COD_USUARIO_SNS = ?");
        query = sbQuery.toString();
        parametros.put("1", codUsuarioSns);
        //
        Vector vectorBD = accesoBd.consulta(query, parametros);
        if (vectorBD != null && vectorBD.size() > 0) {
          HashMap hDatos = (HashMap) vectorBD.get(0);
          String encontrado = Misc.nz( (String) hDatos.get("COD_USUARIO_SNS"));
          salida.setOk();
          salida.setValor(encontrado);
        }
        //logger.debug("FIN");
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        logger.error("query:      " + query);
        logger.error("parametros: " + parametros.toString());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      return salida;
    }

    public gasai.util.out.Salida getUsuarioEnListaNegra(String codUsuarioSns) {
      return getUsuarioEnListaNegra(codUsuarioSns,null);
    }

    public gasai.util.out.Salida getUsuarioEnListaNegra(String codUsuarioSns,String codAgente) {
      //
      AccesoBd bd                  = null;
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        bd = new AccesoBd();
        salida = this.getUsuarioEnListaNegraTx(bd, codUsuarioSns,codAgente);
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      finally {
        if (bd != null) {
          bd.cerrar();
        }
      }
      return salida;
    }

    public gasai.util.out.Salida getUsuarioEnListaNegraTx(AccesoBd accesoBd, String codUsuarioSns) {
      return getUsuarioEnListaNegraTx(accesoBd, codUsuarioSns,null);
    }

    public gasai.util.out.Salida getUsuarioEnListaNegraTx(AccesoBd accesoBd, String codUsuarioSns,String codAgente) {
      //
      String query                 = "";
      HashMap parametros           = new HashMap();
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        //logger.debug("INICIO");
        StringBuffer sbQuery = new StringBuffer();
        sbQuery.append("SELECT COD_USUARIO_SNS,COD_AGENTE_SOLICITANTE ");
        sbQuery.append(" FROM  TRASPASOS_LISTA_NEGRA ");
        sbQuery.append(" WHERE COD_USUARIO_SNS = ?");
        sbQuery.append(" AND ACTIVO = ?");

        parametros.put("1", codUsuarioSns);
        parametros.put("2", sns.config.Constantes.LISTA_NEGRA_ACTIVADO);

        if (codAgente!=null) {
          if (codAgente.equals("")) {
            sbQuery.append(" AND COD_AGENTE_SOLICITANTE is null");
          }else{
            sbQuery.append(" AND COD_AGENTE_SOLICITANTE = ?");
            parametros.put("3", codAgente);
          }
        }

        query = sbQuery.toString();
        //
        logger.debug("sbQuery -> " + sbQuery);
        logger.debug("parametros -> " + parametros);
        Vector vectorBD = accesoBd.consulta(query, parametros);
        logger.debug("vectorBD -> " + vectorBD);
        if (vectorBD != null && vectorBD.size() > 0) {
          HashMap hDatos = (HashMap) vectorBD.get(0);
          ListaNegraBean listaNegraBean=new ListaNegraBean();
          listaNegraBean.setCodAgente(Misc.nz(hDatos.get("COD_AGENTE_SOLICITANTE"),"PARA TODOS"));
          listaNegraBean.setCodUsusarioSns(Misc.nz(hDatos.get("COD_USUARIO_SNS")));
          salida.setOk();
          salida.setValor(listaNegraBean);
        }
        //logger.debug("FIN");
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        logger.error("query:      " + query);
        logger.error("parametros: " + parametros.toString());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      return salida;
    }

    public gasai.util.out.Salida modificarTraspasoListaNegra(ListaNegraBean listaNegraBean) {
      //
      AccesoBd bd                  = null;
      gasai.util.out.Salida salida = new gasai.util.out.Salida();
      //
      try {
        bd = new AccesoBd();
        salida = this.modificarTraspasoListaNegraTx(bd, listaNegraBean);
      }
      catch (Exception e) {
        logger.error("Exception:  " + e.getMessage());
        salida.setError(true);
        salida.setMsg(e.getMessage());
        salida.setExcepcion(e);
      }
      finally {
        if (bd != null) {
          bd.cerrar();
        }
      }
      return salida;
    }

    //modificar
    public gasai.util.out.Salida modificarTraspasoListaNegraTx(AccesoBd accesoBd,ListaNegraBean listaNegraBean) throws SQLException, Exception {
      gasai.util.out.Salida objSalida = new gasai.util.out.Salida();
      try {
        logger.debug("modificarTraspasoListaNegra()");
        StringBuffer sQuery = new StringBuffer();
        sQuery.append("UPDATE TRASPASOS_LISTA_NEGRA SET COD_AGENTE_SOLICITANTE=?, ");
        sQuery.append("ACTIVO=? ");
        sQuery.append("WHERE COD_LISTA_NEGRA=? ");

        HashMap hParametros = new HashMap();
        try {
          hParametros.put("1", Misc.nz(listaNegraBean.getCodAgente()));
          hParametros.put("2", listaNegraBean.isActivar() ? "1" : "0");
          hParametros.put("3", Misc.nz(listaNegraBean.getCodListaNegra()));
          accesoBd.actualizaSinCommit(sQuery.toString(), hParametros);
          objSalida.setOk();
        }
        catch (Exception e) {
          logger.error("Exception: modificarTraspasoListaNegra()", e);
          objSalida.setError(true);
        }
      }
      catch (Exception e) {
        logger.error("Exception: modificarTraspasoListaNegra()", e);
        objSalida.setError(true);
      }
      return objSalida;
    }

    public void desactivarTraspasoListaNegraTx(AccesoBd accesoBd,ListaNegraBean listaNegraBean) throws SQLException, Exception {
      try {
        logger.debug("modificarTraspasoListaNegra()");
        StringBuffer sQuery = new StringBuffer();
        sQuery.append("UPDATE TRASPASOS_LISTA_NEGRA SET ACTIVO=? ");
        sQuery.append("WHERE COD_AGENTE_SOLICITANTE=? and COD_USUARIO_SNS = ?");

        HashMap hParametros = new HashMap();
          hParametros.put("1", sns.config.Constantes.LISTA_NEGRA_NO_ACTIVADO);
          hParametros.put("2", Misc.nz(listaNegraBean.getCodAgente()));
          hParametros.put("3", Misc.nz(listaNegraBean.getCodUsusarioSns()));
          accesoBd.actualizaSinCommit(sQuery.toString(), hParametros);
      }
      catch (Exception e) {
        logger.error("Exception: modificarTraspasoListaNegra()", e);
        throw e;
      }
    }

}

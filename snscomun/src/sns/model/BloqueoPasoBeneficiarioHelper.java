package sns.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Vector;

import sns.bd.AccesoBd;

public class BloqueoPasoBeneficiarioHelper {
   static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(BloqueoPasoBeneficiarioHelper.class);

	public BloqueosPasoBeneficiarioBean comprobarPermiso(String codUsuarioSns,BigDecimal codAgente)  {
		BloqueosPasoBeneficiarioBean respuesta=new BloqueosPasoBeneficiarioBean();
		AccesoBd bd=null;
		try {
			bd=new AccesoBd();
			
			//buscamos si esta permitido la operacion para este usuario y agente
			String query="select * from BLOQUEADOS_PASO_BENEFICIARIO b where COD_USUARIO_SNS=? and COD_AGENTE_ORIGEN=? and BLOQUEO_ACTIVO=0";
			HashMap htParametros=new HashMap();
			htParametros.put("1", codUsuarioSns);
			htParametros.put("2", codAgente);
			Vector vDatos=bd.consulta(query, htParametros);
			if (vDatos!=null) {
				//si retorna algo
				if (vDatos.size()==0 || vDatos.size()>1) {
					//si no hay registros o hay mas de 1 malo
					//si hay mas de un tio esto es malo solo puede haber 1
				}else{
					//si encontramos 1 se devuelve para que se deje hacer la operacion
					return new BloqueosPasoBeneficiarioBean((HashMap)vDatos.elementAt(0));
				}
			}
			
		}catch (Exception e) {
			logger.error("Error comprobando el permiso para tramitar un pase de titular a beneficiario para el [agente,cod_usuario_sns] [" +codAgente + "," + codUsuarioSns + "]",e);
		}finally {
			if (bd!=null) {
				bd.cerrar();
			}
		}
		return respuesta;
	}

	public void registrarOperacionRealizada(String codUsuarioSns,BigDecimal codAgente,Integer operacionMaestra)  {
		AccesoBd bd=null;
		try {
			bd=new AccesoBd();
			//buscamos si esta permitido la operacion para este usuario y agente
			String query="update BLOQUEADOS_PASO_BENEFICIARIO b set BLOQUEO_ACTIVO=1,COD_OPERACION_REALIZADA=?,FECHA_OPERACION_REALIZADA=sysdate where COD_USUARIO_SNS=? and COD_AGENTE_ORIGEN=? and BLOQUEO_ACTIVO=0";
			HashMap htParametros=new HashMap();
			htParametros.put("1", operacionMaestra);
			htParametros.put("2", codUsuarioSns);
			htParametros.put("3", codAgente);
			bd.actualizaConCommit(query, htParametros);
		}catch (Exception e) {
			logger.error("Error registrando Operacion realizada para tramitar un pase de titular a beneficiario para el [agente,cod_usuario_sns,operacionMaestra] [" +codAgente + "," + codUsuarioSns + "," + operacionMaestra + "]",e);
		}finally {
			if (bd!=null) {
				bd.cerrar();
			}
		}
	}

	public void registrarOperacionBloqueada(String codUsuarioSns,BigDecimal codAgente,Integer operacionMaestra)  {
		AccesoBd bd=null;
		try {
			bd=new AccesoBd();
			//buscamos si esta permitido la operacion para este usuario y agente
			String query="insert into BLOQUEADOS_PASO_BENEFICIARIO b (COD_OPERACION,COD_AGENTE_ORIGEN,FECHA_OPERACION,BLOQUEO_ACTIVO,COD_USUARIO_SNS) VALUES (?,?,SYSDATE,1,?)";
			HashMap htParametros=new HashMap();
			htParametros.put("1", operacionMaestra);
			htParametros.put("2", codAgente);
			htParametros.put("3", codUsuarioSns);
			bd.actualizaConCommit(query, htParametros);
		}catch (Exception e) {
			logger.error("Error registrando Operacion realizada para tramitar un pase de titular a beneficiario para el [agente,cod_usuario_sns] [" +codAgente + "," + codUsuarioSns + "," + operacionMaestra + "]",e);
		}finally {
			if (bd!=null) {
				bd.cerrar();
			}
		}
	}
	
}

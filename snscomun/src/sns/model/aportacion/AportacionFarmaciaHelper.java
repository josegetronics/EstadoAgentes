package sns.model.aportacion;

import gasai.util.Misc;

import java.util.HashMap;

import sns.bd.AccesoBdTx;
import sns.model.UsuarioSns;

public class AportacionFarmaciaHelper {
   static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(AportacionFarmaciaHelper.class);
	public void registrarAportacionPorDefecto(UsuarioSns usuarioSnsIn,ResultadoBusquedaAportacion resultadoBusquedaAportacion,Integer opMaestra) {
		AccesoBdTx bd=null;
		try {
			bd=new AccesoBdTx();
			String sql="insert into APORTACION_POR_DEFECTO (COD_OPERACION,FECHA_OPERACION,COD_USUARIO_SNS,COD_TITULO,COD_SITUACION,COD_INDICADOR_DE_FARMACIA,COD_SUBINDICADOR_DE_FARMACIA) values (?,sysdate,?,?,?,?,?)";
			HashMap htParametros=new HashMap();
			htParametros.put("1", Misc.nz(opMaestra));
			htParametros.put("2", usuarioSnsIn.getCodSns());
			htParametros.put("3", Misc.nz(usuarioSnsIn.getCodTitulo()));
			htParametros.put("4", Misc.nz(usuarioSnsIn.getCodSituacion()));
			htParametros.put("5", resultadoBusquedaAportacion.getCodIndicadorDeFarmacia());
			htParametros.put("6", Misc.nz(resultadoBusquedaAportacion.getCodSubIndicadorDeFarmacia()));
			bd.actualizaSinCommit(sql, htParametros);
		}catch (Exception e) {
			logger.error("Error registrando la aportacion por defecto -> " + usuarioSnsIn,e);
		}finally {
			if (bd!=null) {
				bd.cerrar();
			}
		}
	}

}

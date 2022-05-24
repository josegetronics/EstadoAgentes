package sns.model.inss.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

import sns.bd.AccesoBd;
import sns.exception.UsuarioSegSocialException;
import sns.model.gestion.RegistroInss;
import sns.model.inss.IResultadoInss;
import sns.model.inss.dto.ResultadoCruceSnsBean;

public class SegSocialTitularesRealHelperImpl /*implements IInssHelper*/ {
	public SegSocialTitularesRealHelperImpl() {
	}

	public IResultadoInss findByIpf(String ipf, AccesoBd bd) throws UsuarioSegSocialException, SQLException {
		ResultadoSegSocialTitularesRealBean inssBean = new ResultadoSegSocialTitularesRealBean();
		String query = "select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where IPF = ?";
		HashMap hParam = new HashMap();
		hParam.put("1", ipf);
		Vector vResp = bd.consulta(query, hParam);
		if (!vResp.isEmpty()) {
			inssBean = new ResultadoSegSocialTitularesRealBean((HashMap) vResp.elementAt(0));
//			inssBean.setNafIdentificado("naf");

		} else {
			throw new UsuarioSegSocialException("Usuario no encontrado para ipf -> [" + ipf + "]");
		}
		return inssBean;
	}
	
	public IResultadoInss findByPrimaryKey(String naf, AccesoBd bd) throws UsuarioSegSocialException, SQLException {
		ResultadoSegSocialTitularesRealBean inssBean = new ResultadoSegSocialTitularesRealBean();
		String query = "select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF = ?";
		HashMap hParam = new HashMap();
		hParam.put("1", naf);
		Vector vResp = bd.consulta(query, hParam);
		if (!vResp.isEmpty()) {
			inssBean = new ResultadoSegSocialTitularesRealBean((HashMap) vResp.elementAt(0));
			inssBean.setNafIdentificado("naf");

		} else {
			throw new UsuarioSegSocialException("Usuario no encontrado para naf -> [" + naf + "]");
		}
		return inssBean;
	}

	public IResultadoInss findNafSecundarios(String naf, AccesoBd bd) throws UsuarioSegSocialException, SQLException {
		ResultadoSegSocialTitularesRealBean inssBean = new ResultadoSegSocialTitularesRealBean();
		String[] querySecundarios = { "select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC1 = ?", "select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC2 = ?",
				"select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC3 = ?", "select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC4 = ?",
				"select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC5 = ?", "select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC6 = ?",
				"select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC7 = ?", "select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC8 = ?",
				"select * from " + ResultadoSegSocialTitularesRealBean.NOMBRE_TABLA + " where NAF_SEC9 = ?" };
		HashMap hParam = new HashMap();
		hParam.put("1", naf);
		boolean encontrado = false;
		for (int i = 0; i < querySecundarios.length && !encontrado; i++) {
			Vector vResp = bd.consulta(querySecundarios[i], hParam);
			if (!vResp.isEmpty()) {
				inssBean = new ResultadoSegSocialTitularesRealBean((HashMap) vResp.elementAt(0));
				inssBean.setNafIdentificado("naf_sec" + (i+1));
				encontrado = true;
			}
		}
		if (!encontrado) {
			throw new UsuarioSegSocialException("Usuario no encontrado para naf -> [" + naf + "]");
		}
		return inssBean;
	}

	public void actualizar(IResultadoInss resultadoInss, RegistroInss registroInss, ResultadoCruceSnsBean resultadoCruceSnsBean, AccesoBd bd) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void insertar(RegistroInss registroInss, ResultadoCruceSnsBean resultadoCruceSnsBean, AccesoBd bd) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	public void actualizar(String ipf, String ipfAnterior, AccesoBd bd) throws SQLException {
		// TODO Auto-generated method stub
		
	}

}

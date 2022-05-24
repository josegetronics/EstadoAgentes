package sns.busqueda.model;

import gasai.util.Misc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import org.apache.log4j.Logger;
import sns.comun.bd.AccesoBdIndef;
import sns.comun.bean.indef.IndefBean;
import sns.comun.config.InicializacionBusqueda;


public class IndefBdHelper {

	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public boolean getUsuarioInDefByDni (AccesoBdIndef accesoBdIndef, String dniNie) throws Exception {
		//
		boolean encontrado = false;
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		try {
			//logger.debug("INICIO");
			//
			// Se quita el ultimo caracter porque 
			String dniNieSinLetra = dniNie.substring(0, dniNie.length()-1);
			//
			String nie         = "";
			if(dniNieSinLetra.indexOf("X")!= -1 || dniNieSinLetra.indexOf("Y")!= -1 ) {
				nie         = dniNie;
			}
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select * ");
			sbQuery.append(" from   indef.TBLFALLECIDOS f ");
			if(Misc.esVacio(nie)) {
				sbQuery.append(" where  f.DNI = ?   OR    f.DNI = ? ");
				//
				parametros.put("1", Misc.nz(dniNie));
			}
			else {
				sbQuery.append(" where  f.NIE = ?   OR    f.NIE = ? ");
				//
				parametros.put("1", Misc.nz(nie));
			}
			parametros.put("2", Misc.nz(dniNieSinLetra));
			//
			query = sbQuery.toString();			
			//
			Vector vResp = accesoBdIndef.consulta(query, parametros);
			if (!vResp.isEmpty()) {
				encontrado = true;
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		return encontrado;
	}
	
	
	public IndefBean getUsuarioInfoByDni (AccesoBdIndef accesoBdIndef, String dniNie) throws Exception {
		//
		IndefBean indefBean = null;
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			//logger.debug("INICIO");
			//
			// Se quita el ultimo caracter porque 
			String dniNieSinLetra = dniNie.substring(0, dniNie.length()-1);
			//
			String nie         = "";
			if(dniNieSinLetra.indexOf("X")!= -1 || dniNieSinLetra.indexOf("Y")!= -1 ) {
				nie         = dniNie;
			}
			//
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" select DNI, NOMB, APE1, APE2, ANON, MESN, DIAN, SEXO ");
			sbQuery.append(" from   indef.TBLFALLECIDOS f ");
			if(Misc.esVacio(nie)) {
				sbQuery.append(" where  f.DNI = ?   OR    f.DNI = ? ");
				//
				parametros.put("1", Misc.nz(dniNie));
			}
			else {
				sbQuery.append(" where  f.NIE = ?   OR    f.NIE = ? ");
				//
				parametros.put("1", Misc.nz(nie));
			}
			parametros.put("2", Misc.nz(dniNieSinLetra));
			query = sbQuery.toString();			
			//
			HashMap hashMapRaw = accesoBdIndef.consultaRaw(query, parametros);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			if (resultSet.next()) {
				indefBean = new IndefBean (resultSet);
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		finally {
			//
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e3) {
				resultSet = null;
			}
			//
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (Exception e4) {
				preparedStatement = null;
			}
		}
		return indefBean;
	}
	
	
	public boolean getUsuarioInDefByNIF (AccesoBdIndef accesoBdIndef, String dniNie) throws Exception {
		//
		boolean encontrado = false;
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		try {
			//logger.debug("INICIO");
			//
			// Se quita el ultimo caracter porque 
			dniNie = dniNie.substring(0, dniNie.length()-1);
			//
			if(Misc.esDigito(dniNie)) {
				StringBuffer sbQuery = new StringBuffer();
				sbQuery.append(" select * ");
				sbQuery.append(" from   indef.TBLFALLECIDOS f ");
				sbQuery.append(" where  F.DNI = ? ");
				query = sbQuery.toString();			
				//
				parametros.put("1", Misc.nz(dniNie));
				//
				Vector vResp = accesoBdIndef.consulta(query, parametros);
				if (!vResp.isEmpty()) {
					encontrado = true;
				}
			}
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
			logger.error("query:      " + query);
			logger.error("parametros: " + parametros);
			throw e;
		}
		return encontrado;
	}
	
}

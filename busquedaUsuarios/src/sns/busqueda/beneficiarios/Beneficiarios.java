package sns.busqueda.beneficiarios;

import gasai.util.Misc;

import java.io.FileWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import org.apache.log4j.Logger;
import sns.busqueda.model.BusquedaAproximadaHelper;
import sns.busqueda.model.NombreHelper;
import sns.comun.bd.AccesoBdRespaldo;
import sns.comun.bean.beneficiarios.BeneficiarioBean;
import sns.comun.bean.busqueda.BusquedaAproxResultadoBean;
import sns.comun.bean.busqueda.ConsultaBean;
import sns.comun.bean.busqueda.NombreComparacionBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;


public class Beneficiarios {
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);
	
	
	public static final void main(String[] args) throws Exception {
		//
		try {
			logger.debug("Inicio");
			//
			//String urlDestinoTxt = "D:/Procesos/Beneficiarios/";
			String urlDestinoTxt = "/sns10admin/busquedaUsuarios/";
			//
			Beneficiarios beneficiarios = new Beneficiarios ();
			beneficiarios.getFile(urlDestinoTxt);
			//
			logger.debug("FIN");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}

	public void getFile(String path) throws Exception {
		//
		AccesoBdRespaldo   accesoBd = null;
		//
		FileWriter fileWriter        = null;
		FileWriter fileWriterNombre  = null;
		//
		try {
			logger.debug("Inicio");
			//
			fileWriter       = new FileWriter(path + "2014-12-15_fichero.txt");
			fileWriterNombre = new FileWriter(path + "2014-12-15_ficheroNombre.txt");
			//		
			accesoBd = new AccesoBdRespaldo();
			//
			this.getInfoDniDuplicado(accesoBd, fileWriter, fileWriterNombre);
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
		finally {
			try {
				if (accesoBd != null) {
					accesoBd.cerrar();
				}
			}
			catch (Exception e1) {
				accesoBd = null;
			}
		}
		//
		try {
			if (fileWriter != null) {
				fileWriter.close();
			}
		} catch (Exception e5) {
			fileWriter = null;
		}
		try {
			if (fileWriterNombre != null) {
				fileWriterNombre.close();
			}
		}
		catch (Exception e4) {
			fileWriterNombre = null;
		}
	}
	  	
		
	private void getInfoDniDuplicado(AccesoBdRespaldo accesoBd, FileWriter fileWriter, FileWriter fileWriterNombre) throws Exception {
		//
		int contadorRegistros = 0;
		//
		String query = "";
		HashMap<String, String> parametros = new HashMap<String, String>();
		//
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		//
		try {
			logger.debug("INICIO");
			//		
			StringBuffer strBuffer=new StringBuffer();
			strBuffer.append(" select Z.ID_BEN, "); 
			strBuffer.append("    Z.DNI_NIE, ");
			strBuffer.append("    Z.NAF,    "); 
			strBuffer.append("    Z.NAF_TITULAR,    "); 
			strBuffer.append("    Z.IPF_TITULAR,  ");   
			strBuffer.append("    z.NOMBRE,  ");   
			strBuffer.append("    z.APELLIDO1, ");    
			strBuffer.append("    z.APELLIDO2,  ");   
			strBuffer.append("    z.SEXO,  ");   
			strBuffer.append("    z.FECHA_NACIMIENTO_RAW,  ");  
			strBuffer.append("    z.COD_INDICADOR_DE_FARMACIA, ");    
			strBuffer.append("    z.COD_SUBINDICADOR_DE_FARMACIA,  ");  
			strBuffer.append("    Z.TIPO_ASEGURAMIENTO, ");
			strBuffer.append("    b.COD_USUARIO_SNS,  ");   
			strBuffer.append("    b.DNI_NIE_SNS,    "); 
			strBuffer.append("    b.NAF_SNS,    "); 
			strBuffer.append("    b.NAF_TITULAR_SNS, ");    
			strBuffer.append("    b.COD_USUARIO_SNS_TITULAR,  ");   
			strBuffer.append("    b.NOMBRE_SNS, ");    
			strBuffer.append("    b.APELLIDO1_SNS,  ");   
			strBuffer.append("    b.APELLIDO2_SNS, ");    
			strBuffer.append("    b.COD_SEXO_SNS,   ");  
			strBuffer.append("    b.FECHA_NAC_SNS, ");   
			strBuffer.append("    b.INDICADOR_DE_FARMACIA_SNS,  ");   
			strBuffer.append("    b.SUBINDICADOR_SNS,  ");  
			strBuffer.append("    b.COD_TIPO_PROCEDENCIA,  "); 
			strBuffer.append("    b.COD_TITULO  ");  
			strBuffer.append(" from   Z_INSS_MOV_BEN_DIC_ALTA z, Z_Z_Z_BENEFICIARIOS b  ");  
			strBuffer.append(" where  Z.CODIGO_BADAS    = b.COD_USUARIO_SNS   "); 
			//strBuffer.append(" and    Z.ID_BEN between 10000 and 1000000   ");
			//strBuffer.append(" and    rownum < 100   "); 
			strBuffer.append(" order by Z.ID_BEN asc   "); 
			query = strBuffer.toString();
			//
			HashMap hashMapRaw = accesoBd.consultaRaw(query, parametros);
			preparedStatement  = (PreparedStatement) hashMapRaw.get("ps");
			resultSet          = (ResultSet) hashMapRaw.get("rs");
			//
			while (resultSet.next()) {
				//
				contadorRegistros++;
				//
				if (contadorRegistros % 10000 == 0) {
					// Numero de registros procesados
					logger.debug("Registros procesados: " + contadorRegistros);
				}
				//
				BeneficiarioBean beneficiarioBean = new BeneficiarioBean (resultSet);
				//
				ConsultaBean consultaBean = new ConsultaBean ();
				consultaBean.setNombre(beneficiarioBean.getNombre());
				consultaBean.setApellido1(beneficiarioBean.getApellido1());
				consultaBean.setApellido2(beneficiarioBean.getApellido2());
				consultaBean.setCodSexo(beneficiarioBean.getSexo());
				consultaBean.setFechaNac(beneficiarioBean.getFechaNacimientoRaw());		
				consultaBean.setDniNie(beneficiarioBean.getDniNie());
				consultaBean.setNaf(beneficiarioBean.getNaf());
				//
				ConsultaBean consultaBeanComparar = new ConsultaBean ();
				consultaBeanComparar.setNombre(beneficiarioBean.getNombreSns());
				consultaBeanComparar.setApellido1(beneficiarioBean.getApellido1Sns());
				consultaBeanComparar.setApellido2(beneficiarioBean.getApellido2Sns());
				consultaBeanComparar.setCodSexo(beneficiarioBean.getCodSexoSns());
				consultaBeanComparar.setFechaNac(beneficiarioBean.getFechaNacSns());
				consultaBeanComparar.setDniNie(beneficiarioBean.getDniNieSns());
				consultaBeanComparar.setNaf(beneficiarioBean.getNafSns());
				//
				BusquedaAproximadaHelper busquedaAproximadaHelper = new BusquedaAproximadaHelper ();
				BusquedaAproxResultadoBean busquedaAproxResultadoBean = busquedaAproximadaHelper.compararDatos(consultaBean, consultaBeanComparar);
				//
				//
				//
				// Si el NOMBRE no es IGUAL ni Aproximado, el registro solo difiere en el NOMBRE
				// Tenemos que realizar una comprobacion para una futura revision de estos registros por las CCAA.
				// Se comprueba el campo NOMBRE por el estudio de los gemelos
				//
				//
				if(Misc.nz(busquedaAproxResultadoBean.getCriterio()).indexOf(ConstantesBusqueda.CADENA_NOMBRE) == -1) {
					NombreHelper nombreHelper = new NombreHelper ();
					NombreComparacionBean nombreComparacionBean = new NombreComparacionBean ();
					nombreComparacionBean = nombreHelper.managerNombre(consultaBean.getNombre(), consultaBeanComparar.getNombre());
					busquedaAproxResultadoBean.setRevisionNombre(nombreComparacionBean.getRevision());
					nombreComparacionBean.setCodigo(beneficiarioBean.getCodUsuarioSns());
					//fileWriterNombre
					if(!Misc.esVacio(nombreComparacionBean.getCodigo())) {
						fileWriterNombre.write(nombreComparacionBean.write());
						fileWriterNombre.flush();
					}
				}
				//				
				//if(busquedaAproxResultadoBean.getPorcentaje() > 60) {
				escribirFichero (fileWriter, beneficiarioBean, busquedaAproxResultadoBean, "CODIGO_BADAS");
				//}
			}
			logger.debug("FIN  Registros procesados: " + contadorRegistros);
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());

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
	}	
	
	
	private void escribirFichero (FileWriter fileWriter, BeneficiarioBean beneficiarioBean, BusquedaAproxResultadoBean busquedaAproxResultadoBean, String tipoCampo) throws Exception {
		//
		try {
			//logger.debug("INICIO");
			//
			if(busquedaAproxResultadoBean == null){
				busquedaAproxResultadoBean = new BusquedaAproxResultadoBean ();
			}
			//
			StringBuffer str = new StringBuffer ();
			str.append(beneficiarioBean.toString());
			str.append(busquedaAproxResultadoBean.toString());
			str.append(tipoCampo + ConstantesBusqueda.SEPARADOR_CAMPOS);
			str.append("\n");
			//
			fileWriter.write(str.toString());
			//
			//logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			throw e;
		}
	}	

	
	
}

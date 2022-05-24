package sns.descargas;

import gasai.bd.AccesoBD;
import gasai.util.Misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import sns.comun.bd.AccesoBd;
import sns.comun.bean.descargas.NodoQueryBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;
import sns.comun.config.QuerysDescargas;


public class FreskiExcelQuery {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	private HSSFCellStyle cellStyleTitulo, cellStyleNormal;
	
		
	public FreskiExcelQuery (){
	}
	
	public static void main(String[] args) throws Exception {
		//
		try {
			logger.debug("INICIO");
			//
			FreskiExcelQuery freskiExcelQuery = new FreskiExcelQuery ();
			freskiExcelQuery.manager();
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage(), e);
			throw e;
		}
	}
	
	
	public void manager() throws Exception {
		//
		AccesoBd accesoBd = null;
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd       = new AccesoBd();
			//
			QuerysDescargas querysDescargas = new QuerysDescargas ();
			LinkedHashMap <String,NodoQueryBean> mapQuerys = new LinkedHashMap <String,NodoQueryBean>();	   
			//		
			NodoQueryBean nodo01 = new NodoQueryBean();
			//
			//			
			nodo01.setNombreHoja("01_coinciBA_MismaCA_CODSNS");
			nodo01.setQuery(this.getQueryCoincidentesMismaCA_CodSns());
			nodo01.setParametros(null);
			//
			////////////////////////////
			mapQuerys.put("1",  nodo01);
			////////////////////////////
			//
			//
			getExcelQuery(mapQuerys, accesoBd);
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		finally {
			try {
				if (accesoBd != null) {
					accesoBd.cerrar();
					logger.debug("CONENECTION_SNS CLOSE");
				}
			}
			catch (Exception e1) {
				accesoBd = null;
			}
		}
	}
	
	
	
	public String getQueryCoincidentesMismaCA_CodSns () throws Exception {
		//
		String query = "";
		//
		try {
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(" 		SELECT     xxxxxxx ");
			sbQuery.append(" 		FROM        xxxxxx C  ");
			sbQuery.append(" 		where       tiporesultado        = 'COINCIDENTES_MISMA_CA_BA'");
			sbQuery.append(" 		AND         tipocampo            = 'CODSNS'   ");
			sbQuery.append(" 		AND         INDEF                = 0 ");
			query = sbQuery.toString();
		}
		catch (Exception e) {
			logger.error("Exception: " + e);
			throw e;
		}
		return query;
	}	

	
	public void getExcelQuery(LinkedHashMap<String, NodoQueryBean> mapQuerys, AccesoBD accesoBD) throws Exception {
		//
		int numeroColumnas;
		//
		FileInputStream fileInputStream   = null;
		FileOutputStream fileOutputStream = null;
		//
		HSSFWorkbook hSSFWorkbook = null;
		//
		HSSFSheet hoja;
		HSSFRow fila;
		HSSFCell celda;
		HSSFRichTextString texto;
		//
		ResultSet resultSet = null;
		//		
		FileWriter fileWriter  = null;
		//
		try {
			logger.debug("INICIO");
			//
			fileOutputStream = new FileOutputStream(InicializacionBusqueda.PATH_LOCAL_DESCARGAS + InicializacionBusqueda.EXCEL_NOMBRE);
			//
			// Si quiero usar una plantilla
			String plantilla = InicializacionBusqueda.PATH_LOCAL_DESCARGAS + InicializacionBusqueda.PLANTILLA_EXCEL_NOMBRE;
			fileInputStream = new FileInputStream(plantilla);
			POIFSFileSystem fs = new POIFSFileSystem(fileInputStream);
			//
			hSSFWorkbook = new HSSFWorkbook(fs);
			//
			// Se establecen los estilos
			getEstiloTitulo(hSSFWorkbook);
			getEstiloNormal(hSSFWorkbook);
			//
			Iterator<Entry<String, NodoQueryBean>> iterator = mapQuerys.entrySet().iterator();
			//
			while (iterator.hasNext()) {
				//
				Entry <String, NodoQueryBean> entry = iterator.next();
				NodoQueryBean nodoAux = entry.getValue();
				//
				HashMap hashMapRaw = accesoBD.consultaRaw(nodoAux.getQuery(), nodoAux.getParametros());
				resultSet = (ResultSet) hashMapRaw.get("rs");
				//
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				// utilizamos la plantilla -- Se crea la nueva Hoja
				hoja = hSSFWorkbook.cloneSheet(0);
				int hojas = hSSFWorkbook.getNumberOfSheets();
				hSSFWorkbook.setSheetName((hojas - 1), nodoAux.getNombreHoja());
				//
				// // //     FICHERO  TXT   // // //
				fileWriter = new FileWriter(InicializacionBusqueda.PATH_LOCAL_DESCARGAS + nodoAux.getNombreHoja() + ".txt");
				StringBuffer stringBufferCabecera = new StringBuffer();
				StringBuffer stringBufferFila     = new StringBuffer();
				// // //     FICHERO  TXT   // // //
				//
				fila = hoja.createRow(ConstantesBusqueda.FILA_INICIAL_EXCEL);
				//
				int columna             = 0;
				int numeroColumnasTotal = resultSetMetaData.getColumnCount();
				//
				for (columna=0; columna<numeroColumnasTotal ; columna++) {
					celda = fila.createCell(columna);
					texto = new HSSFRichTextString(resultSetMetaData.getColumnName(columna + 1));
					celda.setCellValue(texto);
					celda.setCellStyle(cellStyleTitulo);
					//
					// // //     FICHERO  TXT   // // //
					stringBufferCabecera.append(texto + ConstantesBusqueda.SEPARADOR_CAMPOS);
					// // //     FICHERO  TXT   // // //
				}
				//
				// // //     FICHERO  TXT   // // //
				fileWriter.write(stringBufferCabecera.toString() + "\n");
				stringBufferCabecera.delete(0, stringBufferCabecera.length());
				// // //     FICHERO  TXT   // // //
				//
				numeroColumnas = columna;
				int numerofila = ConstantesBusqueda.FILA_INICIAL_EXCEL + 1;
				//
				int contador = 0;
				//
				while (resultSet.next()) {
					//
					contador++;
					fila = hoja.createRow(numerofila);
					//
					int columnaDatos = 0;
					//					
					for (columnaDatos = 0; columnaDatos < numeroColumnas; columnaDatos++) {
						celda = fila.createCell(columnaDatos);
						String textoCadena = Misc.nz(resultSet.getString(resultSetMetaData.getColumnName(columnaDatos + 1)));
						//
						if(textoCadena.length() == 21 && textoCadena.indexOf("00:00:00.0")!=-1){
							textoCadena = textoCadena.substring(0, 10);
							//1930-11-01 00:00:00.0
						}						
						texto =  new HSSFRichTextString(Misc.nz(textoCadena));
						//texto = new HSSFRichTextString(Misc.nz(resultSet.getString(resultSetMetaData.getColumnName(columnaDatos + 1))));
						//
						
						
						
						celda.setCellValue(texto);
						celda.setCellStyle(cellStyleNormal);
						//
						// // //     FICHERO  TXT   // // //
						stringBufferFila.append(texto + ConstantesBusqueda.SEPARADOR_CAMPOS);
						// // //     FICHERO  TXT   // // //
						//	
					}
					numerofila++;
					//
					//// // //     FICHERO  TXT   // // //
					fileWriter.write(stringBufferFila.toString() + "\n");
					fileWriter.flush();
					stringBufferFila.delete(0, stringBufferFila.length());
					// // //     FICHERO  TXT   // // //
					//
				}
				//
				// // //     FICHERO  TXT   // // //
				if (fileWriter != null) {
					fileWriter.close();
				}
				// // //     FICHERO  TXT   // // //
				//
				//
				// Si el fichero es vacio se borra
				if(contador==0) {
					File file = new File (InicializacionBusqueda.PATH_LOCAL_DESCARGAS + nodoAux.getNombreHoja() + ".txt");
					file.delete();
				}
				
			}
			// Borro la hoja que estoy tomando como plantilla
			hSSFWorkbook.removeSheetAt(0);
			// Guardo en el fichero el libro
			hSSFWorkbook.write(fileOutputStream);
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception:  " + e.getMessage());
			throw e;
		}
		finally {
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
			if (resultSet != null) {
				resultSet.close();
			}
			//
			// // //     FICHERO  TXT   // // //
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			} catch (Exception e1) {
				fileWriter = null;
			}
			// // //     FICHERO  TXT   // // //
		}
	}

	
	private void getEstiloTitulo(HSSFWorkbook hSSFWorkbook) {
		cellStyleTitulo         = hSSFWorkbook.createCellStyle();
		HSSFFont fontTituloHoja = hSSFWorkbook.createFont();
		//
		cellStyleTitulo.setFillForegroundColor(IndexedColors.BLUE_GREY.getIndex());
		cellStyleTitulo.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyleTitulo.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyleTitulo.setBorderTop(CellStyle.BORDER_THIN);
		cellStyleTitulo.setBorderRight(CellStyle.BORDER_THIN);
		cellStyleTitulo.setBorderLeft(CellStyle.BORDER_THIN);
		//
		fontTituloHoja.setFontName("Calibri");
		fontTituloHoja.setFontHeightInPoints((short) 12);
		fontTituloHoja.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontTituloHoja.setColor(IndexedColors.WHITE.getIndex());
		//
		cellStyleTitulo.setFont(fontTituloHoja);
	}
	

	private void getEstiloNormal(HSSFWorkbook libro) {
		cellStyleNormal         = libro.createCellStyle();
		HSSFFont fontTituloHoja = libro.createFont();
		//
		cellStyleNormal.setFillForegroundColor(IndexedColors.WHITE.getIndex());
		cellStyleNormal.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cellStyleNormal.setBorderBottom(CellStyle.BORDER_THIN);
		cellStyleNormal.setBorderTop(CellStyle.BORDER_THIN);
		cellStyleNormal.setBorderRight(CellStyle.BORDER_THIN);
		cellStyleTitulo.setBorderLeft(CellStyle.BORDER_THIN);
		//
		fontTituloHoja.setFontName("Arial");
		fontTituloHoja.setFontHeightInPoints((short) 8);
		//
		cellStyleNormal.setFont(fontTituloHoja);
	}
	

}

package sns.descargas;

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
import sns.comun.bd.AccesoBdCruces;
import sns.comun.bean.descargas.NodoQueryBean;
import sns.comun.config.ConstantesBusqueda;
import sns.comun.config.InicializacionBusqueda;
import sns.comun.config.QuerysDescargas;
import sns.comun.config.QuerysDescargasRevisionCA;


public class ExcelQuery {

	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	private HSSFCellStyle cellStyleTitulo, cellStyleNormal;
	
		
	public ExcelQuery (){
	}
	
	public static void main(String[] args) throws Exception {
		//
		try {
			logger.debug("INICIO");
			//
			ExcelQuery excelQuery = new ExcelQuery ();
			excelQuery.manager();
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
		AccesoBdCruces accesoBd = null;
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd       = new AccesoBdCruces();
			//
			QuerysDescargas querysDescargas = new QuerysDescargas ();
			QuerysDescargasRevisionCA querysDescargasRevisionCA = new QuerysDescargasRevisionCA ();
			//
			LinkedHashMap <String,NodoQueryBean> mapQuerys = new LinkedHashMap <String,NodoQueryBean>();	   
			//		
			NodoQueryBean nodo01 = new NodoQueryBean();
			NodoQueryBean nodo02 = new NodoQueryBean();
			NodoQueryBean nodo03 = new NodoQueryBean();
			NodoQueryBean nodo04 = new NodoQueryBean();
			NodoQueryBean nodo05 = new NodoQueryBean();
			NodoQueryBean nodo06 = new NodoQueryBean();
			NodoQueryBean nodo07 = new NodoQueryBean();
			NodoQueryBean nodo08 = new NodoQueryBean();
			NodoQueryBean nodo09 = new NodoQueryBean();
			NodoQueryBean nodo10 = new NodoQueryBean();
			NodoQueryBean nodo11 = new NodoQueryBean();
			NodoQueryBean nodo12 = new NodoQueryBean();
			NodoQueryBean nodo13 = new NodoQueryBean();
			NodoQueryBean nodo14 = new NodoQueryBean();
			NodoQueryBean nodo15 = new NodoQueryBean();
			NodoQueryBean nodo16 = new NodoQueryBean();
			NodoQueryBean nodo17 = new NodoQueryBean();
			NodoQueryBean nodo18 = new NodoQueryBean();
			//
			// Los de revision por parte de la CA
			NodoQueryBean nodo19 = new NodoQueryBean();
			NodoQueryBean nodo20 = new NodoQueryBean();
			NodoQueryBean nodo21 = new NodoQueryBean();
			NodoQueryBean nodo22 = new NodoQueryBean();
			NodoQueryBean nodo23 = new NodoQueryBean();
			//
			//
			//
			NodoQueryBean nodo30 = new NodoQueryBean();
			NodoQueryBean nodo31 = new NodoQueryBean();
			NodoQueryBean nodo32 = new NodoQueryBean();
			NodoQueryBean nodo33 = new NodoQueryBean();
			//
			//			
			
			
			NodoQueryBean nodo41 = new NodoQueryBean();
			NodoQueryBean nodo42 = new NodoQueryBean();
			NodoQueryBean nodo43 = new NodoQueryBean();
			NodoQueryBean nodo44 = new NodoQueryBean();
			
			/*
			nodo01.setNombreHoja("01_coinBA_MismaCA_CODSNS");
			nodo01.setQuery(querysDescargas.getQueryCoincidentesMismaCA_CodSns());
			nodo01.setParametros(null);
			//
			nodo02.setNombreHoja("02_coinBA_MismaCA_IDSSALUD");
			nodo02.setQuery(querysDescargas.getQueryCoincidentesId());
			nodo02.setParametros(null);
			//
			nodo03.setNombreHoja("03_coinBA_MismaCA_DNI");
			nodo03.setQuery(querysDescargas.getQueryCoincidentesMismaCA_Dni());
			nodo03.setParametros(null);
			//
			nodo04.setNombreHoja("04_coinBA_MismaCA_NAF");//////////////////////////////////////////////  CAMBIA
			nodo04.setQuery(querysDescargas.getQueryCoincidentesMismaCA_Naf());
			nodo04.setParametros(null);
			//
			nodo05.setNombreHoja("05_coinBA_MismaCA_RAIZ");//////////////////////////////////////////////  CAMBIA
			nodo05.setQuery(querysDescargas.getQueryCoincidentesMismaCA_Raiz());
			nodo05.setParametros(null);
			//
			////////////////////////////////////////////////////////////////////////////			
			//			
			nodo06.setNombreHoja("06_coinBA_DifCA_CODSNS");
			nodo06.setQuery(querysDescargas.getQueryCoincidentesDiferenteCA_CodSns());
			nodo06.setParametros(null);
			//
			nodo07.setNombreHoja("07_coinBA_DifCA_DNI");
			nodo07.setQuery(querysDescargas.getQueryCoincidentesDiferenteCA_Dni());
			nodo07.setParametros(null);
			//
			nodo08.setNombreHoja("08_coinBA_DifCA_NAF");//////////////////////////////////////////////  CAMBIA
			nodo08.setQuery(querysDescargas.getQueryCoincidentesDiferenteCA_Naf());
			nodo08.setParametros(null);
			//
			nodo09.setNombreHoja("09_coinBA_DifCA_RAIZ");//////////////////////////////////////////////  CAMBIA
			nodo09.setQuery(querysDescargas.getQueryCoincidentesDiferenteCA_Raiz());
			nodo09.setParametros(null);
			//
			////////////////////////////////////////////////////////////////////////////
			//
			nodo10.setNombreHoja("10_dniNaf");
			nodo10.setQuery(querysDescargas.getQueryDniNafIguales());
			nodo10.setParametros(null);
			//
			nodo11.setNombreHoja("11_noCoinBA_coinVincDNI");
			nodo11.setQuery(querysDescargas.getQueryNoCoincidentesInssVinculadosDni());
			nodo11.setParametros(null);
			//
			nodo12.setNombreHoja("12_noCoinBA_coinVincOtros");//////////////////////////////////////////////  CAMBIA
			nodo12.setQuery(querysDescargas.getQueryNoCoincidentesInssVinculadosOtros());
			nodo12.setParametros(null);
			//
			//nodo13.setNombreHoja("13_noCoinBA_DNI");
			//nodo13.setQuery(querysDescargas.getQueryNoCoincidentesBADni());
			//nodo13.setParametros(null);
			//
			nodo14.setNombreHoja("14_noCoinBA_OtrosCriterios");
			nodo14.setQuery(querysDescargas.getQueryNoCoincidentesBAOtros());
			nodo14.setParametros(null);
			//
			nodo15.setNombreHoja("15_IndiceDef");
			nodo15.setQuery(querysDescargas.getQueryIndiceDef());
			nodo15.setParametros(null);
			//
			//
			nodo16.setNombreHoja("24_duplicados");
			nodo16.setQuery(querysDescargas.getQueryDuplicados());
			nodo16.setParametros(null);
			//
			nodo17.setNombreHoja("25_noEncontrados");
			nodo17.setQuery(querysDescargas.getQueryNoEncontrados());
			nodo17.setParametros(null);
			//
			nodo18.setNombreHoja("26_Vacios");
			nodo18.setQuery(querysDescargas.getQueryVacios());
			nodo18.setParametros(null);
			//
			////////////////////////////////////////////// Los de revision por parte de la CA  //////////////////////////////////////////////
			nodo19.setNombreHoja("19_coinBA_MismaCA_NAF - REV");  
			nodo19.setQuery(querysDescargasRevisionCA.getQueryCoincidentesMismaCA_Naf_Revision());
			nodo19.setParametros(null);
			//
			nodo20.setNombreHoja("20_coinBA_MismaCA_RAIZ - REV");
			nodo20.setQuery(querysDescargasRevisionCA.getQueryCoincidentesMismaCA_Raiz_Revision());
			nodo20.setParametros(null);
			//
			nodo21.setNombreHoja("21_coinBA_DifCA_NAF - REV");
			nodo21.setQuery(querysDescargasRevisionCA.getQueryCoincidentesDiferenteCA_Naf_Revision());
			nodo21.setParametros(null);
			//
			nodo22.setNombreHoja("22_coinBA_DifCA_RAIZ - REV");
			nodo22.setQuery(querysDescargasRevisionCA.getQueryCoincidentesDiferenteCA_Raiz_Revision());
			nodo22.setParametros(null);
			//
			nodo23.setNombreHoja("23_noCoinBA_VincOtros - REV");
			nodo23.setQuery(querysDescargasRevisionCA.getQueryNoCoincidentesInssVinculadosOtros_Revision());
			nodo23.setParametros(null);
			/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			//
			//
			//
			nodo30.setNombreHoja("30_NoCoinBACodSns");
			nodo30.setQuery(querysDescargas.getQueryNoCoincidentesBACodSns());
			nodo30.setParametros(null);
			//			
			nodo31.setNombreHoja("31_RevisarNombreDatos");
			nodo31.setQuery(querysDescargas.getQueryRevisarNombreDatos());
			nodo31.setParametros(null);
			//
			nodo32.setNombreHoja("32_RevisarNombreDniNaf");
			nodo32.setQuery(querysDescargas.getQueryRevisarNombreDniNaf());
			nodo32.setParametros(null);
			//
			nodo33.setNombreHoja("33_RevisarNombreVinc");
			nodo33.setQuery(querysDescargas.getQueryRevisarNombreVinculados());
			nodo33.setParametros(null);
			//
			//
			//
			
			
			
			
			
			
			mapQuerys.put("1",  nodo01);
			mapQuerys.put("2",  nodo02);
			mapQuerys.put("3",  nodo03);
			mapQuerys.put("4",  nodo04);
			mapQuerys.put("5",  nodo05);
			//
			//
			mapQuerys.put("6",  nodo06);
			mapQuerys.put("7",  nodo07);
			mapQuerys.put("8",  nodo08);
			//
			mapQuerys.put("9",  nodo09);
			mapQuerys.put("10", nodo10);
			mapQuerys.put("11", nodo11);
			mapQuerys.put("12", nodo12);
			//mapQuerys.put("13", nodo13);
			mapQuerys.put("14", nodo14);
			mapQuerys.put("15", nodo15);
			//

			// Los de revision por parte de la CA
			mapQuerys.put("19", nodo19);
			mapQuerys.put("20", nodo20);
			mapQuerys.put("21", nodo21);
			mapQuerys.put("22", nodo22);
			mapQuerys.put("23", nodo23);
			

			
			
			//
			//

			mapQuerys.put("16", nodo16);
			mapQuerys.put("17", nodo17);
			mapQuerys.put("18", nodo18);
			*/
			
			


			//
			nodo41.setNombreHoja("41_nafTit_Nombre");
			nodo41.setQuery(querysDescargas.getQueryCoincidentes_NaftTitular_Nombre());
			nodo41.setParametros(null);
			//			
			nodo42.setNombreHoja("42_raiz_Nombre");
			nodo42.setQuery(querysDescargas.getQueryCoincidentes_Raiz_Nombre());
			nodo42.setParametros(null);
			//
			nodo43.setNombreHoja("43_raiz_Apellidos");
			nodo43.setQuery(querysDescargas.getQueryCoincidentes_Raiz_Apellidos());
			nodo43.setParametros(null);
			//
			nodo44.setNombreHoja("44_raiz_Apellido1");
			nodo44.setQuery(querysDescargas.getQueryCoincidentes_Raiz_Apellido1());
			nodo44.setParametros(null);
			
			
			mapQuerys.put("41", nodo41);
			mapQuerys.put("42", nodo42);
			mapQuerys.put("43", nodo43);
			mapQuerys.put("44", nodo44);
			
			
			
			
			
			
		
			//
			/*
			mapQuerys.put("30", nodo30);
			mapQuerys.put("31", nodo31);
			mapQuerys.put("32", nodo32);
			mapQuerys.put("33", nodo33);
			*/
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

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void manager2() throws Exception {
		//
		AccesoBdCruces accesoBd = null;
		//
		try {
			logger.debug("INICIO");
			//
			accesoBd       = new AccesoBdCruces();
			//
			QuerysDescargas querysDescargas = new QuerysDescargas ();
			LinkedHashMap <String,NodoQueryBean> mapQuerys = new LinkedHashMap <String,NodoQueryBean>();	   
			//		
			NodoQueryBean nodo1  = new NodoQueryBean();
			NodoQueryBean nodo2  = new NodoQueryBean();
			NodoQueryBean nodo3  = new NodoQueryBean();
			NodoQueryBean nodo4  = new NodoQueryBean();
			NodoQueryBean nodo5  = new NodoQueryBean();
			NodoQueryBean nodo6  = new NodoQueryBean();
			NodoQueryBean nodo7  = new NodoQueryBean();
			NodoQueryBean nodo8  = new NodoQueryBean();
			NodoQueryBean nodo9  = new NodoQueryBean();
			NodoQueryBean nodo10 = new NodoQueryBean();
			NodoQueryBean nodo11 = new NodoQueryBean();
			//
			//			
			nodo1.setNombreHoja("01_CoincidentesId");
			nodo1.setQuery(querysDescargas.getQueryCoincidentesId());
			nodo1.setParametros(null);
			//
			nodo2.setNombreHoja("02_dniNaf");
			nodo2.setQuery(querysDescargas.getQueryDniNafIguales());
			nodo2.setParametros(null);
			//
			nodo3.setNombreHoja("03_noCoincidentesBA_coincidentesVinculados");
			nodo3.setQuery(querysDescargas.getQueryNoCoincidentesInssVinculados());
			nodo3.setParametros(null);
			//
			nodo4.setNombreHoja("04_coincidentesBA_MismaCA");
			nodo4.setQuery(querysDescargas.getQueryCoincidentesMismaCA());
			nodo4.setParametros(null);
			//
			nodo5.setNombreHoja("05_coincidentesBA_DiferenteCA");
			nodo5.setQuery(querysDescargas.getQueryCoincidentesDiferenteCA());
			nodo5.setParametros(null);
			
			//nodo5b.setNombreHoja("05_coincidentesBA_DiferenteCA");
			//nodo5b.setQuery(querysDescargas.getQueryCoincidentesDiferenteCA());
			//nodo5b.setParametros(null);
			
			//			//
			nodo6.setNombreHoja("06_noCoincidentesBA_DNI");
			nodo6.setQuery(querysDescargas.getQueryNoCoincidentesBADni());
			nodo6.setParametros(null);
			//
			nodo7.setNombreHoja("07_noCoincidentesBA_OtrosCriterios");
			nodo7.setQuery(querysDescargas.getQueryNoCoincidentesBAOtros());
			nodo7.setParametros(null);
			//
			nodo8.setNombreHoja("08_IndiceDef");
			nodo8.setQuery(querysDescargas.getQueryIndiceDef());
			nodo8.setParametros(null);
			//
			nodo9.setNombreHoja("09_duplicados");
			nodo9.setQuery(querysDescargas.getQueryDuplicados());
			nodo9.setParametros(null);
			//
			nodo10.setNombreHoja("10_noEncontrados");
			nodo10.setQuery(querysDescargas.getQueryNoEncontrados());
			nodo10.setParametros(null);
			//
			nodo11.setNombreHoja("11_Vacios");
			nodo11.setQuery(querysDescargas.getQueryVacios());
			nodo11.setParametros(null);
			//
			//
			//			
			mapQuerys.put("1",  nodo1);
			mapQuerys.put("2",  nodo2);
			mapQuerys.put("3",  nodo3);
			mapQuerys.put("4",  nodo4);
			mapQuerys.put("5",  nodo5);
			mapQuerys.put("6",  nodo6);
			mapQuerys.put("7",  nodo7);
			mapQuerys.put("8",  nodo8);
			mapQuerys.put("9",  nodo9);
			mapQuerys.put("10", nodo10);
			mapQuerys.put("11", nodo11);
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
	
	
	public void getExcelQuery(LinkedHashMap<String, NodoQueryBean> mapQuerys, AccesoBdCruces accesoBD) throws Exception {
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

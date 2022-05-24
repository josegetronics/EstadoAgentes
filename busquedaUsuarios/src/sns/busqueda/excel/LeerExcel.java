package sns.busqueda.excel;

import gasai.util.Misc;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import java.io.FileInputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import sns.comun.config.InicializacionBusqueda;


public class LeerExcel {


	public LeerExcel() {
	}
	
	
	private static Logger logger = Logger.getLogger(InicializacionBusqueda.LOGGER_NAME);

	
	public static void main(String[] args) {
		//
		File file             = null;
		FileWriter fileWriter = null;
		//
		try {
			logger.debug("INICIO");
			//
			String path = "D:/Procesos/LeerExcel/"; 
	    	//
			file       = new File(path);
			fileWriter = new FileWriter(path + "prueba.txt");
			//
			//
		    // Array con los ficheros y carpetas contenidos
		    File[] arrayContentFolder = file.listFiles(new Filtro());
			//
		    for (int i = 0; i < arrayContentFolder.length; i++) {
		    	//
		    	if (arrayContentFolder[i].isFile()) {
		    		logger.debug("Nombre: " + arrayContentFolder[i].getName());
		    		logger.debug("Nombre: " + arrayContentFolder[i].getAbsolutePath());
			    	//
		    		String pathFichero = arrayContentFolder[i].getAbsolutePath();
					//
					String nameFile = pathFichero.substring(pathFichero.lastIndexOf("\\")+1, pathFichero.length());
					logger.debug("nameFile: " + nameFile);
					String fecha = nameFile.substring(0, nameFile.indexOf("_"));
					logger.debug("fecha: " + fecha);
					logger.debug("");
					//
					//
					LeerExcel leerExcel = new LeerExcel ();
					int posicion = leerExcel.buscarInicio(pathFichero, fecha);
					logger.debug("posicion: " + posicion);
					
					leerExcel.leer(fileWriter, pathFichero, fecha, posicion);
		    	}
		    }
		    //
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			e.printStackTrace();
			logger.error("Exception: " + e.getMessage());
		}
		finally {
			try {
				if (fileWriter != null) {
					fileWriter.close();
				}
			}
			catch (Exception e) {
				fileWriter = null;
			}
		}
	}	

	
	public void leer(FileWriter fileWriter, String url, String fecha, int posicion) throws Exception {
		
		try {
			logger.debug("INICIO");

			InputStream inputStream = new FileInputStream(url);
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			//
			HSSFSheet sheet = workbook.getSheet("INCIDENCIAS SOPORTE");
			//
			int indice = posicion;
			//
			HSSFRow row = sheet.getRow(posicion);

			while (row != null) {
				//
				//logger.debug("indice: " + indice);
				//
				for (int i = 1; i < 2; i++) {
					//
					HSSFCell cell = row.getCell(i);
					//
					if(cell!=null){
						//
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							logger.debug(" Cadena: " + cell.getStringCellValue() + ", ");
						}
						else {
							if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
								//logger.debug(" Numero: " + cell.getNumericCellValue());
								Double doubleGG = new Double(cell.getNumericCellValue());
								long valor = doubleGG.longValue();
								//logger.debug("valor: " + valor);
								//							
								StringBuffer str = new StringBuffer ();
								str.append(fecha + "|");
								str.append(valor + "|");
								str.append("\n");
								fileWriter.write(str.toString());
							}
							else {
								logger.debug("No es cadena ni numerico");
							}
						}
					}
					else {
						// Son filas vacias, pero q cuentan como row a nivel de excel 
						logger.debug("00000000000000000000000000000000000000000000000000000000000000000000      " + indice);			
					}
				}
				indice++;
				row = sheet.getRow(indice);
			}		
			//
			fileWriter.flush();
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
	}

	
	
	
	
	public int buscarInicio(String url, String fecha) throws Exception {
		//
		int posicion = 0;
		boolean encontrado = false;
		//
		try {
			logger.debug("INICIO");

			InputStream inputStream = new FileInputStream(url);
			HSSFWorkbook workbook = new HSSFWorkbook(inputStream);
			//
			HSSFSheet sheet = workbook.getSheet("INCIDENCIAS SOPORTE");
			//
			int indice = 0;
			//
			while (!encontrado && indice<100) {
				//
				logger.debug("indice: " + indice);
				//
				HSSFRow row = sheet.getRow(indice);
				//
				if(row != null ) {
					//
					HSSFCell cell = row.getCell(1);
					//
					if(cell!=null){
						if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
							logger.debug(" Cadena: " + cell.getStringCellValue() + ", ");
							//
							if(Misc.nz(cell.getStringCellValue()).equals("TIPO")) {
								encontrado = true;
								posicion = row.getRowNum();
								//logger.debug("xxxxxxxxxxxxxxxxxxxxxxxxxx");
							}
						}
					}
				}
				//
				indice++;
			}		
			//
			if(!encontrado) {
				logger.debug("XXXXXXXXXXXXX   NO ENCONTRADO TIPO EN FICHERO " + " fecha " + "XXXXXXXXXXXXXXX");
			}
			//
			logger.debug("FIN");
		}
		catch (Exception e) {
			logger.error("Exception " + e.getMessage());
			e.printStackTrace();
			throw e;
		}
		return posicion;
	}
	  
}	
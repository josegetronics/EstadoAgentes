package sns.mantenimiento;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import sns.config.Constantes;
import sns.config.Inicializacion;

public class CacheoHelper {
  static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(CacheoHelper.class);

  public CacheoHelper() {
  }

  public String cachearXml(String xml,int codXmlEntrada) throws IOException {
    PrintWriter pout=null;
    try {
      String ficheroXmlCacheado = Inicializacion.PATH_REPOSITORIO_COMPARTIDO + "/" + codXmlEntrada + "_" + Inicializacion.IDSERVICIO + Constantes.PREFIJO_XML_SERVICIO + Constantes.EXTENSION_XML_SERVICIO;

      pout = new PrintWriter(new BufferedWriter(new FileWriter(ficheroXmlCacheado)));
      pout.print(xml);

      return ficheroXmlCacheado;

    }catch (IOException e) {
      throw e;
    }finally {
         if (pout!=null) {
             pout.flush();
             pout.close();
         }
    }

  }

  public String leerFichero(String fichero,boolean borrarFichero) throws Exception {
    BufferedReader br = null;
    FileReader fr=null;
    File ficheroXmlSalida=null;
    try {
      ficheroXmlSalida=new File(fichero);
      fr=new FileReader(ficheroXmlSalida);
      br = new BufferedReader(fr);
      String nextLine = "";
      StringBuffer sb = new StringBuffer();
      while ( (nextLine = br.readLine()) != null) {
        sb.append(nextLine);
      }
      // Convert the content into to a string
      return sb.toString();
    }
    finally {
      if (br != null) {
        br.close();
      }
      if (fr != null) {
        fr.close();
      }

      if (ficheroXmlSalida!=null && borrarFichero) {
        boolean borrado=ficheroXmlSalida.delete();
        logger.debug("Fichero de xml salida borrado -> [" + ficheroXmlSalida.getName() + "] -> " + borrado);
      }
    }
  }

}

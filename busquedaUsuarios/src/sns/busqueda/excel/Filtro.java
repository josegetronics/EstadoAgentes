package sns.busqueda.excel;

import java.io.FileFilter;
import java.io.File;

public class Filtro implements FileFilter{


  public Filtro() {
  }

  public String getDescription() {
    return "descripcion del filtro";
  }

  public boolean accept(File testFile) {
    return testFile.getName().toLowerCase().endsWith(".xls") || testFile.getName().toLowerCase().endsWith(".xlsx");
  }

}




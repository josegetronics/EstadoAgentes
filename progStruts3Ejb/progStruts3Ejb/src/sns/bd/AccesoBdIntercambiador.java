package sns.bd;

import gasai.bd.AccesoBD;


public class AccesoBdIntercambiador extends AccesoBD {

  public AccesoBdIntercambiador() {
	 super("ora-intercam.msc.es", "2008", "INTERCAM", "UsuarioNominal", "Password");
  }
}

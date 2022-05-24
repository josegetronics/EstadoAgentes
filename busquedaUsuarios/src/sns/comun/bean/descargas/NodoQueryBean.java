package sns.comun.bean.descargas;

import java.util.HashMap;

public class NodoQueryBean{	  
	  	
	public String query;
	public HashMap<String, String> parametros = new HashMap<String, String>();
	public String nombreHoja;
	
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public HashMap<String, String> getParametros() {
		return parametros;
	}
	public void setParametros(HashMap<String, String> parametros) {
		this.parametros = parametros;
	}
	public String getNombreHoja() {
		return nombreHoja;
	}
	public void setNombreHoja(String nombreHoja) {
		this.nombreHoja = nombreHoja;
	}
	
	
}
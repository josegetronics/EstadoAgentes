package sns.util;

import gasai.util.out.Salida;

public interface IOperacionesNif {
	public String genera(String sdni);
	public Salida valida(String elNif);
	public String formateoNie (String agente,String nie);
	
}

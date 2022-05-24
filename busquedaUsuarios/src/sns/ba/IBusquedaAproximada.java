package sns.ba;

import java.util.ArrayList;

import sns.model.IDatosIdentificativos;

public interface IBusquedaAproximada {
	/**
	 * PersonaActual es el individuo que estamos tratando en el proceso
	 * candidatoExiste es el individuo que ya existe en la base de datos
	 * criterioEmparejamiento es la coleccion de emparejamiento. 
	 * 				Estos criterios se deben tener en cuenta en los pesos asignados y no se deberan chequear
	 * 				dentro de la BA. Es decir, si un criterio es NAF_SEC1 no se debe comparar el NAF, porque
	 * 				nos lo creemos pero si asignar el peso como cuando tienen el NAF igual.
	 * 
	 * @param personaActual
	 * @param candidatoExistente
	 * @param criterioEmparejamiento
	 * @return
	 */
	public IRespuestaBa comparar(IDatosIdentificativos personaActual,IDatosIdentificativos candidatoExistente,ArrayList<String> criterioEmparejamiento) throws Exception;
}

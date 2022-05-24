package sns.model;

import sns.util.out.Salida;

public interface ITransaccionesHelper {
  public Salida start(String codUsuarioSns,Integer operacionMaestra);

  public void release(String codUsuarioSns,Integer operacionMaestra);
}

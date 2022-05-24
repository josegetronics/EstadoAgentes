package sns.util;

import gasai.util.Misc;
import sns.config.Inicializacion;

public class ValidaNaf {

  public ValidaNaf(){
  }

  public static void main(String[] args) {
    ValidaNaf naf = new ValidaNaf();

    
    ValidaNaf validaNaf = new ValidaNaf();
    OperacionesSns sns = new OperacionesSns("BBBBBBBBDL208595");
    String numero=Misc.rellenarIzq(sns.convierte(),"0",8);
    String digitosControlNaf=validaNaf.digitosControl("88" + numero);
    
    System.out.println("88" + numero + digitosControlNaf);

    String nafIn="6099999999";

    String digitos=naf.digitosControl(nafIn);

    System.out.println(nafIn + digitos);

    String nafValidar="011004757003";

    boolean ok=naf.valida(nafValidar);

    System.out.println("ok -> " + ok);

    if (true) {
      System.exit(-1);
    }

//    String nafArg="28616058300";
    String nafArg="28620859800";

    //280620859853

    System.out.println("long: " + nafArg.length());

    char[] cArray=nafArg.toCharArray();
    char[] cArrayAux=new char[10];
    int j=0;
    for (int i=0;i<(cArray.length-2);i++) {
      if (i==2) {
        cArrayAux[j]='0';
        j++;
      }
      cArrayAux[j]=cArray[i];
      j++;
    }

    String raizNafCorregido=new String(cArrayAux);

    String nafCorregido=raizNafCorregido + naf.digitosControl(raizNafCorregido);

    System.out.println("naf ->" + nafCorregido);
    System.out.println(naf.valida("111056907065"));

/*
     System.out.println("naf->" + nafArg);
     System.out.println("nafAux->" + new String(cArrayAux));
     System.out.println("digitos ->" + naf.digitosControl("2806160583"));

     System.out.println("nafAux+Digitos -> " + new String(cArrayAux) + naf.digitosControl("2806160583"));
*/
//     System.out.println(naf.valida(args[0]));
  }

  public String generarNafFicticio(String codUsuarioSns,String codTitulo) {
	    OperacionesSns sns = new OperacionesSns(codUsuarioSns);
	    String numero=Misc.rellenarIzq(sns.convierte(),"0",8);
	    String digitosControlNaf=digitosControl(codTitulo + numero);
	    return codTitulo + numero + digitosControlNaf;
  }
  
  public String digitosControl(String snaf) {
    double naf=0;
    double resto = 0;
    if (snaf.charAt(2) == '0') {
      snaf = snaf.substring(0, 2) + snaf.substring(3);
      naf = Double.parseDouble(snaf.substring(0, 9));
    }
    else
      naf = Double.parseDouble(snaf.substring(0, 10));

    resto = naf % 97;

    return gasai.util.Misc.rellenarIzq(""+((int)resto),"0",2);
  }

  private boolean hayQueValidar(String sNaf,String codAgente) {
    if (!Misc.esVacio(sNaf)
        && sNaf.length()>Inicializacion.LONGITUD_PREFIJOS_AGENTES) {
      String posiblePrefijo=sNaf.substring(0,Inicializacion.LONGITUD_PREFIJOS_AGENTES);
      String codAgentePrefijo=Misc.nz(Inicializacion.hPREFIJOS_AGENTES.get(posiblePrefijo));
      //si el codAgentePrefijo es diferente del que se pasa hay que validar
      if (!codAgentePrefijo.equals(codAgente)) {
        //puede ser un naf valido o bien un codigo con prefijo que no corresponde con el agente
        return true;
      }else{
        //es un codigo con prefijo que corresponde con el agente, no hay que validar
        return false;
      }
    }
    return true;
  }

  public boolean valida(String sNaf,String codAgente) {
    //miramos si el naf que viene no es un naf y viene con el prefijo
    if (!hayQueValidar(sNaf,codAgente)) {
      //no es naf, es un codigo prefijo+codigo unico
      return true;
    }else{
      //es un naf o no corresponde el prefijo con el agente
      return valida(sNaf);
    }
  }

  public boolean valida(String snaf) {
//    Logger logger=new Logger("ValidaNaf.valida");
//    logger.debug("nafOrig -> " + snaf);
    boolean lSalida = true;
    try {
      double naf = 0;
      double resto = 0;
      String p = "";
      String f = "";
      int nlong;

      nlong = snaf.length();
      // Comprobar longitud sea 9
      if (nlong > 12 || nlong == 0)
        lSalida = false;

        // Comprobar no haya letras intermedias
      if (lSalida) {
        for (int nCont = 0; nCont < nlong; nCont++) {
          if (! (snaf.charAt(nCont) >= '0' && snaf.charAt(nCont) <= '9')) {
            lSalida = false;
            break;
          }
        }
      }
      // Comprobar 2 primeros caracteres sean 01 - 57
      if (lSalida) {
        if (nlong == 11) {
          snaf = "0" + snaf;
          nlong++;
        }
        p = snaf.substring(0, 2);
        int nvalor = Integer.parseInt(p);
//      if (!(nvalor >= 1 && nvalor <= 57 && nvalor != 55)) //se quita la restriccion del 55 a peticion
      if (! ((nvalor >= 1 && nvalor <= 57)
    		  || nvalor == 79 //NA Menores de 18 anios extranjeros RD Ley 16/2012
    		  || nvalor == 80 //NA Solicitantes protección Internacional
    		  || nvalor == 81 //NA Victimas de trata de seres humanos
    		  || nvalor == 82 //NA Embarazadas extranjeras
    		  || nvalor == 83 //NA Convenios especiales prestación asistencia sanitaria
    		  || nvalor == 84 //NA Extranjeros urgencias
    		  || nvalor == 85 //NA Extranjeros otras situaciones asistencia CA
    		  || nvalor == 88 //mayores de 26
    		  ))
          lSalida = false;
      }

      // Comprobar dos últimos dígitos de control sean los correctos
      if (lSalida) {
        f = snaf.substring(nlong - 2);
        if (snaf.charAt(2) == '0') {
          snaf = snaf.substring(0, 2) + snaf.substring(3);
          naf = Double.parseDouble(snaf.substring(0, 9));
        }
        else
          naf = Double.parseDouble(snaf.substring(0, 10));

//        logger.debug("snaf ---------> " + snaf.substring(0, 10));

        resto = naf % 97;

//        logger.debug("naf ---------> " + naf);
//        logger.debug("resto ---------> " + resto);

        if (Double.parseDouble(f) != resto)
          lSalida = false;
      }
    }catch (Exception e) {
      lSalida=false;
    }
    return lSalida;
  }
}



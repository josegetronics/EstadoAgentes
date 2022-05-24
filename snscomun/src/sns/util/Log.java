package sns.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Calendar;
import java.util.Date;

/**
 * Genera un fichero de log
 */
public class Log
{
   private static RandomAccessFile raf;
   private static boolean verbose = true;

   /**
	* Devuelve la fecha con formato AAAAMMDD
	*
	* @return la fecha con formato AAAAMMDD
	*/
  private static String getFecha()
  {
	  // componemos la fecha con formato AAAAMMDDHHMMSS
	  StringBuffer fecha = new StringBuffer();
	  Calendar cal = Calendar.getInstance();
	  String a = Integer.toString( cal.get( Calendar.YEAR ) );
	  String m = Integer.toString( cal.get( Calendar.MONTH ) + 1 );
	  m = ( m.length() == 1 ) ? "0" + m : m;
	  String d = Integer.toString( cal.get( Calendar.DAY_OF_MONTH ) );
	  d = ( d.length() == 1 ) ? "0" + d : d;
	  fecha.append( a );
	  fecha.append( m );
	  fecha.append( d );

	  return fecha.toString();
  }

   // pruebas
  /* public static void main( String[] args )
   {
	  try
	  {
	  	System.out.println("empezamos...");
		 raf = new RandomAccessFile( "C:\\pruebas.log", "rw" );
		 System.out.println("1");
		 long pos = raf.length();
		 System.out.println("2");
		 raf.seek( pos );
		 System.out.println("3");
		 //String arg = args[ 0 ];
		 String arg="hola";
		 System.out.println("4");
		 raf.writeBytes( arg );
		 System.out.println("5");
		 raf.writeBytes( "\n" );
		 System.out.println("6");
		 raf.close();
     Log.write("Hola","hola");
     System.out.println("termino");
	  }
	  catch(Exception e)
	  {
		 e.printStackTrace();
	  }
   }*/



   /**
	* Escribe una cadena en una linea al final del fichero especificado en Env.fichLog
	*
	* @param arg la cadena que se escribe en el fichero
	*/
  public static void write( String servicio, String arg )
  {
    //Comentar para produccion
    Log.write( servicio, arg, verbose );
  }

   /**
	* Escribe una cadena en una linea al final del fichero especificado en Env.fichLog
	*
	* @param arg la cadena que se escribe en el fichero
	* @param activado false si no se quiere ejecutar la operacion
	*                 true en caso contrario
	*/
  public static synchronized void write( String servicio, String arg, boolean activado)
  {
    if (activado)
	  {
      try
		  {
			  String filename =("/tmp/snsDes/log/") + getFecha() + ".log";

			  // abrimos el fichero
			  raf = new RandomAccessFile( filename, "rw" );

			  long pos = raf.length();

			  // nos posicionamos al final
			  raf.seek( pos );

			  // escribimos la fecha y la hora
			  raf.writeBytes( new Date().toString() );
			  raf.writeBytes( " - " );

			  // escribimos el servicio
			  raf.writeBytes( servicio );
			  raf.writeBytes( " - " );

			  // escribimos el String
			  raf.writeBytes( arg );

			  // hacemos un retorno de carro
			  raf.writeBytes( "\n" );

			  // cerrando...
			  raf.close();
			  raf = null;
      }
		  catch( FileNotFoundException e)
		  {
        // la ruta especificada es un directorio
			  e.printStackTrace();
      }
		  catch( IOException e )
		  {
        // kask
			  e.printStackTrace();
		  }
		  catch( Exception e )
		  {
        // vaya
			  e.printStackTrace();
		  }
    }
  }
}

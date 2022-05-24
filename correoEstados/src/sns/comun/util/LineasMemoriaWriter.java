package sns.comun.util;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class LineasMemoriaWriter extends Writer {

	private ArrayList lineas = new ArrayList();

	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	public void flush() throws IOException {
		// TODO Auto-generated method stub

	}

	public void write(char[] arg0, int arg1, int arg2) throws IOException {
		// TODO Auto-generated method stub

	}

	public void write(String linea) {
		lineas.add(linea);
	}

	public ArrayList getLineas() {
		return this.lineas;
	}

}
package sns.comun.bean;

import gasai.util.Misc;

import java.sql.ResultSet;
import java.sql.SQLException;

import sns.comun.config.Inicializacion;

public class ListaBlancaBean {
	
	
	private String codUsuarioSns;
	private String descMotivoTraspaso = "";
	private String descAgenteSolicitante = "";
	private String fechaPeticion = "";
	private String fechaRealizado = "";
	private int codOperacion = -1;
	
	
	public ListaBlancaBean () {
	}
	
	public ListaBlancaBean(ResultSet resultSet) throws SQLException, Exception {
		//
		try {
			//
			this.codUsuarioSns         = Misc.nz(resultSet.getString("COD_USUARIO_SNS"));
			this.descMotivoTraspaso    = Misc.nz(resultSet.getString("DESC_MOTIVO_TRASPASO"));
			this.descAgenteSolicitante = Misc.nz(resultSet.getString("DESC_AGENTE_SOLICITANTE"));
			this.fechaPeticion         = Misc.nz(resultSet.getString("FECHA_PETICION"));
			this.fechaRealizado        = Misc.nz(resultSet.getString("FECHA_REALIZADO"));
			this.codOperacion          = resultSet.getInt("COD_OPERACION");
		}
		catch (SQLException sqlE) {
			System.out.println("ListaBlancaBean(ResultSet): SQLException: " + sqlE.getMessage());
			throw sqlE;
		}
		catch (Exception e) {
			System.out.println("ListaBlancaBean(ResultSet): Exception: " + e.getMessage());
			throw e;
		}
	}
	
	
	
	
	public String getCodUsuarioSns() {
		return codUsuarioSns;
	}

	public void setCodUsuarioSns(String codUsuarioSns) {
		this.codUsuarioSns = codUsuarioSns;
	}

	public String getDescMotivoTraspaso() {
		return descMotivoTraspaso;
	}

	public void setDescMotivoTraspaso(String descMotivoTraspaso) {
		this.descMotivoTraspaso = descMotivoTraspaso;
	}

	public String getDescAgenteSolicitante() {
		return descAgenteSolicitante;
	}

	public void setDescAgenteSolicitante(String descAgenteSolicitante) {
		this.descAgenteSolicitante = descAgenteSolicitante;
	}

	public String getFechaPeticion() {
		return fechaPeticion;
	}

	public void setFechaPeticion(String fechaPeticion) {
		this.fechaPeticion = fechaPeticion;
	}

	public String getFechaRealizado() {
		return fechaRealizado;
	}

	public void setFechaRealizado(String fechaRealizado) {
		this.fechaRealizado = fechaRealizado;
	}

	public int getCodOperacion() {
		return codOperacion;
	}

	public void setCodOperacion(int codOperacion) {
		this.codOperacion = codOperacion;
	}
	
	
	public String toString() {
		//
		StringBuffer strBuffer = new StringBuffer ();
		strBuffer.append(Misc.nz(this.codUsuarioSns)         + Inicializacion.SEPARATOR_WRITER);		
		strBuffer.append(Misc.nz(this.descMotivoTraspaso)    + Inicializacion.SEPARATOR_WRITER);
		strBuffer.append(Misc.nz(this.descAgenteSolicitante) + Inicializacion.SEPARATOR_WRITER);
		strBuffer.append(Misc.nz(this.fechaPeticion)         + Inicializacion.SEPARATOR_WRITER);
		strBuffer.append(Misc.nz(this.fechaRealizado)        + Inicializacion.SEPARATOR_WRITER);		
		strBuffer.append(this.codOperacion			         + Inicializacion.SEPARATOR_WRITER);
		//
		return strBuffer.toString();
	}
	

	public String cabeceraToString() {
		//
		// COD_USUARIO_SNS, DESC_MOTIVO_TRASPASO, DESC_AGENTE_SOLICITANTE, FECHA_PETICION, FECHA_REALIZADO, COD_OPERACION
		//
		StringBuffer strBuffer = new StringBuffer();
		strBuffer.append("COD_USUARIO_SNS"         + Inicializacion.SEPARATOR_WRITER);
		strBuffer.append("DESC_MOTIVO_TRASPASO"    + Inicializacion.SEPARATOR_WRITER);
		strBuffer.append("DESC_AGENTE_SOLICITANTE" + Inicializacion.SEPARATOR_WRITER);
		strBuffer.append("FECHA_PETICION"          + Inicializacion.SEPARATOR_WRITER);
		strBuffer.append("FECHA_REALIZADO"         + Inicializacion.SEPARATOR_WRITER);
		strBuffer.append("COD_OPERACION"           + Inicializacion.SEPARATOR_WRITER);
		//
		return strBuffer.toString();
	}

}


package sns.model.inss;

import gasai.bd.AccesoBD;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import sns.exception.MultipleIpfException;
import sns.exception.MultipleNafException;
import sns.exception.TsiException;
import sns.exception.UsuarioSegSocialException;
import sns.model.UsuarioSns;
import sns.model.gestion.RegistroInss;
import sns.model.inss.dto.ResultadoCruceSnsBean;
import sns.model.inss.impl.ResultadoInssExtBean;

public interface IInssHelper {
	public ResultadoInssExtBean findByIpf(String ipf, AccesoBD bd) throws MultipleIpfException,UsuarioSegSocialException, SQLException;
	public ArrayList findByDniNie(String dniNie, AccesoBD bd) throws Exception;
	public ArrayList findByPasaporte(String pasaporte,AccesoBD bd) throws Exception;
	public ResultadoInssExtBean findByCodUsuarioSns(String codUsuarioSns, AccesoBD bd) throws UsuarioSegSocialException, SQLException;
	public ResultadoInssExtBean findByPrimaryKey(BigDecimal idInss, AccesoBD bd) throws UsuarioSegSocialException, SQLException;
	public void actualizar(ResultadoInssExtBean resultadoInss,RegistroInss registroInss,ResultadoCruceSnsBean resultadoCruceSnsBean, AccesoBD bd) throws SQLException;
	public ListaCamposAfectadosInss actualizarIpf(RegistroInss registroInss, ResultadoInssExtBean resultadoInssBean, AccesoBD bd) throws SQLException;
	public void insertar(RegistroInss registroInss,ResultadoCruceSnsBean resultadoCruceSnsBean, AccesoBD bd) throws SQLException;
	public ListaCamposAfectadosInss vincularCodSns(ResultadoInssExtBean resultadoInss,String codUsuarioSns,String criterioIdentificacion,AccesoBD bd) throws SQLException;
	public ListaCamposAfectadosInss vincularSoloCodSns(ResultadoInssExtBean resultadoInss,String codUsuarioSns,String criterioIdentificacion,AccesoBD bd) throws SQLException;
	public void guardarHistorico(BigDecimal idInss, ListaCamposAfectadosInss listaCamposAfectadosInss, BigDecimal procedencia,BigDecimal codTipoProcedencia,AccesoBD bd) throws SQLException;
	public void insertarLineaProcesando(RegistroInss registroInss) throws SQLException;
	public ResultadoInssExtBean findTitularByNaf(String naf) throws MultipleNafException, UsuarioSegSocialException, SQLException;
	public ResultadoInssExtBean findByNafPrincipal(String naf, AccesoBD bd) throws MultipleNafException, UsuarioSegSocialException, SQLException;
	public ResultadoInssExtBean findNafSecundarios(String naf, AccesoBD bd) throws UsuarioSegSocialException, SQLException;
	public ResultadoInssExtBean findByDatosSns(UsuarioSns usuarioSnsIn, AccesoBD bd) throws UsuarioSegSocialException, SQLException;
	public ResultadoInssExtBean findByDatosSnsTitular(UsuarioSns usuarioSnsIn, AccesoBD bd) throws UsuarioSegSocialException, SQLException;
	public UsuarioSns buscarMasVincularTitular(UsuarioSns usuarioSnsIn, AccesoBD bd) throws Exception;
	public UsuarioSns buscarTitularParaModificacion(UsuarioSns usuarioSnsIn) throws Exception;
	public void desvincularCodSns(String codUsuarioSns,Integer opMaestra, BigDecimal codTipoProcedencia,AccesoBD bd) throws SQLException;
	public void desvincularCodSnsPorConstraintIpfUnico(String ipf,BigDecimal idInssProcedencia,AccesoBD bd) throws SQLException,TsiException;
	public void desvincularCodSnsPorDuplicidad(String ipf,BigDecimal idInssFichero,AccesoBD bd) throws SQLException;
	public void moverAbajas(String ipf,String codMotivoBaja,BigDecimal idInssFichero,AccesoBD bd) throws SQLException;
	public ArrayList findBy(CriteriosBusqueda criteriosBusqueda, AccesoBD bd) throws SQLException;
	public void meterCuarentena(BigDecimal idInssFichero,int numLinea,String ipf, ResultadoCruceSnsBean coincidente, String causa, AccesoBD bd) throws SQLException;
	public void actualizarSituacionBaja(ResultadoInssExtBean resultadoInss, RegistroInss registroInss,AccesoBD bd) throws SQLException;
	public boolean esFavorableSituacionActual(IInss registroLineaIfi,IInss registroExistente);
}

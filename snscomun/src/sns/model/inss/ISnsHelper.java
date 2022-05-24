package sns.model.inss;

import gasai.bd.AccesoBD;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

import sns.exception.DiferenteSnsException;
import sns.exception.MultipleNafException;
import sns.exception.MultiplesTitularesInssException;
import sns.exception.MultiplesUsuariosSnsEncontradosException;
import sns.exception.TitularNoEncontradoException;
import sns.exception.UsuarioNoEncontradoException;
import sns.model.ListaCamposAfectadosVisiblesToComunidad;
import sns.model.inss.dto.DatosSnsBean;
import sns.model.inss.dto.ResultadoCruceExtSnsBean;
import sns.model.inss.dto.ResultadoCruceSnsBean;
import sns.model.inss.impl.ResultadoInssExtBean;

public interface ISnsHelper {
	
	/**
	 * 
	 * @param codUsuarioSns
	 * @param bd
	 * @return
	 * @throws SQLException
	 */
	public ResultadoCruceExtSnsBean findByCodUsuarioSns(String codUsuarioSns,AccesoBD bd) throws SQLException;
	
	public ArrayList findBeneficiarios(String codSnsTitular, AccesoBD bd) throws SQLException;
	
	public ResultadoCruceSnsBean findByRegistroInssEnSns(IInss registroInss,AccesoBD bd) throws Exception;
//	public ResultadoActualizacionEnSns actualizar(ResultadoCruceSnsBean resultadoCruceSnsBean,RegistroInss registroInss,AccesoBD bd) throws Exception;
	public ResultadoActualizacionEnSns actualizarDiferido(ResultadoInssExtBean resultadoInssExtBean,Integer codOperacion, AccesoBD bd) throws Exception;
	public DatosSnsBean buscarCodUsuarioSns(String codUsuarioSns,AccesoBD bd) throws Exception;
	public Integer crearRegistroOperacion(String motivoMantenimiento,AccesoBD bdTx) throws Exception;
	/**
	 * 
	 * @param inssBean
	 * @param bd
	 * @return
	 * @throws UsuarioNoEncontradoException
	 * @throws MultiplesUsuariosSnsEncontradosException
	 */
	public ResultadoCruceSnsBean buscarPorAproximacion(IInss inssBean,AccesoBD bd) throws UsuarioNoEncontradoException,MultiplesUsuariosSnsEncontradosException;
	/**
	 * 
	 * @param inssBean
	 * @param codOperacion
	 * @param bd
	 * @return
	 * @throws MultiplesTitularesInssException
	 * @throws MultipleNafException
	 * @throws DiferenteSnsException
	 * @throws UsuarioNoEncontradoException
	 * @throws MultiplesUsuariosSnsEncontradosException
	 * @throws SQLException
	 * @throws TitularNoEncontradoException
	 */
	public ResultadoActualizacionEnSns restaurarBeneficiario(IInss inssBean, Integer codOperacion, AccesoBD bd) throws MultiplesTitularesInssException,MultipleNafException,DiferenteSnsException,UsuarioNoEncontradoException,MultiplesUsuariosSnsEncontradosException,SQLException, TitularNoEncontradoException;

	public ListaCamposAfectadosVisiblesToComunidad actualizarUsuarios(String codUsuarioSns,int codEstadoActual,int codEstadoNuevo, AccesoBD bd) throws Exception;
	
	public void guardarHistorico(String codUsuarioSns, ListaCamposAfectadosVisiblesToComunidad listaCamposAfectados,BigDecimal codTipoModificacion, Integer codOperacion, AccesoBD bd) throws SQLException;
}

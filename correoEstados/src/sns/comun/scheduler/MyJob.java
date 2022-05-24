package sns.comun.scheduler;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import sns.comun.config.Inicializacion;
import sns.estadoagentes.ManagerEstadoAgentes;

public class MyJob implements Job{

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);
	
	public void execute(JobExecutionContext context) throws JobExecutionException {

		logger.debug("INICIO ejecución managerEstadoAgentes");
		ManagerEstadoAgentes managerEstadoAgentes = new ManagerEstadoAgentes();
		try {
			managerEstadoAgentes.getInfo();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("FIN de la ejecución managerEstadoAgentes.");
	}

}

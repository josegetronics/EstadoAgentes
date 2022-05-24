package sns.comun.scheduler;

import org.apache.log4j.Logger;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerMetaData;
import org.quartz.TriggerBuilder;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import sns.comun.config.Inicializacion;
import sns.estadoagentes.ManagerEstadoAgentes;


public class ConfiguracionQuartz{

	private static Logger logger = Logger.getLogger(Inicializacion.LOGGER_NAME);
	
	public void configuracionQuartz() throws SchedulerException {
		
		JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1_1", "jGroup1").build();		
		
		// Expresion crontab (cada 10 seg): "/10 * * * * ?" -
		//Expresion crontab (ejecución a las 6 AM diario):
			// 1ª Forma: "0 0 06 * * 1,2,3,4,5,6,7"
			// 2ª Forma: "0 0 06 * * ?"
		CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger", "group1")
				.withSchedule(CronScheduleBuilder.cronSchedule("0 00 06 * * ?")).forJob("job1_1", "jGroup1").build();
		
		
        // Grab the Scheduler instance from the Factory
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        logger.info("------- Iniciando Scheduler ---------------");
        // and start it off
        scheduler.start();
        
        scheduler.scheduleJob(jobDetail,trigger);
        
        /*
        scheduler.shutdown(true);
        System.out.println("------- Finalizando el scheduler -----------------");
        
        SchedulerMetaData metaData = scheduler.getMetaData();
        System.out.println("~~~~~~~~~~ ejecutado" + metaData.getNumberOfJobsExecuted() + "Trabajo.");
        */
	}


}

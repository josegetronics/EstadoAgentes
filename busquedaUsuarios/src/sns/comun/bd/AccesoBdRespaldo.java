package sns.comun.bd;

public class AccesoBdRespaldo extends gasai.bd.AccesoBD {
	
	public AccesoBdRespaldo() {
	      super("ora-sns10.msc.es", "2008", "SNS10", "snsalud", "snsalud");
		//super("ora-sns10res.msc.es", "2008", "sns10", "SNSALUD", "SNSALUD");
		//
		//super("ora-res-clon.msc.es", "2010", "sns10", "SNSALUD", "snsalud_volga");
	}
}


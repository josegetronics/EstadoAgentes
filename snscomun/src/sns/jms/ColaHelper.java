package sns.jms;
import gasai.util.Misc;

import java.util.Hashtable;

import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class ColaHelper
{
  private static final String JMS_FACTORY="weblogic.jms.ConnectionFactory";
  private static final String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
  static org.apache.log4j.Logger logger = sns.config.Inicializacion.getLogger(ColaHelper.class);

    public ColaHelper() {
    }

    public String enviar(String nombreCola,String body) throws Exception
    {
        return enviar("",nombreCola,body,null);
    }


    public String enviar(String urlProveedor,String nombreCola,String body,Message msgOrig) throws Exception
    {
        QueueConnectionFactory qconFactory;
        QueueConnection qcon;
        QueueSession qsession;
        QueueSender qsender;
        Queue queue;
        TextMessage msg;
        Message msgEnviado;
        String msgId="";
        try {
            InitialContext ctx=null;
            if (!Misc.esVacio(urlProveedor)) {
                Hashtable env = new Hashtable();
                env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
                env.put(Context.PROVIDER_URL, urlProveedor);

                ctx=new InitialContext(env);
            }else{
                ctx=new InitialContext();
            }
            qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
            qcon = qconFactory.createQueueConnection();
            qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            queue = (Queue) ctx.lookup(nombreCola);
            qsender = qsession.createSender(queue);

            if (msgOrig==null) {
                msg = qsession.createTextMessage(body.toString());
                qcon.start();
                msgEnviado=msg;
            }else{
                msgEnviado=msgOrig;
            }
            qsender.send(msgEnviado);
            msgId=msgEnviado.getJMSMessageID();

            qsender.close();
            qsession.close();
            qcon.close();
        } catch (Exception e) {
            msgId="";
            throw e;
        }
        return msgId;
   }
}

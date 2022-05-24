package sns.jms;

import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.QueueBrowser;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import sns.config.Constantes;

/**
 * This example shows how to establish a connection to a JMS
 * queue and browse (but not dequeue) the queued messages. The classes in this
 * package operate on the same JMS queue. Run the classes together to
 * observe messages being sent and received, and to browse the queue
 * for messages. This class is used to browse, but not remove, messages
 * in the queue.
 */

public class QueueBrowse {

  //Defines the JNDI context factory.
  public final static String JNDI_FACTORY="weblogic.jndi.WLInitialContextFactory";
  // Defines the JMS connection factory for the queue.
  public final static String JMS_FACTORY="snsConnectionFactory";
  // Defines the queue.
  public String QUEUE=Constantes.COLA_JMS_ENTRADA;
  private QueueConnectionFactory qconFactory;
  private QueueConnection qcon;
  private QueueSession qsession;
  private QueueBrowser qbrowser;
  private javax.jms.Queue queue;

  public void init(Context ctx, String queueName)
       throws NamingException, JMSException
  {
    qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
    qcon = qconFactory.createQueueConnection();
    qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
    queue = (javax.jms.Queue) ctx.lookup(queueName);
    qbrowser = qsession.createBrowser(queue);
    qcon.start();
  }

  /**
   * Displays the current contents of the queue.
   *
   * @exception JMSException if JMS fails to display messages on the queue due to internal error
   */
  public void displayQueue()
    throws JMSException  {

    Enumeration e = qbrowser.getEnumeration();
    Message m = null;

    if (! e.hasMoreElements()) {
      System.out.println("There are no messages on this queue.");
    } else {
      System.out.println("Queued JMS Messages: ");
      int contador=0;
      while (e.hasMoreElements()) {
        contador++;
        m = (Message) e.nextElement();
        System.out.println("Message ID " + m.getJMSMessageID() +
                           " delivered " + new Date(m.getJMSTimestamp()) +
                           " to " + m.getJMSDestination());
        System.out.print("\tExpires        ");
        if (m.getJMSExpiration() > 0) {
          System.out.println( new Date( m.getJMSExpiration()));
            }
        else
          System.out.println("never");
        System.out.println("\tPriority       " + m.getJMSPriority());
        System.out.println("\tMode           " + (
                      m.getJMSDeliveryMode() == DeliveryMode.PERSISTENT ?
                                       "PERSISTENT" : "NON_PERSISTENT"));
        System.out.println("\tCorrelation ID " + m.getJMSCorrelationID());
        System.out.println("\tReply to       " + m.getJMSReplyTo());
        System.out.println("\tMessage type   " + m.getJMSType());
        System.out.println("\tDestino: " + m.getStringProperty("destino"));
        if (m instanceof TextMessage) {
          System.out.println("\tTextMessage    \"" + ((TextMessage)m).getText() + "\"");
        }
      }
      System.out.println("Numero registros -> " + contador);
    }
  }

  public void mostrarCola(PrintWriter out,String nombreCola)
       throws JMSException
  {
    try{
      InitialContext ic = new InitialContext();
      this.init(ic, nombreCola);
    } catch (javax.naming.NamingException e) {
      out.println("Error al coger el contexto de local host");
    }

    Enumeration e = qbrowser.getEnumeration();
    Message m = null;

    if (! e.hasMoreElements()) {
      out.println("There are no messages on this queue.");
    } else {
      out.println("<table>");
      out.println("<tr><td>Mensaje ID</td><td>Hora</td><td>Destino</td></tr>");
      out.println("Mensajes en la cola : ");
      while (e.hasMoreElements()) {
        m = (Message) e.nextElement();
        out.println("<tr><td><A HREF=\"MostrarXml?id="+ java.net.URLEncoder.encode (m.getJMSMessageID()) +
                    "&nombreCola=" + nombreCola +
                    "\">" + java.net.URLEncoder.encode (m.getJMSMessageID()) + "</a></td><td>" +
                           new Date(m.getJMSTimestamp()) + "</td><td>" +
                           m.getStringProperty("destino") + "</td></tr>");

        //if (m instanceof TextMessage) {
        //  System.out.println("\tTextMessage    \"" + ((TextMessage)m).getText() + "\"");
        // }
      }
      out.println("</table>");
    }
    this.close();
  }


  public void mostrarId(String id, PrintWriter out,String nombreCola)
       throws JMSException
  {
    try{
      InitialContext ic = new InitialContext();
      this.init(ic, nombreCola);
    } catch (javax.naming.NamingException e) {
      out.println("Error al coger el contexto de local host");
    }

    Enumeration e = qbrowser.getEnumeration();
    Message m = null;

    if (! e.hasMoreElements()) {
      out.println("There are no messages on this queue.");
    }
    else {
      boolean fin=false;
      int contador=0;
      while (!fin && e.hasMoreElements()) {
        contador++;
        m = (Message) e.nextElement();
        if (id.equals(m.getJMSMessageID())) {
          if (m instanceof TextMessage) {
            out.println(((TextMessage)m).getText());
            if (m.getStringProperty("error")!=null) {
              out.println("--------------------Error------------");
              out.println(m.getStringProperty("error"));
            }
            fin=true;
          }
        }
      }
      System.out.println("Numero registros -> " + contador);
    }
    this.close();
  }

  public void close()
       throws JMSException
  {
    qbrowser.close();
    qsession.close();
    qcon.close();
  }

  public static void main(String[] args)
       throws Exception
  {
    if (args.length!=2) {
      System.out.println("uso: java sns.jms.QueueBrowse nombreCola WEBLOGICURL");
      System.exit(1);
    }
    InitialContext ic = getInitialContext(args[1]);
    QueueBrowse qb = new QueueBrowse();
    qb.init(ic, args[0]);
    qb.displayQueue();
    qb.close();
  }

  private static InitialContext getInitialContext(String url)
       throws NamingException
  {
    Hashtable env = new Hashtable();
    env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
    env.put(Context.PROVIDER_URL, url);
    return new InitialContext(env);
  }


}

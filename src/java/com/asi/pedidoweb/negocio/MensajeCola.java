/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.negocio;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

/**
 *
 * @author samaelopez
 */
@Stateless
public class MensajeCola implements MensajeColaLocal {

//    @Resource(mappedName = "jms/base")
//    private Queue base;
    

    
    private Logger logger = Logger.getLogger(this.getClass().getName());

     /**
     * Metodo que configura el destino del mensaje de acuerdo al modulo que
     * recibira el mensaje el método recibe un Objeto de tipo Map "objs".
     * Este objeto debe contener <b><u>SIEMPRE</u></b> un Objeto de tipo
     * <code>Genmenproc</code> que sera el cuerpo del mensaje y será
     * identificado como <b><u>"gmp"</u></b>. Por ultimo llama al metodo
     * que se encarga de enviar el mensaje al destino adecuado.
     * @param objs Objeto de tipo Map [String, Object] el cual contiene el
     * objeto de tipo Genmemproc el cual contiene configuraciones adicionales
     * y a su vez puede contener N candidad de Objetos.
     * @param props Objeto de tipo Map [String, String] El cual contiene
     * propiedades adicionales para el mensaje.
     * @param modulo El modulo que recibirá el mensaje.
     * @return Genmenproc actual.
     * @throws javax.jms.JMSException Excepcion de JMS.
     * @throws java.lang.Exception Excepcion de genérica.
     */
    public void mandarMsgJMS(final Map < String, Object > objs,
            final Map props)
            throws Exception {
        System.out.println("antes de");
        Queue cola = null;
                //this.base;
        System.out.println("cola.. " +cola);

        try {
            StringBuffer cod = new StringBuffer();
            this.sendJMSMessage(objs, props, cola);
        } catch (Exception e) {
            e.printStackTrace();
            logger.log(Level.SEVERE,
            "Error configurando y guardando el objeto de mensajes.", e);
            throw e;
        }
    }

    /**
     * Metodo que envia un mensaje de tipo <code>ObjectMessage</code> a
     * <code>cola</code>. El cuerpo del Mensaje es un objeto del tipo
     * Genmenproc que contiene los datos necesarios para monitorear
     * el proceso. Al mensaje enviado se le agrega una propiedad
     * "Accion" con el valor enviado en <code>hint</code> que el
     * MDB (listener) usara como <code>messageselector</code>.
     * @param objMsg El objeto a enviar en el mensaje.
     * @param props Las properties a agregarle al mensaje.
     * @param cola La cola a la que se enviara el mensaje.
     * @throws javax.jms.JMSException Excepcion de JMS.
     */
    private void sendJMSMessage(final Map < String, Object > objs,
            final Map < String, String > props, final Queue cola)
            throws Exception, JMSException {
        Connection connection = null;
        Session sesion = null;
        MessageProducer producer = null;

ConnectionFactory connectionFactory = null;
Destination dest = null;
Context jndiContext = null;
        try {
            
            jndiContext = new InitialContext();
   connectionFactory = (ConnectionFactory) jndiContext.lookup("java:comp/DefaultJMSConnectionFactory");
   dest = (Destination) jndiContext.lookup("jms/base");
 
   connection = connectionFactory.createConnection();
   
            sesion = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            producer = sesion.createProducer(cola);
            ObjectMessage msg = sesion.createObjectMessage();
            Iterator it = props.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry pairs = (Map.Entry) it.next();
                msg.setStringProperty(
                        pairs.getKey().toString(), pairs.getValue().toString());
            }
            msg.setObject((Serializable) objs);
            producer.send(msg);
            producer.close();
        } catch (JMSException e) {
            Logger.getLogger(MensajeCola.class.getName()).log(Level.SEVERE,
                    "No se pudo mandar el mensaje");
            throw e;
        } finally {
            if (producer != null) {
                try {
                    producer.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(
                            Level.WARNING,
                            "No se pudo cerrar el productor de mensajes.", e);
                }
            }
            if (sesion != null) {
                try {
                    sesion.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(
                            Level.WARNING, "No se pudo cerrar la sesion.", e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    Logger.getLogger(this.getClass().getName()).log(
                            Level.WARNING, "No se pudo cerrar la conexion.", e);
                }
            }
        }
    }

//    private void sendJMSMessageToBase(String messageData) {
//        context.createProducer().send(base, messageData);
//    }


}

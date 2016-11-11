/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.negocio;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 *
 * @author samaelopez
 */
//@MessageDriven(activationConfig = {
//    @ActivationConfigProperty(propertyName = "destinationLookup",
//            propertyValue = "jms/base"),
//    @ActivationConfigProperty(propertyName = "destinationType", 
//            propertyValue = "javax.jms.Queue")
//})

//
//    @MessageDriven(mappedName = "jms/base", activationConfig =  {
//        @ActivationConfigProperty(propertyName = "messageSelector",
//            propertyValue = "Accion = 'envMail'"),
//        @ActivationConfigProperty(propertyName = "acknowledgeMode",
//            propertyValue = "Auto-acknowledge"),
//        @ActivationConfigProperty(propertyName = "destinationType",
//            propertyValue = "javax.jms.Queue")
//    })
public class EnviarEmailSP 
      //  implements MessageListener
{
//
//    @EJB
//    private GestorEmailLocal gestorEmail;
//    
//    
//    
//    public EnviarEmailSP() {
//    }
//        /** Instancia del logger de este MDB. */
//    private Logger logger = Logger.getLogger(this.getClass().getName());
//    @Override
//    public void onMessage(Message message) {
//    
//      ObjectMessage msg;
//        Map < String, Object > objs;
//        String accion;
//        String result;
//        // Si el mensaje ha sido enviado por 2a vez, no procesarlo.
//        try {
//            if (message.getJMSRedelivered()) {
//                return;
//            }
//        } catch (Exception e) {
//            e.getMessage();
//            logger.log(Level.INFO, "Mensaje recibido por 2a vez.");
//        }
//        // Si el mensaje no es del tipo ObjectMessage, no procesarlo.
//        if (message instanceof ObjectMessage) {
//            try {
//                msg = (ObjectMessage) message;
//                accion = msg.getStringProperty("Accion");
//                objs = (Map < String, Object > ) msg.getObject();
//               
//                if (accion.equals("envMail")) {
//                    try {
//                        String contenido = (String) objs.get("contenido");
//                        String asunto = (String) objs.get("asunto");
//                        String nombre = (String) objs.get("nombre");
//                        List < String > lst
//                                = (List < String > ) objs.get("lstMails");
//                        String header = (String) objs.get("pheader");
//                        System.out.println("entro a segundo plano   ......");
//                        gestorEmail.enviarEmail(contenido, asunto,
//                                nombre, lst, header);
//                       logger.log(Level.INFO, "El correo se envio exitosamente");
//                    } catch (Exception e) {
//                     logger.log(Level.INFO, e.getMessage());
//                    }
//                }
//            } catch (JMSException e) {
//                logger.log(Level.SEVERE, "Error de JMS: {0}", e.getMessage());
//                
//            } catch (Exception e) {
//                logger.log(Level.SEVERE, "Error: {0}", e.getLocalizedMessage());
//            } finally {
//                try {
//                    
//                } catch (Exception e) {
//                    logger.log(Level.SEVERE, "Error intentando guardar el "
//                            + "registro del proceso finalizado. ", e);
//                    return;
//                }
//            }
//        }
//    }
    }


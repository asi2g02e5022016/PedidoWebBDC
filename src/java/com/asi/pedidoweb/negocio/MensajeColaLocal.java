/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asi.pedidoweb.negocio;

import java.util.Map;
import javax.ejb.Local;
import javax.jms.JMSException;

/**
 *
 * @author samaelopez
 */
@Local
public interface MensajeColaLocal {
    void mandarMsgJMS(final Map < String, Object > objs,
            final Map props)
            throws Exception;
        
}

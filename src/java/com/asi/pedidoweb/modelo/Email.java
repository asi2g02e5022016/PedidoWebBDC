/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samaelopez
 */
public class Email {
   
    //<editor-fold defaultstate="collapsed" desc="atributos">
    /**Nombre del que emite el correo electrónico.*/
    private String nombreEmisor = null;
    /**Asunto del correo electrónico.*/
    private String asunto = null;
    /**Encabezado del mensaje.*/
    private String encabezado = null;
    /**Contenido del mensaje.*/
    private String contenido = null;
    /**Destinatarios que recibirán el mensaje.*/
    private List < String > destinatarios = new ArrayList();
    /**Nombre de usuario integrity.*/
    private String usrIntegrity = null;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Constructores">
    /**Constructor base de la clase.*/
    public Email() {
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getter/Setter">
    /**
     * Nombre del que emite el correo electrónico.
     * @return the nombreEmisor
     */
    public final String getNombreEmisor() {
        return nombreEmisor;
    }

    /**
     * Nombre del que emite el correo electrónico.
     * @param nombre the nombreEmisor to set
     */
    public final void setNombreEmisor(final String nombre) {
        this.nombreEmisor = nombre;
    }

    /**
     * Asunto del correo electrónico.
     * @return the asunto
     */
    public final String getAsunto() {
        return asunto;
    }

    /**
     * Asunto del correo electrónico.
     * @param as the asunto to set
     */
    public final void setAsunto(final String as) {
        this.asunto = as;
    }

    /**
     * Encabezado del mensaje.
     * @return the encabezado
     */
    public final String getEncabezado() {
        return encabezado;
    }

    /**
     * Encabezado del mensaje.
     * @param enca the encabezado to set
     */
    public final void setEncabezado(final String enca) {
        this.encabezado = enca;
    }

    /**
     * Contenido del mensaje.
     * @return the contenido
     */
    public final String getContenido() {
        return contenido;
    }

    /**
     * Contenido del mensaje.
     * @param cont the contenido to set
     */
    public final void setContenido(final String cont) {
        this.contenido = cont;
    }

    /**
     * Destinatarios que recibirán el mensaje.
     * @return the destinatarios
     */
    public final List < String > getDestinatarios() {
        return destinatarios;
    }

    /**
     * Destinatarios que recibirán el mensaje.
     * @param dest the destinatarios to set
     */
    public final void setDestinatarios(final List < String > dest) {
        this.destinatarios = dest;
    }

    /**
     * Nombre de usuario integrity.
     * @return the usrIntegrity
     */
    public final String getUsrIntegrity() {
        return usrIntegrity;
    }

    /**
     * Nombre de usuario integrity.
     * @param usr the usrIntegrity to set
     */
    public final void setUsrIntegrity(final String usr) {
        this.usrIntegrity = usr;
    }
    //</editor-fold> 
}

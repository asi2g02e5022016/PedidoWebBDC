package com.asi.pedidoweb.controller;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.asi.pedidoweb.modelo.Cliente;
import com.asi.pedidoweb.modelo.Medida;
import com.asi.pedidoweb.modelo.OrdenpedidoDTO;
import com.asi.pedidoweb.modelo.OrdenpedidodetalleDTO;
import com.asi.pedidoweb.modelo.Producto;
import com.asi.pedidoweb.modelo.Sucursal;
import com.asi.pedidoweb.modelo.Vwproductos;
import com.asi.pedidoweb.negocio.ConsumerWSLocal;
import com.asi.pedidoweb.negocio.GestorEmailLocal;
import com.asi.pedidoweb.negocio.ProcesosVentasLocal;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author samaelopez
 */
@ManagedBean(name = "pedidoWEb")
@ViewScoped
public class PedidoWeb implements Serializable{
//    @EJB
//    private Cru
    @EJB
    private ConsumerWSLocal consumerWS;

    @EJB
    private ProcesosVentasLocal procesosVentas;

    @EJB
    private GestorEmailLocal gestorEmail;
    @Inject
    private SessionUsr sesion;
    private Producto product;
    private int codsucursal;
    private List<Sucursal> lstSucursal;
    private String descripcionProducto;
    private String URLBASE = null;
    private List< Vwproductos> lstProducto = new ArrayList<>();
     private List<OrdenpedidodetalleDTO> lstDetalle;
     private List<OrdenpedidoDTO> lstedidos;
     private Double cantidadSolic;
     private Integer idCliente;
     private Cliente cliente;
     private String descripCliente;
     private String estado;
     private String medida;
     private Date fecha;
      private Date fechaInicial;
       private Date fechaFinal;
     private OrdenpedidoDTO oderPedido;
     private Map sucMap = new HashMap();
     private boolean  guardar;
     

    /**
     * Creates a new instance of PedidoWEb
     */
    public PedidoWeb() {
    }

        @PostConstruct
     public void postContruction() {
        try {
            /**Creamos un Objeto de tipo Properties*/
   Properties propiedades = new Properties();
    
   /**Cargamos el archivo desde la ruta especificada*/
   propiedades
     .load(getClass().getResourceAsStream("server.properties"));
 
   /**Obtenemos los parametros definidos en el archivo*/
   URLBASE = propiedades.getProperty("serverPrincipal");
            System.out.println("entro al manage" + URLBASE);
            if (URLBASE == null) {
                alert("No se encontro la configuracion de servidor",
                        FacesMessage.SEVERITY_INFO);
            }
                      
            String jsonRetur = this.consumerWS.consumirWebservices(
                    sesion.getUserCliente(), "", 
                    URLBASE + "SucursalWS");
            System.out.println("jsonRetur... " + jsonRetur);
            lstSucursal = new Gson().fromJson(jsonRetur,
                    new TypeToken<ArrayList<Sucursal>>() {
            }.getType());
            System.out.println("lstSucursal..." + lstSucursal);
            if (lstSucursal == null || lstSucursal.isEmpty()) {
                alert("No se encontraron resultados de sucursales.", 
                        FacesMessage.SEVERITY_INFO);
            }
            sucMap = new HashMap();
            for (Sucursal sucursal : lstSucursal) {
                sucMap.put(sucursal.getIdsucursal().toString(),sucursal.getEmail() );
            }
            fecha = new Date();
             descripCliente = sesion.getUserCliente();
        }catch (Exception e) {
            e.printStackTrace();
            alert(e.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    public void mostrarDialogProd() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('dialogoProducto').show();");
    }

    public void mostrarBuscar() {
         guardar = false;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("PF('monitorPedido').show();");
    }

    public void buscarOrdenPedido() {
        try {
            
                if (fechaInicial != null && fechaFinal != null) {
                    if (fechaInicial.after(fechaFinal)) {
                        alert("La fecha inicial tiene que ser menor que la final.",
                                FacesMessage.SEVERITY_WARN);
                        return;
                    }
                } 
            

            Sucursal suc = new Sucursal();
            suc.setIdsucursal(codsucursal);
            
    Map filtros = new HashMap();
    filtros.put("fechaInicial" ,fechaInicial);
    filtros.put("fechaFinal" ,fechaFinal);
        filtros.put("idsucursal" , String.valueOf(codsucursal));
     String jsonDatos = new Gson().toJson(filtros);
       String jsonRetur = this.consumerWS.consumirWebservices(
                    sesion.getUserCliente(),jsonDatos, 
                    URLBASE + "ConsultaPedidosWS");
      lstedidos = new Gson().fromJson(jsonRetur,
                    new TypeToken<ArrayList<OrdenpedidoDTO>>() {
            }.getType());
      if (lstedidos == null || lstedidos.isEmpty()) {
          alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
          return;
      }
          System.out.println("suc.getEmail() .,... " +suc.getEmail() );
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }
    }
    public void buscarProducto() {
        try {

            Map filtro = new HashMap();
            if (descripcionProducto != null) {
                filtro.put("producto", descripcionProducto.trim());
            }
//            filtro.put("activo", 1);
//            filtro.put("tipo", 1);
            String json = new Gson().toJson(filtro);
            String jsonRetur = this.consumerWS.consumirWebservices(
                    sesion.getUserCliente(), json,
                    URLBASE + "Maestros");
            System.out.println("jsonRetur... " + jsonRetur);
            lstProducto = new Gson().fromJson(jsonRetur,
                    new TypeToken<ArrayList<Vwproductos>>() {
            }.getType());
            // lstProducto = ejbBusProd.buscarProducto(filtro);

            System.out.println("lstProducto..." + lstProducto);

            System.out.println("lstProducto.." + lstProducto);
            if (lstProducto == null || lstProducto.isEmpty()) {
                alert("No se encontraron resultados.", FacesMessage.SEVERITY_INFO);
            }
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(
                    Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_INFO);
        }
    }

    public void alert(String mensaje, FacesMessage.Severity faces) {
        FacesMessage message = new FacesMessage(faces,
                "Mensaje", mensaje);

        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    
    
    public void enviarcorreo(String correo) {
        try {
            List< String> lst = new ArrayList<>();
            lst.add(correo);
            gestorEmail.enviarEmail("Correo de Pedido Web.",
                    "Pedido Web", "Pedido Web", lst, "Bodeguita del cerdito");
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void purebaStrin() {
        try {
            procesosVentas.pruebaCola();
        } catch (Exception ex) {
            Logger.getLogger(PedidoWeb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    public void onRowSelect(SelectEvent event) {
        try {
            Vwproductos idP  =  ((Vwproductos) event.getObject());

           product = new Producto();
           Medida medi = new Medida();
           medi.setIdmedida(idP.getIdmedida());
           medi.setMedida(idP.getMedida());
           product.setIdmedida(medi);
           product.setIdproducto(idP.getIdproducto());
           product.setProducto(idP.getProducto());
           medida = idP.getMedida();
           descripcionProducto = product.getProducto();
            RequestContext requestContext = RequestContext.getCurrentInstance();
                requestContext.execute("PF('dialogoProducto').hide();");
        } catch (Exception ex) {
            ex.printStackTrace();
            Logger.getLogger(PedidoWeb.class.getName())
                    .log(Level.SEVERE, null, ex);
            alert(ex.getMessage(), FacesMessage.SEVERITY_ERROR);
        }
        
    }
    
    public  void agreaLinea() {
          if (cantidadSolic == null || cantidadSolic.toString().equals("0")){
                alert("La Cantidad Es obligatorio", FacesMessage.SEVERITY_WARN);         
                return;
            }
          if (product == null) {
              alert("Debe selecionar un producto.",
                      FacesMessage.SEVERITY_WARN);
              return;
          }
            if (lstDetalle == null ) {
                lstDetalle = new ArrayList<>();
            }
            OrdenpedidodetalleDTO pedidoDet = new OrdenpedidodetalleDTO();
            pedidoDet.setIdproducto(product);
            pedidoDet.setCantidadconfirmada(cantidadSolic);
            pedidoDet.setCantidadsolicitada(cantidadSolic);
            pedidoDet.setPrecio(product.getPrecioventa());
            pedidoDet.setMonto(cantidadSolic * pedidoDet.getPrecio());
            Double totaliva = pedidoDet.getMonto() * Double.valueOf("0.13");
            pedidoDet.setIva(totaliva);
            pedidoDet.setTotal(totaliva + pedidoDet.getMonto());
            lstDetalle.add(0, pedidoDet);
            
    }
    /**
     * 
     */
    public void limpiarPedido() {
          lstDetalle = null;
          lstProducto = null;
          this.cantidadSolic =  null;
          this.cliente = null;
          this.descripcionProducto = null;
          this.estado = null;
          this.fecha = null;
           this.idCliente = null;
           this.medida =  null;
           guardar = true;
      }
    /**
     * 
     * @throws Exception 
     */
    public void guardarPedido() throws Exception {
        if (codsucursal == 0) {
            alert("Debe selecionar una sucursal.", FacesMessage.SEVERITY_WARN);
            return;
        }
         String email = getParemetro(String.valueOf(codsucursal),
                 sucMap);
       Sucursal suc = new Sucursal();
       suc.setIdsucursal(codsucursal);
       suc.setEmail(email);
        oderPedido =  new OrdenpedidoDTO();
//        this.oderPedido.setSucursal(suc);
        this.oderPedido.setFechapedido(new Date());
//        this.oderPedido.setIdcliente(sesion.getCliente());
        oderPedido.setOrdenpedidodetalleList(lstDetalle);
//        this.set.setIdcliente(cliente);


    String jsonDatos = new Gson().toJson(oderPedido);
       String jsonRetur = this.consumerWS.consumirWebservices(
                    sesion.getUserCliente(),jsonDatos, 
                    URLBASE + "GuardarPedido");
            System.out.println("jsonRetur... " + jsonRetur);
          alert(jsonRetur, FacesMessage.SEVERITY_INFO);
          System.out.println("suc.getEmail() .,... " +suc.getEmail() );
          try {
              if (suc.getEmail() != null) {
              enviarcorreo(suc.getEmail());
              
              Logger.getLogger(PedidoWeb.class.getName()).log(Level.INFO,
                      "El correo se envio exitosamente.");
              } else {
                 
              Logger.getLogger(PedidoWeb.class.getName()).log(Level.INFO,
                      "la sucursal no tiene asignado correo ");   
              }
              guardar = false;
        } catch (Exception e) {
           Logger.getLogger(PedidoWeb.class.getName()).log(
                    Level.SEVERE, null, e.getMessage());
        }
    }
    public List<Vwproductos> getLstProducto() {
        return lstProducto;
    }
 
    public void setLstProducto(List<Vwproductos> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public int getCodsucursal() {
        return codsucursal;
    }

    public void setCodsucursal(int codsucursal) {
        this.codsucursal = codsucursal;
    }

    public List<Sucursal> getLstSucursal() {
        return lstSucursal;
    }

    public void setLstSucursal(List<Sucursal> lstSucursal) {
        this.lstSucursal = lstSucursal;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public List<OrdenpedidodetalleDTO> getLstDetalle() {
        return lstDetalle;
    }
    

    public void setLstDetalle(List<OrdenpedidodetalleDTO> lstDetalle) {
        this.lstDetalle = lstDetalle;
    }

    public ConsumerWSLocal getConsumerWS() {
        return consumerWS;
    }

    public void setConsumerWS(ConsumerWSLocal consumerWS) {
        this.consumerWS = consumerWS;
    }

    public Producto getProduct() {
        return product;
    }

    public void setProduct(Producto product) {
        this.product = product;
    }

    public Double getCantidadSolic() {
        return cantidadSolic;
    }

    public void setCantidadSolic(Double cantidadSolic) {
        this.cantidadSolic = cantidadSolic;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMedida() {
        return medida;
    }

    public void setMedida(String medida) {
        this.medida = medida;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public OrdenpedidoDTO getOderPedido() {
        return oderPedido;
    }

    public void setOderPedido(OrdenpedidoDTO oderPedido) {
        this.oderPedido = oderPedido;
    }

    public String getDescripCliente() {
        return descripCliente;
    }

    public void setDescripCliente(String descripCliente) {
        this.descripCliente = descripCliente;
    }

    public boolean isGuardar() {
        return guardar;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public List<OrdenpedidoDTO> getLstedidos() {
        return lstedidos;
    }

    public void setLstedidos(List<OrdenpedidoDTO> lstedidos) {
        this.lstedidos = lstedidos;
    }

    public void setGuardar(boolean guardar) {
        this.guardar = guardar;
    }

    
    public static < T> T getParemetro(Object object, Map filtros) {
        if (filtros == null || object == null) {
            return null;
        }
        return (T) filtros.get(object);
    } 
        
}

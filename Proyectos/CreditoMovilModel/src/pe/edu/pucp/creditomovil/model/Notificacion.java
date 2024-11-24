/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.model;

/**
 *
 * @author aaron
 */
public class Notificacion {
    private int id_notificacion;
    private String mensaje;
    private int id_usuario;
    private int activo;
    
    public Notificacion(){}
    public Notificacion(int id_notificacion, String mensaje, int id_usuario, int activo){
        this.id_notificacion = id_notificacion;
        this.mensaje = mensaje;
        this.id_usuario = id_usuario;
        this.activo = activo;
    }

    /**
     * @return the id_notificacion
     */
    public int getId_notificacion() {
        return id_notificacion;
    }

    /**
     * @param id_notificacion the id_notificacion to set
     */
    public void setId_notificacion(int id_notificacion) {
        this.id_notificacion = id_notificacion;
    }

    /**
     * @return the mensaje
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * @param mensaje the mensaje to set
     */
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    /**
     * @return the id_usuario
     */
    public int getId_usuario() {
        return id_usuario;
    }

    /**
     * @param id_usuario the id_usuario to set
     */
    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * @return the activo
     */
    public int getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(int activo) {
        this.activo = activo;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmacia.modelo;

import com.mycompany.farmacia.clases.Sentencias;
import com.mycompany.farmacia.clases.conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mazie
 */
public class Medicamento extends conexion implements Sentencias{
    private int id;
    private String nombre;
    private double precio;
    //private String tipoVenta;
    private String formaAdministracion;
    private int cantidad;
    private Date fechaCaducidad;
    private int idProveedor;
    private int idCategoria;

    // Constructors
    public Medicamento() {
    }

    public Medicamento(int id, String nombre, double precio, String formaAdministracion, int cantidad, Date fechaCaducidad, int idProveedor, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        //this.tipoVenta = tipoVenta;
        this.formaAdministracion = formaAdministracion;
        this.cantidad = cantidad;
        this.fechaCaducidad = fechaCaducidad;
        this.idProveedor = idProveedor;
        this.idCategoria = idCategoria;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /*public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }*/

    public String getFormaAdministracion() {
        return formaAdministracion;
    }

    public void setFormaAdministracion(String formaAdministracion) {
        this.formaAdministracion = formaAdministracion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Medicamento{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                //", tipoVenta='" + tipoVenta + '\'' +
                ", formaAdministracion='" + formaAdministracion + '\'' +
                ", cantidad=" + cantidad +
                ", fechaCaducidad=" + fechaCaducidad +
                ", idProveedor=" + idProveedor +
                ", idCategoria=" + idCategoria +
                '}';
    }

    @Override
    public ArrayList consulta() {
        ArrayList<Medicamento> medicamentos = new ArrayList<>();
        String sql = "SELECT * FROM medicamento";
       
        try (Connection con = getCon();
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
           
            while (rs.next()) {
                int ide = rs.getInt("ID_medicamento");
                String nom = rs.getString("nombre");
                double pre = rs.getDouble("precio");
                //String tipoV = rs.getString("tipo_venta");
                String formaAd = rs.getString("forma_administracion");
                int cant = rs.getInt("cantidad");
                Date fechaCad = rs.getDate("fecha_caducidad");
                int idProv = rs.getInt("id_proveedor");
                int idCat = rs.getInt("id_categoria");
               
                Medicamento medicamento = new Medicamento(ide, nom, pre, formaAd, cant, fechaCad, idProv, idCat);
                medicamentos.add(medicamento);
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(Medicamento.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return medicamentos;
    }

    @Override
    public boolean insertar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean modificar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

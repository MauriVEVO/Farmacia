/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmacia.modelo;

import com.mycompany.farmacia.clases.conexion;
import com.mycompany.farmacia.clases.Sentencias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Federico
 */
public class detalleVenta extends conexion implements Sentencias{
    private int cod;
    private int codProd;
    private String descripcion;
    private double precio;
    private int cantidad;
    private double subTotal;

    public detalleVenta() {
    }
  
    
    public detalleVenta(int codProd, String descripcion, double precio, int cantidad, double subTotal) {
        this.codProd = codProd;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public detalleVenta(int cod, int codProd, String descripcion, double precio, int cantidad, double subTotal) {
        this.cod = cod;
        this.codProd = codProd;
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }
    
    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodProd() {
        return codProd;
    }

    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }

    @Override
    public boolean insertar() {
        String sql="insert into detalle_venta (Medicamento_ID_medicamento, Venta_id_Venta, cantidad) values(?,?,?)";
        try( 
            Connection con=getCon();
            PreparedStatement stm=con.prepareStatement(sql)){
            stm.setInt(1,this.codProd);
            stm.setInt(2,this.cod);
            stm.setInt(3,this.cantidad);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(detalleVenta.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    @Override
    public ArrayList consulta() {
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
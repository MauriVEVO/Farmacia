/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmacia.modelo;

import com.mycompany.farmacia.clases.conexion;
import com.mycompany.farmacia.clases.Sentencias;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Federico
 */
public class venta extends conexion implements Sentencias{
    private int nro;
    private String fecha;
    private String metodo;
    private int codCliente;
    private int codEmpleado;
    private double total;

    public venta(int nro, String fecha, String metodo, int codCliente, int codEmpleado, double total) {
        this.nro = nro;
        this.fecha = fecha;
        this.metodo = metodo;
        this.codCliente = codCliente;
        this.codEmpleado = codEmpleado;
        this.total = total;
    }

    public venta() {
    }
    
    public int getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(int codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public boolean insertar() {
        String sql="insert into venta (fecha, total, metodo_de_pago, Cliente_id_Cliente, Empleado_id_Empleado) values(?,?,?,?,?)";
        try( 
            Connection con=getCon();
            PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            //stm.setInt(1,this.nro);
            stm.setString(1,this.fecha);
            stm.setDouble(2,this.total);
            stm.setString(3,this.metodo);
            stm.setInt(4,this.codCliente);
            stm.setInt(5,this.codEmpleado);
            
            int affectedRows = stm.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("No se insertó fila, no se generó id.");
            }
            
            try (ResultSet rs = stm.getGeneratedKeys()) {
                if (rs.next()) {
                    this.nro = rs.getInt(1);
                }
            }
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
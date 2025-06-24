/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmacia.modelo;

import com.mycompany.farmacia.clases.Sentencias;
import com.mycompany.farmacia.clases.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 *
 * @author natha
 */
public class Cliente extends conexion implements Sentencias {
    private int id; 
    private String nombre; 
    private String apellido; 

    public Cliente() {
        this(0, "", ""); 
    }
    
    
    
    public Cliente(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    @Override
    public boolean insertar() {
        String sql =" insert into cliente (id_Cliente, nombre, apellido) values (?, ?, ?)";
        try (
            
            Connection con = getCon();
            PreparedStatement stm = con.prepareStatement(sql))
        {    
            stm.setInt(1, this.id);
            stm.setString(2, this.nombre);
            stm.setString(3, this.apellido);
            stm.executeUpdate(); //ejecuta la inserccion uwu
            return true; 
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false; 
        }
        
        
    }

    @Override
    public ArrayList consulta() {
        ArrayList<Cliente> clientes = new ArrayList();
        try {
            
            String sql = "Select * from cliente";
            Connection con = getCon();
            Statement stm = con.createStatement(); 
            ResultSet rs = stm.executeQuery(sql);
            
            while(rs.next()){
                int cod = rs.getInt(1);
                String nom = rs.getString(2); 
                String ape = rs.getString(3); 
                Cliente c = new Cliente(cod, nom, ape); 
                clientes.add(c); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clientes; 
    }

    @Override
    public boolean modificar() {
        String sql ="update cliente set nombre=?, apellido=? where id_Cliente=?";
        try (
            
            Connection con = getCon();
            PreparedStatement stm = con.prepareStatement(sql))
        {    
            
            stm.setString(1, this.nombre);
            stm.setString(2, this.apellido);
            stm.setInt(3, this.id);
            stm.executeUpdate(); //ejecuta la inserccion uwu
            return true; 
        } catch (SQLException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            return false; 
        }
    }

    @Override
    public boolean eliminar() {
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("AVISO BORRADO"); 
        alerta.setHeaderText(null); 
        alerta.setContentText("Desea eliminar el registro?"); 
        Optional<ButtonType> opcion=alerta.showAndWait(); 
        String sql ="delete from cliente where id_Cliente=?";

        if(opcion.get()==ButtonType.OK){
            try (
            
                Connection con = getCon();
                PreparedStatement stm = con.prepareStatement(sql))
            {    


                stm.setInt(1, this.id);
                stm.executeUpdate(); //ejecuta la inserccion uwu
                return true; 
            } catch (SQLException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
                return false; 
            }
        } else{
              return false;
        }
      
        
    }
    
}

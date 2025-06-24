/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.farmacia.clases;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author mazie
 */
public class Reportes extends conexion{

    public Reportes() {
    }
    
    public void generarReporte(String ubicacion,String titulo){
       
        try {
            // Ruta al archivo .jasper
            String reportPath = getClass().getResource(ubicacion).getPath();
             
            // Parámetros del informe
            Map<String, Object> parameters = new HashMap<>();
            // Agrega parámetros según sea necesario
           
            // Llenar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, getCon());
             
            // Mostrar el informe en una nueva ventana
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle(titulo);
            viewer.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void generarReporteParametro(String ubicacion,String titulo, int parametro){
       
        try {
            // Ruta al archivo .jasper
            String reportPath = getClass().getResource(ubicacion).getPath();
             
            // Parámetros del informe
            Map<String, Object> parameters = new HashMap<>();
            // Agrega parámetros según sea necesario
            parameters.put("Codigo", parametro);
             
            // Llenar el informe
            JasperPrint jasperPrint = JasperFillManager.fillReport(reportPath, parameters, getCon());
             
            // Mostrar el informe en una nueva ventana
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle(titulo);
            viewer.setVisible(true);

        } catch (JRException ex) {
            Logger.getLogger(Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

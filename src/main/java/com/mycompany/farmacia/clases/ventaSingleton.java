/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Singleton.java to edit this template
 */
package com.mycompany.farmacia.clases;

/**
 *
 * @author Federico
 */
// Clase ventaSingleton implementando el patrón Singleton, que no permite crear mas de un objeto de la clase
public class ventaSingleton {

    // Atributos de la clase: código del cliente y código del producto
    int codCliente;
    int codProd;

    // Constructor privado para evitar que se puedan crear instancias desde fuera de la clase
    private ventaSingleton() {
    }

    // Método público estático para obtener la única instancia de la clase
    public static ventaSingleton getInstance() {
        return ventaSingletonHolder.INSTANCE; // Retorna la instancia contenida en la clase interna
    }

    // Clase estática interna que contiene la instancia única de ventaSingleton
    // Esta técnica asegura que la instancia se cree solo cuando se llama a getInstance()
    private static class ventaSingletonHolder {
        private static final ventaSingleton INSTANCE = new ventaSingleton(); // Instancia única
    }

    // Getter para obtener el código del cliente
    public int getCodCliente() {
        return codCliente;
    }

    // Setter para establecer el código del cliente
    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    // Getter para obtener el código del producto
    public int getCodProd() {
        return codProd;
    }

    // Setter para establecer el código del producto
    public void setCodProd(int codProd) {
        this.codProd = codProd;
    }
}


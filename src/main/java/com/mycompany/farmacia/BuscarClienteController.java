/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.farmacia;

import com.mycompany.farmacia.clases.ventaSingleton;
import com.mycompany.farmacia.modelo.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mazie
 */
public class BuscarClienteController implements Initializable {
    Cliente c = new Cliente(); 
    ObservableList<Cliente> registros; 
    ObservableList<Cliente> registrosFiltrados; 
    boolean modificar = false; 

    @FXML
    private TextField txtBuscar;
    @FXML
    private TableView<Cliente> tablaCliente;
    @FXML
    private TableColumn<Cliente, Integer> columCod;
    @FXML
    private TableColumn<Cliente, String> columNom;
    @FXML
    private TableColumn<Cliente, String> columApe;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrarDatos();
    }    
    
    @FXML
    private void busqueda(KeyEvent event) {
        registrosFiltrados = FXCollections.observableArrayList(); 
        String busqueda = txtBuscar.getText(); 
        if(busqueda.isEmpty()){
            tablaCliente.setItems(registros); //todos los datos 
            
        } else {
            registrosFiltrados.clear(); 
            for(Cliente registro : registros){
                if(registro.getNombre().toLowerCase().contains(busqueda.toLowerCase())){
                    registrosFiltrados.add(registro); 
                }//fin if
            }//fin del for (p0r si sos pdjo uwu)
            tablaCliente.setItems(registrosFiltrados); 
        }
        
    }

    @FXML
    private void mostrarFila(MouseEvent event) {
        Cliente clie = tablaCliente.getSelectionModel().getSelectedItem(); 
        int cod = clie.getId();
        ventaSingleton.getInstance().setCodCliente(cod);
        //cerrar la ventana luego de seleccionar la fila
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    private void mostrarDatos() {
        registros = FXCollections.observableArrayList(c.consulta()); 
        columCod.setCellValueFactory(new PropertyValueFactory<>("id")); //la columna id de la tabla del modelado corresponde al atributo id
        columNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columApe.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tablaCliente.setItems(registros); 
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.farmacia;

import com.mycompany.farmacia.clases.ventaSingleton;
import com.mycompany.farmacia.modelo.Medicamento;
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
 * @author Federico
 */
public class BuscarProductoController implements Initializable {

    @FXML
    private TextField txtBuscar;
  
    @FXML
    private TableColumn<Medicamento, Integer> columCod;
    @FXML
    private TableColumn<Medicamento, String> columNom;
    @FXML
    private TableColumn<Medicamento, Double> columPrecio;
 Medicamento m=new Medicamento();//objeto de la clase cliente
    ObservableList<Medicamento> registros;//lista para cargar la tabla
    ObservableList<Cliente> registrosFiltrados;//lista filtrada
    @FXML
    private TableView<Medicamento> tablaProducto;
   
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
    }

    @FXML
    private void mostrarFila(MouseEvent event) {
        Medicamento m=tablaProducto.getSelectionModel().getSelectedItem();
     int cod=m.getId();
     ventaSingleton.getInstance().setCodProd(cod);
     //cerrar la ventana luego de seleccionar la fila
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
     public void mostrarDatos(){
       registros=FXCollections.observableArrayList(m.consulta());
       columCod.setCellValueFactory(new PropertyValueFactory<>("id"));
       columNom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
       columPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
       tablaProducto.setItems(registros);
}
}
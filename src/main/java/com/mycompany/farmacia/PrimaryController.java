/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.farmacia;

import com.mycompany.farmacia.modelo.Cliente;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author natha
 */
public class PrimaryController implements Initializable {


    @FXML
    private TextField txt_codigo;
    @FXML
    private TextField txt_nombre;
    @FXML
    private TextField txt_apellido;
    @FXML
    private TextField txt_buscar;
    @FXML
    private TableView<Cliente> tabla_clientes;
    @FXML
    private TableColumn<Cliente, Integer> col_id;
    @FXML
    private TableColumn<Cliente, String> col_nombre;
    @FXML
    private TableColumn<Cliente, String> col_apellido;
    @FXML
    private Button btn_nuevo;
    @FXML
    private Button btn_modificar;
    @FXML
    private Button btn_eliminar;
    @FXML
    private Button btn_guardar;
    @FXML
    private Button btn_cancelar;
    //global uwu
    Cliente c = new Cliente(); 
    ObservableList<Cliente> registros; 
    ObservableList<Cliente> registrosFiltrados; 
    boolean modificar = false; 
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        mostrarDatos(); 
    }    

    @FXML
    private void nuevo(ActionEvent event) {
        txt_codigo.setDisable(false); 
        txt_nombre.setDisable(false); 
        txt_apellido.setDisable(false); 
        btn_guardar.setDisable(false); 
        btn_cancelar.setDisable(false); 
        btn_nuevo.setDisable(true); // deshabilitar uwu
        txt_codigo.requestFocus();//muestra el cursor en el campo <3
        
        
    }

    @FXML
    private void cancelar(ActionEvent event) {
        //limpiar
        txt_codigo.clear(); 
        txt_nombre.clear(); 
        txt_apellido.clear(); 
        //deshabilitar
        txt_codigo.setDisable(true);
        txt_nombre.setDisable(true);
        txt_apellido.setDisable(true);
        btn_guardar.setDisable(true); 
        btn_cancelar.setDisable(true); 
        btn_nuevo.setDisable(false); 
        btn_modificar.setDisable(true); 
        btn_eliminar.setDisable(true); 
        
        
    }

    @FXML
    private void guardar(ActionEvent event) {
        //recibir los datos uwu
        int cod = Integer.parseInt(txt_codigo.getText()); 
        String nombre = txt_nombre.getText(); 
        String apellido = txt_apellido.getText(); 
        c.setId(cod); 
        c.setNombre(nombre); 
        c.setApellido(apellido); 
        if(modificar){//quiere guardar lo editado <3
            if(c.modificar()){//si pudo modificar
                Alert alerta = new Alert(Alert.AlertType.INFORMATION); 
                alerta.setTitle("El sistema comunica: "); 
                alerta.setHeaderText("");
                alerta.setContentText("Datos modificados correctamente"); 
                alerta.show(); 
            } else{
                Alert alerta = new Alert(Alert.AlertType.ERROR); 
                alerta.setTitle("El sistema comunica: "); 
                alerta.setHeaderText("");
                alerta.setContentText("Datos no modificados"); 
                alerta.show();
            }
            modificar = false; //activa la bandera 
        }else {
            if(c.insertar()){//insertar uwu 
                Alert alerta = new Alert(Alert.AlertType.INFORMATION); 
                alerta.setTitle("El sistema comunica: "); 
                alerta.setHeaderText("");
                alerta.setContentText("Datos insertados correctamente"); 
                alerta.show();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR); 
                alerta.setTitle("El sistema comunica: "); 
                alerta.setHeaderText("");
                alerta.setContentText("Datos no insertados"); 
                alerta.show();
            }
        }
        mostrarDatos(); 
        cancelar(event); //llama al boton cancelar uwu
        
        
    }

    private void mostrarDatos() {
        registros = FXCollections.observableArrayList(c.consulta()); 
        col_id.setCellValueFactory(new PropertyValueFactory<>("id")); //la columna id de la tabla del modelado corresponde al atributo id
        col_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        col_apellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        tabla_clientes.setItems(registros); 
    }
    
    @FXML
    private void buscar(KeyEvent event){
        registrosFiltrados = FXCollections.observableArrayList(); 
        String busqueda = txt_buscar.getText(); 
        if(busqueda.isEmpty()){
            tabla_clientes.setItems(registros); //todos los datos 
            
        } else {
            registrosFiltrados.clear(); 
            for(Cliente registro : registros){
                if(registro.getNombre().toLowerCase().contains(busqueda.toLowerCase())){
                    registrosFiltrados.add(registro); 
                }//fin if
            }//fin del for (p0r si sos pdjo uwu)
            tabla_clientes.setItems(registrosFiltrados); 
        }
        
    }

    @FXML
    private void mostrarFila(MouseEvent event) {
        Cliente clie = tabla_clientes.getSelectionModel().getSelectedItem(); 
        txt_codigo.setText(String.valueOf(clie.getId())); 
        txt_nombre.setText(clie.getNombre()); 
        txt_apellido.setText(clie.getApellido()); 
        btn_eliminar.setDisable(false); 
        btn_modificar.setDisable(false); 
        txt_nombre.setDisable(true); //habilitar uwu
        txt_apellido.setDisable(true);
        btn_cancelar.setDisable(false); 
        btn_nuevo.setDisable(true); 
    }

    @FXML
    private void modificar(ActionEvent event) {
        txt_nombre.setDisable(false); //habilitar uwu
        txt_apellido.setDisable(false); //habilitar uwu
        btn_eliminar.setDisable(true); 
        btn_nuevo.setDisable(true); 
        btn_modificar.setDisable(true); 
        btn_guardar.setDisable(false); 
        modificar = true; 
        
        
        
    }

    @FXML
    private void eliminar(ActionEvent event) {
        int cod = Integer.parseInt(txt_codigo.getText()); 
        String nombre = txt_nombre.getText(); 
        String apellido = txt_apellido.getText(); 
        c.setId(cod); 
        c.setNombre(nombre); 
        c.setApellido(apellido); 
        if(c.eliminar()){//insertar uwu 
                Alert alerta = new Alert(Alert.AlertType.INFORMATION); 
                alerta.setTitle("El sistema comunica: "); 
                alerta.setHeaderText("");
                alerta.setContentText("Datos eliminados correctamente"); 
                alerta.show();
            } else {
                Alert alerta = new Alert(Alert.AlertType.ERROR); 
                alerta.setTitle("El sistema comunica: "); 
                alerta.setHeaderText("");
                alerta.setContentText("Datos no eliminados"); 
                alerta.show();
            }
        mostrarDatos(); 
        cancelar(event); 
        
    }
    
}

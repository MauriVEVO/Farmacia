/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.farmacia;

import com.mycompany.farmacia.clases.conexion;
import com.mycompany.farmacia.clases.ventaSingleton;
import com.mycompany.farmacia.modelo.Cliente;
import com.mycompany.farmacia.modelo.Medicamento;
import com.mycompany.farmacia.modelo.detalleVenta;
import com.mycompany.farmacia.modelo.venta;
//import com.mysql.cj.xdevapi.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import org.apache.batik.ext.awt.image.spi.RegistryEntry;

/**
 * FXML Controller class
 *
 * @author mazie
 */
public class FacturaController extends conexion implements Initializable{

    @FXML
    private TableView<detalleVenta> TablaFactura;
    @FXML
    private TableColumn<detalleVenta, Integer> ColumnCodigo;
    @FXML
    private TableColumn<detalleVenta, String> ColumnDescripcion;
    @FXML
    private TableColumn<detalleVenta, Integer> ColumnPrecio;
    @FXML
    private TableColumn<detalleVenta, Integer> ColumnCantidad;
    @FXML
    private TableColumn<detalleVenta, Double> ColumnSubtotal;
    @FXML
    private TextField TxtNroFactura;
    @FXML
    private TextField TxtCliente;
    @FXML
    private TextField TxtProductos;
    @FXML
    private DatePicker FechaFactura;
    @FXML
    private TextField TxtCantidad;
    @FXML
    private Button BtnNuevo;
    @FXML
    private Button BtnGrabar;
    @FXML
    private Button BtnCancelar;
    @FXML
    private Button BtnImprimir;
    @FXML
    private TextField TxtTotal;
    @FXML
    private Button BtnCliente;
    @FXML
    private Button BtnAgregar;
    @FXML
    private ComboBox<String> MenuFactura;
    @FXML
    private Button btnBuscarProd;
    
    int codCliente;
    int codProd = 0;
    double precio = 0;

    //para clientes
    Cliente c = new Cliente(); //objeto de la clase cliente
    ObservableList<Cliente> registros;
    
    //para productos
    Medicamento m = new Medicamento();
    ObservableList<Medicamento> registrosMedicamentos;
    double suma;
    
    //para venta
    detalleVenta dv = new detalleVenta();
    ObservableList<detalleVenta> registrosDetalle;
    venta v = new venta();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        registros = FXCollections.observableArrayList(c.consulta());
        registrosMedicamentos = FXCollections.observableArrayList(m.consulta());
        registrosDetalle = FXCollections.observableArrayList();
        ///definicion de las columnas de la tabla
        ColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("codProd"));
        ColumnDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        ColumnPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        ColumnCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        ColumnSubtotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));
    }    
    
    public void abrirFxmlModal(String fxml, String titulo){
     
        try {
            FXMLLoader loader=new FXMLLoader(getClass().getResource(fxml));
            Parent root=loader.load();
            Stage stage=new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle(titulo);
            stage.setResizable(false); // Evita que la ventana sea redimensionable
            stage.setMinWidth(300); // Establece un ancho mínimo
            stage.setMinHeight(200); // Establece una altura mínima
            stage.showAndWait();
        } catch (IOException ex) {
            Logger.getLogger(FacturaController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void nuevo(ActionEvent event) {
        //TxtNroFactura.setDisable(false);
        //TxtNroFactura.requestFocus();
        BtnCliente.setDisable(false);
        //FechaFactura.setDisable(false);
        BtnNuevo.setDisable(true);
        //metodo de pago
        MenuFactura.setDisable(false);
       
        MenuFactura.setPromptText("Seleccione Método");
        MenuFactura.getItems().add("Efectivo");
        MenuFactura.getItems().add("Tarjeta de Débito");
        MenuFactura.getItems().add("Tarjeta de Crédito");
        //fecha actual
        FechaFactura.setValue(LocalDate.now());
    }

    @FXML
    private void buscarCliente(ActionEvent event) {
        abrirFxmlModal("buscarCliente.fxml", "Buscar Cliente");
        registros = FXCollections.observableArrayList(c.consulta());
        codCliente = ventaSingleton.getInstance().getCodCliente();
        for (Cliente registro : registros) {
            if (registro.getId() == codCliente) {
                TxtCliente.setText(registro.getNombre() + " " + registro.getApellido());
            } //fin if
        } //fin for
        
        btnBuscarProd.setDisable(false);
    }

    @FXML
    private void buscarProducto(ActionEvent event) {
        abrirFxmlModal("buscarProducto.fxml","Buscar Producto");
         codProd = ventaSingleton.getInstance().getCodProd();
         for (Medicamento object : registrosMedicamentos) {
            if(object.getId()==codProd){
                TxtProductos.setText(object.getNombre());
               precio=object.getPrecio();
            }              
        }
          TxtCantidad.setDisable(false);
          BtnAgregar.setDisable(false);
    }

    @FXML
    private void AgregarFila(ActionEvent event) {
        if(!TxtCantidad.getText().isEmpty()){ // sino esta vacio el campo de texto cantidad
            double subtotal=precio*Integer.parseInt(TxtCantidad.getText());// se calcula el subtotal(precio por cantiadad)
            suma=suma+subtotal;// se calcula la suma(variable global)
            // se crea un nuevo objeto de tipo detalle de venta, esta declaradado de forma global
            dv=new detalleVenta(codProd, TxtProductos.getText(), precio,Integer.parseInt(TxtCantidad.getText()),subtotal);
            //se agrega el objeto a un observable list(previamente declarado e inicializado)
            registrosDetalle.add(dv);
            //se asocia con la tabla la lista, la tabla debe estar formateado correctamente
            //(indicar que modelo lleva e inicializar las columnas)    
            TablaFactura.setItems(registrosDetalle);
            //se muestra el total formateado a dos decimales
            TxtTotal.setText(String.format("%.2f", suma));
            //se limpia los campos y se desactivan
            TxtProductos.clear();
            TxtCantidad.clear();
            TxtCantidad.setDisable(true);
            BtnGrabar.setDisable(false);
             
        }else{
           //mensaje correspondiente(ventana emergente)
            System.out.println("no debe quedar vacio");
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
        //limpiar los campos
       TextField[] fields = {TxtNroFactura, TxtCantidad, TxtCliente,TxtProductos,TxtTotal};
        for (TextField field : fields) {
            field.clear();
            field.setDisable(true);
        }
        MenuFactura.getItems().clear();
        MenuFactura.setDisable(true);
        //FechaFactura.setDisable(true);
        BtnGrabar.setDisable(true);
        BtnNuevo.setDisable(false);
        BtnCliente.setDisable(true);
        btnBuscarProd.setDisable(true);
        BtnAgregar.setDisable(true);
        BtnImprimir.setDisable(true);
        TablaFactura.getItems().clear();
    }

    @FXML
    private void grabar(ActionEvent event) throws SQLException {
        //mostramos un mensaje para grabar
        Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle("El sistema comunica;");
        alerta.setHeaderText(null);
        alerta.setContentText("¿Desea grabar la venta?");
        Optional<ButtonType> opcion=alerta.showAndWait();
        if(opcion.get()==ButtonType.OK){//si presiona ok
            //enviamos los datos al objeto previamente creado de forma global
            //v.setNro(Integer.parseInt(TxtNroFactura.getText()));
            v.setFecha(FechaFactura.getValue().toString());
            v.setMetodo(MenuFactura.getSelectionModel().getSelectedItem());
            v.setCodCliente(codCliente);
            v.setCodEmpleado(1);// debe existir en la base  de dato
            v.setTotal(suma);
            if(v.insertar()){//llama al metodo insertar de venta
                TxtNroFactura.setText(String.valueOf(v.getNro()));
                BtnImprimir.setDisable(false);
                TxtNroFactura.setDisable(false);
                TxtNroFactura.setEditable(false);
                //recorremos la lista detalle venta y obtenemos los valores y los enviamos al modelo detalle
                try (Connection con = getCon()){
                    for (detalleVenta object : registrosDetalle) {
                        String checkSql = "SELECT cantidad FROM detalle_venta "
                                            + "WHERE Medicamento_ID_medicamento = ? AND Venta_id_Venta = ?";
                        try (PreparedStatement check = con.prepareStatement(checkSql)){
                            check.setInt(1, object.getCodProd());
                            check.setInt(2, v.getNro());
                            ResultSet rs = check.executeQuery();
                            if (rs.next()) {
                                int nuevaCant = rs.getInt("cantidad") + object.getCantidad();
                                String updSql = "UPDATE detalle_venta SET cantidad = ?, subTotal = ? "
                                              + "WHERE Medicamento_ID_medicamento = ? AND Venta_id_Venta = ?";
                                try (PreparedStatement upd = con.prepareStatement(updSql)) {
                                    upd.setInt(1, nuevaCant);
                                    upd.setDouble(2, nuevaCant * object.getPrecio());
                                    upd.setInt(3, object.getCodProd());
                                    upd.setInt(4, v.getNro());
                                    upd.executeUpdate();
                                }
                            } else {
                                String insSql = "INSERT INTO detalle_venta "
                                              + "(Medicamento_ID_medicamento, Venta_id_Venta, cantidad, subTotal) "
                                              + "VALUES (?, ?, ?, ?)";
                                try (PreparedStatement ins = con.prepareStatement(insSql)) {
                                    ins.setInt(1, object.getCodProd());
                                    ins.setInt(2, v.getNro());
                                    ins.setInt(3, object.getCantidad());
                                    ins.setDouble(4, object.getSubTotal());
                                    ins.executeUpdate();
                                }
                            }
                        }
                            //dv.setCod(Integer.parseInt(TxtNroFactura.getText()));
                            dv.setCod(v.getNro());
                            dv.setCodProd(object.getCodProd());
                            dv.setCantidad(object.getCantidad());
                            dv.insertar();   //llama al metodo insertar de detalle venta
                    }
                }
                    //mensaje de confirmacion
                        Alert alertaIn=new Alert(Alert.AlertType.INFORMATION);
                        alertaIn.setTitle("El sistema comunica:");
                        alertaIn.setHeaderText(null);
                        alertaIn.setContentText("Insertado correctamente en la base de datos");
                        alertaIn.show();
                        //habilitar Imprimir
                         BtnImprimir.setDisable(false);
                          //mensaje de error
            }else{
                Alert alertaIn=new Alert(Alert.AlertType.ERROR);
                alertaIn.setTitle("El sistema comunica:");
                alertaIn.setHeaderText(null);
                alertaIn.setContentText("Error. Registro no insertado.");
                alertaIn.show();
            }
        }
        cancelar(event);//ejecuta el boton cancelar
    }
}

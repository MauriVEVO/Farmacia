module com.mycompany.farmacia {
    requires javafx.controls;
    requires javafx.fxml;
    //para la base de datos 
    requires java.sql; 
    requires java.base; 
    requires net.sf.jasperreports.core;
    
    opens com.mycompany.farmacia to javafx.fxml;
    exports com.mycompany.farmacia;
    exports com.mycompany.farmacia.clases;
    exports com.mycompany.farmacia.modelo;
    
}

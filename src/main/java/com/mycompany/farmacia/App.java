package com.mycompany.farmacia;

import com.mycompany.farmacia.clases.conexion;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Alert;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        
        conexion conectar = new conexion(); 
        if(conectar.getCon()!=null){
            scene = new Scene(loadFXML("MenuPrincipal"), 700, 600);
            stage.setScene(scene);
            stage.show();
            
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de conexion");
            alert.setHeaderText("Por favor, revise su conexion y vuelva a intentarlo.");
            alert.showAndWait();
        }
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
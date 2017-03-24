/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author daw
 */
public class Pizzeria extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        Parent root1 = FXMLLoader.load(getClass().getResource("FXMLDocument1.fxml"));
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
//        Scene scene1 = new Scene(root1);
//        stage.setScene(scene1);
//        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

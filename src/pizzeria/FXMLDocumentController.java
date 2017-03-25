/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.awt.Image;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author daw
 */
public class FXMLDocumentController implements Initializable {
    
    Pizza p;
    Precios pre;
    @FXML
    private ToggleButton buttonFina;
    @FXML
    private ToggleButton buttonGorda;
    @FXML
    private Label resumenTipoMasa;
    @FXML
    private Label resumenTotal;
    @FXML
    private AnchorPane menuMasa;
    @FXML
    private AnchorPane resultado;
    @FXML
    private Label resumenNumeroPizza;
    @FXML
    private Label resumenTipoPizza;
    @FXML
    private AnchorPane menuTipoPizza;
    @FXML
    private ToggleButton pizzaBasica;
    @FXML
    private ToggleButton pizzaCuatroQuesos;
    @FXML
    private ToggleButton pizzaBarbacoa;
    @FXML
    private ToggleButton pizzaMexicana;
    @FXML
    private ToggleGroup tipoPizzas;
    @FXML
    private ToggleGroup tipoMasas;
    @FXML
    private Button siguienteMasa;
    @FXML
    private Button siguientePizza;
    @FXML
    private Button volverPizza;
    @FXML
    private AnchorPane menuIngredientes;
    @FXML
    private ToggleButton sinIngredientes;
    @FXML
    private Button volverIngredientes;
    @FXML
    private ToggleButton ingredienteCebollaImagen;
    @FXML
    private ToggleButton sinIngredientesImagen;
    @FXML
    private RadioButton ingredienteCebolla;
    @FXML
    private RadioButton ingredienteJamon;
    @FXML
    private ToggleButton ingredienteJamonImagen;
    @FXML
    private RadioButton ingredienteQueso;
    @FXML
    private ToggleButton ingredienteQuesoImagen;
    @FXML
    private RadioButton ingredienteOlivas;
    @FXML
    private ToggleButton ingredienteOlivasImagen;
    @FXML
    private RadioButton ingredienteTomate;
    @FXML
    private ToggleButton ingredienteTomateImagen;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p = new Pizza();
        this.pizzaBasica.setUserData("Basica");
        this.pizzaCuatroQuesos.setUserData("CuatroQuesos");
        this.pizzaBarbacoa.setUserData("Barbacoa");
        this.pizzaMexicana.setUserData("Mexicana");
        this.buttonFina.setUserData("fina");
        this.buttonGorda.setUserData("gruesa");
        this.ingredienteCebolla.setUserData("cebolla");
        this.ingredienteJamon.setUserData("jamon");
        this.ingredienteOlivas.setUserData("olivas");
        this.ingredienteQueso.setUserData("queso");
        this.ingredienteTomate.setUserData("tomate");
        this.sinIngredientes.setUserData("sinIngredientes");
        pre = new Precios();
        
    }
    
    @FXML
    private void masa(ActionEvent event) {
        String tipo = "";
        if (this.tipoMasas.getSelectedToggle() != null) {
            tipo = this.tipoMasas.getSelectedToggle().getUserData().toString();
            elegirMasa(tipo);
        }
        
    }
    
    @FXML
    private void tipoPizza(ActionEvent event) {
        String tipo = "";
        if (this.tipoPizzas.getSelectedToggle() != null) {
            tipo = this.tipoPizzas.getSelectedToggle().getUserData().toString();
            elegirPizza(tipo);
        }
    }
    
    @FXML
    private void ingredientes(ActionEvent event) {
        Optional<RadioButton> tipo;
        elegirIngredientes(event.getSource());
        
    }
    
    @FXML
    private void siguienteMasa(ActionEvent event) {
        this.menuMasa.setVisible(false);
        this.menuTipoPizza.setVisible(true);
    }
    
    @FXML
    private void volverPizza(ActionEvent event) {
        this.menuMasa.setVisible(true);
        this.menuTipoPizza.setVisible(false);
    }
    
    @FXML
    private void siguientePizza(ActionEvent event) {
        this.menuTipoPizza.setVisible(false);
        this.menuIngredientes.setVisible(true);
    }
    
    @FXML
    private void volverIngredientes(ActionEvent event) {
        this.menuTipoPizza.setVisible(true);
        this.menuIngredientes.setVisible(false);
    }
    
    private void elegirMasa(String tipo) {
        this.resumenTotal.setText(String.valueOf(p.setTipoMasa(tipo)));
        this.resumenTipoMasa.setText("Tipo de masa: " + tipo);
    }
    
    private void elegirPizza(String tipo) {
        this.resumenTotal.setText(String.valueOf(p.setTipoPizza(tipo)));
        this.resumenTipoPizza.setText("Tipo de pizza: " + tipo);
    }
    
    private void elegirIngredientes(Object tipo) {
        if (tipo == this.ingredienteCebolla) {
            this.ingredienteCebollaImagen.setStyle("-fx-border-color: #7990e7;"
                    + "-fx-border-width: 2px; ");
        }
        
    }
    
}

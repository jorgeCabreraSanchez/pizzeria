/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.awt.Image;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
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
    private Button siguiente;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p = new Pizza();
        this.pizzaBasica.setUserData("Basica");
        this.pizzaCuatroQuesos.setUserData("CuatroQuesos");
        this.pizzaBarbacoa.setUserData("Barbacoa");
        this.pizzaMexicana.setUserData("Mexicana");
        this.buttonFina.setUserData("fina");
        this.buttonGorda.setUserData("gruesa");
        pre = new Precios();
    }

    @FXML
    private void masa(ActionEvent event) {
        String tipo = this.tipoMasas.getSelectedToggle().getUserData().toString();
        elegirMasa(tipo);
    }

    @FXML
    private void siguienteMasa(ActionEvent event) {
        this.menuMasa.setVisible(false);
        this.menuTipoPizza.setVisible(true);
    }

    @FXML
    private void tipoPizza(ActionEvent event) {
        String tipo = this.tipoPizzas.getSelectedToggle().getUserData().toString();
        elegirPizza(tipo);
    }

    private void elegirMasa(String tipo) {
        if (p.existeTipoMasa()) {
            double total = Double.parseDouble(this.resumenTotal.getText());
            double quitar = p.getTipoMasa();
            this.resumenTotal.setText(String.valueOf(total - quitar));
            sumarMasa(tipo);
        } else {
            sumarMasa(tipo);
        }
    }

    private void sumarMasa(String tipo) {
        double total = Double.parseDouble(this.resumenTotal.getText());
        System.out.println(tipo);
        Double dinero = p.setTipoMasa(tipo);
        this.resumenTotal.setText(String.valueOf(total + dinero));
        this.resumenTipoMasa.setText("Tipo de masa: " + tipo);
    }

    private void elegirPizza(String tipo) {
        if (p.existeTipoPizza()) {

        } else {
            double precio = p.setTipoPizza(tipo);
        }

    }
    
    private void sumarPizza(){
        
    }

}

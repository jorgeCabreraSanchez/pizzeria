/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.awt.Image;
import java.math.RoundingMode;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
    private CheckBox sinIngredientes;
    @FXML
    private Button volverIngredientes;
    @FXML
    private CheckBox ingredienteJamon;
    @FXML
    private CheckBox ingredienteQueso;
    @FXML
    private CheckBox ingredienteOlivas;
    @FXML
    private CheckBox ingredienteTomate;
    @FXML
    private CheckBox ingredienteCebolla;
    @FXML
    private Label resumenIngredientes;
    @FXML
    private Label resumenTamaño;
    @FXML
    private AnchorPane menuTamaño;


    @FXML
    private Button volverTamaño;
    @FXML
    private Button Terminar;
    @FXML
    private Button siguienteIngredientes;
    @FXML
    private ToggleButton tamanoMediana;
    @FXML
    private ToggleButton tamanoFamiliar;
    @FXML
    private ToggleButton tamanoPequeña;
    @FXML
    private ToggleGroup tamano;

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
        this.tamanoPequeña.setUserData("pequeña");
        this.tamanoMediana.setUserData("mediana");
        this.tamanoFamiliar.setUserData("familiar");
    }

    @FXML
    private void masa(ActionEvent event) {
        if (this.tipoMasas.getSelectedToggle() != null) {
            elegirMasa(this.tipoMasas.getSelectedToggle().getUserData().toString());
        }

    }

    @FXML
    private void tipoPizza(ActionEvent event) {
        if (this.tipoPizzas.getSelectedToggle() != null) {
            elegirPizza(this.tipoPizzas.getSelectedToggle().getUserData().toString());
        }
    }

    @FXML
    private void ingredientes(ActionEvent event) {
        elegirIngredientes(event.getSource());
    }
    
        @FXML
    private void ponerTamaño(ActionEvent event) {
       if(this.tamano.getSelectedToggle() != null){
           elegirTamaño(this.tamano.getSelectedToggle().getUserData().toString());
       }
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
        this.menuIngredientes.setVisible(false);
        this.menuTipoPizza.setVisible(true);
    }
    
    @FXML
    private void siguienteIngredientes(ActionEvent event) {
        this.menuIngredientes.setVisible(false);
        this.menuTamaño.setVisible(true);
    }

    @FXML
    private void volverTamaño(ActionEvent event) {
        this.menuTamaño.setVisible(false);
        this.menuIngredientes.setVisible(true);
    }
    
    @FXML
    private void terminar(ActionEvent event) {
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
        if (this.sinIngredientes.isSelected()) {
            this.ingredienteCebolla.setSelected(false);
            this.ingredienteJamon.setSelected(false);
            this.ingredienteOlivas.setSelected(false);
            this.ingredienteQueso.setSelected(false);
            this.ingredienteTomate.setSelected(false);
            this.resumenIngredientes.setText("Ingredientes Extra: Ninguno");
            this.resumenTotal.setText(String.valueOf(p.setIngredientesExtraNinguno()));
        } else if (this.resumenIngredientes.getText().isEmpty() || this.resumenIngredientes.getText().equalsIgnoreCase("Ingredientes Extra: Ninguno")) {
            this.resumenIngredientes.setText("Ingredientes Extra: ");
        }
        if (!this.sinIngredientes.isSelected()) {

            if (tipo == this.ingredienteCebolla && this.ingredienteCebolla.isSelected()) {
                this.resumenTotal.setText(String.valueOf(p.setIngredientesExtra(this.ingredienteCebolla.getUserData().toString())));
                ponerIngrediente("cebolla");

            } else if (tipo == this.ingredienteCebolla) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteCebolla.getUserData().toString()));
                borrarIngrediente("cebolla");
            }

            if (tipo == this.ingredienteJamon && this.ingredienteJamon.isSelected()) {
                this.resumenTotal.setText(p.setIngredientesExtra(this.ingredienteJamon.getUserData().toString()));
                ponerIngrediente("jamon");

            } else if (tipo == this.ingredienteJamon) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteJamon.getUserData().toString()));
                borrarIngrediente("jamon");
            }

            if (tipo == this.ingredienteQueso && this.ingredienteQueso.isSelected()) {
                this.resumenTotal.setText(String.valueOf(p.setIngredientesExtra(this.ingredienteQueso.getUserData().toString())));
                ponerIngrediente("queso");

            } else if (tipo == this.ingredienteQueso) {
                this.resumenTotal.setText(String.valueOf(p.removeIngredienteExtra(this.ingredienteQueso.getUserData().toString())));
                borrarIngrediente("queso");
            }

            if (tipo == this.ingredienteTomate && this.ingredienteTomate.isSelected()) {
                this.resumenTotal.setText(String.valueOf(p.setIngredientesExtra(this.ingredienteTomate.getUserData().toString())));
                ponerIngrediente("tomate");

            } else if (tipo == this.ingredienteTomate) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteTomate.getUserData().toString()));
                borrarIngrediente("tomate");
            }

            if (tipo == this.ingredienteOlivas && this.ingredienteOlivas.isSelected()) {
                this.resumenTotal.setText(p.setIngredientesExtra(this.ingredienteOlivas.getUserData().toString()));
                ponerIngrediente("olivas");

            } else if (tipo == this.ingredienteOlivas) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteOlivas.getUserData().toString()));
                borrarIngrediente("olivas");
            }
        }
    }

    private void borrarIngrediente(String palabra) {
        
    }

    private void ponerIngrediente(String poner) {
        this.resumenIngredientes.getText().equalsIgnoreCase("Ingredientes Extra: ");
            
    }

    private String primeraMayuscula(String palabra) {
        String palabra1 = palabra.substring(0, 1);
        palabra = palabra1.toUpperCase() + palabra.substring(1);
        return palabra;
    }
    
    private void elegirTamaño(String tipo){
        this.resumenTotal.setText(p.setTamaño(tipo));
        this.resumenTamaño.setText("Tamaño de la pizza: " + tipo);
    }

    /* Crear alerta al pasar de ingredientes Extra diciendo que si todos estan en getSelected(false)
    tiene que poner alguno */



    
}

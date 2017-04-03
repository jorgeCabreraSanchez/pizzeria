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
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author daw
 */
public class FXMLDocumentController implements Initializable {

    Pizza p;
    Pedido ped;
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
    private Button siguienteIngredientes;
    @FXML
    private ToggleButton tamanoMediana;
    @FXML
    private ToggleButton tamanoFamiliar;
    @FXML
    private ToggleGroup tamano;
    @FXML
    private ToggleButton tamanoPequena;
    @FXML
    private Button Hecha;
    @FXML
    private Button nuevaPizza;
    @FXML
    private Button terminarPedido;
    @FXML
    private AnchorPane menuOtraPizza;
    @FXML
    private AnchorPane pedidoFinalPedidos;
    @FXML
    private GridPane pedidos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ped = new Pedido();
        p = new Pizza();
        p.setNumero(1);
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
        this.tamanoPequena.setUserData("pequeña");
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
        if (this.tamano.getSelectedToggle() != null) {
            elegirTamaño(this.tamano.getSelectedToggle().getUserData().toString());
        }
    }

    @FXML
    private void pizzaHecha(ActionEvent event) {
        if (p.faltaAlgo()) {
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("PizzeriaJorge");
            alerta.setHeaderText("No se pudo crear la Pizza");
            alerta.setContentText("Faltan datos, no ha seleccionado alguna opcion en todos los menus");
            alerta.showAndWait();
        } else {
            this.menuTamaño.setVisible(false);
            this.resultado.setVisible(false);
            this.resumenIngredientes.setText("");
            this.resumenNumeroPizza.setText("");
            this.resumenTamaño.setText("");
            this.resumenTipoMasa.setText("");
            this.resumenTipoPizza.setText("");
            this.resumenTotal.setText("0");
            ped.añadirPedido(p);
            renovarPedidos();
            this.menuOtraPizza.setVisible(true);

        }
    }
    
     @FXML
    private void nuevaPizza(ActionEvent event) {
        this.menuOtraPizza.setVisible(false);
        this.menuMasa.setVisible(true);
        this.resultado.setVisible(true);
        int numero = p.getNumero()+1;
        p = new Pizza();
        p.setNumero(numero);
        
        
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

    private void elegirMasa(String tipo) {
        this.resumenTotal.setText(p.setTipoMasa(tipo));
        this.resumenTipoMasa.setText("Tipo de masa: " + tipo);
    }

    private void elegirPizza(String tipo) {
        this.resumenTotal.setText(p.setTipoPizza(tipo));
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
            this.resumenTotal.setText(p.setIngredientesExtraNinguno());

        } else {

            if (tipo == this.ingredienteCebolla && this.ingredienteCebolla.isSelected()) {
                this.resumenTotal.setText(p.setIngredientesExtra(this.ingredienteCebolla.getUserData().toString()));
            } else if (tipo == this.ingredienteCebolla) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteCebolla.getUserData().toString()));
            }

            if (tipo == this.ingredienteJamon && this.ingredienteJamon.isSelected()) {
                this.resumenTotal.setText(p.setIngredientesExtra(this.ingredienteJamon.getUserData().toString()));
            } else if (tipo == this.ingredienteJamon) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteJamon.getUserData().toString()));
            }

            if (tipo == this.ingredienteQueso && this.ingredienteQueso.isSelected()) {
                this.resumenTotal.setText(p.setIngredientesExtra(this.ingredienteQueso.getUserData().toString()));
            } else if (tipo == this.ingredienteQueso) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteQueso.getUserData().toString()));
            }

            if (tipo == this.ingredienteTomate && this.ingredienteTomate.isSelected()) {
                this.resumenTotal.setText(p.setIngredientesExtra(this.ingredienteTomate.getUserData().toString()));
            } else if (tipo == this.ingredienteTomate) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteTomate.getUserData().toString()));
            }

            if (tipo == this.ingredienteOlivas && this.ingredienteOlivas.isSelected()) {
                this.resumenTotal.setText(p.setIngredientesExtra(this.ingredienteOlivas.getUserData().toString()));
            } else if (tipo == this.ingredienteOlivas) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.ingredienteOlivas.getUserData().toString()));
            }

            if (tipo == this.sinIngredientes) {
                this.resumenTotal.setText(p.removeIngredienteExtra(this.sinIngredientes.getUserData().toString()));
            }

            this.resumenIngredientes.setText("Ingredientes Extra: " + p.ingredientes());
        }
    }

    private String primeraMayuscula(String palabra) {
        String palabra1 = palabra.substring(0, 1);
        palabra = palabra1.toUpperCase() + palabra.substring(1);
        return palabra;
    }

    private void elegirTamaño(String tipo) {
        this.resumenTotal.setText(p.setTamaño(tipo));
        this.resumenTamaño.setText("Tamaño de la pizza: " + tipo);
    }

    private void renovarPedidos() {
        List<Pizza> pizzas = ped.getPizzas();
        
        for (Pizza pizza : pizzas) {
            String descripcion = "";
            TitledPane panel = new TitledPane();
            panel.setText("Pizza" + pizza.getNumero());
            descripcion += "Tipo de masa: " + pizza.getTipoMasa() + "\n" +
                    "Tipo de pizza: " + pizza.getTipoPizza() + "\n" +
                    "Ingredientes Extra: " + pizza.ingredientes() + "\n" +
                    "Tamaño de la pizza: " + pizza.getTamaño() + "\n" +
                    "Total: " + pizza.getTotal() + "€";
            
            TextArea textArea = new TextArea(descripcion);
            panel.setContent(textArea);
            this.pedidos.add(panel, 0, pizza.getNumero()-1);
            
        }
    }

   

  
}

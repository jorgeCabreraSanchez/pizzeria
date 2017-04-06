/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.awt.Image;
import java.io.File;
import java.math.RoundingMode;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import static pizzeria.Precios.cargarPreciosDefault;

/**
 *
 * @author daw
 */
public class FXMLDocumentController implements Initializable {

    Pizza p;
    Pedido ped;
    GridPane pedidos2 = new GridPane();

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
    private Button inicio;
    @FXML
    private Button buttonCancelarPizza;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
        this.tamanoPequena.setUserData("pequenia");
        this.tamanoMediana.setUserData("mediana");
        this.tamanoFamiliar.setUserData("familiar");
        Precios.cargaPreciosTuyos();
        this.pedidoFinalPedidos.getChildren().add(pedidos2);

    }

    @FXML
    private void entrar(ActionEvent event) {
        ped = new Pedido();
        p = new Pizza();
        p.setNumero(p.numeroPizzas());

        this.inicio.setVisible(false);
        this.menuMasa.setVisible(true);
        this.resultado.setVisible(true);

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
            p.setInfoPizza();
            ped.añadirPedido(p);
            renovarPedidos();
            this.menuOtraPizza.setVisible(true);
            limpieza();
        }
    }

    @FXML
    private void nuevaPizza(ActionEvent event) {
        this.menuOtraPizza.setVisible(false);
        this.menuMasa.setVisible(true);
        this.resultado.setVisible(true);
        int numero = p.numeroPizzas();
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

        @FXML
    private void terminar(ActionEvent event) {
        boolean variable = true;
        Alert ventanita = new Alert(AlertType.INFORMATION);
        ventanita.setTitle("Precios");
        ventanita.setHeaderText("Eliga el archivo donde quiere generar el ticket");
        ButtonType abrir = new ButtonType("Buscar archivo");
        ButtonType defecto = new ButtonType("Por defecto");
        ButtonType cancelar = new ButtonType("Cancelar");
        ventanita.getButtonTypes().setAll(abrir, defecto, cancelar);
        Optional<ButtonType> elegido = ventanita.showAndWait();

        if (elegido.get() == abrir) {
            DirectoryChooser elegir = new DirectoryChooser();
            Path archivo = Paths.get(new File("").getAbsolutePath() + "\\tickets");
            elegir.setInitialDirectory(archivo.toFile());

            variable = ped.generarTicket1(elegir.showDialog(null).toPath());
        } else if (elegido.get() == cancelar) {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Ticket");
            alerta.setHeaderText("No se generará ticket");
            alerta.setContentText("No se generará ticket debido a que usted no ha puesto \n"
                    + "un archivo destino");
            variable = false;
        } else if (elegido.get() == defecto) {
            variable = ped.generarTicket1();
        }

        if (variable) {
            this.menuOtraPizza.setVisible(false);
            this.inicio.setVisible(true);

        }

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
        pedidos2.getChildren().clear();
        List<Pizza> pizzas = ped.getPizzas();
        int x = 0;
        int y = 0;
        int numero = 0;

        for (Pizza pizza : pizzas) {
            pedidos2.setPrefSize(this.pedidoFinalPedidos.getWidth(), this.pedidoFinalPedidos.getHeight());
            String descripcion = "";
            TitledPane panel = new TitledPane();
            panel.setText("Pizza" + pizza.getNumero());
            descripcion += pizza.getInfoPizza();

            TextArea textArea = new TextArea(descripcion);
            textArea.setEditable(false);
            panel.setContent(textArea);
            if (pizzas.size() > 6) {
                panel.setExpanded(false);
            }

            if (numero == 0) {
                pedidos2.add(panel, 0, 0);
            } else if (x == 0) {
                x += 1;
                pedidos2.add(panel, x, y);
            } else {
                x = 0;
                y += 1;
                pedidos2.add(panel, x, y);
            }
            numero++;
        }

    }

    private void limpieza() {
        this.resumenIngredientes.setText("");
        this.resumenNumeroPizza.setText("");
        this.resumenTamaño.setText("");
        this.resumenTipoMasa.setText("");
        this.resumenTipoPizza.setText("");
        this.resumenTotal.setText("0");
        if (this.tipoMasas.getSelectedToggle() != null) {
            this.tipoMasas.getSelectedToggle().setSelected(false);
        }
        if (this.tipoPizzas.getSelectedToggle() != null) {
            this.tipoPizzas.getSelectedToggle().setSelected(false);
        }
        this.ingredienteCebolla.setSelected(false);
        this.ingredienteJamon.setSelected(false);
        this.ingredienteOlivas.setSelected(false);
        this.ingredienteQueso.setSelected(false);
        this.ingredienteTomate.setSelected(false);
        this.sinIngredientes.setSelected(false);
        if (this.tamano.getSelectedToggle() != null) {
            this.tamano.getSelectedToggle().setSelected(false);
        }
    }

    



}

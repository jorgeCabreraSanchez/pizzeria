/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author jorge
 */
public class Pedido {

    private List<Pizza> pizzas = new ArrayList<>();
    private static int totalPedidos = 0;

    public Pedido() {

    }

    public void generarTicket() {
        Path archivo = Paths.get(new File("").getAbsolutePath().toString() + "\\tickets");
        try {
            BufferedWriter bw = Files.newBufferedWriter(archivo, StandardOpenOption.CREATE_NEW);
            
        } catch (IOException ex) {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Generar Tickets");
            alerta.setHeaderText("Error generando el ticket");
            alerta.setContentText("El numero de este ticket ya ha sido generado anteriormente. \n"
                    + "Borre el archivo que contenga este nombre y lo podra borrar");
        }

    }

    public void añadirPedido(Pizza p) {
        this.pizzas.add(p);
    }

    public String datosPizzas() {
        String devolver = "";
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza p = pizzas.get(i);
            devolver += p.getInfoPizza() + "\n\n";
        }
        return devolver;
    }

    public List<Pizza> getPizzas() {
        return this.pizzas;
    }
}

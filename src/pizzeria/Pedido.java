/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.awt.Window;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jorge
 */
public class Pedido {

    private List<Pizza> pizzas = new ArrayList<>();
    private static int numPedidos = 1;
    private int numPizzas = 0;
    private double totalPedido = 0.0;
    
    public Pedido() {         
        
    }

    public boolean generarTicket1(Path directorio) {
        return generarTicket(directorio);
    }

    public double totalPedido(){
        return this.totalPedido;
    }
    public void sumarPizza(double dinero){
        this.totalPedido += dinero;
    }
    
    public int numeroPizzas(){
        this.numPizzas++;
        return this.numPizzas;
    }
    
    public boolean generarTicket1() {
        return generarTicket(Paths.get(new File("").getAbsolutePath() + "\\tickets"));
    }

    private boolean generarTicket(Path directorio) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("'Ticket Nº'" + numPedidos + "  'fecha' dd-mm-yyyy 'hora' HH-mm-ss");
        LocalDateTime nombre = LocalDateTime.now();

        Path archivo = Paths.get(directorio + "\\" + formato.format(nombre) + ".txt");
        try (BufferedWriter bw = Files.newBufferedWriter(archivo, StandardOpenOption.CREATE_NEW)) {

            for (int i = 0; i < pizzas.size(); i++) {
                Pizza p = pizzas.get(i);
                bw.append("Pizza" + p.getNumero());
                bw.newLine();
                StringTokenizer t = new StringTokenizer(p.infoTicket(), "\n");
                while (t.hasMoreTokens()) {
                    bw.append(t.nextToken());
                    bw.newLine();
                }
                bw.newLine();
                
            }
            
            bw.newLine();
            DecimalFormat formate = new DecimalFormat("00.00€");
            bw.append("Total Pedido:" + formate.format(this.totalPedido));
            bw.newLine();
            
            
            Alert alerta = new Alert(AlertType.INFORMATION);
            alerta.setTitle("Generar Tickets");
            alerta.setHeaderText("El ticket se ha genarado correctamente");
            alerta.setContentText("Ubicación del ticket: " + archivo.toAbsolutePath().toString());
            alerta.showAndWait();
            numPedidos++;
            return true;

        } catch (IOException ex) {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setTitle("Generar Tickets");
            alerta.setHeaderText("Error generando el ticket");
            alerta.setContentText("Este ticket ya fue generado anteriormente");
            alerta.showAndWait();
            return false;
        }

    }

    public void añadirPedido(Pizza p) {
        this.pizzas.add(p);
    }

    private String datosPizzas() { // MODIFICAR hacer que sirve para modificar una pizza, que se pongan los datos
        String devolver = "";
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza p = pizzas.get(i);
            devolver += p.getInfoPizza();
        }
        return devolver;
    }

    public List<Pizza> getPizzas() {
        return this.pizzas;
    }
}

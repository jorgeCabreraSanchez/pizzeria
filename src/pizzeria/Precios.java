/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author daw
 */
public class Precios {

    public static Map<String, Double> precioTipoMasa = new HashMap<>();
    static public Map<String, Double> tiposPizza = new HashMap<>();
    static public Map<String, Double> ingredientesExtra = new HashMap<>();
    static public Map<String, Double> tamaño = new HashMap<>();

    public static boolean cargarPreciosDefault() {
        precioTipoMasa.put("fina", 9.00);
        precioTipoMasa.put("gruesa",9.50);
        tiposPizza.put("Basica",3.00);
        tiposPizza.put("CuatroQuesos",5.00);
        tiposPizza.put("Barbacoa",7.00);
        tiposPizza.put("Mexicana",8.50);
        ingredientesExtra.put("sinIngredientes",0.00);
        ingredientesExtra.put("jamon",0.50);
        ingredientesExtra.put("queso",0.75);
        ingredientesExtra.put("tomate",1.50);
        ingredientesExtra.put("cebolla",2.50);
        ingredientesExtra.put("olivas",1.00);
        tamaño.put("pequenia",1.00);
        tamaño.put("mediana",1.15);
        tamaño.put("familiar",1.30);
        return true;
    }

    public static boolean cargaPreciosTuyos() {
        try (Stream<String> datos = Files.lines(Paths.get("CartaPrecios.txt"))) {
            Iterator<String> it = datos.iterator();
            int hacer = 0;
            double dinero = 0;
            while (it.hasNext()) {
                String linea = it.next();
                if (!linea.equals("#")) {
                    if (linea.equalsIgnoreCase("Masas")) {
                        hacer = 1;
                    } else if (linea.equalsIgnoreCase("TipoPizzas")) {
                        hacer = 2;
                    } else if (linea.equalsIgnoreCase("IngredientesExtra")) {
                        hacer = 3;
                    } else if (linea.equalsIgnoreCase("Tamanio")) {
                        hacer = 4;
                    } else {
                        String frase[] = linea.split(":");
                        String ing = frase[0];
                        dinero = Double.parseDouble(frase[1]);
                        switch (hacer) {
                            case 1:
                                precioTipoMasa.put(ing, dinero);
                                break;

                            case 2:
                                tiposPizza.put(ing, dinero);
                                break;
                            case 3:
                                ingredientesExtra.put(ing, dinero);
                                break;
                            case 4:
                                tamaño.put(ing, dinero);
                                break;
                        }
                    }
                }

            }
        } catch (Exception ex) {
            Alert alerta = new Alert(AlertType.WARNING);
            alerta.setHeaderText("Hemos tenido un problema cargando los precios y hemos cargado los precios por defecto");
            alerta.showAndWait();
            cargarPreciosDefault();
            
        }
        return true;
    }

    public Precios() {
    }

}

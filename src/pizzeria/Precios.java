/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author daw
 */
public class Precios {

    public static  Map<String, Double> precioTipoMasa = new HashMap<>();
    static public Map<String, Double> tiposPizza = new HashMap<>();
    static public Map<String, Double> ingredientesExtra = new HashMap<>();
    static public Map<String, Double> tamaño = new HashMap<>();

    public Precios() {
        precioTipoMasa.put("fina", 9.00);
        precioTipoMasa.put("gruesa", 9.50);
        tiposPizza.put("Basica", 3.00);
        tiposPizza.put("CuatroQuesos", 5.00);
        tiposPizza.put("Barbacoa", 7.00);
        tiposPizza.put("Mexicana", 8.50);
        ingredientesExtra.put("sinIngredientes", 0.00);
        ingredientesExtra.put("jamon", 0.50);
        ingredientesExtra.put("queso", 0.75);
        ingredientesExtra.put("tomate", 1.50);
        ingredientesExtra.put("cebolla", 2.50);
        ingredientesExtra.put("olivas", 1.00);
        tamaño.put("pequeña", 1.00);
        tamaño.put("mediana", 1.15);
        tamaño.put("familiar", 1.30);
    }

}

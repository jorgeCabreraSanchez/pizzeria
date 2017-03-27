/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pizzeria;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jorge
 */
public class Pedido {

    private List<Pizza> pizzas = new ArrayList<>();

    public Pedido(){
        
    }
    
    public void añadirPedido(Pizza p){
        this.pizzas.add(p);
    }
    
    public String datosPizzas(){
        String devolver = "";
        for (int i = 0; i < pizzas.size(); i++) {
            Pizza p = pizzas.get(i);
            
        }
        return devolver;
    }
    
    public List<Pizza> getPizzas(){
        return this.pizzas;
    }
}

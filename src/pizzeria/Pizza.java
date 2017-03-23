package pizzeria;

import java.util.HashMap;
import java.util.Map;

public class Pizza {

    private double Total;

    private Map<String, Double> precioTipoMasa;
    private String tipoMasa = "";
    private Map<String, Double> tipoPizza;

    private Map<String,Double> ingredientesExtra;
    
    private Map<String,Double> tamaño;

   
    public Pizza() {
        this.precioTipoMasa = new HashMap<>();
        this.precioTipoMasa.put("fina", 9.00);
        this.precioTipoMasa.put("gruesa", 9.50);
        this.tipoPizza = new HashMap<>();
        this.tipoPizza.put("Basica", 3.00);
        this.tipoPizza.put("CuatroQuesos", 5.00);
        this.tipoPizza.put("Barbacoa", 7.00);
        this.tipoPizza.put("Mexicana", 8.50);
        this.ingredientesExtra = new HashMap<>();
        this.ingredientesExtra.put("sinIngredientes", 0.00);
        this.ingredientesExtra.put("jamon", 0.50);
        this.ingredientesExtra.put("queso", 0.75);
        this.ingredientesExtra.put("tomate", 1.50);
        this.ingredientesExtra.put("cebolla", 2.50);
        this.ingredientesExtra.put("olivas", 1.00);
        this.tamaño = new HashMap<>();
        this.tamaño.put("pequeña", 1.00);
        this.tamaño.put("mediana", 1.15);
        this.tamaño.put("familiar", 1.30);
    }

    public double setTipoMasa(String tipo){
        this.tipoMasa=tipo;
        return this.precioTipoMasa.get(tipo);
    }
    public double getTipoMasa(){  
        return this.precioTipoMasa.get(this.tipoMasa);
    }
    
    
    public boolean existeTipoMasa(){
        if(this.tipoMasa.isEmpty()){
            return false;
        } else {
            return true;
        }
    }
    
    public void setTotal(double numero){
        this.Total=numero;
    }
    
    public double getTotal() {        
        return Total;
    }

    

    
    
    
    
}

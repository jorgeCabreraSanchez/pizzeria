package pizzeria;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pizza {

    private double Total = 0.0;
    private String tipoMasa = "";
    private String tipoPizza = "";
    private Set<String> ingredientes = new HashSet<>();
    private String tamaño = "";
    
    public Pizza() {

    }

    public Double setTipoMasa(String tipo) {
        if(this.tipoMasa.isEmpty()){           
            return sumarTipoMasa(tipo);
        } else {
            this.Total-=Precios.precioTipoMasa.get(this.tipoMasa);
            return sumarTipoMasa(tipo);
        }
    }

    private Double sumarTipoMasa(String tipo){
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        this.tipoMasa=tipo;
        this.Total+=Precios.precioTipoMasa.get(tipo);
        return Double.parseDouble(formato.format(this.Total));
    }

    public double setTipoPizza(String tipo) {
        if (this.tipoPizza.isEmpty()) {
            return sumarTipoPizza(tipo);
        } else {
            this.Total -= Precios.tiposPizza.get(this.tipoPizza);
            return sumarTipoPizza(tipo);
        }
    }

    private Double sumarTipoPizza(String tipo) {        
        this.tipoPizza = tipo;
        this.Total += Precios.tiposPizza.get(tipo);
        return this.Total;
    }
    
    public Double setIngredientesExtraNinguno(){
        for (String ingrediente : ingredientes) {
            this.Total -= Precios.ingredientesExtra.get(ingrediente);            
        }       
        this.ingredientes.clear();
        return this.Total;
    }
    
    public double setIngredientesExtra(String tipo){
        this.ingredientes.add(tipo);
        this.Total += Precios.ingredientesExtra.get(tipo);
        return this.Total;
    }
    
    public Double removeIngredienteExtra(String tipo){
        this.ingredientes.remove(tipo);
        this.Total -= Precios.ingredientesExtra.get(tipo);
        return this.Total;
    }
    
    public Double setTamaño(String tipo){
        if(this.tamaño.isEmpty()){            
            return sumarTamaño(tipo);
        } else {
            this.Total /= Precios.tamaño.get(this.tamaño);
            return sumarTamaño(tipo);
        }        
    }
    
    private Double sumarTamaño(String tipo){
        this.tamaño = tipo;
        this.Total *= Precios.tamaño.get(tipo);
        return this.Total;
    }

    public void setTotal(double numero) {
        this.Total = numero;
    }

    public double getTotal() {
        return Total;
    }

}

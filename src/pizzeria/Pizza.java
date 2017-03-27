package pizzeria;

import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
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

    public String setTipoMasa(String tipo) {
        if (this.tipoMasa.isEmpty()) {
            return sumarTipoMasa(tipo);
        } else {
            this.Total -= Precios.precioTipoMasa.get(this.tipoMasa);
            return sumarTipoMasa(tipo);
        }
    }

    private String sumarTipoMasa(String tipo) {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        this.tipoMasa = tipo;
        this.Total += Precios.precioTipoMasa.get(tipo);
        return formato.format(this.Total);
    }

    public String setTipoPizza(String tipo) {
        if (this.tipoPizza.isEmpty()) {
            return sumarTipoPizza(tipo);
        } else {
            this.Total -= Precios.tiposPizza.get(this.tipoPizza);
            return sumarTipoPizza(tipo);
        }
    }

    private String sumarTipoPizza(String tipo) {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        this.tipoPizza = tipo;
        this.Total += Precios.tiposPizza.get(tipo);
        return formato.format(this.Total);
    }

    public String setIngredientesExtraNinguno() {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        for (String ingrediente : ingredientes) {
            this.Total -= Precios.ingredientesExtra.get(ingrediente);
        }
        this.ingredientes.clear();
        return formato.format(this.Total);
    }

    public String setIngredientesExtra(String tipo) {
        NumberFormat formato = NumberFormat.getInstance();
        
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        this.ingredientes.add(tipo);
        this.Total += Precios.ingredientesExtra.get(tipo);
        return formato.format(this.Total);
    }

    public String removeIngredienteExtra(String tipo) {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        this.ingredientes.remove(tipo);
        this.Total -= Precios.ingredientesExtra.get(tipo);
        return formato.format(this.Total);
    }

    public String setTamaño(String tipo) {
        if (this.tamaño.isEmpty()) {
            return sumarTamaño(tipo);
        } else {
            this.Total /= Precios.tamaño.get(this.tamaño);
            return sumarTamaño(tipo);
        }
    }

    private String sumarTamaño(String tipo) {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        this.tamaño = tipo;
        this.Total *= Precios.tamaño.get(tipo);
        return formato.format(this.Total);
    }

    public void setTotal(double numero) {
        this.Total = numero;
    }

    public double getTotal() {
        return Total;
    }

}

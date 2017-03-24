package pizzeria;

import java.util.HashMap;
import java.util.Map;

public class Pizza {

    private double Total;

    private String tipoMasa = "";
    private String tipoPizza = "";

    public Pizza() {

    }

    public Double setTipoMasa(String tipo) {
        this.tipoMasa = tipo;
        return Precios.precioTipoMasa.get(tipo);
    }

    public double getTipoMasa() {
        return Precios.precioTipoMasa.get(this.tipoMasa);
    }

    public double setTipoPizza(String tipo) {
        this.tipoPizza = tipo;
        return Precios.tiposPizza.get(tipo);
    }

//    public double getTipoPizza() {
//        
//    }

    public boolean existeTipoMasa() {
        if (this.tipoMasa.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean existeTipoPizza() {
        if (this.tipoPizza.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public void setTotal(double numero) {
        this.Total = numero;
    }

    public double getTotal() {
        return Total;
    }

}

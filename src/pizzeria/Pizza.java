package pizzeria;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Pizza {

    private double Total = 0.0;
    private String tipoMasa = "";
    private String tipoPizza = "";
    private Set<String> ingredientes = new HashSet<>();
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
        this.tipoMasa=tipo;
        this.Total+=Precios.precioTipoMasa.get(tipo);
        return this.Total;
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

    public void setTotal(double numero) {
        this.Total = numero;
    }

    public double getTotal() {
        return Total;
    }

}

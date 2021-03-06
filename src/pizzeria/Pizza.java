package pizzeria;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class Pizza {

    private int numero = 0;
    private double Total = 0.0;
    private String tipoMasa = "";
    private String tipoPizza = "";
    private Set<String> ingredientes = new HashSet<>();
    private String tamaño = "";
    private String infoPizza = "";
    private Pedido pedido;

    public Pizza(Pedido ped) {
        this.numero = ped.numeroPizzas();
        this.pedido = ped;
    }

    public String getTotalPedido() {
        DecimalFormat formato = new DecimalFormat("00.00€");
        return formato.format(pedido.totalPedido());
    }

    public void setInfoPizza() {
        Double porcentaje = (Precios.tamaño.get(tamaño) - 1) * 100;
        this.infoPizza += String.format("Tipo de masa              %-12s \n"
                + "Tipo de pizza:              %-12s  \n"
                + "Ingredientes Extra:      %-12s \n"
                + "Tamaño de la pizza:    %-12s  \n"
                + "Total: %32s€", tipoMasa, tipoPizza, ingredientes(), tamaño, getTotal());
        DecimalFormat formato = new DecimalFormat("00.00€");
        pedido.sumarPizza(this.Total);
    }

    public String getInfoPizza() {
        return this.infoPizza;
    }

    public String getTipoMasa() {
        return this.tipoMasa;
    }

    public String getTipoPizza() {
        return this.tipoPizza;
    }

    public String getTamaño() {
        return this.tamaño;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public boolean faltaAlgo() {
        if (this.tipoMasa.isEmpty() || this.tipoPizza.isEmpty()
                || this.ingredientes.isEmpty() || this.tamaño.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public String setTipoMasa(String tipo) {
        porcentajeQuitar();
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
        porcentajePoner();
        return formato.format(this.Total);
    }

    public String setTipoPizza(String tipo) {
        porcentajeQuitar();
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
        porcentajePoner();
        return formato.format(this.Total);
    }

    public String setIngredientesExtraNinguno() {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        porcentajeQuitar();
        for (String ingrediente : ingredientes) {
            this.Total -= Precios.ingredientesExtra.get(ingrediente);
        }
        this.ingredientes.clear();
        this.ingredientes.add("sinIngredientes");
        porcentajePoner();
        return formato.format(this.Total);
    }

    public String setIngredientesExtra(String tipo) {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        porcentajeQuitar();
        if (this.ingredientes.contains("sinIngredientes")) {
            this.ingredientes.clear();
        }
        this.ingredientes.add(tipo);
        this.Total += Precios.ingredientesExtra.get(tipo);
        porcentajePoner();
        return formato.format(this.Total);
    }

    public String removeIngredienteExtra(String tipo) {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        porcentajeQuitar();
        this.ingredientes.remove(tipo);
        this.Total -= Precios.ingredientesExtra.get(tipo);
        porcentajePoner();
        return formato.format(this.Total);
    }

    public String ingredientes() {
        String devolver = "";
        for (String ingrediente : ingredientes) {
            devolver += (ingrediente + " ,");
        }
        if (!ingredientes.isEmpty()) {
            devolver = devolver.substring(0, devolver.length() - 1);
        }

        return devolver;
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

    private void porcentajeQuitar() {
        if (!this.tamaño.isEmpty()) {
            this.Total /= Precios.tamaño.get(this.tamaño);
        }
    }

    private void porcentajePoner() {
        if (!this.tamaño.isEmpty()) {
            this.Total *= Precios.tamaño.get(this.tamaño);
        }
    }

    public void setTotal(double numero) {
        this.Total = numero;
    }

    public String getTotal() {
        NumberFormat formato = NumberFormat.getInstance();
        formato.setMaximumFractionDigits(2);
        formato.setRoundingMode(RoundingMode.DOWN);
        return formato.format(this.Total);
    }

    public String ingredientesConDinero() {
        String extras = "";
        int contador = 0;
        for (String ingrediente : ingredientes) {
            if (contador == 0) {
                extras += String.format("%-12s %8.2f  \n", ingrediente, Precios.ingredientesExtra.get(ingrediente));
            } else {
                extras += String.format("                      %-12s %8.2f  \n", ingrediente, Precios.ingredientesExtra.get(ingrediente));
            }
            contador++;
        }
        return extras;
    }

    
    public String infoTicket() {
        String devolver = "";
        DecimalFormat formato = new DecimalFormat("0.00%");
        Double porcentaje = Precios.tamaño.get(tamaño) - 1;
        devolver += String.format("Tipo de masa          %-12s %8.2f \n"
                + "Tipo de pizza:        %-12s %8.2f \n"
                + "Ingredientes Extra:   %-12s"
                + "Tamaño de la pizza:   %-12s %9s \n"
                + "Total: %36s€", tipoMasa, Precios.precioTipoMasa.get(tipoMasa), tipoPizza, Precios.tiposPizza.get(tipoPizza), ingredientesConDinero(), tamaño, formato.format(porcentaje), getTotal());
        return devolver;
    }

}

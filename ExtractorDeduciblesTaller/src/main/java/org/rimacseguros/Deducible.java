package org.rimacseguros;

public class Deducible {
    private String deducible;
    private String copago;
    private String moneda;
    private String tipo;
    private String marca;
    private String taller;

    public String getDeducible() {
        return deducible;
    }

    public void setDeducible(String deducible) {
        this.deducible = deducible;
    }

    public String getCopago() {
        return copago;
    }

    public void setCopago(String copago) {
        this.copago = copago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getTaller() {
        return taller;
    }

    public void setTaller(String taller) {
        this.taller = taller;
    }

    @Override
    public String toString() {
        return "{" +
                "\"deducible\":" + getDeducible() + "," +
                "\"copago\":" + getCopago() + "," +
                "\"moneda\":\"" + getMoneda() + "\","+
                "\"tipo\":\"" + getTipo() + "\"," +
                "\"marca\":\"" + getMarca() + "\"," +
                "\"taller\":\"" + getTaller() + "\"" +
                "}";
    }
}

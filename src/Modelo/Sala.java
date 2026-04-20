package Modelo;

public class Sala {
    private int idSala;
    private String nombre;
    private String tematica;
    private int capacidad;
    private double precio;

    public Sala(int idSala, String nombre, String tematica, int capacidad, double precio) {
        this.idSala = idSala;
        this.nombre = nombre;
        this.tematica = tematica;
        this.capacidad = capacidad;
        this.precio = precio;
    }

    public Sala(int idSala) {
        this.idSala = idSala;
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}

package Modelo;

public class Reserva {
    private int idSala;
    private int idReserva;
    private String fechaReserva;
    private String horaReserva;
    private String nombreCliente;
    private int telefonoCliente;
    private int numJugadores;

    public Reserva(int idSala, int idReserva, String fechaReserva, String horaReserva, String nombreCliente, int telefonoCliente, int numJugadores) {
        this.idSala = idSala;
        this.idReserva = idReserva;
        this.fechaReserva = fechaReserva;
        this.horaReserva = horaReserva;
        this.nombreCliente = nombreCliente;
        this.telefonoCliente = telefonoCliente;
        this.numJugadores = numJugadores;
    }

    public int getIdSala() {return idSala;}

    public int setIdSala(int idSala) {
        return this.idSala = idSala;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public int getTelefonoCliente() {
        return telefonoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public String getFechaReserva() {
        return fechaReserva;
    }

    public String getHoraReserva() {
        return horaReserva;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }

    public void setTelefonoCliente(int telefonoCliente) {
        this.telefonoCliente = telefonoCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public void setHoraReserva(String horaReserva) {
        this.horaReserva = horaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    @Override
    public String toString() {
        return "Reserva: " +
                "ID Reserva: " + idReserva +
                ", Fecha: " + fechaReserva +
                ", Hora: " + horaReserva +
                ", Cliente: " + nombreCliente +
                ", Teléfono: " + telefonoCliente +
                ", Jugadores: " + numJugadores +
                ", Sala: " + getIdSala();
        }
    }



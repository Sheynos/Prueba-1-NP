package aerolinea.domain;


import java.util.Date;

    public class Pasaje {
        private int numeroVuelo;
        private Pasajero pasajero;
        private String destino;
        private Date fecha;
        private double valor;
        private int numeroAsiento;
        private boolean traeEquipaje;
    
    public Pasaje(){
    }

    public Pasaje(int numeroVuelo, Pasajero pasajero, String destino, Date fecha, double valor, int numeroAsiento, boolean traeEquipaje) {
        this.numeroVuelo = numeroVuelo;
        this.pasajero = pasajero;
        this.destino = destino;
        this.fecha = fecha;
        this.valor = valor;
        this.numeroAsiento = numeroAsiento;
        this.traeEquipaje = traeEquipaje;
    }

    public int getNumeroVuelo() {
        return numeroVuelo;
    }

    public void setNumeroVuelo(int numeroVuelo) {
        this.numeroVuelo = numeroVuelo;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNumeroAsiento() {
        return numeroAsiento;
    }

    public void setNumeroAsiento(int numeroAsiento) {
        this.numeroAsiento = numeroAsiento;
    }

    public boolean isTraeEquipaje() {
        return traeEquipaje;
    }

    public void setTraeEquipaje(boolean traeEquipaje) {
        this.traeEquipaje = traeEquipaje;
    }
       
       
         public boolean esPasajeVigente() {
             Date fechaHoy = new Date();
             return !fecha.before(fechaHoy);
    }

         public double calcularValorFinal() {
             if (traeEquipaje) {
                 return valor * 1.20; 
        }    else {
                 return valor;
        }
    }
}

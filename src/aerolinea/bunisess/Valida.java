package aerolinea.bunisess;

import java.util.Date;

public class Valida {

    public static boolean esFechaValida(Date fecha) {
        Date fechaHoy = new Date();
        return !fecha.before(fechaHoy);
    }

    public static boolean esValorValido(double valor) {
        return valor >= 10000;
       
    }

    public static boolean esNumeroAsientoValido(int numeroAsiento) {
        return numeroAsiento >= 1 && numeroAsiento <= 90;
    }

    }


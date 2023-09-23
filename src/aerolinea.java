import aerolinea.bunisess.Valida;
import aerolinea.domain.Pasaje;
import aerolinea.domain.Pasajero;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class aerolinea {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Pasajero pasajero = null;
        Pasaje pasaje = null;

        while (true) {
            mostrarMenu();

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    pasajero = crearPasajero(scanner);
                    break;

                case 2:
                    if (pasajero == null) {
                        System.out.println("Primero debe crear un pasajero.");
                    } else {
                        pasaje = crearPasaje(scanner, pasajero);
                        System.out.println("Pasaje creado de forma exitósa.");
                    }
                    break;

                case 3:
                    mostrarInformacionPasaje(pasaje);
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opcion no valida. Por favor, seleccione una opcion valida.");
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. crear un pasajero");
        System.out.println("2. crear un pasaje");
        System.out.println("3. Mostrar informacion del pasaje");
        System.out.println("4. Salir");
        System.out.print("Seleccione una de las opciones: ");
    }

    private static Pasajero crearPasajero(Scanner scanner) {
        System.out.print("Ingrese su rut: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese su nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese su apellido: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese su edad: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); 

        return new Pasajero(rut, nombre, apellido, edad);
    }

    private static Pasaje crearPasaje(Scanner scanner, Pasajero pasajero) {
        System.out.print("Ingrese el numero de su vuelo: ");
        int numeroVuelo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese su destino: ");
        String destino = scanner.nextLine();
        System.out.print("Ingrese la fecha de vuelo (dias/mes/año): ");
        String fechaStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha;
        try {
            fecha = sdf.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Fecha no valida.");
            return null;
        }

        double valor;
        while (true) {
            System.out.print("Ingrese el valor del pasaje ): ");
            valor = scanner.nextDouble();
            scanner.nextLine(); 
            if (Valida.esValorValido(valor)) {
                break;
            } else {
                System.out.println("El valor del pasaje no cumple con el requisito maximo(debe ser de 10,000 o superior).");
            }
        }

        int numeroAsiento;
        while (true) {
            System.out.print("Ingrese el numero de asiento : ");
            numeroAsiento = scanner.nextInt();
            scanner.nextLine(); 
            if (Valida.esNumeroAsientoValido(numeroAsiento)) {
                break;
            } else {
                System.out.println("El numero de asiento no cumple con los requisitos (entre 1 y 90).");
            }
        }

        System.out.print("¿trae equipaje? (Si­/No): ");
        boolean traeEquipaje = scanner.nextLine().equalsIgnoreCase("si­");

        return new Pasaje(numeroVuelo, pasajero, destino, fecha, valor, numeroAsiento, traeEquipaje);
    }

    private static void mostrarInformacionPasaje(Pasaje pasaje) {
        if (pasaje == null) {
            System.out.println("Primero debe crear un pasaje.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Informacion del pasaje:");
        System.out.println("Numero de vuelo: " + pasaje.getNumeroVuelo());
        System.out.println("Pasajero: " + pasaje.getPasajero().getNombre() + " " + pasaje.getPasajero().getApellido());
        System.out.println("Destino: " + pasaje.getDestino());
        System.out.println("Fecha de vuelo: " + dateFormat.format(pasaje.getFecha()));
        System.out.println("Valor del pasaje: " + pasaje.getValor());
        System.out.println("Numero de asiento: " + pasaje.getNumeroAsiento());
        System.out.println("¿trae equipaje?: " + (pasaje.isTraeEquipaje() ? "si­" : "No"));
        System.out.println("Es un pasaje vigente: " + (Valida.esFechaValida(pasaje.getFecha()) ? "si­" : "No"));
        System.out.println("Valor final del pasaje: " + pasaje.calcularValorFinal());
    }
}


// me ayude con la parte de mostrar las fechas con el formato dd/mm/yyyy(ParseException).
//con partes de scanner para interactuar con el teclado y usuario agregando el nextline para la continuacion. 
// y uno que otro error q se me presentaba en los whiles x ejemplo en el catch.
//mi ayuda fue con https://www.youtube.com/watch?v=zeuKnjQTqBE. https://chat.openai.com, https://campusvirtual.duoc.cl/ultra/courses/_521070_1/messages/edit/_1975594_1?courseId=_521070_1&offset=0&count=8.
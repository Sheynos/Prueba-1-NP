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
                        System.out.println("Pasaje creado con éxito.");
                    }
                    break;

                case 3:
                    mostrarInformacionPasaje(pasaje);
                    break;

                case 4:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Crear un pasajero");
        System.out.println("2. Crear un pasaje");
        System.out.println("3. Mostrar información del pasaje");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static Pasajero crearPasajero(Scanner scanner) {
        System.out.print("Ingrese el rut del pasajero: ");
        String rut = scanner.nextLine();
        System.out.print("Ingrese el nombre del pasajero: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del pasajero: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese la edad del pasajero: ");
        int edad = scanner.nextInt();
        scanner.nextLine(); 

        return new Pasajero(rut, nombre, apellido, edad);
    }

    private static Pasaje crearPasaje(Scanner scanner, Pasajero pasajero) {
        System.out.print("Ingrese el número de vuelo: ");
        int numeroVuelo = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el destino: ");
        String destino = scanner.nextLine();
        System.out.print("Ingrese la fecha de vuelo (dd/MM/yyyy): ");
        String fechaStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fecha;
        try {
            fecha = sdf.parse(fechaStr);
        } catch (ParseException e) {
            System.out.println("Fecha no válida. Formato: dd/MM/yyyy");
            return null;
        }

        double valor;
        while (true) {
            System.out.print("Ingrese el valor del pasaje (debe ser de 10,000 o superior): ");
            valor = scanner.nextDouble();
            scanner.nextLine(); 
            if (Valida.esValorValido(valor)) {
                break;
            } else {
                System.out.println("El valor del pasaje no cumple con el requisito mínimo.");
            }
        }

        int numeroAsiento;
        while (true) {
            System.out.print("Ingrese el número de asiento (entre 1 y 90): ");
            numeroAsiento = scanner.nextInt();
            scanner.nextLine(); 
            if (Valida.esNumeroAsientoValido(numeroAsiento)) {
                break;
            } else {
                System.out.println("El número de asiento no cumple con los requisitos.");
            }
        }

        System.out.print("¿Trae equipaje? (Sí/No): ");
        boolean traeEquipaje = scanner.nextLine().equalsIgnoreCase("Sí");

        return new Pasaje(numeroVuelo, pasajero, destino, fecha, valor, numeroAsiento, traeEquipaje);
    }

    private static void mostrarInformacionPasaje(Pasaje pasaje) {
        if (pasaje == null) {
            System.out.println("Primero debe crear un pasaje.");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Información del pasaje:");
        System.out.println("Número de vuelo: " + pasaje.getNumeroVuelo());
        System.out.println("Pasajero: " + pasaje.getPasajero().getNombre() + " " + pasaje.getPasajero().getApellido());
        System.out.println("Destino: " + pasaje.getDestino());
        System.out.println("Fecha de vuelo: " + dateFormat.format(pasaje.getFecha()));
        System.out.println("Valor del pasaje: " + pasaje.getValor());
        System.out.println("Número de asiento: " + pasaje.getNumeroAsiento());
        System.out.println("¿Trae equipaje?: " + (pasaje.isTraeEquipaje() ? "Sí" : "No"));
        System.out.println("Es un pasaje vigente: " + (Valida.esFechaValida(pasaje.getFecha()) ? "Sí" : "No"));
        System.out.println("Valor final del pasaje: " + pasaje.calcularValorFinal());
    }
}

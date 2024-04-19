import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class veterinariaRegistro {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while (true) {
            System.out.println("1. Registrar cliente");
            System.out.println("2. Mostrar clientes registrados");
            System.out.println("3. Importar archivo");
            System.out.println("4. Salir");
         
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer
            
            switch (opcion) {
                case 1:
                    registrarCliente();
                    break;
                case 2:
                    mostrarClientes();
                    break;
                case 3: 
                    importarArchivo();
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
    }
    
    private static void registrarCliente() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("clientes.txt", true))) {
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Ingrese el DNI del cliente: ");
            String dni = scanner.nextLine();
            System.out.print("Ingrese el nombre del cliente: ");
            String nombreCliente = scanner.nextLine();
            System.out.print("Ingrese el nombre de la mascota: ");
            String nombreMascota = scanner.nextLine();
            
            writer.write(dni + "," + nombreCliente + "," + nombreMascota);
            writer.newLine();
            
            System.out.println("Cliente registrado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo.");
            e.printStackTrace();
        }
    }
    
    private static void mostrarClientes() {
        try (BufferedReader reader = new BufferedReader(new FileReader("clientes.txt"))) {
            String linea;
            System.out.println("Clientes registrados:");
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                System.out.println("DNI: " + datos[0] + ", Nombre: " + datos[1] + ", Mascota: " + datos[2]);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo.");
            e.printStackTrace();
        }
    }
    
    
    private static void importarArchivo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del archivo a importar: ");
        String nombreArchivo = scanner.nextLine();
        
        var archivo = new File(nombreArchivo);
        try {
            var entrada = new BufferedReader(new FileReader(archivo));
            var lectura = entrada.readLine();
            while (lectura != null) {
                System.out.println("lectura = " + lectura);
                lectura = entrada.readLine();
            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
    }
}

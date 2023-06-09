import java.io.*;
import java.util.*;
import java.util.Map.Entry;
public class AddressBook {
    public static void main (String[]args){
        HashMap<String, Object> myMapAddressBook = new HashMap<>();
        Scanner ag = new Scanner(System.in);
        boolean Salir = false;
        int op;
        load (myMapAddressBook);
        while (!Salir) {
            System.out.println("(1) Para ver agenda ");
            System.out.println("(2) Para crear un nuevo contacto ");
            System.out.println("(3) Para eliminar contacto ");
            System.out.println("(4) Para ver sus numeros favoritos  ");
            System.out.println("(5) Para Salir por favor teclee ");
            try{
                System.out.println("Seleciona una opcion");
                op = ag.nextInt();
                switch (op){
                    case 1:
                        list(myMapAddressBook);
                        break;
                    case 2:
                        create(myMapAddressBook);
                        break;
                    case 3:
                        delet(myMapAddressBook);
                        break;
                    case 4:
                        fav(myMapAddressBook);
                        break;
                    case 5:
                        save(myMapAddressBook);
                        Salir = true;
                        break;
                    default:
                        System.out.println("Opcion no valida, por favor teclea la operacion que deseas realizar");
                }
            }catch  (InputMismatchException e) {
                System.out.println("Ingresa un numero");
                ag.next();
            }
        }
    }
    public static void list(HashMap myMapAddressBook) {
        System.out.println("\n Lista de contactos");
        for (Iterator<Entry<String, Object>> entries = myMapAddressBook.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry<String, Object> entry = entries.next();
            String output = String.format("%s:%s:", entry.getKey(), entry.getValue());
            System.out.println(output);
        }
    }

    public static void create(HashMap myMapAddressBook) {
        BufferedReader ag = new BufferedReader(new InputStreamReader(System.in));
        String telefono = null;
        String nombre = null;
        System.out.println("Por favor tecleé el numero telefonico");
        try {
            telefono = ag.readLine();
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Por favor tecleé el nombre del contacto:");
        try {
            nombre = ag.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } System.out.println("Desea guardarlo como Favorito (S/N)");
        try {
            nombre = ag.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (telefono != null && nombre !=null)
            myMapAddressBook.put(telefono, nombre);

    }
    public static void delet(HashMap myMapAddressBook){
        BufferedReader de = new BufferedReader(new InputStreamReader(System.in));
        String telefono = null;
        System.out.println("tecleé el numero telefonico a Eliminar:");

        try {
            telefono = de.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        myMapAddressBook.remove(telefono);
    }
    public static void fav(HashMap myMapAddressBook) {
        System.out.println("\n Lista de contactos");
        for (Iterator<Entry<String, Object>> entries = myMapAddressBook.entrySet().iterator(); entries.hasNext(); ) {
            Map.Entry<String, Object> entry = entries.next();
            String output = String.format("%s:%s", entry.getKey(), entry.getValue());
            System.out.println(output);
        }
    }

    public static void save(HashMap myMapAddressBook){
        String outputFilename = "src/agenda.csv";
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(outputFilename));
            for (Iterator<Entry<String, Object>> entries = myMapAddressBook.entrySet().iterator(); entries.hasNext(); ) {
                Map.Entry<String, Object> entry = entries.next();
                String output = String.format("%s,%s", entry.getKey(), entry.getValue() + "\r\n");
                bufferedWriter.write(output);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }

    public static void load(HashMap myMapAddressBook){
        String inputFilename = "src/agenda.csv";
        BufferedReader bufferedReader = null;
        String Numero = "";
        String Nombre = "";
        try {
            bufferedReader = new BufferedReader(new FileReader(inputFilename));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                int coma = line.indexOf(',');
                Numero = line.substring(0, coma);
                Nombre = line.substring(coma+1, line.length());
                myMapAddressBook.put(Numero, Nombre);
            }
        } catch(IOException e) {
            System.out.println("IOException catched while reading: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                System.out.println("IOException catched while closing: " + e.getMessage());
            }
        }
    }


}

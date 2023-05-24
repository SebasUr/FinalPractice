import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PR1OBJECTS {
    public static void main(String[] args) {

        System.out.println("<----¡Bienvenido a analiza tus DATASETS!---->");
        File selectedFile = selectFile();
        System.out.println();

        System.out.println(selectedFile);

        nowWhat(selectedFile);





















        // Analyzer CSV - Analiza todo el archivo CSS e imprime en consola todos los datos en conjunto, con sus respectivas características.
/*         
        CsvAnalyzer analyzer = new CsvAnalyzer(selectedFile);
        analyzer.analyze();
 */
        // Columna, Clase que recibe la columna y el archivo css para crear un objeto que alberga la columna y se pueden llamar métodos para ver sus características
        
/*         String archivoCSV = "./Medellin Agosto 2022.csv";
        String nombreColumna = "\"TMAX\"";
        try {
            ColumnaCSV columna = new ColumnaCSV(nombreColumna, archivoCSV);
            columna.imprimirColumna();
            System.out.println("Valor máximo: " + columna.valorMaximo());
            System.out.println("Número de datos nulos o vacíos: " + columna.contarNulos());
        } catch (FileNotFoundException e) {
            System.out.println("No se pudo leer el archivo CSV: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } */
    }

    
    public static void menuFiltrar(){
        System.out.println();
        System.out.println("- ¿Qué columnas quieres filtrar? -");
        System.out.println("1. Nombre de la estación\n 2. Rango de temperaturas \n 3. Rango de precipitación");

        
        
    }
    public static void nowWhat(File selectedFile){
        Scanner in = new Scanner(System.in);
        System.out.println("■ Escoge qué quieres hacer con el archivo ■ " + selectedFile);
        System.out.println(" 1. Solamente quiero hacer un análisis completo del archivo\n 2. Adicionar otro archivo \n 3. Cargar un archivo distinto");
        int opcion = in.nextInt();
        boolean h = true;

        while(h==true){
            if(opcion==1){
                CsvAnalyzer analyzer = new CsvAnalyzer(selectedFile);
                analyzer.analyze();
                h = false;
            }else if(opcion==2){
                System.out.println("Casoprueba");
                h = false;
            }else if(opcion==3){
                selectedFile = selectFile();
                nowWhat(selectedFile);
                h = false;
            }
            else if(opcion>3 || opcion<=0){
                System.out.println("Introduzca un número válido.");
                opcion = in.nextInt();
            }
        }
        
    }

    public static File selectFile(){
        File file1 = new File("./3337050.csv");
        File file2 = new File("./3337063.csv");
        File file3 = new File("./3337045.csv");
        Scanner in = new Scanner(System.in);
        System.out.println("        Escoge tu archivo a cargar: ");
        System.out.println(" 1. Ciudad de Pasadena \n 2. Ciudad de Bremerton \n 3. Ciudad de Oakland\n 4. Cargar un archivo diferente\n 5. Salir");
        int opcion = in.nextInt();
        File selectedFile = new File("./3337050.csv");
        boolean h = true;

        while(h==true){
            if(opcion==1){
                System.out.println("Cargando archivo de la Ciudad de Pasadena...");
                System.out.println();
                selectedFile = file1;
                h=false;
            }else if(opcion==2){
                System.out.println("Cargando archivo de la Ciudad de Bremerton...");
                System.out.println();
                selectedFile = file2;
                h=false;
            }else if(opcion==3){
                System.out.println("Cargando archivo de la Ciudad de Oakland...");
                System.out.println();
                selectedFile = file3;
                h=false;
            }else if(opcion==4){
                System.out.println("Ingresa la ruta completa del archivo para cargarlo:");
                String filePath = in.next();
                selectedFile = new File(filePath);
                h=false;
            }else if(opcion==5){
                System.exit(0);
                h=false;
            }
            else if(opcion>5 || opcion<=0){
                System.out.println("Introduzca un número válido.");
                opcion = in.nextInt();
            }
        }
        return selectedFile;
    }
}

class ColumnaCSV {  // Utilización de solo arreglos
    
    private String nombreColumna;
    private String[] datos;
    
    public ColumnaCSV(String nombreColumna, String archivoCSV) throws FileNotFoundException {
        this.nombreColumna = nombreColumna;
        this.datos = leerDatosDeColumna(nombreColumna, archivoCSV);
    }
    
    private String[] leerDatosDeColumna(String nombreColumna, String archivoCSV) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(archivoCSV));
        String[] encabezados = scanner.nextLine().split(",");
        int indiceColumna = -1;
        for (int i = 0; i < encabezados.length; i++) {
            if (encabezados[i].equals(nombreColumna)) {
                indiceColumna = i;
                break;
            }
        }
        if (indiceColumna == -1) {
            throw new IllegalArgumentException("No se encontró la columna " + nombreColumna + " en el archivo " + archivoCSV);
        }
        int numFilas = 0;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            numFilas++;
        }

        String[] datos = new String[numFilas];
        scanner = new Scanner(new File(archivoCSV));
        scanner.nextLine();
        int filaActual = 0;
        while (scanner.hasNextLine()) {
            String[] fila = scanner.nextLine().split(",");
            datos[filaActual] = fila[indiceColumna];
            filaActual++;
        }
        scanner.close();
        return datos;
    }
    
    public void imprimirColumna() {
        System.out.println(nombreColumna);
        for (String dato : datos) { // for - each
            System.out.println(dato);
        }
    }
    
    public String valorMaximo() {
        String maximo = datos[0];
        for (int i = 1; i < datos.length; i++) {
            if (datos[i].compareTo(maximo) > 0) {
                maximo = datos[i];
            }
        }
        return maximo;
    }
    public double valorMinimo() throws NumberFormatException {
        double minimo = Double.MAX_VALUE;
        for (int i = 0; i < datos.length; i++) {
            String dato = datos[i];
            if (dato != null && !dato.equals("")) {
                double valor = Double.parseDouble(dato.replace(",", "."));
                if (valor < minimo) {
                    minimo = valor;
                }
            }
        }
        return minimo;
    }
    
    public int contarNulos() {
        int numNulos = 0;
        for (String dato : datos) {
            if (dato == null || dato.equals("")) {
                numNulos++;
            }
        }
        return numNulos;
    }
}


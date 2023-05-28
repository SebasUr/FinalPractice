import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PR1OBJECTS {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("<----¡Bienvenido a analiza tus DATASETS!---->");
        CsvR selectedFile = selectFile();
        String[][] Matriztotal = nowWhat(selectedFile);

        for(int i =0; i<Matriztotal.length; i++){
            for(int j=0; j<Matriztotal[i].length-12; j++) // para saber que mtriz llevamos hasta ahora
            {
                System.out.print(Matriztotal[i][j] + "\t");
            }
            System.out.println();
        }

        String[][] FiltrarMatriz = menuFiltrar(Matriztotal);
        continuarConFiltro(FiltrarMatriz);
        

        





    }
    public static String[][] continuarConFiltro(String[][] MatrizFiltrada){
            Scanner in = new Scanner(System.in);
            System.out.println("");
            System.out.println(" -¿Quieres agregar otro filtro?-");
            System.out.println(" 1. Sí\n 2. No");
            int opcion = in.nextInt();
        boolean h = true;
        String[][] nextFiltrada = MatrizFiltrada;
        while(h== true){
            if (opcion ==1){
                System.out.println("Accede acá");
            }
            else if (opcion == 2){
                System.out.println("Accede allá");
            }
        }
        return nextFiltrada;



    }

    
    public static String[][] menuFiltrar(String[][] matrizFinal){
        System.out.println();
        System.out.println("- ¿Qué columnas quieres filtrar? -");
        System.out.println(" 1. Nombre de la estación\n 2. Rango de temperaturas \n 3. Rango de precipitación \n 4. No deseo agregar ningún filtro");
        boolean h = true;
        Scanner in = new Scanner(System.in);
        int opcion = in.nextInt();
        String [][] matrizFiltrada = matrizFinal;
        while(h==true){
            if(opcion == 1){
                System.out.println("▲▲ - FILTRANDO POR NOMBRE DE ESTACIÓN - ▲▲");
                Scanner on = new Scanner(System.in);
                System.out.println("Ingresa el nombre de la estación. Ej: US1CALA0036");
                String nombre = on.nextLine();
                on.close();
                String nombreCo = "\"" + nombre + "\"";

                matrizFiltrada = MatrizR.filtrarMatriz(matrizFinal, 0, nombreCo);
                
                // comprobar filtro ---- ELIMINAR LUEGO
                for(int i =0; i<matrizFiltrada.length; i++){
                    for(int j=0; j<matrizFiltrada[i].length-18; j++) // el length de la fila actual.
                    {
                        System.out.print(matrizFiltrada[i][j] + "\t");
                    }
                    System.out.println();
                }

                return matrizFiltrada;
            } else if(opcion == 2){
                System.out.println(" - FILTRANDO RANGOS PROMEDIO DE TEMPERATURA(TAVG) - ");
                Scanner on = new Scanner(System.in);
                System.out.println("    Ingresa el promedio mínimo por el que quieres filtrar. Ej: 0");
                String minimo = on.nextLine();
                System.out.println("    Ingresa el promedio máximo por el que quieres filtrar. Ej: 0");
                String maximo = on.nextLine();
                on.close();
                String minEdit = "\"" + minimo + "\"";
                String maxEdit = "\"" + maximo + "\"";

                matrizFiltrada = MatrizR.filtrarMatrizPorRango(matrizFinal, 11, minEdit, maxEdit);

                    // comprobar filtro ---- ELIMINAR LUEGO
                    for(int i =0; i<matrizFiltrada.length; i++){
                        for(int j=0; j<matrizFiltrada[i].length-15; j++) // el length de la fila actual.
                        {
                            System.out.print(matrizFiltrada[i][j] + "\t");
                        }
                        System.out.println();
                    }

                    return matrizFiltrada;
            } else if(opcion == 3){
                System.out.println(" - FILTRANDO RANGOS DE PRECIPITACIÓN(PRCP) - ");
                Scanner on = new Scanner(System.in);
                System.out.println("    Ingresa el promedio mínimo por el que quieres filtrar. Ej: 0");
                String minimo = on.nextLine();
                System.out.println("    Ingresa el promedio máximo por el que quieres filtrar. Ej: 0");
                String maximo = on.nextLine();
                on.close();
                String minEdit = "\"" + minimo + "\"";
                String maxEdit = "\"" + maximo + "\"";

                matrizFiltrada = MatrizR.filtrarMatrizPorRango(matrizFinal, 8, minEdit, maxEdit);

                    // comprobar filtro ---- ELIMINAR LUEGO
                    for(int i =0; i<matrizFiltrada.length; i++){
                        for(int j=0; j<matrizFiltrada[i].length-15; j++) // el length de la fila actual.
                        {
                            System.out.print(matrizFiltrada[i][j] + "\t");
                        }
                        System.out.println();
                    }

                    return matrizFiltrada;
            } else if(opcion ==4){
                return matrizFiltrada;
            }

        }
        return matrizFiltrada;
    }

    public static String[][] nowWhat(CsvR selectedFile) throws FileNotFoundException{
        Scanner in = new Scanner(System.in);
        System.out.println("■ Escoge qué quieres hacer con el archivo ■ " + selectedFile);
        System.out.println(" 1. Solamente quiero hacer un análisis completo del archivo\n 2. Adicionar otro archivo \n 3. Cargar un archivo distinto \n 4. Continuar con los filtros" );
        int opcion = in.nextInt();
        boolean h = true;
        selectedFile.loadData();
        String [][] FileActual = selectedFile.getData(); 

        while(h==true){
            if(opcion==1){
                // Csv Analyzer
                h = false;
            }else if(opcion==2){
                CsvR Archivoadicional = selectFile();
                Archivoadicional.loadData();
                String[][] matrizAdicional = Archivoadicional.getData();
                FileActual = Archivoadicional.combinarMatrices(FileActual,matrizAdicional);
                h=false;

            }else if(opcion==3){
                selectedFile = selectFile();
                nowWhat(selectedFile);
                h = false;
            }else if(opcion==4){
                System.out.println("Continuando...");
                System.out.println();
                return FileActual;
            }
            else if(opcion>4 || opcion<=0){
                System.out.println("Introduzca un número válido.");
                opcion = in.nextInt();
            }
        }
        return FileActual;
        
    }

    public static CsvR selectFile() throws FileNotFoundException{
        File file1 = new File("./3337050.csv");
        File file2 = new File("./3337063.csv");
        File file3 = new File("./3337045.csv");
        Scanner in = new Scanner(System.in);
        System.out.println("        Escoge tu archivo a cargar: ");
        System.out.println(" 1. Ciudad de Pasadena \n 2. Ciudad de Bremerton \n 3. Ciudad de Oakland\n 4. Cargar un archivo diferente\n 5. Salir");
        int opcion = in.nextInt();
        File selectedFile = new File("./3337050.csv");
        boolean h = true;
        CsvR csvArchivo = new CsvR(selectedFile); 


        while(h==true){
            if(opcion==1){
                System.out.println("Cargando archivo de la Ciudad de Pasadena...");
                System.out.println();
                selectedFile = file1;
                csvArchivo = new CsvR(selectedFile); 
                h=false;
            }else if(opcion==2){
                System.out.println("Cargando archivo de la Ciudad de Bremerton...");
                System.out.println();
                selectedFile = file2;
                csvArchivo = new CsvR(selectedFile); 
                h=false;
            }else if(opcion==3){
                System.out.println("Cargando archivo de la Ciudad de Oakland...");
                System.out.println();
                selectedFile = file3;
                csvArchivo = new CsvR(selectedFile); 
                h=false;
            }else if(opcion==4){
                System.out.println("Ingresa la ruta completa del archivo para cargarlo:");
                String filePath = in.next();
                selectedFile = new File(filePath);
                csvArchivo = new CsvR(selectedFile); 
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
        return csvArchivo;
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


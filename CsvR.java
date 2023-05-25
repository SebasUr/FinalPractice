import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvR {
    private String[][] data;
    private File file;

    public CsvR(File file) {
        this.file = file;
        this.data = loadData();
    }


    public String[][] loadData() {
        int columnas = contarColumnas(file)+1;
        int filas = contarLineas(file);
        String[][] aloj = new String[filas][columnas];

        Scanner scanner;
        try {
            int fila = 0;
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                for(int i=0; i<fields.length; i++){
                    if (i>=2) {
                        aloj[0][i+1] = fields[i];
                    }
                    aloj[fila][i] = fields[i];
                }
                fila++;
                aloj[0][2] = "STATE";
            }

            scanner.close();

        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        return aloj;
    }

    public String[][] getData(){
        return this.data;
    }
    
    private int contarColumnas(File archivo){
        Scanner scanner;
        int columnas = 0;
        try {
            scanner = new Scanner(archivo);
            columnas = 0;
            if (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] fields = line.split(",");
                columnas = fields.length;
            }
            scanner.close();
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        return columnas;
    }

    private int contarLineas(File archivo){
        Scanner scanner;
        int totalLineas = 0;
        try {
            scanner = new Scanner(archivo);
            totalLineas = 0;
            while (scanner.hasNextLine()){
                totalLineas = totalLineas+ 1;
                scanner.nextLine(); // le decimos que siga a la sgnte linea
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return totalLineas;
    }


    public void imprimir(){
        for(int i =0; i<data.length; i++){
            for(int j=0; j<data[i].length-23; j++) // el length de la fila actual.
            {
                System.out.print(data[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public String contarNulosYVacios(int columna, String[][] matriz) {
        int variable = 0;
        int contadorVac = 0;
        for (int i = 0;i<matriz.length;i++) {
            if (matriz[i][columna] == null) {
                variable+=1;
            }
            else if (matriz[i][columna].isEmpty() || matriz[i][columna].equals("")) {
                contadorVac+=1;
            }
        }
        return "La columna " + matriz[0][columna] + " tiene " + variable + " nulos"+ " y tiene " + contadorVac + " vacios.";
    }

    public String maxPerColumn(int columna, String[][] matriz){
        float max = 0;
        
        for (int i = 0;i<matriz.length;i++) {
            if(matriz[i][columna] != null && !matriz[i][columna].isEmpty() && !matriz[i][columna].equals("")){
                String datostring = matriz[i][columna];
                String numstring = "";
                for (int j = 0; j < datostring.length(); j++) {
                    char c = datostring.charAt(j);
                    if ((c >= '0' && c <= '9') || c == '.') {
                        numstring += c;
                    }           
                }
                if (numstring.length() > 0) {
                    float numActual = Float.parseFloat(numstring);

                    if (numActual > max) {
                        max = numActual;
                    }
                }
                
            }
        }
        float count = max;
        float min = 0;
        for (int i = 0;i<matriz.length;i++) {
            if(matriz[i][columna] != null && !matriz[i][columna].isEmpty() && !matriz[i][columna].equals("")){
                String datostring = matriz[i][columna];
                String numstring = "";
                for (int j = 0; j < datostring.length(); j++) {
                    char c = datostring.charAt(j);
                    if ((c >= '0' && c <= '9') || c == '.') {
                        numstring += c;
                    }           
                }
                if (numstring.length() > 0) {
                    float numActual = Float.parseFloat(numstring);

                    if (numActual < count) {
                        min = numActual;
                        count = numActual;
                    }
                }
                
                
            }
        }
        return "La columna " + matriz[0][columna] + " tiene como número máximo " + max + " y " + min + " como número mínimo.";

        
    }
    private void ordenarArreglo(String[] arregloStrings) {
        for (int i = 0; i < arregloStrings.length - 1; i++) {
            for (int j = i + 1; j < arregloStrings.length; j++) {
                if (arregloStrings[j].compareTo(arregloStrings[i]) < 0) {
                    String temp = arregloStrings[i];
                    arregloStrings[i] = arregloStrings[j];
                    arregloStrings[j] = temp;
                }
            }
        }
    }

    public String cuartil25(String[][] matriz, int columna) {
        int filas = contarColumnas(file);
        
        String[] arr = new String[filas];
        
        for (int i = 0; i < filas; i++) {
            arr[i] = matriz[i][columna];
        }
        
        ordenarArreglo(arr);
        
        int index = (int) Math.ceil(filas * 0.25) - 1;
        
        return arr[index];
    }

    public String cuartil75(String[][] matriz, int columna) {
        int filas = contarColumnas(file);
        
        String[] arr = new String[filas];
        
        for (int i = 0; i < filas; i++) {
            arr[i] = matriz[i][columna];
        }
        
        ordenarArreglo(arr);
        
        int index = (int) Math.ceil(filas * 0.75) - 1;
        
        return arr[index];
    }
}


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CsvR {
    private String[][] data;
    private File file;

    public CsvR(File file) throws FileNotFoundException {
        this.file = file;
        this.data = loadData();
    }


    public String[][] loadData() throws FileNotFoundException {
        int columnas = contarColumnas(file) + 1;
        int filas = contarLineas(file);
        String[][] aloj = new String[filas][columnas];
        Scanner scan1 = new Scanner(file);
        String linea1 = scan1.nextLine();
        String[] myLine = linea1.split(",");
        String[] nuevo = new String[myLine.length+1];
        for(int i=0; i<nuevo.length;i++){
            if(i<2){
                nuevo[i] = myLine[i];
            }
            if(i==2){
                nuevo[i] = "\"STATE\"";
            }
            if(i>2){
                nuevo[i] = myLine[i-1];
            }
        }

        String[][] asd = {nuevo};

        Scanner scanner;
        try {
            int fila = 0;
            boolean primeraLineaLeida = false;
            scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!primeraLineaLeida) {
                    primeraLineaLeida = true;
                    continue; // Salta la primera línea
                }
                String[] fields = line.split(",");
                for (int i = 0; i < fields.length; i++) {
                    aloj[fila][i] = fields[i];
                }
                fila++;
            }
    
            scanner.close();
    
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[][] laMatriz = combinarMatrices(asd, aloj);
        return laMatriz;
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

    public String[][] combinarMatrices(String[][] matriz1, String[][] matriz2) {
        int filas1 = matriz1.length;
        int columnas1 = matriz1[0].length;
        int filas2 = matriz2.length;
        int columnas2 = matriz2[0].length;

        String[][] matrizCombinada = new String[filas1 + filas2][columnas1];

        for (int i = 0; i < filas1; i++) {
            for (int j = 0; j < columnas1; j++) {
                matrizCombinada[i][j] = matriz1[i][j];
            }
        }

        for (int i = 0; i < filas2; i++) {
            for (int j = 0; j < columnas2; j++) {
                matrizCombinada[filas1 + i][j] = matriz2[i][j];
            }
        }

        return matrizCombinada;
    }
}


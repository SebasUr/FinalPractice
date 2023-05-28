import java.util.Scanner;
public class MatrizFiltrada {
    
    public static String[][] filtrarMatrizPorRango(String[][] matriz, int columna, String rangoMenor, String rangoMayor) {
        int contador = 0;
        
        // Contar cuántas filas están dentro del rango especificado en la columna dada
        for (int i = 0; i < matriz.length; i++) {
            String edad = matriz[i][columna];
            if(edad !=null){
            if (edad.compareTo(rangoMenor) >= 0 || edad.compareTo(rangoMayor) <= 0) {
                contador++;
                System.out.println(contador);
            }}
        }
        
        // Crear una nueva matriz con el tamaño adecuado
        String[][] matrizFiltrada = new String[contador][matriz[0].length];
        int fila = 0;
        
        // Copiar las filas que están dentro del rango a la nueva matriz
        for (int i = 0; i < matriz.length; i++) {
            String edad = matriz[i][columna];
            if(edad != null){
            if (edad.compareTo(rangoMenor) >= 0 && edad.compareTo(rangoMayor) <= 0) {
                for (int j = 0; j < matriz[i].length; j++) {
                    matrizFiltrada[fila][j] = matriz[i][j];
                }
                fila++;
            }
        }
        }
        
        return matrizFiltrada;
    }
    
    public static void main(String[] args) {
        // Ejemplo de uso
        String[][] matriz = {
            {"Juan", "25", "asd"},
            {"María", "30", "ss"},
            {"Pedro", "20", "hola"},
            {"Luisa", "40", "xD"},
            {"Luisa", null, "dsad"},
            
        };
        
        int columna = 1;
        
        Scanner in = new Scanner(System.in);
        System.out.println("min");
        String rangoMenor = in.nextLine();
        System.out.println("max");
        String rangoMayor = in.nextLine();

        
        String[][] matrizFiltrada = filtrarMatrizPorRango(matriz, columna, rangoMenor, rangoMayor);
        
        // Imprimir la matriz filtrada
        for (int i = 0; i < matrizFiltrada.length; i++) {
            for (int j = 0; j < matrizFiltrada[i].length; j++) {
                System.out.print(matrizFiltrada[i][j] + " ");
            }
            System.out.println();
        }
    }
}
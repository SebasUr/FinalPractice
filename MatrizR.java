public class MatrizR {
    private String[][] data;

    public MatrizR(String[][] data){
        this.data = data;
    }

    public void imprimir(){
        for(int i =0; i<data.length; i++){
            for(int j=0; j<data[i].length-18; j++) // el length de la fila actual.
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
        int filas = contarFilas(matriz);
        
        String[] arr = new String[filas];
        
        for (int i = 0; i < filas; i++) {
            arr[i] = matriz[i][columna];
        }
        
        ordenarArreglo(arr);
        
        int index = (int) Math.ceil(filas * 0.25) - 1;
        
        return arr[index];
    }

    public String cuartil75(String[][] matriz, int columna) {
        int filas = contarFilas(matriz);
        
        String[] arr = new String[filas];
        
        for (int i = 0; i < filas; i++) {
            arr[i] = matriz[i][columna];
        }
        
        ordenarArreglo(arr);
        
        int index = (int) Math.ceil(filas * 0.75) - 1;
        
        return arr[index];
    }

    public static int contarFilas(String[][] matriz) {
        if (matriz.length == 0) {
            return 0;
        }
        return matriz.length;
    }
    
    public static int contarColumnas(String[][] matriz) {
        if (matriz.length == 0 || matriz[0].length == 0) {
            return 0;
        }
        return matriz[0].length;
    }

}

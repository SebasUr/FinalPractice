import javax.print.DocFlavor.STRING;

public class MatrizR {
    private String[][] data;

    public MatrizR(String[][] data){
        this.data = data;
    }

    public String[][] getMatriz() {
        return this.data;
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

    public String contarNulosYVacios(int columna, String name) {
        int variable = 0;
        int contadorVac = 0;
        for (int i = 0;i<data.length;i++) {
            if (data[i][columna] == null) {
                variable+=1;
            }
            else if (data[i][columna].isEmpty() || data[i][columna].equals("")) {
                contadorVac+=1;
            }
        }
        return "La columna " + name + " tiene " + variable + " nulos"+ " y tiene " + contadorVac + " vacios.";
    }

    public String maxPerColumn(int columna, String name){
        float max = 0;
        
        for (int i = 0;i<data.length;i++) {
            if(data[i][columna] != null && !data[i][columna].isEmpty() && !data[i][columna].equals("")){
                String datostring = data[i][columna];
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
        for (int i = 0;i<data.length;i++) {
            if(data[i][columna] != null && !data[i][columna].isEmpty() && !data[i][columna].equals("")){
                String datostring = data[i][columna];
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
        return "La columna " + name + " tiene como número máximo " + max + " y " + min + " como número mínimo."; 
    }

    private int nulosArreglo(String[] arr) {
        int variable = 0;
        int contadorVac = 0;
        for (int i = 0;i<arr.length;i++) {
            if (arr[i] == null) {
                variable+=1;
            }
            else if (arr[i].isEmpty() || arr[i].equals("")) {
                contadorVac+=1;
            }
        }
        return variable + contadorVac;
    }
    
    private void ordenarArreglo(double[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    double temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private double quitarComillas(String arr) {
        String stringWithoutQuotes = arr.replaceAll("^\"|\"$", "");
        return Double.parseDouble(stringWithoutQuotes);
    }

    public static String[][] filtrarMatriz(String[][] matriz, int columna, String dato) {
        int contador = 0;
        
        // Contar cuántas filas coinciden con el dato proporcionado en la columna dada
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][columna] != null){
                if (matriz[i][columna].equals(dato)) {
                    contador++;
                }
            }
        }
        
        // Crear una nueva matriz con el tamaño adecuado
        String[][] matrizFiltrada = new String[contador][matriz[1].length];
        int fila = 0;
        
        // Copiar las filas que coinciden con el dato a la nueva matriz
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][columna] != null){
                if (matriz[i][columna].equals(dato)) {
                    for (int j = 0; j < matriz[i].length; j++) {
                        matrizFiltrada[fila][j] = matriz[i][j];
                    }
                    fila++;
                }
            }
        }
        
        return matrizFiltrada;
    }

    public static String[][] filtrarMatrizPorRango(String[][] matriz, int columna, String rangoMenor, String rangoMayor) {
        int contador = 0;
/*         rangoMenor = "\"" + rangoMenor + "\"";
        rangoMayor = "\"" + rangoMayor + "\""; */
        
        // Contar cuántas filas están dentro del rango especificado en la columna dada
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][columna] !=null){
                String edad = matriz[i][columna];
                if (edad.compareTo(rangoMenor) >= 0 && edad.compareTo(rangoMayor) <= 0) {
                    contador++;
                }
            }
        }
        
        // Crear una nueva matriz con el tamaño adecuado
        String[][] matrizFiltrada = new String[contador][matriz[0].length];
        int fila = 0;
        
        // Copiar las filas que están dentro del rango a la nueva matriz
        for (int i = 0; i < matriz.length; i++) {
            if(matriz[i][columna] !=null){
                String edad = matriz[i][columna];
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

    public void cuartil(int columna, String name) {
        int filas = contarFilas(data);
        
        String[] arr = new String[filas];
        
        for (int i = 0; i < filas; i++) {
            arr[i] = data[i][columna];
        }

        int nuls = nulosArreglo(arr);
        int longi = (filas - nuls);
        String[] arr2 = new String[longi];

        int contador = 0;

        for (int l = 0;l<filas;l++) {
            if (!(arr[l] == null  || arr[l].isEmpty() || arr[l].equals(""))) {
                arr2[contador] = arr[l];
                contador++;
            }
        }

        double[] nums = new double[longi];

        for (int j = 0;j<longi;j++) {
            nums[j] = quitarComillas(arr2[j]);
        }
        
        ordenarArreglo(nums);
        
        int index = (int) Math.ceil(longi * 0.25) - 1;
        int index2 = (int) Math.ceil(longi * 0.75) - 1; 
        
        if (nums.length > 0) {
            System.out.println("En la columna " + name + " el primer cuartil es: " + nums[index] + " Y el tercer cuartil es: " + nums[index2]);
        } else {
            System.out.println("La columna " + name + " no tiene cuartiles debido a que es nula o vacía");

        }
        
    }


    public double promedio(int columna) {
        int filas = contarFilas(data);
        
        String[] arr = new String[filas];
        
        for (int i = 0; i < filas; i++) {
            arr[i] = data[i][columna];
        }

        int nuls = nulosArreglo(arr);
        int longi = (filas - nuls);
        String[] arr2 = new String[longi];

        int contador = 0;

        for (int l = 0;l<filas;l++) {
            if (!(arr[l] == null  || arr[l].isEmpty() || arr[l].equals(""))) {
                arr2[contador] = arr[l];
                contador++;
            }
        }
        
        double[] nums = new double[longi];

        for (int j = 0;j<longi;j++) {
            nums[j] = quitarComillas(arr2[j]);
        }

        double acum = 0;

        for (int k = 0;k<longi;k++) {
            acum += nums[k];
        }
        double fils = longi;
        return acum/fils;
        
    }

    public double desviacion(int columna) {
        int filas = contarFilas(data);

        double acum = 0;

        double prom = promedio(columna);

        String[] arr = new String[filas];

        for (int i = 0; i < filas; i++) {
            arr[i] = data[i][columna];
        }  

        int nuls = nulosArreglo(arr);
        int longi = (filas - nuls);
        String[] arr2 = new String[longi];

        int contador = 0;

        for (int l = 0;l<filas;l++) {
            if (!(arr[l] == null  || arr[l].isEmpty() || arr[l].equals(""))) {
                arr2[contador] = arr[l];
                contador++;
            }
        }
        
        double[] nums = new double[longi];

        for (int j = 0;j<longi;j++) {
            nums[j] = quitarComillas(arr2[j]);
        }

        for (int k = 0;k<longi;k++) {
            acum += Math.pow((nums[k] - prom), 2);
        }
        double varianza = acum / longi;
        double desviacionEstandar = Math.sqrt(varianza);
        if (Double.isNaN(desviacionEstandar)) {
            desviacionEstandar = 999999;
        } 
        return desviacionEstandar;
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

    public String promedioG(int columna, String name) {
        int filas = contarFilas(data);
        
        String[] arr = new String[filas];
        
        for (int i = 0; i < filas; i++) {
            arr[i] = data[i][columna];
        }

        int nuls = nulosArreglo(arr);
        int longi = (filas - nuls);
        String[] arr2 = new String[longi];

        int contador = 0;

        for (int l = 0;l<filas;l++) {
            if (!(arr[l] == null  || arr[l].isEmpty() || arr[l].equals(""))) {
                arr2[contador] = arr[l];
                contador++;
            }
        }
        
        double[] nums = new double[longi];

        for (int j = 0;j<longi;j++) {
            nums[j] = quitarComillas(arr2[j]);
        }

        double acum = 0;

        for (int k = 0;k<longi;k++) {
            acum += nums[k];
        }
        double resp = acum/filas;
        if (acum == 0) {
            resp = 0;
        } 
        return "El promedio de la columna " + name + " es: " + resp;
        
    }

    public String[][] filtrarPrimeros() {
        int filas = Math.min(5, data.length);
        int columnas = data[0].length; 
    
        String[][] matrizNueva = new String[filas][columnas];
    
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizNueva[i][j] = data[i][j];
            }
        }
    
        return matrizNueva;
    }

    public String[][] filtrarUltimos() {
        int filas = Math.min(5, data.length); 
        int columnas = data[0].length; 
        int inicio = data.length - filas;  
    
        String[][] matrizNueva = new String[filas][columnas];
    
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                matrizNueva[i][j] = data[inicio + i][j];
            }
        }
    
        return matrizNueva;
    }

}

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

    public double cuartil25(int columna) {
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
        
        return nums[index];
    }

    public double cuartil75(int columna) {
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
        
        int index = (int) Math.ceil(longi * 0.75) - 1; 
        
        return nums[index];
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

}

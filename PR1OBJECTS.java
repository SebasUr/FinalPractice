import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.naming.ldap.ControlFactory;

public class PR1OBJECTS {
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println(" .d8b.  d8b   db  .d8b.  db    db    db d88888D d88888b d8888b. ");
        System.out.println("d8' `8b 888o  88 d8' `8b 88    `8b  d8' YP  d8' 88'     88  `8D ");
        System.out.println("88ooo88 88V8o 88 88ooo88 88     `8bd8'     d8'  88ooooo 88oobY' ");
        System.out.println("88~~~88 88 V8o88 88~~~88 88       88      d8'   88~~~~~ 88`8b   ");
        System.out.println("88   88 88  V888 88   88 88booo.  88     d8' db 88.     88 `88. ");
        System.out.println("YP   YP VP   V8P YP   YP Y88888P  YP    d88888P Y88888P 88   YD ");

        System.out.println();
                // LLAMAMOS FUNCIONES DEL MENÚ
        System.out.println("<----¡Bienvenido a analiza tus DATASETS!---->");
        CsvR selectedFile = selectFile();
        String[][] Matriztotal = nowWhat(selectedFile);
        String[] primeraFila = new String[Matriztotal[0].length];
        for (int i = 0; i < (Matriztotal[0].length); i++) {
            primeraFila[i] = Matriztotal[0][i];
        }

        /* for(int i =0; i<Matriztotal.length; i++){
            for(int j=0; j<Matriztotal[i].length-12; j++) // para saber que mtriz llevamos hasta ahora
            {
                System.out.print(Matriztotal[i][j] + "\t");
            }
            System.out.println();
        } */

        String[][] FiltrarMatriz = menuFiltrar(Matriztotal);
        String[][] FinallyMatriz = continuarConFiltro(FiltrarMatriz, Matriztotal);

        /* System.out.println("IMPRIMIENDO FINALMENTE LA MATRIZ");
        for(int i =0; i<FinallyMatriz.length; i++){
            for(int j=0; j<FinallyMatriz[i].length-16; j++) // para saber que mtriz llevamos hasta ahora
            {
                System.out.print(FinallyMatriz[i][j] + "\t");
            }
            System.out.println();
        } */
        
        MatrizR LastMatriz = new MatrizR(FinallyMatriz);
        if(LastMatriz.getMatriz().length<=0){
            System.out.println("TE QUEDASTE SIN DATOS! POR FAVOR INICIA NUEVAMENTE EL PROGRAMA.");
        } else {
        menuFuncionesFinales(LastMatriz, primeraFila);
    }




    }

    // MENÚ PARA FUNCIONES FINALES CON LA MATRIZ TOTALMENTE FILTRADA
    public static void menuFuncionesFinales(MatrizR matriz, String[] primeraFila){
        String[][] data = matriz.getMatriz();
        Scanner in = new Scanner(System.in);
        System.out.println();
        System.out.println("====== ¿Qué deseas ver o realizar con tus datos finales? ======");
        System.out.println(" 1. Ver datos nulos de cada columna \n 2. Visualizar primeros datos del dataset \n 3. Visualizar últimos datos del dataset \n 4. Cantidad de registros y columnas \n 5. Visualizar mínimos y máximos de cada colúmna \n 6. Promedio de cada columna \n 7. Cuartiles \n 8. Desviación estándar \n 9. Imprimir mi dataset con sus filtros. \n 10. Salir");
        System.out.println("================================================================");
        System.out.print("  |=====> ");
        int opcion = 0;
        boolean isInputValid = false;

        while (!isInputValid) { // VALIDACIÓN DE LETRAS Y NUMEROS INVÁLIDOS
            try {
                opcion = in.nextInt();
                isInputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                System.out.print("  |=====> ");
                in.nextLine();
            }
        }
        if (opcion >10 || opcion <1) {
            System.out.println("  -Ingrese como respuesta el número de la opción que desea elegir-");
            System.out.print("  |=====> ");
            opcion = in.nextInt();
        }
        boolean h = true;
        while (h==true) {
            if (opcion == 1) {
                for (int i =4;i<(data[0].length);i++) {
                    System.out.println(matriz.contarNulosYVacios(i, primeraFila[i]));
                }
                menuFuncionesFinales(matriz, primeraFila);
                h = false;
            } else if (opcion == 2) { 
                String[][] nueva = matriz.filtrarPrimeros();
                for(int i =0; i<nueva.length; i++){
                    for(int j=0; j<nueva[i].length; j++)
                    {
                        System.out.print(nueva[i][j] + " ");
                    }
                    System.out.println();
                }
                menuFuncionesFinales(matriz, primeraFila);
                h=false;
            } else if (opcion == 3) {
                String[][] nueva = matriz.filtrarUltimos();
                for(int i =0; i<nueva.length; i++){
                    for(int j=0; j<nueva[i].length; j++)
                    {
                        System.out.print(nueva[i][j] + " ");
                    }
                    System.out.println();
                }
                menuFuncionesFinales(matriz, primeraFila);
                h = false;
            } else if (opcion == 4) {
                System.out.println();
                System.out.println("El número de registros es: " + data.length + " y el número de colúmnas es: " + data[0].length);
                menuFuncionesFinales(matriz, primeraFila);
                h=false;
                
            } else if (opcion == 5) {
                for (int i = 4;i<data[0].length;i++) {
                    System.out.println(matriz.maxPerColumn(i, primeraFila[i]));
                }
                menuFuncionesFinales(matriz, primeraFila);
                h = false;
            } else if (opcion == 6) {
                for (int i = 4;i<data[0].length;i++) {
                    System.out.println(matriz.promedioG(i, primeraFila[i]));
                }
                menuFuncionesFinales(matriz, primeraFila);
                h = false;
            } else if (opcion == 7) {
                for (int i = 4;i<data[0].length;i++) {
                    matriz.cuartil(i, primeraFila[i]);
                }
                menuFuncionesFinales(matriz, primeraFila);
                h = false;
            } else if (opcion == 8) {
                for (int i = 4;i<data[0].length;i++) {
                    double desv = matriz.desviacion(i);
                    if (desv == 999999) {
                        System.out.println("En la columna " + primeraFila[i] + " no se puede calcular la desviación estándar debido a que es nula o vacía");
                    } else {
                        System.out.println("En la columna "+ primeraFila[i] + " la desviación estándar es " + desv);
                    }
                }
                menuFuncionesFinales(matriz, primeraFila);
                h = false;
            } else if (opcion == 9) {
                for(int i =0; i<data.length; i++){
                    for(int j=0; j<data[i].length; j++)
                    {
                        System.out.print(data[i][j] + " ");
                    }
                    System.out.println();
                }
                menuFuncionesFinales(matriz, primeraFila);
                h = false;
            } else if (opcion == 10) {
                System.out.println("    =====> Finalizando.....");
                h=false;
            }
        }

    }

// menú para confirmar filtros
    public static String[][] continuarConFiltro(String[][] MatrizFiltrada, String[][] MatrizSinCambios){
        Scanner in = new Scanner(System.in);
        System.out.println("");
        System.out.println(" -¿Quieres agregar otro filtro? (¡Si no has agregado puedes arrepentirte!)-");
        System.out.println(" 1. Sí\n 2. No \n 3. Quiero descartar mis filtros actuales y poner otros o cambiarlos. \n 4. Quiero descartar los filtros y continuar.");
        System.out.print("  |=====> ");
        int opcion = 0;
        boolean isInputValid = false;

        while (!isInputValid) { // Validación de letras y nuúmeros i nválidos
            try {
                opcion = in.nextInt();
                isInputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                System.out.print("  |=====> ");
                in.nextLine();
            }
        }
        if (opcion >4 || opcion <1) {
            System.out.println("  -Ingrese como respuesta el número de la opción que desea elegir-");
            System.out.print("  |=====> ");
            opcion = in.nextInt();
        }


        boolean h = true; // Para romper el ciclo
        String[][] nextFiltrada = MatrizFiltrada;
        while(h== true){
            if (opcion ==1){
                nextFiltrada = menuFiltrar(MatrizFiltrada); // Se vuelven a llamar los menús y se retorna posteriormente la nueva matriz
                nextFiltrada = continuarConFiltro(nextFiltrada, MatrizSinCambios); // Recursividad
                h = false;
            }
            else if (opcion == 2){
                System.out.println(" -:Continuando.....");
                h=false;
            }
            else if (opcion == 3){
                nextFiltrada = menuFiltrar(MatrizSinCambios); // Se vuelven a llamar los menús y se retorna posteriormente la nueva matriz (Ahora con la primera matriz)
                nextFiltrada = continuarConFiltro(nextFiltrada, MatrizSinCambios); // Recursividad
                h = false;
            }
            else if(opcion == 4){
                nextFiltrada = MatrizSinCambios;
                h=false;
            }
        }
        return nextFiltrada;
    }

    
    public static String[][] menuFiltrar(String[][] matrizFinal){ // Menú para filtrar nuestra matriz
        System.out.println();
        System.out.println("- ¿Qué columnas quieres filtrar? -");
        System.out.println(" 1. Nombre de la estación\n 2. Rango de temperaturas \n 3. Rango de precipitación \n 4. No deseo agregar ningún filtro");
        System.out.print("  |=====> ");
        boolean h = true;
        Scanner in = new Scanner(System.in);
        int opcion = 0;
        boolean isInputValid = false;

        while (!isInputValid) { // Validación de números o letras inválidas
            try {
                opcion = in.nextInt();
                isInputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                System.out.print("  |=====> ");
                in.nextLine();
            }
        }
        if (opcion >4 || opcion <1) {
            System.out.println("  -Ingrese como respuesta el número de la opción que desea elegir-");
            System.out.print("  |=====> ");
            opcion = in.nextInt();
        }
        String [][] matrizFiltrada = matrizFinal;
        while(h==true){
            if(opcion == 1){
                System.out.println();

                System.out.println("//- FILTRANDO POR NOMBRE DE ESTACIÓN -//");
                Scanner on = new Scanner(System.in);
                System.out.println("Ingresa el nombre de la estación. Ej: US1CALA0036");
                System.out.print("  |=====> ");
                String nombre = on.nextLine();
                String nombreCo = "\"" + nombre + "\"";

                matrizFiltrada = MatrizR.filtrarMatriz(matrizFinal, 0, nombreCo);
                
                /* // comprobar filtro ---- ELIMINAR LUEGO
                for(int i =0; i<matrizFiltrada.length; i++){
                    for(int j=0; j<matrizFiltrada[i].length-18; j++) // Comprobar matriz que se lleva hasta ahora
                    {
                        System.out.print(matrizFiltrada[i][j] + "\t");
                    }
                    System.out.println();
                } */

                return matrizFiltrada;
            } else if(opcion == 2){
                System.out.println();
                System.out.println(" - FILTRANDO RANGOS PROMEDIO DE TEMPERATURA(TAVG) - ");
                Scanner on = new Scanner(System.in);
                System.out.println("    Ingresa el promedio mínimo por el que quieres filtrar. Ej: 0");
                System.out.print("  |=====> ");
                String minimo = on.nextLine();
                System.out.println("    Ingresa el promedio máximo por el que quieres filtrar. Ej: 0");
                System.out.print("  |=====> ");
                String maximo = on.nextLine();
                String minEdit = "\"" + minimo + "\""; // Ponemos dentro de comillas pues en los csv se encuentra así.
                String maxEdit = "\"" + maximo + "\"";

                matrizFiltrada = MatrizR.filtrarMatrizPorRango(matrizFinal, 11, minEdit, maxEdit);

                    // comprobar filtro ---- ELIMINAR LUEGO
                    for(int i =0; i<matrizFiltrada.length; i++){
                        for(int j=0; j<matrizFiltrada[i].length-15; j++) // Comprobar matriz que se lleva hasta ahora
                        {
                            System.out.print(matrizFiltrada[i][j] + "\t");
                        }
                        System.out.println();
                    }

                    return matrizFiltrada;
            } else if(opcion == 3){
                System.out.println();

                System.out.println(" - FILTRANDO RANGOS DE PRECIPITACIÓN(PRCP) - ");
                Scanner on = new Scanner(System.in);
                System.out.println("    Ingresa el promedio mínimo por el que quieres filtrar. Ej: 0");
                String minimo = on.nextLine();
                System.out.println("    Ingresa el promedio máximo por el que quieres filtrar. Ej: 0");
                String maximo = on.nextLine();
                String minEdit = "\"" + minimo + "\"";
                String maxEdit = "\"" + maximo + "\"";

                matrizFiltrada = MatrizR.filtrarMatrizPorRango(matrizFinal, 8, minEdit, maxEdit);

                    // comprobar filtro ---- ELIMINAR LUEGO
                    for(int i =0; i<matrizFiltrada.length; i++){
                        for(int j=0; j<matrizFiltrada[i].length-15; j++) // Comprobar matriz que se lleva hasta ahora
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

    // Menú para adicionar, cambiar o seguir con el archivo
    public static String[][] nowWhat(CsvR selectedFile) throws FileNotFoundException {
        Scanner in = new Scanner(System.in);
        System.out.println(" ■ Escoge qué quieres hacer con el archivo ■ ");
        System.out.println(" 1. Adicionar otro archivo \n 2. Cargar un archivo distinto \n 3. Continuar con los filtros" );
        System.out.println(" ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■" );
        System.out.print("  |=====> ");
        int opcion = 0;
        boolean isInputValid = false;

        while (!isInputValid) {
            try {
                opcion = in.nextInt();
                isInputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                System.out.print("  |=====> ");
                in.nextLine();
            }
        }
        if(opcion>3 || opcion<=0){
            System.out.println("Introduzca un número válido.");
            System.out.print("  |=====> ");
            opcion = in.nextInt();
        }
        boolean h = true;
        selectedFile.loadData();
        String [][] FileActual = selectedFile.getData(); 


        while(h==true){
            if(opcion==1){
                CsvR Archivoadicional = selectFile();
                Archivoadicional.loadData();
                String[][] matrizAdicional = Archivoadicional.getData();
                FileActual = Archivoadicional.combinarMatricesConExcepcion(FileActual,matrizAdicional);
                h=false;

            }else if(opcion==2){
                selectedFile = selectFile();
                nowWhat(selectedFile);
                h = false;
            }else if(opcion==3){
                System.out.println();
                System.out.println("Continuando...");
                System.out.println();
                return FileActual;
            }
            
        }
        return FileActual;
        
    }
// Menú para seleccionar archivo
    public static CsvR selectFile() throws FileNotFoundException{
        File file1 = new File("./3337050.csv");
        File file2 = new File("./3337063.csv");
        File file3 = new File("./3337045.csv");
        Scanner in = new Scanner(System.in);
        System.out.println("");
        System.out.println("        Escoge tu archivo a cargar: ");
        System.out.println(" 1. San Francisco y sus alrededores \n 2. Los Angeles y sus alrededores  \n 3. Seattle y sus alrededores \n 4. Cargar un archivo diferente\n 5. Salir");
        System.out.println("<------------------------------------------->");
        System.out.print("  |=====> ");
        int opcion = 0;
        boolean isInputValid = false;

        while (!isInputValid) {
            try {
                opcion = in.nextInt();
                isInputValid = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número válido.");
                System.out.print("  |=====> ");
                in.nextLine();
            }
        }
        if(opcion>5 || opcion<=0){
            System.out.println("Introduzca un número válido.");
            System.out.print("  |=====> ");
            opcion = in.nextInt();
        }
        File selectedFile = new File("./3337050.csv");
        boolean h = true;
        CsvR csvArchivo = new CsvR(selectedFile); 


        while(h==true){
            if(opcion==1){
                System.out.println();
                System.out.println("    Cargando archivo de San Francisco y sus alrededores... ");
                System.out.println();
                selectedFile = file1;
                csvArchivo = new CsvR(selectedFile); 
                h=false;
            }else if(opcion==2){
                System.out.println();
                System.out.println("    Cargando archivo de Los Angeles y sus alrededores...");
                System.out.println();
                selectedFile = file2;
                csvArchivo = new CsvR(selectedFile); 
                h=false;
            }else if(opcion==3){
                System.out.println();
                System.out.println("    Cargando archivo de Seattle y sus alrededores...");
                System.out.println();
                selectedFile = file3;
                csvArchivo = new CsvR(selectedFile); 
                h=false;
            }else if(opcion==4){
                System.out.println();
                System.out.println("    ***RECUERDA QUE TU ARCHIVO DEBERÍA DE SER DE UN ESTADO, CON LAS COLUMNAS CORRESPONDIENTES A LOS DIFERENTES DATOS***");
                System.out.println("Ingresa la ruta completa del archivo para cargarlo:");
                String filePath = in.next();
                selectedFile = new File(filePath);
                csvArchivo = new CsvR(selectedFile); 
                h=false;
            }else if(opcion==5){
                System.exit(0);
                h=false;
            }
        }
        return csvArchivo;
    }
}

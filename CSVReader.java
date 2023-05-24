import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVReader {
    private String[][] data;
    private int numRows;
    private int numCols;

    public CSVReader(String filename) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line;
            boolean isFirstLine = true;

            while ((line = br.readLine()) != null) {
                String[] row = parseCSVLine(line);

                if (isFirstLine) {
                    numCols = row.length;
                    data = new String[10][numCols]; // Assuming a maximum of 10 rows initially
                    isFirstLine = false;
                } else {
                    if (numRows == data.length) {
                        // Increase the capacity of the data array if needed
                        String[][] newData = new String[numRows * 2][numCols];
                        System.arraycopy(data, 0, newData, 0, numRows);
                        data = newData;
                    }

                    if (row.length < numCols) {
                        row = expandRow(row);
                    }

                    data[numRows] = row;
                    numRows++;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] parseCSVLine(String line) {
        String[] row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        for (int i = 0; i < row.length; i++) {
            row[i] = row[i].replaceAll("^\"|\"$", "");
        }
        return row;
    }

    private String[] expandRow(String[] row) {
        String[] expandedRow = new String[numCols];
        int currentIndex = 0;
        boolean insideQuotes = false;
        StringBuilder sb = new StringBuilder();

        for (String value : row) {
            if (value.startsWith("\"") && !value.endsWith("\"")) {
                insideQuotes = true;
                sb.append(value).append(",");
            } else if (value.endsWith("\"") && !value.startsWith("\"")) {
                insideQuotes = false;
                sb.append(value);
                expandedRow[currentIndex] = sb.toString();
                sb.setLength(0);
                currentIndex++;
            } else if (insideQuotes) {
                sb.append(value).append(",");
            } else {
                expandedRow[currentIndex] = value;
                currentIndex++;
            }
        }

        return expandedRow;
    }

    public String[][] getData() {
        String[][] trimmedData = new String[numRows][numCols];
        System.arraycopy(data, 0, trimmedData, 0, numRows);
        return trimmedData;
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader("3337050.csv");
        String[][] data = csvReader.getData();

        // Print the data
        for (String[] row : data) {
            for (String value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
        System.out.println(data[0][1]);

    }
}

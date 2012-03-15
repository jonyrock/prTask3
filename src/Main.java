import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Need input file path.");
            return;
        }

        String[] sourceLines = getSourceLines(args[0]);

        Processor pr = new Processor(sourceLines);
        pr.contain("asd");
                
        
    }

    private static String[] getSourceLines(String sourceFile) {

        BufferedReader reader = null;

        try {

            reader = new BufferedReader(new FileReader(sourceFile));

            ArrayList<String> sourceLines = new ArrayList<String>();
            while (reader.ready()) {
                sourceLines.add(reader.readLine());
            }

            return sourceLines.toArray(new String[sourceLines.size()]);

        } catch (FileNotFoundException e) {
            System.out.println("Can't read file " + sourceFile);
        } catch (IOException e) {
            System.out.println("Error occurred while reading");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        System.exit(1);
        return null;


    }

}

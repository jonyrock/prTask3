import java.io.*;
import java.util.ArrayList;

public class Main {


    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Need input file path.");
            return;
        }
        
        char c = '1';
        System.out.println(Character.isUpperCase(c));
        
        String[] sourceLines = getSourceLines(args[0]);

        Processor pr = new Processor(sourceLines);
        printNFA(pr.toString());

        if (args.length < 2) {
            System.out.println("No word to check, see output.dot");
            return;
        }

        System.out.println(pr.contain(args[1]));


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

    private static void printNFA(String string) {

        try {

            FileWriter writer = new FileWriter("output.dot");
            writer.write(string);
            writer.close();

        } catch (Exception e) {

            System.err.println(e.getMessage());

        }
    }

}


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CSVClass {

    private static final CSVClass ourInstance = new CSVClass();

    /** Static 'instance' method */
    public static CSVClass getInstance() {

        return ourInstance;
    }

    /** A private Constructor prevents any other
     * class from instantiating.
     */
    private CSVClass() {}

    /** Other methods protected by singleton */


    protected ArrayList<String> getCSV(String p) {
        ArrayList<String> strData = new  ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(p));
            scanner.useDelimiter(",");
            while (scanner.hasNext()) {
                strData.add(scanner.next().trim());
            }
            scanner.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return strData;
    }

    //Dummy method to give the instance something to do if need be
    protected  int dummyMethod(){
        int k = 0;
        for (int i = 0; i <1000 ; i++) {
            k+=i;
        }
        return k;
    }

}





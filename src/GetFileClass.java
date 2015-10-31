import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GetFileClass {
    private static GetFileClass ourInstance = new GetFileClass();

    /** Static 'instance' method */
    public static GetFileClass getInstance() {

        return ourInstance;
    }

    /** A private Constructor prevents any other
     * class from instantiating.
     */
    private GetFileClass() {}


    /** This function creates a message box and ask user for a file to import */
    protected String getFile() {
        String filename;

        // get instances of imported class file chooser
        // file chooser will be collected by garbage collection
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("/Users/wescratty/Desktop"));
        fileChooser.setSelectedFile(new File("README.html"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            filename = fileChooser.getSelectedFile().getPath();
            JOptionPane.showMessageDialog(null, "You selected " + filename);

            return filename;
        } else if (result == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "You selected nothing.");
        } else if (result == JFileChooser.ERROR_OPTION) {
            JOptionPane.showMessageDialog(null, "An error occurred.");
        }
        return "Failed to procure file";

    }

    protected void writeFile(ArrayList<String> a){
        try
        {
            // try to get instances of imported class file writer
            File aFile = new File("/Users/wescratty/Desktop/data.csv");
            FileWriter writer = new FileWriter(aFile);


            for(String str: a) {
                writer.append(str);
                writer.append(",\n");
            }
            // close out  writer and clear ArrayList
            writer.append("0,");
            writer.flush();
            writer.close();
            a.clear();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
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
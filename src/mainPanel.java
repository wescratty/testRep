import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;


public class mainPanel extends JPanel{
    // Class level
    private JComboBox<String> tactChoice,collectorType,dataDisplayType ;//
    private  JButton Go, reset,Write_to_File,makeObjects,graphMem;
    private ArrayList<String> myStrArray = new ArrayList<>();

    public mainPanel() {

        JLabel  pathLb2;
        JPanel buttonPanel;

        //Choices for drop down menus
        String[] tactChoices = {  "Import CSVClass", "Collect","GraphClass sin" }; //
        String[] collectorChoices = { "Visual", "Audio", "GPS","Generic" }; //
        String[] dataDisplayChoices = {  "Scatter plot","Statistics" }; //

        // Label for display
        String pathString2 = " data and display results as a ";

        // New components for JPanel
        pathLb2 = new JLabel(pathString2);
        Write_to_File = new JButton("Data to file");
        Go = new JButton("Go");
        reset = new JButton("Clear graph");
        makeObjects = new JButton("makeObjects");
        graphMem = new JButton("Graph memory");
        tactChoice = new JComboBox<>(tactChoices);
        collectorType = new JComboBox<>(collectorChoices);
        dataDisplayType = new JComboBox<>(dataDisplayChoices);
        buttonPanel = new JPanel();

        // Window dressing for JPanel
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLoweredBevelBorder());
        setBackground(Color.GRAY);
        setPreferredSize(new Dimension(1200, 750));
        add(buttonPanel, BorderLayout.SOUTH);

        // add all of the components to JPanel
        buttonPanel.add(tactChoice);
        buttonPanel.add(collectorType);
        buttonPanel.add(pathLb2);
        buttonPanel.add(dataDisplayType);
        buttonPanel.add(Go);
        buttonPanel.add(reset);
        buttonPanel.add(Write_to_File);
        buttonPanel.add(makeObjects);
        buttonPanel.add(graphMem);


        // Couple buttons with action listener
        Write_to_File.addActionListener(new buttonListener());
        Go.addActionListener(new buttonListener());
        reset.addActionListener(new buttonListener());
        makeObjects.addActionListener(new buttonListener());
        graphMem.addActionListener(new buttonListener());


    }




    //**********************************************
//************************* Methods ********************************
    //**********************************************


    /** Gets selection from drop down menus and creates a code option*/
    private void activate(){

        // gets selection from drop downs and stores in a variable
        int io_Technique = tactChoice.getSelectedIndex();
        int io_Type  = collectorType.getSelectedIndex();
        int display_Type = dataDisplayType.getSelectedIndex();

        // Concatenates into a string code from the drop down boxes above and allows a selection to be made.
        String code = Integer.toString(io_Technique) + Integer.toString(io_Type) + Integer.toString(display_Type);

        switch (code){
            case "030":
                makeCSVReader();
                makeGraph();
                break;


            case "230":
                makeFunction();
                makeGraph();
                break;

            case "110":
                makeAudio();
                makeGraph();
                break;

            default:
                System.out.print("Incorrect selection:  case: "+code+" is unimplemented");
                break;
        }
    }

    /** Organizes data and makes a graph*/
    private void makeGraph(){
        revalidate();
        /** Call to singleton.getInstance() and use methods */
        GraphClass myGraphClass = GraphClass.getInstance();
        //myStringArray is sent to graph to be displayed for user
        myGraphClass.setStrData(myStrArray);
        // add the graph to the panel
        add(myGraphClass);
        // Update the JPanel with the new graph
        repaint();
    }

    /** */
    private void makeCSVReader(){
        /** Call to singleton.getInstance() and use methods */
        GetFileClass myPath = GetFileClass.getInstance();
        String file_path = myPath.getFile();

        /** Call to singleton.getInstance() and use methods */
        CSVClass csvReader = CSVClass.getInstance();
        myStrArray =   csvReader.getCSV(file_path);
    }

    /** */
    private void makeAudio(){
        /** Call to singleton.getInstance() and use methods */
        AudioClass myAudio =AudioClass.getInstance();
        byte[] myBytes = myAudio.getAudio();
        // myBytes are sent to a converter function to convert to a string array
        byteToString(myBytes);
    }

    /** Converts Audio byte string into a string array to prepare export to graph */
    private ArrayList byteToString(byte[] b){

        for (int i =0;i<b.length;i++){
            myStrArray.add(Byte.toString( b[i]));
        }

        return myStrArray;
    }

    /** Gets plots of memory usage and makes a graph */
    private void makeMemGraph(){
        MemoryUsageLogClass mem = MemoryUsageLogClass.getInstance();
        myStrArray = mem.getArray();
        makeGraph();
    }

    /** Creates plots for a sign function*/
    private void makeFunction(){
        FunctionClass myFunc = FunctionClass.getInstance();
        myStrArray = myFunc.makeSin();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
    }

    //    private void makeStats(){
//  Non goal
//    }

    /** Distinguishes between buttons and executes selection */
    private class buttonListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            /** Call to singleton.getInstance() and use methods */
            MemoryUsageLogClass memory = MemoryUsageLogClass.getInstance();
            memory.addPlot();// captures memory usage before action

            if (event.getSource() == Go) {
                /** call to method activate to get user selection */
                activate();

            }else if(event.getSource() == reset){
                /** Call to singleton.getInstance() to clear persistent data */
                GraphClass theGraphClass = GraphClass.getInstance();
                theGraphClass.clearData();
                System.gc();
                revalidate();
                repaint();


            }else if(event.getSource() == Write_to_File){
                /** Call to singleton.getInstance() and writes data to file */
                GetFileClass fileWriter = GetFileClass.getInstance();
                fileWriter.writeFile(myStrArray);


            }else if(event.getSource() == makeObjects){

                /** Call to singleton.getInstance() to see if any increase in memory and run a dummy function with
                 * instance */
                GetFileClass fileWriter = GetFileClass.getInstance();
                fileWriter.dummyMethod();
                GraphClass theGraphClass = GraphClass.getInstance();
                theGraphClass.dummyMethod();
                CSVClass theInstance = CSVClass.getInstance();
                theInstance.dummyMethod();
                AudioClass myAudio =AudioClass.getInstance();
                myAudio.dummyMethod();

            }
            else if(event.getSource() == graphMem){

                makeMemGraph();

            }
            // Displays change in memory usage from last action
            memory.changeInMem();

        }
    }
}

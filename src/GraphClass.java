import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JPanel;

public class GraphClass extends JPanel {

    private static GraphClass ourInstance = new GraphClass();

    /** Static 'instance' method */
    public static GraphClass getInstance() {

        return ourInstance;
    }

    /** A private Constructor prevents any other
     * class from instantiating.
     */
    private GraphClass() {}

    private ArrayList<String> data = new ArrayList<>();

    final int PAD = 20;
        /**Paint component draws the data onto the jPanel */
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);                                    //sends g to parent
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        int w = getWidth();                                         // gets width of parent frame
        int h = getHeight();                                        // gets height of parent frame
        // Draw axis.
        g2.draw(new Line2D.Double(PAD, PAD, PAD, h - PAD));
        // Draw axis.
        g2.draw(new Line2D.Double(PAD, h - PAD, w - PAD, h - PAD));
        double xInc = (double) (w - 2 * PAD) / (data.size() - 1);
        double scale = (double) (h - 2 * PAD) / getMax();
        // Mark data points.
        g2.setPaint(Color.red);

        for (int i = 0; i < data.size(); i++) {
            double x = PAD + i * xInc;
            double y = h - PAD - scale * Integer.parseInt(data.get(i) );    // plot data points
            g2.fill(new Ellipse2D.Double(x - 2, y - 2, 4, 4));
        }
    }
    /** Get max returns the maximum value in the array to the graph so a range can be established
         *  for setting up the graph*/
    private int getMax() {

        int max = -Integer.MAX_VALUE;

            for (String i : data)
                if (Integer.parseInt(i) > max)
                max = Integer.parseInt(i);

        return max;
    }

    /** Transfer data to local array before graphing */
    protected void setStrData(ArrayList<String> a){
        data = a;
    }
    /** clears data to reduce memory usage*/
    protected void clearData(){
        if (data.size()>1)
            data.clear();
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


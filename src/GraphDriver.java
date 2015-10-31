
import javax.swing.JFrame;
import java.lang.management.ManagementFactory;

public class GraphDriver{

 /** This main function sets up the JFrame for use by the other classes*/

    public static void main(String[] args) {
        System.out.println("From main: Memory usage: " + ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed());
        JFrame mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.add(new mainPanel());
        mainFrame.setSize(1200, 750);
        mainFrame.setLocation(200, 200);
        mainFrame.setVisible(true);
    }
}
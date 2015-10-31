import java.lang.management.ManagementFactory;
import java.util.ArrayList;

public class MemoryUsageLogClass {
    private static MemoryUsageLogClass ourInstance = new MemoryUsageLogClass();


    // Runtime initialization
    // By default ThreadSafe
    public static MemoryUsageLogClass getInstance() {

        return ourInstance;
    }


    private ArrayList<String> memData = new  ArrayList<>();

    private MemoryUsageLogClass() {
    }

    // adds the current memory usage to the array memData
    protected void addPlot(){
        memData.add(Long.toString(ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed()));
    }

    // Displays the change in memory usage
    protected void changeInMem(){
        if (memData.size()>1) {
            System.out.println("Change in usage: " + (ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed() - Integer.parseInt(memData.get(memData.size() - 1))));
            addPlot();
        }
    }

    protected ArrayList<String> getArray(){
        // make shallow copy of memData to return to proliferate data in memData
        return new ArrayList<>(memData);
    }

}








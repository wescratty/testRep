import java.util.ArrayList;

public class FunctionClass {
    private static final FunctionClass ourInstance = new FunctionClass();

    /** Static 'instance' method */
    public static FunctionClass getInstance() {

        return ourInstance;
    }

    /** A private Constructor prevents any other
     * class from instantiating.
     */
    private FunctionClass() {}

    /** Other methods protected by singleton */
    protected ArrayList<String> makeSin(){
        double theta;
        final int thousand = 1000;
        final int twoPi = 720;
        final int ratio = 90;
        ArrayList<String> data = new ArrayList<>();

        /** Loop cycles and produces a sign wave  */
        for (int i = 0; i < twoPi; i++) {
            theta = (((i) * Math.PI)/ratio );
            data.add(Integer.toString((int) (Math.sin(theta) * thousand) + thousand));
        }
        return data;
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

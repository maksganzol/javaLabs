import java.util.ArrayList;
import java.util.List;

public class ExtendedDevice extends Device{

    private List<Value> val;

    public ExtendedDevice(String name, double price, double h, double w, double l, //Конструктор с параметрами
                   boolean isOn, boolean isOk, String value,
                   int lowLim, int upLim, double error){
        super(name, price, h, w, l, isOn, isOk);
        val = new ArrayList<>();
        val.add(new Value(value, lowLim, upLim, error));
    }

    @Override
    public void print(){
        super.print();
        for(Value v: val)
            System.out.println("Values: \n" + v.name + " - lower limit: " + v.lowLim
                + " upper limit: " + v.upLim + " error: " + v.error);

    }
    private class Value{
        private int lowLim, upLim;
        private String name;
        private double error;

        public Value( String name, int lowLim, int upLim, double error) {
            this.lowLim = lowLim;
            this.upLim = upLim;
            this.name = name;
            this.error = error;
        }
    }


}

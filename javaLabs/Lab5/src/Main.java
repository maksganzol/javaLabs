import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<AbstractDevice> devs = new ArrayList<>();
        devs.add(new Device("Barometr", 33, 12, 22, 11, true, false));
        devs.add(new ExtendedDevice("Molotok", 12, 13,12,99,true, false, "Length", 12, 200, 7.8));

        for(AbstractDevice dev: devs)
            dev.print();
    }
}

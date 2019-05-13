import java.util.Scanner;

public class Device extends AbstractDevice{

    private Gabarits gab;
    private State state;
    private Scanner sc = new Scanner(System.in);
    Device(String name, double price, double h, double w, double l, boolean isOn, boolean isOk){ //Конструктор с параметрами
        super(name, price);
        gab = new Gabarits(h, w, l);
        state = new State(isOn, isOk);
    }
    @Override
    public void print() {
        System.out.println("Name: " + name + "\nPrice: " + price +
                " Height: " + gab.height + " Length: " + gab.length + " Width: " + gab.width +
                "\nOn: " + state.on + " In order: " + state.inOrder);
    }

    private class Gabarits{
        private double height, width, length;
        public Gabarits(double h, double w, double l){
            height = h;
            width = w;
            length = l;
        }
    }

    private class State{
        private boolean on, inOrder;
        public State(boolean isOn, boolean isOk){
            on = isOn;
            inOrder = isOk;
        }
    }

}

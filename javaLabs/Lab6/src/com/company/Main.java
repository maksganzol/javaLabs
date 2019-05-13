package com.company;

public class Main {

    public static void main(String[] args) {
	    Set set = new ProductSet(3);
        try {
            set.add(new Product("Maslo", "Maxim", 110, 12, 1000, 12));
            set.add(new Product("Moloko", "Kolya", 10, 12, 76, 12));
            set.add(new Product("Pivo", "Misha", 11, 12, 32, 12));
            set.printItems();
            System.out.println("Product with minimal profit: " + set.minProfit());
        } catch (ArrayOwerflow arrayOwerflow) {
             System.out.println(arrayOwerflow.getMessage());
        } catch (ShortProducerName shortProducerName) {
            System.out.println(shortProducerName.getMessage());
        } catch (ShortProductName shortProductName) {
            System.out.println(shortProductName.getMessage());
        }


    }
}

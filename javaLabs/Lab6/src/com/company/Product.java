package com.company;

public class Product {
    private String name, producer;
    private int count = 0;
    private double wholeSale, retale;
    private float varranty;

    public Product(String name, String producer, int count, double wholeSale, double retale, float varranty)
            throws ShortProductName, ShortProducerName{
        if(name.length() < 3)
            throw new ShortProductName(name);
        if(producer.length() < 5)
            throw new ShortProducerName(producer);
        this.name = name;
        this.producer = producer;
        this.count = count;
        this.wholeSale = wholeSale;
        this.retale = retale;
        this.varranty = varranty;
        System.out.println(name + " successfully created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getWholeSale() {
        return wholeSale;
    }

    public void setWholeSale(double wholeSale) {
        this.wholeSale = wholeSale;
    }

    public double getRetale() {
        return retale;
    }

    public void setRetale(double retale) {
        this.retale = retale;
    }

    public float getVarranty() {
        return varranty;
    }

    public void setVarranty(float varranty) {
        this.varranty = varranty;
    }

    public double getProfit(){
        return getCount() * (getRetale() - getWholeSale());
    }
    @Override
    public String toString(){
        return "Name: " + name + ", producer: " + producer + "\nwholesale: " + wholeSale + ", retale: " + retale + ", varranty: " + varranty;
    }
}

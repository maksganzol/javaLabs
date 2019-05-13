package com.company;

public class ProductSet implements Set{

    private int countItems = 0;
    private Product[] prodList;

    public ProductSet(int size){
        prodList = new Product[size];
    }
    @Override
    public void add(Product prod) throws ArrayOwerflow {
        countItems++;
        if(countItems > prodList.length)
            throw new ArrayOwerflow(prodList.length);
        prodList[countItems - 1] = prod;
        System.out.println("Product successfully added");
    }

    @Override
    public void printItems() {
        for(Product prod: prodList)
            if(prod!=null)
                System.out.println(prod);
    }

    @Override
    public String maxVarrantyTerm() {
        Product maxVar = null;
        for(Product prod: prodList)
            if(maxVar == null || prod.getVarranty() > maxVar.getVarranty())
                maxVar = prod;
        return maxVar.getName();
    }

    @Override
    public String minProfit() {
        if(prodList[0] == null)
            return "Array id empty";
        Product minProf = null;
        for(Product prod: prodList)
            if(minProf == null || prod.getProfit() < minProf.getProfit())
                minProf = prod;
        return minProf.getName() + ", " + minProf.getProducer();
    }

}

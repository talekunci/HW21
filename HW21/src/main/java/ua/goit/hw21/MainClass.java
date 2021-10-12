package ua.goit.hw21;

import ua.goit.hw21.shop.ShopDataBase;
import ua.goit.hw21.shop.Ware;

public class MainClass {
    public static void main(String[] args) {
        ShopDataBase dataBase = ShopDataBase.getInstance();
        dataBase.add(new Ware("A", 2.));
        dataBase.add(new Ware("B", 1., 3, .75));

        CostCalculator calculator = new CostCalculator();
        System.out.println("result: " + calculator.calculateTotalCost("AABBB"));
        calculator.calculateTotalCostAndPrint("AABBB");
        calculator.calculateTotalCostAndPrint("AA");
        calculator.calculateTotalCostAndPrint("AAA");
        calculator.calculateTotalCostAndPrint("BBB");
    }
}

package ua.goit.hw21;

import ua.goit.hw21.shop.*;

import java.util.HashMap;
import java.util.Map;

public class CostCalculator {

    private final ShopDataBase dataBase;
    private Map<String, Integer> cartMap;

    public CostCalculator(ShopDataBase dataBase) {
        this.dataBase = dataBase;
    }

    public CostCalculator() {
        dataBase = ShopDataBase.getInstance();
    }

    private void initCart(String cart) {
        cartMap = new HashMap<>();

        for (int i = 0; i < cart.length(); i++) {
            String wareName = String.valueOf(cart.charAt(i));
            if (!dataBase.has(wareName)) continue;

            if (cartMap.containsKey(wareName)) {
                cartMap.put(wareName, cartMap.get(wareName) + 1);
            } else {
                cartMap.put(wareName, 1);
            }
        }
    }

    public double calculateTotalCostAndPrint(String cart) {
        double result = 0;
        System.out.println("result: " + (result = calculateTotalCost(cart)));

        return result;
    }

    public double calculateTotalCost(String cart) {
        initCart(cart);
        double result = 0;

        for (Map.Entry<String, Integer> pair : cartMap.entrySet()) {
            result += calculateCost(pair.getKey(), pair.getValue());
        }

        return result;
    }

    private double calculateCost(String name, int count) {
        double result = 0;

        if (cartMap.containsKey(name)) {
            Ware ware = dataBase.get(name);

            int wareSaleCount = ware.getSaleCount();            //акционная цена для N количества
            if (wareSaleCount >= 1 && count >= wareSaleCount) {
                if (wareSaleCount >= 1) {         // Значит товар всегда по скидке
                    result = count * ware.getSaleCost();
                } else {
                    int saleCount = 0;
                    while (saleCount + wareSaleCount < count) {
                        saleCount += wareSaleCount;
                    }

                    result += saleCount * ware.getSaleCost();
                    result += (count - saleCount) * ware.getCost();
                }
            } else {    // Скидки нет :(
                result = count * ware.getCost();
            }
        }
        return result;
    }

    public ShopDataBase getDataBase() {
        return dataBase;
    }
}

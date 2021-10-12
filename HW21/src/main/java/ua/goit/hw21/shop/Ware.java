package ua.goit.hw21.shop;

public class Ware {

    private final String name;
    private final double cost;
    private final double saleCost;
    private final int saleCount;

    public Ware(String name, double cost) {
        this(name, cost, -1, 0);
    }

    public Ware(String name, double cost, int saleCount, double saleCost) {
        this.name = name;
        this.cost = cost;
        this.saleCount = saleCount;
        this.saleCost = saleCost;
    }

    public String getName() {
        return name;
    }

    public double getCost() {
        return cost;
    }

    public int getSaleCount() {
        return saleCount;
    }

    public double getSaleCost() {
        return saleCost;
    }

    @Override
    public String toString() {
        return "Ware{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", saleCost=" + saleCost +
                ", saleCount=" + saleCount +
                '}';
    }
}

package ua.goit.hw21.shop;

import java.util.HashMap;
import java.util.Map;

public class ShopDataBase {

    private final Map<String, Ware> dataBase = new HashMap<>();
    private static ShopDataBase instance;

    private ShopDataBase() {
    }

    public static ShopDataBase getInstance() {
        return instance == null ? instance = new ShopDataBase() : instance;
    }

    public void add(Ware ware) {
        dataBase.put(ware.getName(), ware);
    }

    public Ware get(String name) {
        return dataBase.get(name);
    }

    public boolean has(String name) {
        return dataBase.containsKey(name);
    }

    public int size(){
        return dataBase.size();
    }
}

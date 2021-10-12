package ua.goit.hw21;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import ua.goit.hw21.shop.ShopDataBase;
import ua.goit.hw21.shop.Ware;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.mockito.Mockito.when;

public class CostCalculatorTest {
    public static ShopDataBase mockDataBase;
    public static CostCalculator calculator;

    @BeforeClass
    public static void initClass(){
//          Создание "заглушки"
        mockDataBase = Mockito.mock(ShopDataBase.class);
//          Создание объекта "калькулятор"
        calculator = new CostCalculator(mockDataBase);

//          Настройки заглушки
        when(mockDataBase.get("A")).thenReturn(new Ware("A", 3, 3, 2.75));
        when(mockDataBase.get("B")).thenReturn(new Ware("B", 8, 2, 6.2));
        when(mockDataBase.get("C")).thenReturn(new Ware("C", .75, 4, .5));
        when(mockDataBase.has("A")).thenReturn(true);
        when(mockDataBase.has("B")).thenReturn(true);
        when(mockDataBase.has("C")).thenReturn(true);
    }

    @Test
    public void calculateCostTest(){
//          Тестирование работы метода подсчёта стоимости корзины
        Assert.assertEquals("calculateCost(\"AB\") Test", 11., calculator.calculateTotalCost("AB"), 0.0);
        Assert.assertEquals("calculateCost(\"ABC\") Test", 11.75, calculator.calculateTotalCost("ABC"), 0.0);
        System.out.println("new CostCalculator.calculateCost(String cart) works correctly");
    }

    @Test
    public void calculateCostAndPrintTest() {
//        Проверка корректности вывода результата в консоль
        PrintStream consoleStream = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream stream = new PrintStream(outputStream);
        System.setOut(stream);

//          Вызов проверяемого метода
        calculator.calculateTotalCostAndPrint("AB");

        stream.flush();
        System.setOut(consoleStream);
        stream.close();

//          Тесты
        Assert.assertEquals("outputStream data is wrong", "result: 11.0\r\n", outputStream.toString());
        Assert.assertNotEquals("outputStream data is wrong", "", outputStream.toString());

//          Если мы сюда дошли, значит все работает корректно :)
        System.out.println("calculateCostAndPrintTest() works correctly");
    }

    @Test
    public void dataBaseGetTest() {
//          Тестирование работы метода get()
        Assert.assertEquals("dataBaseGet EqualsTest", mockDataBase.get("A"), calculator.getDataBase().get("A"));
        Assert.assertNotEquals("dataBaseGet NotEqualsTest", mockDataBase.get("A"), calculator.getDataBase().get("B"));
        Assert.assertNull("dataBaseGet assert 'null'", mockDataBase.get("D"));
        System.out.println("ShopDataBase.get() works correctly");
    }

    @Test
    public void getDataBaseTest() {
//          Тестирование "заглушки" Mockito
        Assert.assertSame("getDataBase() SameTest", mockDataBase, calculator.getDataBase());
        Assert.assertNotSame("getDataBase() NorSameTest", mockDataBase, ShopDataBase.getInstance());
        System.out.println("new CostCalculator.getDataBase() works correctly");
    }

    @Test
    public void getInstanceTest() {
//          Тестирование работы логики синглтона
        Assert.assertSame("getInstance() SameTest", ShopDataBase.getInstance(), ShopDataBase.getInstance());
        Assert.assertNotSame("getInstance() NotSameTest", ShopDataBase.getInstance(), mockDataBase);
        System.out.println("ShopDataBase.getInstance() works correctly");
    }

    @Test
    public void mockHasTest() {
//          Тестирование работы Mockito
        Assert.assertTrue("mockHasTest() Expected True", mockDataBase.has("A"));
        Assert.assertFalse("mockHasTest() Expected False", mockDataBase.has("D"));
        System.out.println("mockDataBase.has() works correctly");
    }
}

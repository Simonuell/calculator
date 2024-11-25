package main;
import java.math.BigInteger;

public class Calculator {
    // Функция калькулятора
    public static String calculate(int a, int b, String op, int base)
    {
        BigInteger result = null;
        // Преобразуем числа в BigInteger для поддержки больших значений
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigB = BigInteger.valueOf(b);
        switch(op)
        {
            case "+":
                result = bigA.add(bigB);
                break;
            case "-":
                result = bigA.subtract(bigB);
                break;
            case "*":
                result = bigA.multiply(bigB);
                break;
            case "/":
                if (!bigB.equals(BigInteger.ZERO)) {
                    result = bigA.divide(bigB);
                } else {
                    return "деление на ноль";
                }
                break;
            default:
                return "Неверный оператор";
        }
        // Переводим результат в указанную систему счисления
        return result.toString(base).toUpperCase();
    }
    // Тестовая функция main
    public static void main(String[] args) 
    {
        System.out.println(calculate(100, 40, "-", 16)); // Ожидаемый результат: "30"
    }
    
}

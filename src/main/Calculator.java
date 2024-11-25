package main;
public class Calculator {

    public static void main(String[] args) {
        String firstNumber = "32";
        int firstBase = 8;
        String secondNumber = "30";
        int secondBase = 10;
        String operation = "+";
        int resultBase = 2; 

        String result = calculate(firstNumber, firstBase, secondNumber, secondBase, operation, resultBase);

        System.out.println("Result: " + result);
    }

    public static String calculate(String num1, int base1, String num2, int base2, String operation, int resultBase) {
        int result = 0;
        int number1 = Integer.parseInt(num1, base1);
        int number2 = Integer.parseInt(num2, base2);

        switch (operation) {
            case "+" -> {
                result = number1 + number2;
                return Integer.toString(result, resultBase);
            }
            case "-" -> {
                result = number1 - number2;
                return Integer.toString(result, resultBase);
            }
            case "*" -> {
                result = number1 * number2;
                return Integer.toString(result, resultBase);
            }
            case "/" -> {
                if (number2 == 0) {
                    return "Деление на ноль";
                }
                result = number1 / number2;
                return Integer.toString(result, resultBase);
            }
            default -> throw new IllegalArgumentException("Operation not valid");
        }
    }

}
// что было 
// задача 
// скриншот с результатами
// приложение код программмы
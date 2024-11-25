package test;

import org.junit.Assert;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;



public class CalculatorTest 
{
    @ParameterizedTest
    @CsvSource(value = {
            "1010, 2, A, 16, +, 10, 20",
            "32, 8, 30, 10, +, 2, 111000",
            "B, 16, 32, 8, +, 16, 25",
            "1010, 2, A, 16, +, 10, 20"
    }, ignoreLeadingAndTrailingWhitespace = true)

    public void testSummOperation(String num1, int base1, String num2, int base2, String operation, int resultBase, String result) {
        Assert.assertEquals(result, Calculator.calculate(num1, base1, num2, base2, operation, resultBase));          
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1010, 2, A, 16, -, 10, 0",
        "32, 8, 30, 10, -, 2, -100",
        "B, 16, 32, 8, -, 16, -f",
        "1010, 2, A, 16, -, 10, 0"
    }, ignoreLeadingAndTrailingWhitespace = true)

    public void testMinusOperation(String num1, int base1, String num2, int base2, String operation, int resultBase, String result) {
        Assert.assertEquals(result, Calculator.calculate(num1, base1, num2, base2, operation, resultBase));          
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1010, 2, A, 16, *, 10, 100",
        "32, 8, 30, 10, *, 2, 1100001100",
        "B, 16, 32, 8, *, 16, 11e",
        "1010, 2, A, 16, *, 10, 100"
    }, ignoreLeadingAndTrailingWhitespace = true)

    public void testMuliplyOperation(String num1, int base1, String num2, int base2, String operation, int resultBase, String result) {
        Assert.assertEquals(result, Calculator.calculate(num1, base1, num2, base2, operation, resultBase));          
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1010, 2, A, 16, /, 10, 1",
        "32, 8, 30, 10, /, 2, 0",
        "B, 16, 32, 8, /, 16, 0",
        "1010, 2, A, 16, /, 10, 1"
    }, ignoreLeadingAndTrailingWhitespace = true)

    public void testDivideOperation(String num1, int base1, String num2, int base2, String operation, int resultBase, String result) {
        Assert.assertEquals(result, Calculator.calculate(num1, base1, num2, base2, operation, resultBase));          
    }

    @ParameterizedTest
    @CsvSource(value = {
        "1010, 2, 0, 16, /, 10, 1",
        "32, 8, 0, 10, /, 2, 0",
        "B, 16, 0, 8, /, 16, 0",
        "1010, 2, 0, 16, /, 10, 1"
    }, ignoreLeadingAndTrailingWhitespace = true)

    public void testDivideZEROOperation(String num1, int base1, String num2, int base2, String operation, int resultBase, String result) {
        Assert.assertEquals("Деление на ноль", Calculator.calculate(num1, base1, num2, base2, operation, resultBase));          
    }

    @TestFactory
    public Collection<DynamicTest> runDynamicTestCalculators() throws Exception
    {
        Collection<DynamicTest> dList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Admin\\OneDrive\\Рабочий стол\\СОФТ\\Автоматизация тестирования\\src\\test\\testnum.csv"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] values = line.split(", ");
                org.junit.jupiter.api.function.Executable exec = () -> {
                    Assert.assertEquals(values[6], Calculator.calculate(values[0], Integer.parseInt(values[1]), values[2], Integer.parseInt(values[3]), values[4], Integer.parseInt(values[5])));
                };
                String testname = values[0] + "_" + values[1] + "_" + values[2] + "_" + values[3];
                DynamicTest dtest = DynamicTest.dynamicTest(testname, exec);
                dList.add(dtest);      
            }
        }
        return dList;
    }
}

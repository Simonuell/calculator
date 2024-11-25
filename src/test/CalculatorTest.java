package test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import main.Calculator;

public class CalculatorTest 
{
    @ParameterizedTest
    @CsvSource(value = {
            "10, 40, +, 10, 50",
            "10, 40, +, 2, 110010",
            "10, 40, +, 8, 62",
            "10, 40, +, 16, 32"
    }, ignoreLeadingAndTrailingWhitespace = true)
    public void testSummOperation(int a, int b, String op, int base, String result) throws Exception {
        Assert.assertEquals(result, Calculator.calculate(a, b, op, base));          
    }

    @ParameterizedTest
    @CsvSource(value = {
            "100, 40, -, 10, 60",
            "100, 40, -, 2, 111100",
            "100, 40, -, 8, 74",
            "100, 40, -, 16, 3C"
    }, ignoreLeadingAndTrailingWhitespace = true)
    public void testMinusOperation(int a, int b, String op, int base, String result) throws Exception {
        Assert.assertEquals(result, Calculator.calculate(a, b, op, base));          
    }


    @TestFactory
    public Collection<DynamicTest> runDynamicTestCalculators() throws Exception
    {
        Collection<DynamicTest> dList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/test/testnum.csv"))) 
        {
            String line;
            while ((line = br.readLine()) != null) 
            {
                String[] values = line.split(",");
                
                // @SuppressWarnings("deprecation")
                // Calculator calc = (Calculator)Class.forName("sis."+values[0]).newInstance();
                // Method method = calc.getClass().getMethod(values[1], String.class);
                
                org.junit.jupiter.api.function.Executable exec = () -> {
                    Assert.assertEquals(values[4], Calculator.calculate(Integer.parseInt(values[0]), Integer.parseInt(values[1]), values[2], Integer.parseInt(values[3])));
                    // method.invoke(calc,values[2]);
                    // assertEquals(values[3],calc.getString());
                };
                String testname = values[0] + "_" + values[1] + "_" + values[2] + "_" + values[3];
                DynamicTest dtest = DynamicTest.dynamicTest(testname, exec);
                dList.add(dtest);      
            }
        }
        return dList;
    }
}

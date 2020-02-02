package pages;

public class Calculator {
    public int sum(int ... arguments){
        int result = 0;
        for (int argument : arguments){
            result += argument;
        }
        return result;
    }

    public int substraction(int ... arguments){
        int result = arguments[0];
        for (int i = 1; i < arguments.length; i++){
            result = result - arguments[i];
        }
        return result;
    }

    public int multiplication(int ... arguments){
        int result = 1;
        for (int argument : arguments){
            result *= argument;
        }
        return result;
    }

    public int division(int ... arguments){
        int result = arguments[0];
        for (int i = 1; i < arguments.length; i++){
            result = result/arguments[i];
        }
        return result;
    }
}

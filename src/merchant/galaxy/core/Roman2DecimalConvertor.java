package merchant.galaxy.core;

import merchant.galaxy.utils.RulesUtils;

public class Roman2DecimalConvertor {

    public float romanToDecimal(String romanNumber) {

        float decimal = 0;
        float lastNumber = 0;
        char[] romanNumeral = romanNumber.toUpperCase().toCharArray();

        for (int x = 0; x <romanNumeral.length; x++) {
            Character convertToDecimal = romanNumeral[x];

            switch (convertToDecimal) {
                case 'L':
                    RulesUtils.checkLiteralCountValidity(convertToDecimal);
                    decimal = computerDecimal(50, lastNumber, decimal);
                    lastNumber = 50;
                    break;

                case 'I':
                    RulesUtils.checkLiteralCountValidity(convertToDecimal);
                    decimal = computerDecimal(1, lastNumber, decimal);
                    lastNumber = 1;
                    break;

                case 'V':
                    RulesUtils.checkLiteralCountValidity(convertToDecimal);
                    decimal = computerDecimal(5, lastNumber, decimal);
                    lastNumber = 5;
                    break;

                case 'X':
                    RulesUtils.checkLiteralCountValidity(convertToDecimal);
                    decimal = computerDecimal(10, lastNumber, decimal);
                    lastNumber = 10;
                    break;

                case 'M':
                    RulesUtils.checkLiteralCountValidity(convertToDecimal);
                    decimal = computerDecimal(1000, lastNumber, decimal);
                    lastNumber = 1000;
                    break;

                case 'D':
                    RulesUtils.checkLiteralCountValidity(convertToDecimal);
                    decimal = computerDecimal(500, lastNumber, decimal);
                    lastNumber = 500;
                    break;

                case 'C':
                    RulesUtils.checkLiteralCountValidity(convertToDecimal);
                    decimal = computerDecimal(100, lastNumber, decimal);
                    lastNumber = 100;
                    break;
            }
        }
        RulesUtils.resetLiteralsCounter();
        return decimal;
    }

    public float computerDecimal(float decimal, float lastNumber, float lastDecimal) {
        if (lastNumber > decimal) {
            return RulesUtils.subtractionLogic(lastDecimal, decimal, lastNumber);
        } else {
            return lastDecimal + decimal;
        }
    }
}

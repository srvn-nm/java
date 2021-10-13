package com.company;

/***
 * This class will show the numbers in digital form.
 * @author Sarvin Nami
 */
public class NumberDisplay {
    private int limit;
    private int value;

    /**
     * Ths constructor will set the fields.
     * @param rollOverLimit
     */
    public NumberDisplay(int rollOverLimit)
    {
        limit = rollOverLimit;
        value = 0;
    }

    /**
     * This method will return the value of number.
     * @return value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * This method will return the value of number in a way we can show it digitally.
     * @return value
     */
    public String getDisplayValue()
    {
        if(value < 10) {
            return "0" + value;
        }
        else {
            return "" + value;
        }
    }

    /**
     * This method will set a value.
     * @param replacementValue
     */
    public void setValue(int replacementValue)
    {
        if((replacementValue >= 0) && (replacementValue < limit)) {
            value = replacementValue;
        }
    }

    /**
     * This method will increase the value.
     */
    public void increment()
    {
        value = (value + 1) % limit;
    }
}

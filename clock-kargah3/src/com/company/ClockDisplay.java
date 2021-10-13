package com.company;

/***
 * This method will save and show the information of a digital clock.
 * @author Sarvin Nami
 */
public class ClockDisplay {
    private NumberDisplay hours;
    private NumberDisplay minutes;
    private NumberDisplay seconds;
    private String displayString;    // simulates the actual display

    /**
     * This constructor will set fields.
     */
    public ClockDisplay()
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        updateDisplay();
    }

    /**
     * This method will set the given time.
     * @param hour
     * @param minute
     * @param second
     */
    public void setTime(int hour, int minute, int second)
    {
        hours.setValue(hour);
        minutes.setValue(minute);
        seconds.setValue(second);
        updateDisplay();
    }

    /**
     * This constructor will set the fields with the given ones.
     * @param hour
     * @param minute
     * @param second
     */
    public ClockDisplay(int hour, int minute, int second)
    {
        hours = new NumberDisplay(24);
        minutes = new NumberDisplay(60);
        seconds = new NumberDisplay(60);
        setTime(hour, minute , second);
    }

    /**
     * This method will update the fields while they are increasing.
     */
    public void timeTick()
    {
        seconds.increment();
        if(seconds.getValue() == 0) {
            minutes.increment();
            if(minutes.getValue() == 0) {
                hours.increment();
            }
        }
        updateDisplay();
    }

    /**
     * This method will return the display value to show.
     * @return displayString
     */
    public String getTime()
    {
        return displayString;
    }

    /**
     * This method will update the display with the updated values.
     */
    private void updateDisplay()
    {
        displayString = hours.getDisplayValue() + ":" +
                minutes.getDisplayValue() + ":" + seconds.getDisplayValue();
    }
}

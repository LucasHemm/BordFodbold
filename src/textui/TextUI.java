/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textui;

/**
 *
 * @author Tobias
 */
public interface TextUI
{
    public void print(String s);
    public String get();
    public void clear();
    
    default public void println(String s)
    {
        print(s + System.lineSeparator());
    }
    
    default public int getInteger()
    {
        while(true)
        {
            String input = get();
            try
            {
                return Integer.parseInt(input);
            }
            catch(NumberFormatException exp)
            {
                println("You must enter an integer!");
            }
        }
    }
    
    default public int getInteger(int min, int max)
    {
        if(min > max)
        {
            throw new IllegalArgumentException("min > max, min: " + min + " max:" + max);
        }
        while(true)
        {
            int res = getInteger();
            if(res >= min && res <= max)
            {
                return res;
            }
            println("You must enter an integer between " + min + " and " + max + ", both inclusive.");
        }
    }
    
    default public int select(String header, Iterable<String> choices, String footer)
    {
        println(header);
        int count = 0;
        for(String s : choices)
        {
            println("    " + (++count) + " - " + s);
        }
        println(footer);
        return getInteger(1, count)-1;
    }

    default public int select(String header, String[] choices, String footer)
    {
        println(header);
        int count = 0;
        for(String s : choices)
        {
            println("    " + (++count) + " - " + s);
        }
        println(footer);
        return getInteger(1, count)-1;
    }
}

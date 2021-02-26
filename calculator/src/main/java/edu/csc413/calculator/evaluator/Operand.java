package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {

    private int intOperand;
    /**
     * construct operand from string token.
     */
    public Operand(String token){
        /*try{//try parsing token into an integer
            this.intOperand = Integer.parseInt(token);
        }catch(Exception e) {//if try throws an exception return false as its not an integer
            System.out.println("Exception thrown in Operand class, token could not be parsed");
        }*/
        this.intOperand = Integer.parseInt(token);
    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        this.intOperand = value;
    }

    /**
     * return value of operand
     */
    public int getValue()
    {
        return this.intOperand;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token)
    {
        int number;
        try{//try parsing token into an integer
            number = Integer.parseInt(token);
        }catch(Exception e) {//if try throws an exception return false as its not an integer
            System.out.println("Exception thrown in Operand class, token could not be parsed");
            return false;
        }
        return true;//return true if the try block works
    }
}

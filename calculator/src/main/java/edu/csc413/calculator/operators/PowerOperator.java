package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator {
    private final int powerPriority = 3;
    /**
     * retrieve the priority of an Operator
     * @return priority of an Operator as an int
     */
    public int priority(){
        return(this.powerPriority);
    }

    /**
     * Abstract method to execute an operator given two operands.
     * @param operandOne first operand of operator
     * @param operandTwo second operand of operator
     * @return an operand of the result of the operation.
     */
    public Operand execute(Operand operandOne, Operand operandTwo){
        Operand returnOp = new Operand((int)Math.pow(operandOne.getValue() ,operandTwo.getValue()));
        return returnOp;
    }

    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     */
    public static Operator getOperator(String token)
    {
        //super.getOperator(token);
        //cant do the above cause of static keyword
        //so i guess i just copy and paste the same method in each getOperator method
        return null;
    }


    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check(String token) {
        return false; //use hashmap to check token
    }
}

package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.exceptions.InvalidTokenException;
import edu.csc413.calculator.operators.*;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {

  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer expressionTokenizer;
  private final String delimiters = " +/*-^()";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }

  public int evaluateExpression(String expression ) throws InvalidTokenException {
    String expressionToken;

    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.expressionTokenizer = new StringTokenizer( expression, this.delimiters, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators



    while ( this.expressionTokenizer.hasMoreTokens() )
    {
      // filter out spaces
      if ( !( expressionToken = this.expressionTokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( expressionToken )) {
          operandStack.push( new Operand( expressionToken ));
        } else {
          if ( ! Operator.check( expressionToken )) {
            throw new InvalidTokenException(expressionToken);
          }

          if(expressionToken.equals(")"))
          //for when current token is closed parenthesis
          {
            //this "if' statement is to process the stacks when the token is a ")"
            if (expressionToken.equals(")"))
            {
              boolean openParenthesisNotReached = true;
              while (openParenthesisNotReached)
              {
                //this "if" block inside the while loop is
                if(operatorStack.peek().priority() == 0) //if the priority is 0 that means its a closed parenthesis, "("
                {
                  operatorStack.pop();//gets rid of the extra open parenthesis
                  openParenthesisNotReached = false;
                }
                else
                {
                  processAnOperator();
                }
              }
            }
          }
          else if(expressionToken.equals("("))
          //for when current token is open parenthesis
          {
            operatorStack.push(Operator.getOperator(expressionToken));
          }
          else
          //Operator is one of these "+/*-^" so store it as a new operator to be push onto Operatorstack
          {
            // TODO Operator is abstract - these two lines will need to be fixed:
            // The Operator class should contain an instance of a HashMap,
            // and values will be instances of the Operators.  See Operator class
            // skeleton for an example.

            Operator newOperator = Operator.getOperator(expressionToken);

            while (!operatorStack.empty() && operatorStack.peek().priority() >= newOperator.priority())
            //if top of the stack operator's priority is greater than new operator's priority
            //process the current top of the operator stack
            {

              // note that when we eval the expression 1 - 2 we will
              // push the 1 then the 2 and then do the subtraction operation
              // This means that the first number to be popped is the
              // second operand, not the first operand - see the following code
              processAnOperator();
            }

            operatorStack.push( newOperator );
          }
        }
      }
    }

    // Control gets here when we've picked up all of the tokens; you must add
    // code to complete the evaluation - consider how the code given here
    // will evaluate the expression 1+2*3
    // When we have no more tokens to scan, the operand stack will contain 1 2
    // and the operator stack will have + * with 2 and * on the top;
    // In order to complete the evaluation we must empty the stacks,
    // that is, we should keep evaluating the operator stack until it is empty;
    // Suggestion: create a method that processes the operator stack until empty.

    while(!operatorStack.empty()) //processes what is left in the operator stack
    {
      processAnOperator();
    }

    Operand grandTotal = operandStack.pop(); //pops the last operand which is the total evaluation
    return grandTotal.getValue(); //returns its value
  }

  private void processAnOperator(){
    Operator operatorFromStack = operatorStack.pop();
    Operand operandTwo = operandStack.pop();
    Operand operandOne = operandStack.pop();
    Operand result = operatorFromStack.execute( operandOne, operandTwo );
    operandStack.push( result );
  }
}

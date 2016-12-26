import java.util.Scanner;

/**Calculates with RPN.
 * @author JZ
 *
 */
public final class Calc {
    /**Empty constructor.
     */
    private void Calc(){
    }

    /**main method of program.
     * @param args string array from command line
     */
    public static void main(String[] args){
        Stack<Integer> list = new ArrayStack<Integer>();
        Scanner sc = new Scanner(System.in);
        String input = "";
        boolean stop = false;
        while (!stop && sc.hasNext()) {
            //loop only exits when "!" or eof is typed
            input = sc.next();
            if ("?".equals(input)) {
                System.out.println(list.toString());
            }

            else if (("*").equals(input) && (!list.empty())) {
                //use try catch to see if operation is
                //valid and then pushes the original
                //value back if the operation was invalid.
                int a = list.top();
                try {
                    list.pop();
                    int b = list.top();
                    int op = a * b;
                    list.pop();
                    list.push(op);
                }
                catch (EmptyException e) {
                    System.err.println("?Cannot do *");
                    list.push(a);
                }
            }
            else if (("/").equals(input) && (!list.empty())) {
                //use try catch to see if operation is
                //valid and then pushes the original
                //value back if the operation was invalid.
                //catches arithmeticexception to avoid / by 0
                int a = list.top();
                try {
                    list.pop();
                    int b = list.top();
                    int op = a / b;
                    list.pop();
                    list.push(op);
                }
                catch (EmptyException | ArithmeticException e) {
                    System.err.println("?Cannot Do /");
                    list.push(a);
                }
            }
            else if (("%").equals(input) && (!list.empty())) {
                //use try catch to see if operation is
                //valid and then pushes the original
                //value back if the operation was invalid.
                //catches arithmeticexception to avoid % by 0
                int a = list.top();
                try {
                    list.pop();
                    int b = list.top();
                    int op = a % b;
                    list.pop();
                    list.push(op);
                }
                catch (EmptyException | ArithmeticException e) {
                    System.err.println("?Cannot do %");
                    list.push(a);
                }
            }
            else if (("+").equals(input) && (!list.empty())) {
                //use try catch to see if operation is
                //valid and then pushes the original
                //value back if the operation was invalid.
                int a = list.top();
                try {
                    list.pop();
                    int b = list.top();
                    int op = a + b;
                    list.pop();
                    list.push(op);
                }
                catch (EmptyException e) {
                    System.err.println("?Cannot do +");
                    list.push(a);
                }
            }
            else if (("-").equals(input) && (!list.empty())) {
                //use try catch to see if operation is
                //valid and then pushes the original
                //value back if the operation was invalid.
                int a = list.top();
                try {
                    list.pop();
                    int b = list.top();
                    int op = a - b;
                    list.pop();
                    list.push(op);
                }
                catch (EmptyException e) {
                    System.err.println("?Cannot do -");
                    list.push(a);
                }
            }
            else if ((".").equals(input) && (!list.empty())) {
                System.out.println(list.top());
                list.pop();
            }
            else if (("!").equals(input)) {
                stop = true;
            }
            else if (checknum(input)) {
                list.push(Integer.parseInt(input));
            }
        }
    }

    /**Checks if a value is numeric.
     * @param a takes in a string
     * @return returns a boolean
     */
    public static boolean checknum(String a) {
        try {
            Integer.parseInt(a);
            return true;
        }
        catch (NumberFormatException e) {
            System.err.println("?Cannot Use input");
            return false;
        }
    }

}

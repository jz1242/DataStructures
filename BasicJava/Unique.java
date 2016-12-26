
/**This program finds unique numbers.
 * @author Jason Zhang(jzhan127)
 *     Program takes integer values and finds the
 *     Unique values within the args
 *
 */
public final class Unique {
    /**empty constructor.
 */
    private Unique(){ }
    /**main method.
     * @param args
     * method finds the unique numbers
     */

    public static void main(String[] args) {
        int check = 0;
        int hold = 0;
        for (int i = 0; i < args.length; i++) {
            if (intCheck(args[i])) {
                System.out.println(Integer.parseInt(args[i]));
                check = Integer.parseInt(args[i]);
                for (int j = i; j < args.length; j++) {
                    if (intCheck(args[j])) {
                        hold = Integer.parseInt(args[j]);
                    }
                    if (check == hold) {
                        args[j] = "s";
                    }
                }
            }
        }

    }

    /** method checks if input is integer.
     * @param val (string)
     * @return boolean value
     */
    public static boolean intCheck(String val) {
        try {
            Integer.parseInt(val);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

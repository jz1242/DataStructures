/**
    This is a sample-solution for Problem 1 on Assignment 1. It doesn't use
    anything fancy, just stuff you should have known from your previous Java
    courses. (Well, and some stuff checkstyle would have told you about.)
*/
import java.util.Scanner;
/**program finds the unique values.
 */

public final class Unique {
    //array of unique numbers
    /**generic array type of integer data.
     */
    private static Array<Integer> data;
    // how many slots in data are used?
    /**counts how many used numbers.
     */
    private static int used;

    // make checkstyle happy
    /**empty constructor.
     */
    private Unique() {
    }

    // position of given value in data[], -1 if not found
    /**finds the specific value.
     * @param value inputed value to check
     * @return the value itself
     */
    private static int find(int value) {
        for (int i = 0; i < used; i++) {
            if ((int) data.get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    // insert value into data[] if not already present
    /**inserts the value desired into array.
     * @param value that we want
     */
    private static void insert(int value) {
        int position = find(value);
        if (position < 0) {
            data.put(used, value);
            used += 1;
        }
    }

    /**
    * Print only unique integers out of given command line arguments.
    * @param args
    *            Command line arguments.
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // worst case: args.length distinct numbers
        data = new SimpleArray<Integer>(2, 0);
        Array<Integer> hold;
        int count = 1;
        // process args and insert unique numbers into data[]
        while (sc.hasNext()) {
            if (sc.hasNext()) {

                try {
                    ++count;
                    int i = Integer.parseInt(sc.next());
                    if (count >= data.length()) {
                        hold = new SimpleArray<Integer>(data.length() * 2, 0);
                        for (int j = 0; j < data.length(); j++) {
                            hold.put(j, data.get(j));
                        }
                        data = new SimpleArray<Integer>(data.length()
                       * data.length(), 0);
                        data = hold;
                    }
                    insert(i);

                } catch (NumberFormatException e) {
                    System.err.printf("Ignored non-integer argument %s\n",
                        sc.next());
                }
            }
        }
        // output unique numbers in array order
        for (int i = 0; i < used; i++) {
            System.out.println(data.get(i));
        }
    }
}

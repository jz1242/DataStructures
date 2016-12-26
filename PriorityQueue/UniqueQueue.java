import java.util.ArrayList;
import java.util.Scanner;

/**jzhan127 Jason Zhang.
 * @author JZ
 *
 */
public final class UniqueQueue {
    /**empty.
     */
    private UniqueQueue(){}

    /**main.
     * @param args String
     */
    public static void main(String[] args) {
        BinaryHeapPriorityQueue<Integer> data = new
                BinaryHeapPriorityQueue<Integer>();
        ArrayList<Integer> used = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextInt()) {
            int s = sc.nextInt();
            data.insert(s);
        }
        while (!data.empty()) {
            int a = data.top();
            if (!(used.contains(a))) {
                System.out.println(a);
                used.add(a);
            }
            data.remove();
        }

    }

}

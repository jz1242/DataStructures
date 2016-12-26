
/**new Sentineltest.
 *@author jzhan127 JasonZhang
 */
public class SentinelListTest extends ListTestBase {
    @Override
    protected List<String> createList() {
        return new SentinelList<>();
    }
}

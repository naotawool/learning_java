package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.assertj.core.util.Maps;
import org.junit.Test;

/**
 * AssertJ を使った Map 検証の学習。
 *
 * @author naotake
 */
public class MapLearning {

    @Test
    public void Entry検証() {
        Map<String, Integer> actuals = Maps.newHashMap();
        actuals.put("Key1", 101);
        actuals.put("Key2", 202);
        actuals.put("Key3", 303);

        assertThat(actuals).containsEntry("Key1", 101)
                           .containsEntry("Key2", 202)
                           .doesNotContainEntry("Key9", 999);
    }

    @Test
    public void Key検証() {
        Map<String, Integer> actuals = Maps.newHashMap();
        actuals.put("Key1", 101);
        actuals.put("Key2", 202);
        actuals.put("Key3", 303);

        assertThat(actuals).containsKey("Key1")
                           .containsKeys("Key2", "Key3")
                           .doesNotContainKey("Key9");
    }

    @Test
    public void Value検証() {
        Map<String, Integer> actuals = Maps.newHashMap();
        actuals.put("Key1", 101);
        actuals.put("Key2", 202);
        actuals.put("Key3", 303);

        assertThat(actuals).containsValue(101)
                           .containsValues(202, 303)
                           .doesNotContainValue(999);
    }

    @Test
    public void 件数検証() {
        Map<String, Integer> actuals = Maps.newHashMap();
        actuals.put("Key1", 101);
        actuals.put("Key2", 202);
        actuals.put("Key3", 303);

        assertThat(actuals).hasSize(3);
    }

    @Test
    public void 空検証() {
        Map<String, Integer> actuals = Maps.newHashMap();
        assertThat(actuals).isEmpty();
    }
}

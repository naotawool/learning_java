package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

import org.junit.Ignore;
import org.junit.Test;

/**
 * AssertJ を使った数値検証の学習。
 *
 * @author naotake
 */
public class NumberLearning {

    @Ignore
    @Test
    public void 範囲検証() {
        assertThat(7).isBetween(0, 9).isBetween(7, 7);
        assertThat(7).isCloseTo(5, within(2)); // 5 ± 2 -> OK
        assertThat(7).isCloseTo(5, within(1)); // 5 ± 1 -> NG
    }

    @Test
    public void 大なり小なり検証() {
        assertThat(7).isGreaterThan(6).isGreaterThanOrEqualTo(7);
        assertThat(7).isLessThan(8).isLessThanOrEqualTo(7);
    }
}

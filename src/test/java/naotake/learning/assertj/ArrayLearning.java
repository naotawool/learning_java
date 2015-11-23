package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.util.Arrays;
import org.junit.Test;

/**
 * AssertJ を使った配列検証の学習。
 *
 * @author naotake
 */
public class ArrayLearning {

    @Test
    public void contains() {
        String[] actuals = Arrays.array("Lucy", "Debit", "Anna", "Jack");

        // 並び順は検証しない
        // 期待値に検証対象の値が全て含まれていなくても OK
        assertThat(actuals).contains("Debit", "Lucy", "Anna")
                           .contains("Lucy", "Anna");
    }

    @Test
    public void containsOnly() {
        String[] actuals = Arrays.array("Lucy", "Debit", "Anna", "Jack");

        // 並び順は検証しない
        // 期待値に検証対象の値が全て含まれていないと NG
        assertThat(actuals).containsOnly("Debit", "Lucy", "Jack", "Anna");
    }

    @Test
    public void containsSequence() {
        String[] actuals = Arrays.array("Lucy", "Debit", "Anna", "Jack");

        // 並び順を検証
        // 期待値と検証対象の件数は検証しない
        assertThat(actuals).containsSequence("Lucy", "Debit")
                           .containsSequence("Debit", "Anna", "Jack");
    }

    @Test
    public void containsSubsequence() {
        String[] actuals = Arrays.array("Lucy", "Debit", "Anna", "Jack");

        // 並び順を検証
        // 期待値と検証対象の件数は検証しない
        // 期待値に検証対象の抜け盛れがあった場合でも OK
        assertThat(actuals).containsSubsequence("Lucy", "Anna")
                           .containsSubsequence("Debit", "Jack")
                           .containsSubsequence("Lucy", "Jack");
    }

    @Test
    public void containsExactly() {
        String[] actuals = Arrays.array("Lucy", "Debit", "Anna", "Jack");

        // 並び順を検証
        // 期待値と検証対象の件数も検証
        // 検証失敗時、失敗した値の情報しか結果に表示されない
        assertThat(actuals).containsExactly("Lucy", "Debit", "Anna", "Jack");
    }

    @Test
    public void containsNull() {
        String[] actuals = Arrays.array("Lucy", null, "Anna", "Jack");

        // 検証対象に null が含まれていることを検証
        assertThat(actuals).containsNull();
    }

    @Test
    public void containsOnlyOnce() {
        String[] actuals = Arrays.array("Lucy", "Debit", "Anna", "Lucy");

        // 期待値が検証対象に 1 つだけ含まれることを検証
        // 期待値が検証対象に 2 つ以上含まれる、もしくは 1 つも含まれないと NG
        assertThat(actuals).containsOnlyOnce("Debit", "Anna");
    }

    @Test
    public void 件数検証() {
        String[] actuals = Arrays.array("Lucy", "Debit", "Anna", "Jack");
        assertThat(actuals).hasSize(4);
    }

    @Test
    public void 空検証() {
        String[] actuals = Arrays.array();
        assertThat(actuals).isEmpty();
    }
}

package naotake.learning.guava;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.collect.ComparisonChain;

/**
 * {@link ComparisonChain}に対するテストクラス。
 * 
 * @author naotake
 */
public class ComparisonChainTest {

    @Test
    public void 氏名の昇順で比較が行われること() {
        Person p1 = newPerson("Test1", 26, true);
        Person p2 = newPerson("Test2", 26, true);

        assertThat(p1.compareTo(p2), is(-1));
        assertThat(p2.compareTo(p1), is(1));
    }

    @Test
    public void 年齢の降順で比較が行われること() {
        Person p1 = newPerson("Test", 20, true);
        Person p2 = newPerson("Test", 30, true);

        assertThat(p1.compareTo(p2), is(1));
        assertThat(p2.compareTo(p1), is(-1));
    }

    @Test
    public void 性別が男性_女性の順で比較が行われること() {
        Person p1 = newPerson("Test", 55, true);
        Person p2 = newPerson("Test", 55, false);

        assertThat(p1.compareTo(p2), is(-1));
        assertThat(p2.compareTo(p1), is(1));
    }

    @Test
    public void 全ての値が等しい場合_0_が返されること() {
        Person p1 = newPerson("Test", 55, true);
        Person p2 = newPerson("Test", 55, true);

        assertThat(p1.compareTo(p2), is(0));
    }

    private static Person newPerson(String name, int age, boolean isMale) {
        if (isMale) {
            return Person.asMale(name, age);
        } else {
            return Person.asFemale(name, age);
        }
    }
}

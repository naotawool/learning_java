package naotake.learning.guava;

import static org.junit.Assert.assertThat;
import static org.junit.extention.matcher.GreaterThan.greaterThanZero;
import static org.junit.extention.matcher.LessThan.lessThanZero;

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

        assertThat(p1.compareTo(p2), lessThanZero());
        assertThat(p2.compareTo(p1), greaterThanZero());
    }

    @Test
    public void 年齢の降順で比較が行われること() {
        Person p1 = newPerson("Test", 20, true);
        Person p2 = newPerson("Test", 30, true);

        assertThat(p1.compareTo(p2), greaterThanZero());
        assertThat(p2.compareTo(p1), lessThanZero());
    }

    @Test
    public void 性別が男性_女性の順で比較が行われること() {
        Person p1 = newPerson("Test", 55, true);
        Person p2 = newPerson("Test", 55, false);

        assertThat(p1.compareTo(p2), lessThanZero());
        assertThat(p2.compareTo(p1), greaterThanZero());
    }

    private static Person newPerson(String name, int age, boolean isMale) {
        if (isMale) {
            return Person.asMale(name, age);
        } else {
            return Person.asFemale(name, age);
        }
    }
}

package naotake.learning.guava;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * {@link Person}に対するテストクラス。
 *
 * @author naotake
 */
public class PersonTest {

    @Test
    public void 同値オブジェクトなら_equals_で_true_が返されること() {
        Person testee1 = Person.asFemale("Naotake", 27);
        Person testee2 = Person.asFemale("Naotake", 27);

        assertThat(testee1.equals(testee2), is(true));
    }

    @Test
    public void 型が異なるオブジェクトなら_equals_で_false_が返されること() {
        Person testee1 = Person.asFemale("Naotake", 27);
        Object testee2 = new Object();

        assertThat(testee1.equals(testee2), is(false));
    }

    @Test
    public void toString_でオブジェクトの文字列表現を取得できること() {
        Person testee = Person.asFemale("Naotake", 27);
        assertThat(testee.toString(), is("Person{Name=Naotake, Age=27, Gender=女性}"));
    }
}

package naotake.learning.java8;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * {@link Student}に対するテストクラス。
 *
 * @author naotake
 */
public class StudentTest {

    @Test
    public void toString_でオブジェクトの文字列表現を取得できること() {
        Student testee = new Student();
        testee.setName("Naotake");
        testee.setPref("Tokyo");
        testee.setScore(82);

        assertThat(testee.toString(), is("Student{Name=Naotake, Pref=Tokyo, Score=82}"));
    }
}

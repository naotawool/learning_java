package naotake.learning.assertj;

import static naotake.learning.assertj.StudentAssert.assertThat;
import naotake.learning.java8.Student;

import org.junit.Test;

/**
 * {@link naotake.learning.java8.StudentTest}を AssertJ で書き直したテストクラス。
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

        assertThat(testee).isToString("Student{Name=Naotake, Pref=Tokyo, Score=82}");
    }
}

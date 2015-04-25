package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import naotake.learning.java8.Student;
import naotake.learning.java8.StudentFormatter;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * {@link naotake.learning.java8.StudentFormatterTest}を AssertJ で書き直したテストクラス。
 *
 * @author naotake
 */
@RunWith(Enclosed.class)
public class StudentFormatterTest implements StudentFormatter {

    public static class デフォルト実装をオーバーライドした場合 {

        private StudentFormatter testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = new SimpleFormatter();
        }

        @Test
        public void 拡張した書式でフォーマットされること() {
            String actual = testee.format(newStudent());
            assertThat(actual).isEqualTo("Jack[沖縄県:79]");
        }
    }

    public static class デフォルト実装をオーバーライドしない場合 {

        private StudentFormatter testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = new DefaultFormatter();
        }

        @Test
        public void デフォルト実装でフォーマットされること() {
            String actual = testee.format(newStudent());
            assertThat(actual).isEqualTo("[Jack,沖縄県,79]");
        }
    }

    private static Student newStudent() {
        Student student = new Student();
        student.setName("Jack");
        student.setPref("沖縄県");
        student.setScore(79);
        return student;
    }

    private static final class DefaultFormatter implements StudentFormatter {
        // FIXME IF のデフォルト実装を override しない
    }

    private static final class SimpleFormatter implements StudentFormatter {

        @Override
        public String format(Student student) {
            return student.getName() + "[" + student.getPref() + ":" + student.getScore() + "]";
        }
    }
}

package naotake.learning.java8;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import com.sun.istack.internal.NotNull;

/**
 * Java8で新たに追加された{@link Optional}に対するテストクラス。
 * 
 * @author naotake
 */
@RunWith(Enclosed.class)
public class OptionalTest {

    public static class of_でインスタンスを指定した場合 {

        private Optional<Student> testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = Optional.of(StudentFixture.newStudent("Jack", "東京都", 29));
        }

        @Test
        public void get_で最初に設定したインスタンスを取得できること() {
            Student actual = testee.get();
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Jack"));
        }

        @Test
        public void orElse_で最初に設定したインスタンスを取得できること() {
            Student actual = testee.orElse(StudentFixture.newStudent("Anna", "京都府", 39));

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Jack"));
        }

        @Test
        public void orElseGet_で最初に設定したインスタンスを取得できること() {
            Student actual = testee.orElseGet(() -> {
                Student student = StudentFixture.newStudent("Debit", "北海道", 51);
                student.setScore(71);
                return student;
            });

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Jack"));
            assertThat(actual.getScore(), is(29));
        }

        @Test
        public void orElseThrow_で最初に設定したインスタンスを取得できること() {
            Student actual = testee.orElseThrow(() -> new NullPointerException("Non Object!!"));

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Jack"));
        }
    }

    public static class empty_で取得した_Optional_の場合 {

        @Rule
        public ExpectedException expected = ExpectedException.none();

        private Optional<Student> testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = Optional.empty();
        }

        @Test(expected = NoSuchElementException.class)
        public void get_で_NoSuchElementException_が送出されること() {
            testee.get();
            fail("ここまで来ない");
        }

        @Test
        public void orElse_で指定したインスタンスを取得できること() {
            Student actual = testee.orElse(StudentFixture.newStudent("Anna", "京都府", 39));

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Anna"));
        }

        @Test
        public void orElseGet_でラムダ式に指定したインスタンスを取得できること() {
            Student actual = testee.orElseGet(() -> {
                Student student = StudentFixture.newStudent("Debit", "北海道", 51);
                student.setScore(71);
                return student;
            });

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Debit"));
            assertThat(actual.getScore(), is(71));
        }

        @Test
        public void orElseThrow_でラムダ式に指定した例外が送出されること() {
            expected.expect(NullPointerException.class);
            expected.expectMessage(is("Non Object!!"));

            testee.orElseThrow(() -> new NullPointerException("Non Object!!"));
            fail("ここまで来ない");
        }
    }

    public static class インスタンスを指定した_ofNullable_で取得した_Optional_の場合 {

        private Optional<Student> testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = Optional.ofNullable(StudentFixture.newStudent("Jack", "東京都", 29));
        }

        @Test
        public void get_で最初に設定したインスタンスを取得できること() {
            Student actual = testee.get();
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Jack"));
        }

        @Test
        public void orElse_で最初に設定したインスタンスを取得できること() {
            Student actual = testee.orElse(StudentFixture.newStudent("Anna", "京都府", 39));

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Jack"));
        }

        @Test
        public void orElseGet_で最初に設定したインスタンスを取得できること() {
            Student actual = testee.orElseGet(() -> {
                Student student = StudentFixture.newStudent("Debit", "北海道", 51);
                student.setScore(71);
                return student;
            });

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Jack"));
            assertThat(actual.getScore(), is(29));
        }

        @Test
        public void orElseThrow_で最初に設定したインスタンスを取得できること() {
            Student actual = testee.orElseThrow(() -> new NullPointerException("Non Object!!"));

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Jack"));
        }
    }

    public static class null_を指定した_ofNullable_で取得した_Optional_の場合 {

        @Rule
        public ExpectedException expected = ExpectedException.none();

        private Optional<Student> testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = Optional.ofNullable(null);
        }

        @Test(expected = NoSuchElementException.class)
        public void get_で_NoSuchElementException_が送出されること() {
            testee.get();
            fail("ここまで来ない");
        }

        @Test
        public void orElse_で指定したインスタンスを取得できること() {
            Student actual = testee.orElse(StudentFixture.newStudent("Anna", "京都府", 39));

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Anna"));
        }

        @Test
        public void orElseGet_でラムダ式に指定したインスタンスを取得できること() {
            Student actual = testee.orElseGet(() -> {
                Student student = StudentFixture.newStudent("Debit", "北海道", 51);
                student.setScore(71);
                return student;
            });

            // 検証
            assertThat(actual, notNullValue());
            assertThat(actual.getName(), is("Debit"));
            assertThat(actual.getScore(), is(71));
        }

        @Test
        public void orElseThrow_でラムダ式に指定した例外が送出されること() {
            expected.expect(NullPointerException.class);
            expected.expectMessage(is("Non Object!!"));

            testee.orElseThrow(() -> new NullPointerException("Non Object!!"));
            fail("ここまで来ない");
        }

        @Test
        public void test() {
            @NotNull
            Object obj = null;

            System.out.println(obj);
        }
    }
}

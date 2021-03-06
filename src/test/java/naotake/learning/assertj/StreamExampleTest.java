package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.assertj.core.api.Assertions.tuple;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import naotake.learning.java8.StreamExample;
import naotake.learning.java8.StreamExample.Mode;
import naotake.learning.java8.Student;
import naotake.learning.java8.StudentFixture;

import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * {@link naotake.learning.java8.StreamExampleTest}を AssertJ で書き直したテストクラス。
 *
 * @author naotake
 */
@RunWith(Enclosed.class)
public class StreamExampleTest {

    private static List<Student> students;

    public static class Java8以前の場合 {

        private StreamExample testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = new StreamExample();
            testee.mode = Mode.JAVA_7;
            students = newStudents();
        }

        @Test
        public void 平均値を取得できること() {
            double actual = testee.average(students, "2_東京都");
            assertThat(actual).isEqualTo(150.5);
        }

        @Test
        public void 東京都の合計を取得できること() {
            int actual = testee.sum(students, "2_東京都");
            assertThat(actual).isEqualTo(301);
        }

        @Test
        public void 全ての合計を取得できること() {
            int actual = testee.sum(students, null);
            assertThat(actual).isEqualTo(701);
        }

        @Test
        public void 加工された生徒名の一覧を取得できること() {
            String actual = testee.mapToNames(students);
            assertThat(actual).isEqualTo("[Debit]:[Anna]:[Lucy]:[Jack]");
        }

        @Test
        public void スコアの一覧を昇順で取得できること() {
            List<Integer> actuals = testee.sortedScores(students);
            assertThat(actuals).hasSize(4).containsSequence(90, 120, 181, 310);
        }

        @Test
        public void スコアの最大値を取得できること() {
            int actual = testee.maxScore(students);
            assertThat(actual).isEqualTo(310);
        }

        @Test
        public void 都道府県の一覧を降順で重複無く取得できること() {
            String actual = testee.distinctPrefs(students);
            assertThat(actual).isEqualTo("3_大阪府:2_東京都:1_北海道");
        }

        @Test
        public void 指定したスコアを下回る件数を取得できること() {
            assertThat(testee.countUnderScore(students, 200)).isEqualTo(3);
            assertThat(testee.countUnderScore(students, 1)).isEqualTo(0);
        }

        @Test
        public void 指定したスコアを上回るスコアが存在するかどうかを取得できること() {
            assertThat(testee.hasOverScore(students, 300)).isTrue();
            assertThat(testee.hasOverScore(students, 310)).isFalse();
        }

        @Test
        public void 指定した名前の生徒が存在しないかどうかを取得できること() {
            assertThat(testee.isNotExistsName(students, "Jack")).isFalse();
            assertThat(testee.isNotExistsName(students, "Hoge")).isTrue();
        }

        @Test
        public void 指定したスコアを上回る生徒の名前の一覧を取得できること() {
            List<String> actuals = testee.findNameByOverScore(students, 100);
            assertThat(actuals).hasSize(3).containsSequence("Debit", "Jack", "Lucy");
        }

        @Test
        public void 都道府県別の生徒数を取得できること() {
            Map<String, Long> actuals = testee.countStudentByPref(students);
            assertThat(actuals).containsOnly(entry("1_北海道", 1L), entry("2_東京都", 2L),
                                             entry("3_大阪府", 1L));
        }
    }

    public static class Java8の場合 {

        private StreamExample testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = new StreamExample();
            testee.mode = Mode.JAVA_8;
            students = newStudents();
        }

        @Test
        public void stream_を使って平均値を取得できること() {
            double actual = testee.average(students, "2_東京都");
            assertThat(actual).isEqualTo(150.5);
        }

        @Test
        public void stream_を使って東京都の合計を取得できること() {
            int actual = testee.sum(students, "2_東京都");
            assertThat(actual).isEqualTo(301);
        }

        @Test
        public void stream_で全ての合計を取得できること() {
            int actual = testee.sum(students, null);
            assertThat(actual).isEqualTo(701);
        }

        @Test
        public void stream_で加工された生徒名の一覧を取得できること() {
            String actual = testee.mapToNames(students);
            assertThat(actual).isEqualTo("[Debit]:[Anna]:[Lucy]:[Jack]");
        }

        @Test
        public void stream_でスコアの一覧を昇順で取得できること() {
            List<Integer> actuals = testee.sortedScores(students);
            assertThat(actuals).hasSize(4);
            assertThat(actuals).containsSequence(90, 120, 181, 310);
        }

        @Test
        public void stream_でスコアの最大値を取得できること() {
            int actual = testee.maxScore(students);
            assertThat(actual).isEqualTo(310);
        }

        @Test
        public void stream_を使って都道府県の一覧を降順で重複無く取得できること() {
            String actual = testee.distinctPrefs(students);
            assertThat(actual).isEqualTo("3_大阪府:2_東京都:1_北海道");
        }

        @Test
        public void stream_を使って指定したスコアを下回る件数を取得できること() {
            assertThat(testee.countUnderScore(students, 200)).isEqualTo(3);
            assertThat(testee.countUnderScore(students, 1)).isEqualTo(0);
        }

        @Test
        public void stream_を使って指定したスコアを上回るスコアが存在するかどうかを取得できること() {
            assertThat(testee.hasOverScore(students, 300)).isTrue();
            assertThat(testee.hasOverScore(students, 310)).isFalse();
        }

        @Test
        public void stream_を使って指定した名前の生徒が存在しないかどうかを取得できること() {
            assertThat(testee.isNotExistsName(students, "Jack")).isFalse();
            assertThat(testee.isNotExistsName(students, "Hoge")).isTrue();
        }

        @Test
        public void stream_を使って指定したスコアを上回る生徒の名前の一覧を取得できること() {
            List<String> actuals = testee.findNameByOverScore(students, 100);
            assertThat(actuals).hasSize(3);
            assertThat(actuals).containsSequence("Debit", "Jack", "Lucy");
        }

        @Test
        public void stream_を使って都道府県別の生徒数を取得できること() {
            Map<String, Long> actuals = testee.countStudentByPref(students);
            assertThat(actuals).containsOnly(entry("1_北海道", 1L), entry("2_東京都", 2L),
                                             entry("3_大阪府", 1L));
        }

        @Test
        public void 指定した数の2の倍数を取得できること() {
            String actual = testee.get2MultipleSeries(5);
            assertThat(actual).isEqualTo("2,4,8,16,32");

            actual = testee.get2MultipleSeries(10);
            assertThat(actual).isEqualTo("2,4,8,16,32,64,128,256,512,1024");
        }

        @Test
        public void SoftAssertionsを使って生徒の情報を検証できること() {
            List<String> actuals = testee.findNameByOverScore(students, 100);
            assertThat(actuals).hasSize(3);
            assertThat(actuals).containsSequence("Debit", "Jack", "Lucy");

            SoftAssertions softly = new SoftAssertions();
            softly.assertThat(actuals).as("Actual Students size").hasSize(3);
            softly.assertThat(actuals).as("Actual Students name")
                  .containsSequence("Debit", "Jack", "Lucy");

            softly.assertAll();
        }
    }

    public static class AssertJを使ったテスト {

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            students = newStudents();
        }

        @Test
        public void extracting_を使って生徒の名前を検証できること() {

            List<String> studentNames = students.stream().map(s -> s.getName())
                                                .collect(Collectors.toList());
            assertThat(studentNames, hasSize(4));
            assertThat(studentNames.get(0), is("Debit"));
            assertThat(studentNames.get(1), is("Anna"));
            assertThat(studentNames.get(2), is("Lucy"));
            assertThat(studentNames.get(3), is("Jack"));

            // JUnit4
            assertThat(students, hasSize(4));
            assertThat(students.get(0).getName(), is("Debit"));
            assertThat(students.get(1).getName(), is("Anna"));
            assertThat(students.get(2).getName(), is("Lucy"));
            assertThat(students.get(3).getName(), is("Jack"));

            // AssertJ
            assertThat(students).extracting(Student::getName).hasSize(4)
                                .containsSequence("Debit", "Anna", "Lucy", "Jack");

            assertThat(students)
                                .extracting(Student::getName, Student::getScore)
                                .hasSize(4)
                                .containsSequence(tuple("Debit", 120), tuple("Anna", 90), tuple("Lucy", 181),
                                                  tuple("Jack", 310));
        }
    }

    private static List<Student> newStudents() {
        return StudentFixture.newStudents();
    }
}

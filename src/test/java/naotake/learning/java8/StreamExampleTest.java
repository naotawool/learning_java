package naotake.learning.java8;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import naotake.learning.java8.StreamExample.Mode;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

/**
 * {@link StreamExample}に対するテストクラス。
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
            assertThat(actual, is(150.5));
        }

        @Test
        public void 東京都の合計を取得できること() {
            int actual = testee.sum(students, "2_東京都");
            assertThat(actual, is(301));
        }

        @Test
        public void 全ての合計を取得できること() {
            int actual = testee.sum(students, null);
            assertThat(actual, is(701));
        }

        @Test
        public void 加工された生徒名の一覧を取得できること() {
            String actual = testee.mapToNames(students);
            assertThat(actual, is("[Debit]:[Anna]:[Lucy]:[Jack]"));
        }

        @Test
        public void スコアの一覧を昇順で取得できること() {
            List<Integer> actuals = testee.sortedScores(students);
            assertThat(actuals, hasSize(4));
            assertThat(actuals, hasItems(90, 120, 181, 310));
        }

        @Test
        public void スコアの最大値を取得できること() {
            int actual = testee.maxScore(students);
            assertThat(actual, is(310));
        }

        @Test
        public void 都道府県の一覧を降順で重複無く取得できること() {
            String actual = testee.distinctPrefs(students);
            assertThat(actual, is("3_大阪府:2_東京都:1_北海道"));
        }

        @Test
        public void 指定したスコアを下回る件数を取得できること() {
            assertThat(testee.countUnderScore(students, 200), is(3L));
            assertThat(testee.countUnderScore(students, 1), is(0L));
        }

        @Test
        public void 指定したスコアを上回るスコアが存在するかどうかを取得できること() {
            assertThat(testee.hasOverScore(students, 300), is(true));
            assertThat(testee.hasOverScore(students, 310), is(false));
        }

        @Test
        public void 指定した名前の生徒が存在しないかどうかを取得できること() {
            assertThat(testee.isNotExistsName(students, "Jack"), is(false));
            assertThat(testee.isNotExistsName(students, "Hoge"), is(true));
        }

        @Test
        public void 指定したスコアを上回る生徒の名前の一覧を取得できること() {
            List<String> actuals = testee.findNameByOverScore(students, 100);
            assertThat(actuals, hasSize(3));
            assertThat(actuals, hasItems("Debit", "Lucy", "Jack"));
        }

        @Test
        public void 都道府県別の生徒数を取得できること() {
            Map<String, Long> actuals = testee.countStudentByPref(students);
            assertThat(actuals.get("1_北海道"), is(1L));
            assertThat(actuals.get("2_東京都"), is(2L));
            assertThat(actuals.get("3_大阪府"), is(1L));
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
            assertThat(actual, is(150.5));
        }

        @Test
        public void stream_を使って東京都の合計を取得できること() {
            int actual = testee.sum(students, "2_東京都");
            assertThat(actual, is(301));
        }

        @Test
        public void stream_で全ての合計を取得できること() {
            int actual = testee.sum(students, null);
            assertThat(actual, is(701));
        }

        @Test
        public void stream_で加工された生徒名の一覧を取得できること() {
            String actual = testee.mapToNames(students);
            assertThat(actual, is("[Debit]:[Anna]:[Lucy]:[Jack]"));
        }

        @Test
        public void stream_でスコアの一覧を昇順で取得できること() {
            List<Integer> actuals = testee.sortedScores(students);
            assertThat(actuals, hasSize(4));
            assertThat(actuals, hasItems(90, 120, 181, 310));
        }

        @Test
        public void stream_でスコアの最大値を取得できること() {
            int actual = testee.maxScore(students);
            assertThat(actual, is(310));
        }

        @Test
        public void stream_を使って都道府県の一覧を降順で重複無く取得できること() {
            String actual = testee.distinctPrefs(students);
            assertThat(actual, is("3_大阪府:2_東京都:1_北海道"));
        }

        @Test
        public void stream_を使って指定したスコアを下回る件数を取得できること() {
            assertThat(testee.countUnderScore(students, 200), is(3L));
            assertThat(testee.countUnderScore(students, 1), is(0L));
        }

        @Test
        public void stream_を使って指定したスコアを上回るスコアが存在するかどうかを取得できること() {
            assertThat(testee.hasOverScore(students, 300), is(true));
            assertThat(testee.hasOverScore(students, 310), is(false));
        }

        @Test
        public void stream_を使って指定した名前の生徒が存在しないかどうかを取得できること() {
            assertThat(testee.isNotExistsName(students, "Jack"), is(false));
            assertThat(testee.isNotExistsName(students, "Hoge"), is(true));
        }

        @Test
        public void stream_を使って指定したスコアを上回る生徒の名前の一覧を取得できること() {
            List<String> actuals = testee.findNameByOverScore(students, 100);
            assertThat(actuals, hasSize(3));
            assertThat(actuals, hasItems("Debit", "Lucy", "Jack"));
        }

        @Test
        public void stream_を使って都道府県別の生徒数を取得できること() {
            Map<String, Long> actuals = testee.countStudentByPref(students);
            assertThat(actuals.get("1_北海道"), is(1L));
            assertThat(actuals.get("2_東京都"), is(2L));
            assertThat(actuals.get("3_大阪府"), is(1L));
        }

        @Test
        public void 指定した数の2の倍数を取得できること() {
            String actual = testee.get2MultipleSeries(5);
            assertThat(actual, is("2,4,8,16,32"));

            actual = testee.get2MultipleSeries(10);
            assertThat(actual, is("2,4,8,16,32,64,128,256,512,1024"));
        }
    }

    private static List<Student> newStudents() {
        return StudentFixture.newStudents();
    }
}

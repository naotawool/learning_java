package naotake.learning.java8;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import naotake.learning.java8.StreamExample.Mode;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import com.google.common.collect.Lists;

/**
 * {@link StreamExample}に対するテストクラス。
 * 
 * @author naotake
 */
@RunWith(Enclosed.class)
public class StreamExampleTest {

    public static class Java8以前の場合 {

        private StreamExample testee;

        /**
         * 事前処理。
         */
        @Before
        public void setUp() {
            testee = new StreamExample();
            testee.mode = Mode.JAVA_7;
        }

        @Test
        public void 平均値を取得できること() {
            double actual = testee.average(newStudents(), "2_東京都");
            assertThat(actual, is(150.5));
        }

        @Test
        public void 東京都の合計を取得できること() {
            int actual = testee.sum(newStudents(), "2_東京都");
            assertThat(actual, is(301));
        }

        @Test
        public void 全ての合計を取得できること() {
            int actual = testee.sum(newStudents(), null);
            assertThat(actual, is(701));
        }

        @Test
        public void 加工された生徒名の一覧を取得できること() {
            String actual = testee.mapToNames(newStudents());
            assertThat(actual, is("[Debit]:[Annna]:[Lucy]:[Jack]"));
        }

        @Test
        public void スコアの一覧を昇順で取得できること() {
            List<Integer> actuals = testee.sortedScores(newStudents());
            assertThat(actuals, hasSize(4));
            assertThat(actuals, hasItems(90, 120, 181, 310));
        }

        @Test
        public void スコアの最大値を取得できること() {
            int actual = testee.maxScore(newStudents());
            assertThat(actual, is(310));
        }

        @Test
        public void 都道府県の一覧を降順で重複無く取得できること() {
            String actual = testee.distinctPrefs(newStudents());
            assertThat(actual, is("3_大阪府:2_東京都:1_北海道"));
        }

        @Test
        public void 指定したスコアを下回る件数を取得できること() {
            assertThat(testee.countUnderScore(newStudents(), 200), is(3L));
            assertThat(testee.countUnderScore(newStudents(), 1), is(0L));
        }

        @Test
        public void 指定したスコアを上回るスコアが存在するかどうかを取得できること() {
            assertThat(testee.hasOverScore(newStudents(), 300), is(true));
            assertThat(testee.hasOverScore(newStudents(), 310), is(false));
        }

        @Test
        public void 指定した名前の生徒が存在しないかどうかを取得できること() {
            assertThat(testee.isNotExistsName(newStudents(), "Jack"), is(false));
            assertThat(testee.isNotExistsName(newStudents(), "Hoge"), is(true));
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
        }

        @Test
        public void stream_を使って平均値を取得できること() {
            double actual = testee.average(newStudents(), "2_東京都");
            assertThat(actual, is(150.5));
        }

        @Test
        public void stream_を使って東京都の合計を取得できること() {
            int actual = testee.sum(newStudents(), "2_東京都");
            assertThat(actual, is(301));
        }

        @Test
        public void stream_で全ての合計を取得できること() {
            int actual = testee.sum(newStudents(), null);
            assertThat(actual, is(701));
        }

        @Test
        public void stream_で加工された生徒名の一覧を取得できること() {
            String actual = testee.mapToNames(newStudents());
            assertThat(actual, is("[Debit]:[Annna]:[Lucy]:[Jack]"));
        }

        @Test
        public void stream_でスコアの一覧を昇順で取得できること() {
            List<Integer> actuals = testee.sortedScores(newStudents());
            assertThat(actuals, hasSize(4));
            assertThat(actuals, hasItems(90, 120, 181, 310));
        }

        @Test
        public void stream_でスコアの最大値を取得できること() {
            int actual = testee.maxScore(newStudents());
            assertThat(actual, is(310));
        }

        @Test
        public void stream_を使って都道府県の一覧を降順で重複無く取得できること() {
            String actual = testee.distinctPrefs(newStudents());
            assertThat(actual, is("3_大阪府:2_東京都:1_北海道"));
        }

        @Test
        public void stream_を使って指定したスコアを下回る件数を取得できること() {
            assertThat(testee.countUnderScore(newStudents(), 200), is(3L));
            assertThat(testee.countUnderScore(newStudents(), 1), is(0L));
        }

        @Test
        public void stream_を使って指定したスコアを上回るスコアが存在するかどうかを取得できること() {
            assertThat(testee.hasOverScore(newStudents(), 300), is(true));
            assertThat(testee.hasOverScore(newStudents(), 310), is(false));
        }

        @Test
        public void stream_を使って指定した名前の生徒が存在しないかどうかを取得できること() {
            assertThat(testee.isNotExistsName(newStudents(), "Jack"), is(false));
            assertThat(testee.isNotExistsName(newStudents(), "Hoge"), is(true));
        }
    }

    private static List<Student> newStudents() {
        List<Student> students = Lists.newArrayListWithCapacity(3);
        students.add(newStudent("Debit", "2_東京都", 120));
        students.add(newStudent("Annna", "3_大阪府", 90));
        students.add(newStudent("Lucy", "2_東京都", 181));
        students.add(newStudent("Jack", "1_北海道", 310));

        return students;
    }

    private static Student newStudent(String name, String pref, int score) {
        Student student = new Student();
        student.setName(name);
        student.setPref(pref);
        student.setScore(score);
        return student;
    }
}
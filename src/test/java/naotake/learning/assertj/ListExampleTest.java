package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import static naotake.learning.assertj.StudentsAssert.assertThat;

import java.util.List;

import naotake.learning.java8.Student;
import naotake.learning.java8.StudentFixture;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * {@link naotake.learning.java8.ListExampleTest}を AssertJ で書き直したテストクラス。
 *
 * @author naotake
 */
public class ListExampleTest {

    private List<Student> students;

    /**
     * 事前処理。
     */
    @Before
    public void setUp() {
        students = StudentFixture.newStudents();
    }

    @Test
    public void sort_を使って生徒一覧を成績の昇順にソートできること() {
        students.sort((s1, s2) -> s1.getScore() - s2.getScore());

        // 検証
        assertThat(students).hasSize(4);
        assertThat(students).indexOf(0).isName("Anna");
        assertThat(students).indexOf(1).isName("Debit");
        assertThat(students).indexOf(2).isName("Lucy");
        assertThat(students).indexOf(3).isName("Jack");
    }

    @Test
    public void forEach_を使って都道府県名の加工が行われること() {
        students.forEach(s -> s.setPref(s.getPref().substring(0, 1)));

        // 検証
        assertThat(students).hasSize(4);
        assertThat(students).indexOf(0).isPref("2");
        assertThat(students).indexOf(1).isPref("3");
        assertThat(students).indexOf(2).isPref("2");
        assertThat(students).indexOf(3).isPref("1");
    }

    @Test
    public void removeIf_を使って成績が_100_より低い生徒が除外されること() {
        students.removeIf(s -> s.getScore() < 100);

        // 検証
        assertThat(students).hasSize(3);
        assertThat(students).indexOf(0).isName("Debit");
        assertThat(students).indexOf(1).isName("Lucy");
        assertThat(students).indexOf(2).isName("Jack");
    }
}

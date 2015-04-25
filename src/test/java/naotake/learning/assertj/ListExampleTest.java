package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;

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
        StudentsAssert studentsAssert = new StudentsAssert(students);
        assertThat(students).hasSize(4);
        assertThat(studentsAssert).indexOf(0).isEqualName("Anna");
        assertThat(studentsAssert).indexOf(1).isEqualName("Debit");
        assertThat(studentsAssert).indexOf(2).isEqualName("Lucy");
        assertThat(studentsAssert).indexOf(3).isEqualName("Jack");
    }

    @Test
    public void forEach_を使って都道府県名の加工が行われること() {
        students.forEach(s -> s.setPref(s.getPref().substring(0, 1)));

        // 検証
        StudentsAssert studentsAssert = new StudentsAssert(students);
        assertThat(students).hasSize(4);
        assertThat(studentsAssert).indexOf(0).isEqualPref("2");
        assertThat(studentsAssert).indexOf(1).isEqualPref("3");
        assertThat(studentsAssert).indexOf(2).isEqualPref("2");
        assertThat(studentsAssert).indexOf(3).isEqualPref("1");
    }

    @Test
    public void removeIf_を使って成績が_100_より低い生徒が除外されること() {
        students.removeIf(s -> s.getScore() < 100);

        // 検証
        StudentsAssert studentsAssert = new StudentsAssert(students);
        assertThat(students).hasSize(3);
        assertThat(studentsAssert).indexOf(0).isEqualName("Debit");
        assertThat(studentsAssert).indexOf(1).isEqualName("Lucy");
        assertThat(studentsAssert).indexOf(2).isEqualName("Jack");
    }
}

package naotake.learning.java8;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Java8で{@link java.util.List}に追加された新たなメソッドに対するテストクラス。
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
        assertThat(students, hasSize(4));
        assertThat(students.get(0).getName(), is("Anna"));
        assertThat(students.get(1).getName(), is("Debit"));
        assertThat(students.get(2).getName(), is("Lucy"));
        assertThat(students.get(3).getName(), is("Jack"));
    }

    @Test
    public void forEach_を使って都道府県名の加工が行われること() {
        students.forEach(s -> s.setPref(s.getPref().substring(0, 1)));

        // 検証
        assertThat(students, hasSize(4));
        assertThat(students.get(0).getPref(), is("2"));
        assertThat(students.get(1).getPref(), is("3"));
        assertThat(students.get(2).getPref(), is("2"));
        assertThat(students.get(3).getPref(), is("1"));
    }

    @Test
    public void removeIf_を使って成績が_100_より低い生徒が除外されること() {
        students.removeIf(s -> s.getScore() < 100);

        // 検証
        assertThat(students, hasSize(3));
        assertThat(students.get(0).getName(), is("Debit"));
        assertThat(students.get(1).getName(), is("Lucy"));
        assertThat(students.get(2).getName(), is("Jack"));

        // 元のリストが変更されていること
        assertThat(students, hasSize(3));
    }
}

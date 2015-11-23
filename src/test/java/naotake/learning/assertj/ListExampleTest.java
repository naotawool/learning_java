package naotake.learning.assertj;

import static naotake.learning.assertj.LearningAssertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.util.List;

import naotake.learning.java8.Student;
import naotake.learning.java8.StudentFixture;

import org.assertj.core.api.Assertions;
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

        // extracting (TypeSafe on Java8) from AssertJ
        // 順番は気にしない
        // 期待値に実値の全てが含まれていなくても OK
        Assertions.assertThat(students).extracting(Student::getName)
                  .contains("Debit", "Lucy", "Anna", "Jack").contains("Lucy", "Anna");

        // 順番は気にしない
        // 期待値に実値の全てが含まれていないと NG
        Assertions.assertThat(students).extracting(Student::getName)
                  .containsOnly("Lucy", "Debit", "Anna", "Jack");

        // 順番まで厳密に比較
        // 件数まで比較
        Assertions.assertThat(students).extracting(Student::getName)
                  .containsExactly("Anna", "Debit", "Lucy", "Jack");

        // 順番まで厳密に比較
        // 件数まで比較しない (途中の順番でも一致していれば OK とみなす)
        Assertions.assertThat(students).extracting(Student::getName)
                  .containsSequence("Anna", "Debit", "Lucy")
                  .containsSequence("Lucy", "Jack");

        // 抜け漏れを加味して順番を比較
        Assertions.assertThat(students).extracting(Student::getName)
                  .containsSubsequence("Anna", "Debit", "Lucy", "Jack")
                  .containsSubsequence("Anna", "Jack").containsSubsequence("Debit", "Lucy");

        // extracting (String) from AssertJ
        // 順番は気にしない
        // 期待値に実値の全てが含まれていなくても OK
        Assertions.assertThat(students).extracting("name")
                  .contains("Debit", "Lucy", "Anna", "Jack").contains("Lucy", "Anna");

        // 順番は気にしない
        // 期待値に実値の全てが含まれていないと NG
        Assertions.assertThat(students).extracting("name")
                  .containsOnly("Lucy", "Debit", "Anna", "Jack");

        // 順番まで厳密に比較
        // 失敗すると、失敗した値しか結果に表示されない
        // 件数まで比較
        Assertions.assertThat(students).extracting("name")
                  .containsExactly("Anna", "Debit", "Lucy", "Jack");

        // 順番まで厳密に比較
        // 失敗すると、全ての順序を含めて結果に表示される
        // 件数まで比較しない (途中の順番でも一致していれば OK とみなす)
        Assertions.assertThat(students).extracting("name")
                  .containsSequence("Anna", "Debit", "Lucy", "Jack");

        // 抜け漏れを加味して順番を比較
        Assertions.assertThat(students).extracting("name")
                  .containsSubsequence("Anna", "Debit", "Lucy", "Jack")
                  .containsSubsequence("Anna", "Jack").containsSubsequence("Debit", "Lucy");

        // 特定のメソッド呼び出しの結果を基に検証
        Assertions.assertThat(students).extractingResultOf("greeting")
                  .containsExactly("I'm Anna.", "I'm Debit.", "I'm Lucy.", "I'm Jack.");
    }

    @Test
    public void extracting_tuple_を使って複数のプロパティを一括で検証する() {
        students.sort((s1, s2) -> s1.getScore() - s2.getScore());

        Assertions.assertThat(students).extracting(Student::getName, Student::getScore)
                  .contains(tuple("Lucy", 181), tuple("Anna", 90))
                  .contains(tuple("Debit", 120), tuple("Jack", 310));
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

        // extracting from AssertJ
        // 期待値に指定した値が実値の中に必ず 1 度しか現れないとエラー
        Assertions.assertThat(students).extracting(Student::getPref).containsOnlyOnce("1", "3")
        // .containsOnlyOnce("2") // 2件データとして含まれるのでエラーとなる
        // .containsOnlyOnce("4") // 1件もデータとして含まれていないのでエラーとなる
        ;
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

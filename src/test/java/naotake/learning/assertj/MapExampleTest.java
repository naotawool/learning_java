package naotake.learning.assertj;

import static naotake.learning.java8.StudentFixture.newStudent;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import naotake.learning.java8.Student;
import naotake.learning.java8.StudentFixture;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link naotake.learning.java8.MapExampleTest}を AssertJ で書き直したテストクラス。
 *
 * @author naotake
 */
public class MapExampleTest {

    private Map<String, Student> students;

    /**
     * 事前処理。
     */
    @Before
    public void setUp() {
        students = StudentFixture.newStudentsMap();
    }

    @Test
    public void putIfAbsent_で指定したキーの値が存在しない場合だけMapに値が追加されること() {
        // キーがない場合
        Student actual = students.putIfAbsent("Kebin", newStudent("Kebin", "4_和歌山県", 152));
        assertThat(actual).isNull();

        // キーがある場合
        actual = students.putIfAbsent("Anna", newStudent("Anna", "3_大阪府", 91));
        assertThat(actual).isNotNull().isEqualToComparingFieldByField(students.get("Anna"));
    }

    @Test
    public void getOrDefault_で指定したキーの値が存在しない場合だけデフォルト値が返されること() {
        // キーがない場合
        Student actual = students.getOrDefault("Kebin", StudentFixture.NULL);
        assertThat(actual).isNotNull().isEqualToComparingFieldByField(StudentFixture.NULL);

        // キーがある場合
        actual = students.getOrDefault("Anna", StudentFixture.NULL);
        assertThat(actual).isNotNull().isEqualToComparingFieldByField(students.get("Anna"));
    }

    @Test
    public void merge_で指定したキーの値が存在しない場合は追加_存在する場合は成績を合計してマージできること() {
        // キーがない場合
        students.merge("Kebin", newStudent("Kebin", "4_和歌山県", 152), (x, y) -> {
            int sum = x.getScore() + y.getScore();
            y.setScore(sum);
            return y;
        });
        assertThat(students).containsKey("Kebin");
        assertThat(students.get("Kebin").getScore(), is(152));

        // キーがある場合
        students.merge("Anna", newStudent("Anna", "3_大阪府", 110), (x, y) -> {
            int sum = x.getScore() + y.getScore();
            y.setScore(sum);
            return y;
        });
        assertThat(students).containsKey("Anna");
        assertThat(students.get("Anna").getScore(), is(200)); // 90 + 110
    }
}

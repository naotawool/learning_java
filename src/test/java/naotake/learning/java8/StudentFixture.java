package naotake.learning.java8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * {@link Student}のテストデータクラス。
 * 
 * @author naotake
 */
public class StudentFixture {

    public static final Student NULL = new NullStudent();

    public static List<Student> newStudents() {
        List<Student> students = new ArrayList<>(4);
        students.add(newStudent("Debit", "2_東京都", 120));
        students.add(newStudent("Anna", "3_大阪府", 90));
        students.add(newStudent("Lucy", "2_東京都", 181));
        students.add(newStudent("Jack", "1_北海道", 310));

        return students;
    }

    public static Map<String, Student> newStudentsMap() {
        Map<String, Student> students = new HashMap<>(4);
        for (Student student : newStudents()) {
            students.put(student.getName(), student);
        }
        return students;
    }

    public static Student newStudent(String name, String pref, int score) {
        Student student = new Student();
        student.setName(name);
        student.setPref(pref);
        student.setScore(score);
        return student;
    }

    private static class NullStudent extends Student {

        public String getName() {
            return StringUtils.EMPTY;
        }

        public String getPref() {
            return StringUtils.EMPTY;
        }

        public int getScore() {
            return 0;
        }
    }
}

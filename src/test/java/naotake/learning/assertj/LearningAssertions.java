package naotake.learning.assertj;

import java.util.List;

import naotake.learning.java8.Student;

public class LearningAssertions {

    public static StudentAssert assertThat(Student actual) {
        return new StudentAssert(actual);
    }

    public static StudentsAssert assertThat(List<Student> actual) {
        return new StudentsAssert(actual);
    }
}

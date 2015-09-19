package naotake.learning.assertj;

import java.util.List;

import naotake.learning.java8.Student;

import org.assertj.core.api.AbstractAssert;
import org.hamcrest.core.Is;
import org.junit.Assert;

class StudentsAssert extends AbstractAssert<StudentsAssert, List<Student>> {

    protected StudentsAssert(List<Student> actual) {
        super(actual, StudentsAssert.class);
    }

    public static StudentsAssert assertThat(List<Student> actual) {
        return new StudentsAssert(actual);
    }

    public StudentsAssert hasSize(int expected) {
        Assert.assertThat(actual.size(), Is.is(expected));
        return this;
    }

    public StudentAssert indexOf(int index) {
        return new StudentAssert(actual.get(index));
    }
}

package naotake.learning.assertj;

import naotake.learning.java8.Student;

import org.assertj.core.api.AbstractAssert;
import org.hamcrest.core.Is;
import org.junit.Assert;

class StudentAssert extends AbstractAssert<StudentAssert, Student> {

    protected StudentAssert(Student actual) {
        super(actual, StudentAssert.class);
    }

    public static StudentAssert assertThat(Student actual) {
        return new StudentAssert(actual);
    }

    public StudentAssert isName(String expect) {
        Assert.assertThat(actual.getName(), Is.is(expect));
        return this;
    }

    public StudentAssert isPref(String expect) {
        Assert.assertThat(actual.getPref(), Is.is(expect));
        return this;
    }

    public StudentAssert isScore(int expect) {
        Assert.assertThat(actual.getScore(), Is.is(expect));
        return this;
    }

    public StudentAssert isToString(String expect) {
        Assert.assertThat(actual.toString(), Is.is(expect));
        return this;
    }
}

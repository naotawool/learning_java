package naotake.learning.assertj;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import naotake.learning.java8.Student;

import org.assertj.core.api.AssertDelegateTarget;

class StudentAssert implements AssertDelegateTarget {

    private final Student testee;

    StudentAssert(final Student testee) {
        this.testee = testee;
    }

    void isEqualName(String expect) {
        assertThat(testee.getName(), is(expect));
    }

    void isEqualPref(String expect) {
        assertThat(testee.getPref(), is(expect));
    }

    void isEqualScore(int expect) {
        assertThat(testee.getScore(), is(expect));
    }
}

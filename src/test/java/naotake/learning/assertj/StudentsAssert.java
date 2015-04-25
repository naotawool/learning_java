package naotake.learning.assertj;

import java.util.List;

import naotake.learning.java8.Student;

import org.assertj.core.api.AssertDelegateTarget;

class StudentsAssert implements AssertDelegateTarget {

    private final List<Student> testees;

    StudentsAssert(List<Student> testees) {
        this.testees = testees;
    }

    StudentAssert indexOf(int index) {
        return new StudentAssert(testees.get(index));
    }
}

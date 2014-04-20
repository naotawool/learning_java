package naotake.learning.java8;

public interface StudentFormatter {

    default public String format(Student student) {
        return "[" + student.getName() + "," + student.getPref() + "," + student.getScore() + "]";
    }
}

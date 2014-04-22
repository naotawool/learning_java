package naotake.learning.guava;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.google.common.base.Objects;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

public class Person implements Comparable<Person> {

    private String name;

    private int age;

    private Gender gender;

    private Person(String name, int age, Gender gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public static Person asMale(String name, int age) {
        return new Person(name, age, Gender.MALE);
    }

    public static Person asFemale(String name, int age) {
        return new Person(name, age, Gender.FEMALE);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    /**
     * {@link Person}の比較を行う。
     * <ol>
     * <li>氏名（昇順）</li>
     * <li>年齢（降順）</li>
     * <li>性別（男性, 女性)</li>
     * </ol>
     */
    @Override
    public int compareTo(Person that) {
        return ComparisonChain.start()
                .compare(this.name, that.getName(), Ordering.natural().nullsLast())
                .compare(that.getAge(), this.age).compare(this.gender, that.getGender()).result();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = Person.class.cast(other);
        return new EqualsBuilder().append(this.name, otherPerson.getName())
                .append(this.age, otherPerson.getAge())
                .append(this.gender, otherPerson.getGender()).isEquals();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("Name", name).add("Age", age).add("Gender", gender)
                .toString();
    }

    private enum Gender {
        /** 男性 */
        MALE,
        /** 女性 */
        FEMALE
    }
}

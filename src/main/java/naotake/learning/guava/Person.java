package naotake.learning.guava;

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

    private enum Gender {
        /** 男性 */
        MALE,
        /** 女性 */
        FEMALE
    }
}

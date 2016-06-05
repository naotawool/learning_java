package naotake.learning.java8;

import com.google.common.base.Objects;

public class Student {

    private String name;

    private String pref;

    private int score;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPref() {
        return pref;
    }

    public void setPref(String pref) {
        this.pref = pref;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String greeting() {
        return String.format("I'm %s.", name);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("Name", name).add("Pref", pref).add("Score", score)
                      .toString();
    }
}

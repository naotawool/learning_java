package naotake.learning.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.base.Joiner;

/**
 * Java8から導入された{@link Stream}を使ったサンプルクラス。
 * 
 * @author naotake
 */
public class StreamExample {

    public Mode mode = Mode.JAVA_7;

    public double average(List<Student> students, String targetPref) {
        if (mode == Mode.JAVA_7) {
            return averageOnForeach(students, targetPref);
        } else {
            return averageOnStream(students, targetPref);
        }
    }

    private double averageOnForeach(List<Student> students, String targetPref) {
        int total = 0;
        int count = 0;

        for (Student student : students) {
            if (Objects.equals(targetPref, student.getPref())) {
                total += student.getScore();
                count++;
            }
        }

        return (double) total / count;
    }

    private double averageOnStream(List<Student> students, String targetPref) {
        return students.stream().filter(s -> Objects.equals(targetPref, s.getPref()))
                .mapToInt(s -> s.getScore()).average().orElse(0);
    }

    public int sum(List<Student> students, String targetPref) {
        if (mode == Mode.JAVA_7) {
            return sumOnForeach(students, targetPref);
        } else {
            return sumOnStream(students, targetPref);
        }
    }

    private int sumOnForeach(List<Student> students, String targetPref) {
        int total = 0;
        for (Student student : students) {
            if (Objects.isNull(targetPref) || Objects.equals(targetPref, student.getPref())) {
                total += student.getScore();
            }
        }
        return total;
    }

    private int sumOnStream(List<Student> students, String targetPref) {
        return students.stream()
                .filter(s -> Objects.isNull(targetPref) || Objects.equals(targetPref, s.getPref()))
                .mapToInt(s -> s.getScore()).sum();
    }

    public String mapToNames(List<Student> students) {
        if (mode == Mode.JAVA_7) {
            return mapToNamesOnForeach(students);
        } else {
            return mapToNamesOnStream(students);
        }
    }

    private String mapToNamesOnForeach(List<Student> students) {
        List<String> names = new ArrayList<>(students.size());
        for (Student student : students) {
            names.add("[" + student.getName() + "]");
        }
        return Joiner.on(":").join(names);
    }

    private String mapToNamesOnStream(List<Student> students) {
        return students.stream().map(s -> "[" + s.getName() + "]").collect(Collectors.joining(":"));
    }

    public List<Integer> sortedScores(List<Student> students) {
        if (mode == Mode.JAVA_7) {
            return sortedScoresOnForeach(students);
        } else {
            return sortedScoresOnStream(students);
        }
    }

    private List<Integer> sortedScoresOnForeach(List<Student> students) {
        List<Integer> scores = new ArrayList<>(students.size());
        for (Student student : students) {
            scores.add(student.getScore());
        }
        Collections.sort(scores);
        return scores;
    }

    private List<Integer> sortedScoresOnStream(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingInt((ToIntFunction<Student>) Student::getScore))
                .map(s -> s.getScore()).collect(Collectors.toList());
    }

    public int maxScore(List<Student> students) {
        if (mode == Mode.JAVA_7) {
            return maxScoreOnForeach(students);
        } else {
            return maxScoreOnStream(students);
        }
    }

    private int maxScoreOnForeach(List<Student> students) {
        List<Integer> scores = new ArrayList<>(students.size());
        for (Student student : students) {
            scores.add(student.getScore());
        }
        return Collections.max(scores);
    }

    private int maxScoreOnStream(List<Student> students) {
        return students.stream()
                .max(Comparator.comparingInt((ToIntFunction<Student>) Student::getScore)).get()
                .getScore();
    }

    public String distinctPrefs(List<Student> students) {
        if (mode == Mode.JAVA_7) {
            return distinctPrefsOnForeach(students);
        } else {
            return distinctPrefsOnStream(students);
        }
    }

    private String distinctPrefsOnForeach(List<Student> students) {
        // 重複を省く
        Set<String> prefSets = new HashSet<>(students.size());
        for (Student student : students) {
            prefSets.add(student.getPref());
        }

        // ソート
        List<String> prefs = new ArrayList<>(prefSets);
        prefs.sort(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });

        return Joiner.on(":").join(prefs);
    }

    private String distinctPrefsOnStream(List<Student> students) {
        List<String> prefs = students.stream().map(Student::getPref).distinct()
                .collect(Collectors.toList());
        prefs.sort((String o1, String o2) -> o2.compareTo(o1));
        return prefs.stream().collect(Collectors.joining(":"));
    }

    public long countUnderScore(List<Student> students, int targetScore) {
        if (mode == Mode.JAVA_7) {
            return countUnderScoreOnForeach(students, targetScore);
        } else {
            return countUnderScoreOnStream(students, targetScore);
        }
    }

    private long countUnderScoreOnForeach(List<Student> students, int targetScore) {
        long count = 0;
        for (Student student : students) {
            if (student.getScore() < targetScore) {
                count++;
            }
        }
        return count;
    }

    private long countUnderScoreOnStream(List<Student> students, int targetScore) {
        return students.stream().map(s -> s.getScore()).filter(s -> (s < targetScore)).count();
    }

    public boolean hasOverScore(List<Student> students, int targetScore) {
        if (mode == Mode.JAVA_7) {
            return hasOverScoreOnForeach(students, targetScore);
        } else {
            return hasOverScoreOnStream(students, targetScore);
        }
    }

    private boolean hasOverScoreOnForeach(List<Student> students, int targetScore) {
        for (Student student : students) {
            if (student.getScore() > targetScore) {
                return true;
            }
        }
        return false;
    }

    private boolean hasOverScoreOnStream(List<Student> students, int targetScore) {
        return students.stream().map(s -> s.getScore()).anyMatch(score -> (score > targetScore));
    }

    public boolean isNotExistsName(List<Student> students, String targetName) {
        if (mode == Mode.JAVA_7) {
            return isNotExistsNameOnForeach(students, targetName);
        } else {
            return isNotExistsNameOnStream(students, targetName);
        }
    }

    private boolean isNotExistsNameOnForeach(List<Student> students, String targetName) {
        for (Student student : students) {
            if (student.getName().equals(targetName)) {
                return false;
            }
        }
        return true;
    }

    private boolean isNotExistsNameOnStream(List<Student> students, String targetName) {
        return students.stream().map(s -> s.getName()).noneMatch(name -> name.equals(targetName));
    }

    public List<String> findNameByOverScore(List<Student> students, int targetScore) {
        if (mode == Mode.JAVA_7) {
            return findNameByOverScoreOnForeach(students, targetScore);
        } else {
            return findNameByOverScoreOnStream(students, targetScore);
        }
    }

    private List<String> findNameByOverScoreOnForeach(List<Student> students, int targetScore) {
        List<String> names = new ArrayList<>();
        for (Student student : students) {
            if (student.getScore() > targetScore) {
                names.add(student.getName());
            }
        }
        Collections.sort(names);
        return names;
    }

    private List<String> findNameByOverScoreOnStream(List<Student> students, int targetScore) {
        return students.stream().filter(s -> (s.getScore() > targetScore))
                .sorted(Comparator.comparing((Function<Student, String>) Student::getName))
                .map(Student::getName).collect(Collectors.toList());
    }

    public Map<String, Long> countStudentByPref(List<Student> students) {
        if (mode == Mode.JAVA_7) {
            return countStudentByPrefOnForeach(students);
        } else {
            return countStudentByPrefOnStream(students);
        }
    }

    private Map<String, Long> countStudentByPrefOnForeach(List<Student> students) {
        Map<String, Long> counter = new HashMap<>();
        for (Student student : students) {
            Long count = counter.getOrDefault(student.getPref(), 0L);
            counter.put(student.getPref(), (count + 1));
        }
        return counter;
    }

    private Map<String, Long> countStudentByPrefOnStream(List<Student> students) {
        return students.stream().collect(
                Collectors.groupingBy(s -> s.getPref(), Collectors.counting()));
    }

    public enum Mode {
        JAVA_8, JAVA_7;
    }
}

package naotake.learning.guava;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.LineProcessor;
import com.google.common.io.Resources;

/**
 * {@link Resources}に対するテストクラス。
 * 
 * @author naotake
 */
public class ResourcesTest {

    @Test
    public void 指定したパスのリソース情報を読み込めること() throws IOException {
        URL url = Resources.getResource(ResourcesTest.class, "Test1.txt");
        String actual = Resources.toString(url, Charsets.UTF_8);

        // 検証
        assertThat(actual, is("Foo,Bar,Baz"));
    }

    @Test
    public void 指定したパスのリソース情報を行単位で読み込めること() throws IOException {
        URL url = Resources.getResource(ResourcesTest.class, "Test2.txt");
        List<String> actuals = Resources.readLines(url, Charsets.UTF_8);

        // 検証
        assertThat(actuals.size(), is(2));
        assertThat(actuals.get(0), is("Foo,Bar,Baz"));
        assertThat(actuals.get(1), is("Google,Guava"));
    }

    @Test
    public void 指定したパスのリソース情報をオブジェクト単位で読み込めること() throws IOException {
        URL url = Resources.getResource(ResourcesTest.class, "MalePersons.txt");
        List<Person> actuals = Resources.readLines(url, Charsets.UTF_8,
                new ConvertPersonProcessor());

        // 検証
        assertThat(actuals.size(), is(3));
        assertThat(actuals.get(0), PersonMatcher.equalPersonAsMale("Jack", 25));
        assertThat(actuals.get(1), PersonMatcher.equalPersonAsMale("Carry", 19));
        assertThat(actuals.get(2), PersonMatcher.equalPersonAsMale("Alice", 31));
    }

    private static final class ConvertPersonProcessor implements LineProcessor<List<Person>> {

        private final List<Person> persons = Lists.newArrayList();

        @Override
        public boolean processLine(String line) throws IOException {
            List<String> values = Splitter.on(",").splitToList(line);
            if (values.isEmpty()) {
                return false;
            }
            return persons.add(Person.asMale(values.get(0), Integer.valueOf(values.get(1))));
        }

        @Override
        public List<Person> getResult() {
            return persons;
        }
    }

    private static final class PersonMatcher extends TypeSafeMatcher<Person> {

        private final Person expect;

        private PersonMatcher(String name, int age) {
            this.expect = Person.asMale(name, age);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText(expect.toString());
        }

        @Override
        protected boolean matchesSafely(Person actual) {
            return actual.equals(expect);
        }

        public static PersonMatcher equalPersonAsMale(String name, int age) {
            return new PersonMatcher(name, age);
        }
    }
}

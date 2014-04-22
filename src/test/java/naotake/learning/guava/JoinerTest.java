package naotake.learning.guava;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

/**
 * {@link Joiner}に対するテストクラス。
 * 
 * @author naotake
 */
public class JoinerTest {

    private static final String SEPARATOR = ":";

    @Test
    public void testJoinToArrays() {
        String actual = Joiner.on(SEPARATOR).join("Foo", "Bar", "Baz");
        assertThat(actual, is("Foo:Bar:Baz"));
    }

    @Test
    public void testJoinToLists() {
        List<String> lists = Lists.newArrayList("Foo", "Bar", "Baz");
        String actual = Joiner.on(SEPARATOR).join(lists);
        assertThat(actual, is("Foo:Bar:Baz"));
    }

    @Test
    public void testJoinWithNull() {
        String actual = Joiner.on(SEPARATOR).skipNulls().join("Foo", null, "Bar", null, "Baz");
        assertThat(actual, is("Foo:Bar:Baz"));
    }

    @Test
    public void testJoinNullOnly() {
        String value = null;
        String actual = Joiner.on(SEPARATOR).skipNulls().join(value, null);
        assertThat(actual, is(StringUtils.EMPTY));
    }

    @Test
    public void testJoinNullUseOther() {
        String actual = Joiner.on(SEPARATOR).useForNull("other").join("Foo", null, "Baz");
        assertThat(actual, is("Foo:other:Baz"));
    }
}

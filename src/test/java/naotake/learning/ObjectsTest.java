package naotake.learning;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Objects;

import org.junit.Test;

/**
 * Java の{@link Objects}に対するテストクラス。
 * 
 * @author naotake
 */
public class ObjectsTest {

    private Object obj = new Object();

    @Test
    public void testIsNull() {
        assertThat(Objects.isNull(obj), is(false));
        assertThat(Objects.isNull(null), is(true));
    }

    @Test
    public void testNonNull() {
        assertThat(Objects.nonNull(obj), is(true));
        assertThat(Objects.nonNull(null), is(false));
    }

    @Test
    public void testRequireNonNull() {
        assertThat(Objects.requireNonNull(obj), notNullValue());
    }

    @Test(expected = NullPointerException.class)
    public void testRequireNonNullOnNull() {
        Objects.requireNonNull(null);
    }
}

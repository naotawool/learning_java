package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Ignore;
import org.junit.Test;

/**
 * AssertJ を使った基本的な検証の学習。
 *
 * @author naotake
 */
public class BasicLearning {

    @Test
    public void 同値検証() {
        assertThat("Foo").isEqualTo("Foo");
        assertThat("Foo").isNotEqualTo("Bar");
    }

    @Test
    public void null検証() {
        Object actual = null;
        assertThat(actual).isNull();

        actual = "Foo";
        assertThat(actual).isNotNull();
    }

    @Test
    public void compareToによる検証() {
        Foo foo1 = new Foo();
        Foo foo2 = new Foo();

        assertThat(foo1).isEqualByComparingTo(foo2);
    }

    private static final class Foo implements Comparable<Foo> {
        @Override
        public int compareTo(Foo other) {
            return 0;
        }
    }

    @Test
    public void インスタンス検証() {
        Bar bar1 = new Bar();
        Bar bar2 = bar1;

        assertThat(bar1).isSameAs(bar2);
    }

    private static class Bar {
    }

    @Test
    public void 型検証() {
        Baz baz = new Baz();
        Qux qux = new Qux();

        assertThat(baz).isInstanceOf(Baz.class);
        assertThat(qux).isInstanceOf(Baz.class).isInstanceOf(Qux.class);
    }

    private static class Baz {
    }

    private static final class Qux extends Baz {
    }

    @Test
    public void toStringによる検証() {
        FooBar fooBar = new FooBar();

        assertThat(fooBar).hasToString("FooBar");
    }

    private static final class FooBar {
        @Override
        public String toString() {
            return "FooBar";
        }
    }

    @Ignore
    @Test
    public void 失敗時に表示される検証内容を設定() {
        assertThat("Foo").as("AssertJ sample").isEqualTo("Bar");

        // org.junit.ComparisonFailure: [AssertJ sample] expected:<"[Bar]"> but was:<"[Foo]">
    }
}

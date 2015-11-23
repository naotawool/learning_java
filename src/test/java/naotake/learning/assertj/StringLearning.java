package naotake.learning.assertj;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

/**
 * AssertJ を使った文字列検証の学習。
 *
 * @author naotake
 */
public class StringLearning {

    @Test
    public void 接頭辞検証() {
        assertThat("FooBar").startsWith("Foo");
    }

    @Test
    public void 接尾辞検証() {
        assertThat("FooBar").endsWith("Bar");
    }

    @Test
    public void 大文字小文字検証検証() {
        assertThat("Foo").isNotEqualTo("FOO").isEqualToIgnoringCase("FOO");
    }

    /**
     * タブ (\t) も 1 つのスペースとしてカウントされる
     * 先頭と末尾のスペースは無視される
     * 複数のスペースは 1 つにまとめられる
     * 大文字小文字は無視しない
     */
    @Test
    public void スペース無視検証() {
        assertThat("Foo BAR baz").isEqualToIgnoringWhitespace(" Foo   BAR\tbaz ");
        assertThat(" Foo   BAR\tbaz ").isEqualToIgnoringWhitespace("Foo BAR baz");
    }

    @Test
    public void 空文字検証() {
        assertThat("").isEmpty();
    }

    @Test
    public void 空文字null検証() {
        String actual = "";
        assertThat(actual).isNullOrEmpty();

        actual = null;
        assertThat(actual).isNullOrEmpty();
    }

    @Test
    public void 正規表現検証() {
        String actual = "FooBarBaz";
        assertThat(actual).matches("F..B..B..").matches("F.*z");
    }

    @Test
    public void 数字検証() {
        String actual = "1234567890";
        assertThat(actual).containsOnlyDigits();
    }

    @Test
    public void 行数検証() {
        String actual = "foo";
        assertThat(actual).hasLineCount(1);

        actual = "foo\nbar\nbaz";
        assertThat(actual).hasLineCount(3);

        actual = "foo\r\nbar\r\nbaz";
        assertThat(actual).hasLineCount(3);
    }
}

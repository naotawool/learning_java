package naotake.learning;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * {@link LambdaExample}に対するテストクラス。
 * 
 * @author naotake
 */
public class LambdaExampleTest {

    private LambdaExample testee;

    /**
     * 事前処理。
     */
    @Before
    public void setUp() {
        testee = new LambdaExample();
    }

    @Test
    public void testExample() {
        String actual = testee.example();
        assertThat(actual, is("Hello java world : Java8"));
    }
}

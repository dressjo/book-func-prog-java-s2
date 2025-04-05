package fpij.ch8;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

public class DivTest {

    Function<String, String> value = (s) ->  {
        String append = "test";
        return s + append;
    };

    private Function<String, String> createValueCreator(String appendValue) {
        return (s) ->  {
            String append = appendValue;
            return s + append;
        };
    }

    @Test
    void testLambda()  {
        String apply = createValueCreator("appendFirst").compose(createValueCreator("appendSecond")).apply("test");
        System.out.println(apply);
        apply = createValueCreator("appendFirst").andThen(createValueCreator("appendSecond")).apply("test");
        System.out.println(apply);
    }

}

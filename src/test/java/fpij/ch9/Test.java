package fpij.ch9;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class Test {

    public static void main(String[] args) {
        Function<String, String> function = createFunction();

        System.out.println(function.apply("test"));
        System.out.println(function.apply("test"));
    }

    public static Function<String, String> createFunction() {
        final Set<String> cache = new HashSet<>();
        return s -> {
            if(!cache.contains(s)) {
                cache.add(s);
                return s;
            } else {
                return "Cache already exists";
            }
        };
    }

}

package naotake.learning;


public class LambdaExample {

    public String example() {
        Hello hello = str -> {
            return "Hello java world : " + str;
        };
        return hello.show("Java8");
    }

    @FunctionalInterface
    private interface Hello {
        public String show(String str);
    }
}

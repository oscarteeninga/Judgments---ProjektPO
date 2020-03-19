package agh.cs.lab1;

public class AbstracterClass extends AbstractClass {
    public static Object MetodaStatyczna() {
        int sum = 0;
        for (int k = 0; k < 100000; k++) {
            sum += k;
        }
        return new Integer(sum);
    }
}

import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

class StreamOfPrimitives {

    public static LongStream getLongStream(int n) {
        return LongStream.iterate(-n, num -> num <= n, num -> num + 1).filter(num -> num != 0);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        String result = getLongStream(n).mapToObj(e -> e)
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        System.out.println(result);
    }
}
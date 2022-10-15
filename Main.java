import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.IntStream;

public class Main {

    static void num11() {
        try {
            throw new ExceptionClass();
        }
        catch (ExceptionClass e) {
            System.out.println(e);
        }
    }

    static String whereAmI() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
        return ste[3].toString();
    }

    static String num12_2() {
        return whereAmI();
    }

    static String num12_3() {
        return num12_2();
    }

    static void num13() throws Exception {
        try (TryWithResClass s = new TryWithResClass()) {
        }
    }

    static void num17() throws IOException {
        Path path = Paths.get("src/input.txt");
        List<String> lines = Files.readAllLines(path);
        int n = lines.size();
        int [][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                List<Integer> i_str = Arrays.stream(lines.get(i).split(" ")).map(s -> Integer.parseInt(s)).toList();
                arr[i][j] = i_str.get(j);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("---------------");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[j][i] + " ");
            }
            System.out.println();
        }
    }

    static void num19() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        arr.removeIf(a -> arr.indexOf(a) % 2 == 0);
        Collections.reverse(arr);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
    }

    static UnaryOperator<Integer> get_op() {

        UnaryOperator<Integer> operator = i -> (int) Math.pow(i,2);
        return operator;
    }

    static void num20() {
        UnaryOperator<Integer> op = get_op();
        System.out.println(op.apply(3));
    }

    static <T> ArrayList<T> getArr(ArrayList<T> arr, ArrayList<Integer> indexes) {
        arr.removeIf(a -> indexes.contains(arr.indexOf(a)));
        return arr;
    }

    static void num21() {
        ArrayList<Integer> arr = new ArrayList<>();
        ArrayList<Integer> ind = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            arr.add(i);
            if (i % 3 == 0) ind.add(i);
        }
        arr = getArr(arr,ind);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
    }


    public static void main(String[] args) throws Exception {
        num21();
    }
}
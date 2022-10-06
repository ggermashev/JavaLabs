import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    public static void main(String[] args) throws Exception {
        num17();
    }
}

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
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
        Path path = Paths.get("input.txt");
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
        LinkedList<Integer> arr = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            arr.add(in.nextInt());
        }
        LinkedList<Integer> res = new LinkedList<>();
        for (int i = 0; i < n; i ++) {
            if (i % 2 == 0) {
                res.add(arr.peekLast());
            }
            arr.pollLast();
        }
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i) + " ");
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

    static boolean check_3(Integer x) {
        return x % 3 == 0;
    }

    static <T> ArrayList<T> getArr(ArrayList<T> arr, Predicate<T> condition) {
        arr.removeIf(a -> condition.test(a));
        return arr;
    }

    interface Condition{
        <T >boolean check(T x);
    }
    static void num21() {
        ArrayList<String> arr = new ArrayList<>();
        ArrayList<String> ind = new ArrayList<>();
        arr.add("aaaa");
        arr.add("aa");
        arr.add("aaaaa");
        arr.add("aaaaa");
        arr = getArr(arr, a -> a.length()<3);
        for (int i = 0; i < arr.size(); i++) {
            System.out.print(arr.get(i) + " ");
        }
    }

    static <T> Map<T, Integer> getMap(T[] arr) {
        Set<T> arr_set = new HashSet<T>();
        for (T a: arr) {
            arr_set.add(a);
        }
        HashMap<T, Integer> map = new HashMap<T, Integer>();
        for (T s: arr_set) {
            map.put(s, (int)Arrays.stream(arr).filter(a -> a==s).count());
        }
        return map;
    }
    static void num22() {
        Map<Integer,Integer> map = getMap(new Integer[] {1,2,3,2,1,1});
        for (Map.Entry<Integer,Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    static<K,V> Map<V,LinkedList<K>> swapMap(Map<K,V> map) {
        Map<V,LinkedList<K>> res = new HashMap<>();
        for (Map.Entry<K,V> entry: map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            LinkedList<K> res_values = res.get(value);
            if (res_values == null) {
                LinkedList<K> values = new LinkedList<>();
                values.add(key);
                res.put(value, values);
            }
            else {
                res_values.add(key);
                res.put(value, res_values);
            }
        }
        return res;
    }

    static void num23() {
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(1,2);
        map.put(3,2);
        map.put(2,3);
        map.put(5,2);
        Map<Integer,LinkedList<Integer>> newmap = swapMap(map);
        for (Map.Entry<Integer,LinkedList<Integer>> entry: newmap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    static void num24() {
        ArrayList<User> users = new ArrayList<>();
        User user1 = new User("l2","f2", 30,"c1");
        User user2 = new User("l1","f1", 40,"c1");
        User user3 = new User("l3","f3", 6,"c1");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        List<User> sortedByLastName = User.sortByLastName(users);
        for (User u: sortedByLastName) {
            System.out.println(u);
        }
        System.out.println("--------------------------");
        List<User> sortedByAge = User.sortByAge(users);
        for (User u: sortedByAge) {
            System.out.println(u);
        }
        System.out.println("------------");
        System.out.println(User.checkAge(users));
        System.out.println("--------------");
        System.out.println(User.getAvarageAge(users));
        System.out.println("-----------------------");
        System.out.println(User.getUniqueCountries(users));
    }

    static List<String> get_more_often_words(String str) {
        HashMap<String, Integer> words = new HashMap<>();
        Arrays.stream(str.split(" ")).collect(Collectors.toSet()).
                forEach(s -> words.put(s, (int) Arrays.stream(str.split(" ")).
                        filter(s1 -> s1.equals(s)).count()));
        return words.entrySet().stream().sorted((e1,e2)-> !e2.getValue().equals(e1.getValue()) ? e2.getValue() - e1.getValue() : e1.getKey().compareTo(e2.getKey())).map(e->e.getKey()).limit(11).collect(Collectors.toList());
    }
    static void num25() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        str = str.replaceAll("[^A-Za-zА-Яа-я0-9]"," ").toLowerCase();
        List<String> words = get_more_often_words(str);
        for (String s: words) {
            System.out.println(s);
        }
    }
//12 17 19 21 23
    public static void main(String[] args) throws Exception {
        //System.out.println(num12_2());
        //num17();
        //num19();
        //num21();
        num23();
    }
}
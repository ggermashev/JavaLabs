import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class User {
    private static int id_total;
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String country;

    static {
        id_total = 0;
    }

    public User(String firstName, String lastName, int age, String country) {
        this.id = ++id_total;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public User() {
        id = ++id_total;
        firstName = null;
        lastName = null;
        age = 0;
        country = null;
    }

    public static List<User> sortByLastName(ArrayList<User> users) {
        return users.stream().sorted((a,b) -> a.lastName.compareToIgnoreCase(b.lastName)).collect(Collectors.toList());
    }

    public static List<User> sortByAge(ArrayList<User> users) {
        return users.stream().sorted((a,b) -> a.age - b.age).collect(Collectors.toList());
    }

    public static boolean checkAge(ArrayList<User> users) {
        return users.stream().filter(u-> u.age <= 7).collect(Collectors.toList()).size() == 0;
    }


    public static Double getAvarageAge(ArrayList<User> users) {
        return users.stream().mapToInt(u-> u.age).average().getAsDouble();
    }

    public static Set<String> getUniqueCountries(ArrayList<User> users) {
        return users.stream().map(u->u.country).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return (lastName + " " + firstName + " age:" + age + "Country: " + country);
    }
}

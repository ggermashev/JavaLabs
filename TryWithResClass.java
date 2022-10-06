import java.util.ResourceBundle;
import java.util.logging.StreamHandler;

public class TryWithResClass implements AutoCloseable {

    @Override
    public void close() throws Exception {
        System.out.println("closing...");
    }
}

import com.util.FileJSONUtil;
import org.junit.Test;

public class TestJson {
    @Test
    public void testJson() {
        FileJSONUtil.getJSONList("src/main/resources/json/javafx-src.json");

    }
}

import com.frame.MainFrame;
import org.junit.Test;

public class TestFrame {
    MainFrame mainFrame = new MainFrame();

    @Test
    public void testMainFrame() {
        mainFrame.init();
    }
}

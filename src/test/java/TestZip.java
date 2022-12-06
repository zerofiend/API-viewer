import com.service.FileService;
import com.service.impl.FileServiceImpl;
import org.junit.Test;

import java.io.IOException;

public class TestZip {
    FileService fileService = new FileServiceImpl();

    @Test
    public void testZipReader() {
        fileService.zipReader("D:\\JAVA\\jdk1.8.0_202\\src.zip", "src");
    }

    @Test
    public void testFolderReader() {
        fileService.folderReader("D:\\JAVA\\jdk1.8.0_202\\jre\\lib\\rt");
    }

    @Test
    public void testJarReader() {
        fileService.jarReader("D:\\JAVA\\jdk1.8.0_202\\jre\\lib\\rt.jar", "rt");
    }

    @Test
    public void testZipShow() throws IOException {
        fileService.zipShow("D:\\JAVA\\jdk1.8.0_202\\jre\\lib\\rt.jar");
    }
}

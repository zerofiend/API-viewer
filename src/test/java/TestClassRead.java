import com.service.FileService;
import com.service.impl.FileServiceImpl;
import com.util.ClassUtil;
import org.junit.Test;

import java.io.IOException;
import java.util.jar.JarFile;

/**
 * @Description TODO TestClassRead
 * @Author ZFiend
 * @Create 2023.02.14 14:31
 */
public class TestClassRead {
    FileService fileService = new FileServiceImpl();

    @Test
    public void test() {
//        fileService.classShow("src/main/resources/unzip/dt/javax/swing/AbstractButtonBeanInfo.class");
//        FileJSONUtil.getJsonByZip("src/main/resources/dt.jar", "");
        try {
            JarFile jarFile = new JarFile("src/main/resources/dt.jar");
            ClassUtil.findClassesByJar("javax/swing/AbstractButtonBeanInfo", jarFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

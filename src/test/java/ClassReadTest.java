import com.service.FileService;
import com.service.impl.FileServiceImpl;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @Description TODO TestClassRead
 * @Author ZFiend
 * @Create 2023.02.14 14:31
 */
public class ClassReadTest {
    FileService fileService = new FileServiceImpl();

    @Test
    public void test() {
        fileService.classShow("src/main/resources/unzip/dt/javax/swing/AbstractButtonBeanInfo.class");
//        FileJSONUtil.getJsonByZip("src/main/resources/dt.jar", "");
/*        try {
            JarFile jarFile = new JarFile("src/main/resources/dt.jar");
            ClassUtil.findClassesByJar("javax/swing/AbstractButtonBeanInfo", jarFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    @Test
    public void test2() {
        String path = "lib/";
        String fileName = "dt.jar";
        try {
            JarFile jarFile = new JarFile(path + fileName);

            Enumeration<JarEntry> e = jarFile.entries();

            JarEntry entry;
            while (e.hasMoreElements()) {
                entry = (JarEntry) e.nextElement();
                //
                if (entry.getName().indexOf("META-INF") < 0 && entry.getName().indexOf(".class") >= 0) {
                    String classFullName = entry.getName();
                    //去掉后缀.class
                    String className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                    System.out.println(className);

                    Class<?> c = null;
                    try {
                        try {
                            // 用className这个类来装载c,但还没有实例化
                            c = Class.forName(className);
                        } catch (ClassNotFoundException e1) {
                            e1.printStackTrace();
                        }
                        Method[] methods = c.getMethods();
                        for (Method method : methods) {
                            String methodName = method.getName();
                            System.out.println("方法名称:" + methodName);
                            System.out.println("返回值类型" + method.getReturnType());
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            for (Class<?> clas : parameterTypes) {
                                // String parameterName = clas.getName();
                                String parameterName = clas.getSimpleName();
                                System.out.println("参数类型:" + parameterName);
                            }
                            System.out.println("==========================");
                        }
                    } catch (Exception e1) {

                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

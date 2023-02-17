package com.service.impl;

import com.entity.FileOrFolder;
import com.frame.MainFrame;
import com.service.FileService;
import com.util.FIlePathUtil;
import com.util.FileJSONUtil;
import com.util.UnzipUtil;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FileServiceImpl implements FileService {
    List<FileOrFolder> fileList;

    @Override
    public String zipReader(String path, String filename) {
        String unzipPath = UnzipUtil.unzip(path, filename);
//        JavaDocUtil.createDoc(Paths.get(FIlePathUtil.UNZIP_PATH, filename).toString());
        FileJSONUtil.getJsonByPath(unzipPath, filename);
        return unzipPath;
    }

    @Override
    public String jarReader(String path, String filename) {
        String unJarPath = UnzipUtil.unJar(path, String.valueOf(Paths.get(FIlePathUtil.UNZIP_PATH, filename)));
        FileJSONUtil.getJsonByPath(unJarPath, filename);
        return unJarPath;
    }

    @Override
    public void folderReader(String path) {
        FileJSONUtil.getJsonByPath(path, "folder");
    }

    @Override
    public void zipShow(String path) throws IOException {
        String s = UnzipUtil.ZIPShow(path);
    }

    @Override
    public String javaShow(String path) {
        String absolutePath = null;
        String filePath = Paths.get(FIlePathUtil.UNZIP_PATH, path).toString();
        String docPath = Paths.get(FIlePathUtil.JAVADOC_PATH, path).toString();
        // 文件加载时动画
        MainFrame.createWait("正在加载文件，请稍后...");
        MainFrame.startWait();
        try {
            Runtime.getRuntime().exec("javadoc -d " + docPath + " -encoding utf-8 -charset utf-8 " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            File file = Paths.get(docPath, "index.html").toFile();
            while (!file.exists()) {
                file = Paths.get(docPath, "index.html").toFile();
            }
            absolutePath = "file:" + file.getAbsolutePath();
        }
        MainFrame.stopWait();
        return absolutePath;
    }

    @Override
    public String classShow(String path) {
        String filePath = path.replace("/", "\\").replace(FIlePathUtil.UNZIP_PATH + "\\", "");
        String jarPath = filePath.substring(0, filePath.indexOf("\\")) + "." + FIlePathUtil.JAR_SUFFIX;
        filePath = filePath.substring(filePath.indexOf("\\") + 1);
        StringBuilder sb = new StringBuilder();
        try {
            JarFile jarFile = new JarFile(Paths.get(FIlePathUtil.JAR_PATH, jarPath).toString());

            Enumeration<JarEntry> e = jarFile.entries();

            JarEntry entry;
            while (e.hasMoreElements()) {
                entry = (JarEntry) e.nextElement();
                //
                if (!entry.getName().contains("META-INF") && entry.getName().equals(filePath.replaceAll("\\\\",
                        "/"))) {
                    String classFullName = entry.getName();
                    //去掉后缀.class
                    String className = classFullName.substring(0, classFullName.length() - 6).replace("/", ".");
                    sb.append(className).append("\n");
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
                            sb.append("方法名称:").append(methodName).append("\n");
                            sb.append("返回值类型").append(method.getReturnType()).append("\n");
                            Class<?>[] parameterTypes = method.getParameterTypes();
                            for (Class<?> clas : parameterTypes) {
                                // String parameterName = clas.getName();
                                String parameterName = clas.getSimpleName();
                                System.out.println("参数类型:" + parameterName);
                                sb.append("参数类型:").append(parameterName).append("\n");
                            }
                            System.out.println("==========================");
                            sb.append("==========================").append("\n");
                        }
                    } catch (SecurityException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}

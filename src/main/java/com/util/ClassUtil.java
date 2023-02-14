package com.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ClassUtil {

    /**
     * @description: TODO [findClassesByJar] 获取指定jar包目录下的class文件
     * @author: ZFiend
     * @date: 2023/2/14 15:44
     * @param: pkgName
     * @param: jar
     * @param: classes
     * @return: void
     */
    public static void findClassesByJar(String pkgName, JarFile jar) {
        String pkgDir = pkgName.replace(".", "/");
        // 从此jar包 得到一个枚举类
        Enumeration<JarEntry> entry = jar.entries();

        JarEntry jarEntry;
        String name, className;
        Class<?> claze = null;
        // 同样的进行循环迭代
        while (entry.hasMoreElements()) {
            // 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文
            jarEntry = entry.nextElement();

            name = jarEntry.getName();
            // 如果是以/开头的
            if (name.charAt(0) == '/') {
                // 获取后面的字符串
                name = name.substring(1);
            }

            if (jarEntry.isDirectory() || !name.startsWith(pkgDir) || !name.endsWith(".class")) {
                continue;
            }
            //如果是一个.class文件 而且不是目录
            // 去掉后面的".class" 获取真正的类名
            className = name.substring(0, name.length() - 6);
            //加载类
            claze = loadClass(className.replace("/", "."));
        }
        System.out.println(claze);
    }

    /**
     * 获取项目中包下的class 使用FileUtils.listFile()方法，需要依赖commons-io
     * <dependency>
     * <groupId>commons-io</groupId>
     * <artifactId>commons-io</artifactId>
     * <version>2.6</version>
     * </dependency>
     *
     * @param pkgName 包名
     * @param pkgPath 包的绝对路径
     * @param classes
     * @return
     * @Author 嗨哥
     */
    private static void findClassesByFile2(String pkgName, String pkgPath, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(pkgPath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        // 如果存在 就获取包下的所有class
        //FileUtils.listFiles()
        Collection<File> files = FileUtils.listFiles(dir, new String[]{"class"}, true);

        files.forEach(file -> {
            //加载类
            Class clz = loadClass(pkgName + "." + file.getName().split("\\.")[0]);
            // 添加到集合中去
            if (clz != null) {
                classes.add(clz);
            }
        });
    }

    /**
     * 获取项目中包下的class 递归遍历子包
     * 不想引入common-io包的可以用这个方法
     *
     * @param pkgName 包名
     * @param pkgPath 包的绝对路径
     * @param classes
     * @return
     * @Author 嗨哥
     */
    private static void findClassesByFile(String pkgName, String pkgPath, Set<Class<?>> classes) {
        // 获取此包的目录 建立一个File
        File dir = new File(pkgPath);
        // 如果不存在或者 也不是目录就直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        // 如果存在 就获取包下的所有文件 包括目录
        File[] dirfiles = dir.listFiles(pathname -> pathname.isDirectory() || pathname.getName().endsWith("class"));

        if (dirfiles == null || dirfiles.length == 0) {
            return;
        }

        String className;
        Class clz;
        // 循环所有文件
        for (File f : dirfiles) {
            // 如果是目录 则继续扫描
            if (f.isDirectory()) {
                findClassesByFile(pkgName + "." + f.getName(), pkgPath + "/" + f.getName(), classes);
                continue;
            }
            // 如果是java类文件 去掉后面的.class 只留下类名
            className = f.getName();
            className = className.substring(0, className.length() - 6);

            //加载类
            clz = loadClass(pkgName + "." + className);
            // 添加到集合中去
            if (clz != null) {
                classes.add(clz);
            }
        }
    }

    /**
     * 加载类
     *
     * @param fullClzName 类全名
     * @return
     */
    private static Class<?> loadClass(String fullClzName) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(fullClzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}


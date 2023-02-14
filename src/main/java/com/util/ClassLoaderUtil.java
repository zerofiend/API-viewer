package com.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Description TODO ClassLoaderUtil 类加载工具包
 * @Author ZFiend
 * @Create 2023.02.14 14:31
 */
public class ClassLoaderUtil extends ClassLoader {

    /**
     * @description: TODO [findClass] 类的文件名
     * @author: ZFiend
     * @date: 2023/2/14 14:44
     * @param: name
     * @return: java.lang.Class<?>
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] datas = loadClassData(name);
        return defineClass(name, datas, 0, datas.length);
    }

    // 指定文件目录
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @description: TODO [loadClassData] 加载类文件数据
     * @author: ZFiend
     * @date: 2023/2/14 14:45
     * @param: name
     * @return: byte[]
     */
    protected byte[] loadClassData(String name) {
        FileInputStream fis = null;
        byte[] datas = null;
        try {
            fis = new FileInputStream(location + name + ".class");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            int b;
            while ((b = fis.read()) != -1) {
                bos.write(b);
            }
            datas = bos.toByteArray();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null)
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return datas;

    }
}

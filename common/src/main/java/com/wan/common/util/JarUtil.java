package com.wan.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by w1992wishes on 2017/8/12.
 */
public class JarUtil {

    private static Logger logger = LoggerFactory.getLogger(JarUtil.class);
    /**
     * 解压jar包到指定的文件目录
     * @param fileName
     * @param outputPath
     */
    public static synchronized void decompress(String fileName, String outputPath){
        // 保证输出路径为目录
        if (!outputPath.endsWith((File.separator))){
            outputPath += File.separator;
        }
        //如果不存在输出目录，则创建
        File dir = new File(outputPath);
        if (!dir.exists()){
            dir.mkdirs();
        }
        //解压到输出目录
        JarFile jf = null;
        try {
            jf = new JarFile(fileName);
            for (Enumeration<JarEntry> e = jf.entries(); e.hasMoreElements(); ){
                JarEntry je = e.nextElement();
                String outFileName = outputPath + je.getName();
                File f = new File(outFileName);
                if (je.isDirectory()){
                    if (!f.exists()){
                        f.mkdirs();
                    }
                }else {
                    File pf = f.getParentFile();
                    if (!pf.exists()){
                        pf.mkdirs();
                    }
                    InputStream in = jf.getInputStream(je);
                    OutputStream out = new BufferedOutputStream(new FileOutputStream(f));
                    byte[] buffer = new byte[2048];
                    int nBytes;
                    while ((nBytes = in.read(buffer)) > 0){
                        out.write(buffer, 0, nBytes);
                    }
                    out.flush();
                    out.close();
                    in.close();
                }
            }
        } catch (IOException e) {
            logger.error("Decompress {} error {}!", fileName, e.getMessage());
        }finally {
            if (jf != null){
                try {
                    jf.close();
                } catch (IOException e) {
                    logger.error("", e);
                }
            }
        }
    }
}

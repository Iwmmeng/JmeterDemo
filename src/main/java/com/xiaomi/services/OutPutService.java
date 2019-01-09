package com.xiaomi.services;

import java.io.File;
import java.io.PrintWriter;

/**
 * @author wangmeng
 * @date 19/1/7
 */
public class OutPutService {
    public  void output(String filename,int a, int b) throws Exception {
        PrintWriter out = new PrintWriter(new File(filename));
        out.write(a+":"+b);
        out.close();
    }
}

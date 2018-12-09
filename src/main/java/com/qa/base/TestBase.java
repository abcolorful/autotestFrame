package com.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by liweiwei.li on 2018/12/8.
 */
public class TestBase {
    public Properties prop;
    public int RESPONSE_STATUS_CODE_200 = 200;
    public int RESPONSE_STATUS_CODE_201 = 201;
    public int RESPONSE_STATUS_CODE_404 = 404;
    public int RESPONSE_STATUS_CODE_500 = 500;

    //写一个构造函数
    public TestBase() {
        try {
            prop = new Properties();
            FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/com/qa/config/config.properties");
            prop.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



}
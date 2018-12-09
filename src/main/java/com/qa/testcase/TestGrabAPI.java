package com.qa.testcase;

import com.alibaba.fastjson.JSONObject;
import com.qa.base.TestBase;
import com.qa.restclient.RestClient;
import com.qa.util.ParseKey;
import junit.framework.TestCase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liweiwei.li on 2018/12/9.
 */
public class TestGrabAPI extends TestCase {
    TestBase testBase;
    String host;
    String url;
    RestClient restClient;


    @BeforeClass
    public void setUp() {
        testBase = new TestBase();
        host = testBase.prop.getProperty("HOST");


    }

    @Test
    public void testMutiLocations() throws IOException {
        url = host+"&location=Denver%2C+CO";
        restClient = new RestClient();
        CloseableHttpResponse res = restClient.get(url);
        Map<String, String> resMap = new HashMap<String, String>();
        JSONObject jsonObject = restClient.getResponseJson(res);
        resMap = ParseKey.parseJsonObject(jsonObject);
        Assert.assertEquals(resMap.get("ignoreLatLngInput"),"false","ignoreLatLngInput不为false");
        Assert.assertEquals(resMap.get("latLng"),"[{\"lng\":-104.984853,\"lat\":39.738453}, {\"lng\":-104.984853,\"lat\":39.738453}]","latLng期望错误");
    }

    @Test
    public void testSingleLocations() throws IOException {
        url = host+"&location=nanbu";
        restClient = new RestClient();
        CloseableHttpResponse res = restClient.get(url);
        Map<String, String> resMap = new HashMap<String, String>();
        JSONObject jsonObject = restClient.getResponseJson(res);
        resMap = ParseKey.parseJsonObject(jsonObject);
        Assert.assertEquals(resMap.get("ignoreLatLngInput"),"false","ignoreLatLngInput不为false");
        Assert.assertEquals(resMap.get("latLng"),"[{\"lng\":105.905855,\"lat\":31.308495}]","single的latLng期望错误");
    }
}

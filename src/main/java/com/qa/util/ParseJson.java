package com.qa.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by liweiwei.li on 2018/12/8.
 */
public class ParseJson {
    //1 json解析方法


    public static Map<String,String> getKeyValue(CloseableHttpResponse closeableHttpResponse) throws  IOException {
        Map<String, String> map = new HashMap<String, String>();
        //把响应内容存储在字符串对象
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        //创建Json对象，把上面字符串序列化成Json对象
        JSONObject responseJson = JSON.parseObject(responseString);
        Object obj = responseJson;
        return parseJsonToMap(obj ,map);
    }

   public static Map<String,String> parseJsonToMap(Object obj ,Map<String,String> map) {
        if(obj instanceof JSONArray) {
            JSONArray jsonArray = (JSONArray) obj;
            for (int i = 0; i < jsonArray.size(); i++) {
                parseJsonToMap(jsonArray.get(i), map);
            }
        } else if(obj instanceof JSONObject){
            JSONObject jsonObject = (JSONObject) obj;
            Set<String> keySet = jsonObject.keySet();
            Iterator it = keySet.iterator();
            while (it.hasNext()) {
                String key= it.next().toString();
                Object object = jsonObject.get(key);
                if(object != null){
                    if(object instanceof JSONArray) {
                        JSONArray jsonArray = (JSONArray) object;
                        parseJsonToMap(jsonArray, map);
                    }else if (object instanceof JSONObject) {
                        parseJsonToMap(object, map);
                    }else {
                        map.put(key,object.toString());
                    }

                }else{
                    map.put(key, null);
                }
            }
        }
       return map;
}

}


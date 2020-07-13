package org.example;

import static org.junit.Assert.assertTrue;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.example.test.build.a.ConcreteBuilder;
import org.example.test.build.a.Director;
import org.example.test.build.a.Product;
import org.example.test.decorator.ABattercake;
import org.example.test.decorator.Battercake;
import org.example.test.decorator.EggDecorator;
import org.example.test.decorator.SausageDecorator;
import org.example.test.factory.Provider;
import org.example.test.factory.SendMailFactory;
import org.example.test.factory.Sender;
import org.junit.Test;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Unit test for simple App.
 */
public class AppTest 
{

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test20200402() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        System.out.println(sdf.format(d));
    }

    @Test
    public void test20200409() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyymmddHHmmss");
        System.out.println(sdf.format(new Date()));
        Date Date = new Date(new Date().getTime() + (1000 * 60 * 15));
        String format = sdf.format(Date);
        System.out.println(sdf.format(Date));
    }


    @Test
    public void test2020040902() {
        System.out.println("hhh".equals(null));
    }

    @Test
    public void test20200411() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = sdf.parse("2020-04-12 02:02:02");
        Date date = sdf.parse("2020-04-11 08:02:02");
        System.out.println("date:" + date);
        Date now = new Date();
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        String format = sdf.format(now);
        System.out.println("format:" + format);
        Date parse = sdf.parse(format);
        System.out.println("parse:" + parse);
        long day = 24l * 60l * 60l * 1000l;
        System.out.println((date.getTime() - date2.getTime()) / day);
        System.out.println((parse.getTime() - date2.getTime()) / day);
    }

    @Test
    public void test2020041102() {
        List list = new ArrayList();
        list.add(0);
        list.add(1);
        List list2 = new ArrayList();
        list2.add(2);
        list2.add(3);
        list2.add(4);
        list2.add(5);
        list2.addAll(list);
        System.out.println(list2);
    }

    @Test
    public void doString() {
        String s = "江北区,渝北区,南岸区";
        String s2 = ",渝北区";
        String replace = s.replace(s2, "");
        System.out.println(replace);
    }

    @Test
    public void test20200425() {
        String s = "";
        s = "test20200425";
        System.out.println(s);
    }

    @Test
    public void testMap() {
        Map map = new HashMap();
        map.put("姓名", "张三");
        System.out.println(map);
        map.put("姓名", "李四");
        System.out.println(map);
    }

    @Test
    public void testHttps() throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet("https://restapi.amap.com/v3/geocode/geo?key=6fde9d6d37ca776fac472a79fef8be63&address=重庆市南岸区茉莉路125号");
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String s = "";
        JSONObject jsonObject = null;
        try {
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            s = EntityUtils.toString(entity, "utf-8");
            jsonObject = JSONObject.parseObject(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
        }
        int statusCode = response.getStatusLine().getStatusCode();

        System.out.println(s);
        System.out.println(jsonObject);
        JSONArray geocodes = jsonObject.getJSONArray("geocodes");
        Object o = geocodes.get(0);
        String location = JSONObject.parseObject(o.toString()).getString("location");
        System.out.println("location:" + location);
        String[] split = location.split(",");
        System.out.println(split[0]);
        System.out.println(split[1]);
    }

    /**
     * 递归
     * @param n
     */
    public void recursion(int n) {
        int temp = n;
        System.out.println("递进：" + temp);
        if (n > 0) {
            recursion(--n);
        }
        System.out.println("回归：" + temp);
    }
    @Test
    public void testRecursion() {
        recursion(10);
    }

    /**
     * 工厂模式
     */
    @Test
    public void testFactory() {
        Provider provider = new SendMailFactory();
        Sender sender = provider.provider();
        sender.send();
    }

    /**
     * 建造者模式
     */
    @Test
    public void testBuildA() {
        Director director = new Director();
        Product product = director.create(new ConcreteBuilder());
        System.out.println(product);
    }

    /**
     * 装饰者模式
     */
    @Test
    public void testDecorator() {
        ABattercake aBattercake;
        aBattercake = new Battercake();
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new EggDecorator(aBattercake);
        aBattercake = new SausageDecorator(aBattercake);
        System.out.println(aBattercake.getDesc() + ",价格" + aBattercake.cost());
    }

    @Test
    public void testGetSecond() {
        long l = System.currentTimeMillis();
        Date date1 = new Date(l);
        l += 30*60*1000;
        Date date = new Date(l);
        System.out.println("当前时间：" + date1);
        System.out.println("三十分钟之后：" + date);
        long l1 = date.getTime() - date1.getTime();
        long l2 = l1 / 1000;
        System.out.println(l2);
    }

    @Test
    public void testLongInt() {
        Object o = 123456l;
        Long l = Long.valueOf(o.toString());
        int i = l.intValue();
        System.out.println(i);
    }

    @Test
    public void testString() {
        String s = "<p data-v-3d62bbf1=\"\">测试学校简介4</p><p><br></p>";
        int i = s.indexOf(">");
        System.out.println(i);
        int j = s.indexOf("</p>");
        System.out.println(j);
        String substring = s.substring(i + 1, j);
        System.out.println(substring);
    }

}

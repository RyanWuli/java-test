package com.example.redishash;

import com.example.redishash.entity.UserVo;
import com.example.redishash.utils.RedisKeyUtil;
import com.example.redishash.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.scheduling.support.SimpleTriggerContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class RedisHashApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testObj() {
        UserVo userVo = new UserVo("叮当猫", "上海", 16);
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        redisUtil.expireKey("name", 20, TimeUnit.SECONDS);
        valueOperations.set("u", userVo);
        String name = RedisKeyUtil.getKey(UserVo.TABLE, "name", userVo.getName());
        UserVo uv = (UserVo) operations.get(name);
        System.out.println(uv);
    }

    @Test
    public void testValueOption() {
        UserVo userVo = new UserVo("大熊", "东京", 18);
        valueOperations.set("user", userVo);
        System.out.println(valueOperations.get("user"));
    }

    @Test
    public void testSetOperation() {
        UserVo userVo = new UserVo("大熊", "东京", 18);
        UserVo userVo2 = new UserVo("叮当猫", "大阪", 16);
        setOperations.add("user2:test", userVo, userVo2);
        Set<Object> members = setOperations.members("user2:test");
        System.out.println(members);
    }

    @Test
    public void HashOperations() {
        UserVo userVo = new UserVo("大熊", "东京", 18);
        hashOperations.put("hash:user", userVo.hashCode() + "", userVo);
        System.out.println(hashOperations.get("hash:user", userVo.hashCode() + ""));
    }

    @Test
    public void ListOperations() {
        UserVo userVo2 = new UserVo("叮当猫", "大阪", 16);
//        listOperations.leftPush("list:user", userVo2);
        System.out.println(listOperations.leftPop("list:user"));
    }


    /**
     * 获取每月的最后一天
     */
    @Test
    public void testLastMonth() {
        Calendar instance = Calendar.getInstance();
        instance.set(Calendar.DAY_OF_MONTH, 1);
        instance.add(Calendar.DATE, -1);
        Date time = instance.getTime();
        System.out.println("calendar:" + instance);
        System.out.println("date:" + time);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String tim = sdf.format(time);
        System.out.println("tim:" + tim);
    }

    /**
     * 是否当月最后一天
     */
    @Test
    public void testIsLastDayOfMonth() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse("2018-02-02 10:54:00");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        System.out.println(date);
        System.out.println("c:" + c);
        System.out.println(c.get(Calendar.DATE));
        System.out.println(c.getActualMaximum(Calendar.DATE));
        if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
            //是最后一天
            System.out.println("信息员等级升降级。。。。");
        } else {
            System.out.println("不是月末天");
        }
    }

    public void testCommit() {

    }

    @Test
    public void testApp() {
        String s = "2020-05";
        String sub = s.substring(s.indexOf("-") + 1);
        System.out.println(sub);
    }

    @Test
    public void testUUID() {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());

    }

    @Test
    public void transfer() {
        System.out.println("\u6570\u636E\u5E93\u7D22\u5F15\uFF0C\u9ED8\u8BA4\u4E3A0");
    }


}

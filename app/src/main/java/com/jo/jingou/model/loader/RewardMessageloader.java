package com.jo.jingou.model.loader;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dfyu on 2017/5/11.
 */
public class RewardMessageloader {


    static final String[] FirstName = new String[]{
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王",
            "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨",
            "朱", "秦", "尤", "许", "何", "吕", "施", "张",
            "孔", "曹", "严", "华", "金", "魏", "陶", "姜",
            "戚", "谢", "邹", "喻", "柏", "水", "窦", "章",
            "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
            "鲁", "韦", "昌", "马", "苗", "凤", "花", "方",
            "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐",
            "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤",
            "滕", "殷", "罗", "毕", "郝", "邬", "安", "常",
            "乐", "于", "时", "傅", "皮", "卞", "齐", "康",
            "伍", "余", "元", "卜", "顾", "孟", "平", "黄",
            "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪",
            "祁", "毛", "禹", "狄", "米", "贝", "明", "臧",
            "计", "伏", "成", "戴", "谈", "宋", "茅", "庞",
            "熊", "纪", "舒", "屈", "项", "祝", "董", "梁",
            "杜", "阮", "蓝", "闽", "席", "季", "麻", "强",
            "贾", "路", "娄", "危", "江", "童", "颜", "郭",
            "梅", "盛", "林", "刁", "锺", "徐", "丘", "骆",
            "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍",
            "虞", "万", "支", "柯", "昝", "管", "卢", "莫",
            "经", "房", "裘", "缪", "干", "解", "应", "宗",
            "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪",
            "包", "诸", "左", "石", "崔", "吉", "钮", "龚",
            "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁"};

    static final String[] ManOrWeman = new String[]{"先生", "女士"};
    static final int[] Phone = new int[]{
            133, 153, 180, 181, 189, 177, 173, 149,
            130, 131, 132, 155, 156, 145, 185, 186, 176, 175,
            134, 135, 136, 137, 138, 139, 150, 151, 152, 157, 158, 159, 182, 183, 184, 187, 188, 147, 178};

    //张嘉佳（131****9912）获得平台分红￥700
    public static List<String> getRewardMessage() {
        List<String> rewardmessage = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String message =
                    FirstName[random.nextInt(FirstName.length)] +
                            ManOrWeman[random.nextInt(ManOrWeman.length)] +
                            "(" +
                            Phone[random.nextInt(Phone.length)] +
                            "****" +
                            (int) (Math.random() * 9000 + 1000) +
                            ")获得平台分红￥" +
                            ((random.nextInt(10) + 1) * 100);
            //Log.i("TAG", "Message======" + message);
            rewardmessage.add(message);
        }


        return rewardmessage;
    }


}

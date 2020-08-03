package com.autuan.project.promote.base.constant;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author : Autuan.Yu
 * @description :  描述
 * @remark : 备注
 * @date : 2020/8/3 11:28
 * @company : 上海奥若拉信息科技集团有限公司
 */
public class Constant {
    public static final int YES = 1;
    public static final int NO = 0;
    public static final int PAGE_DEF = 0;
    public static final int PAGE_SIZE_5 = 5;
    public static final int PAGE_SIZE_10 = 10;
    public static final int PAGE_SIZE_50 = 50;
    public static final LocalTime MIN_TIME = LocalTime.of(0,0,0);
    public static final LocalTime MAX_TIME = LocalTime.of(23,59,59);
    public static final String PATTERN_YMD = "yyyy-MM-dd";
    public static final DateTimeFormatter FORMATTER_YMD = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 0-不展示
     */
    public static final int ARTICLE_NO_SHOW = 0;
    /**
     * 1-首页轮播
     */
    public static final int ARTICLE_CAROUSEL = 1;
    /**
     *  2-首页推荐
     */
    public static final int ARTICLE_RECOMMEND = 2;
    /** 白条开通方式-普通 */
    public static final int JD_TYPE_COMMON = 0;
    /** 白条开通方式-金条 */
    public static final int JD_TYPE_GOLD = 1;
    /** 白条开通方式-新手 */
    public static final int JD_TYPE_NEWBIE = 2;
    /** 通过 */
    public static final int TASK_PASS = 2;
    /** 拒绝 */
    public static final int TASK_FAIL = 3;


}

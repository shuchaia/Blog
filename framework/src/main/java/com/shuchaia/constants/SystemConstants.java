package com.shuchaia.constants;

/**
 * @ClassName SystemConstants
 * @Description TODO
 * @Author shuchaia
 * @Date 2023/6/27 16:05
 * @Version 1.0
 */
public class SystemConstants {
    /**
     * 文章为草稿状态
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;

    /**
     * 文章为正常发布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 该文章被置顶
     */
    public static final String TOP = "1";

    /**
     * 该文章没有被置顶
     */
    public static final String NO_TOP = "0";


    /**
     * 分类为正常状态
     */
    public static final String CATEGORY_STATUS_NORMAL = "0";

    /**
     * 分类为禁止状态
     */
    public static final String CATEGORY_STATUS_FORBID = "1";

    /**
     * 友链状态为审核通过
     */
    public static final String LINK_STATUS_NORMAL = "0";

    /**
     * 友链审核不通过
     */
    public static final String LINK_STATUS_FORBID = "1";

    /**
     * 友链尚未审核
     */
    public static final String LINK_STATUS_UNCHECK = "2";

    /**
     * 评论类型为：文章评论
     */
    public static final String ARTICLE_COMMENT = "0";
    /**
     * 评论类型为：友联评论
     */
    public static final String LINK_COMMENT = "1";
    public static final String MENU = "C";
    public static final String BUTTON = "F";
    /**
     * 正常状态
     */
    public static final String NORMAL = "0";
    public static final String ADMAIN = "1";

    /**
     * 在redis中存储了关于viewCount信息的key
     */
    public static final String VIEW_COUNT_KEY = "article:viewCount";
    /**
     * 在redis中存储了关于blogLogin信息的key
     */
    public static final String BLOG_LOGIN_KEY = "bloglogin:";
    public static final String LOGIN_KEY = "login:";
}

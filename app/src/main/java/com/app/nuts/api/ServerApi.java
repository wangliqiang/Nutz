package com.app.nuts.api;

/**
 * Created by 王立强 on 2017/2/4.
 */

public class ServerApi {
    public static String BASEURL = "https://api.douban.com/v2/movie/";
    //豆瓣电影TOP250
    public static String TOP250 = BASEURL + "top250";
    //电影详情
    public static String MOVIEDETAIL = BASEURL + "subject/";
}

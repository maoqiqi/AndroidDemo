package com.software.march.commonframe.network.api;

import com.software.march.commonframe.network.bean.BookBean;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * @author Doc.March
 * @version V 1.0
 * @Description Retrofit turns your HTTP API into a Java interface.
 * @date 2017/3/22
 */
public interface ApiClass {

    public static final String BASE_URL = "http://60.205.148.81/?v1/member/";
    public static final String DOU_BAN_BASE_URL = "https://api.douban.com/v2/book/";

    // 获取省市区  返回String
    @GET("getAdvertisementInfo")
    Call<String> getAdvertisementInfo1();

    // 获取省市区  返回JavaBean
    @GET("getAdvertisementInfo")
    Call<String> getAdvertisementInfo2();

    // 搜索商品
    @POST("searchProduct")
    Call<String> searchProduct(String keyword, String pageIndex, String pageSize);

    // 上传图片
    @POST("uploadImage")
    Call<String> uploadImage(String id, byte[] data);


    // 获取图书信息
    @GET("{id}")
    Call<String> book1(@Path("id") String id);

    @GET("{id}")
    Call<BookBean> book2(@Path("id") String id);

    // 搜索图书
    @GET("search")
    Call<String> search();
    // q	查询关键字	q和tag必传其一
    // tag	查询的tag	q和tag必传其一
    // start	取结果的offset	默认为0
    // count	取结果的条数	默认为20，最大为100

    // 发表新评论
    @POST("reviews")
    Call<String> reviewsPost();
    // 参数	意义	备注
    // book	评论所针对的book id	必传
    // title	评论头	必传
    // content	评论内容	必传，且多于150字
    // rating	打分	非必传，数字1～5为合法值，其他信息默认为不打分

    // 修改评论
    @PUT("review/:id")
    Call<String> reviewsPut(@Path("id") String id);

    // 删除评论
    @DELETE("review/:id")
    Call<String> reviewsDelete(@Path("id") String id);
}
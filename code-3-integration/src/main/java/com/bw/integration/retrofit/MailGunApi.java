package com.bw.integration.retrofit;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

/**
 * @author Gibah Joseph
 * Email: gibahjoe@gmail.com
 * Nov, 2019
 **/
public interface MailGunApi {

    @POST("messages")
    @FormUrlEncoded
    Call<ResponseBody> sendMail(
            @Field("to") List<String> recipientEmails,
            @Field("html") String htmlBody, @Field("subject") String subject);

    @POST("messages")
    @Multipart
    Call<ResponseBody> sendMailWithAttachment(
            @Query("to") List<String> recipientEmails,
            @Part("html") RequestBody htmlBody,
            @Query("subject") String subject,
            @Part() List<MultipartBody.Part> attachments);
}

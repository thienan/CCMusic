package com.cc.lastfmapi;

import com.cc.lastfmapi.models.ScrobbleInfo;
import com.cc.lastfmapi.models.UserLoginInfo;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.POST;

/**
 * Created by christoph on 17.07.16.
 */
public interface LastFmUserRestService {

    String BASE = "/";

    @POST(BASE)
    @FormUrlEncoded
    void getUserLoginInfo(@Field("method") String method, @Field("format") String format, @Field("api_key") String apikey, @Field("api_sig") String apisig, @Field("username") String username, @Field("password") String password, Callback<UserLoginInfo> callback);

    @POST(BASE)
    @FormUrlEncoded
    void getScrobbleInfo(@Field("method") String method, @Field("api_key") String apikey, @Field("api_sig") String apisig, @Field("sk") String token, @Field("artist") String artist, @Field("track") String track, @Field("timestamp") long timestamp, Callback<ScrobbleInfo> callback);

}

package com.mosesasiago.hakiki111.interfaces;

import com.mosesasiago.hakiki111.models.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsJsonAPi {
    @GET("news")
    Call<List<News>> getNews();
}

package com.tolet.sajib.retrofit_jan_18_12_18.Api;

import com.tolet.sajib.retrofit_jan_18_12_18.model.ModelTwo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface KitchenApi {
    @GET("api/web/get-req-data/kitchen-filter")
    Call<List<ModelTwo>> getkitchens();
}

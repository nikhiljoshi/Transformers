package com.tels.assignment.connection;

import com.tels.assignment.model.Transformer;
import com.tels.assignment.model.TransformerRequest;
import com.tels.assignment.model.Transformers;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TransformerApi {
  /*  @Headers({
            "Authorization: tokena",
            "Content-Type”: application/json",
    })*/

    @Headers({ "Content-Type: application/json"})
    @GET("transformers")
    Call<Transformers> getData(/*@Header("Authorization:") String token*/);

    @GET("allspark")
    Call<ResponseBody> getToken();

    @POST("transformers")
    Call<TransformerRequest> createTransformers(@Body TransformerRequest data);


}
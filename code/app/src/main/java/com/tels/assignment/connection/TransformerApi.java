package com.tels.assignment.connection;


import com.tels.assignment.model.TransformerRequest;
import com.tels.assignment.model.Transformers;
import com.tels.assignment.model.UpdateTransformer;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

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

    @PUT("transformers")
    Call<UpdateTransformer> updateTransformers(@Body UpdateTransformer data);


    @DELETE
    @GET("transformers")
    Call<Transformers> deleteTransformer(@Query("transformerId}") String transformerId  );
}
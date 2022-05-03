package it.vegans.testapp.rest;


import java.util.List;

import it.vegans.testapp.models.VegansModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    public static String BASE_URL = "https://apivegans.veganslab.xyz/";
//https://apivegans.veganslab.xyz/test.json
    @GET("test.json")
    Call<VegansModel> getData();



}

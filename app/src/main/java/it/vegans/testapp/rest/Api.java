package it.vegans.testapp.rest;


import java.util.List;

import it.vegans.testapp.models.VegansModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    public static String BASE_URL = "https://apivegans.veganslab.xyz/";

    @GET("test.json")
    Call<List<VegansModel>> getData();



}

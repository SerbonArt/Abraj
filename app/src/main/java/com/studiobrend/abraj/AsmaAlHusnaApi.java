package com.studiobrend.abraj;

import com.studiobrend.abraj.model.Name;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface AsmaAlHusnaApi {
    @GET("asmaAlHusna")
    Call<ApiResponse> getNames();
}



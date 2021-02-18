package com.example.rakutenlibrary.network;

import com.example.rakutenlibrary.models.RepositoryModel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("repositories")
    Call<ResponseBody> searchRepositories(@Query("q") String query);

    @GET("repositories")
    Observable<RepositoryModel> searchRepositoriesUsingRx(@Query("q") String query);


}

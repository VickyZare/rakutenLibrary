package com.example.rakutenlibrary;

import android.util.Log;

import com.example.rakutenlibrary.models.RepositoryInfo;
import com.example.rakutenlibrary.models.RepositoryModel;
import com.example.rakutenlibrary.network.ApiService;
import com.example.rakutenlibrary.network.RetrofitAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GithubRepositoriesProvider {

    private static final String TAG = GithubRepositoriesProvider.class.getSimpleName();


    private static GithubRepositoriesProvider githubRepositoriesProvider;

    private GithubRepositoriesProvider() {

    }

    public static GithubRepositoriesProvider getInstance() {
        if (githubRepositoriesProvider == null)
            githubRepositoriesProvider = new GithubRepositoriesProvider();

        return githubRepositoriesProvider;
    }


    public void getAllRepositories(String platform, String organization, final OnRepositoryResultListener onRepositoryResultListener) {
        String query = platform + " org:" + organization;
        ApiService apiService = RetrofitAdapter.getInstance().create(ApiService.class);
        apiService
                .searchRepositories(query)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            RepositoryModel repositoryModel = parseJsonToModel(response.body().string());
                            onRepositoryResultListener.onSuccess(repositoryModel);
                        } catch (Exception e) {
                            onRepositoryResultListener.onFailed(e);
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        onRepositoryResultListener.onFailed(t);
                    }
                });
    }


    public void getAllRepositoriesUsingRx(String platform, String organization, Observer<RepositoryModel> repositoryModelObserver) {
        String query = platform + " org:" + organization;
        ApiService apiService = RetrofitAdapter.getInstance().create(ApiService.class);
        apiService
                .searchRepositoriesUsingRx(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryModelObserver);
    }


    public RepositoryModel parseJsonToModel(String string) {
        RepositoryModel repositoryModel = null;
        if (string != null && !string.isEmpty()) {
            Log.i(TAG, "Response is not null and empty...");
            repositoryModel = new RepositoryModel();
            try {
                JSONObject jsonObject = new JSONObject(string);

                if (jsonObject.has("total_count")) {
                    repositoryModel.setTotal_count(jsonObject.getInt("total_count"));
                    Log.i(TAG, "Response has total_count = " + repositoryModel.getTotal_count());
                }
                if (jsonObject.has("incomplete_results")) {
                    repositoryModel.setIncomplete_results(jsonObject.getBoolean("incomplete_results"));
                    Log.i(TAG, "Response has incomplete_results = " + repositoryModel.isIncomplete_results());
                }

                if (jsonObject.has("items")) {
                    JSONArray items = jsonObject.getJSONArray("items");
                    if (items.length() > 0) {
                        ArrayList<RepositoryInfo> repositoryInfoArrayList = new ArrayList<>();
                        Log.i(TAG, "Response total items = " + items.length());

                        for (int i = 0; i < items.length(); i++) {
                            JSONObject item = items.getJSONObject(i);
                            if (item != null) {
                                RepositoryInfo repositoryInfo = new RepositoryInfo();
                                if (item.has("id"))
                                    repositoryInfo.setId(item.getInt("id"));
                                if (item.has("name"))
                                    repositoryInfo.setName(item.getString("name"));
                                if (item.has("private"))
                                    repositoryInfo.setPrivate(item.getBoolean("private"));
                                if (item.has("description"))
                                    repositoryInfo.setDescription(item.getString("description"));
                                if (item.has("language"))
                                    repositoryInfo.setLanguage(item.getString("language"));
                                repositoryInfoArrayList.add(repositoryInfo);
                            }
                        }
                        repositoryModel.setItems(repositoryInfoArrayList);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return repositoryModel;
    }
}

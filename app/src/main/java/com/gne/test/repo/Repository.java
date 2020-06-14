package com.gne.test.repo;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.gne.test.Common;
import com.gne.test.retrofit.ApiClient;
import com.gne.test.retrofit.ApiInterface;
import com.gne.test.room.RoomRepo;
import com.gne.test.vo.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private Context context;
    private ApiInterface apiInterface;
    private RoomRepo roomRepo;
    private static Repository repository;
    private MutableLiveData<Result> resultsMutableLiveData=new MutableLiveData<>();

    Repository(Context context) {
        this.context=context;
        apiInterface= ApiClient.getInstance();
        roomRepo=new RoomRepo(context);
    }

    public static Repository getInstance(Context context){
        if(repository==null){
            repository=new Repository(context);
        }
        return repository;
    }

    public LiveData<Result> getUsers(int count){
        if(Common.isNetworkAvailable(context)) {
            Call<Result> resultsCall = apiInterface.getUsers(count);
            resultsCall.enqueue(new Callback<Result>() {
                @Override
                public void onResponse(Call<Result> call, Response<Result> response) {
                    if (response.isSuccessful() && response.body()!=null) {
                        roomRepo.insertUsers(response.body().getResults());
                        resultsMutableLiveData.setValue(response.body());
                    } else {
                        resultsMutableLiveData.setValue(new Result(false));
                    }
                }

                @Override
                public void onFailure(Call<Result> call, Throwable t) {
                    resultsMutableLiveData.setValue(new Result(false));
                }
            });
        }
        else {
            try {
                resultsMutableLiveData.setValue(roomRepo.getUsers(count));
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return resultsMutableLiveData;
    }

    public void updateLike(boolean isLike, String phone){
        roomRepo.updateLike(isLike, phone);
    }

    public void updateDislike(boolean isDislike, String phone){
        roomRepo.updateDislike(isDislike, phone);
    }
}

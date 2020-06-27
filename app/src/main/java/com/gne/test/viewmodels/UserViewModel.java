package com.gne.test.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gne.test.Common;
import com.gne.test.repo.Repository;
import com.gne.test.vo.Result;

public class UserViewModel extends AndroidViewModel {
    Repository repository;
    LiveData<Result> resultLiveData;
    int count=10;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository=Repository.getInstance(application);

        if(!Common.isNetworkAvailable(application)){
            count=0;
        }
        resultLiveData= repository.getUsers(count);
    }

    public void fetchUsers(){
        repository.getUsers(count);
    }

    public LiveData<Result> getUsers(){
        return resultLiveData;
    }

    public void updateLike(boolean isLike, String phone){
        repository.updateLike(isLike, phone);
    }

    public void updateDislike(boolean isDislike, String phone){
        repository.updateDislike(isDislike, phone);
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

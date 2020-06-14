package com.gne.test.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gne.test.repo.Repository;
import com.gne.test.vo.Result;

public class UserViewModel extends AndroidViewModel {
    Repository repository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        repository=Repository.getInstance(application);
    }

    public LiveData<Result> getUsers(int count){
        return repository.getUsers(count);
    }

    public void updateLike(boolean isLike, String phone){
        repository.updateLike(isLike, phone);
    }

    public void updateDislike(boolean isDislike, String phone){
        repository.updateDislike(isDislike, phone);
    }
}

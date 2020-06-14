package com.gne.test.room;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Pair;

import androidx.room.Room;

import com.gne.test.vo.Result;
import com.gne.test.vo.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class RoomRepo {

    private String DB_NAME = "db_users";
    private UserDatabase userDatabase;

    public RoomRepo(Context context){
        userDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
    }

    public void deleteUsers(){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDatabase.dao().deleteAll();
                return null;
            }
        }.execute();
    }

    public void insertUsers(final List<User> users) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDatabase.dao().insertAllUsers(users);
                return null;
            }
        }.execute();
    }

    public void updateLike(boolean isLike, String email) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDatabase.dao().updateLike(isLike, email);
                return null;
            }
        }.execute();
    }
    public void updateDislike(boolean isDislike, String email) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                userDatabase.dao().updateDisLike(isDislike, email);
                return null;
            }
        }.execute();
    }

    public Result getUsers(int count) throws InterruptedException, ExecutionException {
        MyAsync myAsync=new MyAsync();
        return myAsync.execute(count).get();
    }

    class MyAsync extends AsyncTask<Integer, Void,Result>{
        Result result;
        @Override
        protected void onPostExecute(Result list) {
        }

        @Override
        protected Result doInBackground(Integer... count) {
            ArrayList<User> list=new ArrayList<>(userDatabase.dao().fetchUsers(count[0]));
            result=new Result();
            result.setResults(list);
            return result;
        }
    }
}

package com.gne.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.gne.test.adapters.CardStackAdapter;
import com.gne.test.databinding.ActivityMainBinding;
import com.gne.test.viewmodels.UserViewModel;
import com.gne.test.vo.Result;
import com.gne.test.vo.User;
import com.google.android.material.snackbar.Snackbar;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.Duration;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CardStackListener, CardStackAdapter.OnCardActionClickListener {

    private ActivityMainBinding binding;

    private int index=0;
    private boolean isEnd=false;
    private Result result=new Result();
    private ArrayList<User> arrayList=new ArrayList<>();
    private CardStackLayoutManager manager;
    private CardStackAdapter adapter;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        userViewModel= new ViewModelProvider(MainActivity.this).get(UserViewModel.class);

        userViewModel.getUsers().observe(this,result -> {
            MainActivity.this.result=result;
            if(result.isStatus()) {
                if(result.getResults().size()!=0) {
                    binding.txtInfo.setVisibility(View.GONE);
                    binding.cardStackView.smoothScrollToPosition(index);
                    arrayList.addAll(result.getResults());
                    adapter.notifyItemRangeInserted(userViewModel.getCount() - 9,userViewModel.getCount());
//                    adapter.notifyDataSetChanged();
                }else {
                    isEnd=true;
                    binding.txtInfo.setText("No one nearby...");
                    binding.txtInfo.setVisibility(View.VISIBLE);
                }
            }
            else {
                binding.txtInfo.setText("Something went wrong :(");
                binding.txtInfo.setVisibility(View.VISIBLE);
            }
        });

        initialize();
    }

    private void initialize() {
        manager=new CardStackLayoutManager(this, this) ;
        adapter=new CardStackAdapter(arrayList,this);

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(8.0f);
        manager.setScaleInterval(0.95f);
        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.HORIZONTAL);
        manager.setCanScrollHorizontal(true);
        manager.setCanScrollVertical(false);
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual);
        binding.cardStackView.setItemAnimator(null);
        binding.cardStackView.setLayoutManager(manager);
        binding.cardStackView.setAdapter(adapter);
    }

    private void setLike(){
        userViewModel.updateLike(true, arrayList.get(index).email);
        userViewModel.updateDislike(false, arrayList.get(index).email);
    }

    private void setDislike(){
        userViewModel.updateLike(false, arrayList.get(index).email);
        userViewModel.updateDislike(true, arrayList.get(index).email);
    }

    @Override
    public void onCardDragging(Direction direction, float ratio) {

    }

    @Override
    public void onCardSwiped(Direction direction) {
        if(direction==Direction.Right){
            setLike();
        }
        else {
            setDislike();
        }
        if (manager.getTopPosition() == adapter.getItemCount()-3 && !isEnd) {
            userViewModel.setCount(userViewModel.getCount()+10);
            userViewModel.fetchUsers();
        }
    }

    @Override
    public void onCardRewound() {

    }

    @Override
    public void onCardCanceled() {

    }

    @Override
    public void onCardAppeared(View view, int position) {
        index=position;
    }

    @Override
    public void onCardDisappeared(View view, int position) {
//        arrayList.remove(position);
//        result.getResults().remove(position);
    }


    @Override
    public void onLikeClicked() {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .build();
        manager.setSwipeAnimationSetting(setting);
        binding.cardStackView.swipe();

        setLike();
    }

    @Override
    public void onDislikeClicked() {
        SwipeAnimationSetting setting = new SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .build();
        manager.setSwipeAnimationSetting(setting);
        binding.cardStackView.swipe();

        setDislike();
    }

    @Override
    public void onMessageClicked() {
        Toast.makeText(this,"Not so fast buddy! :)",Toast.LENGTH_SHORT).show();
    }
}
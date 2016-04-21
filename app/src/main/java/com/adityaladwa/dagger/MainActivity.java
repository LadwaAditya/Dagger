package com.adityaladwa.dagger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.adityaladwa.dagger.di.Post;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.http.GET;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    Retrofit retrofit;
    Subscription subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView textView = (TextView) findViewById(R.id.posts);

        ((App) getApplication()).getNetComponent().inject(this);
        subscribe = retrofit.create(PostService.class).getPostList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Observer<List<Post>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(getApplicationContext(), "Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getApplicationContext(), "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        assert textView != null;
                        textView.setText(e.getMessage());

                    }

                    @Override
                    public void onNext(List<Post> posts) {
                        assert textView != null;
                        textView.setText(posts.get(0).getBody());
                    }
                });


    }

    @Override
    protected void onStop() {
        super.onStop();
        subscribe.unsubscribe();
    }

    private interface PostService {
        @GET("/posts")
        Observable<List<Post>> getPostList();
    }
}

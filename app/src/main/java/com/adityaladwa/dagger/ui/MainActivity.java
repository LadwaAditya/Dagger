package com.adityaladwa.dagger.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.adityaladwa.dagger.MainPresenter;
import com.adityaladwa.dagger.MainScreenContract;
import com.adityaladwa.dagger.Post;
import com.adityaladwa.dagger.R;
import com.adityaladwa.dagger.di.components.DaggerNetComponent;
import com.adityaladwa.dagger.di.module.AppModule;
import com.adityaladwa.dagger.di.module.NetModule;
import com.adityaladwa.dagger.di.module.PresenterModule;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {


    @Inject
    MainPresenter mainPresenter;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.posts);

        DaggerNetComponent.builder()
                .appModule(new AppModule(getApplication()))
                .netModule(new NetModule("http://www.jsonplaceholder.typicode.com"))
                .presenterModule(new PresenterModule(this))
                .build().inject(this);

        mainPresenter.loadPostsFromApi();


    }


    @Override
    public void showPosts(List<Post> posts) {
        assert textView != null;
        textView.setText(posts.get(0).getBody());
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getApplicationContext(), "Error" + message, Toast.LENGTH_SHORT).show();
        assert textView != null;
        textView.setText(message);
    }


    @Override
    public void showComplete() {
        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
    }

}

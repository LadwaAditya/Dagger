package com.adityaladwa.dagger;

import java.util.List;

/**
 * Created by Aditya on 21-Apr-16.
 */
public interface MainScreenContract {
    interface View {
        void showPosts(List<Post> posts);

        void showError(String message);

        void showComplete();

    }
}

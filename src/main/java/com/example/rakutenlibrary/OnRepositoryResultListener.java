package com.example.rakutenlibrary;

import com.example.rakutenlibrary.models.RepositoryModel;

public interface OnRepositoryResultListener {
    void onSuccess(RepositoryModel repositoryModel);
    void onFailed(Throwable t);
}

package com.borjali.mostafa.pray;

// Declare any non-default types here with import statements

interface IMyketDeveloperApi {
    int isDeveloperApiSupported(int apiVersion);

    Bundle getAppUpdateState(int apiVersion, String packageName);

    int isUserLogin(int apiVersion);

    Bundle getLoginIntent(int apiVersion);
}
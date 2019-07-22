package com.example.chat_app.Fragments;

import com.example.chat_app.Notification.MyRespone;
import com.example.chat_app.Notification.Sender;

import retrofit2.Call;
import retrofit2.http.Body;

import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {

                    "Content-Type:application/json",
            "Authorization:key=AAAA4TIytjY:APA91bEhhMp2KB68yi5ekYD4fCmxDKtYBYk_-hvxSnzdBBt64G4j4DP5ntbky0I0b1vsD1b7KEH3qnu2yPsQLyCAPkmrwLOCVQJ-ft0D0i4BgirlVce2CSuBQlwYXGSVipAKeH0MOLzP"

}


    )

    @POST("fcm/send")
    Call<MyRespone>sendNotification(@Body Sender body);

}

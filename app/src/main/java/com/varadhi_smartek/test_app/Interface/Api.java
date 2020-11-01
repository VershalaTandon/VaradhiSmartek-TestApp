package com.varadhi_smartek.test_app.Interface;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    public String BASE_URL = "http://13.127.95.246:7000/marketing/";

    @FormUrlEncoded
    @POST("get_monthly_appointments")
    Call<Object> get_booking_appointment_list(
            @Field("user_employeid") String employeId,
            @Field("status") String page_no,
            @Field("appointment_type") String appointment_type,
            @Field("month") String appointment_date);
}

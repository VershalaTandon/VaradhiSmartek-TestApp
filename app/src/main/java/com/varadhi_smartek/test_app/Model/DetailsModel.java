package com.varadhi_smartek.test_app.Model;

import com.google.gson.annotations.SerializedName;

public class DetailsModel {

    @SerializedName("Institution_Name")
    String Institution_Name;

    @SerializedName("POC_Name")
    String POC_Name;

    @SerializedName("marketing_user_employee_id")
    String marketing_user_employee_id;

    @SerializedName("image_address")
    String image_address;

    @SerializedName("Address_Line_1")
    String Address_Line_1;

    @SerializedName("Address_Line_2")
    String Address_Line_2;

    @SerializedName("Land_Mark")
    String Land_Mark;

    @SerializedName("City")
    String City;

    @SerializedName("State")
    String State;

    @SerializedName("PIN")
    String PIN;

    public DetailsModel(String Institution_Name, String POC_Name, String marketing_user_employee_id, String image_address,
                       String Address_Line_1, String Address_Line_2, String Land_Mark, String City, String State, String PIN){
        this.Institution_Name = Institution_Name;
        this.POC_Name = POC_Name;
        this.marketing_user_employee_id = marketing_user_employee_id;
        this.image_address = image_address;
        this.Address_Line_1 = Address_Line_1;
        this.Address_Line_2 = Address_Line_2;
        this.Land_Mark = Land_Mark;
        this.City = City;
        this.State = State;
        this.PIN = PIN;
    }

    public String getInstitution_Name() {
        return Institution_Name;
    }

    public String getPOC_Name() {
        return POC_Name;
    }

    public String getMarketing_user_employee_id() {
        return marketing_user_employee_id;
    }

    public String getImage_address() {
        return image_address;
    }

    public String getAddress_Line_1() {
        return Address_Line_1;
    }

    public String getAddress_Line_2() {
        return Address_Line_2;
    }

    public String getLand_Mark() {
        return Land_Mark;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getPIN() {
        return PIN;
    }
}

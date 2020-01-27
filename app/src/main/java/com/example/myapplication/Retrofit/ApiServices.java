package com.example.myapplication.Retrofit;

import com.example.myapplication.AddParcel.ParcelResponseModel;
import com.example.myapplication.Model.Retrofit.ChangeAddressModel;
import com.example.myapplication.Model.Retrofit.ChangePasswordModel;
import com.example.myapplication.Model.Retrofit.CheckoutModel;
import com.example.myapplication.Model.Retrofit.Checkoutpage;
import com.example.myapplication.Model.Retrofit.ContactUsModel;
import com.example.myapplication.Model.Retrofit.CyberData;
import com.example.myapplication.Model.Retrofit.CyberResponse;
import com.example.myapplication.Model.Retrofit.DeliveryModel;
import com.example.myapplication.Model.Retrofit.FaqDataModel;
import com.example.myapplication.Model.Retrofit.FoodData;
import com.example.myapplication.Model.Retrofit.FreezeModel;
import com.example.myapplication.Model.Retrofit.FreezePackageModel;
import com.example.myapplication.Model.Retrofit.Freezes;
import com.example.myapplication.Model.Retrofit.GatewayModel;
import com.example.myapplication.Model.Retrofit.GetAddressModel;
import com.example.myapplication.Model.Retrofit.GetCardModel;
import com.example.myapplication.Model.Retrofit.HistoryModel;
import com.example.myapplication.Model.Retrofit.Item;
import com.example.myapplication.Model.Retrofit.LoginModel;
import com.example.myapplication.Model.Retrofit.Logins;
import com.example.myapplication.Model.Retrofit.MenuModel;
import com.example.myapplication.Model.Retrofit.Myprofile;
import com.example.myapplication.Model.Retrofit.MyprofileModel;
import com.example.myapplication.Model.Retrofit.PackageModel;
import com.example.myapplication.Model.Retrofit.PackageResponse;
import com.example.myapplication.Model.Retrofit.ParcelBucketModel;
import com.example.myapplication.Model.Retrofit.ParcelCafeModel;
import com.example.myapplication.Model.Retrofit.ParcelDataModel;
import com.example.myapplication.Model.Retrofit.ParcelHistoryNewModel;
import com.example.myapplication.Model.Retrofit.PassValue;
import com.example.myapplication.Model.Retrofit.PasswordChangeModel;
import com.example.myapplication.Model.Retrofit.PaypalCancel;
import com.example.myapplication.Model.Retrofit.PromoModel;
import com.example.myapplication.Model.Retrofit.PromoResponse;
import com.example.myapplication.Model.Retrofit.PromoValidate;
import com.example.myapplication.Model.Retrofit.ReplaceModel;
import com.example.myapplication.Model.Retrofit.SaveAddressModel;
import com.example.myapplication.Model.Retrofit.SubcribeModel;
import com.example.myapplication.Model.Retrofit.TermsConditionModel;
import com.example.myapplication.Model.Retrofit.UpdatePackagePojo;
import com.example.myapplication.Model.Retrofit.UserID;
import com.example.myapplication.Model.Retrofit.checkout;
import com.example.myapplication.Model.Retrofit.history;
import com.example.myapplication.Model.Retrofit.homemenumodel;
import com.example.myapplication.Model.Retrofit.paypalmodel;
import com.example.myapplication.Model.Retrofit.softdrinkModel;
import com.example.myapplication.ParcelHisotry.ParcelHistoryModel;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiServices {

    @POST("api/packages")
    Call<PackageModel> GetPackage(@Body UserID task);

    @POST("api/parcelcafe")
    Call<ParcelCafeModel> GetParcel(@Body UserID task);

    @POST("api/partybox")
    Call<ParcelDataModel> GetParty(@Body UserID task);

    @POST("api/allmainmenu")
    Call<MenuModel> GetMenu(@Body UserID task);

    @POST("api/mainmenu")
    Call<homemenumodel> GetHomeMenu(@Body PassValue task);

    @POST("api/checkout")
    Call<CheckoutModel> GetCheckOut(@Body checkout task);

    @POST("api/partycheckout")
    Call<CheckoutModel> GetPartyCheckOut(@Body checkout task);

    @POST("api/login")
    Call<LoginModel> GetLogin(@Body Logins task);

    @POST("api/pages")
    Call<TermsConditionModel> Getterms(@Body TermsConditionModel task);

    @POST("api/profile")
    Call<MyprofileModel> GetProfile(@Body Myprofile task);

    @POST("api/uptprofile")
    Call<MyprofileModel> UpdateProfile(@Body Myprofile task);

    @POST("api/contact")
    Call<ContactUsModel> ContactUsApi(@Body Checkoutpage task);

    @POST("api/updatepassword")
    Call<PasswordChangeModel> ChangePassword(@Body ChangePasswordModel task);


    @POST("api/history")
    Call<HistoryModel> GetHistory(@Body history task);


    @POST("api/partyhistorydetail")
    Call<ParcelHistoryNewModel> GetPartyHistory(@Body history task);

    @POST("api/promovalidate")
    Call<PromoResponse> GetPromoValidate(@Body PromoValidate task);

    @POST("api/forgetpassword")
    Call<PromoResponse> SendForgotEmail(@Body Myprofile task);


    @POST("api/promocodes")
    Call<PromoModel> GetPromo(@Body history task);

    @POST("api/mysubscription")
    Call<SubcribeModel> GetSubcribe(@Body history task);

    @POST("api/replacementfood")
    Call<ReplaceModel> GetRelace(@Body UserID task);

    @POST("api/packageupgrade")
    Call<ParcelDataModel> FreezePackage(@Body FreezePackageModel task);

    @POST("api/gateway_success")
    Call<paypalmodel> GetPaypalSuccess(@Body GatewayModel task);

    @POST("api/gateway_cancel")
    Call<paypalmodel> GetPaypalCancel(@Body GatewayModel task);

    @POST("api/gateway_success")
    Call<paypalmodel> GetPaypalSuccessCheck(@Body GatewayModel task);

    @POST("api/softdrinks")
    Call<softdrinkModel> GetSoftdrink(@Body UserID task);


    @POST("api/foodoftheday")
    Call<FoodData> GetFoodOfDay(@Body UserID task);


    @POST("api/updatepackage")
    Call<PackageResponse> UpdatePackage(@Body UpdatePackagePojo task);

    @POST("api/deliveryaddress")
    Call<GetAddressModel> GetAddress(@Body DeliveryModel task);

    @POST("api/saveaddress")
    Call<ResponseBody> SaveAddress(@Body SaveAddressModel task);

    @POST("api/changeaddress")
    Call<ResponseBody> ChangeAddress(@Body ChangeAddressModel task);

    @POST("api/parcelbucketpay")
    Call<ParcelResponseModel> sendParcelBucket(@Body ParcelBucketModel task);

    @POST("api/faqs")
    Call<FaqDataModel> getFaqData(@Body Item task);


    @POST("api/cybersourceAPI")
    Call<CyberResponse> getCyberPay(@Body CyberData task);


    @POST("api/accountexist")
    Call<PasswordChangeModel> checkAccountExist(@Body checkout task);

    @POST("api/add_card")
    Call<PasswordChangeModel> addCardApi(@Body checkout task);

    @POST("api/delete_card")
    Call<PasswordChangeModel> deleteCardApi(@Body checkout task);

    @POST("api/cards")
    Call<List<GetCardModel>> showCards(@Body checkout task);


    @POST("api/manualcronjob")
    Call<ResponseBody> manualCronJob(@Body PaypalCancel task);

    @POST("api/freezedate")
    Call<FreezeModel> freezedate(@Body Freezes task);

}

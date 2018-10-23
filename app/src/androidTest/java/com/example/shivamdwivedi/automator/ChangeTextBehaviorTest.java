package com.example.shivamdwivedi.automator;

import android.app.Instrumentation;
import android.content.Intent;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SdkSuppress;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;
import android.util.Log;


@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class ChangeTextBehaviorTest {

    private static final String BASIC_SAMPLE_PACKAGE
            = "com.whatsapp";
    private static final int LAUNCH_TIMEOUT = 5000;
    private static final String STRING_TO_BE_TYPED = "UiAutomator";
    public static final String COM_WHATSAPP = "com.whatsapp";
    private UiDevice mDevice;

    @Test
    public void sendMsgs() throws Exception{


        Log.d("sdfd","sdfdsf");



            openWhatsappContact(InstrumentationRegistry.getArguments().getString("mobile"), InstrumentationRegistry.getArguments().getString("msg"));



    }

//
//    @Test
//    public void tp() {
//
//        Intent sendIntent = new Intent();
//        sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
//        sendIntent.setPackage(COM_WHATSAPP);
//        sendIntent.setType("text/plain");
//        InstrumentationRegistry.getContext().startActivity(sendIntent);
//
//
//    }


    public void validateMobileNumber(String num){


        if(num == null) throw new NullPointerException("number not supplied");
        if(num.length() != 12)
        {
            throw new IllegalArgumentException("Invalid Number");

        }
        Long.parseLong(num);
    }

    public void validateMSG(String msg){

        if(msg.trim().length()<1 ) throw new IllegalArgumentException("msg too short");

        if(msg == null) throw  new NullPointerException("msg not supplied");



    }


    public void openWhatsappContact(String number, String msg) throws UiObjectNotFoundException {



        validateMobileNumber(number);

        validateMSG(msg);

        msg = msg.replace("@@@"," ");

        // Initialize UiDevice instance
        mDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

        // Start from the home screen
        mDevice.pressHome();

        Uri uri = Uri.parse("smsto:" + number);
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
//        i.setType("text/plain");

        i.putExtra(Intent.EXTRA_TEXT, "Hai Good Morning");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setPackage(COM_WHATSAPP);
        InstrumentationRegistry.getContext().startActivity(i);


        UiObject uiObject = mDevice.findObject(new UiSelector().resourceId("com.whatsapp:id/entry"));

        uiObject.setText(msg);

        UiObject uiObject1 = mDevice.findObject(new UiSelector().resourceId("com.whatsapp:id/send"));

        uiObject1.click();

    }


//    public static boolean openApp(Context context, String packageName) {
//        PackageManager manager = context.getPackageManager();
//        try {
//            Intent i = manager.getLaunchIntentForPackage(packageName);
//            if (i == null) {
//                return false;
//                //throw new ActivityNotFoundException();
//            }
//            i.addCategory(Intent.CATEGORY_LAUNCHER);
//            context.startActivity(i);
//            return true;
//        } catch (ActivityNotFoundException e) {
//            return false;
//        }
//    }
//
//    @Test
//    public void SelectPerson() {
//
//
//        openApp(InstrumentationRegistry.getContext(), COM_WHATSAPP);
//
//
//    }
}
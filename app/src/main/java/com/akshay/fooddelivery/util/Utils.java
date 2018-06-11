package com.akshay.fooddelivery.util;

import android.content.Context;
import android.widget.Toast;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created by Akshay on 20-02-2018.
 */

public class Utils {

    public static boolean isPhoneNumberValid(String phoneNumber){
        if(Pattern.matches("[0-9]{10}", phoneNumber)){
            return true;
        }else
            return false;
    }

    public static String formattedPhoneNumber(String phoneNumber) {
        return "+91" + phoneNumber;
    }

    public static HashMap<String, Object> getCurrentTimeStamp() {
        HashMap<String, Object> timestampJoined = new HashMap<>();
        timestampJoined.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

        return timestampJoined;
    }

    public static String getNameToDisplay(String str) {
        if (str == null){
            return str;
        }
        String[] words = str.split(" ");

        String text = "";
        for(String word: words) {
            if(Pattern.matches("[a-zA-Z]*", word)) {
                text += word.substring(0, 1).toUpperCase() + word.substring(1) + " ";
            }else
                text += word + " ";
        }
        return text.substring(0, text.length()-1);
    }

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}

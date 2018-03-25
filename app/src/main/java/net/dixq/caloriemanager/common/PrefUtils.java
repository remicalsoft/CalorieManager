package net.dixq.caloriemanager.common;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dixq on 2018/03/25.
 */

public class PrefUtils {

    public static final String FILENAME = "pref_datta";
    public static final String DELM = ",";
    public static final String PREF_TAG_HISTORY = "pref_tag_history";
    public static final String PREF_TAG_TAKEN_DATA ="%04d.%02d.%02d_pref_tag_taken_data";


    static public String[] read(Context c, String tag) {
        SharedPreferences pref = c.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        String ret = pref.getString(tag, null);
        if(ret==null){
            return null;
        }
        return ret.split(DELM);
    }

    static public void add(Context c, String tag, String dat){
        SharedPreferences pref = c.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        String ret = pref.getString(tag, null);
        if(ret==null){
            ret = dat;
        } else {
            ret = ret + DELM + dat;
        }
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(tag, ret);
        editor.commit();
    }

    static public void remove(Context c, String tag, int index){
        String[] ret = read(c, tag);
        if(ret==null){
            return;
        }
        String dat = "";
        for(int i=0; i<ret.length; i++){
            if(i!=index) {
                dat += ret[i];
                if(i+1!=ret.length){
                    dat += DELM;
                }
            }
        }
        SharedPreferences pref = c.getSharedPreferences(FILENAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(tag, dat);
        editor.commit();
    }



}

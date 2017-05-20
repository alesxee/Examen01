package com.example.ariso.planetas.util;

import android.content.Context;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.example.ariso.planetas.R;


public class SnackBarHelper {

    public static void showWarningMessage(View view, Context context, int id){
        Snackbar snackbar = Snackbar.make(view, context.getText(id), Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_dark_background));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_dark_background));
        snackbar.show();
    }

    public static void showWarningMessage(View view, Context context, String message){
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_end_color));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_light_background));
        snackbar.show();
    }

    public static void showErrorMessage(View view, Context context, int id){
        Snackbar snackbar = Snackbar.make(view, context.getText(id), Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_start_color));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_dark_background));
        snackbar.show();
    }

    public static void showErrorMessage(View view, Context context, String message){
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_start_color));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_light_background));
        snackbar.show();
    }

    public static void showSuccessMessage(View view, Context context, int id){
        Snackbar snackbar = Snackbar.make(view, context.getText(id), Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_end_color));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));
        snackbar.show();
    }

    public static void showSuccessMessage(View view, Context context, int id, BaseTransientBottomBar.BaseCallback<Snackbar> callback){
        Snackbar snackbar = Snackbar.make(view, context.getText(id), Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_end_color));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_light_background));
        snackbar.addCallback(callback);
        snackbar.show();
    }

    public static void showSuccessMessage(View view, Context context, String msj){
        Snackbar snackbar = Snackbar.make(view, msj, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_start_color));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorAccent));
        snackbar.show();
    }

    public static void showSuccessMessage(View view, Context context, String msj, BaseTransientBottomBar.BaseCallback<Snackbar> callback){
        Snackbar snackbar = Snackbar.make(view, msj, Snackbar.LENGTH_LONG);
        View snackBarView = snackbar.getView();
        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_light_background));
        TextView textView = (TextView) snackBarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setBackgroundColor(ContextCompat.getColor(context, R.color.cardview_shadow_end_color));
        snackbar.addCallback(callback);
        snackbar.show();
    }
}

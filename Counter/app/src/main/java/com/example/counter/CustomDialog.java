package com.example.counter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyboardShortcutGroup;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomDialog extends Dialog implements View.OnClickListener {
    private  Activity activity;
    private Button yes,no;

    public CustomDialog(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

    }

    @Override
    public void onProvideKeyboardShortcuts(List<KeyboardShortcutGroup> data, @Nullable Menu menu, int deviceId) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yes:
                activity.finish();
                break;
            case R.id.no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }
    }


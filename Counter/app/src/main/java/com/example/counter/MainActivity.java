package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int STARTING_HEALTH = 20;

    public static int count1 = STARTING_HEALTH;
    public static int count2 = STARTING_HEALTH;
    private int image1 = 0;
    private int image2 = 0;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView life;
    private TextView life2;
    private LinearLayout layout;
    private LinearLayout layout2;
    private LinearLayout counterLayout1;
    private LinearLayout counterLayout2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.add);
        button2 = findViewById(R.id.sub);
        button3 = findViewById(R.id.add2);
        button4 = findViewById(R.id.sub2);
        life = findViewById(R.id.life);
        life2 = findViewById(R.id.life2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        life.setText(String.valueOf(count1));
        life2.setText(String.valueOf(count2));
        layout = findViewById(R.id.wrapper);
        layout2 = findViewById(R.id.wrapper2);
        layout.setOnClickListener(this);
        layout2.setOnClickListener(this);
        button1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                count1 += 5;
                life.setText(String.valueOf(count1));
                return true;
            }
        });
        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                count1 -= 5;
                life.setText(String.valueOf(count1));
                if (count1 <= 0) {
                    getMessage1(MainActivity.this);
                }
                return true;
            }
        });
        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                count2 += 5;
                life2.setText(String.valueOf(count2));

                return true;
            }
        });
        button4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                count2 -= 5;
                life2.setText(String.valueOf(count2));
                if (count2 <= 0) {
                    getMessage2(MainActivity.this);
                }
                return true;
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        life.setText(String.valueOf(count1));
        life2.setText(String.valueOf(count2));
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case (R.id.add):
                count1++;
                life.setText(String.valueOf(count1));
                break;
            case (R.id.sub):
                count1--;
                life.setText(String.valueOf(count1));
                if (count1 <= 0) {
                    getMessage1(this);
                }
                break;
            case (R.id.add2):
                count2++;
                life2.setText(String.valueOf(count2));

                break;
            case (R.id.sub2):
                count2--;
                life2.setText(String.valueOf(count2));
                if (count2 <= 0) {
                    getMessage2(this);
                }
                break;
            case (R.id.wrapper):
                changeBackground(id);
                break;
            case (R.id.wrapper2):
                changeBackground(id);
                break;
        }

    }


    public void changeBackground(int id) {
        LinearLayout layout = findViewById(id);
        LinearLayout layoutColor;
        if (id == R.id.wrapper) {
            layoutColor = findViewById(R.id.counterLinearLayout);
        } else {
            layoutColor = findViewById(R.id.counterLinearLayout2);
        }
        int image;
        if (id == R.id.wrapper) {
            image1++;
            if (image1 > 5) {
                image1 = 0;
            }
            image = image1;
        } else {
            image2++;
            if (image2 > 5) {
                image2 = 0;
            }
            image = image2;
        }

        switch (image) {
            case (0):
                TypedValue outValue = new TypedValue();
                getApplicationContext().getTheme().resolveAttribute(
                        android.R.attr.selectableItemBackground, outValue, true);
                layout.setBackgroundResource(outValue.resourceId);
                layoutColor.setBackground(getDrawable(R.drawable.shape_brown));
                break;
            case (1):
                layout.setBackground(getDrawable(R.drawable.forest));
                layoutColor.setBackground(getDrawable(R.drawable.shape_green));
                break;
            case (2):
                layout.setBackground(getDrawable(R.drawable.swamp));
                layoutColor.setBackground(getDrawable(R.drawable.shape_black));
                break;
            case (3):
                layout.setBackground(getDrawable(R.drawable.mountain));
                layoutColor.setBackground(getDrawable(R.drawable.shape_red));
                break;
            case (4):
                layout.setBackground(getDrawable(R.drawable.plains));
                layoutColor.setBackground(getDrawable(R.drawable.shape_white));
                break;
            case (5):
                layout.setBackground(getDrawable(R.drawable.island));
                layoutColor.setBackground(getDrawable(R.drawable.shape_blue));
                break;
        }

    }

    public void reset(View view) {
        int id = view.getId();
        switch (id) {
            case (R.id.resetLife):
                count1 = STARTING_HEALTH;
                count2 = STARTING_HEALTH;

                break;
            case (R.id.resetPairsLife):
                count1 = STARTING_HEALTH * 2;
                count2 = STARTING_HEALTH * 2;
                break;
        }
        life.setText(String.valueOf(count1));
        life2.setText(String.valueOf(count2));
    }

    public static void getMessage1(Context context) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        toastView.setRotation(180);
        toast.setGravity(Gravity.TOP, 0, 0);

        if (count2 >= 20) {
            toast.setText("Que lapada hein");
        } else {
            toast.setText("Perdeu lixo");
        }
        toast.show();
    }

    public static void getMessage2(Context context) {
        if (count1 >= 20) {
            Toast.makeText(context, "Que lapada hein", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Perdeu lixo", Toast.LENGTH_SHORT).show();
        }
    }

    public void goToCounters(View view) {
        startActivity(new Intent(this, CounterActivity.class));
    }

}
package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.counter.model.Card;

import java.util.ArrayList;
import java.util.List;

public class CounterActivity extends AppCompatActivity implements View.OnClickListener {

    private static CardAdapter aCardAdapter;
    private RecyclerView mCardRecyclerView;
    private static List<Card> list1;
    private static CardAdapter aCardAdapter2;
    private RecyclerView mCardRecyclerView2;
    private static List<Card> list2;
    private TextView life1;
    private TextView life2;
    private Button add1;
    private Button sub1;
    private Button add2;
    private Button sub2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter);
//        CustomDialog cdd = new CustomDialog(CounterActivity.this);
//        cdd.show();
//        LayoutInflater factory = LayoutInflater.from(this);
//        final View deleteDialogView = factory.inflate(R.layout.custom_dialog, null);
//        final AlertDialog deleteDialog = new AlertDialog.Builder(this).create();
//        deleteDialog.setView(deleteDialogView);
//        deleteDialogView.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //your business logic
//                deleteDialog.dismiss();
//            }
//        });
//        deleteDialogView.findViewById(R.id.no).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deleteDialog.dismiss();
//            }
//        });
//
//        deleteDialog.show();
        try {
            list1.size();
        } catch (NullPointerException e) {
            list1 = new ArrayList<>();
        }
        try {
            list2.size();
        } catch (NullPointerException e) {
            list2 = new ArrayList<>();
        }
        life1 = findViewById(R.id.life1);
        life2 = findViewById(R.id.life2);
        life1.setText(String.valueOf(MainActivity.count1));
        life2.setText(String.valueOf(MainActivity.count2));
        add1 = findViewById(R.id.add1);
        sub1 = findViewById(R.id.sub1);
        add2 = findViewById(R.id.add2);
        sub2 = findViewById(R.id.sub2);
        add1.setOnClickListener(this);
        sub1.setOnClickListener(this);
        add2.setOnClickListener(this);
        sub2.setOnClickListener(this);
        aCardAdapter = new CardAdapter(this, list1,true);
        mCardRecyclerView = findViewById(R.id.cardRecyclerView1);
        mCardRecyclerView.setAdapter(aCardAdapter);
        mCardRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        aCardAdapter2 = new CardAdapter(this, list2,false);
        mCardRecyclerView2 = findViewById(R.id.cardRecyclerView2);
        mCardRecyclerView2.setAdapter(aCardAdapter2);
        mCardRecyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        add1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MainActivity.count1 += 5;
                life1.setText(String.valueOf(MainActivity.count1));
                return true;
            }
        });
        sub1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MainActivity.count1 -= 5;
                life1.setText(String.valueOf(MainActivity.count1));
                if (MainActivity.count1 <= 0) {
                    MainActivity.getMessage1(CounterActivity.this);
                }
                return true;
            }
        });
        add2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MainActivity.count2 += 5;
                life2.setText(String.valueOf(MainActivity.count2));

                return true;
            }
        });
        sub2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                MainActivity.count2 -= 5;
                life2.setText(String.valueOf(MainActivity.count2));
                if (MainActivity.count2 <= 0) {
                    MainActivity.getMessage2(CounterActivity.this);
                }
                return true;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        life1.setText(String.valueOf(MainActivity.count1));
        life2.setText(String.valueOf(MainActivity.count2));
    }

    public void back(View view) {
        updateLists();
        finish();
    }

    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case (R.id.add1):
                MainActivity.count1++;
                life1.setText(String.valueOf(MainActivity.count1));

                break;
            case (R.id.sub1):
                MainActivity.count1--;
                life1.setText(String.valueOf(MainActivity.count1));
                if (MainActivity.count1 <= 0) {
                    MainActivity.getMessage1(this);
                }

                break;
            case (R.id.add2):
                MainActivity.count2++;
                life2.setText(String.valueOf(MainActivity.count2));

                break;
            case (R.id.sub2):
                MainActivity.count2--;
                life2.setText(String.valueOf(MainActivity.count2));
                if (MainActivity.count2 <= 0) {
                    MainActivity.getMessage2(this);
                }

                break;
        }
    }

    public static void updateLists(){
        list1 =aCardAdapter.cards;
        list2 =aCardAdapter2.cards;
    }


}
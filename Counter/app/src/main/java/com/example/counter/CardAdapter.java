package com.example.counter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.counter.model.Card;

import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardHolder> {
    private final LayoutInflater layoutInflater;
    private Context context;
    public List<Card> cards;
    private boolean reversed;

    public CardAdapter(Context context, List<Card> cards, boolean reversed) {
        this.context = context;
        this.cards = cards;
        this.reversed = reversed;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemViewType(int position) {
        return (position == cards.size()) ? R.layout.button_layout : R.layout.cardlayout;
    }

    @NonNull
    @Override
    public CardHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == R.layout.cardlayout) {
            itemView = layoutInflater.inflate(R.layout.cardlayout, parent, false);
        } else {
            itemView = layoutInflater.inflate(R.layout.button_layout, parent, false);
        }
        return new CardHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CardHolder holder, int position) {
        if (position == cards.size()) {
            holder.plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(context);
                    LinearLayout layout = new LinearLayout(context);
                    layout.setBackground(context.getDrawable(R.drawable.shape_brown));
                    layout.setOrientation(LinearLayout.VERTICAL);
                    final EditText name = new EditText(context);
                    name.setHint("Nome");
                    layout.addView(name);
                    final EditText counter = new EditText(context);
                    counter.setHint("Counter");
                    counter.setInputType(InputType.TYPE_CLASS_NUMBER);
                    layout.addView(counter);
                    final EditText attack = new EditText(context);
                    attack.setInputType(InputType.TYPE_CLASS_NUMBER);
                    attack.setHint("Ataque");
                    layout.addView(attack);
                    final EditText defense = new EditText(context);
                    defense.setInputType(InputType.TYPE_CLASS_NUMBER);
                    defense.setHint("Defesa");
                    layout.addView(defense);
                    alert.setView(layout);

                    alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            String mName;
                            int mCounter;
                            int mAttack;
                            int mDefense;

                            try {
                                mName = name.getText().toString();
                                if (name.getText().toString().trim().equals("")) {
                                    throw new Exception();
                                }
                            } catch (Exception e) {
                                mName = "Carinha";
                            }
                            try {
                                mCounter = Integer.parseInt(counter.getText().toString());
                            } catch (Exception e) {
                                mCounter = 0;
                            }
                            try {
                                mAttack = Integer.parseInt(attack.getText().toString());
                            } catch (Exception e) {
                                mAttack = 0;
                            }
                            try {
                                mDefense = Integer.parseInt(defense.getText().toString());
                            } catch (Exception e) {
                                mDefense = 0;
                            }
                            Card card = new Card(mName, mCounter, mAttack, mDefense);
                            cards.add(card);
                            notifyItemInserted(cards.size());
                            notifyItemRangeChanged(cards.size(), cards.size());
                            CounterActivity.updateLists();


                        }
                    });

                    alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            // Canceled.
                        }
                    });

                    alert.show();
                }
            });
        } else {
            Card card = cards.get(position);
            holder.cardName.setText(card.getCardName());
            holder.counters.setText(String.valueOf(card.getCounters()));
            holder.cardStart.setText(String.valueOf(card.getInitialAttack()) + " / " + String.valueOf(card.getInitialDefense()));
        }
    }

    @Override
    public int getItemCount() {
        return cards.size() + 1;
    }

    class CardHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private LinearLayout plus;
        private TextView cardName;
        private TextView counters;
        private TextView cardStart;
        private LinearLayout linearAdd;
        private LinearLayout linearSub;
        private ConstraintLayout card;

        public CardHolder(@NonNull View itemView) {
            super(itemView);
            plus = itemView.findViewById(R.id.plus);
            cardName = itemView.findViewById(R.id.cardName);
            counters = itemView.findViewById(R.id.counters);
            cardStart = itemView.findViewById(R.id.cardStart);
            linearAdd = itemView.findViewById(R.id.linearAdd);
            linearSub = itemView.findViewById(R.id.linearSub);
            card = itemView.findViewById(R.id.card);
            try {
                linearAdd.setOnClickListener(this);
                linearSub.setOnClickListener(this);
                linearAdd.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(
                                context);
                        alert.setMessage("Morreu?");
                        alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cards.remove(getLayoutPosition());
                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getLayoutPosition(), cards.size());
                                if (reversed) {
                                    Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
                                    View toastView = toast.getView();
                                    toastView.setRotation(180);
                                    toast.setGravity(Gravity.TOP, 0, 0);
                                    toast.setText("RIP");
                                    toast.show();
                                } else {
                                    Toast.makeText(context, "RIP", Toast.LENGTH_LONG).show();

                                }
                                dialog.dismiss();
                            }
                        });
                        alert.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });

                        alert.show();
                        return true;
                    }
                });
                linearSub.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        AlertDialog.Builder alert = new AlertDialog.Builder(
                                context);
                        alert.setMessage("Morreu?");
                        alert.setPositiveButton("SIM", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                cards.remove(getLayoutPosition());
                                notifyItemRemoved(getAdapterPosition());
                                notifyItemRangeChanged(getLayoutPosition(), cards.size());
                                if (reversed) {
                                    Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
                                    View toastView = toast.getView();
                                    toastView.setRotation(180);
                                    toast.setGravity(Gravity.TOP, 0, 0);
                                    toast.setText("RIP");
                                    toast.show();
                                } else {
                                    Toast.makeText(context, "RIP", Toast.LENGTH_LONG).show();
                                }
                                dialog.dismiss();

                            }
                        });
                        alert.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.dismiss();
                            }
                        });

                        alert.show();
                        return true;
                    }
                });
            } catch (NullPointerException e) {

            }
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.linearAdd) {
                counters.setText(String.valueOf(cards.get(getLayoutPosition()).getCounters() + 1));
                cards.get(getLayoutPosition()).setCounters(cards.get(getLayoutPosition()).getCounters() + 1);
            }
            if (view.getId() == R.id.linearSub) {
                counters.setText(String.valueOf(cards.get(getLayoutPosition()).getCounters() - 1));
                cards.get(getLayoutPosition()).setCounters(cards.get(getLayoutPosition()).getCounters() - 1);
            }
        }
    }
}

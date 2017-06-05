package io.rohithram.cricapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import static io.rohithram.cricapp.R.id.rv_list;

/**
 * Created by rohithram on 4/6/17.
 */

public class CustomAdapter extends RecyclerView.Adapter <CustomAdapter.ViewHolder>{
    Context context;
    List<Matches> Matches;
    List<Matchscores> Matchscores;
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.contentmain1, parent, false);
        return new ViewHolder(itemView);
    }

    public CustomAdapter(Context context, List<Matches> matches){
        this.context = context;
        this.Matches=matches;
    }
    @Override
    public void onBindViewHolder(final CustomAdapter.ViewHolder holder, int position) {
        holder.tv_teams.setText(Matches.get(holder.getAdapterPosition()).getTeam1()+"   vs  "+Matches.get(holder.getAdapterPosition()).getTeam2());
        final double id = Matches.get(holder.getAdapterPosition()).getMatchid();
        holder.cv_match.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent i = new Intent(context,Main2Activity.class);
                i.putExtra("send", String.valueOf(holder.tv_teams.getText()));
                i.putExtra("send1",String.valueOf(id));
                context.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return Matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       public TextView tv_teams;
       public CardView cv_match;
       public ViewHolder(View itemView) {
            super(itemView);
           cv_match=(CardView)itemView.findViewById(R.id.cv_match);
           tv_teams=(TextView)itemView.findViewById(R.id.tv_teams);
        }
    }
}

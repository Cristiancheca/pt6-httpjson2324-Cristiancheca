package com.example.actividad_6_cristian_checa;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder>{

    private List<Team> teamElements;
    private String URL;

    public TeamAdapter(List<Team> elements, String URL) {
        this.teamElements = elements;
        this.URL = URL;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {

        View viewElement = LayoutInflater.from(parent.getContext()).inflate(R.layout.one_line, parent, false);

        return new ViewHolder(viewElement);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.println(teamElements.get(position).getTextViewTitles());
        holder.getTxtElement().setText(teamElements.get(position).getTextViewTitles());
    }

    @Override
    public int getItemCount() {
        return teamElements.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.TextViewName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ViewHolder.this.showElement(v);
                }
            });

        }

        private void showElement(View v) {
            Team team = teamElements.get(getAdapterPosition());

            //TODO: FIX THIS
            String URL2 = URL + "/" + team.getteamAbbreviation();

            System.out.println("TeamAdapter: " + URL2);
            Intent showTeam = new Intent(v.getContext(), ShowTeam.class);
            showTeam.putExtra("URL", URL2);

            v.getContext().startActivity(showTeam);
        }

        public TextView getTxtElement(){
            return textViewName;
        }
    }

}

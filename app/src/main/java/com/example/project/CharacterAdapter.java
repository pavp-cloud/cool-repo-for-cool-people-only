package com.example.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characterList;

    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        Character character = characterList.get(position);
        holder.nameText.setText(character.getName());
        holder.statsText.setText(String.format("Health: %d | Class: %s | Exp: %d",
                character.getMaxHealth(),
                character.getClass().getSimpleName(), character.getExp()));
    }

    @Override
    public int getItemCount() { return characterList.size(); }

    public void refreshData(List<Character> newList) {
        this.characterList = newList;
        notifyDataSetChanged();
    }

    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, statsText;
        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.text_character_name);
            statsText = itemView.findViewById(R.id.text_character_stats);
        }
    }
}

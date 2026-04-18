package com.example.project.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.entities.characterObjects.Character;

import java.util.List;

public class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {
    private List<Character> characterList;
    private OnCharacterClickListener listener;

    // Interface to handle character selection clicks
    public interface OnCharacterClickListener {
        void onCharacterClick(Character character);
    }

    // Default constructor
    public CharacterAdapter(List<Character> characterList) {
        this.characterList = characterList;
    }

    // Constructor with listener for selection screens
    public CharacterAdapter(List<Character> characterList, OnCharacterClickListener listener) {
        this.characterList = characterList;
        this.listener = listener;
    }

    @NonNull
    @Override // Wraps the view in a holder so it can be reused later
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
        return new CharacterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        com.example.project.entities.characterObjects.Character character = characterList.get(position);
        
        // Display character info
        holder.nameText.setText(String.format("%s \nStatus: %s", character.getName(), character.getStatus()));
        holder.statsText.setText(String.format("Health: %d | Class: %s | Exp: %d \nMissions Completed: %d",
                character.getMaxHealth(), character.getClass().getSimpleName(), character.getExp(), character.getMissionsCompleted()));

        // Trigger the listener when the item is clicked
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCharacterClick(character);
            }
        });
    }

    // Gets the number of characters
    @Override
    public int getItemCount() { return characterList.size(); }

    // Refreshes list of characters when needed
    public void refreshData(List<Character> newList) {
        this.characterList = newList;
        notifyDataSetChanged();
    }

    // Finds the view fields and saves them onto the nameText and statsText fields
    static class CharacterViewHolder extends RecyclerView.ViewHolder {
        TextView nameText, statsText;
        public CharacterViewHolder(@NonNull View itemView) {
            super(itemView);
            nameText = itemView.findViewById(R.id.text_character_name);
            statsText = itemView.findViewById(R.id.text_character_stats);
        }
    }
}

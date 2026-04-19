package com.example.project.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.fragments.MainMenuFragment;
import com.example.project.R;
import com.example.project.spaceshipObjects.SpaceShip;
import com.example.project.entities.characterObjects.Character;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        // initializes the spaceship
        SpaceShip.getInstance();

        // initializes the start game button
        Button newGameButton = findViewById(R.id.new_game_button);

        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hides all the buttons and text views
                findViewById(R.id.new_game_button).setVisibility(View.GONE);
                findViewById(R.id.game_name_text).setVisibility(View.GONE);
                findViewById(R.id.imageView).setVisibility(View.GONE);


                // makes the main menu visible
                View container = findViewById(R.id.fragment_container);
                if (container != null) {
                    container.setVisibility(View.VISIBLE);

                    // adds the main menu fragment into the container
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new MainMenuFragment())
                            .commit();
                }
            }
        });
    }
}
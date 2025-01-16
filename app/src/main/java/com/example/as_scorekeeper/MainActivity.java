package com.example.as_scorekeeper;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private int mScore1 = 0; // Puntuación del equipo 1
    private int mScore2 = 0; // Puntuación del equipo 2
    private TextView mScoreText1; // TextView para mostrar la puntuación del equipo 1
    private TextView mScoreText2; // TextView para mostrar la puntuación del equipo 2

    // Claves para guardar las puntuaciones en onSaveInstanceState
    private static final String STATE_SCORE_1 = "Team 1 Score";
    private static final String STATE_SCORE_2 = "Team 2 Score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Vinculación de los TextView con los ID del layout
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Restaurar el estado de las puntuaciones si se guarda previamente
        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1);
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2);
            mScoreText1.setText(String.valueOf(mScore1));
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    // Método para incrementar la puntuación
    public void increaseScore(View view) {
        int viewID = view.getId();
        if (viewID == R.id.increaseTeam1) {
            mScore1++;
            mScoreText1.setText(String.valueOf(mScore1));
        } else if (viewID == R.id.increaseTeam2) {
            mScore2++;
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    // Método para decrementar la puntuación
    public void decreaseScore(View view) {
        int viewID = view.getId();
        if (viewID == R.id.decreaseTeam1) {
            mScore1--;
            mScoreText1.setText(String.valueOf(mScore1));
        } else if (viewID == R.id.decreaseTeam2) {
            mScore2--;
            mScoreText2.setText(String.valueOf(mScore2));
        }
    }

    // Crear el menú de opciones (incluyendo el modo nocturno)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        // Cambiar el título del menú según el modo actual (día o noche)
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return true;
    }

    // Manejar los clics en las opciones del menú
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.night_mode) {
            // Alternar entre modo día y noche
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            recreate(); // Recrear la actividad para aplicar el cambio de tema
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Guardar las puntuaciones actuales cuando se destruye la actividad
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SCORE_1, mScore1);
        outState.putInt(STATE_SCORE_2, mScore2);
        super.onSaveInstanceState(outState);
    }
}

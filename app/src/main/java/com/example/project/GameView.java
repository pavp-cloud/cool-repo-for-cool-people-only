package com.example.project;

import static android.provider.SyncStateContract.Helpers.update;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    public enum combatState{
        Players_Turn,
        Enemys_Turn,
        Animating,
        GameOver,
    }
    public enum attackState{
        Attack,
        Special_Attack,
    }
    private combatState activeCombat = combatState.Players_Turn;
    private attackState selectAttack = null;
    private Thread thread;
    private SurfaceHolder surfaceHolder;
    private volatile boolean isPlaying;
    private Canvas canvas;
    private Paint paint;

    public GameView(Context context) {
        super(context);
        this.surfaceHolder = getHolder();
        this.paint = new Paint();

    }

    @Override
    public void run() {
        // This is for when the game is running
        while (isPlaying) {
            update();//controls positioning
            draw();//control what is on the screen
            control();//controls frame rate
        }
    }
    private void update(){
        //Function as the logic to move/control the characters and actions by turns 
        if (activeCombat == combatState.Players_Turn) {
            //wait for input from player
            }
        else if (activeCombat == combatState.Enemys_Turn){
            perfromEnemyActions();
            activeCombat = combatState.Players_Turn;
            }
        }


    private void draw(){

    }

}

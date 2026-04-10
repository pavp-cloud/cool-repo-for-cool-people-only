package com.example.project;

import static android.provider.SyncStateContract.Helpers.update;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CombatView extends SurfaceView implements Runnable {
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
    private int animationTimer = 0;
    private boolean showExplosion = false;
    private float exploisonX, explosionY;
    private boolean showBasicAttack = false;
    private float swingX, swingY;


    public CombatView(Context context) {
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
            //sends back to players turn
            }
        if (activeCombat == combatState.Animating){

        }
        }
    private void perfromEnemyActions(){
        //Enemy AI logic
    }

    private void draw(){
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.BLACK);

            paint.setColor(Color.WHITE);
            paint.setTextSize(35);
            canvas.drawText("Turn: " + activeCombat, 50, 50, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
    private void control(){
        try {
            thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void pause() {
        isPlaying = true;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void resume() {
        isPlaying = false;
        try{
            thread = new Thread(this);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

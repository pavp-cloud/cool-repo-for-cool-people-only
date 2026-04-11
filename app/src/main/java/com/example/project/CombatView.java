package com.example.project;

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
    private boolean showSpecialAttack = false;
    private float exploisonX, explosionY;
    private boolean showBasicAttack = false;
    private float shootX, shootY;



    public CombatView (Context context) {
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


    private void update() {
        //Function as the logic to move/control the characters and actions by turns
        if (activeCombat == combatState.Players_Turn) {
            //wait for input from player
        } else if (activeCombat == combatState.Enemys_Turn) {
            perfromEnemyActions();
            activeCombat = combatState.Players_Turn;
            //sends back to players turn
        }
        if (activeCombat == combatState.Animating) {
            animationTimer++;

            if (selectAttack == attackState.Attack) {
                if (animationTimer > 50) endAnimation();
            } else if (selectAttack == attackState.Special_Attack) {
                if (animationTimer > 80) endAnimation();
            }
        }
    }
    private void endAnimation(){
        animationTimer = 0;
        activeCombat = combatState.Enemys_Turn;
        selectAttack = null;
    }
    private static void perfromEnemyActions(){
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
        if (activeCombat == combatState.Animating){
            if (selectAttack == attackState.Attack){
                paint.setColor(Color.RED);
                float laserX = 300 + (animationTimer % 10) * 40;
                canvas.drawRect(laserX, 500, laserX + 20, 505, paint);

            }
            else if (selectAttack == attackState.Special_Attack){
                int alpha = Math.max(0, 255 - (animationTimer * 255));
                paint.setAlpha(alpha);
                paint.setColor(Color.rgb(20,69, 255));
                canvas.drawCircle(800, 500, animationTimer * 4, paint);

            }

        }
        surfaceHolder.unlockCanvasAndPost(canvas);
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

package com.example.project.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.project.entities.threatObjects.specializations.Demon;
import com.example.project.entities.characterObjects.specializations.Engineer;
import com.example.project.entities.threatObjects.specializations.Gundam;
import com.example.project.entities.characterObjects.specializations.Medic;
import com.example.project.mission.Mission;
import com.example.project.entities.threatObjects.specializations.Parasite;
import com.example.project.entities.characterObjects.specializations.Pilot;
import com.example.project.entities.threatObjects.specializations.Pirate;
import com.example.project.R;
import com.example.project.entities.characterObjects.specializations.Scientist;
import com.example.project.entities.characterObjects.specializations.Soldier;
import com.example.project.entities.characterObjects.Character;
import com.example.project.entities.threatObjects.Threat;
import com.example.project.entities.threatObjects.specializations.Alien;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class CombatView extends SurfaceView implements Runnable, SurfaceHolder.Callback {

    public enum combatState {
        Players_Turn,
        Enemys_Turn,
        Animating,
        GameOver,
    }

    public enum attackState {
        Attack,
        Special_Attack,
    }
    //interfacinging to ensure combat has started to initate combatview
    public interface OnCombatEndedListener {
        void onCombatEnded();
    }

    private combatState activeCombat = combatState.Players_Turn;
    private attackState selectAttack = null;
    private int actingCrewIndex = 1;

    private Thread thread;
    private SurfaceHolder surfaceHolder;
    private volatile boolean isPlaying;
    private Canvas canvas;
    private Paint paint;
    private int animationTimer = 0;
    //Positioning
    private float targetX, targetY;
    private float character1X, character1Y;
    private float character2X, character2Y;
    private float enemyX, enemyY;

    // Button Bounds
    private RectF c1AttackBtn = new RectF();
    private RectF c1SpecialBtn = new RectF();
    private RectF c2AttackBtn = new RectF();
    private RectF c2SpecialBtn = new RectF();
    private RectF continueBtn = new RectF();

    private Character crewMember1;
    private Character crewMember2;
    private Threat missionThreat;
    private Mission activeMission;
    private Random random = new Random();

    // Sprite Map
    private Map<Class<?>, Bitmap> spriteMap = new HashMap<>();
    private Bitmap defaultSprite;
    private Bitmap backgroundSprite;
    private OnCombatEndedListener combatEndedListener;

    public CombatView(Context context) {
        super(context);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.paint = new Paint();
        loadSprites(context);
    }
    //checking for end of combat to then be linked to the continue button to initate endofcombat prep.
    public void setOnCombatEndedListener(OnCombatEndedListener listener) {
        this.combatEndedListener = listener;
    }

    private void loadSprites(Context context) {
        spriteMap.put(Medic.class, BitmapFactory.decodeResource(getResources(), R.drawable.medic_sprite));
        spriteMap.put(Soldier.class, BitmapFactory.decodeResource(getResources(), R.drawable.solider_sprite));
        spriteMap.put(Scientist.class, BitmapFactory.decodeResource(getResources(), R.drawable.scientist_sprite));
        spriteMap.put(Pilot.class, BitmapFactory.decodeResource(getResources(), R.drawable.pilot_sprite));
        spriteMap.put(Engineer.class, BitmapFactory.decodeResource(getResources(), R.drawable.engineer_sprite));
        spriteMap.put(Pirate.class, BitmapFactory.decodeResource(getResources(), R.drawable.pirate_sprite));
        spriteMap.put(Parasite.class, BitmapFactory.decodeResource(getResources(), R.drawable.parasite_sprite));
        spriteMap.put(Gundam.class, BitmapFactory.decodeResource(getResources(), R.drawable.gundam_sprite));
        spriteMap.put(Alien.class, BitmapFactory.decodeResource(getResources(), R.drawable.alien_sprite));
        spriteMap.put(Demon.class, BitmapFactory.decodeResource(getResources(), R.drawable.demon_sprite));
        
        defaultSprite = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        backgroundSprite = BitmapFactory.decodeResource(getResources(), R.drawable.combat_background2);
    }

    //Set up Mission with 2 charaters and 1 threat
    public void setupCombat(Mission mission) {
        this.activeMission = mission;
        this.crewMember1 = mission.getCrewMember1();
        this.crewMember2 = mission.getCrewMember2();
        this.missionThreat = mission.getMissionTarget();
    }

    // update override, draw override, and control overrride
    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }
        //drawing in buttons, sprites, and victory/defeat
    private void update() {
        if (activeCombat == combatState.Enemys_Turn) {
            performEnemyActions();
        }

        if (activeCombat == combatState.Animating) {
            animationTimer++;
            int limit = (selectAttack == attackState.Attack) ? 30 : 50;
            if (animationTimer > limit) endAnimation();
        }

        checkGameOver();
    }

    private void checkGameOver() {
        if (activeMission != null && activeMission.isGameOver()) {
            activeCombat = combatState.GameOver;
        }
    }

    private void endAnimation() {
        animationTimer = 0;
        if (targetX == enemyX) { // Player finished attack
            if (activeMission.isGameOver()) {
                activeCombat = combatState.GameOver;
            } else if (!activeMission.isPlayerTurn()) {
                activeCombat = combatState.Enemys_Turn;
            } else {
                activeCombat = combatState.Players_Turn;
            }
        } else { // Enemy finished attack
            activeCombat = combatState.Players_Turn;
        }
        selectAttack = null;
    }

    public void playerAttack(int crewIndex, attackState type) {
        if (activeCombat == combatState.Players_Turn) {
            Character member = (crewIndex == 1) ? crewMember1 : crewMember2;
            if (member == null || member.getCurrentHealth() <= 0) return;

            if (crewIndex == 1 && activeMission.isCrew1Moved()) return;
            if (crewIndex == 2 && activeMission.isCrew2Moved()) return;

            actingCrewIndex = crewIndex;
            selectAttack = type;
            targetX = enemyX;
            targetY = enemyY;

            int damage = (type == attackState.Attack ? member.attack() : member.special());
            String attackName = (type == attackState.Attack ? "Attacked" : "used a Special Attack");
            showCombatToast(member.getName() + " " + attackName + " for " + damage + " damage!");


            activeMission.playerTurn(crewIndex, (type == attackState.Attack ? 0 : 1));
            activeCombat = combatState.Animating;
        }
    }

    private void performEnemyActions() {
        if (activeCombat != combatState.Enemys_Turn || activeMission == null) return;

        int actionIndex = activeMission.enemyTurn();
        if (actionIndex == -1) return;
        
        selectAttack = (actionIndex == 0) ? attackState.Attack : attackState.Special_Attack;

        String actionName = (actionIndex == 0) ? "Attacked" : "used a Special Attack";
        showCombatToast(missionThreat.getName() + " " + actionName + "!");

        
        if (crewMember1 != null && crewMember1.getCurrentHealth() > 0 && crewMember2 != null && crewMember2.getCurrentHealth() > 0) {
            targetX = random.nextBoolean() ? character1X : character2X;
            targetY = (targetX == character1X) ? character1Y : character2Y;
        } else if (crewMember1 != null && crewMember1.getCurrentHealth() > 0) {
            targetX = character1X;
            targetY = character1Y;
        } else {
            targetX = character2X;
            targetY = character2Y;
        }
        
        activeCombat = combatState.Animating;
    }



    private void showCombatToast(final String message) {
        post(() -> Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show());
    }
    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            if (canvas == null) return;
            //character and enemy positions on the screen
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            character1X = width * 0.25f;
            character1Y = height * 0.40f;
            character2X = width * 0.25f;
            character2Y = height * 0.65f;
            enemyX = width * 0.75f;
            enemyY = height * 0.50f;
            float btnY = height * 0.85f;
            float btnHeight = height * 0.07f;
            float btnWidth = width * 0.20f;
            float spacing = width * 0.03f;
            float totalWidth = (btnWidth * 4) + (spacing * 3);
            float startX = (width - totalWidth) / 2;

            c1AttackBtn.set(startX, btnY, startX + btnWidth, btnY + btnHeight);
            c1SpecialBtn.set(startX + btnWidth + spacing, btnY, startX + btnWidth*2 + spacing, btnY + btnHeight);
            c2AttackBtn.set(startX + btnWidth*2 + spacing*2, btnY, startX + btnWidth*3 + spacing*2, btnY + btnHeight);
            c2SpecialBtn.set(startX + btnWidth*3 + spacing*3, btnY, startX + btnWidth*4 + spacing*3, btnY + btnHeight);
            continueBtn.set(width * 0.25f, height * 0.72f, width * 0.75f, height * 0.82f);

            // Draw Background
            if (backgroundSprite != null) {
                canvas.drawBitmap(backgroundSprite, null, new Rect(0, 0, width, height), null);
            } else {
                canvas.drawColor(Color.BLACK);
            }

            paint.setColor(Color.WHITE);
            paint.setTextSize(width * 0.04f);
            canvas.drawText("Turn State: " + activeCombat, 50, 50, paint);

            drawEntity(canvas, crewMember1, character1X, character1Y);
            drawEntity(canvas, crewMember2, character2X, character2Y);
            drawEntity(canvas, missionThreat, enemyX, enemyY);

            if (activeCombat == combatState.Players_Turn) {
                int c1Color = (activeMission.isCrew1Moved() || crewMember1 == null || crewMember1.getCurrentHealth() <= 0) ? Color.GRAY : Color.DKGRAY;
                int c2Color = (activeMission.isCrew2Moved() || crewMember2 == null || crewMember2.getCurrentHealth() <= 0) ? Color.GRAY : Color.DKGRAY;

                drawButton(canvas, c1AttackBtn, "C1 ATK", c1Color);
                drawButton(canvas, c1SpecialBtn, "C1 SPEC", c1Color);
                drawButton(canvas, c2AttackBtn, "C2 ATK", c2Color);
                drawButton(canvas, c2SpecialBtn, "C2 SPEC", c2Color);
            }

            if (activeCombat == combatState.Animating) {
                drawAnimation();
            }

            if (activeCombat == combatState.GameOver) {
                paint.setColor(Color.YELLOW);
                paint.setTextSize(width * 0.12f);
                String msg = (missionThreat != null && missionThreat.getCurrentHealth() <= 0) ? "VICTORY" : "DEFEAT";
                float textWidth = paint.measureText(msg);
                canvas.drawText(msg, width/2 - textWidth/2, height * 0.20f, paint);

                drawButton(canvas, continueBtn, "CONTINUE TO SHIP", Color.BLUE);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawButton(Canvas canvas, RectF bounds, String text, int color) {
        paint.setColor(color);
        canvas.drawRoundRect(bounds, 15, 15, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(canvas.getWidth() * 0.035f);
        float textWidth = paint.measureText(text);
        canvas.drawText(text, bounds.centerX() - textWidth / 2, bounds.centerY() + paint.getTextSize()/3, paint);
    }

    private void drawEntity(Canvas canvas, Object entity, float x, float y) {
        if (entity == null) return;
        
        int width = canvas.getWidth();
        String name = "";
        int hp = 0, maxHp = 0;
        Bitmap sprite = defaultSprite;

        if (entity instanceof Character) {
            Character c = (Character) entity;
            name = c.getName();
            hp = c.getCurrentHealth();
            maxHp = c.getMaxHealth();
            sprite = spriteMap.getOrDefault(c.getClass(), defaultSprite);
        } else if (entity instanceof Threat) {
            Threat t = (Threat) entity;
            name = t.getName();
            hp = t.getCurrentHealth();
            maxHp = t.getMaxHealth();
            sprite = spriteMap.getOrDefault(t.getClass(), defaultSprite);
        }


        if (sprite != null) {
            float size = width * 0.15f;
            Rect dest = new Rect((int)(x - size), (int)(y - size), (int)(x + size), (int)(y + size));
            if (hp <= 0) {
                paint.setAlpha(128);
            } else {
                paint.setAlpha(255);
            }
            canvas.drawBitmap(sprite, null, dest, paint);
            paint.setAlpha(255);
        }

        paint.setColor(Color.WHITE);
        paint.setTextSize(width * 0.035f);
        float nameWidth = paint.measureText(name);
        canvas.drawText(name, x - nameWidth/2, y - width * 0.18f, paint);
        
        // HP bar
        float barWidth = width * 0.25f;
        float barHeight = width * 0.04f;
        float barTop = y + width * 0.18f;

        paint.setColor(Color.GRAY);
        canvas.drawRect(x - barWidth/2, barTop, x + barWidth/2, barTop + barHeight, paint);
        
        if (maxHp > 0) {
            paint.setColor(Color.GREEN);
            float hpRatio = (float) Math.max(0, hp) / maxHp;
            canvas.drawRect(x - barWidth/2, barTop, x - barWidth/2 + (barWidth * hpRatio), barTop + barHeight, paint);
            
            //HP Text
            paint.setColor(Color.WHITE);
            paint.setTextSize(width * 0.045f);
            String hpText = hp + " / " + maxHp;
            float textWidth = paint.measureText(hpText);
            canvas.drawText(hpText, x - textWidth/2, barTop + barHeight + paint.getTextSize() + 5, paint);
        }
    }

    private void drawAnimation() {
        float startX, startY;
        if (targetX == enemyX) {
            startX = (actingCrewIndex == 1) ? character1X : character2X;
            startY = (actingCrewIndex == 1) ? character1Y : character2Y;
        } else {
            startX = enemyX;
            startY = enemyY;
        }

        if (selectAttack == attackState.Attack) {
            paint.setColor(Color.RED);
            float progress = (float) animationTimer / 30;
            float currentX = startX + (targetX - startX) * progress;
            float currentY = startY + (targetY - startY) * progress;
            canvas.drawCircle(currentX, currentY, 15, paint);
        } else if (selectAttack == attackState.Special_Attack) {
            paint.setColor(Color.YELLOW);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawCircle(targetX, targetY, animationTimer * 10, paint);
            paint.setStyle(Paint.Style.FILL);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            performClick();
            float x = event.getX();
            float y = event.getY();

            if (activeCombat == combatState.Players_Turn) {
                if (c1AttackBtn.contains(x, y)) playerAttack(1, attackState.Attack);
                else if (c1SpecialBtn.contains(x, y)) playerAttack(1, attackState.Special_Attack);
                else if (c2AttackBtn.contains(x, y)) playerAttack(2, attackState.Attack);
                else if (c2SpecialBtn.contains(x, y)) playerAttack(2, attackState.Special_Attack);
            } else if (activeCombat == combatState.GameOver) {
                if (continueBtn.contains(x, y)) {
                    if (combatEndedListener != null) {
                        combatEndedListener.onCombatEnded();
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    private void control() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {}

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        isPlaying = false;
        try {
            if (thread != null) thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

package com.example.project;

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

import androidx.annotation.NonNull;

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

    private combatState activeCombat = combatState.Players_Turn;
    private attackState selectAttack = null;
    private int actingCrewIndex = 1;

    private Thread thread;
    private SurfaceHolder surfaceHolder;
    private volatile boolean isPlaying;
    private Canvas canvas;
    private Paint paint;
    private int animationTimer = 0;
    
    private float targetX, targetY;
    private float character1X = 200, character1Y = 300;
    private float character2X = 200, character2Y = 500;
    private float enemyX = 800, enemyY = 400;

    // Button Bounds
    private RectF c1AttackBtn = new RectF(50, 700, 250, 780);
    private RectF c1SpecialBtn = new RectF(270, 700, 470, 780);
    private RectF c2AttackBtn = new RectF(50, 800, 250, 880);
    private RectF c2SpecialBtn = new RectF(270, 800, 470, 880);

    private Character crewMember1;
    private Character crewMember2;
    private Threat missionThreat;
    private Mission activeMission;
    private Random random = new Random();

    // Sprite Map
    private Map<Class<?>, Bitmap> spriteMap = new HashMap<>();
    private Bitmap defaultSprite;
    private Bitmap backgroundSprite;

    public CombatView(Context context) {
        super(context);
        this.surfaceHolder = getHolder();
        this.surfaceHolder.addCallback(this);
        this.paint = new Paint();
        loadSprites(context);
    }

    private void loadSprites(Context context) {
        // Character Sprites
        spriteMap.put(Medic.class, BitmapFactory.decodeResource(getResources(), R.drawable.medic_sprite)); // Replace with actual medic sprite
        spriteMap.put(Soldier.class, BitmapFactory.decodeResource(getResources(), R.drawable.solider_sprite)); // Replace with actual soldier sprite
        spriteMap.put(Scientist.class, BitmapFactory.decodeResource(getResources(), R.drawable.scientist_sprite)); // Replace with actual scientist sprite
        spriteMap.put(Pilot.class, BitmapFactory.decodeResource(getResources(), R.drawable.pilot_sprite)); // Replace with actual pilot sprite
        spriteMap.put(Engineer.class, BitmapFactory.decodeResource(getResources(), R.drawable.engineer_sprite)); // Replace with actual engineer sprite

        // Threat Sprites
        spriteMap.put(Pirate.class, BitmapFactory.decodeResource(getResources(), R.drawable.pirate_sprite)); // Replace with actual pirate sprite
        spriteMap.put(Parasite.class, BitmapFactory.decodeResource(getResources(), R.drawable.parasite_sprite)); // Replace with actual parasite sprite
        spriteMap.put(Gundam.class, BitmapFactory.decodeResource(getResources(), R.drawable.gundam_sprite)); // Replace with actual gundam sprite
        spriteMap.put(Alien.class, BitmapFactory.decodeResource(getResources(), R.drawable.alien_sprite)); // Replace with actual alien sprite
        spriteMap.put(Demon.class, BitmapFactory.decodeResource(getResources(), R.drawable.demon_sprite)); // Replace with actual demon sprite

        defaultSprite = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        
        // Load Background Sprite
        backgroundSprite = BitmapFactory.decodeResource(getResources(), R.drawable.combat_background);
    }

    public void setupCombat(Mission mission) {
        this.activeMission = mission;
        this.crewMember1 = mission.getCrewMember1();
        this.crewMember2 = mission.getCrewMember2();
        this.missionThreat = mission.getMissionTarget();
    }

    @Override
    public void run() {
        while (isPlaying) {
            update();
            draw();
            control();
        }
    }

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
            // Ensure character exists and is alive
            Character member = (crewIndex == 1) ? crewMember1 : crewMember2;
            if (member == null || member.getCurrentHealth() <= 0) return;

            // Check if this specific character has already moved in the Mission logic
            if (crewIndex == 1 && activeMission.isCrew1Moved()) return;
            if (crewIndex == 2 && activeMission.isCrew2Moved()) return;

            actingCrewIndex = crewIndex;
            selectAttack = type;
            targetX = enemyX;
            targetY = enemyY;

            activeMission.playerTurn(crewIndex, (type == attackState.Attack ? 0 : 1));
            activeCombat = combatState.Animating;
        }
    }

    private void performEnemyActions() {
        if (activeCombat != combatState.Enemys_Turn || activeMission == null) return;

        int actionIndex = activeMission.enemyTurn();
        if (actionIndex == -1) return;
        
        selectAttack = (actionIndex == 0) ? attackState.Attack : attackState.Special_Attack;
        
        // Pick a visual target that is alive for the animation
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

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            if (canvas == null) return;
            
            // Draw Background
            if (backgroundSprite != null) {
                Rect destRect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
                canvas.drawBitmap(backgroundSprite, null, destRect, null);
            } else {
                canvas.drawColor(Color.BLACK);
            }

            paint.setColor(Color.WHITE);
            paint.setTextSize(40);
            canvas.drawText("Turn State: " + activeCombat, 50, 50, paint);

            drawEntity(canvas, crewMember1, character1X, character1Y);
            drawEntity(canvas, crewMember2, character2X, character2Y);
            drawEntity(canvas, missionThreat, enemyX, enemyY);

            if (activeCombat == combatState.Players_Turn) {
                // Gray out buttons for crew members who have already moved or are dead
                int c1Color = (activeMission.isCrew1Moved() || crewMember1 == null || crewMember1.getCurrentHealth() <= 0) ? Color.GRAY : Color.DKGRAY;
                int c2Color = (activeMission.isCrew2Moved() || crewMember2 == null || crewMember2.getCurrentHealth() <= 0) ? Color.GRAY : Color.DKGRAY;

                drawButton(canvas, c1AttackBtn, "C1 Attack", c1Color);
                drawButton(canvas, c1SpecialBtn, "C1 Special", c1Color);
                drawButton(canvas, c2AttackBtn, "C2 Attack", c2Color);
                drawButton(canvas, c2SpecialBtn, "C2 Special", c2Color);
            }

            if (activeCombat == combatState.Animating) {
                drawAnimation();
            }

            if (activeCombat == combatState.GameOver) {
                paint.setColor(Color.YELLOW);
                paint.setTextSize(100);
                String msg = (missionThreat != null && missionThreat.getCurrentHealth() <= 0) ? "VICTORY" : "DEFEAT";
                canvas.drawText(msg, 200, 500, paint);
            }

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawButton(Canvas canvas, RectF bounds, String text, int color) {
        paint.setColor(color);
        canvas.drawRoundRect(bounds, 15, 15, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        float textWidth = paint.measureText(text);
        canvas.drawText(text, bounds.centerX() - textWidth / 2, bounds.centerY() + 10, paint);
    }

    private void drawEntity(Canvas canvas, Object entity, float x, float y) {
        if (entity == null) return;
        
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

        // Draw Sprite
        if (sprite != null) {
            Rect dest = new Rect((int)x - 60, (int)y - 60, (int)x + 60, (int)y + 60);
            if (hp <= 0) {
                paint.setAlpha(128); // Ghostly look if dead
            } else {
                paint.setAlpha(255);
            }
            canvas.drawBitmap(sprite, null, dest, paint);
            paint.setAlpha(255);
        }

        paint.setColor(Color.WHITE);
        paint.setTextSize(30);
        canvas.drawText(name, x - 50, y - 70, paint);
        
        paint.setColor(Color.GRAY);
        canvas.drawRect(x - 50, y + 60, x + 50, y + 70, paint);
        paint.setColor(Color.GREEN);
        if (maxHp > 0) {
            float hpWidth = 100 * (Math.max(0, (float) hp / maxHp));
            canvas.drawRect(x - 50, y + 60, x - 50 + hpWidth, y + 70, paint);
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
            canvas.drawCircle(targetX, targetY, animationTimer * 5, paint);
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

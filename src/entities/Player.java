/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

import static Utilz.Constants.Directions.DOWN;
import static Utilz.Constants.Directions.LEFT;
import static Utilz.Constants.Directions.RIGHT;
import static Utilz.Constants.Directions.UP;
import static Utilz.Constants.PlayerConstants.GetSpriteAmount;
import static Utilz.Constants.PlayerConstants.IDLE;
import static Utilz.Constants.PlayerConstants.RUNNING;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author 27656
 */
public class Player extends Entity {

    private BufferedImage[][] animations;
    private int animationTick, animationIndex, animationSpeed = 8;
    private int playerAction = IDLE;

    private boolean moving = false;
    private boolean left, right, up, down;
    private int playerDir;

    public Player(float x, float y) {
        super(x, y);
        LoadAnimations();
    }

    public void update() {
        updateAnimation();
        setAnimation();
        updatePos();
    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 128, 128, null);
    }

    public void setDirection(int direction) {
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving(Boolean moving) {
        this.moving = moving;
    }

    private void updateAnimation() {
        animationTick++;
        if (animationTick >= animationSpeed) {
            animationTick = 0;
            animationIndex++;
            if (animationIndex >= GetSpriteAmount(playerAction)) {
                animationIndex = 0;
            }
        }
    }

    private void setAnimation() {
        if (moving) {
            playerAction = RUNNING;
        } else {
            playerAction = IDLE;
        }
    }

    private void updatePos() {
        if (moving) {
            switch (playerDir) {
                case LEFT:
                    x -= 5;
                    break;
                case UP:
                    y -= 5;
                    break;
                case RIGHT:
                    x += 5;
                    break;
                case DOWN:
                    y += 5;
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }

    private void LoadAnimations() {
        InputStream is = getClass().getResourceAsStream("/blueman.png");
        try {
            BufferedImage img = ImageIO.read(is);
            animations = new BufferedImage[7][12];
            for (int j = 0; j < animations.length; j++) {
                for (int i = 0; i < animations[j].length; i++) {
                    animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                System.err.println(e);
            }
        }

    }

}

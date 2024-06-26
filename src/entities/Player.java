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
    private float playerSpeed = 3.0f;

    public Player(float x, float y) {
        super(x, y);
        LoadAnimations();
    }

    public void update() {
        updatePos();
        updateAnimation();
        setAnimation();

    }

    public void render(Graphics g) {
        g.drawImage(animations[playerAction][animationIndex], (int) x, (int) y, 128, 128, null);
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
        moving = false;
        if (left && !right) {
            x -= playerSpeed;
            moving = true;
        } else {
            if (right && !left) {
                x += playerSpeed;
                moving = true;
            }
        }

        if (up && !down) {
            y -= playerSpeed;
            moving = true;
        } else {
            if (down && !up) {
                y += playerSpeed;
                moving = true;
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

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

}

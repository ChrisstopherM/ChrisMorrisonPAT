/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utilz;

/**
 *
 * @author 27656
 */
public class Constants {

    public static class Directions {
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;

    }

    public static class PlayerConstants {

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int DOUBLE_JUMP = 3;
        public static final int HIT = 4;
        public static final int WALL_CLIMB = 5;
        public static final int FALL = 6;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case IDLE:
                    return 11;
                case RUNNING:
                    return 12;
                case JUMP:
                    return 1;
                case DOUBLE_JUMP:
                    return 6;
                case HIT:
                    return 7;
                case WALL_CLIMB:
                    return 5;
                case FALL:
                    return 1;
                default:
                    return 1;
            }
        }
    }
}

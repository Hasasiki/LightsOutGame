package com.example.administrator.lightsoutgame;


import java.util.Random;

/**
 * Model class for a linear lights out game.  Clicking a button toggles the state
 * of that button and any neighboring buttons. The goal is to make all the buttons
 * match.
 */
public class LightsOutGame {
    public static final int MIN_NUM_BUTTONS = 3;
    private static final int RANDOMIZER_MULTIPLIER = 10;
    private int[] mButtonValues;
    private int mNumPresses;
    private boolean mDoingSetup;

    /**
     * Default constructor (defaults to 7 buttons)
     */
    public LightsOutGame() {
        this(7);
    }

    /**
     * Create a new linear lights out game with a certain number of buttons
     *
     * @param numButtons Number of buttons in this linear lights out game
     */
    public LightsOutGame(int numButtons) {
        if (numButtons < MIN_NUM_BUTTONS) {
            // CONSIDER: Throw an exception.  For now just enforce the minimum.
            numButtons = MIN_NUM_BUTTONS;
        }
        mDoingSetup = true;
        mButtonValues = new int[numButtons];
        randomizeButtons();
        mDoingSetup = false;
    }

    private void randomizeButtons() {
        // Attempt #1: Randomly select a value for each button.为每个按钮随机选择一个值。
        //  Might result in an unwinable game with some number of buttons 可能会导致一些不可点击的游戏
//		Random generator = new Random();
//		for (int i = 0 ; i < mButtonValues.length ; i++) {
//			mButtonValues[i] = generator.nextInt(2);
//		}
        // Attempt #2: Start with a win and randomly press buttons 从一个胜利开始，随机按下按钮
        Random generator = new Random();
        for (int i = 0; i < mButtonValues.length * RANDOMIZER_MULTIPLIER; i++) {
            pressedButtonAtIndex(generator.nextInt(mButtonValues.length));
        }
        // Make sure the game is not currently a win 确保游戏不是一场胜利
        while (checkForWin() && mButtonValues.length > 2) {
            pressedButtonAtIndex(generator.nextInt(mButtonValues.length));
        }
        mNumPresses = 0;
    }

    /**
     * When the user presses a button call this method to update the game
     * then read values to determine the updated game state.
     *当用户按下按钮调用该方法来更新游戏时，读取值以确定更新的游戏状态
     * @param buttonIndex The button that is pressed in the view 在视图中按下的按钮
     * @return Returns if this press resulted in all the buttons matching 如果此按下导致所有按钮匹配，则返回
     */
    public boolean pressedButtonAtIndex(int buttonIndex) {
        if (buttonIndex < 0 || buttonIndex >= mButtonValues.length)
            return false;
        if (!mDoingSetup && checkForWin())
            return true;
        mNumPresses++;
        toggleValueAtIndex(buttonIndex - 1);
        toggleValueAtIndex(buttonIndex);
        toggleValueAtIndex(buttonIndex + 1);
        return checkForWin();
    }

    /**
     * Convenience method that returns if the game is in a win state. 如果游戏处于赢家状态，则返回方便方法。
     * See also: Return value of pressedButtonAtIndex 参见：预扣索引的返回值
     *
     * @return true if all the button values match 如果所有按钮值匹配，则为
     */
    public boolean checkForWin() {
        int winnerColor = mButtonValues[0];
        for (int i = 1; i < mButtonValues.length; i++) {
            if (mButtonValues[i] != winnerColor)
                return false;
        }
        return true;
    }

    private void toggleValueAtIndex(int i) {
        if (i >= 0 && i < mButtonValues.length) {
            mButtonValues[i] ^= 1;
        }
    }

    /**
     * Sets the states of the buttons to the given values. This should only
     * be used to recreate a game in progress after a rotation. 将按钮的状态设置为给定的值。这只应用于在旋转后重新创建游戏。
     *
     * @param newValues
     */
    public void setAllValues(int[] newValues) {
        if (newValues.length != mButtonValues.length) {
            // Should give error
            return;
        }
        for (int i = 0; i < mButtonValues.length; i++) {
            mButtonValues[i] = newValues[i];
        }
    }


    /**
     * Get the state of a button at index
     *
     * @param buttonIndex The button of interest
     * @return A 0 or 1 value to indicate the button state (0 for OFF, 1 for ON)
     */
    public int getValueAtIndex(int buttonIndex) {
        return mButtonValues[buttonIndex];
    }

    /**
     * Get the number of button presses so far in this game
     *
     * @return The number of legal calls to pressedButtonAtIndex
     */
    public int getNumPresses() {
        return mNumPresses;
    }

    /**
     * Sets the number of button presses so far in this game.
     * This should only be used to recreate a game in progress after a rotation.
     */
    public void setNumPresses(int numPresses) {
        mNumPresses = numPresses;
    }


    @Override
    public String toString() {
        String ret = "";
        for (int aButtonValue : mButtonValues) {
            ret += aButtonValue;
        }
        return ret;
    }
}

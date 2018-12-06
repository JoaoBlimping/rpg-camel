package com.liquidpig.framework;

import java.util.List;

/**
 * Service to manage user input.
 */
public interface Input
{
  /**
   * Represents an event of the user touching the screen.
   */
  public static class TouchEvent
  {
    public static final int TOUCH_DOWN = 0;
    public static final int TOUCH_UP = 1;
    public static final int TOUCH_DRAGGED = 2;
    public static final int TOUCH_HOLD = 3;
    public int type;
    public Point point;
    public int pointer;

    /**
     * Generic constructor to create the point thingy.
     */
    public TouchEvent()
    {
      this.point = new Point();
    }
  }

  /**
   * Polls if a given pointer is currently touching the screen.
   * @param pointer is like a numbered point touching the screen like if there is one finger on the
   *                screen then that is number zero, and if there are two on the screen then the
   *                new one is pointer 1. I dunno, it's something like that.
   * @return true iff it is the case that the pointer is currently touching.
   */
  public boolean isTouchDown(int pointer);

  /**
   * If a given pointer is down then this gives you the position at which it is down.
   * @param pointer is the pointer number we are checking.
   * @return the point at which it is at.
   */
  public Point getTouchPoint(int pointer);

  /**
   * Gives you a list of all current touch events.
   * @return the list I just told you about just then.
   */
  public List<TouchEvent> getTouchEvents();
}

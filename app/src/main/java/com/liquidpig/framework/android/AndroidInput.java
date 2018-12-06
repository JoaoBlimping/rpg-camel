package com.liquidpig.framework.android;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.MotionEvent;
import android.view.View;

import com.liquidpig.framework.Input;
import com.liquidpig.framework.Point;
import com.liquidpig.framework.Pool;

/**
 * Android implementation of input.
 */
public class AndroidInput implements Input, View.OnTouchListener
{
  private static final int MAX_TOUCH_POINTS = 10;
  private boolean[] isTouched = new boolean[MAX_TOUCH_POINTS];
  private Point[] touchPoints = new Point[MAX_TOUCH_POINTS];
  private int[] touchIds = new int[MAX_TOUCH_POINTS];
  private Pool<TouchEvent> touchEventPool;
  private List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
  private List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
  private Point scale;

  /**
   * Creates the android input and sets it's scale and links it as a listener.
   * @param view is the view from which it listens for touch events.
   * @param scale is the scale.
   */
  public AndroidInput(View view, Point scale)
  {
    Pool.PoolObjectFactory<TouchEvent> factory = new Pool.PoolObjectFactory<TouchEvent>()
    {
      @Override
      public TouchEvent createObject()
      {
        System.out.println("Creating the thingo NOW");
        return new TouchEvent();
      }
    };
    touchEventPool = new Pool<TouchEvent>(factory, 100);
    view.setOnTouchListener(this);
    for (int i = 0; i < MAX_TOUCH_POINTS; i++) touchPoints[i] = new Point();
    this.scale = scale;
  }

  @Override
  public boolean onTouch(View v, MotionEvent event)
  {
    synchronized (this)
    {
      int action = event.getAction() & MotionEvent.ACTION_MASK;
      int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
      int pointerCount = event.getPointerCount();
      TouchEvent touchEvent;
      for (int i = 0; i < MAX_TOUCH_POINTS; i++)
      {
        if (i >= pointerCount)
        {
          isTouched[i] = false;
          touchIds[i] = -1;
          continue;
        }
        int pointerId = event.getPointerId(i);
        if (event.getAction() != MotionEvent.ACTION_MOVE && i != pointerIndex) continue;
        switch (action)
        {
          case MotionEvent.ACTION_DOWN:
          case MotionEvent.ACTION_POINTER_DOWN:
            touchEvent = touchEventPool.newObject();
            touchEvent.type = TouchEvent.TOUCH_DOWN;
            touchEvent.pointer = pointerId;
            touchEvent.point.x = touchPoints[i].x = event.getX(i) * scale.x;
            touchEvent.point.y = touchPoints[i].y = event.getY(i) * scale.y;
            isTouched[i] = true;
            touchIds[i] = pointerId;
            touchEventsBuffer.add(touchEvent);
            break;
          case MotionEvent.ACTION_UP:
          case MotionEvent.ACTION_POINTER_UP:
          case MotionEvent.ACTION_CANCEL:
            touchEvent = touchEventPool.newObject();
            touchEvent.type = TouchEvent.TOUCH_UP;
            touchEvent.pointer = pointerId;
            touchEvent.point.x = touchPoints[i].x = event.getX(i) * scale.x;
            touchEvent.point.y = touchPoints[i].y = event.getY(i) * scale.y;
            isTouched[i] = false;
            touchIds[i] = -1;
            touchEventsBuffer.add(touchEvent);
            break;
          case MotionEvent.ACTION_MOVE:
            touchEvent = touchEventPool.newObject();
            touchEvent.type = TouchEvent.TOUCH_DRAGGED;
            touchEvent.pointer = pointerId;
            touchEvent.point.x = touchPoints[i].x = event.getX(i) * scale.x;
            touchEvent.point.y = touchPoints[i].y = event.getY(i) * scale.y;
            isTouched[i] = true;
            touchIds[i] = pointerId;
            touchEventsBuffer.add(touchEvent);
            break;
        }
      }
      return true;
    }
  }

  @Override
  public boolean isTouchDown(int pointer)
  {
    synchronized (this)
    {
      int index = getIndex(pointer);
      if (index < 0 || index >= MAX_TOUCH_POINTS) return false;
      else return isTouched[index];
    }
  }

  @Override
  public Point getTouchPoint(int pointer)
  {
    synchronized (this)
    {
      int index = getIndex(pointer);
      if (index < 0 || index >= MAX_TOUCH_POINTS) return new Point();
      else return touchPoints[index];
    }
  }

  @Override
  public List<TouchEvent> getTouchEvents()
  {
    int len = touchEvents.size();
    for (int i = 0; i < len; i++) touchEventPool.free(touchEvents.get(i));
    touchEvents.clear();
    touchEvents.addAll(touchEventsBuffer);
    touchEventsBuffer.clear();
    return touchEvents;
  }

  /**
   * Finds the index of a given pointer in the touch point lists.
   * @param pointerId is the id of the pointer you want to find.
   * @return the index at which the given pointer arrives in the lists or -1 if it's not there.
   */
  private int getIndex(int pointerId)
  {
    for (int i = 0; i < MAX_TOUCH_POINTS; i++)
    {
      if (touchIds[i] == pointerId) return i;
    }
    return -1;
  }
}

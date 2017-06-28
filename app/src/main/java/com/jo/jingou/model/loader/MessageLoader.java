package com.jo.jingou.model.loader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dfyu on 2017/5/3.
 */
public class MessageLoader {


    private static final List<OnMessageChangeListener> onMessageChangeListeners = new ArrayList<>();
    private static final int[] messageNumberList = new int[]{0, 0, 0, 0};


    public static void addListener(OnMessageChangeListener onMessageChangeListener) {
        if (onMessageChangeListener != null && onMessageChangeListeners.indexOf(onMessageChangeListener) < 0)
            onMessageChangeListeners.add(onMessageChangeListener);

        int messageNumberAll = getMessageNumberAll();
        onMessageChangeListener.onMessageChanged(messageNumberAll != 0 ? true : false, messageNumberAll);
    }

    public static void removeListener(OnMessageChangeListener onMessageChangeListener) {
        if (onMessageChangeListener != null && onMessageChangeListeners.indexOf(onMessageChangeListener) >= 0)
            onMessageChangeListeners.remove(onMessageChangeListener);
    }

    public static void clearListener() {
        onMessageChangeListeners.clear();
    }

    public static void setMessage(int... messageNumber) {
        for (int i = 0; i < messageNumber.length; i++) setmessage(i, messageNumber[i]);
        int messageNumberAll = getMessageNumberAll();
        callMessageChange(messageNumberAll != 0 ? true : false, messageNumberAll);
    }

    public static void setMessage(int index, int number) {
        setmessage(index, number);
        int messageNumberAll = getMessageNumberAll();
        callMessageChange(messageNumberAll != 0 ? true : false, messageNumberAll);
    }


    private static void setmessage(int index, int number) {
        if (index >= 0 && index < messageNumberList.length) {
            messageNumberList[index] = number;
        }
    }


    public static int getMessageNumberAll() {
        int messageNumber = 0;
        for (int number : messageNumberList) {
            messageNumber += number;
        }
        return messageNumber;
    }

    public static int getMessageNumber(int index) {
        if (index >= 0 && index < messageNumberList.length) return messageNumberList[index];
        else return 0;
    }


    private static void callMessageChange(boolean hasNewMessage, int newMessageNumber) {

        List<OnMessageChangeListener> nullListeners = new ArrayList<>();
        nullListeners.add(null);
        onMessageChangeListeners.removeAll(nullListeners);

        for (OnMessageChangeListener onMessageChangeListener : onMessageChangeListeners) {
            if (onMessageChangeListener != null) {
                onMessageChangeListener.onMessageChanged(hasNewMessage, newMessageNumber);
            }
        }
    }


    public interface OnMessageChangeListener {
        void onMessageChanged(boolean hasNewMessage, int newMessageNumber);
    }
}

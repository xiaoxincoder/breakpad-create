package com.keepshare.breakpadcreate;

public class BreakPadManager {

    static {
        System.loadLibrary("break-pad");
    }

    public native void init(String filePath);
}

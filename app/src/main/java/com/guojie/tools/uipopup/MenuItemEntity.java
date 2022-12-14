package com.guojie.tools.uipopup;

import android.graphics.Color;
import android.util.TypedValue;

/**
 *
 *
 */
public class MenuItemEntity {
    public int icon = -1;
    public String text;
    public boolean clickable = true;

    public int textColor = Color.BLACK;
    public float textSize = 16;
    public int textSizeUnit = TypedValue.COMPLEX_UNIT_SP;

    public MenuItemEntity(int icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public MenuItemEntity(int icon, String text, Object color) {
        this.icon = icon;
        this.text = text;
        try {
            if (color instanceof Integer) {
                this.textColor = (int) color;
            } else if (color instanceof String) {
                this.textColor = Color.parseColor((String) color);
            } else {
                this.textColor = Color.BLACK;
            }
        } catch (Exception e) {
            this.textColor = Color.BLACK;
        }
    }


}

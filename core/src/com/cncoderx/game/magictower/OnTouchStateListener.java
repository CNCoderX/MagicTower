package com.cncoderx.game.magictower;

import com.cncoderx.game.magictower.widget.DirectionButton;

/**
 * Created by ll on 2017/5/21.
 */
public interface OnTouchStateListener extends DirectionButton.DirectionButtonListener {
    boolean onBackPressed();
}

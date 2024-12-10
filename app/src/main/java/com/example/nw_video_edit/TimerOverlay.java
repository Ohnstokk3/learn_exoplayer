package com.example.nw_video_edit;


import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import androidx.annotation.OptIn;
import androidx.media3.common.C;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.effect.OverlaySettings;
import androidx.media3.effect.TextOverlay;
import androidx.media3.effect.TextureOverlay;
import java.util.Locale;

/**
 * A {@link TextureOverlay} that displays a "time elapsed" timer in the bottom left corner of the
 * frame.
 */
/* package */ @UnstableApi
final class TimerOverlay extends TextOverlay {

    private final OverlaySettings overlaySettings;

    public TimerOverlay() {
        overlaySettings =
                new OverlaySettings.Builder()
                        // Place the timer in the bottom left corner of the screen with some padding from the
                        // edges.
                        .setOverlayFrameAnchor(/* x= */ -1f, /* y= */ -1f)
                        .setBackgroundFrameAnchor(/* x= */ -0.7f, /* y= */ -0.95f)
                        .build();
    }

    @Override
    public SpannableString getText(long presentationTimeUs) {
        SpannableString text =
                new SpannableString(
                        String.format(Locale.US, "%.02f", presentationTimeUs / (float) C.MICROS_PER_SECOND));
        text.setSpan(
                new ForegroundColorSpan(Color.WHITE),
                /* start= */ 0,
                text.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return text;
    }

    @OptIn(markerClass = UnstableApi.class)
    @Override
    public OverlaySettings getOverlaySettings(long presentationTimeUs) {
        return overlaySettings;
    }
}

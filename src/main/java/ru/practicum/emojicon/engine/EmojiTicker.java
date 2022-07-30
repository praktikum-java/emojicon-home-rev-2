package ru.practicum.emojicon.engine;

import com.googlecode.lanterna.TextColor;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class EmojiTicker implements Drawable {

    private static final List<Character> cycle = Arrays.asList('\u2801', '\u2809', '\u2819', '\u281B', '\u281E', '\u2856', '\u28C6', '\u28E4',
            '\u28E0', '\u28A0', '\u2820', '\u2804', '\u2844', '\u28C4', '\u28E4', '\u28F0', '\u28B2', '\u2833', '\u281B', '\u280B',
            '\u2809', '\u2808');

    private Instant timestamp = Instant.now();

    private int state = 0;

    @Override
    public void drawFrame(Frame frame) {
        Character stateChar = cycle.get(state);
        frame.setPosition(frame.getRight(), frame.getTop());
        frame.fill(TextColor.ANSI.BLACK);
        frame.color(TextColor.ANSI.WHITE);
        frame.draw(stateChar);
        long dt = Instant.now().toEpochMilli() - timestamp.toEpochMilli();
        if(dt > 100){
            state = (state + 1) == cycle.size() ? 0 : state + 1;
            timestamp = Instant.now();
        }
    }
}

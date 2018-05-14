package com.sethphat.gigapet.Configs;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

import java.io.IOException;

public class MusicService {
    public static MediaPlayer media = new MediaPlayer();
    public static int current_music = 0;

    public static void TurnMusic()
    {
        if (Setting.isIsSoundOn())
        {
            media.start();
        }
        else
        {
            media.pause();
        }
    }

    public static void PlaySong(Context ct, int type)
    {
        if (current_music == type)
            return;

        media.pause();

        AssetFileDescriptor source_music;
        switch (type)
        {
            case 1: source_music = Setting.GetMusic(ct, "katharsis.mp3"); break;
            case 2: source_music = Setting.GetMusic(ct, "venus.mp3"); break;
            case 3: source_music = Setting.GetMusic(ct, "sakura.mp3"); break;
            case 4: source_music = Setting.GetMusic(ct, "voltage.mp3"); break;
            default: source_music = Setting.GetMusic(ct, "sakura.mp3"); break;
        }

        current_music = type;

        // get source
        try {
            media = new MediaPlayer();
            media.setDataSource(source_music.getFileDescriptor(), source_music.getStartOffset(), source_music.getLength());
            media.setVolume(0.6f, 0.6f);
            media.prepare();

            if (Setting.isIsSoundOn())
                media.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

        // set replayable
        media.setLooping(true);
    }

}

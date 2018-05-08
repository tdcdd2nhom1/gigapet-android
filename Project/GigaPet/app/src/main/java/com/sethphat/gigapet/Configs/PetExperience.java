package com.sethphat.gigapet.Configs;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class PetExperience {
    private static int[] EXP = new int[] {
            15, // Level 1
            35,
            75,
            150,
            350,
            550,
            850,
            1250,
            1850,
            2500 // Level 10 - final
    };

    /**
     * Check if level up pet
     * @param nowLevel
     * @param nowExp
     * @return
     */
    public static boolean isUpLevel(int nowLevel, int nowExp)
    {
        if (nowLevel == 10)
            return false;

        for (int i = 0; i < EXP.length; i++)
        {
            if (nowLevel == i && nowExp >= EXP[i])
            {
                return true;
            }
        }

        return false;
    }

    public static boolean isDownLevel(int nowLevel, int nowExp)
    {
        if (nowLevel == 1)
            return false;

        for (int i = EXP.length - 1; i >= 0; i--)
        {
            int level = i + 1;
            if (nowLevel == level && nowExp <= EXP[i - 1])
            {
                return true;
            }
        }

        return false;
    }

    public static int getExp(int nowLevel)
    {
        return EXP[nowLevel];
    }
}

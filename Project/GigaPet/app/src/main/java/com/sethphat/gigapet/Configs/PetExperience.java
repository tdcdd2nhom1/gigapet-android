package com.sethphat.gigapet.Configs;

/**
 * Created by Seth Phat on 3/19/2018.
 */

public class PetExperience {
    private static int[] EXP = new int[] {
            100, // Level 1
            200,
            500,
            1000,
            2000,
            4000,
            8000,
            16000,
            32000,
            64000 // Level 10
    };

    /**
     * Check if level up pet
     * @param nowLevel
     * @param nowExp
     * @return
     */
    public static boolean isUpLevel(int nowLevel, int nowExp)
    {
        for (int i = 0; i < EXP.length; i++)
        {
            int level = i + 1;
            if (nowLevel == level && nowExp >= EXP[i])
            {
                return true;
            }
        }

        return false;
    }
}

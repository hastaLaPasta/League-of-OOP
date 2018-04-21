package main;

public class Deflect {
    private float percentage;
    private float percentageLVL;
    static final float DEFLECT_MAX_CONST = 0.7f;
    static final float DEFLECT_CONST = 0.35f;
    static final float DEFLECT_LVL = 0.02f;
    static final float[] MODIFIERS = {0.2f, 0.4f, 0.3f, 0.0f};

    /**
     * Getter pentru variabila percentage.
     * @return
     */
    public float getPercentage() {
        return percentage;
    }

    public Deflect() {
        percentage = DEFLECT_CONST;
        percentageLVL = DEFLECT_LVL;
    }

    /**
     * Functie de level up a abilitatii.
     */
    public void levelUp() {
        if (percentage >= DEFLECT_MAX_CONST) {
            return;
        }
        percentage += percentageLVL;
    }
}

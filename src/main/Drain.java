package main;

public class Drain {
    private float percentage;
    private float percentageLVL;
    static final float DRAIN_CONST = 0.2f;
    static final float CONST = 0.3f;
    static final float DRAIN_LVLUP = 0.05f;
    static final float[] MODIFIERS = {-0.2f, 0.2f, -0.1f, 0.05f};
    static final float LAND_MODIFIER = 1.1f;
    public Drain() {
        percentage = DRAIN_CONST;
        percentageLVL = DRAIN_LVLUP;
    }

    /**
     * Calculeaza damage-ul pe care abilitatea
     * il da unui erou.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul primit.
     */
    public float damage(final Hero hero) {
        int i;
        for (i = 0; i < MODIFIERS.length; i++) {
            if (hero.getType() == Ability.VICTIMS[i]) {
                break;
            }
        }
        float damage = (1 + MODIFIERS[i]) * DRAIN_CONST * Math.min(CONST * hero.getMaxHP(),
                hero.getHP());
        if (hero.getLandtype().equals("D")) {
            return damage * LAND_MODIFIER;
        }
        return damage;
    }

    /**
     * Functie de upgrade a abilitatii(level up).
     */
    public void levelUp() {
        percentage += percentageLVL;
    }
}

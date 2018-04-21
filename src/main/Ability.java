package main;

public class Ability {
    private int baseDamage;
    private int baseDamageLvlUp;
    private float[] modifiers;
    static final String[] VICTIMS = {"R", "K", "P", "W"};

    /**
     * Getter pentru damage-ul abilitatilor.
     * @return
     */
    public int getBaseDamage() {
        return baseDamage;
    }

    public Ability(final int baseDamage, final int baseDamageLvlUp,
                   final float[] modifiers) {
        this.baseDamage = baseDamage;
        this.baseDamageLvlUp = baseDamageLvlUp;
        this.modifiers = modifiers;
    }

    /**
     * Functie pentru a upgrada abilitatile cand
     * eroul face level up.
     * @param hero
     */
    public void levelUp(final Hero hero) {
        baseDamage += baseDamageLvlUp * hero.getLevel();
    }

    /**
     *
     * @param hero
     * @return
     */
    public int getModifierIndex(final Hero hero) {
        int i;
        for (i = 0; i < Ability.VICTIMS.length; i++) {
            if (Ability.VICTIMS[i] == hero.getType()) {
                break;
            }
        }
        return i;
    }
}

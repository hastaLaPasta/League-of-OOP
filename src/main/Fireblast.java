package main;

public class Fireblast extends Ability {
    static final int DAMAGE = 350;
    static final int DAMAGE_LVL = 50;
    static final float[] MODIFIERS = {-0.2f, 0.2f, -0.1f, 0.05f};
    static final float VOLCANIC_BONUS = 1.25f;
    public Fireblast() {
        super(DAMAGE, DAMAGE_LVL, MODIFIERS);
    }

    /**
     * Calculeaza damage-ul dat de
     * Fireblast unui erou.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul primit.
     */
    public float damage(final Hero hero) {
        int i = getModifierIndex(hero);
        float damage = getBaseDamage() + getBaseDamage() * MODIFIERS[i];
        if (!hero.getLandtype().equals("V")) {
            return damage;
        }
        return damage * VOLCANIC_BONUS;
    }
}

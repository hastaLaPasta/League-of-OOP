package main;

public class Slam extends Ability {
    static final int BASE_DAMAGE = 100;
    static final int BASE_LVLUP = 40;
    static final int STUN_TIME = 1;
    static final float[] MODIFIERS = {-0.2f, 0.2f, -0.1f, 0.05f};
    public Slam() {
        super(BASE_DAMAGE, BASE_LVLUP, MODIFIERS);
    }
    /**
     * Calculeaza damage-ul dat unui erou.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul primit.
     */
    public float damage(final Hero hero) {
        hero.setStun(true);
        hero.setRoundsStun(STUN_TIME);
        int i = getModifierIndex(hero);
        float damage = getBaseDamage() + getBaseDamage()
                * MODIFIERS[i];
        if (hero.getLandtype().equals("L")) {
            return damage * Execute.LAND_MODIFIER;
        }
        return damage;
    }
}

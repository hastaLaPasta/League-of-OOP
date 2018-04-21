package main;

public class Execute extends Ability {
    private float hpModifierLvlUp;
    private float hpModifier;
    static final int BASE_DAMAGE = 200;
    static final int BASE_LVLUP = 30;
    static final float[] MODIFIERS = {0.15f, 0.0f, 0.1f, -0.2f};
    static final float HPMOD_CONST = 0.2f;
    static final float HPMOD_CONST_LVLUP = 0.01f;
    static final float MAX_HP_MODIFIER = 0.4f;
    static final float LAND_MODIFIER = 1.15f;
    public Execute() {
        super(BASE_DAMAGE, BASE_LVLUP, MODIFIERS);
        hpModifier = HPMOD_CONST;
        hpModifierLvlUp = HPMOD_CONST_LVLUP;
    }
    /**
     * Calculeaza damage-ul dat unui erou.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul dat.
     */
    public float damage(final Hero hero) {
        if (hero.getHP() < hero.getMaxHP() * hpModifier) {
            return hero.getHP();
        }
        int i = getModifierIndex(hero);
        float damage = getBaseDamage() + getBaseDamage() * MODIFIERS[i];
        if (hero.getLandtype().equals("L")) {
            return damage * LAND_MODIFIER;
        }
        return damage;
    }
    /**
     * Creste level-ul abilitatii si toate
     * statusurile ei.
     * @param hero Eroul ce face level up
     */
    @Override
    public void levelUp(final Hero hero) {
        super.levelUp(hero);
        if (hpModifier == MAX_HP_MODIFIER) {
            return;
        }
        hpModifier += hpModifierLvlUp;
    }
}

package main;

public class Paralysis extends Ability {
    static final int MIN_ROUNDS = 3;
    static final int MAX_ROUNDS = 6;
    static final int BASE_DMG = 40;
    static final int BASE_DMG_LVLUP = 10;
    static final float[] MODIFIERS = {-0.1f, -0.2f, 0.2f, 0.25f};
    public Paralysis() {
        super(BASE_DMG, BASE_DMG_LVLUP, MODIFIERS);
    }
    /**
     * Calculeaza damage-ul dat unui erou.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul dat.
     */
    public float damage(final Hero hero) {
        int i = getModifierIndex(hero);
        if (hero.getLandtype().equals("W")) {
            return Math.round(Math.round(getBaseDamage() + getBaseDamage() * MODIFIERS[i])
                    * Rogue.ROGUE_MODIFIER);
        }
        return Math.round(getBaseDamage() + getBaseDamage() * MODIFIERS[i]);
    }

    /**
     * Calculeaza damage-ul overtime pe care un erou
     * trebuie sa il primeasca in fiecare runda si
     * seteaza numarul de runde in care acesta primeste
     * damage.
     * @param hero Eroul ce primeste overtime damage.
     */
    public void overtimeDamage(final Hero hero) {
        int i;
        for (i = 0; i < Ability.VICTIMS.length; i++) {
            if (hero.getType() == Ability.VICTIMS[i]) {
                break;
            }
        }
        float damage = getBaseDamage() + getBaseDamage() * MODIFIERS[i];
        if (hero.getLandtype().equals("W")) {
            hero.setOvertimeDamageValue(Math.round(damage * Rogue.ROGUE_MODIFIER));
            hero.setOvertimeDamage(true);
            hero.setDebuff(MAX_ROUNDS);
            return;
        }
        hero.setOvertimeDamageValue(Math.round(damage));
        hero.setOvertimeDamage(true);
        hero.setDebuff(MIN_ROUNDS);
    }
}

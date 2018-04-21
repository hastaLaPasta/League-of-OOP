package main;

public class Backstab extends Ability {
    private boolean multiplier = false;
    private int cicle = 0;
    private boolean wizzardCritical = false;
    static final int MAX_CICLE = 3;
    static final float DEBUFF = 1.5f;
    static final int BASE_DMG = 200;
    static final int BASE_DMG_LVLUP = 20;
    static final float[] MODIFIERS = {0.2f, -0.1f, 0.25f, 0.25f};
    static final int CICLE_RESET = 0;
    public Backstab() {
        super(BASE_DMG, BASE_DMG_LVLUP, MODIFIERS);
    }

    /**
     * Variabila multiplier este folosita pentru a verifica
     * daca Critica a fost data asupra unui erou.
     * @return Getter pentru variabila multiplie.
     */
    public boolean isMultiplier() {
        return multiplier;
    }

    /**
     * Variabila wizzardCritical este folosita pentru a
     * verifica daca Criticala a fost data asupra Wizard-ului
     * si pentru a pasa damage-ul corect pentru Deflect.
     * @return Getter pentru variabila wizzardCritical
     */
    public boolean isWizzardCritical() {
        return wizzardCritical;
    }

    /**
     * Setter pentru variabila wizzardCritical.
     * @param wizzardCritical
     */
    public void setWizzardCritical(final boolean wizzardCritical) {
        this.wizzardCritical = wizzardCritical;
    }

    /**
     * Calculeaz damage-ul dat unui erou cu toate
     * modificatoare, inclusiv Criticala data de
     * Backstab.
     * @param hero Eroul ce o sa primeasca damage.
     * @return Damage-ul.
     */
    public float damage(final Hero hero) {
        if (cicle > MAX_CICLE) {
            cicle = CICLE_RESET;
            wizzardCritical = false;
            multiplier = false;
        }
        int i = getModifierIndex(hero);
        if (cicle < MAX_CICLE && !multiplier && hero.getLandtype().equals("W")) {
            multiplier = true;
            cicle++;
            return Math.round(Math.round(Math.round(getBaseDamage()
                    + getBaseDamage() * MODIFIERS[i])
                    * DEBUFF) * Rogue.ROGUE_MODIFIER);
        } else if (cicle < MAX_CICLE && !multiplier && !hero.getLandtype().equals("W")) {
            cicle++;
            return Math.round(getBaseDamage() + getBaseDamage() * MODIFIERS[i]);
        } else if (multiplier && hero.getLandtype().equals("W")) {
            cicle++;
            return Math.round(Math.round(getBaseDamage() + getBaseDamage() * MODIFIERS[i])
                   * Rogue.ROGUE_MODIFIER);
        }
        cicle++;
        return Math.round(getBaseDamage() + getBaseDamage() * MODIFIERS[i]);
    }
}

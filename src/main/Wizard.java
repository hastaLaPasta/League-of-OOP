package main;

public class Wizard extends Hero {
    private Drain drain = new Drain();
    private Deflect deflect = new Deflect();
    static final float HP = 400f;
    static final float HP_LVLUP = 30f;
    static final int WIZARD_MODIFIER_POS = 4;
    public Wizard(final int line, final int col) {
        super("W", line, col);
        setHP(HP);
        setMaxHP(HP);
    }

    /**
     * Functie de level up.
     */
    @Override
    public void levelUp() {
        while (getXP() >= LEVEL_CONST + getLevel() * LEVEL_MULTIPLIER
                && !isDead()) {
            setLevel(getLevel() + Hero.INCREASE_LVL);
            setHP(HP + getLevel() * HP_LVLUP);
            setMaxHP(getHP());
            drain.levelUp();
            deflect.levelUp();
        }
    }

    /**
     * Calculeaza damage-ul dat de deflect.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul primit.
     */
    public float deflectDamage(final Hero hero) {
        if (hero.getType().equals("W")) {
            return 0.0f;
        }
        int i;
        for (i = 0; i < Ability.VICTIMS.length; i++) {
            if (Ability.VICTIMS[i] == hero.getType()) {
                break;
            }
        }
        float percentage = deflect.getPercentage()
                + deflect.getPercentage() * Deflect.MODIFIERS[i];
        if (hero.getLandtype().equals("D")) {
            return Math.round(hero.damageNoModifiers(this) * Drain.LAND_MODIFIER
                   * percentage);
        }
        return Math.round(hero.damageNoModifiers(this) * percentage);
    }

    /**
     * Calculeaza damage-ul total pe care
     * Wizzard-ul il da.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul total.
     */
    @Override
    public float calcDamage(final Hero hero) {
        return Math.round(deflectDamage(hero) + drain.damage(hero));
    }

    private void attack(final Hero hero) {
        if (!hero.isDead()) {
            hero.getDamage(calcDamage(hero));
        }
    }

    /**
     * Functie ce initializeaza lupta.
     * @param hero
     */
    @Override
    public void checkBattle(final Hero hero) {
        if ((getLine() == hero.getLine()) && (getCol() == hero.getCol()) && !hero.isDead()) {
            attack(hero);
            if (hero.isDead()) {
                hero.setDead(true);
                gatherXP(hero);
            }
        }
    }
}

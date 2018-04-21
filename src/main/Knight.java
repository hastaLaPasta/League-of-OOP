package main;

public class Knight extends Hero {
    private Execute execute = new Execute();
    private Slam slam = new Slam();
    static final float HP = 900f;
    static final float HP_LVL = 80;
    public Knight(final int line, final int col) {
        super("K", line, col);
        setHP(HP);
        setMaxHP(HP);
    }

    /**
     * Calculeaza damage-ul dat unui erou.
     * @param hero Eroul ce ia damage.
     * @return Damage-ul primit.
     */
    @Override
    public float calcDamage(final Hero hero) {
        return Math.round(execute.damage(hero) + slam.damage(hero));
    }

    /**
     * Functie de level-up.
     */
    @Override
    public void levelUp() {
        while (getXP() >= LEVEL_CONST + getLevel() * LEVEL_MULTIPLIER
                && !isDead()) {
            setLevel(getLevel() + Hero.INCREASE_LVL);
            setHP(HP + getLevel() * HP_LVL);
            setMaxHP(HP + HP_LVL * getLevel());
            execute.levelUp(this);
            slam.levelUp(this);
        }
    }
    private void attack(final Hero hero) {
        if (!hero.isDead()) {
            hero.getDamage(calcDamage(hero));
            hero.setStun(true);
            hero.setOvertimeDamageValue(0);
        }
    }

    /**
     * Metoda ce initializeaza lupta.
     * @param hero Eroul cu care se lupta Knight-ul.
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
    /**
     * Calculeaza damage-ul pe care un Rogue i l-ar da unui
     * erou fara Race Modifiers. Folosita pentru a calcula
     * damage-ul abilitatii Deflect al unui Wizard.
     * @param hero
     * @return
     */
    @Override
    public float damageNoModifiers(final Hero hero) {
        if (hero.getLandtype().equals("L")) {
            return Math.round(execute.getBaseDamage() * Execute.LAND_MODIFIER)
                    + Math.round(slam.getBaseDamage() * Execute.LAND_MODIFIER);
        }
        return execute.getBaseDamage() + slam.getBaseDamage();
    }
}

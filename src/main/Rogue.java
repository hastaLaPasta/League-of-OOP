package main;

public class Rogue extends Hero {
    private Paralysis paralysis = new Paralysis();
    private Backstab backstab = new Backstab();
    static final float HP = 600.0f;
    static final float HP_LVLUP = 40.0f;
    static final float ROGUE_MODIFIER = 1.15f;
    public Rogue(final int line, final int col) {
        super("R", line, col);
        setHP(HP);
        setMaxHP(HP);
    }

    /**
     * Functie pentru a face level up eroului.
     */
    @Override
    public void levelUp() {
        while (getXP() >= LEVEL_CONST + getLevel() * LEVEL_MULTIPLIER
                && !isDead()) {
            setLevel(getLevel() + Hero.INCREASE_LVL);
            setHP(getMaxHP() + HP_LVLUP);
            setMaxHP(getMaxHP() + HP_LVLUP);
            paralysis.levelUp(this);
            backstab.levelUp(this);
        }
    }

    /**
     * Calculeaza damage-ul dat unui erou.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul ce il primeste eroul primit ca parametru.
     */
    @Override
    public float calcDamage(final Hero hero) {
        return Math.round(paralysis.damage(hero) + backstab.damage(hero));
    }

    private void attack(final Hero hero) {
        if (!hero.isDead()) {
            hero.getDamage(calcDamage(hero));
            paralysis.overtimeDamage(hero);
            hero.setStun(false);
        }
        if (hero.getLandtype().equals("W")) {
            hero.setRoundsStun(Paralysis.MAX_ROUNDS);
            hero.setStun(true);
        } else if (!hero.getLandtype().equals("W")) {
            hero.setStun(true);
            hero.setRoundsStun(Paralysis.MIN_ROUNDS);
        }
    }
    /**
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

    /**
     * Calculeaza damage-ul pe care un Rogue i l-ar da unui
     * erou fara Race Modifiers. Folosita pentru a calcula
     * damage-ul abilitatii Deflect al unui Wizard.
     * @param hero
     * @return
     */
    @Override
    public float damageNoModifiers(final Hero hero) {
        if (hero.getLandtype().equals("W") && !backstab.isWizzardCritical()) {
            backstab.setWizzardCritical(true);
            return (paralysis.getBaseDamage() + backstab.getBaseDamage()
                    * Backstab.DEBUFF) * ROGUE_MODIFIER;
        } else if (hero.getLandtype().equals("W") && backstab.isWizzardCritical()) {
            return (paralysis.getBaseDamage() + backstab.getBaseDamage()) * ROGUE_MODIFIER;
        }
        return paralysis.getBaseDamage() + backstab.getBaseDamage();
    }
}

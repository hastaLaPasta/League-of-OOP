package main;

public class Pyromancer extends Hero {
    private Fireblast fireblast = new Fireblast();
    private Ignite ignite = new Ignite();
    static final float HP = 500f;
    static final float HP_LEVELUP = 50f;
    public Pyromancer(final int line, final int col) {
        super("P", line, col);
        setHP(HP);
        setMaxHP(HP);
    }

    /**
     * Metoda de level up.
     */
    @Override
    public void levelUp() {
        while (getXP() >= Hero.LEVEL_CONST + getLevel() * Hero.LEVEL_MULTIPLIER
                && !isDead()) {
            setLevel(getLevel() + Hero.INCREASE_LVL);
            setHP(HP + HP_LEVELUP * getLevel());
            setMaxHP(HP + HP_LEVELUP * getLevel());
            fireblast.levelUp(this);
            ignite.levelUp(this);
        }
    }

    /**
     * Calculeaza damage-ul dat unui erou.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul primit.
     */
    @Override
    public float calcDamage(final Hero hero) {
        return Math.round(fireblast.damage(hero) + ignite.damage(hero));
    }
    private void attack(final Hero hero) {
        if (!hero.isDead()) {
            hero.getDamage(calcDamage(hero));
            ignite.overtimeDamage(hero);
            ignite.setIgnite(hero);
        }
    }

    /**
     * Metoda ce initializeaza batalia.
     * @param hero Eroul cu care Pyromancerul se lupta.
     */
    public void checkBattle(final Hero hero) {
        if ((getLine() == hero.getLine()) && (getCol() == hero.getCol()) && !hero.isDead()) {
            attack(hero);
            if (hero.isDead()) {
                gatherXP(hero);
                hero.setDead(true);
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
        if (hero.getLandtype().equals("V")) {
            return Math.round(fireblast.getBaseDamage() * Fireblast.VOLCANIC_BONUS)
                   + Math.round(ignite.getBaseDamage() * Fireblast.VOLCANIC_BONUS);
        }
        return Math.round(fireblast.getBaseDamage()) + Math.round(ignite.getBaseDamage());
    }
}

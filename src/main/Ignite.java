package main;

public class Ignite extends Ability {
    private int damageRound;
    private int damageRoundLvl;
    static final int DAMAGE_ROUND = 50;
    static final int DAMAGE_ROUND_LVLUP = 30;
    static final int ROUNDS = 2;
    static final int DAMAGE = 150;
    static final int DAMAGE_LVL = 20;
    public Ignite() {
        super(DAMAGE, DAMAGE_LVL, Fireblast.MODIFIERS);
        damageRound = DAMAGE_ROUND;
        damageRoundLvl = DAMAGE_ROUND_LVLUP;
    }

    /**
     * Metoda de level up.
     * @param hero Eroul ce face level up.
     */
    @Override
    public void levelUp(final Hero hero) {
        super.levelUp(hero);
        damageRound += damageRoundLvl * hero.getLevel();
    }
    /**
     * Calculeaza damage-ul dat unui erou.
     * @param hero Eroul ce primeste damage.
     * @return Damage-ul dat.
     */
    public float damage(final Hero hero) {
        int i;
        for (i = 0; i < Ability.VICTIMS.length; i++) {
            if (hero.getType() == Ability.VICTIMS[i]) {
                break;
            }
        }
        float damage = getBaseDamage() + getBaseDamage()
               * Fireblast.MODIFIERS[i];
        if (!hero.getLandtype().equals("V")) {
            return damage;
        }
        return damage * Fireblast.VOLCANIC_BONUS;
    }
    /**
     * Calculeaza damage-ul overtime pe care un erou
     * trebuie sa il primeasca in fiecare runda si
     * seteaza numarul de runde in care acesta primeste
     * damage.
     * @param hero Eroul ce primeste overtime damage.
     */
    public void overtimeDamage(final Hero hero) {
        int i = getModifierIndex(hero);
        float damage = damageRound + damageRound * Fireblast.MODIFIERS[i];
        if (!hero.getLandtype().equals("V")) {
            hero.setOvertimeDamageValue(damage);
            return;
        }
            hero.setOvertimeDamageValue(damageRound * Fireblast.VOLCANIC_BONUS);
    }

    /**
     * Seteaza numarul de runde(debuff-ul) in care un erou
     * primeste overtime damage si variabila booleana
     * overtimeDamage la true.
     * @param hero Eroul caruia i se atribuie overtime damage.
     */
    public void setIgnite(final Hero hero) {
        hero.setDebuff(ROUNDS);
        hero.setOvertimeDamage(true);
    }
}

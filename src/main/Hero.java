package main;

public class Hero {
    private float maxHP;
    private float hp;
    private String type;
    private int line;
    private int col;
    private String landtype;
    private int roundsStun;
    private int xp = 0;
    private int level = 0;
    private boolean overtimeDamage = false;
    private float overtimeDamageValue;
    private boolean stun;
    private boolean dead = false;
    private int debuff = 0;
    static final int XP_CONSTANT = 200;
    static final int XP_MULTIPLIER = 40;
    static final int LEVEL_CONST = 250;
    static final int LEVEL_MULTIPLIER = 50;
    static final int INCREASE_LVL = 1;
    public Hero(final String type, final int line, final int col) {
        this.type = type;
        this.line = line;
        this.col = col;
    }

    /**
     * Getter pentru numarul de runde in care
     * eroul are stun.
     * @return
     */
    public int getRoundsStun() {
        return roundsStun;
    }

    /**
     * Setter pentru numarul de runde de stun.
     * @param roundsStun
     */
    public void setRoundsStun(final int roundsStun) {
        this.roundsStun = roundsStun;
    }
    /**
     * Getter pentru HP maxim.
     * @return
     */
    public float getMaxHP() {
        return maxHP;
    }

    /**
     * Setter pentru HP maxim.
     * @param maxHP
     */
    public void setMaxHP(final float maxHP) {
        this.maxHP = maxHP;
    }

    /**
     * Setter pentru numarul de runde de overtime damage.
     * @param debuff
     */
    public void setDebuff(final int debuff) {
        this.debuff = debuff;
    }

    /**
     * Getter pentru a verifica daca un erou e mort.
     * @return
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Setter pentru a omori un erou.
     * @param dead
     */
    public void setDead(final boolean dead) {
        this.dead = dead;
    }

    /**
     * Setter pentru damage-ul overtime pe care
     * eroul o sa il primeasca in "debuff" runde.
     * @param overtimeDamage
     */
    public void setOvertimeDamage(final boolean overtimeDamage) {
        this.overtimeDamage = overtimeDamage;
    }

    /**
     * Setter pentru HP.
     * @param hitpoints
     */
    public void setHP(final float hitpoints) {
        this.hp = hitpoints;
    }

    /**
     * Setter pentru tipul de teren.
     * @param landType
     */
    public void setLandType(final String landType) {
        this.landtype = landType;
    }

    /**
     * Metoda ce decrementeaza numarul de runde
     * in care eroul are stun.
     */
    public void decreaseRoundsStun() {
        roundsStun--;
    }

    /**
     * Metoda pentru a creste XP-ul.
     * @param killedHero Eroul ce a fost omorat.
     */
    public void gatherXP(final Hero killedHero) {
        if (killedHero.isDead() && !isDead()) {
            xp += Math.max(0, XP_CONSTANT - (level
                    - killedHero.level) * XP_MULTIPLIER);
        }
    }

    /**
     * Getter pentru linia pe care se afla eroul.
     * @return
     */
    public int getLine() {
        return line;
    }

    /**
     * Getter pentru coloana pe care se afla eroul.
     * @return
     */
    public int getCol() {
        return col;
    }

    /**
     * Getter pentru tipul eroului(P/K/R/W).
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Getter pentru tipul terenului pe care
     * se afla eroul.
     * @return
     */
    public String getLandtype() {
        return landtype;
    }

    /**
     * Getter pentru XP.
     * @return XP.
     */
    public int getXP() {
        return xp;
    }

    /**
     * Metoda ce permite eroilor sa primeasca
     * damage, nu doar sa dea.
     * @param damage
     */
    public void getDamage(final float damage) {
        hp -= damage;
        if (hp <= 0) {
            hp = 0;
            dead = true;
        }
    }

    /**
     * Setter pentru level.
     * @param level
     */
    public void setLevel(final int level) {
        this.level = level;
    }

    /**
     * Setter pentru stun.
     * @param stun
     */
    public void setStun(final boolean stun) {
        this.stun = stun;
    }

    /**
     * Metoda pentru a face level up ce
     * urmeaza a fi suprascrisa.
     */
    public void levelUp() { }

    /**
     * Getter pentru level.
     * @return
     */
    public int getLevel() {
        return level;
    }

    /**
     * Metoda ce deplaseaza eroul pe harta.
     * @param command Directia in care eroul
     *                se misca pe harta.
     */
    public void move(final char command) {
        if (!stun) {
            switch (command) {
                case 'U':
                    line--;
                    break;
                case 'D':
                    line++;
                    break;
                case 'L':
                    col--;
                    break;
                case 'R':
                    col++;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Getter pentru stun pentru a verifica
     * daca un erou se poate misca.
     * @return
     */
    public boolean isStun() {
        return stun;
    }

    /**
     * Getter pentru HP.
     * @return
     */
    public float getHP() {
        return hp;
    }

    /**
     * Metoda pentru a o suprascrie si pentru
     * a face Upcasting. Initializeaza o lupta.
     * @param hero Eroul ce o sa fie atacat.
     */
    public void checkBattle(final Hero hero) { }

    /**
     * Metoda pentru a calcula damage-ul total dat
     * de un erou asupra altui erou.
     * @param hero Eroul ce o sa primeasca damage.
     * @return Damage-ul primit.
     */
    public float calcDamage(final Hero hero) {
        return 0.0f;
    }

    /**
     * Setter pentru valoare overtimeDamage.
     * @param overtimeDamageValue Damage-ul ce o sa fie primit
     *                            de erou in fiecare runda.
     */
    public void setOvertimeDamageValue(final float overtimeDamageValue) {
        this.overtimeDamageValue = overtimeDamageValue;
    }

    /**
     * Primeste overtime damage.
     */
    public void receiveOvertimeDamage() {
        if (hp <= 0) {
            hp = 0;
            dead = true;
            return;
        }
        if (hp - overtimeDamageValue <= 0) {
            hp = 0;
            dead = true;
        }
        if (overtimeDamage && debuff != 0) {
            hp -= overtimeDamageValue;
            debuff--;
        }
    }

    /**
     * Metoda pentru a face override.
     * @param hero Eroul asupra caruia vor fi folosite
     *             abilitatile, fara Race Modifiers.
     * @return Damage-ul primit.
     */
    public float damageNoModifiers(final Hero hero) {
        return 0;
    }
}

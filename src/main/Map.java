package main;

import fileio.FileSystem;

import java.io.IOException;

public class Map {
    private int lines;
    private int cols;
    private String[][] lands;
    private int nrHeroes;
    private Hero[] heroes;
    private int nrRounds;
    private String[] commands;

    public Map(final String[] args) throws IOException {
        FileSystem fileSystem = new FileSystem(args[0], args[1]);
        lines = fileSystem.nextInt();
        cols = fileSystem.nextInt();
        lands = new String[lines][cols];
        for (int i = 0; i < lines; i++) {
            String aux = fileSystem.nextWord();
            for (int j = 0; j < aux.length(); j++) {
                lands[i][j] = aux.substring(j, j + 1);
            }
        }
        nrHeroes = fileSystem.nextInt();
        heroes = new Hero[nrHeroes];
        for (int i = 0; i < nrHeroes; i++) {
            String type = fileSystem.nextWord();
            switch (type) {
                case "R":
                    heroes[i] = new Rogue(fileSystem.nextInt(), fileSystem.nextInt());
                    break;
                case "K":
                    heroes[i] = new Knight(fileSystem.nextInt(), fileSystem.nextInt());
                    break;
                case "P":
                    heroes[i] = new Pyromancer(fileSystem.nextInt(), fileSystem.nextInt());
                    break;
                case "W":
                    heroes[i] = new Wizard(fileSystem.nextInt(), fileSystem.nextInt());
                    break;
                default:
                    break;
            }
        }
        nrRounds = fileSystem.nextInt();
        commands = new String[nrRounds];
        for (int i = 0; i < nrRounds; i++) {
            commands[i] = fileSystem.nextWord();
        }
        fileSystem.close();
    }

    /**
     * Getter pentru tipul de teren de la
     * linia x si coloana y.
     * @param x Linia
     * @param y Coloana
     * @return Tipul de teren.
     */
    public String getLandType(final int x, final int y) {
        return lands[x][y];
    }

    /**
     * Seteaza tipul de teren pe care se afla
     * fiecare erou in parte.
     */
    public void setLandType() {
        for (int i = 0; i < heroes.length; i++) {
            heroes[i].setLandType(getLandType(heroes[i].getLine(),
                    heroes[i].getCol()));
        }
    }

    /**
     * Inceperea jocului.
     */
    public void startGame() {
        setLandType();
        for (int i = 0; i < nrRounds; i++) {
            for (int j = 0; j < heroes.length; j++) {
                heroes[j].receiveOvertimeDamage();
            }
            char[] aux = commands[i].toCharArray();
            for (int j = 0; j < aux.length; j++) {
                if (heroes[j].isDead()) {
                    continue;
                }
                if (heroes[j].isStun() && heroes[j].getRoundsStun() != 0) {
                    heroes[j].decreaseRoundsStun();
                    if (heroes[j].getRoundsStun() == 0) {
                        heroes[j].setStun(false);
                    }
                    continue;
                }
                heroes[j].move(aux[j]);
                setLandType();
            }
            for (int k = 0; k < heroes.length - 1; k++) {
                for (int q = k + 1; q < heroes.length; q++) {
                    if (heroes[k].isDead() || heroes[q].isDead()) {
                        continue;
                    }
                    heroes[q].checkBattle(heroes[k]);
                    heroes[k].checkBattle(heroes[q]);
                }
            }
            for (int j = 0; j < heroes.length; j++) {
                if (heroes[j].isDead()) {
                    continue;
                }
                heroes[j].levelUp();
            }
        }
    }

    /**
     * Scrierea rezultatelor in fisierul text.
     * @param args Fisierul de intrare si cel de iesire.
     * @throws IOException
     */
    public void writeOutput(final String[] args) throws IOException {
        FileSystem fileSystem = new FileSystem(args[0], args[1]);
        for (int i = 0; i < nrHeroes; i++) {
            if (heroes[i].isDead()) {
                fileSystem.writeWord(heroes[i].getType() + " dead");
                fileSystem.writeNewLine();
            } else {
                fileSystem.writeWord(heroes[i].getType() + " "
                       + heroes[i].getLevel() + " "
                       + heroes[i].getXP() + " "
                       + ((int) heroes[i].getHP()) + " "
                        + heroes[i].getLine() + " "
                       + heroes[i].getCol());
                fileSystem.writeNewLine();
            }
        }
        fileSystem.close();
    }
}

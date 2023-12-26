package ir.sharif.math.ap2023.mafia.model;

public abstract class Player {
    protected int id;

    protected String name;
    protected boolean alive = true;
    protected boolean mute = false;
    protected boolean heal = false;

    public int target = 0;

    public int vote = 0;

    public boolean shooted=false;
    public boolean mafiaShooted = false;

    boolean isHealedSniper = false;

    public void setHealedSniper(boolean healedSniper) {
        isHealedSniper = healedSniper;
    }

    public boolean isHealedSniper() {
        return isHealedSniper;
    }


    public Player(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public abstract String action(Player target);

    public void vote(Player target) {

        target.vote++;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }

    public boolean isShooted() {
        return shooted;
    }

    public void setShooted(boolean shooted) {
        this.shooted = shooted;
    }

    public boolean isMafiaShooted() {
        return mafiaShooted;
    }

    public void setMafiaShooted(boolean mafiaShooted) {
        this.mafiaShooted = mafiaShooted;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setMute(boolean mute) {
        this.mute = mute;
    }

    public boolean isMute() {
        return mute;
    }

    public void setHeal(boolean heal) {
        this.heal = heal;
    }

    public boolean isHeal() {
        return heal;
    }
}
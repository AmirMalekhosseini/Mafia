package ir.sharif.math.ap2023.mafia.model;

public class Sniper extends Citizen {

    public Sniper(String name, int id) {
        super(name, id);
    }
    @Override
    public void vote(Player target) {
        super.vote(target);
    }

    @Override
    public String action(Player target) {

        if (getNumberOfBulletsLeft() == 0) {
            return "NO_BULLETS";
        } else if (target != null && !target.isHealedSniper) {
                target.setAlive(false);
                target.shooted = true;
        }
        numberOfBulletsLeft--;
        return "";
    }


    int numberOfBulletsLeft = 2;

    public int getNumberOfBulletsLeft() {
        return numberOfBulletsLeft;
    }
}
package ir.sharif.math.ap2023.mafia.model;

public class Natasha extends Mafia {

    public Natasha(String name, int id) {
        super(name, id);
    }
    @Override
    public void vote(Player target) {
        super.vote(target);
    }

    @Override
    public String action(Player target) {

        target.setMute(true);

        return "";
    }
}
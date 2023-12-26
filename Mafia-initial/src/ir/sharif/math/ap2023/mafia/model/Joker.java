package ir.sharif.math.ap2023.mafia.model;

public class Joker extends Player{

    @Override
    public String action(Player target) {
        return "";
    }

    @Override
    public void vote(Player target) {
        super.vote(target);
    }

    public Joker(String name, int id) {
        super(name, id);
    }
}
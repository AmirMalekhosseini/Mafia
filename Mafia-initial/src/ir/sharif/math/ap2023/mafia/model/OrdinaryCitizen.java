package ir.sharif.math.ap2023.mafia.model;

public class OrdinaryCitizen extends Citizen {

    public OrdinaryCitizen(String name, int id) {
        super(name, id);
    }

    @Override
    public String action(Player target) {
        return "";
    }

    @Override
    public void vote(Player target) {
        super.vote(target);
    }
}
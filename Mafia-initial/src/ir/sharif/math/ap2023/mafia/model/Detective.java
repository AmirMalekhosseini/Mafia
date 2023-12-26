package ir.sharif.math.ap2023.mafia.model;

public class Detective extends Citizen{

    public Detective(String name, int id) {
        super(name, id);
    }
    @Override
    public void vote(Player target) {
        super.vote(target);
    }

    @Override
    public String action(Player target) {

        if (target instanceof GodFather && ((GodFather) target).isAsked()) {
            return "MAFIA";
        } else if (target instanceof GodFather) {
            ((GodFather) target).asked = true;
            return "NO_MAFIA";
        } else if (target instanceof Mafia) {
            return "MAFIA";
        } else if (target instanceof Citizen || target instanceof Joker) {
            return "NO_MAFIA";
        } else
            return "";

    }
}
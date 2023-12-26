package ir.sharif.math.ap2023.mafia.model;

public class GodFather extends Mafia {


    public GodFather(String name, int id) {
        super(name, id);
    }
    @Override
    public void vote(Player target) {
        super.vote(target);
    }

    @Override
    public String action(Player target) {

        if (!target.isHeal()) {
            target.setAlive(false);
            target.mafiaShooted = true;
        }

        return "";
    }


    protected boolean asked;

    public boolean isAsked() {
        return asked;
    }

}


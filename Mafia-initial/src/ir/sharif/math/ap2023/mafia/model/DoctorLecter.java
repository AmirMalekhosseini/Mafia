package ir.sharif.math.ap2023.mafia.model;

public class DoctorLecter extends Mafia {

    public DoctorLecter(String name, int id) {
        super(name, id);
    }
    @Override
    public void vote(Player target) {
        super.vote(target);
    }

    @Override
    public String action(Player target) {

        if (target != null) {
            if (!target.alive && target.shooted) {
                target.setAlive(true);
                target.setHealedSniper(true);
            } else if (target.isAlive()) {
                target.setHealedSniper(true);
            }
        }

        return "";
    }



}
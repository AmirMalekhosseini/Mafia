package ir.sharif.math.ap2023.mafia.model;

public class Doctor extends Citizen  {

    public Doctor(String name, int id) {
        super(name, id);
    }
    @Override
    public void vote(Player target) {
        super.vote(target);
    }

    @Override
    public String action(Player target) {
        if (target instanceof Doctor && ((Doctor) target).getNumberOfSelfSave()==0) {
            return "NO_SAVE";
        }
        else if (target != null) {
            if(!target.isAlive() && target.mafiaShooted) {
                if(target instanceof Doctor){
                    numberOfSelfSave--;
                }
                target.setAlive(true);
                target.setHeal(true);
            } else if (target.isAlive()) {
                target.setHeal(true);
                if(target instanceof Doctor) {
                    numberOfSelfSave--;
                }
            }
        }

        return "";
    }

    int numberOfSelfSave = 2;

    public int getNumberOfSelfSave() {
        return numberOfSelfSave;
    }


}
package ir.sharif.math.ap2023.mafia.logic;

import ir.sharif.math.ap2023.mafia.model.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameState {

    int round = 0;

    boolean isDay = true;

    ArrayList<Player> players = new ArrayList<>();
    ArrayList<Player> alivePlayers = new ArrayList<>();
    ArrayList<Player> deadPlayers = new ArrayList<>();
    ArrayList<Player> winnerPlayers = new ArrayList<>();
    ArrayList<Player> playerExecuted = new ArrayList<>();
    ArrayList<Player> jokerWinner = new ArrayList<>();
    ArrayList<Player> mafiaWinner = new ArrayList<>();
    ArrayList<Player> citizenWinner = new ArrayList<>();

    public GameState(List<Player> players) {

        this.players.addAll(players);
        alivePlayers.addAll(players);

    }

    public List<Player> allPlayers() {
        return this.players;
    }

    public void nextDay() {
        ArrayList<Integer> mafiaTarget = new ArrayList<>();
        int alivePlayerSize = alivePlayers.size();
        int deadPlayerIndex = -1;
        if (playerExecuted.size() > 0) {
            playerExecuted.remove(0);
        }
        boolean godFatherIn = false;
        for (int q = 0; q < alivePlayers.size(); q++) {
            if (alivePlayers.get(q) instanceof GodFather) {
                godFatherIn = true;
                break;
            }
        }
        if (!godFatherIn) {
            for (int k = 0; k < alivePlayers.size(); k++) {
                mafiaTarget.add(alivePlayers.get(k).target);
            }

            if (mafiaTarget.size() > 0) {
                Integer maxTarget = Collections.max(mafiaTarget);

                if (maxTarget != 0) {
                    for (int i = 0; i < mafiaTarget.size(); i++) {
                        if (Objects.equals(maxTarget, mafiaTarget.get(i)))
                            deadPlayerIndex = i;
                    }

                    if (!alivePlayers.get(deadPlayerIndex).isHeal()) {
                        alivePlayers.get(deadPlayerIndex).mafiaShooted = true;
                        alivePlayers.get(deadPlayerIndex).setAlive(false);
                    }
                }
            }
        }


        for (int i = alivePlayerSize - 1; i >= 0; i--) {
            if (!alivePlayers.get(i).isAlive()) {
                if (deadPlayers.size() == 0) {
                    deadPlayers.add(alivePlayers.get(i));
                } else {
                    deadPlayers.add(alivePlayers.get(i));
                }
            }

        }
        for (int j = 0; j < alivePlayers.size(); j++) {
            alivePlayers.get(j).vote = 0;
        }

        for (int i = alivePlayers.size() - 1; i >= 0; i--) {
            if (!alivePlayers.get(i).isAlive()) {
                alivePlayers.remove(i);
            }
        }


        round++;
        isDay = true;

    }

    public void nextNight() {

        ArrayList<Integer> executedVote = new ArrayList<>();

        int executedPlayerIndex = -1;
        boolean sameVote = false;
        int voteCounter = 0;

        for (int i = 0; i < alivePlayers.size(); i++) {
            executedVote.add(alivePlayers.get(i).vote);
        }

        if (executedVote.size() > 0) {
            Integer maxVote = Collections.max(executedVote);


            for (int i = 0; i < executedVote.size(); i++) {
                if (Objects.equals(executedVote.get(i), maxVote)) {
                    voteCounter++;
                }
                if (voteCounter > 1) {
                    sameVote = true;
                }
            }

            if (sameVote || maxVote <= (float) alivePlayers.size() / 2) {
                for (int i = 0; i < playerExecuted.size(); i++) {
                    playerExecuted.remove(i);
                }
            } else {
                for (int i = 0; i < executedVote.size(); i++) {
                    if (maxVote.equals(executedVote.get(i))) {
                        executedPlayerIndex = i;
                        break;
                    }
                }
            }
            if (executedPlayerIndex != -1) {
                alivePlayers.get(executedPlayerIndex).setAlive(false);
                playerExecuted.add(alivePlayers.get(executedPlayerIndex));
                alivePlayers.remove(executedPlayerIndex);
                if (playerExecuted.get(0) instanceof Joker) {
                    winnerPlayers.add(playerExecuted.get(0));
                }

            }
        }
        for (int i = deadPlayers.size() - 1; i >= 0; i--) {
            deadPlayers.remove(i);
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).setMute(false);
        }

        for (int j = 0; j < alivePlayers.size(); j++) {
            alivePlayers.get(j).setHeal(false);
            alivePlayers.get(j).setHealedSniper(false);
            alivePlayers.get(j).shooted = false;
            alivePlayers.get(j).mafiaShooted = false;
            alivePlayers.get(j).target = 0;
        }

        isDay = false;


    }

    public int getRound() {

        return round;
    }

    public List<Player> getAlivePlayers() {

        int alivePlayerSize = alivePlayers.size();
        for (int i = alivePlayerSize - 1; i >= 0; i--) {
            if (!alivePlayers.get(i).isAlive() && alivePlayers.size() > 0) {
                alivePlayers.remove(i);
            }
        }

        return alivePlayers;
    }

    public boolean isDay() {
        return isDay;
    }

    public List<Player> getDeadPlayersInLastRound() {

        return deadPlayers;
    }

    public Player getSilentPlayerInLastRound() {

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isMute()) {
                return players.get(i);
            }
        }

        return null;
    }

    public List<Player> getWinners() {
        ArrayList<Player> empty = new ArrayList<>();

        int mafiaCounter = 0;
        int citizenCounter = 0;
        boolean jokerIn = false;
        int citizenWinnerSize = citizenWinner.size();
        int mafiaWinnerSize = mafiaWinner.size();

        if (jokerWinner.size() > 0) {
            jokerWinner.remove(0);
        }

        if (citizenWinner.size() > 0) {
            for (int i = citizenWinnerSize - 1; i >= 0; i--) {
                citizenWinner.remove(i);
            }
        }

        if (mafiaWinner.size() > 0) {
            for (int i = mafiaWinnerSize - 1; i >= 0; i--) {
                mafiaWinner.remove(i);
            }
        }

        for (int i = 0; i < winnerPlayers.size(); i++) {
            if (winnerPlayers.get(i) instanceof Joker) {
                jokerWinner.add(winnerPlayers.get(i));
                return jokerWinner;
            }
        }

        for (int m = 0; m < alivePlayers.size(); m++) {
            if (alivePlayers.get(m) instanceof Joker) {
                jokerIn = true;
                break;
            }
        }

        for (int j = 0; j < alivePlayers.size(); j++) {
            if (alivePlayers.get(j) instanceof Mafia) {
                mafiaCounter++;
            } else if (alivePlayers.get(j) instanceof Citizen) {
                citizenCounter++;
            }
        }


        for (int i = 0; i < players.size(); i++) {
            if (players.get(i) instanceof Mafia) {
                mafiaWinner.add(players.get(i));
            } else if (players.get(i) instanceof Citizen) {
                citizenWinner.add(players.get(i));
            }
        }

        if (jokerIn) {

            if (mafiaCounter >= citizenCounter + 1) {
                return mafiaWinner;
            } else if (mafiaCounter == 0) {
                return citizenWinner;
            }
        } else {
            if (mafiaCounter >= citizenCounter) {
                return mafiaWinner;
            } else if (mafiaCounter == 0) {
                return citizenWinner;
            }
        }
        return empty;
    }

    public Player getExecutedPlayer() {

        if (playerExecuted.size() == 0) {
            return null;
        } else
            return playerExecuted.get(0);
    }
}


package org.example;

public class TennisGame1 implements TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame1(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName == "player1")
            player1Score += 1;
        else
            player2Score += 1;
    }

    public String getScore() {
        if (isDeuce()) {
            return getGameEqual();
        } else if (isSomeoneInAdvantage()) {
            return getAdvantageOrWinner();
        } else {
            return getNormalScore();
        }
    }

    private boolean isDeuce() {
        return player1Score == player2Score;
    }

    private String getNormalScore() {
        int tempScore;
        StringBuilder score = new StringBuilder();
        for (int i = 1; i < 3; i++) {
            if (i == 1) tempScore = player1Score;
            else {
                score.append("-");
                tempScore = player2Score;
            }
            switch (tempScore) {
                case 0 -> score.append("Love");
                case 1 -> score.append("Fifteen");
                case 2 -> score.append("Thirty");
                case 3 -> score.append("Forty");
            }
        }
        return score.toString();
    }

    private String getAdvantageOrWinner() {
        String score;
        int scoreDifference = player1Score - player2Score;
        if (scoreDifference == 1) score = "Advantage player1";
        else if (scoreDifference == -1) score = "Advantage player2";
        else if (scoreDifference >= 2) score = "Win for player1";
        else score = "Win for player2";
        return score;
    }

    private boolean isSomeoneInAdvantage() {
        return player1Score >= 4 || player2Score >= 4;
    }

    private String getGameEqual() {
        return switch (player1Score) {
            case 0 -> "Love-All";
            case 1 -> "Fifteen-All";
            case 2 -> "Thirty-All";
            default -> "Deuce";
        };
    }
}

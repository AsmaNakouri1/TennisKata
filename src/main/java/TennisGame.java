public class TennisGame {
    String scoreGamePlayerOne;
    String scoreGamePlayerTow;
    Integer playerOneGameWins;
    Integer playerTowGameWins;

    public TennisGame(){
        this.scoreGamePlayerOne= "0";
        this.scoreGamePlayerTow= "0";
        this.playerOneGameWins = 0;
        this.playerTowGameWins =0;
    }

    public String getScoreGame() {
        StringBuilder scoreGame = new StringBuilder();

        scoreGame.append(scoreGamePlayerOne);
        scoreGame.append(" _ ");
        scoreGame.append(scoreGamePlayerTow);

        return scoreGame.toString();
    }

    public void firstPlayerWinsOnePoint() {
        playerOneGameWins = playerOneGameWins + 1;
        updateScoreGameForEachPlayer();
    }

    public void secondPlayerWinsOnePoint() {
        playerTowGameWins = playerTowGameWins + 1;
        updateScoreGameForEachPlayer();
    }

    private void updateScoreGameForEachPlayer() {

        if (isThereAdvantage()) {
            scoreGamePlayerOne = (playerOneGameWins > playerTowGameWins ? "ADV" : "40");
            scoreGamePlayerTow = playerTowGameWins > playerOneGameWins ? "ADV" : "40";
        }

        if (isThereDeuce()) {
            scoreGamePlayerOne = "DEUCE";
            scoreGamePlayerTow = "DEUCE";
        }

        if (isThereGameWin()) {
            scoreGamePlayerOne = "0";
            scoreGamePlayerTow = "0";
        }
        if(playerOneGameWins<4 && playerTowGameWins<4) {
            scoreGamePlayerOne = getScoreGameFromPoint(playerOneGameWins);
            scoreGamePlayerTow = getScoreGameFromPoint(playerTowGameWins);
        }

    }

    private boolean isThereDeuce() {
        return (playerOneGameWins >= 4 && playerTowGameWins >= 4) && (playerOneGameWins == playerTowGameWins);
    }

    private boolean isThereGameWin() {
        boolean hasAnyPlayerFourGameWins = playerOneGameWins >= 4 || playerTowGameWins >= 4;
        boolean isThereTowGameWinsDifferenceBetweenPlayers = Math.abs(playerOneGameWins - playerTowGameWins) >= 2;

        return hasAnyPlayerFourGameWins && isThereTowGameWinsDifferenceBetweenPlayers;
    }

    private boolean isThereAdvantage() {
        return (playerOneGameWins >= 3 && playerTowGameWins >= 3) && (Math.abs(playerOneGameWins - playerTowGameWins) == 1);
    }


    private String getScoreGameFromPoint(Integer point) {
        switch (point) {
            case 0:
                return "0";
            case 1:
                return "15";
            case 2:
                return "30";
            case 3:
                return "40";
            default:
                return null;
        }
    }

}

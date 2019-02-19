public class TennisGame {
    private String scoreGamePlayerOne;
    private String scoreGamePlayerTow;
    Integer playerOneGameWins;
    Integer playerTowGameWins;
    private Integer scoreSetPlayerOne;
    private Integer scoreSetPlayerTow;
    Integer scoreTieBreakPlayerOne;
    Integer scoreTieBreakPlayerTow;

    public TennisGame(){
        this.scoreGamePlayerOne= "0";
        this.scoreGamePlayerTow= "0";
        this.playerOneGameWins = 0;
        this.playerTowGameWins = 0;
        this.scoreSetPlayerOne = 0;
        this.scoreSetPlayerTow = 0;
        this.scoreTieBreakPlayerOne = 0;
        this.scoreTieBreakPlayerTow = 0;
    }

    public String getScoreGame() {
        StringBuilder scoreGame = new StringBuilder();

        scoreGame.append(scoreGamePlayerOne);
        scoreGame.append(" _ ");
        scoreGame.append(scoreGamePlayerTow);

        return scoreGame.toString();
    }

    public String getScoreSet() {
        StringBuilder scoreSet = new StringBuilder();

        scoreSet.append(scoreSetPlayerOne);
        scoreSet.append(" _ ");
        scoreSet.append(scoreSetPlayerTow);

        return scoreSet.toString();
    }

    public String getScoreTieBreakPoint() {
        StringBuilder scoreTieBreak = new StringBuilder();
        scoreTieBreak.append(scoreTieBreakPlayerOne.toString());
        scoreTieBreak.append(" _ ");
        scoreTieBreak.append(scoreTieBreakPlayerTow.toString());
        return (scoreTieBreak.toString());
    }

    public void firstPlayerWinsOnePoint() {
        if (scoreSetPlayerTow == 6 && scoreSetPlayerOne == 6)
            firstPlayerWinsOneTieBreakPoint();
        else
            firstPlayerWinsOneGamePoint();
    }

    private void firstPlayerWinsOneGamePoint() {
        playerOneGameWins = playerOneGameWins + 1;
        updateScoreGameForEachPlayer();
        if(isThereGameWin()) firstPlayerWinsOneGame();
    }

    private void firstPlayerWinsOneGame() {
        firstPlayerAddsOnePointToScoreSet();
        playerOneGameWins =0;
        playerTowGameWins =0;
    }

    public  void firstPlayerWinsOneTieBreakPoint() {
        scoreTieBreakPlayerOne = scoreTieBreakPlayerOne + 1;
         if (isThereWinTieBreak()) {
             firstPlayerWinsTieBreak();
         }
    }

    private void firstPlayerWinsTieBreak() {
        scoreTieBreakPlayerOne = 0;
        scoreTieBreakPlayerTow = 0;
        firstPlayerAddsOnePointToScoreSet();
    }

    private boolean isThereWinTieBreak() {
        return (scoreTieBreakPlayerOne >= 7 || scoreTieBreakPlayerTow >= 7) && (Math.abs(scoreTieBreakPlayerOne - scoreTieBreakPlayerTow) >= 2);

    }


    public void secondPlayerWinsOnePoint() {
        if (scoreSetPlayerTow == 6 && scoreSetPlayerOne == 6)
            secondPlayerWinsOneTieBreakPoint();
        else
            secondPlayerWinsOneGamePoint();
    }

    private void secondPlayerWinsOneGamePoint() {
        playerTowGameWins = playerTowGameWins + 1;
        updateScoreGameForEachPlayer();
        if(isThereGameWin()) secondPlayerWinsOneGame();
    }

    public void secondPlayerWinsOneTieBreakPoint() {
        scoreTieBreakPlayerTow = scoreTieBreakPlayerTow+1;
        if (isThereWinTieBreak()) {
            secondPlayerWinsTieBreak();
        }
    }

    private void secondPlayerWinsTieBreak() {
        scoreTieBreakPlayerOne = 0;
        scoreTieBreakPlayerTow = 0;
        secondPlayerAddsOnePointToScoreSet();
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


    private void secondPlayerWinsOneGame() {
        secondPlayerAddsOnePointToScoreSet();
        playerOneGameWins =0;
        playerTowGameWins =0;
    }


    private boolean isThereDeuce() {
        return (playerOneGameWins >= 4 && playerTowGameWins >= 4) && (playerOneGameWins.equals(playerTowGameWins));
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


    public void firstPlayerAddsOnePointToScoreSet() {
        scoreSetPlayerOne = scoreSetPlayerOne + 1;
    }

    public void secondPlayerAddsOnePointToScoreSet() {
        scoreSetPlayerTow = scoreSetPlayerTow + 1;
    }
}

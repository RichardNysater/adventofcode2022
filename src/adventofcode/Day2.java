package adventofcode;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

//TODO: Model winning/losing differently instead of using a switch case
public class Day2 {
    public static final char ROCK = 'X';
    public static final char PAPER = 'Y';
    public static final char SCISSORS = 'Z';

    public static final char NEED_TO_LOSE = 'X';
    public static final char NEED_TO_DRAW = 'Y';
    public static final char NEED_TO_WIN = 'Z';

    public static final char OPPONENT_ROCK = 'A';
    public static final char OPPONENT_PAPER = 'B';
    public static final char OPPONENT_SCISSORS = 'C';

    public static final Map<Character, Integer> SCORE_BY_SHAPE = Map.of(ROCK, 1, PAPER, 2, SCISSORS, 3);

    public static final Integer SCORE_WIN = 6;
    public static final Integer SCORE_DRAW = 3;
    public static final Integer SCORE_LOSE = 0;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("resources/day2.txt"));
        System.out.println(getStrategyScore(lines));
        System.out.println(getStrategy2Score(lines));
    }

    public static int getStrategyScore(List<String> lines) {
        return lines.stream().mapToInt(line -> getScoreByPlay(line.charAt(0), line.charAt(2))).sum();
    }

    public static int getScoreByPlay(Character opponentsPlay, Character myPlay) {
        return SCORE_BY_SHAPE.get(myPlay) + switch (myPlay) {
            case ROCK -> switch (opponentsPlay) {
                case OPPONENT_ROCK -> SCORE_DRAW;
                case OPPONENT_PAPER -> SCORE_LOSE;
                case OPPONENT_SCISSORS -> SCORE_WIN;
                default -> throw new IllegalArgumentException("Illegal play: " + opponentsPlay);
            };
            case PAPER -> switch (opponentsPlay) {
                case OPPONENT_ROCK -> SCORE_WIN;
                case OPPONENT_PAPER -> SCORE_DRAW;
                case OPPONENT_SCISSORS -> SCORE_LOSE;
                default -> throw new IllegalArgumentException("Illegal play: " + opponentsPlay);
            };
            case SCISSORS -> switch (opponentsPlay) {
                case OPPONENT_ROCK -> SCORE_LOSE;
                case OPPONENT_PAPER -> SCORE_WIN;
                case OPPONENT_SCISSORS -> SCORE_DRAW;
                default -> throw new IllegalArgumentException("Illegal play: " + opponentsPlay);
            };
            default -> throw new IllegalArgumentException("Illegal play: " + opponentsPlay);
        };
    }

    public static int getStrategy2Score(List<String> lines) {
        return lines.stream().mapToInt(line -> getScore2ByPlay(line.charAt(0), line.charAt(2))).sum();
    }

    public static int getScore2ByPlay(Character opponentsPlay, Character wantedOutcome) {
        return switch (wantedOutcome) {
            case NEED_TO_LOSE -> SCORE_LOSE + switch (opponentsPlay) {
                case OPPONENT_ROCK -> SCORE_BY_SHAPE.get(SCISSORS);
                case OPPONENT_PAPER -> SCORE_BY_SHAPE.get(ROCK);
                case OPPONENT_SCISSORS -> SCORE_BY_SHAPE.get(PAPER);
                default -> throw new IllegalArgumentException("Illegal play: " + opponentsPlay);
            };
            case NEED_TO_DRAW -> SCORE_DRAW + switch (opponentsPlay) {
                case OPPONENT_ROCK -> SCORE_BY_SHAPE.get(ROCK);
                case OPPONENT_PAPER -> SCORE_BY_SHAPE.get(PAPER);
                case OPPONENT_SCISSORS -> SCORE_BY_SHAPE.get(SCISSORS);
                default -> throw new IllegalArgumentException("Illegal play: " + opponentsPlay);
            };
            case NEED_TO_WIN -> SCORE_WIN + switch (opponentsPlay) {
                case OPPONENT_ROCK -> SCORE_BY_SHAPE.get(PAPER);
                case OPPONENT_PAPER -> SCORE_BY_SHAPE.get(SCISSORS);
                case OPPONENT_SCISSORS -> SCORE_BY_SHAPE.get(ROCK);
                default -> throw new IllegalArgumentException("Illegal play: " + opponentsPlay);
            };
            default -> throw new IllegalArgumentException("Illegal play: " + opponentsPlay);
        };
    }

}

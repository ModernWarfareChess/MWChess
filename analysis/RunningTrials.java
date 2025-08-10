package ludii_tutorials;

import java.io.*;
import java.util.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.Game;
import other.AI;
import other.GameLoader;
import other.context.Context;
import other.model.Model;
import other.trial.Trial;
import search.flat.HeuristicSampling;
import search.mcts.MCTS;
import search.mcts.playout.HeuristicPlayout;
import search.mcts.selection.UCB1;
import search.mcts.selection.UCB1Tuned;
import search.minimax.AlphaBetaSearch;
import search.minimax.UBFM;
import utils.RandomAI;

/**
 * Example class showing how we can run trials in Ludii
 *
 * @author Dennis Soemers
 */
public class RunningTrials
{
	
	/** The number of trials that we'd like to run */
	private static final int NUM_TRIALS = 100;
	
	/**
	 * Main method
	 * @param args Command-line arguments.
	 */
	public static void main(final String[] args) throws IOException
	{
		// Load our game -- we only need to do this once, and can use it for many trials
		final Game game = GameLoader.loadGameFromFile(new File("resources/luds/MWChess/MWChessV3_1.lud"));
		//final Game game = GameLoader.loadGameFromFile(new File("resources/luds/MWChess/Chess.lud"));
		
		// Prepare Context and Trial objects; these are also re-usable by resetting them,
		// but we'd have to copy them if we wanted to preserve all of the different objects
		// corresponding to different trials
		final Trial trial = new Trial(game);
		final Context context = new Context(game, trial);
		
		// Create AI objects that we'd like to use to play our Trials
		// Here we just use Ludii's built in Random AI, because it's fast
		// Ludii uses 1-based indexing for players, so we insert a null in the list first
		final List<AI> ais = new ArrayList<AI>();
		ais.add(null);
		for (int p = 1; p <= game.players().count(); ++p)
		{
			//ais.add(new RandomAI());
			//ais.add(new AlphaBetaSearch());
			ais.add(new UBFM());
		}

		// statistical variables
		int player1Wins = 0;
		int player2Wins = 0;
		int draws = 0;
		List<Integer> nonDrawTurns = new ArrayList<>();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter("results_MWChess.txt"))) {

			// Now we play through multiple trials
			for (int i = 0; i < NUM_TRIALS; ++i) {
				// This starts a new trial (resetting the Context and Trial objects if necessary)
				game.start(context);
				writer.write("Trial " + (i + 1) + ":\n");
				System.out.println("Starting a new trial NO. " + (i+1) + " !");

				// Random AI technically doesn't require initialisation, but it's good practice to do so
				// for all AIs at the start of every new trial
				for (int p = 1; p <= game.players().count(); ++p) {
					ais.get(p).initAI(game, p);
				}

				// This "model" object lets us go through a trial step-by-step using a single API
				// that works correctly for alternating-move as well as simultaneous-move games
				final Model model = context.model();

				// We keep looping for as long as the trial is not over
				int count = 0;
				while (!trial.over() && count < 140) { //70 truns
					// This call simply takes a single "step" in the game, using the list of AIs we give it,
					// and 1.0 second of "thinking time" per move.
					//
					// A step is a single move in an alternating-move game (by a single player), or a set of
					// moves (one per active player) in a simultaneous-move game.
					//if (count % 2 == 0)
					//	System.out.println("Turn " + (count/2 + 1));
					model.startNewStep(context, ais, 5);
					count++;
					//System.out.println(trial.getMove(count));
				}
				int turns = count / 2;
				System.out.println("Turns: " + turns);
				writer.write("Turns: " + turns + "\n");
				// When we reach this code, we know that the trial is over and we can see what ranks the
				// different players achieved
				final double[] ranking;

				if (count < 139) { // end of turn 70th
					ranking = trial.ranking();
				} else {
					ranking = new double[]{0.0, 1.5, 1.5};
				}
				// Rank of each player
				double rank1 = ranking[1];
				double rank2 = ranking[2];

				writer.write("P" + context.state().playerToAgent(1) + " Rank: " + rank1 + "\n");
				writer.write("P" + context.state().playerToAgent(2) + " Rank: " + rank2 + "\n");
				writer.write("\n");

				// Win rate
				if (rank1 == 1.0 && rank2 == 2.0) {
					player1Wins++;
					nonDrawTurns.add(turns);
				} else if (rank2 == 1.0 && rank1 == 2.0) {
					player2Wins++;
					nonDrawTurns.add(turns);
				} else {
					draws++;
				}

				for (int p = 1; p <= game.players().count(); ++p) {
					// Here we print the rankings as achieved by every agent, where
					// the "agent indices" correspond to the order of agents prior
					// to the game's start. This order will usually still be the same
					// at the end of a trial, but may be different if any swaps happened.
					//
					// ranking[p] tells you which rank was achieved by the player
					// who controlled the p'th "colour" at the end of a trial, and
					// trial.state().playerToAgent(p) tells you which agent (in the list
					// of AI objects) controls that colour at the end of the trial.
					//System.out.println("Agent " + context.state().playerToAgent(p) + " achieved rank: " + ranking[p]);
					System.out.println("P" + context.state().playerToAgent(p) + "Rank: " + ranking[p]);
				}
				System.out.println();
			}
			// Final statistics
			writer.write("\n===========================\n");
			writer.write("Total Trials: " + NUM_TRIALS + "\n");
			writer.write("Player 1 Wins: " + player1Wins + "\n");
			writer.write("Player 2 Wins: " + player2Wins + "\n");
			writer.write("Draws: " + draws + "\n");

			double p1WinRate = player1Wins * 1.0 / NUM_TRIALS;
			double p2WinRate = player2Wins * 1.0 / NUM_TRIALS;
			double drawRate = draws * 1.0 / NUM_TRIALS;

			writer.write(String.format("Win Rate - Player 1: %.4f\n", p1WinRate));
			writer.write(String.format("Win Rate - Player 2: %.4f\n", p2WinRate));
			writer.write(String.format("Draw Rate: %.4f\n", drawRate));

			// Mean and standard deviation (number of non-tied games)
			if (!nonDrawTurns.isEmpty()) {
				double mean = nonDrawTurns.stream().mapToInt(x -> x).average().orElse(0.0);
				double std = Math.sqrt(nonDrawTurns.stream()
						.mapToDouble(x -> (x - mean) * (x - mean))
						.average().orElse(0.0));
				writer.write(String.format("Non-Draw Avg Turns: %.2f\n", mean));
				writer.write(String.format("Non-Draw Turns StdDev: %.2f\n", std));
			} else {
				writer.write("No non-draw games, cannot compute avg turns.\n");
			}
		} catch (IOException e) {
			System.err.println("Error writing to results file: " + e.getMessage());
		}
	}
}

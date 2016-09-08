package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import players.*;

/**
 * Just replace add whatever players you want to participate in the tournament
 * to the players list. Make sure no two players have the same name or else the
 * scores will come out wrong. Scores at the end are printed out in no
 * particular order.
 * 
 * @author eliotkaplan
 * 
 */
public class Tournament {

	public static void main(String[] args) {
		//learningAlgorithm();
		//learningAlgorithm3();
		createComparison();
		//learningAlgorithm4();
		//kaplanBots();
	}

	public static void kaplanBots() {
		Tournament t = new Tournament();
		t.addPlayer(new DomanKat2000("DomanKat2000"));
		t.addPlayer(new CowardlyPlayer("Coward"));
		t.addPlayer(new AggressivePlayer("Aggressive"));
		t.addPlayer(new RandomPlayer("Random"));
		t.playTournament(100000, false);
	}

	public static void createComparison() {
		Tournament t = new Tournament();
		//t.addPlayer(new DomanKat("NewOne",0.7188068737200484, 0.8168048951519049, 0.2754850957575389, -5.270942745505025, 226.48509144059676, -196.62015701893074));
		//t.addPlayer(new DomanKat("NewOne2",0.7188068737200484, 0.8359860475395424, 0.2754850957575389, -5.270942745505025, -92.58260938439838, -196.62015701893074));
		//t.addPlayer(new DomanKat1("DomanKat1",0.52));
		//t.addPlayer(new SafetyBot2("DomanKat0",0.72));
		t.addPlayer(new DomanKat2000("DomanKat2000"));
//		//t.addPlayer(new DomanKat4("DomanKat4"));
//		//t.addPlayer(new DomanKat5("DomanKat5"));
//		//t.addPlayer(new DomanKat6("DomanKat6"));
//		//t.addPlayer(new DomanKat3("DomanKat3"));
		t.addPlayer(new DomanKat("DomanKat"));
//		//t.addPlayer(new DomanKat("NewoOne",10, 0));
////		t.addPlayer(new DomanKat10("DomanKat10"));
////		t.addPlayer(new DomanKat11("DomanKat11"));
//		t.addPlayer(new JosephBot("JosephBot"));
		//t.addPlayer(new BigBooty("BigBooty"));
		//t.addPlayer(new AlanBot("AlanBot"));
//		t.addPlayer(new AnaBot("AnaBot"));
		//t.addPlayer(new DomanKat7("DomanKat7"));
		//t.addPlayer(new DomanKat8("DomanKat8"));
		t.playTournament(20000, false);
	}

	//1: 0.652555, 0.657401490856765, 0.2384126067032677, -5.7912, -43.25296564985203. -347.66189734607747
	//2: 0.652555, 0.8788111381069228, 0.24831420802172627, -5.7912, 66.95238180882978, 152.82196572335602
	//3: 0.652555, 0.6835582001858403, 0.24396491692341576, -5.7912, -92.94502801420563, 391.3936615349471
	
	//4: 0.6877861014313942, 0.8196982029537958, 0.22570878974964947, -6.597898520705137, 78.3688614392571, 346.0450616049418
	//5: 0.6877861014313942, 0.8027507248164123, 0.2598728756863312, -6.597898520705137, -240.64589758975418, -977.1787670193635
	
	public static void learningAlgorithm2() {
		double bestRisk = 0.509659;
		double bestOtherRisk = 0.81782;
		double bestRandom = 0.289906;
		double bestProportion = -12.06879;
		double bestOtherProportion = -10000;
		double bestThirdProportion = -10000;

		double bestRisk2 = 0.652555;
		double bestOtherRisk2 = 0.7909;
		double bestRandom2 = 0.2232931931223644;
		double bestProportion2 = -5.7912;
		double bestOtherProportion2 = -76.53776;
		double bestThirdProportion2 = -572.2237;

		for (int i = 0; i < 200; i++) {
			int round = i + 1;
			System.out.println("Generation " + round + ":");
			Tournament t = new Tournament();
			t.addPlayer(new DomanKat(bestRisk + ", " + bestOtherRisk + ", "
					+ bestRandom + ", " + bestProportion + ", "
					+ bestOtherProportion + ", " + bestThirdProportion,
					bestRisk, bestOtherRisk, bestRandom, bestProportion,
					bestOtherProportion, bestThirdProportion));
			t.addPlayer(new DomanKat(bestRisk2 + ", " + bestOtherRisk2 + ", "
					+ bestRandom2 + ", " + bestProportion2 + ", "
					+ bestOtherProportion2 + ", " + bestThirdProportion2,
					bestRisk2, bestOtherRisk2, bestRandom2, bestProportion2,
					bestOtherProportion2, bestThirdProportion2));
			for (int j = 0; j < 6; j++) {
				double risk = 0.652555;
				double otherRisk = 0.7909;
				double random = 0.4 * Math.random();
				double proportion = -5.7912;
				double proportion2 = -76.53776;
				double proportion3 = 1500 * Math.random() - 750;
				if (200 < Math.abs(proportion3) && Math.abs(proportion3) < 40) {
					proportion3 = 80 * Math.random() - 40;
				}
				String name = risk + ", " + otherRisk + ", " + random + ", "
						+ proportion + ", " + proportion2 + ", " + proportion3;
				t.addPlayer(new DomanKat(name, risk, otherRisk, random,
						proportion, proportion2, proportion3));
			}
			ArrayList<Player> winners = t.playTournament(2000, false);
			Player winner = winners.get(winners.size() - 1);
			if (winner instanceof DomanKat) {
				bestRisk = ((DomanKat) winner).getRisk();
				bestOtherRisk = ((DomanKat) winner).getRisk2();
				bestRandom = ((DomanKat) winner).getRandom();
				bestProportion = ((DomanKat) winner).getProportion1();
				bestOtherProportion = ((DomanKat) winner).getProportion2();
				bestThirdProportion = ((DomanKat) winner).getProportion3();

			}
			Player winner2 = winners.get(winners.size() - 2);
			if (winner2 instanceof DomanKat) {
				bestRisk2 = ((DomanKat) winner2).getRisk();
				bestOtherRisk2 = ((DomanKat) winner2).getRisk2();
				bestRandom2 = ((DomanKat) winner2).getRandom();
				bestProportion2 = ((DomanKat) winner2).getProportion1();
				bestOtherProportion2 = ((DomanKat) winner2).getProportion2();
				bestThirdProportion2 = ((DomanKat) winner2).getProportion3();
			}
			System.out.println("");
		}
	}
	
	//21.370245921501645, 4.9204927274834045
	//10.455211424664428, 5.585668023171147
	
	public static void learningAlgorithm3() {
		double DistanceThree1 = 5.705645660384668;
		double DistanceTwo1 = 4.245628466096058;
		double DistanceThree2 = 0;
		double DistanceTwo2 = 10;
		for(int i = 0; i < 1000; i++) {
			int round = i + 1;
			System.out.println("Generation " + round + ":");
			Tournament t = new Tournament();
			t.addPlayer(new DomanKat(DistanceTwo1+", " +DistanceThree1, DistanceTwo1, DistanceThree1));
			t.addPlayer(new DomanKat(DistanceTwo2+", " +DistanceThree2, DistanceTwo2, DistanceThree2));
			for(int j = 0; j < 5; j++) {
				double DistanceTwo = 23*Math.random() - 1;
				double DistanceThree = 23*Math.random() - 1;
				String name = DistanceTwo + ", " + DistanceThree;
				t.addPlayer(new DomanKat(name, DistanceTwo, DistanceThree));
			}
			ArrayList<Player> winners = t.playTournament(1500, false);
			Player winner = winners.get(winners.size() - 1);
			if(winner instanceof DomanKat) {
				DistanceTwo1 = ((DomanKat) winner).getDistanceTwo();
				DistanceThree1 = ((DomanKat) winner).getDistanceThree();
			}
			Player winner2 = winners.get(winners.size() - 2);
			if(winner2 instanceof DomanKat) {
				DistanceTwo2 = ((DomanKat) winner2).getDistanceTwo();
				DistanceThree2 = ((DomanKat) winner2).getDistanceThree();
			}
			System.out.println("");
		}
	}
	
	public static void learningAlgorithm4() {
		double distanceFive1 = 0;
		double distanceFive2 = 0;
		for(int i = 0; i < 1000; i++) {
			int round = i + 1;
			System.out.println("Generation " + round + ":");
			Tournament t = new Tournament();
			t.addPlayer(new DomanKat("distanceFive1: " +distanceFive1, distanceFive1));
			t.addPlayer(new DomanKat("distanceFive2: " +distanceFive2, distanceFive2));
			for(int j = 0; j < 5; j++) {
				double distanceFour = 23*Math.random() - 1;
				String name = "other " +distanceFour;
				t.addPlayer(new DomanKat(name, distanceFour));
			}
			ArrayList<Player> winners = t.playTournament(1500, false);
			Player winner = winners.get(winners.size() - 1);
			if(winner instanceof DomanKat) {
				distanceFive1 = ((DomanKat) winner).getDistance5();
			}
			Player winner2 = winners.get(winners.size() - 2);
			if(winner2 instanceof DomanKat) {
				distanceFive2 = ((DomanKat) winner2).getDistance5();
			}
			System.out.println("");
		}
	}
	
	//9.692370767695756, 5.34849110924015
	//9.968747188283503, 4.518723544300553
	//1.9534283694962906, 5.1889914734880715

	public static void learningAlgorithm() {
		double bestRisk = 0.687786;
		double bestOtherRisk = 0.80275;
		double bestRandom = 0.25987;
		double bestProportion = -6.59789;
		double bestOtherProportion = -240.6458;
		double bestThirdProportion = -977.1787;
		
		double bestRisk2 = 0.652555;
		double bestOtherRisk2 = 0.7909;
		double bestRandom2 = 0.22329;
		double bestProportion2 = -5.7912;
		double bestOtherProportion2 = -76.53776;
		double bestThirdProportion2 = -572.2237;

		double bestRisk3 = 0.652555;
		double bestOtherRisk3 = 0.7909;
		double bestRandom3 = 0.290987;
		double bestProportion3 = -5.7912;
		double bestOtherProportion3 = -76.53776;
		double bestThirdProportion3 = -10000;

		for (int i = 0; i < 10000; i++) {
			int round = i + 1;
			System.out.println("Generation " + round + ":");
			Tournament t = new Tournament();
			t.addPlayer(new DomanKat(bestRisk + ", " + bestOtherRisk + ", "
					+ bestRandom + ", " + bestProportion + ", "
					+ bestOtherProportion + ", " + bestThirdProportion,
					bestRisk, bestOtherRisk, bestRandom, bestProportion,
					bestOtherProportion, bestThirdProportion));
			t.addPlayer(new DomanKat(bestRisk2 + ", " + bestOtherRisk2 + ", "
					+ bestRandom2 + ", " + bestProportion2 + ", "
					+ bestOtherProportion2 + ", " + bestThirdProportion2,
					bestRisk2, bestOtherRisk2, bestRandom2, bestProportion2,
					bestOtherProportion2, bestThirdProportion2));
			t.addPlayer(new DomanKat(bestRisk3 + ", " + bestOtherRisk3 + ", "
					+ bestRandom3 + ", " + bestProportion3 + ", "
					+ bestOtherProportion3 + ". " + bestThirdProportion3,
					bestRisk3, bestOtherRisk3, bestRandom3, bestProportion3,
					bestOtherProportion3, bestThirdProportion3));
			for (int j = 0; j < 1; j++) {
				double risk = 0.5 * Math.random() + 0.5;
				double otherRisk = 0.75 * Math.random() + 0.3;
				double random = 0.4 * Math.random();
				double proportion = 1500 * Math.random() - 750;
				double proportion2 = 1500 * Math.random() - 750;
				double proportion3 = 2000 * Math.random() - 1000;
				if (400 < Math.abs(proportion) && Math.abs(proportion) < 650) {
					proportion = 40 * Math.random() - 20;
				}
				if (275 < Math.abs(proportion2) && Math.abs(proportion2) < 350) {
					proportion2 = 150 * Math.random() - 75;
				}
				String name = risk + ", " + otherRisk + ", " + random + ", "
						+ proportion + ", " + proportion2 + ", " + proportion3;
				t.addPlayer(new DomanKat(name, risk, otherRisk, random,
						proportion, proportion2, proportion3));
			}
			ArrayList<Player> winners = t.playTournament(1500, false);
			Player winner = winners.get(winners.size() - 1);
			if (winner instanceof DomanKat) {
				bestRisk = ((DomanKat) winner).getRisk();
				bestOtherRisk = ((DomanKat) winner).getRisk2();
				bestRandom = ((DomanKat) winner).getRandom();
				bestProportion = ((DomanKat) winner).getProportion1();
				bestOtherProportion = ((DomanKat) winner).getProportion2();
				bestThirdProportion = ((DomanKat) winner).getProportion3();

				bestRisk3 = ((DomanKat) winner).getRisk();
				bestOtherRisk3 = ((DomanKat) winner).getRisk2();
				bestRandom3 = ((DomanKat) winner).getRandom();
				bestProportion3 = ((DomanKat) winner).getProportion1();
				bestOtherProportion3 = ((DomanKat) winner).getProportion2();
				bestThirdProportion3 = ((DomanKat) winner).getProportion3();

				if (round % 3 == 0) {
					bestRisk3 = 0.5 * Math.random() + 0.5;
					bestProportion3 = 50 * Math.random() - 25;
				}
				if (round % 3 == 1) {
					bestOtherRisk3 = 0.6 * Math.random() + 0.5;
					bestOtherProportion3 = 500 * Math.random() - 250;
				}
				if (round % 3 == 2) {
					bestRandom3 = 0.4 * Math.random();
					bestThirdProportion3 = 2000 * Math.random() - 1000;
				}
			}
			Player winner2 = winners.get(winners.size() - 2);
			if (winner2 instanceof DomanKat) {
				bestRisk2 = ((DomanKat) winner2).getRisk();
				bestOtherRisk2 = ((DomanKat) winner2).getRisk2();
				bestRandom2 = ((DomanKat) winner2).getRandom();
				bestProportion2 = ((DomanKat) winner2).getProportion1();
				bestOtherProportion2 = ((DomanKat) winner2).getProportion2();
				bestThirdProportion2 = ((DomanKat) winner2).getProportion3();
			}
			System.out.println("");
		}
	}

	private LinkedList<Player> players = new LinkedList<Player>();
	private HashMap<Player, Integer> scores = new HashMap<Player, Integer>();

	public void addPlayer(Player player) {
		players.add(player);
	}

	public HashMap<Player, Integer> getScores() {
		return scores;
	}

	public ArrayList<Player> playTournament(int gamesPerMatchup) {
		return playTournament(gamesPerMatchup, true);
	}

	public ArrayList<Player> playTournament(int gamesPerMatchup, boolean logMe) {
		if (gamesPerMatchup > 10)
			logMe = false;
		Random r = new Random();
		while (players.size() > 0) {
			Player p0 = players.pop();
			for (Player p1 : players) {
				for (int i = 0; i < gamesPerMatchup; i++) {
					Player winner;
					if (r.nextBoolean()) {
						winner = new Game(p0, p1, false, logMe).play();
					} else {
						winner = new Game(p1, p0, false, logMe).play();
					}
					Player loser;
					if (winner == p0)
						loser = p1;
					else
						loser = p0;
					// System.out.println(winner.name + " beat " + loser.name);
					if (scores.containsKey(winner)) {
						scores.put(winner, scores.get(winner) + 1);
					} else {
						scores.put(winner, 1);
					}
					if (!scores.containsKey(loser)) {
						scores.put(loser, 0);
					}
				}
			}
		}
		ArrayList<Player> out = new ArrayList<Player>(scores.keySet());
		Collections.sort(out, new Comparator<Player>() {
			public int compare(Player p0, Player p1) {
				return scores.get(p0) - scores.get(p1);
			}
		});
		for (Player dood : out) {
			double percentWon = 100. * scores.get(dood)
					/ (gamesPerMatchup * (out.size() - 1));
			System.out.println(dood.getName() + ": " + percentWon + "%");
		}
		return out;
	}
}

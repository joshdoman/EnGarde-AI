package players;

import java.util.Arrays;

public class DomanKat2000 extends Player {

	private int[] counter;
	private int[] counter2;
	private double risk;
	private double random;
	private boolean open;
	private double risk2;
	private double proportion1;
	private double proportion2;
	private double proportion3;
	private double distanceMove2;
	private double distanceMove3;
	private double distance4;
	private double distance5;

	public DomanKat2000(String name) {
		super(name);
		this.risk = 0.7188;
		this.risk2 = 0.8168;
		this.random = 0.275485;
		this.open = false;
		this.proportion1 = -5.2709;
		this.proportion2 = 226.485;
		this.proportion3 = -196.620157;
		counter = new int[5];
		counter2 = new int[5];
		this.distanceMove2 = 10.455211424664428;
		this.distanceMove3 = 5.585668023171147;
		this.distance4 = 4.784530160177786;
		this.distance5 = 1.3272038182434724;
	}
	
	public DomanKat2000(String name, double distanceMove2, double distanceMove3) {
		super(name);
		this.risk = 0.7188;
		this.risk2 = 0.8168;
		this.random = 0.275485;
		this.open = false;
		this.proportion1 = -5.2709;
		this.proportion2 = 226.485;
		this.proportion3 = -196.620157;
		counter = new int[5];
		counter2 = new int[5];
		this.distanceMove2 = distanceMove2;
		this.distanceMove3 = distanceMove3;
	}
	
	public DomanKat2000(String name, double distance5) {
		super(name);
		this.risk = 0.7188;
		this.risk2 = 0.8168;
		this.random = 0.275485;
		this.open = false;
		this.proportion1 = -5.2709;
		this.proportion2 = 226.485;
		this.proportion3 = -196.620157;
		counter = new int[5];
		counter2 = new int[5];
		this.distanceMove2 = 10.455211424664428;
		this.distanceMove3 = 5.585668023171147;
		this.distance4 = 4.784530160177786;
		this.distance5 = distance5;
	}

	// 0.7188068737200484, 0.8168048951519049, 0.2754850957575389, -5.270942745505025, 226.48509144059676, -196.62015701893074

	public DomanKat2000(String name, double risk, double risk2, double random,
			double proportion1, double proportion2, double proportion3) {
		super(name);
		this.risk = risk;
		this.risk2 = risk2;
		this.random = random;
		this.open = false;
		this.proportion1 = proportion1;
		this.proportion2 = proportion2;
		this.proportion3 = proportion3;
	}

	public double getRisk2() {
		return risk2;
	}

	public double getRisk() {
		return risk;
	}

	public double getRandom() {
		return random;
	}

	public double getProportion1() {
		return proportion1;
	}

	public double getProportion2() {
		return proportion2;
	}

	public double getProportion3() {
		return proportion3;
	}
	
	public double getDistanceTwo() {
		return distanceMove2;
	}
	
	public double getDistanceThree() {
		return distanceMove3;
	}
	
	public double getDistance4() {
		return distance4;
	}
	
	public double getDistance5() {
		return distance5;
	}

	@Override
	public int move() {
		// TODO Auto-generated method stub
		updateCounter(getLastMove());
		updateCounter2();
		/**System.out.print("Counter: ");
		for (int i = 0; i < counter.length; i++) {
			System.out.print(counter[i] + ", ");
		}
		System.out.println("");
		System.out.print("Hand: ");
		int[] hand = getHand();
		for (int i = 0; i < hand.length; i++) {
			System.out.print(hand[i] + ", ");
		}
		System.out.println("");*/
		if (killThem(getDistance()))
			return getDistance();
		if (Math.abs(getDistance()) > 10)
			return opening();
		if (getTurnsRemaining() > 5)
			return middle(riskIt1(), riskIt2(), randomIt());
		if (getTurnsRemaining() > 4)
			return endGame2(distance5);
		if (getTurnsRemaining() > 3)
			return endGame2(distance4);
		if (getTurnsRemaining() > 2)
			return endGame2(distanceMove3);
		if (getTurnsRemaining() > 1)
			return endGame2(distanceMove2);
		return endGame();
	}

	public void start() {
		this.counter = new int[5];
		this.counter2 = new int[5];
	}

	// move as far as possible toward the center
	private int endGame() {
		int[] hand = getPossibleMoves();
		if (hand.length == 0)
			return 0;
		if (direction() == 1)
			return hand[hand.length - 1];
		return hand[0];
	}

	private double riskIt1() {
		return risk - scen() / proportion1;
	}

	private double riskIt2() {
		return risk2 - scen() / proportion2;
	}

	private double randomIt() {
		return random - scen() / proportion3;
	}

	// do the largest move that clears the risk level
	// if no move clears the risk level, do the move with the least amount of
	// risk
	private int middle(double risk1, double risk2, double thisRandom) {
		int[] hand = getPossibleMoves();
		double[] chance = new double[hand.length];
		double[] chance2 = chance;
		double[] otherChance = new double[hand.length];
		for (int i = 0; i < hand.length; i++) {
			int distance = Math.abs(getDistance() - hand[i]);
			chance[i] = chanceLive(distance);
			otherChance[i] = chanceLive2(distance);
			//if (distance > 5) { 
				//System.out.println(hand[i]+ "--> Chance1: " +chance[i]+ " Chance2: " +otherChance[i]+ " Counter: 0"); 
			//} else { 
				//System.out.println(hand[i]+ "--> Chance1: " +chance[i]+ " Chance2: " +otherChance[i]+ " Counter:" +counter2[distance-1]);
			//} 
		}
		boolean finished = false;
		boolean firstTime = true;
		int[] otherMoves = new int[hand.length];
		int otherPos = 0;
		int safe = 0;
		for (int i = 0; i < hand.length; i++) {
			if (chance[i] >= risk1 || otherChance[i] > risk2) {
				if (otherChance[i] > risk2 && chance[i] < risk1) {
					otherMoves[otherPos] = hand[i];
					otherPos++;
				}
				if (firstTime) {
					safe = hand[i];
					firstTime = false;
				}
				safe = bestMiddle(safe, hand[i], thisRandom);
				finished = true;
			}
		}
		if (!finished) {
			Arrays.sort(chance2);
			for (int i = 0; i < chance.length; i++) {
				if (chance2[chance2.length - 1] == chance[i])
					safe = hand[i];
			}
		}
		/**
		 * for (int i = 0; i < hand.length; i++) { if (otherMoves[i] == safe) {
		 * for (int k = 0; k < 100; k++) { System.out.println("YAY!!!"); }
		 * System.out.println(" "); } } // System.out.println("");
		 */
		updateCounter(safe);
		return safe;
	}

	private int bestMiddle(int previous, int newVal, double thisRandom) {
		int distance0 = Math.abs(getDistance() - previous);
		int distance1 = Math.abs(getDistance() - newVal);
		int valPrevious = getValue(Math.abs(previous)) / Math.abs(previous);
		int valNew = getValue(Math.abs(newVal)) / Math.abs(newVal);
		if (distance0 * Math.pow(valPrevious, -1 * thisRandom) > distance1
				* Math.pow(valNew, -1 * thisRandom))
			return newVal;
		else
			return previous;
	}

	// chance you will survive at given distance from opponent
	private double chanceLive(int distance) {
		if (distance > 5)
			return 1;
		int[] myHand = getHand();
		int numberPlayed = counter[distance - 1];
		for (int i = 0; i < 5; i++) {
			if (myHand[i] == distance)
				numberPlayed++;
		}
		int numberLeft = 5 - numberPlayed;
		if (numberLeft < 0)
			numberLeft = 0;
		/**int deckSize = getTurnsRemaining();
		double chanceInDeck = 1;
		for (int i = 0; i < 5; i++) {
			chanceInDeck = chanceInDeck * (deckSize + 5 - numberLeft - i)
					/ (deckSize + 5 - i);
		}
		//return chanceInDeck; */
		return multiplier(numberLeft);
	}
	
	private double multiplier(int numberLeft) {
		if(numberLeft > getTurnsRemaining()) return 0;
		double chanceInDeck = 1;
		for (int i = 0; i < 5; i++) {
			chanceInDeck = chanceInDeck * (getTurnsRemaining() + 5 - numberLeft - i)
					/ (getTurnsRemaining() + 5 - i);
		}
		return chanceInDeck;
	}

	private int previousLocation() {
		return getOpponentLocation() - getLastMove();
	}

	// returns the best move that your opponent could have made
	private int bestMove() {
		int[] moves = new int[10];
		int bestMove = getLastMove();
		int previousDistance = Math.abs(getDistance());
		for (int i = 0; i < moves.length; i++) {
			if (i < 5)
				moves[i] = i - 5;
			else
				moves[i] = i - 4;
		}
		for (int i = 0; i < moves.length; i++) {
			int distance = previousLocation() - getLocation();
			int newDistance = Math.abs(distance + moves[i]);
			if (newDistance > 5) {
				if (previousDistance > newDistance) {
					bestMove = moves[i];
					previousDistance = newDistance;
				}
			}
		}
		return bestMove;
	}

	private boolean updateOpponentCounter() {
		int difference = Math.abs(bestMove() - getLastMove());
		int number = 0;
		int bestMove = Math.abs(bestMove());
		int lastMove = Math.abs(getLastMove());
		if (difference < 2)
			return false;
		if (bestMove() / bestMove == getLastMove() / lastMove) {
			for (int i = 0; i < Math.abs(bestMove() - getLastMove()) - 1; i++) {
				if (lastMove < bestMove)
					counter2[i + lastMove + 1] = 1;
				else
					counter2[i] = 1;
			}
		} else {
			if (lastMove - bestMove == 1)
				number = bestMove;
			else
				number = lastMove - 2;
			for (int i = 0; i < number; i++) {
				counter2[i] = 1;
			}
		}
		return true;
	}

	private boolean updateCounter2() {
		if (getTurnsRemaining() == 15)
			return false;
		for (int i = 0; i < counter2.length; i++) {
			if (counter2[i] != 0)
				counter2[i]++;
		}
		updateOpponentCounter();
		return true;
	}

	private double chanceLive2(int distance) {
		if (distance > 5)
			return 1;
		if (counter2[distance - 1] == 0)
			return 0;
		double chanceLive = 1;
		int[] myHand = getHand();
		int numberPlayed = counter[distance - 1];
		for (int i = 0; i < 5; i++) {
			if (myHand[i] == distance)
				numberPlayed++;
		}
		int numberRemaining = 5 - numberPlayed;
		for (int i = 0; i < counter2[distance - 1]; i++) {
			chanceLive = chanceLive
					* (getTurnsRemaining() + 2
							* (counter2[distance - 1] - i - 1) - numberRemaining)
					/ (getTurnsRemaining() + 2 * (counter2[distance - 1] - i - 1));
		}
		return chanceLive;
	}

	private boolean updateCounter(int lastMove) {
		if (getTurnsRemaining() == 15)
			return true;
		for (int i = 0; i < 5; i++) {
			if (Math.abs(lastMove) == i + 1) {
				counter[i]++;
				return true;
			}
		}
		return false;
	}

	private boolean updateCounter(int lastMove, boolean count) {
		for (int i = 0; i < 5; i++) {
			if (Math.abs(lastMove) == i + 1) {
				counter[i]++;
				return true;
			}
		}
		return false;
	}

	private int direction() {
		return getDistance() / Math.abs(getDistance());
	}

	private int getDistance() {
		return getOpponentLocation() - getLocation();
	}

	private int getValue(int n) {
		int[] hand = getHand();
		int count = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] == n)
				count++;
		}
		return count * n;
	}

	// if you can kill them, do it
	private boolean killThem(int distance) {
		int[] hand = getHand();
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] == Math.abs(distance))
				return true;
		}
		return false;
	}

	private double scen() {
		int myDistance = 0;
		int theirDistance = 0;
		if (direction() > 0) {
			myDistance = getLocation();
			theirDistance = 22 - getOpponentLocation();
		} else {
			myDistance = 22 - getLocation();
			theirDistance = getOpponentLocation();
		}
		return (myDistance - theirDistance) / getTurnsRemaining();
	}

	private int endGame2(double distance) {
		int middleMove = middle(riskIt1(), riskIt2(), randomIt());
		int myDistance = 0;
		int newLocation = getLocation() + middleMove;
		if (direction() > 0) {
			myDistance = newLocation;
		} else {
			myDistance = 22 - newLocation;
		}
		if (myDistance > distance)
			return middleMove;

		int[] myHand = getPossibleMoves();
		if (myHand.length == 0)
			return middleMove;

		int[] hand = getHand();
		int[] movesLeft = new int[5];
		for (int i = 0; i < 5; i++) {
			int numberPlayed = counter[i];
			for (int j = 0; j < 5; j++) {
				if (hand[j] == i + 1)
					numberPlayed++;
			}
			int numberLeft = 5 - numberPlayed;
			if (numberLeft < 0)
				numberLeft = 0;
			movesLeft[i] = numberLeft;
		}
		double[] chanceLose = new double[myHand.length];
		double[] chanceLoseCopy = new double[myHand.length];
		//System.out.print("Counter: "); for (int i = 0; i < counter.length; i++) { 
			//System.out.print(counter[i] + ", "); 
			//} 
		//System.out.println("");
		//System.out.print("Hand: "); for (int i = 0; i < hand.length; i++) {
		//System.out.print(hand[i] + ", "); } System.out.println("");
		//System.out.print("movesLeft: "); for (int i = 0; i < 5; i++) {
		//System.out.print(movesLeft[i] + ", "); } System.out.println("");
		 
		for (int i = 0; i < myHand.length; i++) {
			chanceLose[i] = willLose(getLocation() + myHand[i], movesLeft);
			chanceLoseCopy[i] = willLose(getLocation() + myHand[i], movesLeft);
			//System.out.println(myHand[i] + " --> chanceLose: " + chanceLose[i]);
		}
		Arrays.sort(chanceLoseCopy);
		//System.out.println("ChanceMin = " + chanceLoseCopy[0]);
		if (chanceLoseCopy[0] == 1)
			return middleMove;
		/**
		 * System.out.print("chanceLoseArray: "); for (int i = 0; i <
		 * chanceLose.length; i++) { System.out.print(chanceLose[i] + ", "); }
		 * System.out.println("");
		 */
		if (direction() < 0) {
			for (int i = 0; i < myHand.length; i++) {
				if (chanceLose[i] == chanceLoseCopy[0]) {
					// System.out.println("chance = " + chanceLoseCopy[0]);
					return myHand[i];
				}
			}
		}
		if (direction() > 0) {
			for (int i = 0; i < myHand.length; i++) {
				if (chanceLose[myHand.length - i - 1] == chanceLoseCopy[0]) {
					// System.out.println("chance = " + chanceLoseCopy[0]);
					return myHand[myHand.length - i - 1];
				}
			}
		}
		return middleMove;
	}

	private double willLose(int location, int[] movesLeft) {
		double max = 1 - chanceLive(Math.abs(getOpponentLocation() - location));
		if(max == 1) return max;
		for (int i = -5; i < 5; i++) {
			if (i == 0)
				continue; // illegal move
			if (direction() > 0 && getOpponentLocation() + i - location <= 0)
				continue; // illegal move
			if (direction() < 0 && location - (getOpponentLocation() + i) <= 0)
				continue; // illegal move
			if (movesLeft[Math.abs(i) - 1] == 0)
				continue;
			if (direction() > 0) {
				if ((22 - getOpponentLocation() - i) > location) {
					if (movesLeft[Math.abs(i) - 1] > 2) {
						return 1;
					} else {
						max = Math.max(max, 1 - multiplier(movesLeft[Math.abs(i) - 1]));
					}
				}
			} else {
				if (getOpponentLocation() + i > 22 - location) {
					if (movesLeft[Math.abs(i) - 1] > 2) {
						return 1;
					} else {
						max = Math.max(max, 1 - multiplier(movesLeft[Math.abs(i) - 1]));
					}
				}
			}
		}
		return max;
		// return 0;
	}

	// first few moves of the game (zero chance that you will die)
	// make the best move that best diversifies your hand and moves you the
	// farthest
	private int opening() {
		if (open) {
			int max = 0;
			for (int i = 1; i < 6; i++) {
				max = Math.max(max, getValue(i));
			}
			for (int i = 5; i > 3; i--) {
				if (getValue(i) == max) {
					updateCounter(direction() * i);
					return direction() * i;
				}
			}
			int[] hand = getPossibleMoves();
			for (int i = 5; i > 0; i--) {
				for (int j = 0; j < hand.length; j++) {
					if (hand[j] == i) {
						updateCounter(hand[j]);
						return direction() * hand[j];
					}
				}
			}
		} else {
			int[] hand = getPossibleMoves();
			int move = 0;
			if (direction() > 0)
				move = hand[hand.length - 1];
			else
				move = hand[0];
			updateCounter(move, true);
			return move;
		}
		return 0;
	}
}

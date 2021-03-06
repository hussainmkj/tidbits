/**
  * Don Mills CI
  * ICS4U H. Strelkovska
  * String Game
  * Scrambled Word Slide Puzzle
  *
  * Hussain Jasim #310372974
  *
  * This work is licensed under the Creative Commons Attribution-NonCommercial-ShareAlike 3.0 Unported License.
  * To view a copy of this license, visit
  * http://creativecommons.org/licenses/by-nc-sa/3.0/
  * or send a letter to
  * Creative Commons, 444 Castro Street, Suite 900, Mountain View, California, 94041, USA.
**/

package slide_puzzle;

import java.util.Random;

// This class holds the puzzle state
// and provides methods for manipulating it.
public class Puzzle {
	private String currentConfiguration, initialConfiguration;
	private PuzzleWord puzzleWord;
	public enum Direction {UP, DOWN, LEFT, RIGHT }

	public Boolean isComplete() {
		return currentConfiguration.equals(puzzleWord.getWord());
	}
	public PuzzleWord getPuzzleWord() {
		return puzzleWord;
	}
	public String getInitialConfiguration() {
		return initialConfiguration;
	}
	public String getCurrentConfiguration() {
		return currentConfiguration;
	}

	// returns a string with the characters swapped at the given indices
	public String swapCharacters(String str, int first, int second) {
		char[] swapped = str.toCharArray();
		char tmp = swapped[first];
		swapped[first] = swapped[second];
		swapped[second] = tmp;
		return new String(swapped);
	}

// slides a tile into the empty space based on the provided direction
	public void slideTile(Direction dir) {
		int emptyPosition = currentConfiguration.indexOf(" ");
		int tilePosition;
		// determine the position of the tile to slide by working backwards using the provided direction
		// throws IllegalArguementException if the move is invalid
		switch(dir) {
			case UP:
				tilePosition = emptyPosition+puzzleWord.getWidth();
				break;
			case DOWN:
				tilePosition = emptyPosition-puzzleWord.getWidth();
				break;
			case LEFT:
				tilePosition = emptyPosition+1;
				// if the empty space is on the right edge, it is impossible to slide a tile left into it
				if((emptyPosition + 1)%puzzleWord.getWidth() == 0)
					throw new IllegalArgumentException();
				break;
			case RIGHT:
				tilePosition = emptyPosition-1;
				// if the empty space is on the left edge, it is impossible to slide a tile right into it
				if(emptyPosition%puzzleWord.getWidth() == 0)
					throw new IllegalArgumentException();
				break;
			default:
				throw new IllegalArgumentException();
		}
		// prevent sliding tiles off the horizontal edges
		if(tilePosition < 0 || tilePosition >= currentConfiguration.length())
			throw new IllegalArgumentException();
		currentConfiguration = swapCharacters(currentConfiguration, tilePosition, emptyPosition);
	}

	// print out the current puzzle configuration in a tabulated format
	public void displayPuzzle() {
		String horizontalSeparator = "+";
		for(int i = 0; i < puzzleWord.getWidth(); i++) 
			horizontalSeparator += "-+";
		for(int i = 0; i < puzzleWord.getHeight(); i++) {
			System.out.println(horizontalSeparator);
			String substr = currentConfiguration.substring(i*puzzleWord.getWidth(), puzzleWord.getWidth()*(i+1));
			for(char c : substr.toCharArray())
				System.out.print("|" + c);
			System.out.println("|");
		}
		System.out.println(horizontalSeparator);
	}

	public Puzzle(PuzzleWord puzzleWord) {
		Random rnd = new Random();
		this.puzzleWord = puzzleWord;
		initialConfiguration = puzzleWord.getWord();
		// scramble the initial configuration string
		for(int i = 0; i < initialConfiguration.length()-1; i++)
			initialConfiguration = swapCharacters(initialConfiguration, i, rnd.nextInt(initialConfiguration.length()-i-1)+i);
		currentConfiguration = initialConfiguration + " ";
	}
}

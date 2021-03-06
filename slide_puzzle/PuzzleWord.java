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

// Class to contain metadata about puzzle configurations.
class PuzzleWord {
	private String word, hint;
	private int width, height;
	public String getWord() {
		return word;
	}
	public String getHint() {
		return hint;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public PuzzleWord(String word, String hint, int width, int height) {
		this.word = word;
		this.hint = hint;
		this.width = width;
		this.height = height;
	}
}

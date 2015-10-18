package model;
/**
 * 
 * @author Yicun Zeng
 * Define room types and symbol in the text view.
 *
 */
public enum RoomType {
	SLIME('S'),PIT('P'),BLOOD('B'),GOOP('G'),WUMPUS('W'),GROUND('\u0020');
	
	char symbol;
	
	RoomType(char symbol){
		this.symbol = symbol;
	}
	
	public char RoomSymbol(){
		return symbol;
	}
}

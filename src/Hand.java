import java.util.ArrayList;

public class Hand extends Deck {
	
	public Hand()
	{
		
	}

	//interaction guess between players
	public void showCards() 
	{
	
		//loop for asking about final guess
	}	
	
	
	
	@Override
	public String toString() { // prints the hand
		String output = "no clue what's going on";
		return output;
	}



	@Override
	public void deal(ArrayList<Player> p) {
		ArrayList<String> newDeck = shuffle();
		if(newDeck.size()/p.size() == 0) {
			//shuffle deck evenly
		} else if (newDeck.size()/p.size()!= 0) {
			// mod shuffle deck
		}  
		
	}
	
	
}

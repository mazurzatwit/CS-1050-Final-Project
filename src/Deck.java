import java.util.ArrayList;
import java.util.Random;

public abstract class Deck extends Card{
	private Random rand = new Random();
	
	public void Solution()
	{
		 Object[] solution = new Object[3];
        int num = rand.nextInt(characters.size()); 
        solution[0] = characters.get(num);
        characters.remove(num);
       
        num = rand.nextInt(rooms.size());
        solution[1] = rooms.get(num);
        rooms.remove(num);
        
        num = rand.nextInt(weapons.size());
        solution[2] = weapons.get(num);
        weapons.remove(num);
	}
	
	public ArrayList<String> fillDeck()
	{
		ArrayList<String> deck = new ArrayList<>();
		String[] cString = new String[5];
		for(int i = 0; i < characters.size(); i++)
		{
			cString[i] = characters.get(i).toString();
			deck.add(cString[i]);
		}
		String[] rString = new String[8];
		for(int j = 0; j < rooms.size(); j++)
		{
			rString[j] = rooms.get(j).toString();
			deck.add(rString[j]);
		}
		String[] wString = new String[5];
		for(int k = 0; k < weapons.size(); k++)
		{
			wString[k] = weapons.get(k).toString();
			deck.add(wString[k]);
		}
		return deck;
	}
	
	public ArrayList<String> shuffle() 
	{
		ArrayList<String> deck = fillDeck();
		ArrayList<String> shuffledDeck = new ArrayList<>();
		int num = 0;
		for(int i = 0; i < deck.size(); i++)
		{
			num = rand.nextInt(deck.size());
			shuffledDeck.add(i, deck.get(num));
			deck.remove(num);
		}
		return shuffledDeck;
	}
	
	public abstract void deal(int size, ArrayList<Player> p);

}

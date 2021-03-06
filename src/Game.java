import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Game extends Application{
	double oX, oY;
	double oTX, oTY;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {	
			
		Scanner s = new Scanner(System.in);
		int increment = 0;
		
		System.out.printf("Welcome to Clue! %n%n");
		System.out.printf("PUBLIC SERVICE ANNOUCEMENT: Please have a notebook or a small piece of paper handy to keep track of your guesses. Thank you! %n%n%n");
		System.out.printf("Enter an amount of players between 3-6: ");
		int numPlayers = s.nextInt();
		s.nextLine();
		System.out.println();
		
		if(numPlayers<3 || numPlayers>6) {
			System.out.printf("You have entered an incorrect amount of players. Please start the game over.%n");
			System.exit(0);
		} 
	
		Card c = new Card();
		GameManager gm = new GameManager(numPlayers);
		
		String[] allNames = new String[numPlayers];
		for(int j = 1; j <= numPlayers; j++)
		{
			System.out.printf("Enter player #%d name: ", j);
			String player = s.nextLine();
			allNames[j-1] = player;
			System.out.printf("%n");
		}
		String[] finalnames = gm.playerName(allNames);
		
		System.out.printf("%n%n%n");
		String[] allCharacters = new String[numPlayers];
		for(int k = 1; k <= numPlayers; k++)
		{
			System.out.printf("Choose a character: %n"
					+ "Mrs. White (White) %n"
					+ "Professor Plum (Purple) %n"
					+ "Colonel Mustard (Yellow)%n"
					+ "Mrs. Peacock (Blue) %n"
					+ "Miss Scarlet (Red) %n"
					+ "Mr. Green (Green) %n%n");
			System.out.printf("Player %d: Enter the character you want to be from the above list (NAME ONLY): ", k);
			String character = s.nextLine();
			allCharacters[k-1] = character;
			System.out.printf("%n%n%n");
		}
		String[] finalCharacters = gm.playerCharacter(allCharacters);
		
		ArrayList<Player> players = gm.deal(finalnames, finalCharacters);
		
		Pane board = new Pane();
		board.setMinSize(500, 500);
		board.setStyle("-fx-background-color: #CD853F;");
		Button diningRoom = new Button("Dining Room");
		diningRoom.setStyle("-fx-background-color: #FFFACD;");
		diningRoom.setMaxHeight(200);
		diningRoom.setMaxWidth(200); //supposed to change button size
		diningRoom.setOnAction(e -> {System.out.printf("You are now in the dining room.%n");});
		Button hall = new Button("Hall");
		hall.setStyle("-fx-background-color: #B0E0E6;");
		hall.setLayoutX(0);
		hall.setLayoutY(250);
		hall.setOnAction(e -> {System.out.printf("You are now in the hall.%n");});
		Button library = new Button("Library");
		library.setStyle("-fx-background-color: #BC8F8F;");
		library.setLayoutX(0);
		library.setLayoutY(500);
		library.setOnAction(e -> {System.out.printf("You are now in the library.%n");});
		Button ballroom = new Button("Ballroom");
		ballroom.setStyle("-fx-background-color: #B22222;");
		ballroom.setLayoutX(160);
		ballroom.setLayoutY(0);
		ballroom.setOnAction(e -> {System.out.printf("You are now in the ballroom.%n");});
		Button study = new Button("Study");
		study.setStyle("-fx-background-color: #008080;");
		study.setLayoutX(500);
		study.setLayoutY(0);
		study.setOnAction(e -> {System.out.printf("You are now in the study.%n");});
		Button billardRoom = new Button("Billard Room");
		billardRoom.setStyle("-fx-background-color: #87CEFA;");
		billardRoom.setLayoutX(250);
		billardRoom.setLayoutY(500);
		billardRoom.setOnAction(e -> {System.out.printf("You are now in the billard room.%n");});
		Button conservatory = new Button("Conservatory");
		conservatory.setStyle("-fx-background-color: #9ACD32;");
		conservatory.setLayoutX(320);
		conservatory.setLayoutY(0);
		conservatory.setOnAction(e -> {System.out.printf("You are now in the conversatory.%n");});
		Button kitchen = new Button("Kitchen");
		kitchen.setStyle("-fx-background-color: #B0C4DE;");
		kitchen.setLayoutX(500);
		kitchen.setLayoutY(500);
		kitchen.setOnAction(e -> {System.out.printf("You are now in the kitchen.%n");});
		Button lounge = new Button("Lounge");
		lounge.setStyle("-fx-background-color: #E6E6FA;");
		lounge.setLayoutX(500);
		lounge.setLayoutY(250);
		lounge.setOnAction(e -> {System.out.printf("You are now in the lounge.%n");});
		board.getChildren().addAll(diningRoom, hall, library, ballroom, study, billardRoom, conservatory, kitchen, lounge);
		
		EventHandler<MouseEvent> pressed = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent m)
			{
				oX = m.getSceneX();
				oY = m.getSceneY();
				oTX = ((Circle)(m.getSource())).getTranslateX();
				oTY = ((Circle)(m.getSource())).getTranslateY();
			}
		};
		
		EventHandler<MouseEvent> dragged = new EventHandler<MouseEvent>()
		{
			@Override
			public void handle(MouseEvent m)
			{
				double newOX = m.getSceneX() - oX;
				double newOY = m.getSceneY() - oY;
				double newOTX = oTX + newOX;
				double newOTY = oTY + newOY;
				
				((Circle)(m.getSource())).setTranslateX(newOTX);
				((Circle)(m.getSource())).setTranslateY(newOTY);
			}
		};
		
		//character Circles
		Circle white = new Circle();
		white.setCenterX(35);
		white.setCenterY(125);
		white.setRadius(25);
		white.setStroke(Color.BLACK);
		white.setFill(Color.GHOSTWHITE);
		white.setOnMousePressed(pressed);
		white.setOnMouseDragged(dragged);
		
		Circle plum = new Circle();
		plum.setCenterX(35);
		plum.setCenterY(375);
		plum.setRadius(25);
		plum.setStroke(Color.BLACK);
		plum.setFill(Color.PURPLE);
		plum.setOnMousePressed(pressed);
		plum.setOnMouseDragged(dragged);
		
		Circle mustard = new Circle();
		mustard.setCenterX(525);
		mustard.setCenterY(125);
		mustard.setRadius(25);
		mustard.setStroke(Color.BLACK);
		mustard.setFill(Color.GOLDENROD);
		mustard.setOnMousePressed(pressed);
		mustard.setOnMouseDragged(dragged);
		
		Circle peacock = new Circle();
		peacock.setCenterX(525);
		peacock.setCenterY(375);
		peacock.setRadius(25);
		peacock.setStroke(Color.BLACK);
		peacock.setFill(Color.ROYALBLUE);
		peacock.setOnMousePressed(pressed);
		peacock.setOnMouseDragged(dragged);
		
		Circle scarlet = new Circle();
		scarlet.setCenterX(155);
		scarlet.setCenterY(495);
		scarlet.setRadius(25);
		scarlet.setStroke(Color.BLACK);
		scarlet.setFill(Color.RED);
		scarlet.setOnMousePressed(pressed);
		scarlet.setOnMouseDragged(dragged);
		
		Circle green = new Circle();
		green.setCenterX(420);
		green.setCenterY(495);
		green.setRadius(25);
		green.setStroke(Color.BLACK);
		green.setFill(Color.OLIVEDRAB);
		green.setOnMousePressed(pressed);
		green.setOnMouseDragged(dragged);
		
		board.getChildren().addAll(white, plum, mustard, peacock, scarlet, green);
		
		Scene s1 = new Scene(board);
		arg0.setTitle("Welcome to Clue!");
		arg0.setScene(s1);
		arg0.show();
		
		boolean alpha = false;
		String ans = "";
		TextField text = new TextField();
		text.relocate(210, 150);
		Button clear = new Button("Enter");
		clear.setLayoutX(265);
		clear.setLayoutY(350);
		clear.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e)
			{
				text.clear();
			}
		});
		TextField character = new TextField();
		character.setPromptText("Enter Character Guess");
		TextField weapon = new TextField();
		weapon.setPromptText("Enter Weapon Guess");
		TextField room = new TextField();
		room.setPromptText("Enter Room Guess");
		character.relocate(210, 200);
		weapon.relocate(210,250);
		room.relocate(210,300);
		Label label1 = new Label("");
		label1.relocate(200, 125);
		board.getChildren().addAll(label1, text, character, weapon, room, clear);
		
		do 
		{
			for(Player p: players) 
			{
				label1.setText("Do you want to move rooms?(y/n)");
				ans = text.getText();	
				if(ans.equals("y")) 
				{
					label1.setText("Drag your designated circle your chosen roon and click the button of that room.");
				}
				label1.setText("Do you want to make a FINAL GUESS? (y/n)");
				ans=text.getText();
				
				
				if(ans.equals("y")) 
				{
					String[] solution = gm.getSolution();
					ArrayList<String> guesses = new ArrayList<>();
					guesses.add(character.getText());
					guesses.add(weapon.getText());
					guesses.add(room.getText());
					int counter = 0;
					for(int n=0; n < solution.length; n++)
					{
						String curr = solution[n];
						for(int o=0; o< guesses.size(); o++)
						{
							if(curr.equals(guesses.get(o)))
							{
								counter++;
							}
						}
					}
					if(counter == 3)
					{
						label1.setText("Correct! You have done did it, Congrats Sherlock!!");
						System.exit(0);
					}
					else if(counter < 3)
					{
						label1.setText("Wrong! Sorry! You have lost!");
						System.exit(0);
					}
				} else {
					label1.setText("Who would you like to ask? Type players name: ");
					ans = text.getText();
					ArrayList<String> guesses = new ArrayList<>();
					Hand guessingH = new Hand();
					for(int a = 0; a < players.size(); a++)
					{
						if(ans.equals(players.get(a).getName()))
						{
							guessingH = players.get(a).getHand();
						}
					}
					int counter=0;
					
					guesses.add(character.getText());
					guesses.add(weapon.getText());
					guesses.add(room.getText());
					for(int w = 0; w < guesses.size(); w++)
					{
						String curr = guesses.get(w);
						for(int z = 0; z < guessingH.handLength(); z++)
						{
							if(curr.equals(guessingH.showCards(z)))
								System.out.printf("Card Match: %s%n", guessingH.showCards(z));
							else
								counter++;
						}
					}
					if(counter == (guessingH.handLength()*3))
					{
						System.out.printf("No Matches!");
					}	
				}
			}
			increment++;
		} while(increment<=5);
		
	}
}

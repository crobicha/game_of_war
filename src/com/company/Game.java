package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


class Game {

    private List<Card> startingDeck = new ArrayList<Card>();

    private List<Player> playerList = new ArrayList<Player>();
    private Player p0 = new Player("p0");
    private Player p1 = new Player("p1");



    public Game() {
        //initPlayers();
        initGame();
        dealDeck();
        startGameplay();
    }



    private void initGame() {

        // Add our 2 players
        playerList.add(p0);
        playerList.add(p1);

        for(int suit = 0; suit < 4; suit++) {
            for(int value = 2; value <= 7; value++) { // 14; value++) {
                Card card = new Card(value, suit);

                startingDeck.add(card);
            }
        }
    }


    private void dealDeck() {
        // Deal a random card to each player
        // End result should be the deck dealt evenly between all players
        // Alternatively we could sort the deck initially and then just deal straight

        Random rand = new Random();

        while(startingDeck.size() > 0) {

            // Alternate dealing between player 0 and 1
            Player currentPlayer = playerList.get((startingDeck.size() % playerList.size()));

            int randomCardIndex = rand.nextInt(startingDeck.size()); // Check for off-by-one error

            //int randomCardIndex = Math.random(0, startingDeck.size());
            Card randomCard = startingDeck.get(randomCardIndex);

            currentPlayer.addCard(randomCard);
//            System.out.println("Gave card " + randomCard + " to " + currentPlayer);

            startingDeck.remove(randomCardIndex);
        }
    }

    private void startGameplay() {

        Player loser = null;

        while( loser == null ) {
            takeTurn();
            loser = checkLoser();
        }

        System.out.println("Loser: " + loser.toString());
    }

    private Player getPlayer(int index) {
        if(index == 0) {
            return p0;
        }
        else if(index == 1) {
            return p1;
        }

        return null;
    }

    private void takeTurn() {

        List<Card> cardsInPlay = new ArrayList<Card>();

        do {
            // compare the two card values
            Card p0Card = p0.drawTop();
            Card p1Card = p1.drawTop();

            System.out.println("Starting turn: " + p0Card + " vs " + p1Card);

            cardsInPlay.add(p0Card);
            cardsInPlay.add(p1Card);

            // If one card is higher, that player takes both cards
            if(p0Card.getValue() > p1Card.getValue()) { // p0 wins
                p0.addCard(cardsInPlay);
                System.out.println(p0 + " wins, new count = " + p0.getDeckSize());
                return;
            }
            else if(p0Card.getValue() < p1Card.getValue()) { // player 1 wins
                p1.addCard(cardsInPlay);
                System.out.println(p1 + " wins, new count = " + p1.getDeckSize());
                return;
            }

            System.out.println("Tiebreaker");

            // If there is a tie, add a single card from each player (face-down)
            cardsInPlay.add(p0.drawTop());
            cardsInPlay.add(p1.drawTop());

            // Re-run the comparison, and the winner will take all cards involved
        } while( checkLoser() == null);
    }

    // The game ends when a player has lost the game
    private Player checkLoser() {

        if(p0.getDeckSize() <= 0) {
            return p1;
        }
        else if(p1.getDeckSize() <= 0) {
            return p0;
        }

        return null;
    }
}
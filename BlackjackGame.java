import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Representa una carta en el juego de Blackjack.
 */
class Card {
    private int value; // Valor de la carta

    /**
     * Constructor de la clase Card.
     * @param value Valor de la carta, de 1 a 9.
     */
    public Card(int value) {
        this.value = value;
    }

    /**
     * Retorna el valor de la carta.
     * @return Valor de la carta.
     */
    public int getValue() {
        return value;
    }
}

/**
 * Simula un mazo de cartas para el juego.
 */
class Deck {
    private Random random = new Random(); // Generador de números aleatorios

    /**
     * Genera una carta aleatoria con valor entre 1 y 9.
     * @return Una carta con un valor aleatorio.
     */
    public Card drawCard() {
        int value = random.nextInt(9) + 1; // Genera un valor aleatorio de 1 a 9
        return new Card(value);
    }
}

/**
 * Modela un jugador en el juego de Blackjack.
 */
class Player {
    private String name; // Nombre del jugador
    private List<Card> hand = new ArrayList<>(); // Mano del jugador

    /**
     * Constructor de la clase Player.
     * @param name Nombre del jugador.
     */
    public Player(String name) {
        this.name = name;
    }

    /**
     * Agrega una carta a la mano del jugador.
     * @param card La carta a agregar.
     */
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    /**
     * Calcula y retorna el valor total de la mano del jugador.
     * @return Valor total de la mano.
     */
    public int getHandValue() {
        int totalValue = 0;
        for (Card card : hand) {
            totalValue += card.getValue();
        }
        return totalValue;
    }

    /**
     * Muestra las cartas en la mano del jugador y su valor total.
     */
    public void printHand() {
        System.out.print(name + "'s hand: ");
        for (Card card : hand) {
            System.out.print(card.getValue() + " ");
        }
        System.out.println("(Total: " + getHandValue() + ")");
    }
}

/**
 * Clase principal que maneja el juego de Blackjack.
 */
public class BlackjackGame {
    private Deck deck = new Deck(); // Mazo de cartas
    private Player player; // Jugador
    private Player dealer = new Player("Dealer"); // Dealer (casa)

    /**
     * Constructor de la clase BlackjackGame.
     * @param playerName Nombre del jugador.
     */
    public BlackjackGame(String playerName) {
        this.player = new Player(playerName);
    }

    /**
     * Inicia el juego de Blackjack.
     */
    public void startGame() {
        System.out.println("Welcome, " + player + "! Let's play Blackjack.");

        // Repartir cartas iniciales
        player.addCardToHand(deck.drawCard());
        player.addCardToHand(deck.drawCard());
        dealer.addCardToHand(deck.drawCard());
        dealer.addCardToHand(deck.drawCard());

        // Mostrar las cartas iniciales
        player.printHand();
        dealer.printHand();

        // Turno del jugador
        playerTurn();

        // Si el jugador no ha perdido, turno del dealer
        if (player.getHandValue() <= 21) {
            dealerTurn();
        }

        // Determinar el resultado del juego
        determineWinner();
    }

    /**
     * Maneja el turno del jugador, permitiendo acciones de "Pedir carta" o "Detenerse".
     */
    private void playerTurn() {
        Scanner scanner = new Scanner(System.in);
        String action;

        do {
            System.out.println("Choose an action: (H)it or (S)tand?");
            action = scanner.nextLine().toUpperCase();

            if (action.equals("H")) {
                player.addCardToHand(deck.drawCard());
                player.printHand();

                if (player.getHandValue() > 21) {
                    System.out.println("You busted! Dealer wins.");
                    return;
                }
            }
        } while (!action.equals("S"));
    }

    /**
     * Define el comportamiento del dealer durante su turno.
     */
    private void dealerTurn() {
        System.out.println("Dealer's turn...");

        while (dealer.getHandValue() < 17) {
            dealer.addCardToHand(deck.drawCard());
            dealer.printHand();
        }
    }

    /**
     * Compara las manos del jugador y del dealer para determinar el ganador.
     */
    private void determineWinner() {
        int playerTotal = player.getHandValue();
        int dealerTotal = dealer.getHandValue();

        if (playerTotal > 21) {
            System.out.println("You busted! Dealer wins.");
        } else if (dealerTotal > 21) {
            System.out.println("Dealer busted! You win.");
        } else if (playerTotal > dealerTotal) {
            System.out.println("Congratulations! You win.");
        } else if (dealerTotal > playerTotal) {
            System.out.println("Dealer wins.");
        } else {
            System.out.println("It's a tie!");
        }
    }

    /**
     * Punto de entrada del programa. Solicita el nombre del jugador y comienza el juego.
     * @param args Argumentos de línea de comandos (no se utilizan).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Obtener el nombre del jugador
        System.out.println("Enter your name: ");
        String playerName = scanner.nextLine();

        // Iniciar el juego
        BlackjackGame game = new BlackjackGame(playerName);
        game.startGame();
    }
}

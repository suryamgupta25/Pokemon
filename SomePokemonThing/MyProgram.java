import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class MyProgram extends ConsoleProgram
{

    public void run()
    {
        /* This writes the dialogue for the game and prints
        out any images*/
        // \n is used to separate the text into lines, making it easier for the user to read the screen

        int MAX_POKEMON = 1;
        int MAX_MOVES = 2;
        PokemonImages images = new PokemonImages();
        boolean playerWin = false;
        
        System.out.println("You are a Pokemon trainer. \n \nYou are walking around the woods, enjoying your time off of the arena.\n \nBut then, you notice something weird. \n \nYou hear a loud rumble, and the ground starts shaking from underneath you.\n \nYou don’t know what to do, but luckily, you have your Pokemon bag on you, and you are ready to strike back.");
        String start = readLine("Would you like to start your conquest? (yes or no)");
        System.out.println();

        if (start.equals("yes"))
        {
            String name = readLine("What is your name?");
            Player player = new Player(name);
            System.out.println("Hello " + name + "! We have important work to get to. Train your pokemon and go fight! The clock is ticking.");
            System.out.println("First, you will have to train some pokemon. The maximum you may have in your bag is " + MAX_POKEMON);
            ArrayList<Pokemon> playerBag = player.getBackpack();
            while(player.getBackpack().size() < MAX_POKEMON)
            {
                String makePokemon = readLine("Would you like to summon a pokemon? (yes or no)");
                if (makePokemon.equals("yes"))
                {
                    String pokemonName = readLine("What would be the name of your pokemon?");
                    Pokemon pokemon = new Pokemon(pokemonName);
                    String pokemonImage = images.getPokemonImage(pokemonName);
                    System.out.println(pokemonName + ": \n" + pokemonImage);
                    System.out.println("Nice. Now it is time to teach the pokemon some moves. You may teach the pokemon up to " + MAX_MOVES + " moves. \n You may not have more than one move of max damage(30), \n and the rest of the moves should be under 15 damage. \n If you want to teach your pokemon a high damage move (over 15), it should be taught first");
                    while(true)
                    {
                        String teachMove = readLine("Would you like to teach your pokemon a move?");
                        if (teachMove.equals("yes"))
                        {
                            String moveName = readLine("Name of the move?");
                            int damage = readInt("Move damage");
                            Move yourMove = new Move(moveName, damage);
                            if (pokemon.learnMove(yourMove) == true)
                            {
                                player.addMove(pokemon, yourMove);
                                System.out.println("Move successfully learned!");
                            }
                            else
                            {
                                System.out.println("Move may not be learned. You already have too many moves or too many high damage moves.");
                            }
                        }
                        else
                        {
                            System.out.println("Your pokemon has been all trained!");
                            break;
                        }
                        
                    }
                    // Add here that returns list of pokemon
                    player.addPokemon(pokemon);
                    player.printPokemon();
                }

                else
                {
                    System.out.println("All of your pokemon have been trained!");
                    break;
                }
            System.out.println("Your pokemon: ");
            for (int i = 0; i < playerBag.size(); i++)
            {
                Pokemon curPokemon = playerBag.get(i);
                System.out.println(curPokemon.toString());
            }
        
        }
        System.out.println();
        System.out.println("Opponent pokemon: ");
        ComputerAI comp = new ComputerAI();
        int compChoice = Randomizer.nextInt(1, 4);
        if (compChoice == 1)
        {
            comp.addPokemon1();
        }
        else if (compChoice == 2)
        {
            comp.addPokemon2();
        }
        else if (compChoice == 3)
        {
            comp.addPokemon3();
        }
        else
        {
            comp.addPokemon4();
        }
        // Once difficulty levels are added, other AI Pokemon can be added right at this line
        ArrayList<Pokemon> compBackpack = comp.getCompBag();
        comp.printCompPokemon();
        
        while (playerBag.size() != 0 && compBackpack.size() != 0)
        {
            int playerX = 0;
            int compX = 0;
            Pokemon attackingPlayer = playerBag.get(playerX);
            String attackingImages = images.getPokemonImage(attackingPlayer.getName());
            System.out.println(attackingImages);
            ArrayList<Move> playerMoves = attackingPlayer.getBook();
            Pokemon defendingComp = compBackpack.get(compX);
            String defendingImages = images.getPokemonImage(defendingComp.getName());
            System.out.println(defendingImages);
            ArrayList<Move> computerMoves = defendingComp.getBook();
            if (playerMoves.size() == 2)
            {
                int moveToPlay = readInt("Which move would you like to play? 0 - " + playerMoves.get(0).getName() + " or 1 - " + playerMoves.get(1).getName());
                if (moveToPlay > 1 || moveToPlay < 0)
                {
                    System.out.println("Move can't be chosen!");
                }
                else
                {
                    Move attackingMove = playerMoves.get(moveToPlay);
                    attackingPlayer.attack(defendingComp, attackingMove);
                    System.out.println("Remaining health of opponent: " + defendingComp.getHealth());
                }
            }
            
            if (playerMoves.size() == 1)
            {
                int moveToPlay2 = readInt("Which move would you like to play? 0 - " + playerMoves.get(0).getName());
                if (moveToPlay2 == 0)
                {
                    Move moveToAttack = playerMoves.get(moveToPlay2);
                    attackingPlayer.attack(defendingComp, moveToAttack);
                    System.out.println("Remaining health of opponent: " + defendingComp.getHealth());
                }
                else
                {
                    System.out.println("Move can't be chosen!");
                }
            }
            
            if (playerMoves.size() == 0)
            {
                System.out.println("You can't attack!");
            }
            
            Move compMove = defendingComp.returnMove(Randomizer.nextInt(0, 1));
            System.out.println("Your opponent attacked you with " + compMove.toString());
            defendingComp.attack(attackingPlayer, compMove);
            System.out.println("Your remaining health: " + attackingPlayer.getHealth());
            
            if (player.hasLost() == true)
            {
                playerWin = false;
                break;
            }
            
            if (comp.hasLost() == true)
            {
                playerWin = true;
                break;
            }
            
        }
        
        if (playerWin == true)
        {
            System.out.println("You win! Well done");
        }
        if (playerWin == false)
        {
            System.out.println("You lose! We'll get 'em next time");
        }
        
        System.out.println("Thank you for taking the time to consider playing.");
        String feedback = readLine("What could we do better?");
        System.out.println("Thanks for the feedback! We hope to improve this game in the future!");
        }
        else
        {
            System.out.println("Thank you for taking the time to consider playing.");
            String feedback = readLine("What could we do better?");
            System.out.println("Thanks for the feedback! We hope to improve this game in the future.");
            
        }
        
    }
}

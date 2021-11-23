# CPSC 210 Project

## Dungeon Crawler - turn based

#### What will the application do?  
This is a turn-based card game that features aspects of a dungeon crawler. 
Users can start this world as a hero, and the hero is incentivized to explore the world and attack monsters through
chests and coin piles present in the world. As mentioned previously, this game is turn-based. 
In other words, the hero would have 2 separate actions before the monsters attack.
A key feature of this game is the ability to use special cards on these monsters.

##### Who will use it?
Those who like card-games, board games, DND games, and other RPG games might like this.

##### Why is this project of interest to you?
This project is of interest to me because I spent a lot of time playing the title "Demeo" on Oculus VR.
I cannot find a similar game on PC, as this game title is only released on virtual reality. 
Thus, I would like to make a PC version of this game.


##**User Stories**
- As a user, I want to be able to add a Hero into the world and start playing 
- As a user, I want to be able to add to Hero's experience mana by attacking and removing monsters from the world
- As a user, I want to be able to add cards in my inventory by attacking a certain number of monsters
- As a user, I want to be able to view how many turns I have left
- As a user, I want to be able to view the monsters and objects immediately surrounding me
- As a user, I want to be able to move around in the world
- As a user, I want to be able to select to attack monsters that I can see
- As a user, I want to be able to select a card in my inventory and use it
- As a user, I want the game world to be populated with monsters and generic objects


**Phase 2**
- As a user, when I am displayed my options in the game, I want to be able to save my game as a checkpoint
- As a user, I want to be able to load an old game file and resume the game

### Phase 4: Task 2 Sample EventLog
``` 
Mon Nov 22 14:30:32 PST 2021
Stun card used!
Mon Nov 22 14:30:32 PST 2021
New stun card added to inventory!
Mon Nov 22 14:30:34 PST 2021
Hero is healed for at most 7 points
Mon Nov 22 14:30:34 PST 2021
Healing Card used!
Mon Nov 22 14:30:36 PST 2021
New stun card added to inventory!
Mon Nov 22 14:30:38 PST 2021
New stun card added to inventory!
Mon Nov 22 14:30:39 PST 2021
Your hero has died :( 

``` 
### Phase 4: Task 3: 
Some refactoring I would do would be between the Hero and CardList class.
The CardList has an arraylist of Cards, but this can be more simply implemented as
hero has an arraylist of Cars instead. Also, I think the SmallMonsters class and Monsters interface is not necessary.
I only have one type of monster in this game. Perhaps this structure would be more useful 
if I implemented different kinds of monsters in the game.

Also, the JsonWriter class contains methods for comparing whether or not two objects are equal to 
test the correctness of my JsonReader and JsonWriter classes. I would instead implement this as overriding 
hashcode and equals to make the structure more concise.

Also, I think I would refactor some of the classes in the UI package. Right now,
there is a lot of coupling between classes, and some operations are quite redundant.
For instance, in saving and loading in the GUI, I need to update the private GameWorld 
field in GraphicInterface, GameWorldPanel, and HeroStatsPanel. I am 
essentially doing the same operation in three separate places. I think I can
utilize the observer pattern, so that when there are any changes to the GameWorld class,
I let the graphic interface panels extend an abstract Observer class so that redundancy can 
be eliminated.





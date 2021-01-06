//hello

_to move_

arrow keys to move

A to suck and D to attack 

X to return to neutral Kirby

only single click received





_to build_

gradle build





_to add contents_

(Animator class not optimized to good luck!)

Enemy: An Enemy needs sprites, idle movement, and attack movement. Add sprite sources as String[] spritesources;

       Define the hitbox and the elemental of the enemy;
       
       Add idle movement to the movement() method, !isActive, and pick the suitable animation for the rest state;
       
       Add attack movement to the movement() method, isActive, and pick the suitable animation for the attack state;
       
       Create the object layer for these new Enemy in the map (.tmx file);
       
       Add the new class and the object layer name to the EnemySpawn() method in class Enemy;
       
Kirby: A Kirby needs idle, attack, etc sprites (refer to the other class of Kirby);

       Add the elemental, special move's hitbox and special attack of Kirby;
       
       Add this Kirby to the KirbyUpdate() method in class Kirby;
       
GameStage: A Map;

Supporting Stages: A background image;

                    A manageUI() method;
                    
Map: A Map needs a tile layer, which has to be visible;

     A map need a start layer with ONE ellipse object (this will specify the position of Kirby when first entering the map);
     
     A map will also need object layers to spawn Enemy and other environment elements;
     
     (Will need to mod the EnemySpawn() method to support different types of enemy groups in different maps && EnemySpawn() method currently also spawns breakable blocks so will        need to move to a more general spawn method.)
     
Environmental elements: Sprite and Animation as necessary;

                        An object layer in the .tmx map;
                        
                        Add the reaction in cases of collision; (what will happen if Kirby or whatever collides with this new Element)
                        
                        Add to the spawn method. (currently part of the EnemySpawn() method.)
       

# Artemis Entity System

This is a fork of the Artemis Entity System (in Java) by Gamadu.  The original code repo can
be found at https://code.google.com/p/artemis-framework/  Further information about
Artemis is on Gamadu's site at http://gamadu.com/artemis/index.html

# What is an Entity System?

An entity system (also known as a Component System) consists of three primary items:

* Components
* Entities
* Systems

A component simply holds a piece of data and does not contain any game logic.  Your
typical component will contain data objects, setters, and getters.  An entity
is a collection of components.  A system is typically an implementation that iteratively 
operates on a group of entities that share common components.

##  Can you give me an example?

Let's take a simple platform game where you have characters that move around.  In this
hypothetical example, you'll need ways of dealing with the character's game physics, drawing
the character, and driving how the character moves.  To build this, we could start with
the following components:

* Motion, for driving how the character moves
* Spatial, for drawing how the character appears
* Physics, for maintaining physical information about the character for collision handling, applying physical forces, etc.
* PlayerInput, for defining player input

From this, we'd define a "player entity" and then add those components at run-time.

Next, we create systems to act on the various components.  Our systems could be:

* RenderSystem, which acts on any entities that contain Physics *and* Spatial components. We
utilize the Physics component to determine where in the physics space the character is.  We utilize
the Spatial component to determine how to draw the character.  If an entity does not have
both of these components, then it will NOT be processed by RenderSystem.

* MotionSystem, which acts on any entities that contain Physics *and* Motion components.  We
utilize the Motion component to determine what motion effects are applied to an entity (left,
right, jump, etc.)  We utilize the Physics component to apply forces to the entity based on
Motion.  As above, if an entity does not have both of these components, then it will not be
processed by MotionSystem.

* PlayerInputSystem, which acts on any entities that contain Motion *and* PlayerInput components.  The
player input component could contain info about whether the related input is "locally" or "remotely"
(via network or some other means) defined.  For a "local" player entity, keypresses
update the Motion component with information.  For a "remote" player entity, network 
commands update the Motion component with information.

An example flow of this architecture would be: local player presses the "move left" key.  PlayerInputSystem
executes and detects the keypress, updating the Motion component.  MotionSystem executes and
"sees" the Motion for the entity is to the left, applying a Physics force to the left.  The
RenderSystem executes and reads the current position of the entity, and draws it according to
the Spatial definition (which may include texture region / animation information).

## Ugh.  Seems like a lot of work.  Why not use standard OOP?

One of the advantages of the Entity System is that it helps decompose or compartmentalize
things.  You have code that operates on very specific things and does this regardless of
other components that the entity might have.  Further, it helps prevent from things degenerating
into the "blob" anti-pattern.  As the game architecture becomes more complex, you simply 
add or remove components without having to worry about massive refactoring.  

In a hierarchical approach, if two classes in very different places of the hierarchy tree require
commonality, they'll either need to be derived from the same super class which takes on the
shared qualities or copying and pasting will be required.  This is fine for shallow trees,
but games tend to evolve over time turning this into a potential chore. 

Decomposing the architecture using an entity system gives us a very easy
path for adding / extending / altering operation.  Let's say in our example above, we now wanted
to create some enemies.  Rather than deriving or cutting and pasting things, we create a new
component: EnemyAI.  We then create a new entity that consists of:

* Motion component
* Physics component
* Spatial component
* EnemyAI component

...and we create a new "EnemyAISystem" that operates on any entity with the EnemyAI, Physics,
and Motion components.  This system implements enemy behaviors that define when and how to move the
related entity, updating the Motion component in the process.

Our RenderSystem and MotionSystem will pick up new enemy entities with no further changes 
required.

## Ok.  I'm sold.  Where do I begin?

I can't say things better than the Gamadu guys.  The Artemis manual is located at
http://gamadu.com/artemis/manual.html

There's a forum for Artemis at http://slick.javaunlimited.net/viewforum.php?f=28


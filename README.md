# Uno-Game-Engine
## Introduction
This project is a Command-Line Interface (CLI) based Uno game engine that allows developers to create and simulate their own variations of the Uno game. Uno is a popular card game with many variations, and this engine is designed to be extendable and reusable, adhering to best practices in design principles.

The engine simulates the gameplay and outputs the played card of each player in each turn, along with the final outcome, declaring the winner at the end of the game.

## Design Principles
### 1. Object-Oriented Design
The main classes in the project are:

- GameDriver (Main class)

- Game (abstract)

- UnoCards (abstract)

- Simulation

- Card

- Player

__Additional classes for custom game variations:__

- StandardVariation (extends Game)

- StandardUnoCards (extends UnoCards)


### 2. Clean Code Principles
- Descriptive Naming: Classes and methods are named clearly, with class names in uppercase and methods in camelCase (e.g., Game, shufflePlayedCards()), making the code easy to understand.

- DRY Principle: Reuses code effectively to avoid redundancy, like using constructor chaining and recursion.

 - Minimal Comments: Comments focus on explaining why rather than what, keeping the code self-explanatory.

- Simple Parameters and No Nulls: Methods have minimal parameters, and the code avoids returning null or special values for clarity.

- Code Smell Prevention: Avoids duplicated code, keeps methods short (following SRP), and reduces primitive data use by creating classes like Card and Player.
### 3. Design Patterns
The Strategy Design Pattern is implemented, allowing easy creation of new game variations. The abstract Game and UnoCards classes serve as templates, with specific rules and card sets defined in subclasses.

## 4. SOLID Principles
- Single Responsibility Principle (SRP): Each class and method has a clear, single responsibility.
- Open/Closed Principle (OCP): The design is open for extension via abstract classes and interfaces.
- Liskov Substitution Principle (LSP): Subtypes like __StandardUnoCards__ can replace their parent type __UnoCards__ without altering behavior.
- Interface Segregation Principle (ISP): The Simulation class manages gameplay rules, isolated from other implementations.
- Dependency Inversion Principle (DIP): High-level modules depend on abstractions rather than low-level modules.

## Conclusion
This CLI-based project follows established design patterns and principles to create an extendable and reusable Uno game engine. It has a strong adherence to clean code and SOLID principles, making it easy for developers to implement new game variations. The engine simulates the entire gameplay and provides a clear output for each player’s actions and the game's final winner.


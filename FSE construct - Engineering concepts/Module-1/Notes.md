# Design Principles and Design Patterns Notes

## Table of Contents

1. SOLID Principles
   - Single Responsibility Principle (SRP)
   - Open Closed Principle (OCP)
   - Liskov Substitution Principle (LSP)
   - Interface Segregation Principle (ISP)
   - Dependency Inversion Principle (DIP)

2. Creational Design Patterns
   - Singleton Pattern
   - Factory Method Pattern
   - Builder Pattern

3. Structural Design Patterns
   - Adapter Pattern
   - Decorator Pattern
   - Proxy Pattern

4. Behavioral Design Patterns
   - Observer Pattern
   - Strategy Pattern
   - Command Pattern

5. Architectural Patterns
   - Model View Controller (MVC)
   - Dependency Injection (DI)

---

# SOLID Principles

SOLID is a set of five object-oriented design principles that help developers build maintainable, scalable, flexible, and testable software.

## 1. Single Responsibility Principle (SRP)

### Definition
A class should have only one responsibility and one reason to change.

### Why?
When a class handles multiple responsibilities, changes in one feature can affect unrelated features.

### Real-Life Example
A Book should store book data.
Printing and saving should be handled by separate classes.

### Benefits
- Easier Testing
- Better Readability
- Lower Coupling
- Easier Maintenance

### Quick Reminder
One Class = One Responsibility = One Reason To Change

---

## 2. Open Closed Principle (OCP)

### Definition
Software entities should be open for extension but closed for modification.

### Why?
New features should be added without modifying existing tested code.

### Example
Add a new payment method by creating a new class instead of modifying existing payment logic.

### Benefits
- Easy Feature Addition
- Reduced Bugs
- Better Scalability

### Quick Reminder
Extend Existing Code, Don't Modify Working Code

---

## 3. Liskov Substitution Principle (LSP)

### Definition
Child classes should be replaceable with parent classes without changing program behavior.

### Example
MotorCar can replace Vehicle.
ElectricCar should not be forced to implement engine functionality if it does not have an engine.

### Benefits
- Correct Inheritance
- Better Polymorphism
- Reduced Runtime Errors

### Quick Reminder
If Child Cannot Fully Replace Parent, LSP Is Violated

---

## 4. Interface Segregation Principle (ISP)

### Definition
Clients should not be forced to implement methods they do not use.

### Example
Instead of one large BearKeeper interface:
- BearCleaner
- BearFeeder
- BearPetter

### Benefits
- Small Interfaces
- Better Flexibility
- Easier Maintenance

### Quick Reminder
Prefer Multiple Small Interfaces Over One Large Interface

---

## 5. Dependency Inversion Principle (DIP)

### Definition
High-level modules should depend on abstractions, not concrete implementations.

### Example
Depend on Keyboard interface instead of StandardKeyboard class.

### Benefits
- Loose Coupling
- Better Testability
- Easy Replacement of Components

### Quick Reminder
Depend On Interfaces, Not Implementations

---

# Creational Design Patterns

Creational Patterns focus on object creation mechanisms.

## Singleton Pattern

### Definition
Ensures only one instance of a class exists.

### Real-Life Example
Database Connection Manager

### Use Cases
- Logger
- Cache Manager
- Configuration Manager

### Quick Reminder
One Class → One Object

---

## Factory Method Pattern

### Definition
Creates objects without exposing creation logic to the client.

### Real-Life Example
Vehicle Factory creates Car or Bike.

### Use Cases
- Payment Systems
- Notification Systems
- Driver Managers

### Quick Reminder
Factory Creates Objects For You

---

## Builder Pattern

### Definition
Creates complex objects step-by-step.

### Real-Life Example
Building a Burger with custom ingredients.

### Use Cases
- DTO Objects
- Configuration Objects
- Immutable Classes

### Quick Reminder
Use Builder When Constructors Become Too Large

---

# Structural Design Patterns

Structural Patterns focus on how classes and objects are composed.

## Adapter Pattern

### Definition
Allows incompatible interfaces to work together.

### Real-Life Example
Mobile Charger Adapter

### Use Cases
- Third-Party APIs
- Legacy Systems
- Payment Integrations

### Quick Reminder
Adapter Acts As A Translator

---

## Decorator Pattern

### Definition
Adds functionality to an object dynamically without modifying existing code.

### Real-Life Example
Coffee + Milk + Sugar

### Use Cases
- Java IO Streams
- Logging Systems
- UI Components

### Quick Reminder
Wrap Existing Objects To Add Features

---

## Proxy Pattern

### Definition
Provides controlled access to another object.

### Real-Life Example
ATM Machine

### Use Cases
- Authentication
- Authorization
- Caching
- Lazy Loading

### Quick Reminder
Proxy Acts As A Middleman

---

# Behavioral Design Patterns

Behavioral Patterns focus on communication between objects.

## Observer Pattern

### Definition
One object notifies multiple dependent objects when its state changes.

### Real-Life Example
YouTube Subscribers

### Use Cases
- Notification Systems
- Event Handling
- Stock Market Applications

### Quick Reminder
One Subject → Many Observers

---

## Strategy Pattern

### Definition
Allows selecting different algorithms at runtime.

### Real-Life Example
Google Maps Route Selection

### Use Cases
- Payment Systems
- Sorting Algorithms
- Route Planning

### Quick Reminder
Choose Behavior Dynamically

---

## Command Pattern

### Definition
Converts requests into objects.

### Real-Life Example
TV Remote Control

### Use Cases
- Undo/Redo
- Task Scheduling
- Menu Systems

### Quick Reminder
Convert Requests Into Objects

---

# Architectural Patterns

## Model View Controller (MVC)

### Definition
Separates application into:
- Model
- View
- Controller

### Real-Life Example
Restaurant:
Customer → Waiter → Kitchen

### Benefits
- Separation of Concerns
- Better Maintainability
- Easier Testing

### Quick Reminder
Model = Data, View = UI, Controller = Coordinator

---

## Dependency Injection (DI)

### Definition
Dependencies are provided externally instead of creating them internally.

### Example
Car receives Engine through constructor.

### Benefits
- Loose Coupling
- Better Testing
- Better Maintainability

### Quick Reminder
Inject Dependencies From Outside

---

# Final Comparison Table

| Category | Concept | Purpose |
|----------|----------|----------|
| SOLID | SRP | One Responsibility |
| SOLID | OCP | Extend Without Modifying |
| SOLID | LSP | Proper Substitution |
| SOLID | ISP | Small Interfaces |
| SOLID | DIP | Depend On Abstractions |
| Creational | Singleton | One Instance |
| Creational | Factory | Create Objects |
| Creational | Builder | Build Complex Objects |
| Structural | Adapter | Compatibility |
| Structural | Decorator | Add Features |
| Structural | Proxy | Control Access |
| Behavioral | Observer | Notifications |
| Behavioral | Strategy | Select Algorithm |
| Behavioral | Command | Encapsulate Requests |
| Architecture | MVC | Separate Responsibilities |
| Architecture | DI | Inject Dependencies |

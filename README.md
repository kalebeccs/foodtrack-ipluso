# FoodTrack

FoodTrack is a simulation application for a fast food order system. Developed as part of an Object-Oriented Programming course, it demonstrates order management, price calculation, status updates, order deletion, and statistics display. Using Java and Swing for the graphical interface, it also employs enums to represent order states. The application allows for order insertion, updating, and deletion, and provides an overview of order statistics.

## Features

- Order management
- Price calculation
- Menu item listing
- Order status update
- Order deletion
- Order statistics display

## Technologies Used

- Java
- Swing for the graphical interface
- Enumerations (Enums)

## How to Run

1. Clone the repository.
2. Import the project into your preferred IDE.
3. Compile and run the main class `Main`.

## Project Structure

```plaintext
📁 FoodTrack/
├── 📂 src/
│   ├── 📂 assets/             # Contains project resources, such as images
│   ├── Main.java              # Main class that initializes the project
│   ├── Interface.java         # Class responsible for the graphical interface
│   ├── ControllerPedido.java  # Class that manages orders
│   ├── Pedido.java            # Class that represents an order
│   ├── EstadoPedido.java      # Enumeration that represents the states of an order
│   └── DataInitializer.java   # Class responsible for initializing mock data
├── README.md                  # Project documentation
```

## Preview

<img src="src/assets/preview.png" alt="FoodTrack application preview">

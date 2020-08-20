# AdvancedProgrammingExam

## An Restaurant Online Ordering System for a Neighborhood

### Requirements

* A graph model of a neighborhood composed of 25 houses(nodes) connected by roads (edges) 
* Client-Server TCP connection to send the orders to the restaurant
* Multithreaded Server to allow multiple requests from the clients
* Dijkstra's Algorithm to calculate the shortest path from the restaurant to the order's destination
* Observer Pattern to trigger DistanceCalculator(Observer) every time the Restaurant (Subject) accepts an order

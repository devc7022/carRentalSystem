# 🚗 Car Rental System

A simple Java-based Car Rental System built using Object-Oriented Programming principles. This console application allows customers to rent and return cars with basic functionality such as availability checks, rental pricing, and record management.

---

## 📌 Features

- Add and manage cars
- Rent a car (only if available)
- Return a rented car
- Calculates total rental cost based on rental days
- Displays rental info and confirmation before booking

---

## 🛠️ Technologies Used

- Java (OOP concepts)
- Console I/O using `Scanner`
- Collections (`ArrayList`)
- Basic command-line interaction

---

## 🧩 Class Overview

| Class Name        | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| `Car`             | Represents a car, its brand, model, price, and availability.                |
| `Customer`        | Represents a customer with ID and name.                                     |
| `Rental`          | Ties a `Car` to a `Customer` for a number of rental days.                   |
| `CarRentalSystum` | Core system managing the cars, customers, and rental processes.             |
| `Main`            | The entry point where sample cars are added and the menu is launched.       |

---

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/devc7022/carRentalSystem.git

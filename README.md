# Multi-Client Quiz Game Over Java Sockets

A multi-threaded quiz game implemented in Java using socket programming and MySQL integration. The application supports multiple clients, real-time game state synchronization, and dynamic scoring logic based on user inputs.

---

## 📌 Project Overview

This project demonstrates the integration of **Java Socket Programming**, **MySQL database operations**, and **multi-threading** to build a server-client based quiz system. Clients compete by answering questions correctly; wrong answers lead to elimination, and the player with the highest score wins.

---

## ⚙️ Technology Stack

- **Java SE 8+**
- **MySQL** (with JDBC)
- **NetBeans IDE** or any Java-compatible IDE
- **Threading** for multi-client support
- **Socket API** for network communication

---

## 🧩 System Architecture

### Server

- Opens a socket on port `5056` to accept client connections.
- For each client, spawns a new thread via `ClientHandler`.
- Manages game state, synchronizes question flow across clients, and updates the MySQL database accordingly.

### Client

- Connects to the server using a socket.
- Generates a random username.
- Receives and responds to quiz questions.
- Displays win/loss messages based on game outcomes.

---

## 🛠️ Setup Instructions

### 1. Database Configuration

Create a MySQL database named `datacom` with the following schema:

#### Table: `kullanicilar`

| Column         | Type      |
|----------------|-----------|
| kullanici_adi  | VARCHAR   |
| durum          | INT (1=active, 0=passive) |
| puan           | INT       |

#### Table: `sorular`

| Column         | Type      |
|----------------|-----------|
| soru           | TEXT      |
| cevap          | TEXT      |

> Insert at least 5 sample questions for proper gameplay.

---

### 2. Running the Application

1. **Start the Server**
   - Right-click `Server.java` and select **Run File**.
2. **Start Clients**
   - Launch `Client.java` multiple times in separate terminals or instances.

> The first client to connect will receive the first question. Other clients will be queued until it’s their turn.

---

## 🧪 Gameplay Logic

- **Correct Answer:** +5 points and move to next question.
- **Incorrect Answer:** Immediate elimination and database update (`durum=0`).
- **Game End Conditions:**
  - Only one active player remains.
  - All questions have been answered.
- **Winner:** The client with the highest score or the last remaining player.

---

## 💻 Example Terminal Output

- `ClientXX OYUNA BAĞLANDINIZ`
- `Soru: Türkiye'nin başkenti nedir?`
- `Cevabınız: Ankara`
- `Tebrikler 5 puan kazandınız!`

---

## 🔒 Notes & Constraints

- All clients must be on the same local network or use `localhost`.
- Synchronized control ensures that only one user answers at a time.
- Questions are managed from the database; ensure uniqueness and answer validity.

---

## 📚 References

- [Oracle Java Socket Tutorial](https://docs.oracle.com/javase/tutorial/networking/sockets/index.html)
- [GeeksForGeeks - Java Socket Programming](https://www.geeksforgeeks.org/socket-programming-in-java/)
- [JavaPoint - Java MySQL Connection](https://www.javatpoint.com/example-to-connect-to-the-mysql-database)
- [Yazılım Kodlama - JDBC CRUD](https://www.yazilimkodlama.com/java/java-mysql-veritabani-baglantisi-select-insert-update-delete/)

---

## 📄 License

This project is licensed under the **MIT License**. See the [LICENSE](./LICENSE) file for details.

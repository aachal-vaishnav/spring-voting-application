# Voting Application (Spring IoC + DI + Bean Scope + Lifecycle + Annotations)

## Project Overview

This is a **mini Voting Application** built using **Spring Framework concepts** such as:

* **IoC (Inversion of Control)**
* **DI (Dependency Injection)**
* **Bean Scopes**
* **Bean Lifecycle (`@PostConstruct` / `@PreDestroy`)**
* **Annotations (`@Component`, `@Autowired`, `@Scope`)**

The application allows users to vote for a political party from three options: **Democratic, Republic, and Independent**. Votes are collected into a **user list**, and an **authority class** (`CounterAuthority`) manages the final count of votes and displays the results.

---

## Scenario Flow

1. The user launches the application and is greeted with a menu:

   * `1. Want to Vote`
   * `2. See All Votes (ADMIN)`
   * `3. Exit`

2. **Voting Process**:

   * The user enters their **username**.
   * The user selects a party from three options.
   * A **User object** is created (prototype scoped) and **assigned the chosen party**.
   * The User object is added to the **UserList** (singleton) which keeps track of all votes.

3. **Authority Process**:

   * `AuthorityAccess` (singleton) accesses the **UserList** via **dependency injection** (`@Autowired`).
   * The authority can view all users and their selected party.
   * At the end, the authority counts votes for each party to determine the winner.

4. The program uses **lifecycle callbacks**:

   * `@PostConstruct`: Simulates **database connection or initialization** when the bean is created.
   * `@PreDestroy`: Simulates **cleanup** when the application ends (closing the connection or resources).

---

## Project Structure and Dependencies

| Class                                   | Description                                | Dependency / Injection                                                                    |
| --------------------------------------- | ------------------------------------------ | ----------------------------------------------------------------------------------------- |
| `VotingApplication`                     | Main class containing the voting menu loop | Depends on `AuthorityAccess`, `User`, `UserList` via `AnnotationConfigApplicationContext` |
| `User` / `SimpleUser`                   | Represents a voting user                   | Prototype bean; injected with `PoliticalParty` at runtime                                 |
| `PoliticalParty` interface              | Interface for political parties            | Implemented by `Democratic`, `Republic`, `Independent`                                    |
| `Democratic`, `Republic`, `Independent` | Concrete party classes                     | Singleton beans, injected into `User` at runtime                                          |
| `UserList` / `SimpleUserList`           | Stores all voting users                    | Singleton bean; injected into `AuthorityAccess`                                           |
| `CounterAuthority` / `AuthorityAccess`  | Provides access to all votes               | Singleton bean; uses `@Autowired` to inject `UserList`                                    |

---

## Spring Concepts Used

### 1️⃣ **IoC (Inversion of Control)**

* **Definition**: Spring container controls the creation and lifecycle of objects (beans) instead of us manually creating them.
* **Usage**: All beans (`User`, `UserList`, `AuthorityAccess`, parties) are managed by Spring.
* **Example**:

```java
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.example.Voting");
```

---

### 2️⃣ **DI (Dependency Injection)**

* **Definition**: Spring automatically injects dependencies between beans rather than manually instantiating them.
* **Usage**:

  * `AuthorityAccess` automatically gets the `UserList` injected via `@Autowired`.
  * `User` gets `PoliticalParty` assigned at runtime.
* **Example**:

```java
@Autowired
private UserList userList; // Injected automatically
```

---

### 3️⃣ **Bean Scopes**

* **Prototype Scope** (`User`):

  * Each voting user is a **new object** created per vote.
  * Avoids overwriting previous votes.
* **Singleton Scope** (`UserList`, `AuthorityAccess`, Party classes):

  * Only **one instance** exists in the container.
  * Shared across the entire application.
* **Reasoning**:

  * **User** is dynamic per vote → Prototype.
  * **UserList and Authority** are shared → Singleton.

---

### 4️⃣ **Bean Lifecycle (`@PostConstruct` / `@PreDestroy`)**

* **Purpose**: Simulate **database connections or resource management**.
* **`@PostConstruct`**: Called **after bean creation**, simulating initialization (e.g., DB connection).
* **`@PreDestroy`**: Called **before bean destruction**, simulating cleanup (e.g., closing voting session).
* **Example**:

```java
@PostConstruct
public void init() {
    System.out.println("DB Connection successful");
}

@PreDestroy
public void destroy() {
    System.out.println("Voting has been closed");
}
```

---

### 5️⃣ **Annotations**

| Annotation                       | Usage                                                   |
| -------------------------------- | ------------------------------------------------------- |
| `@Component("beanName")`         | Registers the class as a Spring bean                    |
| `@Autowired`                     | Injects dependencies automatically                      |
| `@Scope("prototype")`            | Creates new instance each time for `User`               |
| `@PostConstruct` / `@PreDestroy` | Executes lifecycle methods on bean creation/destruction |

---

## Enhancements & Commented Code

* Initially, manual dependency setting was **commented out**:

```java
//authorityAccess.setUserList(userList);
```

* **Enhancement**: Used `@Autowired` for automatic injection instead of manual setter.

* **Reason**: Cleaner, less error-prone, and fully utilizes Spring DI.

* Initially, we used manual `context.getBean("userList")` for every vote.

* **Enhancement**: Keep **prototype `User`**, singleton `UserList`, and inject `UserList` into `AuthorityAccess`.

* **Reason**: Allows multiple users to vote without overwriting data.

---

## How the Concepts Work Together

1. Spring **creates and manages beans** (IoC).
2. Dependencies are **injected automatically** (DI), e.g., `UserList` into `AuthorityAccess`.
3. **Prototype `User` beans** ensure each voter is unique.
4. **Singleton beans** (UserList, AuthorityAccess, Party classes) manage shared state.
5. Lifecycle methods handle **initialization and cleanup**, simulating a **real voting system with millions of users**.
6. **Annotations** replace verbose XML configurations for simplicity and maintainability.

---

## Sample Output

```
WELCOME
Choose from below
1.Want to Vote
2.See All Votes(ADMIN)
3.Exit
1
Enter Your Username
Alice
Choose from below
1.Democratic
2.Republic
3.Independent
1
Thank You

WELCOME
Choose from below
1.Want to Vote
2.See All Votes(ADMIN)
3.Exit
2
Alice is voted for Democratic

WELCOME
Choose from below
1.Want to Vote
2.See All Votes(ADMIN)
3.Exit
3
Voting has been closed
```

---

## **Conclusion**

This project demonstrates **core Spring concepts** applied in a practical scenario:

* **IoC + DI**: Beans created and dependencies injected automatically.
* **Bean Scopes**: Prototype for dynamic users, Singleton for shared data.
* **Lifecycle Management**: Safe initialization and cleanup.
* **Annotations**: Replace XML for concise configuration.

It simulates a **mini real-world voting system** safely and efficiently.

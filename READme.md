# To Do List Manager

This is a to-do list manager with animation effects. users can create tasks, mark them as completed, and delete them. Each user gets their own todo list stored as a single linked list.

## Features
- **User Management**: add users, each with their own task list.
- **Task Management**: add, complete, and delete tasks.
- **Animations**: A character runs across the screen before the logo slides in and stays centered.
- **GUI**: UI built with Java Swing (with help from ChatGPT).

## How It Works
1. Users are stored in an array.
2. Each user has a unique name and a task list (single linked list).
3. Tasks have descriptions and a status (Pending/Done).
4. The GUI lets users manage their tasks easily.
5. A runner animation plays once before the logo slides in.

## Running The Program
run:

```bash
javac manager/*.java
java manager.Main
```

## File Structure
```
manager/
|-- GUI.java       # The main UI with animations
|-- Main.java      # Starts the program
|-- Task.java      # Task object with description and status
|-- TList.java     # Single linked list for tasks
|-- User.java      # User object managing their task list
```
By: Michael O'Brien

🚀


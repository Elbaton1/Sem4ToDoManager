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
### Command Line
Run:
```bash
cd src/manager
javac *.java
java Main.java
```
### or

1. Open IntelliJ IDEA.
2. Click **File** → **Open** and select the project folder.
3. Wait for IntelliJ to index the files.
4. Go to **Run** → **Edit Configurations**.
5. Click **+** → **Application**.
6. Set the **Main class** to `manager.Main`.
7. Click **Apply** and **OK**.
8. Click **Run** to start the program.

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


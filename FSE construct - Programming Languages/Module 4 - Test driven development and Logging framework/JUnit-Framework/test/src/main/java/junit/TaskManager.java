package junit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {

    private final List<String> tasks = new ArrayList<>();

    public void addTask(String task) {
        if (task == null || task.isBlank()) {
            throw new IllegalArgumentException("Task cannot be null or blank");
        }
        tasks.add(task);
    }

    public boolean removeTask(String task) {
        return tasks.remove(task);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<String> getAllTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public boolean containsTask(String task) {
        return tasks.contains(task);
    }

    public void clearAllTasks() {
        tasks.clear();
    }

    public String getTaskAt(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index: " + index);
        }
        return tasks.get(index);
    }
}

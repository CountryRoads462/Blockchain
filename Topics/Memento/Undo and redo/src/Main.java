import java.util.*;

class Originator<T> {
    private T value;

    public void setValue(T value) {
        this.value = value;
    }

    public void printValue() {
        System.out.println(value);
    }

    public Memento<T> getMemento() {
        return new Memento<>(value);
    }

    public void setMemento(Memento<T> memento) {
        this.value = memento.value;
    }

    static class Memento<T> {
        private final T value;

        private Memento(T value) {
            this.value = value;
        }
    }
}

class Caretaker<T> {
    private final Originator<T> originator;
    private final Deque<Originator.Memento<T>> undoStack = new ArrayDeque<>();
    private final Deque<Originator.Memento<T>> redoStack = new ArrayDeque<>();

    Caretaker(Originator<T> originator) {
        this.originator = originator;
    }

    public void beforeValueChanged() {
        undoStack.push(originator.getMemento());
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(originator.getMemento());
            Originator.Memento<T> memento = undoStack.pop();
            originator.setMemento(memento);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Originator.Memento<T> memento = redoStack.pop();
            originator.setMemento(memento);
        }
    }
}
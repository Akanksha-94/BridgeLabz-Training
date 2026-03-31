import java.util.*;

public class TextEditorUndoRedo {
  static class State {
    String content;
    State prev, next;

    State(String c) {
      content = c;
    }
  }

  private State head, tail, current;
  private final int limit;
  private int size = 0;

  public TextEditorUndoRedo(int limit) {
    this.limit = limit;
  }

  public void addState(String content) {
    State s = new State(content);
    if (current != null) { // drop redo
      current.next = null;
      tail = current;
    }
    if (tail == null) {
      head = tail = s;
    } else {
      tail.next = s;
      s.prev = tail;
      tail = s;
    }
    current = s;
    size++;
    if (size > limit) {
      head = head.next;
      head.prev = null;
      size--;
    }
  }

  public String undo() {
    if (current != null && current.prev != null) {
      current = current.prev;
      return current.content;
    }
    return current == null ? null : current.content;
  }

  public String redo() {
    if (current != null && current.next != null) {
      current = current.next;
      return current.content;
    }
    return current == null ? null : current.content;
  }

  public String viewCurrent() {
    return current == null ? "" : current.content;
  }

  public static void main(String[] args) {
    TextEditorUndoRedo te = new TextEditorUndoRedo(10);
    te.addState("");
    te.addState("Hello");
    te.addState("Hello World");
    System.out.println("Current: " + te.viewCurrent());
    te.undo();
    System.out.println("After undo: " + te.viewCurrent());
    te.redo();
    System.out.println("After redo: " + te.viewCurrent());
  }
}

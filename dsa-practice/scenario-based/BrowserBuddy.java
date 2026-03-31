import java.util.Stack;

class BrowserBuddy {

    private static class Node {
        String url;
        Node prev;
        Node next;

        Node(String url) {
            this.url = url;
        }
    }

    private Node current;
    private Stack<Node> closedTabs = new Stack<>();

    public void open(String url) {
        Node node = new Node(url);
        if (current != null) {
            current.next = node;
            node.prev = current;
        }
        current = node;
    }

    public void back() {
        if (current != null && current.prev != null) {
            current = current.prev;
        }
    }

    public void forward() {
        if (current != null && current.next != null) {
            current = current.next;
        }
    }

    public void closeTab() {
        if (current == null) return;
        closedTabs.push(current);
        if (current.prev != null) {
            current.prev.next = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }
        current = current.prev != null ? current.prev : current.next;
    }

    public void restoreTab() {
        if (closedTabs.isEmpty()) return;
        Node node = closedTabs.pop();
        if (current != null) {
            node.prev = current;
            node.next = current.next;
            if (current.next != null) {
                current.next.prev = node;
            }
            current.next = node;
        }
        current = node;
    }

    public void showCurrent() {
        if (current == null) {
            System.out.println("No active tab");
        } else {
            System.out.println(current.url);
        }
    }

    public static void main(String[] args) {
        BrowserBuddy browser = new BrowserBuddy();

        browser.open("google.com");
        browser.open("openai.com");
        browser.open("github.com");

        browser.back();
        browser.showCurrent();

        browser.forward();
        browser.showCurrent();

        browser.closeTab();
        browser.showCurrent();

        browser.restoreTab();
        browser.showCurrent();
    }
}
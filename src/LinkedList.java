public class LinkedList {
    Node root;
    Node tail;

    public class Node {
        Node next;
        Node previous;
        private int data;

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }
    }

    public LinkedList() {
    }

    public void add(int data) {
        Node newNode = new Node();
        newNode.setData(data);
        if (root != null) {
            this.tail.next = newNode;
            newNode.previous = this.tail;
            this.tail = newNode;
        } else {
            this.root = newNode;
            this.tail = newNode;
        }
    }

    public void revert() {
        if (this.root != null && !this.root.equals(this.tail)) {
            Node current = this.root;
            Node temp;
            do {
                temp = current.next;
                current.next = current.previous;
                current.previous = temp;
                current = temp;
            } while (current != null);
            temp = this.root;
            this.root = this.tail;
            this.tail = temp;
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[ ");
        Node current = this.root;
        do {
            stringBuilder.append(current.data);
            stringBuilder.append(", ");
            current = current.next;
        } while (current != null);
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(" ]");
        return stringBuilder.toString();
    }
}

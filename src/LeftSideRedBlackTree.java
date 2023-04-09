public class LeftSideRedBlackTree <T extends Comparable<T>>{
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;
    private class Node
    {
        private int value;
        private Node left, right;
        private boolean color;
    }

    public boolean add(int value){
        if (root != null){
            boolean result = insert(root, value);
            root = rebalance(root);
            root.color = BLACK;
            return result;
        } else {
            root = new Node();
            root.color = BLACK;
            root.value = value;
            return true;
        }
    }

    private boolean insert(Node node, int value)
    {
        if (node.value == value) {
            return false;
        } else {
            if (node.value > value) {
                if (node.left != null) {
                    boolean result = insert(node.left, value);
                    node.left = rebalance(node.left);
                    return result;
                } else {
                    node.left = new Node();
                    node.left.color = RED;
                    node.left.value = value;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean result = insert(node.right, value);
                    node.right = rebalance(node.right);
                    return result;
                } else {
                    node.right = new Node();
                    node.right.color = RED;
                    node.right.value = value;
                    return  true;
                }
            }
        }

    }

    private Node rebalance(Node node) {
        Node result = node;
        boolean needRebalance;
        do {
            needRebalance = false;
            if (result.right != null && result.right.color == RED && (result.left == null || result.left.color == BLACK)){
                needRebalance = true;
                result = rotateRight(result);
            }
            if (result.left != null && result.left.color == RED && result.left.left != null && result.left.left.color == RED){
                needRebalance = true;
                result = rotateLeft(result);
            }
            if (result.left != null && result.left.color == RED && result.right != null && result.right.color == RED){
                needRebalance = true;
                colorFlip(result);
            }
        } while (needRebalance);
        return result;
    }

    private void colorFlip(Node node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private Node rotateRight(Node node) {
        Node right = node.right;
        Node between = right.left;
        right.left = node;
        node.right = between;
        right.color = node.color;
        node.color = RED;
        return right;
    }

    private Node rotateLeft(Node node) {
        Node left = node.left;
        Node between = left.right;
        left.right = node;
        node.left = between;
        left.color = node.color;
        node.color = RED;
        return left;
    }

    private boolean isRed(Node node) {
        return node.color;
    }


}

public class Node {
    Node left, right;
    Object val;
    int freq, height;

    Node() {
        freq = 0;
        height = 0;
        left = null;
        right = null;
    }

    Node(Object v) {
        val = v;
        freq = height = 1;
        left = new Node();
        right = new Node();
    }

    boolean is_null() {
        return (freq == 0 && height == 0);
    }

    void updateHeight() {
        height = 1 + Math.max(left.height, right.height);
    }

    int Bf() {
        return left.height - right.height;
    }
    int comp(Object val) {
        if (val instanceof String) {
            return ((String) val).compareTo((String) this.val);
        } else if (val instanceof Integer) {
            if ((int) val > (int) this.val) {
                return 1;
            } else if ((int) val == (int) this.val) {
                return 0;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}
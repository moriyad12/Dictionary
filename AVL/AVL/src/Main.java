public class Main {
    public static void main(String[] args) {
        Node root=new Node();
        Avl tree=new Avl();
        root = tree.ins(root, "aaaaaaaaaaa");
        root = tree.ins(root, "aaaaaaaaaab");
        root = tree.ins(root, "aaaaaaaaaac");
        root = tree.del(root, "aaaaaaaaaaaaaaaa");
        root = tree.del(root, "aaaaaaaaaab");
        root = tree.del(root, "aaaaaaaaaab");

    }
}
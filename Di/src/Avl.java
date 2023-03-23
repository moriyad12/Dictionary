public class Avl {
    int size=0;
    public Avl(){
        size=0;
    }
    Node rightRotation(Node Q){
        Node P = Q.left;
        Q.left = P.right;
        P.right = Q;
        Q.updateHeight();
        P.updateHeight();
        return P;
    }
    Node leftRotation(Node P){

        Node Q = P.right;
        P.right = Q.left;
        Q.left = P;
        P.updateHeight();
        Q.updateHeight();
        return Q;
    }
    Node balance(Node root){

        if(root.Bf() == 2)
        {
            if(root.left.Bf() == -1)
                root.left = leftRotation(root.left);

            root = rightRotation(root);
        }
        else if(root.Bf() == -2)
        {
            if(root.right.Bf() == 1)
                root.right = rightRotation(root.right);

            root = leftRotation(root);
        }
        return root;
    }
    Node balanceCheck(Node root){
        if(!root.left.is_null()){
            root.left= balanceCheck(root.left);
        }
        root.updateHeight();
        root= balance(root);
        return root;
    }
    Node del(Node root,Object val){
        size--;
        return delNode(root,val);
    }
    private  Node delNode(Node root,Object val){
        if(root.is_null())return new Node();
        if(root.comp(val)==0){
            if(root.freq>1){
                root.freq--;
                size++;
                root.updateHeight();
                root = balance(root);
                return root;
            }else if(root.right.is_null()&&root.left.is_null()) {
                return new Node();
            }else if(!root.right.is_null()&&!root.left.is_null()){
                Node s=root.right;
                Node last=root;
                while (!s.left.is_null()){
                    last=s;
                    s=s.left;
                }
                if(last.comp(root.val)!=0)//how???????
                {
                    last.left=s.right;
                    s.right=root.right;
                }
                s.left=root.left;
                root=s;
                root.updateHeight();
                root = balance(root);
                if(!root.right.is_null())
                    root.right= balanceCheck(root.right);
                return root;
            }else{
                if(root.right.is_null()){
                    return root.left;
                }else{
                    return root.right;
                }
            }
        }
        if(root.comp(val)<0) //val < root.val
            root.left = delNode(root.left, val);
        else
            root.right = delNode(root.right, val);
        root.updateHeight();
        root = balance(root);
        return root;
    }
    Node ins(Node root,Object val){
        size++;
        return insert(root,val);
    }
    private Node insert(Node root, Object ins_val) {
        if (root.is_null()) {
            return new Node(ins_val);
        }
        if (root.val.equals(ins_val)) {
            root.freq++;
            size--;
            return root;
        }
        if (root.comp(ins_val) < 0) {
            root.left = insert(root.left, ins_val);
        } else {
            root.right = insert(root.right, ins_val);
        }
        root.updateHeight();
        root = balance(root);
        return root;
    }

    Object getmino(Node root) {//GET minimum value
        if(root.is_null())return "NO MIN";
        Node s = root.left;
        Node last = root;
        while (!(s.is_null())) {
            last = s;
            s = s.left;
        }
        return last.val;
    }
    boolean search(Node root, Object ins_val) {
        if (root.is_null()) {
            return false;
        }
        if (root.comp(ins_val) == 0) {
            return true;
        }
        if (root.comp(ins_val) < 0) {
            return search(root.left, ins_val);
        } else {
            return search(root.right, ins_val);
        }
    }
    int getHeight(Node root){
        return root.height;
    }
    int getSize(){
        return size;
    }
    void printTreeHelper(Node root, int space )
    {
        int i;
        if(root != null )
        {
            space = space + 10;
            printTreeHelper(root.right, space);
            System.out.printf("\n");
            for ( i = 10; i < space; i++)
            {
                System.out.printf(" ");
            }
            System.out.printf("%s", root.val);
            System.out.printf("\n");
            printTreeHelper(root.left, space);
        }
    }
}

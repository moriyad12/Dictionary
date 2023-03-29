import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class test {
    @Test
    void TestAvlsearch1()
    {   int start= (int) System.nanoTime();
        Avl tree = new Avl();
        Node root=new Node();
        root=tree.ins(root,3);
        root=tree.ins(root,2);
        root=tree.ins(root,1);
        assertEquals(true, tree.search(root,3));
        assertEquals(true, tree.search(root,2));
        assertEquals(true, tree.search(root,1));
        assertEquals(false, tree.search(root,5));
        assertEquals(2, tree.getHeight(root));
        assertEquals(3,tree.getSize());
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    void TestAvlsearch2()
    {   int start= (int) System.nanoTime();
        Avl tree = new Avl();
        Node root=new Node();
        root=tree.ins(root,2);
        root=tree.ins(root,3);
        root=tree.ins(root,1);
        assertEquals(true, tree.search(root,3));
        assertEquals(true, tree.search(root,2));
        assertEquals(true, tree.search(root,1));
        assertEquals(false, tree.search(root,5));
        assertEquals(2, tree.getHeight(root));
        assertEquals(3,tree.getSize());
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    void TestAvlsearch3()
    {   int start= (int) System.nanoTime();
        Avl tree = new Avl();
        Node root=new Node();
        root=tree.ins(root,1);
        root=tree.ins(root,2);
        root=tree.ins(root,3);
        assertEquals(true, tree.search(root,3));
        assertEquals(true, tree.search(root,2));
        assertEquals(true, tree.search(root,1));
        assertEquals(false, tree.search(root,5));
        assertEquals(2, tree.getHeight(root));
        assertEquals(3,tree.getSize());
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    void TestRbtsize(){
        int start= (int) System.nanoTime();
        Avl tree = new Avl();
        Node root=new Node();
        root=tree.ins(root,80);
        root=tree.ins(root,40);
        root=tree.ins(root,55);
        root=tree.ins(root,65);
        assertEquals(3, tree.getHeight(root));
        assertEquals(4,tree.size);
        root=tree.del(root,55);
        assertEquals(3, tree.getHeight(root));
        assertEquals(3,tree.size);
        root=tree.del(root,65);
        assertEquals(2, tree.getHeight(root));
        assertEquals(2,tree.size);
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);

    }
    @Test
    public void insert_AVL1 () {
        int start= (int) System.nanoTime();
        Avl tree = new Avl();
        Node root=new Node();
        root = tree.ins(root, "aa");
        root = tree.ins(root, "bb");
        root = tree.ins(root, "cc");
        root = tree.ins(root, "vv");
        root = tree.ins(root, "mm");
        root = tree.ins(root, "zz");
        assertEquals("aa bb cc mm vv zz ", tree.printInorder(root));
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    public void insert_AVL2 () {
        int start= (int) System.nanoTime();
        Avl tree = new Avl();
        Node root=new Node();
        root = tree.ins(root, 10);
        root = tree.ins(root, -100);
        root = tree.ins(root, 50);
        root = tree.ins(root, 30);
        root = tree.ins(root, 40);
        root = tree.ins(root, 1000);
        assertEquals("-100 10 30 40 50 1000 ", tree.printInorder(root));
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    public void delete_AVL () {
        int start= (int) System.nanoTime();
        Avl Atree = new Avl();
        Node Aroot=new Node();
        Aroot = Atree.ins(Aroot, 150);
        Aroot = Atree.ins(Aroot, 100);
        Aroot = Atree.ins(Aroot, 90);
        Aroot = Atree.ins(Aroot, 80);
        Aroot = Atree.ins(Aroot, 300);
        Aroot = Atree.ins(Aroot, 350);
        Aroot = Atree.ins(Aroot, 330);
        Aroot = Atree.del(Aroot, 80);
        Aroot = Atree.del(Aroot, 300);
        Aroot = Atree.del(Aroot, 350);
        Aroot = Atree.del(Aroot, 330);

        assertEquals("90 100 150 ", Atree.printInorder(Aroot));
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    public void delete2_AVL () {
        int start= (int) System.nanoTime();
        Avl Atree = new Avl();
        Node Aroot=new Node();
        Aroot = Atree.del(Aroot, 150);
        assertEquals("", Atree.printInorder(Aroot));
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    public void TestAvlsearch4(){
        int start= (int) System.nanoTime();
        Avl Atree = new Avl();
        Node Aroot=new Node();
        assertEquals(false, Atree.search(Aroot,100));
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    public void TestInsertB(){
        int start= (int) System.nanoTime();
        Avl Atree = new Avl();
        Node Aroot=new Node();
        Aroot = Atree.Patchins(Aroot,"F:\\DIctionary\\AVL\\AVL\\src\\testInsert300.txt");
      //  assertEquals("aa bb cc mm vv zz ", Atree.printInorder(Aroot));
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    public void TestDeleteB(){
        int start= (int) System.nanoTime();
        Avl Atree = new Avl();
        Node Aroot=new Node();
        Aroot = Atree.Patchins(Aroot,"F:\\DIctionary\\AVL\\AVL\\src\\testInsert300.txt");
        Aroot = Atree.Patchdel(Aroot,"F:\\DIctionary\\AVL\\AVL\\src\\testDelete300.txt");

        //assertEquals("aa bb cc mm ",Atree.printInorder(Aroot) );
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    @Test
    public void TestSearchB(){
        int start= (int) System.nanoTime();
        Avl Atree = new Avl();
        Node Aroot=new Node();
        Aroot = Atree.Patchins(Aroot,"F:\\DIctionary\\iinsertTest.txt");
        Aroot = Atree.Patchdel(Aroot,"F:\\DIctionary\\TestDel.txt");
        assertEquals(false, Atree.search(Aroot,"ss"));
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }
    public void TestSearchB2(){
        int start= (int) System.nanoTime();
        Avl Atree = new Avl();
        Node Aroot=new Node();
        Aroot = Atree.Patchins(Aroot,"F:\\DIctionary\\iinsertTest.txt");
        Aroot = Atree.Patchdel(Aroot,"F:\\DIctionary\\TestDel.txt");
        assertEquals(true, Atree.search(Aroot,"mm"));
        int end=(int)System.nanoTime();
        System.out.println((end-start)/1000);
    }

}

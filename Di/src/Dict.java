import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dict {
    Avl Atree;
    RBT RBtree;
    NODERB RBroot;
    Node Aroot;
    boolean flag;
    public Dict(boolean flag) {
        if(flag==true)
        { Atree=new Avl();
            Aroot=new Node();
            this.flag=true;}
        else {RBtree=new RBT();
            RBroot=new NODERB();
            this.flag=false;}
    }
    void insert( Object val){
        if(flag){
            Aroot=Atree.ins(Aroot,val);
        }
        else {
            RBroot=RBtree.ins(RBroot,val);
        }
    }
    void delete(Object val){
        if(flag){
            Aroot=Atree.del(Aroot,val);
        }
        else {
            RBroot=RBtree.delete(RBroot,val);
        }
    }
    void search(Object val){
        if(flag){
            if(Atree.search(Aroot,val))
                System.out.println(" node is found\n");
            else System.out.println(" node is not found\n");
        }
        else {
            if(RBtree.search(RBroot,val)!=null)
                System.out.println(" node is found\n");
            else System.out.println(" node is not found\n");
        }
    }
    void Patchins(String dir)
    {
        try {
            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.insert(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    void Patchdel(String dir){
        try {
            File myObj = new File(dir);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.delete(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    int size(){
        int size;
        if(flag){
            size = Atree.getSize();
//            System.out.println("size =:"+Atree.getSize());
        }
        else {
            size =RBtree.size;
//            System.out.println("size =:"+RBtree.size);
        }
        return size;
    }
    void height(){
        if(flag){
            System.out.println("height =:"+Atree.getHeight(Aroot));
        } else {
            System.out.println("height =:"+RBtree.getHeight(RBroot));
        }
    }
}

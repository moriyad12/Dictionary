import java.util.Scanner;

import static java.lang.Math.abs;

public class CLinterface {
    Dict dictionary;
    Scanner sc =new Scanner(System.in);
    void choosetreeType(){
//        System.out.print("\033[H\033[2J");
        System.out.println("choose dictionary tree type:\n" +
                "[1] AVL tree\n" +
                "[2] Red Black tree");
        int choice=Integer.valueOf(sc.nextLine());
        switch (choice){
            case 1:
                dictionary =new Dict(true);
                break;
            case 2:
                dictionary =new Dict(false);
                break;
            default:
                System.out.println("choose a valid number");
                choosetreeType();
        }
        return;
    }
    void mainMenu(){
//        System.out.printf("\033[H\033[2J");
//        System.out.flush();
        System.out.println("choose a command number:\n" +
                "[1] insert one word\n" +
                "[2] search onw word\n" +
                "[3] delete one word\n" +
                "[4] batch insert words\n" +
                "[5] batch delete words\n" +
                "[6] display tree size\n" +
                "[7] display tree height\n" +
                "[8] exit");
        int choice =Integer.valueOf(sc.nextLine());
        switch (choice){
            case 1:
                System.out.println("type the word you want to insert:");
                String word= sc.nextLine();
                dictionary.insert(word);
                break;
            case 2:
                System.out.println("type the word you want to search:");
                String word1= sc.nextLine();
                dictionary.search(word1);
                break;
            case 3:
                System.out.println("type the word you want to delete:");
                String word2= sc.nextLine();
                dictionary.delete(word2);
                break;
            case 4:
                System.out.println("type the path of the file containing all the words to insert :");
                String path= sc.nextLine();
                int oldSize =dictionary.size();
                dictionary.Patchins(path);
                int newSize = dictionary.size();
                System.out.printf("%d new words were added\n",abs(oldSize-newSize));
                break;
            case 5:
                System.out.println("type the path of the file containing all the words to delete:");
                String path2= sc.nextLine();
                int oldSize2 =dictionary.size();
                dictionary.Patchdel(path2);
                int newSize2 = dictionary.size();
                System.out.printf("%d out of %d were deleted\n",abs(oldSize2-newSize2),oldSize2);
                break;
            case 6:
                System.out.println("size =:"+dictionary.size());
                break;
            case 7:
                dictionary.height();
                break;
            case 8:
                System.exit(0);
            default:
                System.out.println("choose a valid number");
        }
        return;
    }

    public static void main(String[] args) {
        CLinterface userMenu = new CLinterface();
        userMenu.choosetreeType();
        while (true){
            userMenu.mainMenu();
        }
    }
}

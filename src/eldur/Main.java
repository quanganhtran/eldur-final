/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eldur;

/**
 *
 * @author Anh
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Prologue\n" +
"\n" +
"Zain, a young man with huge goals, has decided to embark on a long adventure ac"
                + "ross the land of Theresus, a vast and wild land. He will enco"
                + "unter \nmany dangers and he will need to improve his skills in "
                + "order to progress! This adventure will be a challenge, his go"
                + "al is to achieve the title, \nKing of Theresus. " +
"His adventure begins in the small secluded town of Elysia, known for its music "
                + "and crafts. This will be a good place for him to \nlearn about "
                + "blacksmithing and refining skills. The surrounding woods are "
                + "known to be full of weak monsters in which adventures begin t"
                + "heir combat \ntraining.\n" +
"After learning basic blacksmithing, he makes his way into the Elysian woods in "
                + "search of monsters to hunt for materials to craft his first sword!"
                + "\n");
        ScreenManager screenManager = new ScreenManager();
        screenManager.execute();
    }
}

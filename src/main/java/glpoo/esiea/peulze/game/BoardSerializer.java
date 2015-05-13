package glpoo.esiea.peulze.game;


import java.io.*;


public class BoardSerializer  {

    public static void serialize(Board board) {
        final FileOutputStream fichier;
        ObjectOutputStream oos = null;
        try {
            fichier = new FileOutputStream("src/main/ressources/save");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(board);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (oos != null) {
                    oos.flush();
                    oos.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static Board deserialize(){
        final FileInputStream fichier;
        ObjectInputStream ois = null;
        Board board = null;
        try {
            fichier = new FileInputStream("src/main/ressources/save");
            ois = new ObjectInputStream(fichier);
            board = (Board) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (final IOException ex) {
                ex.printStackTrace();
            }
            return board;
        }
    }

}


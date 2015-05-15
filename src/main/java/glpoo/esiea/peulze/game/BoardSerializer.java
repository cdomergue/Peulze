package glpoo.esiea.peulze.game;


import java.io.*;


public class BoardSerializer  {

    public static void serialize(Board board) {
        FileOutputStream fichier;
        ObjectOutputStream oos = null;
        try {
            fichier = new FileOutputStream("save");
            oos = new ObjectOutputStream(fichier);
            oos.writeObject(board);
            oos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Mauvais chemin, tentative nouveau chemin");
            try {
                fichier = new FileOutputStream("src/main/ressources/save");
                oos = new ObjectOutputStream(fichier);
                oos.writeObject(board);
                oos.flush();
            } catch (IOException er) {
                er.printStackTrace();

            }
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
        FileInputStream fichier;
        ObjectInputStream ois = null;
        Board board = null;
        try {
            fichier = new FileInputStream("save");
            ois = new ObjectInputStream(fichier);
            board = (Board) ois.readObject();
        } catch (IOException e) {
            fichier = new FileInputStream("src/main/ressources/save");
            ois = new ObjectInputStream(fichier);
            board = (Board) ois.readObject();
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


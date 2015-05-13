package glpoo.esiea.peulze.tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    //Classe avec méthodes statiques
    private Dao() {

    }

    /**
     * Récupère les lignes d'un fichier csv
     *
     * @param file
     * @return liste des lignes
     * @throws IOException
     */
    public static List<String> getLignesFromFile(String file) throws IOException {

        final List<String> lignes = new ArrayList<>();
        final FileReader fr = new FileReader(file);
        final BufferedReader br = new BufferedReader(fr);


        for (String ligne = br.readLine(); ligne != null; ligne = br.readLine()) {

            // Suppression des espaces en trop
            ligne = ligne.trim();

            // Filtre des lignes vides
            if (ligne.isEmpty()) {
                continue;
            }

            // Filtre des lignes de commentaire
            if (ligne.startsWith("#")) {
                continue;
            }

            lignes.add(ligne);
        }

        br.close();
        fr.close();
        return lignes;

    }
}

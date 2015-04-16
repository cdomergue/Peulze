package glpoo.esiea.peulze.game.pieces;

/**
 * Created by Christophe on 03/04/2015.
 */
public class QuarterImpl implements Quarter {
    private QuarterType type;
    private String couleurFond;
    private String forme;
    private String couleurForme;
    private int id;

    public QuarterImpl(int id, QuarterType type, String couleurFond, String forme, String couleurForme) {
        this.type = type;
        this.couleurFond = couleurFond;
        this.forme = forme;
        this.couleurForme = couleurForme;
        this.id = id;
    }

    public QuarterType getType() {
        return type;
    }

    public void setType(QuarterType type) {
        this.type = type;
    }

    public String getCouleurFond() {
        return couleurFond;
    }

    public void setCouleurFond(String couleurFond) {
        this.couleurFond = couleurFond;
    }

    public String getForme() {
        return forme;
    }

    public void setForme(String forme) {
        this.forme = forme;
    }

    public String getCouleurForme() {
        return couleurForme;
    }

    public void setCouleurForme(String couleurForme) {
        this.couleurForme = couleurForme;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

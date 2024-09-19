import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

/**
 * Definit un etudiant
 */
public class Etudiant {
    //Descriptions d'un Etudiant
    private Identite identite;
    private Formation formation;
    private TreeMap<String,List<float>> resultats;

    /**
     * Construit un Etudiant
     * @param identite identite
     * @param formation formation
     */
    public Etudiant(Identite identite, Formation formation) {
        this.identite = identite;
        this.formation = formation;
    }

    /**
     * Ajoute une note à une matière si elle existe
     * @param matiere matiere recherchée
     * @param note note à ajouter
     */
    public void ajoutNote(String matiere, float note){
        //Verifie que la matiere existe dans la formation
        if(!this.formation.matiereExiste(matiere)){
            System.err.println("Matière inexistante");
        //Verifie que 0<note<20
        }else if(note>0 || note<20){
            //Agit en fonciton de la presence ou non de la matiere
            if(!this.resultats.containsKey(matiere)){
                this.resultats.get(matiere).add(note);
            }else{
                List<float> l = new ArrayList<float>();
                l.add(note);
                this.resultats.put(matiere,l);
            }
        //Erreur de note
        }else{
            System.err.println("Note incorrecte");
        }
    }

    /**
     * Renvoie la moyenne de l'Etudiant dans une certaine matiere
     * @param matiere matiere recherchee
     * @return moyenne
     */
    public float calculerMoyenneMatiere(String matiere){
        if(!this.formation.matiereExiste(matiere)){
            System.err.println("Matiere inexistante");
            return -1;
        }
        if(this.resultats.containsKey(matiere)){
            float res = 0;
            int nb = 0;
            for(float f:this.resultats.get(matiere)){
                res += f;
                nb++;
            }
            return res/nb;
        }else{
            return -1;
        }
    }

    public float calculerMoyenneGenerale(){
        if(!this.resultats.isEmpty()){
            float somCoef = 0;
            float somRes = 0;
            for(String mat:this.resultats.keySet()){
                float coeff = this.resultats.get(mat).getCoeff();
                float res = 0;
                int nb = 0;
                for(float note:this.resultats.get(mat)){
                    res += note;
                    nb++;
                }
                somRes = res/nb*coeff;
                somCoef += coeff;
            }
            return somRes / somCoef;
        }else{
            return -1;
        }
    }
}
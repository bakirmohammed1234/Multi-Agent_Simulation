import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;

public class Main {
    public static void main(String[] args) {

        try {
            // Lancer la plateforme JADE
            Runtime rt = Runtime.instance();
            Profile p = new ProfileImpl();

            // Activer l’interface graphique JADE
            p.setParameter(Profile.GUI, "true");

            // Créer le container principal
            AgentContainer container = rt.createMainContainer(p);

            // Lancer AgentUser
            AgentController user = container.createNewAgent(
                    "AgentUser",
                    "AgentUser", // mets ici ton vrai package
                    null
            );

            // Lancer AgentCalcule
            AgentController calc = container.createNewAgent(
                    "AgentCalcule",
                    "AgentCalcule", // mets ici ton vrai package
                    null
            );

            user.start();
            calc.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

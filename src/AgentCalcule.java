import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class AgentCalcule extends Agent {

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " started.");

        addBehaviour(new jade.core.behaviours.CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {
                    try {
                        // Récupération du contenu : a;b;op
                        String[] parts = msg.getContent().split(";");
                        double a = Double.parseDouble(parts[0]);
                        double b = Double.parseDouble(parts[1]);
                        String op = parts[2];

                        double res = switch (op) {
                            case "+" -> a + b;
                            case "-" -> a - b;
                            case "*" -> a * b;
                            case "/" -> b != 0 ? a / b : Double.NaN;
                            default -> 0;
                        };

                        // renvoi du résultat
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContent(String.valueOf(res));
                        send(reply);

                    } catch (Exception e) {
                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.FAILURE);
                        reply.setContent("Erreur de calcul");
                        send(reply);
                    }
                } else {
                    block();
                }
            }
        });
    }
}

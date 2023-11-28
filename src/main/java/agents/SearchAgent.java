package agents;

import jade.core.Agent;
import jade.domain.FIPAException;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.util.leap.Iterator;


public class SearchAgent extends Agent {
    private String service;


    private void searchAgents() {
        DFAgentDescription dfd = new DFAgentDescription();
        //ajouter le service à retrouver
        ServiceDescription sd = new ServiceDescription();
        sd.setType("construction");
        dfd.addServices(sd);
        try {
            DFAgentDescription[] result = DFService.search(this, dfd);
            for (int i = 0; i < result.length; i++) {
                String out = result[i].getName() + " provides";
                Iterator iter = result[i].getAllServices();
                while (iter.hasNext()) {
                    sd = (ServiceDescription) iter.next();
                    out += " " + sd.getName();
                }

                System.out.println(this.getLocalName() + ": " + out);
            }
        } catch (FIPAException fe) {
            System.err.println(getLocalName() + " search with DF unsucceeded - " + fe.getMessage());
            doDelete();
        }
    }


    protected void setup() {
        System.out.println("Hello. I am " + this.getLocalName() + ".");
        this.searchAgents();
    }


}

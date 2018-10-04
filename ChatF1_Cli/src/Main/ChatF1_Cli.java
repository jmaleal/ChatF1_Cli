/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Comunicacao.presencesClient;
import UI.ChatUI;

/**
 *
 * @author jorgeleal
 */
public class ChatF1_Cli {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String[] argsCli = new String[1];

        presencesClient client = new presencesClient();
        new ChatUI().createAndDisplayGUI(client);
    }

}

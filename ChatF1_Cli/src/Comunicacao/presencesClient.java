package Comunicacao;

import java.net.*;
import java.io.*;
import java.util.Scanner;

public class presencesClient {

    static final int DEFAULT_PORT = 8082;
    static final String DEFAULT_HOST = "127.0.0.1";
    private static boolean ativo = false;

    /*OBJETOS LIGAÇÃO*/
    private static BufferedReader in;
    private static PrintWriter out;
    private static Socket ligacao;

    public static void main(String[] args) {
        String servidor = DEFAULT_HOST;
        int porto = DEFAULT_PORT;

        /*DESCOMENTAR.....  
        
        if (args.length != 1) {
            System.out.println("Erro: use java presencesClient <ip>");
            System.exit(-1);
        }*/
        //Cria um objecto da classe InetAddress com base no nome do servidor
        InetAddress endereco = null;
        try {
            endereco = InetAddress.getByName(servidor);
        } catch (UnknownHostException e) {
            System.out.println("Endereco desconhecido: " + e);
            System.exit(-1);
        }

        System.out.println("Vai ligar ao porto " + porto + " da maquina " + endereco.getHostName());

        ligacao = null;

        try {
            ligacao = new Socket(endereco, porto);
            ativo = true;
        } catch (Exception e) {
            System.err.println("erro ao criar socket...");
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("ligado ao porto " + porto + " no endereco " + endereco.getHostName());

        try {
            in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            out = new PrintWriter(ligacao.getOutputStream());

//<editor-fold defaultstate="collapsed" desc="Msg de Ligação">
// Enviar msg de ligacao
            String request = "get" + " " + endereco;

            out.println(request);
            out.flush();

            String msg;
            System.out.println("\n\n\nSERVIDOR:\n");
            while ((msg = in.readLine()) != null && msg.length() > 0) {
                System.out.println(msg);
            }

            /* while ((msg = in.readLine()) != null) {
                System.out.println("Resposta do servidor: " + msg);
            }*/
//</editor-fold>
            //inicia comunicação recorrendo a um método á parte
            iniciaComunicacao();

        } catch (IOException e) {
            System.out.println("Erro ao comunicar com o servidor: " + e);
            System.exit(1);
        }

    }

    private static void iniciaComunicacao() throws IOException {

        Scanner consoleIn = new Scanner(System.in);
        while (ativo) {
            System.out.println("\n\n\nCLIENTE:\nIntroduza msg:");
            String msgIn = consoleIn.next();
            /*CONVERTER PARA ASCII*/
            out.println(msgIn);
            out.flush();

            String msg;
            System.out.println("\n\n\nSERVIDOR:\n");
            while ((msg = in.readLine()) != null && msg.length() > 0) {
                System.out.println(msg);
            }
        }

        ligacao.close();
        System.out.println("Terminou a ligacao!");
    }

}

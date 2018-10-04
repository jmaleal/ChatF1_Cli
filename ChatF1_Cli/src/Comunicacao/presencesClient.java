package Comunicacao;

import java.util.*;
import java.net.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class presencesClient {

    static final int DEFAULT_PORT = 8082;
    static final String DEFAULT_HOST = "127.0.0.1";
    private Socket ligacao;
    private BufferedReader in;
    private PrintWriter out;
    private boolean estado;

    /**
     * Inicializa os objetos ligacao, in e out.
     *
     * @param args
     */
    public void startCli(String[] args) {
        //    public static void main(String[] args) {
        String servidor = DEFAULT_HOST;
        int porto = DEFAULT_PORT;

        if (args.length != 1) {
            System.out.println("Erro: use java presencesClient <ip>");
            System.exit(-1);
        }

        //Cria um objecto da classe InetAddress com base no nome do servidor
        InetAddress endereco = null;
        try {
            endereco = InetAddress.getByName(servidor);
        } catch (UnknownHostException e) {
            System.out.println("Endereco desconhecido: " + e);
            System.exit(-1);
        }

        System.out.println("Vai ligar ao porto " + porto + " da maquina " + endereco.getHostName());

        try {
            ligacao = new Socket(endereco, porto);
        } catch (Exception e) {
            System.err.println("erro ao criar socket...");
            e.printStackTrace();
            System.exit(-1);
        }

        System.out.println("ligado ao porto " + porto + " no endereco " + endereco.getHostName());

        try {
            in = new BufferedReader(new InputStreamReader(ligacao.getInputStream()));

            out = new PrintWriter(ligacao.getOutputStream());

            /* O PRIMEIRO ENVIO É PARA TENTAR ESTABELECER A LIGAÇÃO*/
            String request = "get" + " " + InetAddress.getLocalHost();

            out.println(request);
            out.flush();

            estado = true;
            //   ligacao.close();
            // System.out.println("Terminou a ligacao!");
        } catch (IOException e) {
            System.out.println("Erro ao comunicar com o servidor: " + e);
            System.exit(1);
        }

    }

    public void enviarMsg(String msg) {

        String teste = "Teste";
        /*TRANSFORMAR EM ASCCI ANTES DO ENVIO*/
        out.println(teste);

    }

    private void msgRecebida() {
        try {
            String msg;
            while ((msg = in.readLine()) != null && estado == true) {
                System.out.println("Resposta do servidor: " + msg);
            }
        } catch (IOException ex) {
            Logger.getLogger(presencesClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desligar() {
        out.flush();
    }

}

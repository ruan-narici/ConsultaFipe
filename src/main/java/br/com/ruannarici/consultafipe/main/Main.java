package br.com.ruannarici.consultafipe.main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner reader = new Scanner(System.in);
    private List<String> consultType = new ArrayList<String>(
            Arrays.asList("carro", "caminhao", "moto")
    );


    public void executar() {
        System.out.println("#################################################");
        System.out.println("### Seja bem-vindo(a) ao Sistema ConsultaFipe ###");
        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println("###### Qual consulta você deseja realizar? ######");
        System.out.println("###### [1] Carro | [2] Caminhão | [3] Moto ######");
        System.out.println("#################################################");
        Integer consultTypeReceived = reader.nextInt();
        reader.nextLine();

        System.out.println(consultType.get(consultTypeReceived - 1));
    }
}

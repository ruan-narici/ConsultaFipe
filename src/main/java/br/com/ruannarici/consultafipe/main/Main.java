package br.com.ruannarici.consultafipe.main;

import br.com.ruannarici.consultafipe.model.DataBrand;
import br.com.ruannarici.consultafipe.model.DataModelContainer;
import br.com.ruannarici.consultafipe.service.ConsumeAPI;
import br.com.ruannarici.consultafipe.service.ConvertData;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

    private Scanner reader = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private ConvertData convertData = new ConvertData();
    private static final String URL_ADDRESS = "https://parallelum.com.br/fipe/api/v1/";
    private List<String> consultType = new ArrayList<String>(
            Arrays.asList("carros", "caminhoes", "motos")
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

        if (consultTypeReceived < 1 || consultTypeReceived > 3) {
            throw new RuntimeException("Valor inválido!");
        }

        String jsonBrands = consumeAPI
                .consume(URL_ADDRESS +
                        consultType.get(consultTypeReceived - 1) + "/marcas");
        List<DataBrand> dataBrands = convertData.toListObject(jsonBrands, new TypeReference<List<DataBrand>>() {});

        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println("Segue a lista de " + consultType.get(consultTypeReceived - 1));
        System.out.println("#################################################");
        dataBrands.stream()
                .forEach(data -> System.out.println(
                        "Código: " + data.codigo() + ", Nome: " + data.nome()
                ));
        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println("#### Digite o código do modelo para consulta ####");
        System.out.println("#################################################");

        String codeModel = reader.nextLine();

        String jsonModels = consumeAPI
                .consume(URL_ADDRESS +
                        consultType.get(consultTypeReceived - 1) + "/marcas/" +
                        codeModel + "/modelos");

        System.out.println("#################################################");
        System.out.println("#################################################");
        System.out.println("Segue a lista referente ao código " + codeModel);
        System.out.println("#################################################");
        DataModelContainer dataModelContainer = convertData.toObject(jsonModels, DataModelContainer.class);
        dataModelContainer.modelos().stream()
                .forEach(model -> System.out.println(
                        "Código: " + model.codigo() + ", Nome: " + model.nome()
                ));
        System.out.println("#################################################");
    }
}

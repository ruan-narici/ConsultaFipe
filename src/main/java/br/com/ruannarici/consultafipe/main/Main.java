package br.com.ruannarici.consultafipe.main;

import br.com.ruannarici.consultafipe.model.DataBrand;
import br.com.ruannarici.consultafipe.model.DataModel;
import br.com.ruannarici.consultafipe.model.DataModelContainer;
import br.com.ruannarici.consultafipe.model.DataModelDetails;
import br.com.ruannarici.consultafipe.service.ConsumeAPI;
import br.com.ruannarici.consultafipe.service.ConvertData;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.*;

public class Main {

    private Scanner reader = new Scanner(System.in);
    private ConsumeAPI consumeAPI = new ConsumeAPI();
    private ConvertData convertData = new ConvertData();
    private static final String URL_ADDRESS = "https://parallelum.com.br/fipe/api/v1/";
    private List<String> consultType = new ArrayList<String>(
            Arrays.asList("carros", "caminhoes", "motos")
    );


    public void executar() {
        System.out.println("\n#################################################");
        System.out.println("### Seja bem-vindo(a) ao Sistema ConsultaFipe ###");
        System.out.println("#################################################");
        System.out.println("#################################################\n\n");
        System.out.println("###### Qual consulta você deseja realizar? ######");
        System.out.println("###### [1] Carro | [2] Caminhão | [3] Moto ######");
        System.out.println("#################################################\n");
        Integer consultTypeReceived = reader.nextInt();
        reader.nextLine();

        if (consultTypeReceived < 1 || consultTypeReceived > 3) {
            throw new RuntimeException("Valor inválido!");
        }

        String jsonBrands = consumeAPI
                .consume(URL_ADDRESS +
                        consultType.get(consultTypeReceived - 1) + "/marcas");
        List<DataBrand> dataBrands = convertData.toListObject(jsonBrands,
                new TypeReference<List<DataBrand>>() {});

        System.out.println("\n*************************************************");
        System.out.println("#################################################");
        System.out.println("Segue a lista de " + consultType.get(consultTypeReceived - 1));
        System.out.println("#################################################");
        System.out.println("*************************************************\n");
        dataBrands.stream()
                .forEach(data -> System.out.println(
                        "Código: " + data.codigo() + ", Nome: " + data.nome()
                ));

        System.out.println("\n#################################################");
        System.out.println("#################################################");
        System.out.println("#### Digite o código da  marca para consulta ####");
        System.out.println("#################################################");
        System.out.println("#################################################\n");
        String codeBrand = reader.nextLine();

        String jsonModels = consumeAPI
                .consume(URL_ADDRESS +
                        consultType.get(consultTypeReceived - 1) + "/marcas/" +
                        codeBrand + "/modelos");

        System.out.println("\n*************************************************");
        System.out.println("#################################################");
        System.out.println("Segue a lista referente ao código " + codeBrand);
        System.out.println("#################################################");
        System.out.println("*************************************************\n");
        DataModelContainer dataModelContainer = convertData.toObject(jsonModels, DataModelContainer.class);
        dataModelContainer.modelos().stream()
                .forEach(model -> System.out.println(
                        "Código: " + model.codigo() + ", Nome: " + model.nome()
                ));

        System.out.println("\n#################################################");
        System.out.println("#################################################");
        System.out.println("#### Digite o código do modelo para consulta ####");
        System.out.println("#################################################");
        System.out.println("#################################################\n");
        String codeModel = reader.nextLine();

        String jsonModelsPerYear = consumeAPI.consume(URL_ADDRESS +
                consultType.get(consultTypeReceived - 1) + "/marcas/" +
                codeBrand + "/modelos/" + codeModel + "/anos");

        System.out.println("\n*************************************************");
        System.out.println("#################################################");
        System.out.println("Segue a lista de modelo detalhado do código " + codeModel);
        System.out.println("#################################################");
        System.out.println("*************************************************\n");

        List<DataModel> modelsAndYear = convertData.toListObject(jsonModelsPerYear,
                new TypeReference<List<DataModel>>() {});

        modelsAndYear.stream()
                .forEach(model -> {
                    String jsonDetails = consumeAPI.consume(URL_ADDRESS +
                            consultType.get(consultTypeReceived - 1) + "/marcas/" +
                            codeBrand + "/modelos/" + codeModel + "/anos/" + model.codigo());

                    DataModelDetails dataModelDetails = convertData.toObject(jsonDetails, DataModelDetails.class);
                    System.out.println(dataModelDetails);
                });
        System.out.println("\n#################################################");
        System.out.println("#################################################");

    }
}

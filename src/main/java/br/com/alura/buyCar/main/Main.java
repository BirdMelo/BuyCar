package br.com.alura.buyCar.main;

import br.com.alura.buyCar.model.R_Data;
import br.com.alura.buyCar.model.R_Models;
import br.com.alura.buyCar.model.R_Vehicle;
import br.com.alura.buyCar.model.R_Year;
import br.com.alura.buyCar.service.ApiConsumption;
import br.com.alura.buyCar.service.BooleanStrToInt;
import br.com.alura.buyCar.service.DataConverter;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private final Scanner WRITE = new Scanner(System.in);
    private final String URL = "https://parallelum.com.br/fipe/api/v1/";
    private final DataConverter CONVERTER = new DataConverter();
    private final ApiConsumption LINK = new ApiConsumption();
    private final BooleanStrToInt StrToInt = new BooleanStrToInt();
    public void show(){
        System.out.print("""
                            Opções
                Carros
                Motos
                Caminhões
                
                Escolha:\s""");

        //  Escolhendo veículo:

        String vehicleChoice = WRITE.nextLine();
        String address = String.format(URL+"%s/marcas",vehicleChoice);
        var link = LINK.getData(address);

        //  Visualizando marcas:

        var conBrandLink = CONVERTER.getList(link, R_Data.class);
        conBrandLink.stream()
                .sorted(Comparator.comparing(R_Data::code))
                .forEach(e -> System.out.printf("Código: %d | Nome: %s %n", e.code(), e.name()));

        //  Escolhendo um marca:

        System.out.print("Escolha a marca: ");
        var brandChoice = WRITE.nextLine();
        address += String.format("/%s/modelos",brandChoice);
        link = LINK.getData(address);
        var conModelLink = CONVERTER.getDatum(link, R_Models.class);

        //  Visualizando modelos:

        System.out.println("======================== MODELOS =======================");
        conModelLink.models().stream()
                .sorted(Comparator.comparing(R_Data::code))
                .forEach(e -> System.out.printf("Código: %d | Nome: %s %n", e.code(), e.name()));

        //  Escolhendo modelo:

        System.out.print("Digite o código do modelo ou uma parte do nome para filtrar sua busca: ");
        var modelChoice = WRITE.nextLine();

            //  Filtragem de modelos opcional:

        if (!StrToInt.isInteger(modelChoice)){

            String filterModel = modelChoice;
            List<R_Data> filteredModels = conModelLink.models().stream()
                    .filter(m -> m.name().toLowerCase().contains(filterModel.toLowerCase()))
                    .sorted(Comparator.comparing(R_Data::code))
                    .collect(Collectors.toList());

            System.out.println("============ Modelos filtrados ==============");
            filteredModels.forEach(mf -> System.out.printf("Código: %d | Nome: %s %n", mf.code(), mf.name()));
            System.out.print("Digite o código do modelo do carro: ");
            modelChoice = WRITE.nextLine();
        }

        address+=String.format("/%s/anos",modelChoice);
        link = LINK.getData(address);

            //  Organizando em uma lista de veículos:

        List<R_Year> years = CONVERTER.getList(link,R_Year.class);
        List<R_Vehicle> vehicles = new ArrayList<>();

        for (R_Year year : years) {
            var yearess = String.format("%s/%s", address, year.release());
            link = LINK.getData(yearess);
            R_Vehicle vehicle = CONVERTER.getDatum(link, R_Vehicle.class);
            vehicles.add(vehicle);
        }

        //  Mostrando veículos:

        System.out.println("=================== Todos os veículos ===================");
        vehicles.forEach(v -> System.out.printf("""
                
                Modelo: %s
                Marca: %s
                Preço: %s
                Ano: %d
                Combustível: %s
                =========================================================""",
                v.model(), v.brand(), v.price(), v.modelYear(), v.fuel()));
    }
}

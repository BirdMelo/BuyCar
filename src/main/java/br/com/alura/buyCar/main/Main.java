package br.com.alura.buyCar.main;

import br.com.alura.buyCar.model.R_Data;
import br.com.alura.buyCar.model.R_Models;
import br.com.alura.buyCar.service.ApiConsumption;
import br.com.alura.buyCar.service.DataConverter;

import java.util.Comparator;
import java.util.Scanner;

public class Main {
    private final Scanner WRITE = new Scanner(System.in);
    private final String URL = "https://parallelum.com.br/fipe/api/v1/";
    private final DataConverter CONVERTER = new DataConverter();
    private final ApiConsumption LINK = new ApiConsumption();
    public void show(){
        System.out.print("""
                            Opções
                Carros
                Motos
                Caminhões
                
                Escolha:\s""");

        String vehicleChoice = WRITE.nextLine();
        String address = String.format(URL+"%s/marcas",vehicleChoice);
        var link = LINK.getData(address);
        var conBrandLink = CONVERTER.getList(link, R_Data.class);
        conBrandLink.stream()
                .sorted(Comparator.comparing(R_Data::name))
                .forEach(e -> System.out.printf("Nome: %s | Código: %s%n",e.name(),e.code()));

        System.out.print("Escolha o modelo: ");
        var modelChoice = WRITE.nextLine();

        address+=String.format("/%s/modelos",modelChoice);
        link = LINK.getData(address);
        var conModelLink = CONVERTER.getDatum(link, R_Models.class);
        conModelLink.models().stream()
                .sorted(Comparator.comparing(R_Data::code))
                .forEach(e -> System.out.printf("Nome: %s | Código: %s%n",e.name(),e.code()));

    }
}

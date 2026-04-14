package org.fox.jdbc;

public class Main {

    public static void main(String[] args) {
        DatabaseQueryService service = new DatabaseQueryService();

        System.out.println("=== CLIENT BY ID ===");
        System.out.println(service.findClientById(1));

        System.out.println("\n=== CLIENTS BY NAME ===");
        service.findClientsByName("John").forEach(System.out::println);

        System.out.println("\n=== PROJECTS BY CLIENT ID ===");
        service.findProjectsByClientId(1).forEach(System.out::println);
    }
}



package com.example.CurrencyConverter;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CurrencyConverter {

    private static final String API_URL = "https://api.exchangerate-api.com/v4/latest/USD";
    private static final Set<String> CURRENCIES = Set.of("ARS", "BOB", "BRL", "CLP", "COP", "USD", "EUR", "GBP", "JPY");
    private static final List<String> conversionHistory = new ArrayList<>();

    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String jsonData = response.body().string();
                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonData, JsonObject.class);
                JsonObject rates = jsonObject.getAsJsonObject("rates");

                interactWithUser(rates);
            } else {
                System.err.println("Error en la solicitud: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void interactWithUser(JsonObject rates) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Convertir moneda");
            System.out.println("2. Ver historial de conversiones");
            System.out.println("3. Salir");
            String option = scanner.nextLine();

            switch (option) {
                case "1":
                    handleConversion(rates, scanner);
                    break;
                case "2":
                    displayHistory();
                    break;
                case "3":
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
    }

    private static void handleConversion(JsonObject rates, Scanner scanner) {
        System.out.println("Seleccione la moneda de origen:");
        CURRENCIES.forEach(currency -> System.out.println(currency));
        String fromCurrency = scanner.nextLine().toUpperCase();

        if (!CURRENCIES.contains(fromCurrency)) {
            System.out.println("Moneda no válida. Intente nuevamente.");
            return;
        }

        System.out.println("Seleccione la moneda de destino:");
        CURRENCIES.forEach(currency -> System.out.println(currency));
        String toCurrency = scanner.nextLine().toUpperCase();

        if (!CURRENCIES.contains(toCurrency)) {
            System.out.println("Moneda no válida. Intente nuevamente.");
            return;
        }

        System.out.println("Ingrese el monto a convertir:");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Monto no válido. Intente nuevamente.");
            return;
        }

        double fromRate = rates.get(fromCurrency).getAsDouble();
        double toRate = rates.get(toCurrency).getAsDouble();
        double convertedAmount = convertCurrency(amount, fromRate, toRate);

        String result = String.format("Convertido: %.2f %s a %.2f %s", amount, fromCurrency, convertedAmount, toCurrency);
        System.out.println(result);

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String timestamp = now.format(formatter);

        conversionHistory.add(String.format("%s | %s", timestamp, result));
    }

    private static void displayHistory() {
        if (conversionHistory.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
        } else {
            System.out.println("Historial de conversiones:");
            conversionHistory.forEach(System.out::println);
        }
    }

    private static double convertCurrency(double amount, double fromRate, double toRate) {
        return amount * (toRate / fromRate);
    }
}

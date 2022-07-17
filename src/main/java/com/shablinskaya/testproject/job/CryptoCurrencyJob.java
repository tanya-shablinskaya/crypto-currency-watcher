package com.shablinskaya.testproject.job;

import com.shablinskaya.testproject.repository.model.UserEntity;
import com.shablinskaya.testproject.service.CryptoService;
import com.shablinskaya.testproject.service.UserService;
import com.shablinskaya.testproject.service.modelDto.CryptoPriceDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class CryptoCurrencyJob {
    private static final int TIME_INTERVAL = 60000;
    private static final int PERCENT_OF_CHENGES_IN_CURRENCY = 1;

    private final CryptoService cryptoService;
    private final UserService userService;

    @Scheduled(fixedRate = TIME_INTERVAL)
    public void getBtc() throws Exception {
        String uri = "https://api.coinlore.net/api/ticker/?id=90";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = getClientRequest(uri);
        String responseString = client.send(request, HttpResponse.BodyHandlers.ofString())
                .body();

        getCryptoFromResponse(responseString);
    }

    @Scheduled(fixedRate = TIME_INTERVAL)
    public void getSol() throws Exception {
        String uri = "https://api.coinlore.net/api/ticker/?id=48543";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = getClientRequest(uri);
        String responseString = client.send(request, HttpResponse.BodyHandlers.ofString())
                .body();

        getCryptoFromResponse(responseString);
    }

    @Scheduled(fixedRate = TIME_INTERVAL)
    public void getEth() throws Exception {
        String uri = "https://api.coinlore.net/api/ticker/?id=80";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = getClientRequest(uri);
        String responseString = client.send(request, HttpResponse.BodyHandlers.ofString())
                .body();

        getCryptoFromResponse(responseString);
    }


    private HttpRequest getClientRequest(String uri) {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .GET()
                .build();
    }


    private void getCryptoFromResponse(String response) throws ParseException {
        String formatJsonResponse = response.substring(1, response.length() - 1);
        JSONParser jsonParser = new JSONParser();
        JSONObject parse = (JSONObject) jsonParser.parse(formatJsonResponse);
        Long id = Long.valueOf((String) parse.get("id"));
        String symbol = (String) parse.get("symbol");
        Float newPrice = Float.valueOf((String) parse.get("price_usd"));

        compareValuesPriceCrypto(symbol, newPrice);

        cryptoService.update(id, newPrice);
    }

    private void compareValuesPriceCrypto(String symbol, Float newPrice) {

        CryptoPriceDto currentCrypto = cryptoService.findByPrice(symbol);
        Float currentPrice = currentCrypto.getPrice();
        Float priceChangePercents = Math.abs((((newPrice - currentPrice) / currentPrice)) * 100);
        if (priceChangePercents > PERCENT_OF_CHENGES_IN_CURRENCY) {
            List<UserEntity> users = userService.findByUsersByCode(symbol);
            List<UserEntity> newUsersList = users.stream()
                    .peek(item -> item.setPrice((newPrice - item.getPrice()) / item.getPrice() * 100))
                    .collect(Collectors.toList());
            log.warn("Цена на криптовалюту %s измненилась более чем на 1 процент : ", symbol, newUsersList);
        }
    }
}
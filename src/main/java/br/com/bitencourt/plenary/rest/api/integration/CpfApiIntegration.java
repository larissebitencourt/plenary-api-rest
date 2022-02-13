package br.com.bitencourt.plenary.rest.api.integration;

import br.com.bitencourt.plenary.rest.api.model.CpfApiModel;
import br.com.bitencourt.plenary.rest.api.model.exception.ApiIntegrationException;
import br.com.bitencourt.plenary.rest.api.model.exception.ObjectInvalidException;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class CpfApiIntegration {

    private RestTemplate restTemplate;
    private Environment environment;

    public CpfApiIntegration(RestTemplate restTemplate, Environment environment) {
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    public boolean getCpfAbleVote(String cpf) {

        try {
            String url = environment.getProperty("cpf.api.integration.url") + cpf;

            ResponseEntity<CpfApiModel> response = restTemplate.exchange(url, HttpMethod.GET, null, CpfApiModel.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                CpfApiModel cpfApiModel = response.getBody();

                if (cpfApiModel != null) {
                    return !cpfApiModel.status.equals("UNABLE_TO_VOTE");
                }
                return true;
            }
            return false;
        } catch (HttpStatusCodeException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND)
                throw new ObjectInvalidException("CPF Invalid");

            throw new ApiIntegrationException("CPF query API currently unavailable.");
        }
    }
}

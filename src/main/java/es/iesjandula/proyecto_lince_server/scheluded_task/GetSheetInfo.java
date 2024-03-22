package es.iesjandula.proyecto_lince_server.scheluded_task;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import es.iesjandula.proyecto_lince_server.models.Register;
import es.iesjandula.proyecto_lince_server.respositories.IRegisterRepository;
import lombok.extern.slf4j.Slf4j;

/**
 * @author David Martinez
 * Clase para obtener la informacion de la google sheet con una tarea programada
 */
@Component
@Slf4j
public class GetSheetInfo
{
	@Autowired
	private IRegisterRepository iRegisterRepository;

	/**
	 * Method getSheetInfo, tarea programada que llama cada 5s con un delay de 0 segundos al google script que nos
	 * devuelve los datos de la hoja LINCE
	 */
	@Scheduled(fixedDelayString = "5000", initialDelay = 0)
	public void getSheetInfo()
	{
		// --- INICIALIZAMOS VARIABLES ---
		CloseableHttpClient client = null;
		CloseableHttpResponse response = null;

		try
		{
			// --- INICIAMOS CLIENTE Y CREAMOS PETICION GET ---
			client = HttpClients.createDefault();
			
			HttpGet get = new HttpGet(
				"https://script.google.com/macros/s/AKfycbzoHmTioYZVkpZaPdOZKeb-4iFi5tnI2jG0qJ024-Qa09Iljnf0dLuqsRHJmjaEHz8OFA/exec?spreadsheetId=164c5iaFJFFnxqQX87xJsV6AdSNiq7zcqFqowIn4l9DI&sheet=LINCE");

			
			// --- EJECUTAMOS GET Y GUARDAMOS EN RESPONSE ---
			response = client.execute(get);

			// --- TRANSFORMAMOS RESPONSE A STRING IMPORTANTE PORQUE EL GOOGLE SCRIPT DEVUELVE UN STRING ---
			String stringResponse = EntityUtils.toString(response.getEntity());

			// --- EL STRING ANTERIOR SERA UNA LSITA DE REGISTROS , LO TRANSFORMAMOS CON OBJET MAPPER ---
			List<Register> newRegisterList = Arrays
					.asList(new ObjectMapper().readValue(stringResponse, Register[].class));

			// --- POR CADA REGISTRO LE PONEMOS EL ID 1 , Y LO PONEMOS EN BBDD CON ESTO SOLO TENDREMOS UN UNICO REGISTRO
			// QUE SIMPRE ESTARA ACUTALIZADO ---
			for (Register register : newRegisterList)
			{
				register.setId(1L);
			}
			
			// --- GUARDAMOS Y HACEMOS FLUSH ---
			this.iRegisterRepository.saveAll(newRegisterList);
			this.iRegisterRepository.flush();

		} 
		// IMPORTANTE NO ARROJAR ERRORES , MOSTRAR POR LOG PARA QUE SE INTRODUZCAN EN EL ARCHIVO DE LOGS
		// DEL SERVER , SI ARROJAMOS PARAREMOS EL SERVIDOR COMPLETAMENTE Y LOS CLIENTES CAERAN
		catch (ClientProtocolException exception)
		{
			String error = "Error ClientProtocolException";
			log.error(error,exception);
		} 
		catch (IOException exception)
		{
			String error = "Error IOException";
			log.error(error,exception);
		}

	}
}

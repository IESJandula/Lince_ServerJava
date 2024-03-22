package es.iesjandula.proyecto_lince_server.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Martinez
 * Clase para almacenar registros de la google sheet en la base de datos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "register")
public class Register
{
	/** Attribute id*/
	//@GeneratedValue No lo pondremos como autogenerado , para luego sobreescribir todos los registros como ID 1
	// Con esto obtenemos un solo registro siempre y evitamos duplicar registros ya que todos estaran en la google sheet
	// y el server solo actuara de intermediario entre google y cliente
	// El nombre del valor en JSON corresponde al valor de las CABECERAS de la hoja LINCE
	@Id
	@JsonIgnore
	private Long id;
	
	/** Attribute actualLaps*/
	@Column
	@JsonProperty("ACTUAL_LAPS")
	private String actualLaps;
	
    /** Attribute coordinates*/
	@Column
	@JsonProperty("COORDINATES")
    private String coordinates;
    
    /** Attribute totalTimeSeconds*/
	@Column
	@JsonProperty("TOTAL_TIME_SECONDS")
    private String totalTimeSeconds;
    
    /** Attribute totalDistanceMeters*/
	@Column
	@JsonProperty("TOTAL_DISTANCE_METERS")
    private String totalDistanceMeters;
    
    /** Attribute kilometerMedia*/
	@Column
	@JsonProperty("KM/H_MEDIA")
    private String kilometerMedia;
    
    /** Attribute formatTotalTime*/
	@Column
	@JsonProperty("TOTAL_TIME_HH_MM_SS")
    private String formatTotalTime;
    
    /** Attribute circuitLaps*/
	@Column
	@JsonProperty("CIRCUIT_LAPS")
    private String circuitLaps;
    
    /** Attribute totalCircuitLaps*/
	@Column
	@JsonProperty("TOTAL_CIRCUIT_LAPS")
    private String totalCircuitLaps;
    
    /** Attribute instantKilometerMedia*/
	@Column
	@JsonProperty("KM/H_INSTANTANEA")
    private String instantKilometerMedia;
}

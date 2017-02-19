package types;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import types.Data.enumAttributesOfData;


public class NeuralNetwork {

	/**
	 * Attributes
	 */
	private final transient static String path = "PredictionStocks/";
	private final transient static String topology_name = "_network.eg";
	private transient BasicNetwork topology; //transient to dont parse to Json.
	private ArrayList<enumAttributesOfData> attributes;
	private Integer dateInterval;
	private String stock;
	
	
	/**
	 * Constructor
	 */
	public NeuralNetwork(BasicNetwork topology, ArrayList<enumAttributesOfData> attributes, Integer dateInterval, String stock){
		this.topology = topology;
		this.attributes = attributes;
		this.dateInterval = dateInterval;
		this.stock = stock;
	}
	
	
	public void save(String name) throws IOException{
		Gson gson = new Gson();
		EncogDirectoryPersistence.saveObject(new File(path + name + topology_name), topology);
		String json = gson.toJson(this);
		
		FileWriter writer = new FileWriter(path + name + ".json");
		
		writer.write(json);
		writer.close();
		
	}
	
	public static NeuralNetwork load(String name) throws IOException{
		String stock = "";
		Integer dateInterval = null;
		ArrayList<enumAttributesOfData> attributes = new ArrayList<>();
		
		BasicNetwork topology = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(path + name + topology_name));

		BufferedReader br = new BufferedReader(new FileReader(path + name + ".json"));
		
		JsonReader reader = new JsonReader(br);

		while(reader.hasNext()){
			String jsonName = reader.nextName();
			
			if (jsonName.equals("attributes")){
				reader.beginArray();
				while (reader.hasNext()){
					attributes.add(enumAttributesOfData.valueOf(reader.nextString()));
				}
			}
			else if (jsonName.equals("dateInterval")){
				dateInterval = reader.nextInt();
			}
			else if (jsonName.equals("stock")){
				stock = reader.nextString();
			}
		}
		
		reader.close();

		if (stock.equals("")) throw new IllegalArgumentException("Can't find stock from " + name + ".json");
		if (dateInterval == null) throw new IllegalArgumentException("Can't find dateInterval from " + name + ".json");
		if (attributes.size() == 0) throw new IllegalArgumentException("Can't find attributes from " + name + ".json");
		
		return new NeuralNetwork(topology, attributes, dateInterval, stock);
	}
	
	
	
	/**
	 * Getters
	 */
	public BasicNetwork getTopology() {
		return topology;
	}
	public ArrayList<enumAttributesOfData> getAttributes() {
		return attributes;
	}
	public Integer getDateInterval() {
		return dateInterval;
	}
	public String getStock() {
		return stock;
	}
	
	
	
}

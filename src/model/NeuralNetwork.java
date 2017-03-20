package model;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JOptionPane;

import org.encog.neural.networks.BasicNetwork;
import org.encog.persist.EncogDirectoryPersistence;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import model.Data.enumAttributesOfData;


public class NeuralNetwork {

	private final transient static String PATH = System.getProperty("user.dir") + System.getProperty("file.separator");
	private final transient static String TOPOLOGY_NAME = "_network.eg";
	private transient String name;
	private transient BasicNetwork topology; //transient to dont parse to Json.
	private ArrayList<enumAttributesOfData> attributes;
	private Integer dateInterval;
	private String stock;


	public NeuralNetwork(BasicNetwork topology, ArrayList<enumAttributesOfData> attributes, Integer dateInterval, String stock, String name){
		this.topology = topology;
		this.attributes = attributes;
		this.dateInterval = dateInterval;
		this.stock = stock;
		this.name = name;
	}


	/**
	 * Save this network in 2 files: .eg (nn structure)
	 * and .json (some attributes)
	 * @param name - name to file.
	 * @throws IOException - Cannot write the file.
	 */
	public void save(String name) throws IOException{
		this.setName(name);
		Gson gson = new Gson();
		
		// Check if file already exist
		if (existInSystem(name)){
			if (JOptionPane.showConfirmDialog(null, "This network already exists. Do you want overwrite?", "File exist!", JOptionPane.YES_NO_OPTION)
					== JOptionPane.NO_OPTION){
				JOptionPane.showMessageDialog(null, "Please change the name of network!");
				return;
			}
			else{
				JOptionPane.showMessageDialog(null, "Ok, I will overwrite this network...");
			}
		}
		
		File netwFile = new File(NeuralNetwork.PATH + name + TOPOLOGY_NAME);
		
		EncogDirectoryPersistence.saveObject(netwFile , topology);
		String json = gson.toJson(this);

		FileWriter writer = new FileWriter(NeuralNetwork.PATH + name + ".json");

		writer.write(json);
		writer.close();

	}
	
	/**
	 * Check if exist any network with this name.
	 * @param name
	 * @return true: if exists. false: if not exist
	 */
	public static boolean existInSystem(String name){
		File netwFile = new File(NeuralNetwork.PATH + name);
		return netwFile.exists();
	}

	/**
	 * Load NN from file
	 * @param name - name from file
	 * @return - NN from file
	 * @throws IOException - Cannot acess file, or file not exist
	 */
	public static NeuralNetwork load(String name) throws IOException{		
		String stock = "";
		Integer dateInterval = null;
		ArrayList<enumAttributesOfData> attributes = new ArrayList<>();

		
		// Check if file exist
		if (!existInSystem(name + TOPOLOGY_NAME) || !existInSystem(name + ".json")){
			throw new IOException("File " + name + " don't exists!");
		}
		
		
		BasicNetwork topology = (BasicNetwork) EncogDirectoryPersistence.loadObject(new File(PATH + name + TOPOLOGY_NAME));

		BufferedReader br = new BufferedReader(new FileReader(PATH + name + ".json"));
		// bla bla
		JsonReader reader = new JsonReader(br);
		reader.beginObject();
		
		while(reader.hasNext()){
			String jsonName = reader.nextName();
			if (jsonName.equals("attributes")){
				reader.beginArray();
				while (reader.hasNext()){
					attributes.add(enumAttributesOfData.valueOf(reader.nextString()));
				}
				reader.endArray();
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

		return new NeuralNetwork(topology, attributes, dateInterval, stock, name);
	}

	/**
	 * Predict historical data using his knowledgement.
	 * Warning: the value is not normalized
	 * 
	 * @param normalizer - Normalizer
	 * @param from - Date to begin his predictionn
	 * @param to - Date to end his predictio
	 * @return - HistoricalData from values predicteds
	 * @throws IOException
	 */
	public HistoricalData createNNHistoricalData(Normalizer normalizer, Calendar from, Calendar to) throws IOException{
		// TODO: implement this
		
		
		// Get data from a lot of days before, to be very cautious
		Calendar tempFrom = from;
		tempFrom.add(Calendar.DAY_OF_MONTH, this.dateInterval * -1);
		tempFrom.add(Calendar.MONTH, -1);

		// Create Historical Data from DataExtractor
		HistoricalData rtn = new HistoricalData(this.stock, tempFrom, to, this.dateInterval, this.attributes);
		
		
		
		

		return rtn;
	}
	
	//TODO: remove?
	public static HistoricalData createNNHistoricalData(BasicNetwork network, HistoricalData normalizedData, ArrayList<enumAttributesOfData> attr, Normalizer normal, Calendar from){
		int size = normalizedData.size - normalizedData.getDateInterval();
		HistoricalData rtn = new HistoricalData(size);
		Data dt = new Data();
		ArrayList<Data> datas = new ArrayList<>();

		int i = 0;
		int dateInterval = normalizedData.getDateInterval();
		int attrSize = attr.size();

		double[] input = new double[attr.size() * dateInterval];
		double[] calculatedData = new double[attr.size()];
		double value = 0;

		while (normalizedData.getMapHistorical().get(i).getDate().compareTo(from) < 0){
			i ++;
		}
		int dtDif = i - 1; //start of day adjusted


		//first dateInterval times will have data from real (mixed datas)
		for (int t=0; t<dateInterval; t++){
			//for each time until dateInterval times


			//add real data
			for (int d=0; d<dateInterval-t; d++){
				for (int a=0; a<attrSize; a++){
					//for each attributee

					input[(d  * attrSize) + a] = normalizedData.getMapHistorical().get(dtDif + t + d - dateInterval).getValue(attr.get(a));
				}

			}

			//add calculate data
			for (int d=0; d<t; d++){
				for (int a=0; a<attrSize; a++){
					//for each attribute

					input[((dateInterval - t + d) * attrSize) + a] = datas.get(d).getValue(attr.get(a));
				}

			}
			//now input is ok


			dt = new Data();
			dt.setAttributes(attr);
			dt.setTicker(normalizedData.getMapHistorical().get(0).getTicker());

			network.compute(input, calculatedData);
			Data dtClone = normalizedData.getMapHistorical().get(dtDif + t);
			dt.setDate(dtClone.getDate());

			for(int a=0; a<attr.size(); a++){
				//for each atribute

				//Check: every time will be in order?
				value = calculatedData[a];
				dt.setValue(attr.get(a), value);
			}


			datas.add(dt);
		}
		//end of mixed datas

		//now only calculated
		for (i = dtDif + dateInterval; i<normalizedData.size; i++){
			//for each date

			dt = new Data();
			dt.setAttributes(attr);
			dt.setTicker(normalizedData.getMapHistorical().get(0).getTicker());
			Data dtClone = normalizedData.getMapHistorical().get(i);
			dt.setDate(dtClone.getDate());

			//create input
			for (int d=0; d<dateInterval; d++){
				//for each date

				for (int a=0; a<attrSize; a++){
					//for each attribute

					input[(d * attrSize) + a] = datas.get(i - dtDif - dateInterval + d).getValue(attr.get(a));
				}
			}

			network.compute(input, calculatedData); //generate the calculatedData


			for(int a=0; a<attr.size(); a++){
				//for each atribute

				//Check: every time will be in order?
				value = calculatedData[a];
				dt.setValue(attr.get(a), value);
			}


			datas.add(dt);
		}

		rtn.setMapHistorical(datas);

		//denormalizeValues
		normal.denormalizeDatas(rtn);

		return rtn;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



}

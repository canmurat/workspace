package com.mkyong;

import java.util.Date;

import org.apache.log4j.PropertyConfigurator;
import org.hibernate.Session;

import com.mkyong.stock.Stock;
import com.mkyong.stock.StockDailyRecord;
import com.mkyong.util.HibernateUtil;

public class App {
	public static void main(String[] args) {

		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);

		System.out.println("Hibernate one to many (XML Mapping)");
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Stock stock = new Stock();
		stock.setStockCode("7055");
		stock.setStockName("GARCIEL");
		session.save(stock);

		StockDailyRecord stockDailyRecords = new StockDailyRecord();
		stockDailyRecords.setPriceOpen(new Float("1.9"));
		stockDailyRecords.setPriceClose(new Float("2.1"));
		stockDailyRecords.setPriceChange(new Float("88.0"));
		stockDailyRecords.setVolume(3000000L);
		stockDailyRecords.setDate(new Date());

		stockDailyRecords.setStock(stock);
		stock.getStockDailyRecords().add(stockDailyRecords);
		
		

		session.save(stockDailyRecords);

		session.getTransaction().commit();
		System.out.println("Done");
	}
}
//package net.revature.revpay.config;
//
//import javax.sql.DataSource;
//
//import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class TopicDatasourceConfiguration {
//	@Bean
//	@ConfigurationProperties("spring.datasource.topics")
//	public DataSourceProperties topicsDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//	@Bean
//	public DataSource topicsDataSource() {
//		return topicsDataSourceProperties()
//				.initializeDataSourceBuilder()
//				.build();
//	}
//
//}

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
//public class TodoDatasourceConfiguration {
//	@Bean
//	@ConfigurationProperties("spring.datasource.todos")
//	public DataSourceProperties todosDataSourceProperties() {
//		return new DataSourceProperties();
//	}
//	@Bean
//	public DataSource todosDataSource() {
//		return todosDataSourceProperties() 
//				.initializeDataSourceBuilder()
//				.build();
//		}
//}

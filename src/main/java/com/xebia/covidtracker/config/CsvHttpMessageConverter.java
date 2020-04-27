package com.xebia.covidtracker.config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.xebia.covidtracker.exception.InvalidRequestException;

/**
 * CsvHttpMessageConverter 
 * This converter will be added to the list of HttpMessageCoverter
 * as soon as configuration is loaded. 
 * @author ankit.mishra@xebia.com
 *
 * @param <T>
 * @param <L>
 */
public class CsvHttpMessageConverter<T, L extends ListParam<T>> extends AbstractHttpMessageConverter<L> {

	public CsvHttpMessageConverter() {
		super(new MediaType("text", "csv"));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		try {
		return ListParam.class.isAssignableFrom(clazz);
		}catch(Exception e) {
			throw new InvalidRequestException(e);
		}
	}

	@Override
	protected L readInternal(Class<? extends L> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {
		try {
			HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
			Class<T> t = toBeanType(clazz.getGenericSuperclass());
			strategy.setType(t);
	
			CSVReader csv = new CSVReader(new InputStreamReader(inputMessage.getBody()));
			CsvToBean<T> csvToBean = new CsvToBean<>();
			List<T> beanList = csvToBean.parse(strategy, csv);
			L l = clazz.newInstance();
			l.setList(beanList);
			return l;
		} catch (Exception e) {
			throw new InvalidRequestException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void writeInternal(L l, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		try {
			HeaderColumnNameMappingStrategy<T> strategy = new HeaderColumnNameMappingStrategy<>();
			strategy.setType(toBeanType(l.getClass().getGenericSuperclass()));
	
			OutputStreamWriter outputStream = new OutputStreamWriter(outputMessage.getBody());
			StatefulBeanToCsv<T> beanToCsv = new StatefulBeanToCsvBuilder(outputStream)
					.withQuotechar(CSVWriter.NO_QUOTE_CHARACTER).withMappingStrategy(strategy).build();
			beanToCsv.write(l.getList());
			outputStream.close();
		} catch (Exception e) {
			throw new InvalidRequestException(e);
		}
	}

	@SuppressWarnings("unchecked")
	private Class<T> toBeanType(Type type) {
		try {
			return (Class<T>) ((ParameterizedType) type).getActualTypeArguments()[0];
		}catch(Exception e) {
			throw new InvalidRequestException(e);
		}
	}
}

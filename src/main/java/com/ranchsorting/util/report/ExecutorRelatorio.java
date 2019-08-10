package com.ranchsorting.util.report;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.jdbc.Work;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.Exporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ExecutorRelatorio implements Work {

	// classe responsável por executar o relatório e exportar para PDF.

	private String caminhoRelatorio;
	private HttpServletResponse response;
	private Map<String, Object> parametros;
	private String nomeArquivoSaida;
	private boolean relatorioGerado;

	// construtor da classe com quatro parametros
	public ExecutorRelatorio(String caminhoRelatorio, HttpServletResponse response, Map<String, Object> parametros,
			String nomeArquivoSaida) {
		super();
		this.caminhoRelatorio = caminhoRelatorio;
		this.response = response;
		this.parametros = parametros;
		this.nomeArquivoSaida = nomeArquivoSaida;

		// para tornar valores de moedas em formato brasileiro
		this.parametros.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	}

	@Override
	public void execute(Connection connection) throws SQLException {

		try {
			
			// pegando os bytes de entrada e inserindo no inputStream
			InputStream relatorioStream = this.getClass().getResourceAsStream(this.caminhoRelatorio);
			
			// recebe os dados do relatorio
			JasperPrint print = JasperFillManager.fillReport(relatorioStream, this.parametros, connection);
			
			this.relatorioGerado = print.getPages().size() > 0;
			
			if (this.relatorioGerado) {
				Exporter<ExporterInput, PdfReportConfiguration, PdfExporterConfiguration, OutputStreamExporterOutput> exportador = new JRPdfExporter();
				exportador.setExporterInput(new SimpleExporterInput(print));
				exportador.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));
				
				// indica que o arquivo a ser impresso será em PDF
				response.setContentType("application/pdf");
				
				// esse comando é para fazer o download do arquivo. Se não tiver
				// ele, o arquivo é exibido no browse
				//response.setHeader("Content-Disposition", "attachement; filename=\"" + this.nomeArquivoSaida + "\"");

				exportador.exportReport();
			}
		} catch (Exception e) {
			// o metodo execute tem que lancar uma sqlexception
			throw new SQLException("Erro ao executar relatório " + this.caminhoRelatorio, e);
		}
	}

	public boolean isRelatorioGerado() {
		return relatorioGerado;
	}

}

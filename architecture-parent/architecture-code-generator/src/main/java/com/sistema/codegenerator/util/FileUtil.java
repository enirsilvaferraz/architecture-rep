package com.sistema.codegenerator.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import com.sistema.codegenerator.main.GeradorDeCodigo;
import com.sistema.codegenerator.model.AplicacaoConfig;
import com.sistema.codegenerator.model.CampoConfig;
import com.sistema.codegenerator.model.ClasseConfig;
import com.sistema.codegenerator.model.enums.LogicaTela;
import com.sistema.codegenerator.model.enums.TipoTemplate;

public class FileUtil {

	@SuppressWarnings("resource")
	public static String carregarComponente(ClasseConfig pClasseConfig, CampoConfig pCampoConfig,
			TipoTemplate pTipoTemplate) throws FileNotFoundException {

		Scanner scanner = new Scanner(new FileReader(
				obterTemplateComponente(pClasseConfig, pCampoConfig, pTipoTemplate))).useDelimiter("\\Z");
		String column = "";
		if (scanner.hasNext()) {
			column = scanner.next();
		}
		scanner.close();

		return column;

	}

	protected static String getTemplate(String fileName) {
		return FileUtil.class.getResource("/templates/" + fileName).getPath();
	}

	private static Properties obterArquivoProperties() throws IOException {
		Properties prop = new Properties();
		InputStream in = GeradorDeCodigo.class.getResourceAsStream("/parametrosGerador.properties");
		prop.load(in);
		in.close();
		return prop;
	}

	public static AplicacaoConfig obterConfiguracaoArquivoProperties() throws IOException, ClassNotFoundException {

		Properties prop = obterArquivoProperties();

		AplicacaoConfig lConfig = new AplicacaoConfig();

		lConfig.setCaminhoAbsoluto(prop.getProperty("caminho.absoluto"));
		lConfig.setCaminhoJava(lConfig.getCaminhoAbsoluto() + prop.getProperty("caminho.java"));
		lConfig.setCaminhoResources(lConfig.getCaminhoAbsoluto() + prop.getProperty("caminho.resources"));
		lConfig.setCaminhoWeb(lConfig.getCaminhoAbsoluto() + prop.getProperty("caminho.webapp"));
		lConfig.setCaminhoLabel(lConfig.getCaminhoResources() + "labels.properties");

		return lConfig;
	}

	public static List<ClasseConfig> obterListaClassesModelArquivoProperties() throws ClassNotFoundException,
			IOException, NoSuchFieldException, SecurityException {

		List<ClasseConfig> lListClasseConfig = new ArrayList<ClasseConfig>();
		Properties prop = obterArquivoProperties();

		List<String> lClasspath = new ArrayList<>();

		int iteratorClassPath = 1;
		while (true) {

			if (prop.getProperty("caminho." + iteratorClassPath + ".classpath") != null) {
				lClasspath.add(prop.getProperty("caminho." + iteratorClassPath + ".classpath"));
				iteratorClassPath++;
			} else {
				break;
			}
		}

		int iterator = 1;
		do {

			if (prop.getProperty("classe." + iterator + ".nome") == null) {
				break;
			}

			ClasseConfig lClasseConfig = new ClasseConfig();
			lClasseConfig.setPacoteManagedBean(prop.getProperty("classe." + iterator + ".tela.nome"));
			lClasseConfig.setPacoteManagedBean(prop.getProperty("classe." + iterator + ".pacote.mb"));
			lClasseConfig.setLogicaTela(LogicaTela.valueOf(Integer.valueOf(prop.getProperty("classe." + iterator
					+ ".logica.tela"))));

			// Obter a referencia a classe
			Class<?> lClasse = ClassLoaderExtention.getInstance(lClasspath).loadClass(
					prop.getProperty("classe." + iterator + ".nome"));

			lClasseConfig.setClasse(lClasse);

			// Recuperar Fields
			recuperarListaFields(prop, iterator, lClasseConfig);

			lListClasseConfig.add(lClasseConfig);
			iterator++;

		} while (true);

		return lListClasseConfig;

	}

	public static String obterTemplateCombo() {
		return getTemplate("template-combo.txt");
	}

	public static String obterTemplateComponente(ClasseConfig pClasseConfig, CampoConfig pCampoConfig,
			TipoTemplate pTipoTemplate) {

		String template = "componente-" + pTipoTemplate.toString().toLowerCase() + "-";

		if (pCampoConfig.getField().getType().isInstance(Date.class)) {
			template += "data";
		}

		else if (pCampoConfig.getField().getType().isEnum()) {
			template += "enum";
		}

		else if (pCampoConfig.getField().getType().isInstance(Double.class) && pCampoConfig.getMonetario()) {
			template += "monetario";
		}

		else if (verificarInstanciaObjeto(pCampoConfig.getField().getType())) {
			template += "objeto";
		}

		else if (pCampoConfig.getField().getType() == List.class) {
			template += "lista";
		}

		else {
			template += "texto";
		}

		return getTemplate(template + ".xhtml");

	}

	public static String obterTemplateLista() {
		return getTemplate("template-lista.txt");
	}

	public static String obterTemplateMB(ClasseConfig pClasseConfig) {
		return getTemplate("managedbean-" + pClasseConfig.getLogicaTela().getExtesaoTela() + ".txt");
	}

	public static String obterTemplateTela(ClasseConfig pClasseConfig) {
		return getTemplate("template-" + pClasseConfig.getLogicaTela().getExtesaoTela() + ".xhtml");
	}

	@SuppressWarnings("resource")
	public static String obterTextoArquivo(String lPath) throws FileNotFoundException {

		Scanner scanner = new Scanner(new FileReader(lPath)).useDelimiter("\\Z");
		String texto = "";
		if (scanner.hasNext()) {
			texto = scanner.next();
		}
		scanner.close();
		return texto;
	}

	private static void recuperarListaFields(Properties prop, Integer iterator, ClasseConfig lClasseConfig)
			throws NoSuchFieldException, SecurityException {

		int iterator2 = 1;
		do {

			if (prop.getProperty("classe." + iterator + ".field." + iterator2 + ".nome") == null) {
				break;
			}

			CampoConfig lCampo = new CampoConfig();
			lCampo.setNome(prop.getProperty("classe." + iterator + ".field." + iterator2 + ".nome"));
			lCampo.setObrigatorioCadastro(Boolean.valueOf(prop.getProperty("classe." + iterator + ".field." + iterator2
					+ ".obrigatorio.cadastro")));
			lCampo.setObrigatorioPesquisa(Boolean.valueOf(prop.getProperty("classe." + iterator + ".field." + iterator2
					+ ".obrigatorio.pesquisa")));
			lCampo.setTamanho(Integer.valueOf(prop.getProperty("classe." + iterator + ".field." + iterator2
					+ ".tamanho")));
			lCampo.setMonetario(Boolean.valueOf(prop.getProperty("classe." + iterator + ".field." + iterator2
					+ ".monetario")));
			lCampo.setField(lClasseConfig.getClasse().getDeclaredField(lCampo.getNome()));

			lClasseConfig.getListCampo().add(lCampo);
			iterator2++;

		} while (true);

	}

	public static boolean verificarInstanciaObjeto(Class<?> tipo) {

		// Se for java.lang.String por exemplo
		if (tipo != Object.class && tipo.getPackage().getName().toString().contains("java.")) {
			return false;
		}

		else if (tipo.getSuperclass() == null) {
			return tipo == Object.class;
		}

		else {
			return verificarInstanciaObjeto(tipo.getSuperclass());
		}
	}

}

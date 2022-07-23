import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_pklddlz0";
        // String url = "https://api.mocki.io/v2/549a5d8b";
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();


        String url = "https://api.nasa.gov/planetary/apod?api_key=jPARZgxn9D6y8F2Cd2SQ4DEwm35UQRnqV8grHAI8&start_date=2022-07-19&end_date=2022-07-22";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradorDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImage()).openStream();
            String nomeArquivo = conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
        }
    }
}

package modulos.produtos;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import paginas.LoginPage;

import java.time.Duration;

@DisplayName("Testes Web no modulo de produto")
public class ProdutosTest {

    private WebDriver navegador;

    @BeforeEach
    public void beforeEach(){

        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver-win129\\chromedriver.exe");
        this.navegador = new ChromeDriver();

        //maximizar a tela
        this.navegador.manage().window().maximize();

        //definir tempo de espera padrão de 5 segundos
        this.navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));


        //Navegar para a pagina da Lojinha Web
        this.navegador.get("http://165.227.93.41/lojinha-web/v2/");
    }



    @Test
    @DisplayName("Não e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoIgualAZero() {
        //Fazer login
        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeProduto("Notebook Acer")
                .informarValorProduto("000")
                .informarCoresProduto("Preto, Branco")
                .submeterFormularioDeAdicaoComError()
                .capturarMensagemApresentada();



        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @Test
    @DisplayName("Não é permitido registrar um produto com valor maior que 7.000,00")
    public void testNaoEPermitidoRegistrarProdutoComValorAcimaDeSeteMil(){

        String mensagemApresentada = new LoginPage(navegador)
                .informarOUsuario("admin")
                .informarASenha("admin")
                .submeterFormularioDeLogin()
                .acessarFormularioAdicaoNovoProduto()
                .informarNomeProduto("Notebook Apple")
                .informarValorProduto("700001")
                .informarCoresProduto("Preto, Branco")
                .submeterFormularioDeAdicaoComError()
                .capturarMensagemApresentada();



        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemApresentada);

    }

    @AfterEach
    public void afterEach() {
        //vou fechar o navegador
        navegador.quit();
    }
}

package modulos.produtos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@DisplayName("Testes Web no modulo de produto")
public class ProdutosTest {
    @Test
    @DisplayName("Não e permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoIgualAZero() {
        //Abrir o navegador
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver-win129\\chromedriver.exe");
        WebDriver navegador = new ChromeDriver();

        //maximizar a tela
        navegador.manage().window().maximize();

        //definir tempo de espera padrão de 5 segundos
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));




        //Navegar para a pagina da Lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        //Fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");

        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");

        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        //Vou para a tela de registro do produto

        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //vou preencher os dados do produto e o valor sera igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys("Notebook Acer");
        navegador.findElement(By.id("produtovalor")).sendKeys("000");
        navegador.findElement(By.id("produtocores")).sendKeys("preto, branco");

        //vou submeter o formulario
        navegador.findElement(By.cssSelector("button[type='submit']")).click();

        //vou validar que a mensagem de erro foi aprensentada
        String mensagemToast = navegador.findElement(By.cssSelector(".toast.rounded")).getText();
        Assertions.assertEquals("O valor do produto deve estar entre R$ 0,01 e R$ 7.000,00", mensagemToast);

        //vou fechar o navegador
        navegador.quit();
    }
}

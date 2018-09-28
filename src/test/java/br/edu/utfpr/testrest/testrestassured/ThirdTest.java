package br.edu.utfpr.testrest.testrestassured;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

/**
 *
 * @author andreendo
 * 
 * Testes para o projeto LivrosREST
 */
public class ThirdTest {

    @Test
    public void testLivrosSize() {
        when().
                get("http://localhost:3000/livros").
        then()
                .statusCode(200).
                body("livro.size()", is(3));
    }

    @Test
    public void testLivroInfo() {
        when().
                get("http://localhost:3000/livros/{id}", 6789).
        then()
                .statusCode(200).
                body("titulo", is("Cem Anos de Solidao"));
    }

    @Test
    public void testRemoverLivro() {
        when().
                delete("http://localhost:3000/livros/{id}", 6789).
        then()
                .statusCode(200);
    }
   
    @Test
    public void testAdicionarLivro() {
        Map<String,String> livro = new HashMap<>();
        livro.put("titulo", "Java Como Programar");
        livro.put("autor", "R. Deitel");
        livro.put("ano", "1999");
        livro.put("preco", "67.88");
        
        given().
                contentType(ContentType.JSON).
                body(livro).
        when().
                post("http://localhost:3000/livros").
        then()
                .statusCode(200).
                body("codigo", is(not("")));
    }     
}
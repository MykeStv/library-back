package com.myke.library;

import com.myke.library.model.Book;
import com.myke.library.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class ControllerLibraryTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET/books")
    public void getAllBooks() throws Exception {
        // ARRANGE setup mockup service
        var book1 = new Book();
        book1.setId("1111");
        book1.setIsbn("9780756413712");
        book1.setName("the name of the wind");
        book1.setAuthor("Patrick Rothfuss");
        book1.setCategory("fantasy");
        book1.setType("book");

        var book2 = new Book();
        book2.setId("2222");
        book2.setIsbn("9780316029186");
        book2.setName("the last wish");
        book2.setAuthor("Andrzej Sapkowski");
        book2.setCategory("fantasy");
        book2.setType("book");

        var listBooks = new ArrayList<Book>();
        listBooks.add(book1);
        listBooks.add(book2);
        Mockito.when(bookService.getBooks()).thenReturn(listBooks);

        // ACT: execute get request
        mockMvc.perform(MockMvcRequestBuilders
                .get("/book")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", hasValue("1111")))
                .andExpect(jsonPath("$[0].isbn", hasValue("9780756413712")))
                .andExpect(jsonPath("$[0].name", hasValue("the name of the wind")))
                .andExpect(jsonPath("$[0].author", hasValue("Patrick Rothfuss")))
                .andExpect(jsonPath("$[0].category", hasValue("fantasy")))
                .andExpect(jsonPath("$[0].type", hasValue("book")))
                .andExpect(jsonPath("$[0].borrowed", hasValue("false")))
                .andExpect(jsonPath("$[1].id", hasValue("2222")))
                .andExpect(jsonPath("$[1].name", hasValue("the last wish")));





        //validate retruned files



    }


}

package marcosmello04.github.services;

import marcosmello04.github.controllers.BookController;
import marcosmello04.github.data.dto.v1.BookDTO;
import marcosmello04.github.exception.RequiredObjectIsNullException;
import marcosmello04.github.exception.ResourceNotFoundException;
import marcosmello04.github.model.Book;
import marcosmello04.github.repository.BookRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static marcosmello04.github.mapper.ObjectMapper.parseListObject;
import static marcosmello04.github.mapper.ObjectMapper.parseObject;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class BookServices {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(BookServices.class.getName());

    //Inject
    @Autowired
    BookRepository repository;

    // http://localhost:8080/book
    public List<BookDTO> findAll() {
        logger.info("Listing Books.");

        var books = parseListObject(repository.findAll(), BookDTO.class);
        //ppl.forEach(p -> addHateoasLinks(p));
        books.forEach(this::addHateoasLinks);

        return books;

    }

    // http://localhost:8080/book/id
    public BookDTO findById(Long id) {
        logger.info("Finding Book.");
        var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found."));
        var dto = parseObject(entity, BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    private void addHateoasLinks(BookDTO dto) {
        dto.add(linkTo(methodOn(BookController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(BookController.class).createBook(dto)).withRel("createBook").withType("POST"));
        dto.add(linkTo(methodOn(BookController.class).updateBook(dto)).withRel("updateBook").withType("PUT"));
        dto.add(linkTo(methodOn(BookController.class).deleteBook(dto.getId())).withRel("deleteBook").withType("DELETE"));
    }

    //Post V1
    public BookDTO createBook(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("Creating Book. V1");
        var entity = parseObject(book, Book.class);
        var dto =  parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    //Put
    public BookDTO updateBook(BookDTO book) {
        if (book == null) throw new RequiredObjectIsNullException();
        logger.info("Updating Book.");
        Book entity = repository.findById(book.getId()).orElseThrow(() -> new ResourceNotFoundException("Book not found."));

        entity.setAuthor(book.getAuthor());
        entity.setReleaseDate(book.getReleaseDate());
        entity.setPrice(book.getPrice());
        entity.setTitle(book.getTitle());

        var dto = parseObject(repository.save(entity), BookDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    //Delete
    public void deleteBook(Long id) {
        logger.info("Deleting Book.");
        Book entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Book not found."));

        repository.delete(entity);
    }
}

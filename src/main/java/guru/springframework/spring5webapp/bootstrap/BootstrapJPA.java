package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Address;
import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapJPA implements CommandLineRunner {

    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public BootstrapJPA(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("Martin", "Kleppman");
        Book book1 = new Book("Designing Data intensive apps", "315248534684");
        author1.getBooks().add(book1);
        book1.getAuthors().add(author1);

        authorRepository.save(author1);
        bookRepository.save(book1);

        System.out.println(authorRepository.count());
        System.out.println(bookRepository.count());

        Address address = new Address("7932 Regional Street", "San Ramon", "CA", 82722);
        Publisher publisher = new Publisher("O'Reilly", address);
        publisher.setAddress(address);

        book1.setPublisher(publisher);
        publisher.getBooks().add(book1);

        publisherRepository.save(publisher);

        System.out.println(publisherRepository.count());
        System.out.println(publisher.getBooks().size());

    }
}

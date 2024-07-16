package br.dev.rx.literalura.repository;

import br.dev.rx.literalura.model.Author;
import br.dev.rx.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT DISTINCT a FROM Book b JOIN b.authors a WHERE YEAR(a.birthYear) <= :year")
    List<Author> findByAuthorsBirthYearLessThanEqual(int year);

    @Query("SELECT b.authors FROM Book b")
    List<Author> findAllAuthors();

    @Query("SELECT a FROM Book b JOIN b.authors a WHERE lower(a.name) LIKE lower(concat('%', :name, '%'))")
    List<Author> findByAuthorsName(String name);
}

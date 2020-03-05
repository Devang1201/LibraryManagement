package com.example.library.management;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.library.management.Book;
import com.example.library.management.CreateRequest;
import com.example.library.management.BookRepository;
import com.example.library.management.UserRepository;

@RestController
public class ManagementSystemController {
	
	@Autowired
    BookRepository bookRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RentRepository rentRepository;
	
	@GetMapping(value = "/getBooks")
    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }
	
	@GetMapping(value = "/getUsers")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
	
	@PostMapping(value = "/insertBook")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertBook(@RequestBody CreateRequest createRequest) throws Exception{
        try {
            Book book = new Book(createRequest.getName());
            bookRepository.save(book);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
	
	@PostMapping(value = "/insertUser")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertUser(@RequestBody CreateRequest createRequest) throws Exception{
        try {
            User user = new User(createRequest.getName());
            userRepository.save(user);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
	
	@PostMapping(value = "/insertRent")
    @ResponseStatus(HttpStatus.CREATED)
    public void insertRent(@RequestBody CreateRequest createRequest) throws Exception{
        try {
        	int user_id = userRepository.findByUserName(createRequest.getName());
        	int book_id = bookRepository.findByBookName(createRequest.getName1());
            Rent rent = new Rent(user_id,book_id);
            rentRepository.save(rent);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception();
        }
    }
	
	@GetMapping(value = "/getBookByUser")
    public List<Book> getBookByUserName(@RequestParam(value = "q") String auth){
        List<Book> books = bookRepository.findBooksByUserName(auth);
//        System.out.println(books);
        return books;
    }
	
	@GetMapping(value = "/getUserByBook")
    public List<User> getUserByBookName(@RequestParam(value = "q") String auth){
        List<User> users = userRepository.findUsersByBookName(auth);
//        System.out.println(books);
        return users;
    }
}

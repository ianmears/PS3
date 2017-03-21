package pkgEmpty;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import pkgException.BookException;
import pkgLibrary.Book;
import pkgLibrary.Catalog;

public class TestGetAndAdd {
	private static Catalog ReadXMLFile() {

		Catalog cat = null;

		String basePath = new File("").getAbsolutePath();
		basePath = basePath + "/src/main/resources/XMLFiles/Books.xml";
		File file = new File(basePath);

		System.out.println(file.getAbsolutePath());
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			cat = (Catalog) jaxbUnmarshaller.unmarshal(file);
			System.out.println(cat);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return cat;

	}
	@Test
	public void testGetGoodBook() throws BookException {
		
		Catalog cat = new Catalog();
		
		Date date1 = new Date();
		Book b1 = new Book("bk101","Gambardella, Matthew","XML Developer's Guide",
				"Computer",44.95,35.96,date1,"An in-depth look at creating applications with XML.");
		
		cat=ReadXMLFile();
		//System.out.println(b1);
		//System.out.println(cat.getBook("bk101"));
		assertEquals(cat.getBook("bk101").getId(),b1.getId());
		

	}
	@Test(expected=BookException.class)
	public void testGetBadBook() throws BookException{
		Date date2 = new Date();
		Book b2 = new Book("bk113","Hemingway, Ernest","Old Man and the Sea",
				"Entertainment",5.99,4.79,date2,"A timeless classic");
		Catalog cat=ReadXMLFile();
		//System.out.println(b1);
		//System.out.println(cat.getBook("bk101"));
		assertEquals(cat.getBook("bk113").getId(),b2.getId());
	}
	
	@Test(expected=BookException.class)
	public void testAddDuplicateBook() throws BookException{
		
		Catalog cat = new Catalog();
		
		Date date1 = new Date();
		Book b1 = new Book("bk101","Gambardella, Matthew","XML Developer's Guide",
				"Computer",44.95,35.96,date1,"An in-depth look at creating applications with XML.");
		
		cat=ReadXMLFile();
		//System.out.println(b1);
		//System.out.println(cat.getBook("bk101"));
		cat.addBook(b1);
		assertTrue(true);
	}
	
	@Test()
	public void testAddNewBook() throws BookException{
		
		Catalog cat = new Catalog();
		
		Date date2 = new Date();
		Book b2 = new Book("bk113","Hemingway, Ernest","Old Man and the Sea",
				"Entertainment",5.99,4.79,date2,"A timeless classic");
		
		
		cat=ReadXMLFile();
		cat.addBook(b2);
		assertTrue(true);
	}
	
	
	
	

}
